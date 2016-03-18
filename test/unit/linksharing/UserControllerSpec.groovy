package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {

    void "index"() {
        when:
        controller.index()

        then:
        response.contentAsString=='User dashboard'
    }

    void "register should render success when user is saved"(){
        setup:
        User.metaClass.save = {
            return true
        }

        when:
        controller.register()

        then:
        response.contentAsString=='success'
    }

   void "register should set flash error on save fail"(){
       setup:
       User.metaClass.save = {
           return  false
       }

       when:
       controller.register()

       then:
       flash.error == 'User not registered'
       flash.message!=null

   }


}
