package linksharing

import grails.test.spock.IntegrationSpec

class ResourceRatingIntegrationSpec extends IntegrationSpec {

    void "top posts"() {
        setup:
        List list = ResourceRating.getTopPosts()

        expect:
        list.size() == 5

    }
}
