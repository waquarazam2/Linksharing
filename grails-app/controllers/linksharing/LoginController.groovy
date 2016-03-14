package linksharing

class LoginController {

    def index() {
        if(session.user){
            forward(controller: "User", action: "index")
        }
        else{
            //render("failure")
            render(view: "index")
        }
    }

    def loginHandler(){
        User user=User.findByUserNameAndPassword(params.userName,params.password)
        if(user){
            if(user.active==true){
                session.user=user
                redirect(controller: 'login',action: 'index')
            }
            else{
                flash.error='Your account is not active'
                render flash.error
            }
        }
        else{
                flash.error='User not found'
                render flash.error
        }
    }




    def logout(){
        session.invalidate()
        forward(controller: 'Login',action: 'index')
    }
}
