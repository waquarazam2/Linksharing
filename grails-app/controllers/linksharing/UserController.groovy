package linksharing

import org.springframework.beans.factory.annotation.Autowired

class UserController {

    def userService

    def index() {
        List subscribedTopics = session.user.subscribedTopics
        List readingItems = ReadingItem.getReadingItems(session.user)
        //List<TopicVO> trendingTopics=Topic.getTrendingTopics()
        render(view: "index", model: [subscribedTopics: subscribedTopics, readingItems: readingItems])

    }

    def register(UserCO co) {
        def f = request.getFile('photo')


        log.info("File uploaded: $user.avatarType")
        User user = new User(email: co.email, userName: co.userName, password: co.password, confirmPassword: co.confirmPassword, firstName: co.firstName, lastName: co.lastName, active: true, admin: false)
        user.photo = f.bytes
        user.photoType = f.contentType
        if (user.save()) {
            render 'success'
        } else {
            flash.message = user.errors
            flash.error = 'User not registered'
            render user.errors
        }
    }

//    def myBeanConstructor2
    @Autowired
    CustomBean myBeanConstructor1

    def saveUser() {
        render userService.saveService()
    }
    def assetResourceLocator

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


    def isUsernameValid(String username) {
        int numUser = 0
        numUser = User.countByUsername(username)
        if (numUser >= 1)
            render false
        else
            render true
    }

    def isEmailIdValid(String emailId) {
        int num = 0;
        num = User.countByEmailId(emailId)
        if (num >= 1)
            render false
        else
            render true

    }
    def customMailService


    def forgotPassword(String email) {
        User user = User.findByEmail(email)
        if (user && user.active) {
            int newPassword = RandomPasswordGenerator.generate()
            EmailDTO emailDTO = new EmailDTO(to: [email], subject: "Account Recovery", view: "/user/_password", model: [userName: user.name, newPassword: newPassword, serverUrl: grailsApplication.config.grails.serverURL])
            customMailService.sendMail(emailDTO)
            user.password = newPassword
            user.confirmPassword = newPassword
            user.save(flush: true)
            flash.message = "Success"
        } else {
            flash.error = "Email not for a valid user"
        }
        redirect(controller: "login", action: "index")
    }
}


