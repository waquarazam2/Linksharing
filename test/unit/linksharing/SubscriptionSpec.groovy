package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    void "topic should not be null"() {
        setup:
        Subscription subscription = new Subscription(topic: topic)

        when:
        subscription.validate()

        then:
        subscription.errors.getFieldError('topic')?.code == 'nullable'

        where:
        topic << null
    }

    void "user should not be null"() {
        setup:
        Subscription subscription = new Subscription(user: user)

        when:
        subscription.validate()

        then:
        subscription.errors.getFieldError('user')?.code == 'nullable'

        where:
        user << null
    }

    void "Seriousness should not be null"() {
        setup:
        Subscription subscription = new Subscription(seriousness: seriousness)

        when:
        subscription.validate()

        then:
        subscription.errors.getFieldError('seriousness')?.code == 'nullable'

        where:
        seriousness << null
    }
}
