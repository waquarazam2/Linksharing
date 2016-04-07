package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
class TopicControllerSpec extends Specification {

    void "show public topic"() {
        given:
        Topic.metaClass.'static'.read = { def id ->
            return new Topic(visibility: Visibility.PUBLIC)
        }

        when:
        controller.show()

        then:
        response.contentAsString == 'success'
    }

    void "show not topic subscribed"() {
        given:
        Topic topic = new Topic(visibility: Visibility.PRIVATE)
        Topic.metaClass.'static'.read = { def id ->
            return topic
        }

        and:
        session.user = new User()

        and:
        User.metaClass.subscriptions = {
            null
        }
        when:
        controller.show()

        then:
        response.redirectedUrl == '/login/index'
        flash.error == 'Topic nut subscribed'

    }

    void "topic does not exist"() {
        given:
        Topic.metaClass.'static'.read = { def id ->
            return null
        }

        when:
        controller.show()

        then:
        response.redirectedUrl == '/login/index'
        flash.error == 'Topic does not exist!'
    }

    void "Topic save"() {
        given:
        Topic.metaClass.'static'.save = {
            return true
        }

        when:
        controller.save(name, visibility)

        then:
        response.contentAsString == 'success'
        flash.message == 'topic saved'

        where:
        name      | visibility
        "abcd"    | "public"
        "dfsbsdg" | 'private'
    }

    def "Topic unable to save"() {
        given:
        Topic.metaClass.'static'.save = {
            return false
        }

        when:
        controller.save(name, visibility)

        then:
        response.contentAsString != null
        flash.error != null


        where:
        name      | visibility
        "abcd"    | "public"
        "dfsbsdg" | 'private'
    }

}
