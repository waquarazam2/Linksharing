package linksharing
import linksharing.Visibility
class Topic {
    String name;
    User createdBy;
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static  hasMany = [subscriptions:Subscription,resources:Resource]

    static constraints = {
        name(nullable: false,blank: false,unique: true)
        createdBy(nullable: false)
        visibility(nullable: false)
    }
}
