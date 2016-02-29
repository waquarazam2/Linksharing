package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock(Resource)
@TestFor(ResourceController)
class ResourceControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "delete"() {
        setup:

        LinkResource resource = new LinkResource()
        Resource.metaClass.'static'.load = { def id ->
            return control
        }

        and:
        resource.metaClass.delete = {}

        when:
        controller.delete()

        then:
        response.contentAsString == result

        where:
        control  | result
        resource | ""
        null     | 'resource not found'


    }
}
