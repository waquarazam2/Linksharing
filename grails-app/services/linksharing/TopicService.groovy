package linksharing

import grails.transaction.Transactional

@Transactional
class TopicService {

    def update(long id,String visibility) {
        println 'called ' + id + '   ' + visibility
        def message
        Topic topic = Topic.read(id)
        if (topic) {
            topic.visibility = Visibility.convertToEnum(visibility)
            topic.save(flush: true, failOnError: true)
            message = ["message": "Success"]
        } else {
            message = ["message": "Could not Update"]
        }
        return  message
    }
}
