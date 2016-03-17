package linksharing

import grails.converters.JSON

class SubscriptionController {

    def index() {}

    def delete(long id) {
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.load(id))
        if (subscription && (subscription.topic.createdBy != session.user)) {
            subscription.delete(flush: true)
                    render(ls.showSubscribe(topicId:id))
        } else {
         render([error:'unable to delete'])  as JSON
        }


        redirect(controller: 'user', action: 'index')
    }

    def save(long id) {

        Topic topic = Topic.get(id)
        Subscription subscription = new Subscription(topic: topic, user: session.user)

        if (subscription.save(flush: true)) {
            render(ls.showSubscribe(topicId: id))
        } else {
            render([error:'unable to save']) as JSON
        }


    }

    def update(String seriousness,long id) {
        def message
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.read(id))
        if (subscription) {
            subscription.seriousness = Seriousness.convertToEnum(seriousness)
            subscription.save(flush: true)
            message = ["message": "Success"]
        } else {
            message = ["message": "Could not Update"]
        }
        render message as JSON
    }
}
