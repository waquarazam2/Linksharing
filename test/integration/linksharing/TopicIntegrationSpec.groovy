package linksharing

import grails.test.spock.IntegrationSpec

class TopicIntegrationSpec extends IntegrationSpec {

    void "trending topics"() {

        when:
        List list = Topic.getTrendingTopics()

        then:
        list.size() == 5
    }

    void "get Subscribed users"() {
        setup:
        Topic topic = Topic.load(1)
        User user = new User(email: "waquar.azamd@tothenew.com", userName: "waqdfsuar1234", firstName: "Waquar",
                lastName: "Azam", admin: true, active: true, password: "abcdefgh")
        user.save(flush: true, validate: false)

        List list1 = topic.getSubscribedUsers()
        Subscription subscription = new Subscription(user: user, topic: topic)

        when:
        subscription.save(flush: true)

        then:
        list1.size() + 1 == topic.getSubscribedUsers().size()
    }

    void "is Public"() {
        setup:
        Topic topic = new Topic(visibility: visibility)

        when:
        boolean isPublic = topic.isPublic()

        then:
        isPublic == result

        where:
        visibility         | result
        Visibility.PUBLIC  | true
        Visibility.PRIVATE | false
    }
}
