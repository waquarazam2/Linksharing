package linksharing

import grails.converters.JSON

class SubscriptionController {

    def index() {}

    def delete(long id) {
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.load(id))
        if (subscription && (subscription.topic.createdBy != session.user)) {
            subscription.delete(flush: true)
                    render([message:'deleted successfully']) as JSON
        } else {
         render([error:'unable to delete'])  as JSON
        }


        redirect(controller: 'user', action: 'index')
    }

    def save(long id) {

        Topic topic = Topic.get(id)
        Subscription subscription = new Subscription(topic: topic, user: session.user)

        if (subscription.save(flush: true)) {
            render([message:'saved successfully']) as JSON
        } else {
            render([error:'unable to save']) as JSON
        }


    }

    def update(long id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        if (subscription && Seriousness.convertToEnum(seriousness)) {
            subscription.seriousness = Seriousness.convertToEnum(seriousness)
            if (subscription.save(flush: true)) {
                render([message:'success']) as JSON
            } else {
                render([error:'errors']) as JSON
            }
        } else {
            render([error:'subscription or seriousness not found']) as JSON
        }
    }
}
