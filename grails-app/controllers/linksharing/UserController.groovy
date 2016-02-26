package linksharing

class UserController {

    def index() {
        render 'User dashboard'

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

    def saveUser() {

    }
}
