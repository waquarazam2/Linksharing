package linksharing

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'userName')
@ToString(includes = 'userName', includeNames = true, includePackage = false)
class User implements Serializable {
    def springSecurityService
    private static final long serialVersionUID = 1
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
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    User(String userName, String password) {
        this()
        this.userName = userName
        this.password = password
    }


    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
        confirmPassword=password
    }


    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    String getName() {
        firstName + " " + lastName
    }


    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]


    static transients = ['authorities', "name", "subscribedTopics"]

    transient confirmPassword
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

    static mapping = {
        password column: '`password`'
        photo sqlType: 'longblob'
        id sort: 'desc'
    }

    String toString() {
        return getName()
    }

    List<Topic> getSubscribedTopics() {
        List<Subscription> subscriptions = Subscription.findAllWhere(user: this)
        return subscriptions*.topic
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

    void changePassword(String password) {
        this.password = password
        this.confirmPassword = password
        this.save(flush: true, failOnError: true)
    }

}
