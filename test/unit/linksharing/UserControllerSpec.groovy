package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {

    @ConfineMetaClassChanges(value = [User,ReadingItem])
    void "index"() {
        setup:
        User user =new User()
        user.metaClass.getSubscribedTopics={
            return [new Topic()]
        }
        session.user=user
        ReadingItem.metaClass.static.getReadingItems={User usr->
            return [new ReadingItem()]
        }

        when:
        controller.index()

        then:
        response.contentAsString!=null
    }

    void "register should render success when user is saved"(){
        setup:
        UserCO co = new UserCO()
        def mockedUserService = Mock(UserService)
        mockedUserService.register(co) >>new User()
        controller.userService=mockedUserService

        when:
        controller.register(co)

        then:
        response.redirectedUrl !=null
    }

   void "register should set flash error on save fail"(){
       setup:
       UserCO co = new UserCO()
       def mockedUserService = Mock(UserService)
       controller.userService=mockedUserService

       when:
       controller.register(co)

       then:
       flash.error == 'User not registered'
       flash.message!=null

   }


}
