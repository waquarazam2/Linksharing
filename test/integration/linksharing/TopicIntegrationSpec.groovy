package linksharing

import grails.test.spock.IntegrationSpec

class TopicIntegrationSpec extends IntegrationSpec {

    void "trending topics"() {

        when:
        List list=Topic.getTrendingTopics()

        then:
        list.size()==5
    }
}
