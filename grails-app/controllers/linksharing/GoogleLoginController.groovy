package linksharing

import grails.plugin.springsecurity.annotation.Secured

class GoogleLoginController {

    def userService
    def googleLoginService
    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def index() {
    User user = googleLoginService.getUserDetails(params.id_token)
    }
}
