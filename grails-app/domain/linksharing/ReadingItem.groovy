package linksharing

class ReadingItem {
    Resource resource
    User user
    Boolean isRead
    Date dateCreated
    Date lastUpdated


    static constraints = {
        resource(nullable: false)
        user(nullable: false,unique: 'resource')
        isRead(nullable: false)
    }
}
