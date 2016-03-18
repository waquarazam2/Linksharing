package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.IgnoreRest
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock(Resource)
@TestFor(ResourceController)
class ResourceControllerSpec extends Specification {

    void "delete successful"() {
        setup:
        Resource resource
        Resource.metaClass.static.load = { def id ->
            return resource
        }
        and:
        resource = new LinkResource()

        resource.metaClass.delete = {}

        when:
        controller.delete()

        then:
        response.contentAsString == ""

    }

    def "delete resource not found"() {
        setup:
        Resource.metaClass.static.load = { def id ->
            return null
        }
        Resource.metaClass.delete = {}
        when:
        controller.delete()

        then:
        response.contentAsString == 'resource not found'
    }
}
