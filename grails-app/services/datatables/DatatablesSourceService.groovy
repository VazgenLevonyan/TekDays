package datatables

import event.TekEvent
import grails.converters.JSON
import grails.transaction.Transactional
import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.support.aware.GrailsApplicationAware
import org.hibernate.SessionFactory
import org.hibernate.envers.query.AuditQuery
import org.hibernate.envers.AuditReaderFactory
import javax.sql.DataSource
import java.util.stream.Collectors

@Transactional
class DatatablesSourceService implements GrailsApplicationAware {

    GrailsApplication grailsApplication
    DataSource dataSource
    SessionFactory sessionFactory

    def dataTablesSource(propertiesToRender, entityName, params) {
        boolean someFilter = false
        Class clazz = grailsApplication.domainClasses.find { it.clazz.simpleName == entityName }.clazz
        def filters = []
        propertiesToRender.eachWithIndex { prop, idx ->
            def sSearchField = params["sSearch_${idx}"]
            if (sSearchField != '') {
                someFilter = true
                filters << "dt.${prop} = '${sSearchField}'"
            }
            if (params.sSearch) {
                if (params["bSearchable_${idx}"] == 'true') {
                    filters << "dt.${prop} like :filter"
                }
            }
        }

        def filter = filters.join(" OR ")

        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []  // Array of data.

        dataToRender.iTotalRecords = clazz.count()
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords

        def query = new StringBuilder("from ${entityName} as dt where dt.id is not null")
        def appendToQuery = ""

        query.append(appendToQuery)
        if (params.sSearch) {
            query.append(" and (${filter})")
        } else if (someFilter) {
            query.append(" and (${filter})")
        }
        def sortingCols = params?.iSortingCols as int
        def orderBy =  new StringBuilder()
        (0..sortingCols-1).each {
            if(it>0) {
                orderBy.append(",")
            }
            def sortDir = params["sSortDir_${it}"]?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
            def sortProperty = propertiesToRender[params["iSortCol_${it}"] as int]
            orderBy.append("dt.${sortProperty} ${sortDir}")
        }
        query.append(" order by ${orderBy}")
        def records
        if (params.sSearch) {
            String sSearch = params.sSearch
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from ${entityName} as dt where dt.id is not null")
            countQuery.append(appendToQuery).append(" and (${filter})")
            def result = clazz.executeQuery(countQuery.toString(), [filter: "%${sSearch}%"])
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            records = clazz.findAll(query.toString(), [filter: "%${sSearch}%"], [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        } else if (someFilter) {
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from ${entityName} as dt where dt.id is not null")
            countQuery.append(appendToQuery).append(" and (${filter})")
            def result = clazz.executeQuery(countQuery.toString())
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            records = clazz.findAll(query.toString(), [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        }
        else {
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from ${entityName} as dt where dt.id is not null")
            countQuery.append(appendToQuery)
            def result = clazz.executeQuery(countQuery.toString())
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            records = clazz.findAll(query.toString(), [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        }

        records?.each { r ->

            def data = []
            propertiesToRender.each { f ->

                data.add((r["${f}"] instanceof BigDecimal)?r["${f}"]:r["${f}"]?.toString())
            }
            dataToRender.aaData << data
        }
       return dataToRender as JSON

    }






    def getRevisions(def tekEventId,propertiesToRender, params ) {

        def dataToRender = [:]
        dataToRender.sEcho = 1
        dataToRender.aaData = []



            List listOfAudited = AuditReaderFactory
                    .get(sessionFactory.currentSession)
                    .createQuery()
                    .forRevisionsOfEntity(TekEvent.class, false, true).getResultList()

        listOfAudited.each {subList->
            def tekEvent=subList[0]
            def tekEventRevision=subList[1]
            def data = []
            propertiesToRender.each {f->
                try {
                    // Dynamically access the property of tekEvent using bracket notation
                    def value = tekEvent[f]  // Access the property dynamically

                    // Handle the case where the property is a Long or other type
                    if (value instanceof Long) {
                        data.add(value.toString())  // Convert Long to String
                    } else {
                        data.add(value?.toString()) // Convert other types to String, if not null
                    }
                } catch (MissingPropertyException e) {
                    // Handle the case where the property does not exist in tekEvent
                    println("Property '${f}' not found in tekEvent")
                    data.add("N/A")  // Add a placeholder if the property is missing
                }
            }
            dataToRender.aaData << data
        }

              println(444)

        dataToRender.iTotalRecords=100
        dataToRender.iTotalDisplayRecords=10
        return dataToRender as JSON



//        def auditQueryCreator = AuditReaderFactory.get(sessionFactory.currentSession).createQuery()
//        def revisionList = []
//        AuditQuery query = auditQueryCreator.forRevisionsOfEntity(TekEvent.class, true, true)
//        query.resultList.each { result->
//           def tekEvent= result[0];
//           def revision = result[1];
//            if(tekEvent.id==tekEventId) {
//                revisionList.add(revision)
//            }
//        }

//        [revisionList: revisionList]
    }

    def getAudit(id)
    {
        def sql = new Sql(dataSource)
        String query = "select a.name, a.description, a.city, a.date_created,a.last_updated, r.current_user_id as changedBy\n" +
                "FROM\n" +
                "    audited_tek_event as a\n" +
                "        join \n" +
                "    tek_event_revision_entity as r on r.id = a.rev\n" +
                "        join \n" +
                "    tek_event as dt on dt.id = a.id\n" +
                "where \n" +
                "    dt.id =${id}"
        def result = sql.rows(query)
        sql.close()
        return result
    }
}
