package datatables

import event.TekEvent
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.support.aware.GrailsApplicationAware
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import java.time.Instant
import java.time.ZoneId
import java.time.LocalDate


import javax.sql.DataSource

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
        def orderBy = new StringBuilder()
        (0..sortingCols - 1).each {
            if (it > 0) {
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
        } else {
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

                data.add((r["${f}"] instanceof BigDecimal) ? r["${f}"] : r["${f}"]?.toString())
            }
            dataToRender.aaData << data
        }
        return dataToRender as JSON

    }


    def getRevisions(def tekEventId, List propertiesToRender, Map params) {

        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho?.toInteger() ?: 1
        dataToRender.aaData = []

        // Pagination parameters from DataTables
        int displayStart = params.iDisplayStart?.toInteger() ?: 0   // Start index
        int displayLength = params.iDisplayLength?.toInteger() ?: 10 // Page length (number of records per page)

        // Get all audit data for TekEvent class
        List listOfAudited = AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(TekEvent.class, false, true)
                .getResultList()

        // Filter by tekEventId
        List filteredList = listOfAudited.findAll { it[0].id == tekEventId }

        // Total records count (before pagination)
        dataToRender.iTotalRecords = filteredList.size()






        int sortColumnsCount = params.iSortingCols?.toInteger() ?: 0

        if (sortColumnsCount > 0) {
            filteredList.sort { a, b ->
                def comparisonResult = 0

                for (int i = 0; i < sortColumnsCount; i++) {
                    int sortColumnIndex = params["iSortCol_$i"].toInteger()
                    String sortDirection = params["sSortDir_$i"] ?: "asc"
                    String propertyName = propertiesToRender[sortColumnIndex]

                    def valueA = (a[0].hasProperty(propertyName) ? a[0].getAt(propertyName) : a[1].getAt(propertyName))
                    def valueB = (b[0].hasProperty(propertyName) ? b[0].getAt(propertyName) : b[1].getAt(propertyName))

                    // Special handling for 'currentUser' relationship
                    if (propertyName == 'currentUser') {
                        valueA = valueA?.fullName?.toString() ?: ""
                        valueB = valueB?.fullName?.toString() ?: ""
                    }

                    // Determine comparison result based on direction
                    comparisonResult = (sortDirection == "asc") ? (valueA <=> valueB) : (valueB <=> valueA)

                    // If comparison result is not zero, break out of the loop
                    if (comparisonResult != 0) break
                }

                return comparisonResult
            }
        }

        List paginatedList = filteredList.subList(displayStart, Math.min(displayStart + displayLength, filteredList.size()))

        paginatedList.each { subList ->
            def tekEvent = subList[0]
            def tekEventRevision = subList[1]
            def data = []


            propertiesToRender.each { f ->
                try {
                    def value
                    if (tekEvent.hasProperty(f)) {
                        value = tekEvent[f]
                    } else if (tekEventRevision.hasProperty(f)) {
                        value = tekEventRevision[f]

                        // Special handling for the 'currentUser' relationship
                        if (f == 'currentUser' && value) {
                            // Assuming currentUser is a string or can be cast to a string
                            value = value ?: 'Unknown User'
                        }

                        // Special handling for 'timestamp' field: convert to LocalDate format
                        if (f == 'timestamp' && value) {
                            if (value instanceof Long) {
                                value = Instant.ofEpochMilli(value)
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                                        .toString()
                            } else {
                                value = value.toString()
                            }
                        }
                    }

                    data.add(value?.toString() ?: "N/A")
                } catch (MissingPropertyException e) {
                    println("Property '${f}' not found in either tekEvent or tekEventRevision")
                    data.add("N/A")  // Add a placeholder if the property is missing
                }
            }
            dataToRender.aaData << data
        }
        dataToRender.iTotalDisplayRecords = filteredList.size()
        return dataToRender as JSON
    }
}

































