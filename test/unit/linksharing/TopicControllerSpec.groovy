package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
class TopicControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "show public topic"() {
        given:
        Topic.metaClass.'static'.findById={def id->
            return new Topic(visibility: Visibility.PUBLIC)
        }

        when:
        controller.show()

        then:
        response.contentAsString == 'success'
    }

    void "show redirect when topic not public"() {
        given:
        Topic.metaClass.'static'.findById={def id->
            return new Topic(visibility: Visibility.PRIVATE)
        }

        and:
        Subscription.metaClass.find{def it->
            return  true
        }

        and:
        session.user==new User()

        when:
        controller.show()

        then:
        response.redirectedUrl=='/login/index'

    }
}
