package linksharing
import linksharing.Seriousness
class Subscription {
    Topic topic
    User user
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness;

    static constraints = {
        user(nullable: false)
        topic(nullable :false)
        seriousness(nullable: false)
    }
}
