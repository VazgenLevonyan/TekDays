package event


import org.hibernate.envers.RevisionListener
import user.TekUser
import org.codehaus.groovy.grails.web.util.WebUtils

class TekEventRevisionListener implements RevisionListener {

    @Override
    void newRevision(Object entity) {

        TekEventRevisionEntity revisionEntity = (TekEventRevisionEntity) entity
        def session = WebUtils.retrieveGrailsWebRequest().session
        TekUser user = session.user
        revisionEntity.currentUser = user

    }
}