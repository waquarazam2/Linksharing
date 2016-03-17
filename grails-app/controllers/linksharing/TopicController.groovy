package linksharing

import grails.converters.JSON

class TopicController {

    def index() {

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

    def show(long id) {
        Topic topic = Topic.read(id)
        List<User> subscribedUsers = topic.subscribedUsers

        render(view: "index", model: [users: subscribedUsers, topicName: topic.name])
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
            List resources = Resource.findAllByTopic(topic)
            Subscription.findAllByTopic(topic)*.delete(flush: true)
            resources.each{resource->
                ReadingItem.findAllByResource(resource)*.delete(flush: true)
                ResourceRating.findAllByResource(resource)*.delete(flush: true)
                resource.delete(flush: true)
            }

            topic.delete(flush: true)String
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