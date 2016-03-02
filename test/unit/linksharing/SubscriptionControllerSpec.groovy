package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.Use

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock([Subscription, Topic, User])
@TestFor(SubscriptionController)
class SubscriptionControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "delete response success when successful"() {
        setup:
        Subscription subscription = new Subscription()

        and:
        Subscription.metaClass.'static'.load = { def id ->
            return subscription
        }

        when:
        controller.delete()

        then:
        response.contentAsString == 'success'
    }

    void "resource not found"() {
        setup:
        Subscription.metaClass.'static'.load = { def id -> }

        when:
        controller.delete()

        then:
        response.contentAsString == 'not found'
    }

    void "save"() {
        setup:
        Topic topic = new Topic()
        topic.save(validate: false)
        Topic.metaClass.'static'.get = { long id ->
            return null
        }
        User user = new User()
        user.save(validate: false)
        session.user = user

        when:
        controller.save(1)

        then:
        response.contentAsString == 'success'

    }

    def "update subscription"() {
        setup:
        Subscription subscription = new Subscription()
        subscription.save(flush: true, validate: false)

        Subscription.metaClass.save = { Map m ->
            return control
        }

        when:
        controller.update(1L, 'serious')

        then:
        response.contentAsString == result

        where:
        control | result
        true    | 'success'
        false   | 'errors'
    }

    def "update subscription or seriousneess not found"(){
        Subscription.metaClass.static.get={Long id->
            return null

        }

        when:
        controller.update(1L,'serious')

        then:
        response.contentAsString=='subscription or seriousness not found'
    }
}
