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

    void "toString"(){
        setup:
        Topic topic=new Topic(name: 'History')

        expect:
        topic.name==topic.toString()
    }

}
