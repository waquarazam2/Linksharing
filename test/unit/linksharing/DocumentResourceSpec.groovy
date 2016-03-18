package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    void "TestingToString"() {
        setup:
        DocumentResource documentResource = new DocumentResource(filePath: 'Home')
        expect:
        documentResource.toString() == "Home"
    }

    void 'File path should not be blank'() {
        given:
        DocumentResource documentResource = new DocumentResource()

        and:
        documentResource.filePath = filePath

        when:
        documentResource.validate()

        then:
        documentResource.errors.getFieldError('filePath')?.code == result

        where:
        filePath | result
        null     | 'nullable'
        '/Home'  | null
    }
}
