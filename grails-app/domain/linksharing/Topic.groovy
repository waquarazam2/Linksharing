package linksharing

class Topic {
    String name;
    User createdBy;
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static hasMany = [subscriptions: Subscription, resources: Resource]

    static mapping = {
        sort name: 'asc'
    }

    static transients = ['subscribedUsers']

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

    String toString() {
        return name
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Topic)) return false

        Topic topic = (Topic) o

        if (createdBy != topic.createdBy) return false
        if (dateCreated != topic.dateCreated) return false
        if (id != topic.id) return false
        if (lastUpdated != topic.lastUpdated) return false
        if (name != topic.name) return false
        if (resources != topic.resources) return false
        if (subscriptions != topic.subscriptions) return false
        if (version != topic.version) return false
        if (visibility != topic.visibility) return false

        return true
    }

    int hashCode() {
        int result
        result = (name != null ? name.hashCode() : 0)
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0)
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0)
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        result = 31 * result + (subscriptions != null ? subscriptions.hashCode() : 0)
        result = 31 * result + (resources != null ? resources.hashCode() : 0)
        return result
    }

    static List<TopicVO> getTrendingTopics() {
        List trendingTopics = Resource.createCriteria().list(max: 5) {
            projections {
                createAlias('topic', 't')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                property('t.createdBy')
                rowCount('count')
            }

            order('count', 'desc')
            order('t.name', 'desc')
        }

        List<TopicVO> vos = []
        trendingTopics.each {
            TopicVO vo = new TopicVO(id: it[0], topicName: it[1], createdBy: it[3], visibility: it[2], count: it[4])
            vos.add(vo)
        }
        vos
    }

    List<User> getSubscribedUsers() {
        return Subscription.findAllByTopic(this)*.user
    }

    boolean isPublic(){
        return this.visibility==Visibility.PUBLIC
    }

    boolean canViewedBy(User user){
        user?.admin || this.isPublic() || user?.subscribedTopics?.contains(this)
    }

}