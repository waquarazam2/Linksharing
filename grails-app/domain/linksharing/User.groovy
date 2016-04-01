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

    boolean equals(o) {

        if (this.is(o)) return true
        if (!(o instanceof User)) return false

        User user = (User) o

        if (accountExpired != user.accountExpired) return false
        if (accountLocked != user.accountLocked) return false
        if (passwordExpired != user.passwordExpired) return false
        if (active != user.active) return false
        if (admin != user.admin) return false
        if (confirmPassword != user.confirmPassword) return false
        if (dateCreated != user.dateCreated) return false
        if (email != user.email) return false
        if (firstName != user.firstName) return false
        if (id != user.id) return false
        if (lastName != user.lastName) return false
        if (lastUpdated != user.lastUpdated) return false
        if (password != user.password) return false
        if (!Arrays.equals(photo, user.photo)) return false
        if (photoType != user.photoType) return false
        if (readingItems != user.readingItems) return false
        if (resources != user.resources) return false
        if (springSecurityService != user.springSecurityService) return false
        if (subscriptions != user.subscriptions) return false
        if (topics != user.topics) return false
        if (userName != user.userName) return false
        if (version != user.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (springSecurityService != null ? springSecurityService.hashCode() : 0)
        result = 31 * result + (email != null ? email.hashCode() : 0)
        result = 31 * result + (userName != null ? userName.hashCode() : 0)
        result = 31 * result + (password != null ? password.hashCode() : 0)
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0)
        result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0)
        result = 31 * result + (photoType != null ? photoType.hashCode() : 0)
        result = 31 * result + (admin != null ? admin.hashCode() : 0)
        result = 31 * result + (active != null ? active.hashCode() : 0)
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0)
        result = 31 * result + (accountExpired ? 1 : 0)
        result = 31 * result + (accountLocked ? 1 : 0)
        result = 31 * result + (passwordExpired ? 1 : 0)
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        result = 31 * result + (subscriptions != null ? subscriptions.hashCode() : 0)
        result = 31 * result + (topics != null ? topics.hashCode() : 0)
        result = 31 * result + (readingItems != null ? readingItems.hashCode() : 0)
        result = 31 * result + (resources != null ? resources.hashCode() : 0)
        return result
    }

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
