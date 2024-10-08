package event

import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp
import user.TekUser


@RevisionEntity(TekEventRevisionListener.class)
class TekEventRevisionEntity {
    @RevisionNumber
    Long id
    @RevisionTimestamp
    Long timestamp

    TekUser currentUser

    static constraints = {
        currentUser(nullable: true)
    }

    static transients = ['revisionDate']
    Date getRevisionDate() {
        return new Date(timestamp);
    }
}
