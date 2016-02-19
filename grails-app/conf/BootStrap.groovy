import linksharing.*

class BootStrap {
    def grailsApplication
    def init = {
        println "External file ins incl" + grailsApplication.config.myname
        createUser()
        createTopic()
        createResource()

    }

    List<User> createUser() {
        println "called"
        if (User.count == 0) {
            User admin = new User(email: 'waquar.azam@tothenew.com', userName: 'waquarazam2', password: '123456dfefe', firstName: 'Waquar', lastName: 'Azam', admin: true, active: true)
            User normalUser = new User(email: 'mohan.gupta@tothenew.com', userName: 'mohan1243', password: 'sa231dwerfewrc', firstName: 'Mohan', lastName: 'Gupta', admin: false, active: true)
            List users = []
            if (admin.save(flush: true, failOnError: true)) {
                log.info("successfully created ${admin}")
                users.add(admin)
            } else {
                log.error("failed to create ${admin}")
            }
            if (normalUser.save(flush: true, failOnError: true)) {
                log.info("sccessfully created ${normalUser}")
            } else {
                log.error("failed to create ${normalUser}")
            }
            return users
        }
    }

    void createTopic() {
        User.getAll().each {
            if (it.topics.size() == 0) {
                (1..5).each { index ->
                    Topic topic = new Topic(name: "name ${index} ${it}", createdBy: it, visibility: Visibility.PUBLIC)
                    if (topic.save()) {
                        log.info("Created topic ${topic}")
                    } else {
                        log.error("unable to create ${topic}")
                    }
                }
            }
        }
    }

    void createResource() {
        if (Resource.count == 0) {
            Topic.getAll().each {index->
                (1..2).each {
                    DocumentResource documentResource = new DocumentResource(filePath: 'home',description: index.name)
                    if (documentResource.save()) {
                        it.resources.add(documentResource)
                        log.info('document resource add to Topic')
                    }
                }
                (1..2).each {
                    LinkResource linkResource = new LinkResource(url: 'www.google.com',index.name)
                    if (linkResource.save()) {
                        it.resources.add(linkResource)
                        log.info('link resource added to topic')
                    }
                }
            }
        }
    }

    void subscribeTopics() {
        Topic topics = Topic.getAll()
        User users = User.getAll()

    }


    def destroy = {
    }


}
