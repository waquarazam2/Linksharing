package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(Topic)
class TopicSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "validate name"() {
        setup:
        Topic topic = new Topic()

        and:
        topic.name = name

        when:
        topic.validate()

        then:
        topic.errors.getFieldError('name')?.code == result

        where:
        name      | result
        'science' | null
        ''        | 'blank'
        null      | 'nullable'

    }

    void "TestingToString"() {
        setup:
        Topic topic = new Topic(name: 'History')

        expect:
        topic.name == topic.toString()
    }

    void "createdBy"() {
        setup:
        Topic topic = new Topic()

        and:
        topic.createdBy = createdBy

        when:
        topic.validate()

        then:
        topic.errors.getFieldError('createdBy')?.code == result

        where:
        createdBy  | result
        new User() | null
        null       | 'nullable'
    }

    def "visibility"() {
        given:
        Topic topic = new Topic()

        and:
        topic.visibility = visibility

        when:
        topic.validate()

        then:
        topic.errors.getFieldError('visibility')?.code == result

        where:
        visibility         | result
        Visibility.PUBLIC  | null
        Visibility.PRIVATE | null
        null               | 'nullable'
    }

}
