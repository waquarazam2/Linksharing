package linksharing

class LoginController {

    def index() {
        if(session.user){
            forward(controller: "User", action: "index")
        }
        else{
            render("failure")
        }
    }

    def loginHandler(){
        User user=User.findByUserNameAndPassword(params.userName,params.password)
        if(user){
            if(user.active==true){
                session.user=user
                forward(controller: 'login',action: 'index')
            }
            else{
                flash.error='Your account is not active'
            }
        }
        else{
                flash.error='User not found'
        }
    }




    def logout(){
        session.invalidate()
        forward(controller: 'Login',action: 'index')
    }
}
