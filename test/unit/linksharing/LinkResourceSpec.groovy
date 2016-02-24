package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "TestingToString"(){
        setup:
        LinkResource linkResource=new LinkResource(url: "https://www.google.com")

        expect:
        linkResource.toString()=="https://www.google.com"
    }

    void "url should be valid"(){
        setup:
        LinkResource linkResource=new LinkResource()

        and:
        linkResource.url=url

        when:
        linkResource.validate()

        then:
        linkResource.errors.getFieldError('url')?.code==result

        where:
        url|result
        "www.google.com"|'url.invalid'
        "https://www.google.com"|null
        ''|'blank'
    }
}
