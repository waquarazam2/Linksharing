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

    void "show topic subscribed"() {
        given:
        Topic topic=new Topic(visibility: Visibility.PRIVATE)
        Topic.metaClass.'static'.findById={def id->
            return topic
        }

        and:
        session.user=new User()

        and:
        User.metaClass.subscriptions={
            null
        }
        when:
        controller.show()

        then:
        response.redirectedUrl=='/login/index'
        flash.error == 'Topic nut subscribed'

    }

    void "topic not subscribed"(){
        given:
        Topic.metaClass.'static'.findById={def id->
            return null
        }

        when:
        controller.show()

        then:
        response.redirectedUrl=='/login/index'
        flash.error=='Topic does not exist!'
    }
}
