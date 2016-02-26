package linksharing

class TopicController {

    def index() { }

    def show(){
        Topic topic=Topic.findById(params.id)
        if(topic){
            if(topic.visibility==Visibility.PUBLIC){
                render('success')
            }
            else{
               List subscriptions= session.user.subscriptions
                if(subscriptions.find{it.topic==topic}){
                    render('success')
                }
                else{
                    flash.error='Topic nut subscribed'
                    redirect(controller: 'login',action: 'index')
                }
            }
        }

        else{
            flash.error='Topic does not exist!'
            redirect(controller: 'login', action: 'index')
        }
    }
}
