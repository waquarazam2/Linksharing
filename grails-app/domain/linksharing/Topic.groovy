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
        user.admin || this.isPublic() || user.subscribedTopics.contains(this)
    }

}