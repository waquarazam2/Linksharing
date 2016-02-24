package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "User should not be null"() {
        given:
        ResourceRating resourceRating = new ResourceRating()

        and:
        resourceRating.user = user

        when:
        resourceRating.validate()

        then:
        resourceRating.errors.getFieldError('user')?.code == result

        where:
        user       | result
        new User() | null
        null       | 'nullable'
    }

    void "resource should not be null"() {
        given:
        ResourceRating resourceRating = new ResourceRating()

        and:
        resourceRating.resource = resource

        when:
        resourceRating.validate()

        then:
        resourceRating.errors.getFieldError('resource')?.code == result

        where:
        resource               | result
        new LinkResource()     | null
        new DocumentResource() | null
        null                   | 'nullable'

    }

    def "score"() {
        given:
        ResourceRating resourceRating = new ResourceRating()

        and:
        resourceRating.score = score

        when:
        resourceRating.validate()

        then:
        resourceRating.errors.getFieldError('score')?.code == result

        where:
        score | result
        1     | null
        5     | null
        23    | 'max.exceeded'
        0     | 'min.notmet'
    }
}

