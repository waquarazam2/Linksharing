package linksharing
class Topic {
    String name;
    User createdBy;
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static hasMany = [subscriptions: Subscription, resources: Resource]

    static constraints = {
        name(nullable: false, blank: false, unique: true)
        createdBy(nullable: false)
        visibility(nullable: false)

    }

    def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(topic: this, createdBy: this.createdBy, seriousness: Seriousness.VERY_SERIOUS)
            subscription.save()
        }
    }

    String toString(){
        return name
    }
}
