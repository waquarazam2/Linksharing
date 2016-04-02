package linksharing

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    def userService

    def index() {
        List subscribedTopics = session.user?.subscribedTopics
        List readingItems = ReadingItem.getReadingItems(session.user)
        //List<TopicVO> trendingTopics=Topic.getTrendingTopics()
        render(view: "index", model: [subscribedTopics: subscribedTopics, readingItems: readingItems,loggedInUser:session.user])

    }

    def register(UserCO co) {
        User user=userService.register(co)
        if (user) {
            session.user=user
            redirect(controller: 'login', action: 'index')
        } else {
            flash.message = 'errors'
            flash.error = 'User not registered'
        }
    }


    def assetResourceLocator

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def image(Long id) {
        User user = User.get(id)
        byte[] image
        if (user.photo) {
            image = user.photo
        } else {
            image = assetResourceLocator.findAssetForURI('1.jpg').byteArray
        }
        response.contentType = user.photoType
        response.contentLength = image.size()
        OutputStream out = response.outputStream
        out.write(image)
        out.close()
    }


    def isUsernameValid(String userName) {
        int numUser = 0
        numUser = User.countByUserName(userName)
        if (numUser >= 1)
            render false
        else
            render true
    }

    def isEmailIdValid(String emailId) {
        int num = 0;
        num = User.countByEmail(emailId)
        if (num >= 1)
            render false
        else
            render true

    }
    def customMailService

    def forgotPassword(String email) {
        User user = User.findByEmail(email)
        if (user && user.active) {
            String newPassword = RandomPasswordGenerator.generate()
            EmailDTO emailDTO = new EmailDTO(to: [email], subject: "Account Recovery", view: "/email/_password", model: [userName: user.name, newPassword: newPassword, serverUrl: grailsApplication.config.grails.serverURL])
            customMailService.sendMail(emailDTO)
            user.changePassword(newPassword)
            flash.message = "Success"
        } else {
            flash.error = "Email not for a valid user"
        }
        redirect(controller: "login", action: "index")
    }

    def save(UpdateProfileCO co) {
        def f = request.getFile('photo')
        byte[] a = f.bytes
        Long id = session.user.id

        if (userService.save(id, co.firstName, co.lastName, co.userName, a)) {
            render([message: 'success']) as JSON
        } else {
            render([error: 'failure']) as JSON
        }

    }

    def edit() {
        render(view: 'edit', model: [user: session.user])
    }

    def updatePassword(UpdatePasswordCO co) {
        if (userService.updatePassword(session.user.id, co.password, co.confirmPassword)) {
            render([message: 'success']) as JSON
        } else {
            render([error: 'failure']) as JSON
        }
        render(view: 'edit')
    }

    def userShow() {
        render(view: "users", model: [userCount: User.count])
    }


    def loadUserTable(String q, String sortBy) {
        def userList=userService.loadUserTable(q,sortBy,params)
        render(template: "/user/userTable", model: [users: userList])
    }

    def activateUser(long userId, boolean activate) {
        println userId + "     " + activate
        if (userService.changeActivation(userId, activate)) {
            render([message: 'success']) as JSON
        } else {
            render([message: 'failure']) as JSON
        }

    }

    def invite(String email, String topic) {
        println 'called'+topic
        EmailDTO emailDTO = new EmailDTO(to: [email], subject: "Topic Invitation", view: "/email/_invite", model: [user: session.user.name,topic:topic, id:Topic.findByName(topic).id,serverUrl: grailsApplication.config.grails.serverURL])
        customMailService.sendMail(emailDTO)
        redirect(controller: 'user',action: 'index')
    }
}


