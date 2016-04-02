package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    void "User should not be null"() {
        given:
        ReadingItem readingItem = new ReadingItem()

        and:
        readingItem.user = user

        when:
        readingItem.validate()

        then:
        readingItem.errors.getFieldError('user')?.code == result

        where:
        user       | result
        new User() | null
        null       | 'nullable'
    }

    void "resource should not be null"() {
        given:
        ReadingItem readingItem = new ReadingItem()

        and:
        readingItem.resource = resource

        when:
        readingItem.validate()

        then:
        readingItem.errors.getFieldError('resource')?.code == result

        where:
        resource               | result
        new LinkResource()     | null
        new DocumentResource() | null
        null                   | 'nullable'

    }

    void "isRead should not be null"() {
        given:
        ReadingItem readingItem = new ReadingItem()

        and:
        readingItem.isRead = isRead

        when:
        readingItem.validate()

        then:
        readingItem.errors.getFieldError('isRead')?.code == result

        where:
        isRead | result
        null   | 'nullable'
        true   | null
        false  | null
    }
}
