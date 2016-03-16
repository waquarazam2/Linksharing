package linksharing

import grails.converters.JSON

class TopicController {

    def index() {
        Topic topic = Topic.get(params.id)
        render(view: 'index', model: [users: topic.getSubscribedUsers(), topicName: topic.name])
    }


    def update(long id, String visibility) {
        def message
        Topic topic = Topic.read(id)
        if (topic) {
            topic.visibility = Visibility.convertToEnum(visibility)
            topic.save()
            message = ["message": "Success"]
        } else {
            message = ["message": "Could not Update"]
        }
        render message as JSON
    }


    def show(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                render('success')
            } else {
                List subscriptions = session.user.subscriptions
                if (subscriptions.find { it.topic == topic }) {
                    render('success')
                } else {
                    flash.error = 'Topic nut subscribed'
                    redirect(controller: 'login', action: 'index')
                }
            }
        } else {
            flash.error = 'Topic does not exist!'
            redirect(controller: 'login', action: 'index')
        }
    }

    def save(String topicName, String visibility) {
        Topic topic = new Topic(name: topicName, visibility: Visibility.convertToEnum(visibility), createdBy: session.user)
        if (topic.save()) {
            flash.message = 'topic saved'
        } else {
            log.error topic.errors
            flash.error = topic.errors.toString()
        }
        redirect(controller: 'user', action: 'index')
    }

    def updateTopicName(long id, String topic) {
        def message
        Topic updateTopic = Topic.read(id)
        if (updateTopic) {
            updateTopic.name = topic
            if (updateTopic.validate()) {
                updateTopic.save(flush: true)
                message = ["message": "Topic Updated"]
            } else {
                println updateTopic.errors
                message = ["message": "Could not be Updated"]
            }
        } else {
            message = ["message": "Cannot find topic"]
        }
        render message as JSON
    }

    def delete(long id) {
        def message
        Topic topic = Topic.load(id)
        if (topic) {
            topic.delete(flush: true)
            message = ["message": "Deleted"]
        } else {
            message = ["message": "Topic not Found"]
        }
        render message as JSON
    }

    def customMailService

    def invite(long id,String email) {
        Topic topic = Topic.get(id)
        EmailDTO emailDTO = new EmailDTO(to: [email], subject: "${session.user} invited you to like a topic.", view: "/email/_invite", model: [topic: topic, user: session.user, serverUrl: grailsApplication.config.grails.serverURL])
        customMailService.sendMail(emailDTO)
        redirect(controller: "user", action: "index")
    }

    def join(long id) {
        User invitedUser = session.user
        Topic invitedTopic = Topic.read(id)
        if (Subscription.countByTopicAndUser(invitedTopic, invitedUser)) {
            Subscription newSubscription = new Subscription(topic: invitedTopic, user: invitedUser)
            if (newSubscription.validate()) {
                newSubscription.save()
                flash.message = "Subscribed"
            } else {
                flash.error = "Could'nt save Subscription"
            }
        } else {
            flash.message = "Already Subscribed"
        }
        redirect(controller: "user", action: "index")
    }


}