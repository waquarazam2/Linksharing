package linksharing

class TopicController {

    def index() {
        Topic topic=Topic.get(params.id)
        render(view:'index',model: [users:topic.getSubscribedUsers(),topicName:topic.name])
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

    def update() {

    }
}
