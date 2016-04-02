package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    void "Login index forward"() {
        setup:
        session.user = new User()

        when:
        controller.index()

        then:
        response.forwardedUrl == '/user/index'
    }

    void "Login index should render failure when Session.user is not set"() {
        when:
        controller.index()

        then:
        response.contentAsString == "failure"
    }

    void "Login Handler"() {
        setup:
        User.metaClass.'static'.findByUserNameAndPassword = { String username, String password ->
            return new User(active: active)
        }

        when:
        controller.loginHandler()

        then:
        response.forwardedUrl == result
        flash.error == error

        where:
        active | result         | error
        true   | '/login/index' | null
        false  | null           | 'Your account is not active'
    }

    void "Login Handler should set error"() {
        setup:
        User.metaClass.'static'.findByUserNameAndPassword = { String username, String password ->
            return null
        }

        when:
        controller.loginHandler()

        then:
        flash.error == 'User not found'
    }

    void "logout"() {
        setup:
        session.user = new User()

        when:
        controller.logout()

        then:
        session.user == null
        response.forwardedUrl == '/login/index'
    }

}
