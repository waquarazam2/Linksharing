package linksharing

import org.springframework.beans.factory.annotation.Autowired

class UserController {

    def index() {
        List subscribedTopics=session.user.subscribedTopics
        List readingItems=ReadingItem.getReadingItems(session.user)
        //List<TopicVO> trendingTopics=Topic.getTrendingTopics()
        render(view: "index",model: [subscribedTopics:subscribedTopics,readingItems:readingItems])

    }

    def register() {
        User user = new User(email: params.email, userName: params.userName, password: params.password, confirmPassword: params.confirmPassword, firstName: params.firstName, lastName: params.lastName, active: params.active, admin: params.admin)
        if (user.save()) {
            render 'success'
        } else {
            flash.message =user.errors
            flash.error ='User not registered'
        }
    }

//    def myBeanConstructor2
    @Autowired
    CustomBean myBeanConstructor1

    def saveUser() {
       render userService.saveService()
    }
}
