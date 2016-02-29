package linksharing
class Subscription {
    Topic topic
    User user
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness=Seriousness.SERIOUS;

    static mapping = {
        topic lazy: false
        user lazy: false
    }
    static constraints = {
        user(nullable: false)
        topic(nullable :false)
        seriousness(nullable: false)
    }
}
