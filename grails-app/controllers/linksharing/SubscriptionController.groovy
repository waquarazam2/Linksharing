package linksharing
class SubscriptionController {

    def index() {}

    def delete(){
        Subscription subscription=Subscription.load(params.id)
        if(subscription){
            subscription.delete()
            render 'success'
        }else{
            render 'not found'
        }
    }

    def save(long id){
        Topic topic=Topic.get(id)
        Subscription subscription =new Subscription(topic: topic,user:session.user)

        if(subscription.save()){
            render 'success'
        }

        else{
            render subscription.errors
        }
    }

    def update(long id,String seriousness){
        Subscription subscription=Subscription.get(id)
        if(subscription && Seriousness.convertToEnum(seriousness)){
            subscription.seriousness=Seriousness.convertToEnum(seriousness)
            if(subscription.save(flush: true)){
                render 'success'
            }else{
                render 'errors'
            }
        }else{
            render 'subscription or seriousness not found'
        }
    }
}
