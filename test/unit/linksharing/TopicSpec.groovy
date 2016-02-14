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

    void "name should be unique"() {
        setup:
        Topic topic = new Topic(name: 'History')

        and:
        topic.save()

        and:
        Topic topic2=new Topic(name:'History')

        when:
        topic2.save()

        then:
        topic2.errors.getFieldError('name')?.code=='unique'
    }
}
