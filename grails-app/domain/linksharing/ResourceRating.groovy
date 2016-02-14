package linksharing

class ResourceRating {
    Resource resource;
    User user
    int score
    Date dateCreated
    Date lastUpdated

    static constraints = {
        user(nullable: false)
        resource(nullable: false)
        score(nullable:false,max: 5,min: 1)
        user(unique: 'resource')

    }
}
