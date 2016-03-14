package linksharing

import grails.converters.JSON

class SubscriptionController {

    def index() {}

    def delete(long id) {
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.load(id))
        if (subscription) {
            subscription.delete(flush: true)
                    render([message:'deleted successfully',status:true] as JSON)
        } else {
         render([error:'unable to delete',status: true] as JSON)
        }


      //  redirect(controller: 'user', action: 'index')
    }

    def save(long id) {

        Topic topic = Topic.get(id)
        Subscription subscription = new Subscription(topic: topic, user: session.user)

        if (subscription.save(flush: true)) {
            flash.message = 'saved successfullyt'
        } else {
            flash.error = 'unable to save'
        }

        redirect(controller: 'user', action: 'index')
    }

    def update(long id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        if (subscription && Seriousness.convertToEnum(seriousness)) {
            subscription.seriousness = Seriousness.convertToEnum(seriousness)
            if (subscription.save(flush: true)) {
                render 'success'
            } else {
                render 'errors'
            }
        } else {
            render 'subscription or seriousness not found'
        }
    }
}
