package linksharing

class User {
    String email
    String userName
    String password
    String firstName
    String lastName
    byte[] photo
    String photoType
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated

    static transients = ["name", "subscribedTopics"]
    transient confirmPassword

    static mapping = {
        photo sqlType: 'longblob'
        id sort: 'desc'
    }

    String getName() {
        firstName + " " + lastName
    }

    List<Topic> getSubscribedTopics() {
        List<Subscription> subscriptions = Subscription.findAllWhere(user: this)
        return subscriptions*.topic

    }

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]
    static constraints = {
        email(unique: true, email: true, nullable: false, blank: false)
        password(nullable: false, blank: false, minSize: 5)
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        photoType(nullable: true)
        confirmPassword(bindable: true, validator: { val, obj ->
            if (obj.id) {
                return true
            } else {
                return val == obj.password
            }
        })
    }

    String toString() {
        return getName()
    }

    boolean canDeleteResource(Resource resource) {
        return (resource?.createdBy && resource?.createdBy == this)
    }

    int getScore(Resource resource) {
        ResourceRating resourceRating = ResourceRating.findByUserAndResource(this, resource)
        int score = 1
        if (resourceRating) {
            score = resourceRating.score
        }
        return score
    }

    boolean isSubscribed(long topicId) {
        return Subscription.findByUserAndTopic(this, Topic.read(topicId))
    }

    Subscription getSubscription(long topicId) {
        Subscription.findByUserAndTopic(this, Topic.load(topicId))
    }

//    boolean  equals(User user1,User user2){
//       user1==user2
//    }

    static List<User> getUsersWithUnreadItems() {
        List users = ReadingItem.createCriteria().listDistinct {
            projections {
                property('user')
            }
            eq('isRead', false)
        }
        return users
    }

    void changePassword(String password){
        this.password=password
        this.confirmPassword=password
        this.save(flush: true,failOnError: true)
    }

}
