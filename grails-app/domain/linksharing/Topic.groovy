package linksharing
class Topic {
    String name;
    User createdBy;
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static hasMany = [subscriptions: Subscription, resources: Resource]

    static  mapping = {
        sort name:'asc'
    }

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

    static TopicVO getTrendingTopics(){
        List trendingTopics= Resource.createCriteria().list(max:5) {
            projections {
                createAlias('topic','t')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                property('t.createdBy')
                count()
            }

            order('count','desc')
            order('topic.name','desc')
        }

    List<TopicVO> vos = []
        trendingTopics.each{
            TopicVO vo =new TopicVO(id:it[0],topicNamrame:it[1],createdBy: it[2],visibility: it[3],count: it[4])
            vos.add(vo)
        }
        vos
    }
}
