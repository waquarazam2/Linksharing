import linksharing.*

class BootStrap {
    def grailsApplication
    def init = {
        println "External file ins incl" + grailsApplication.config.myname
        createUser()
        createTopic()
        createResource()
        subscribeTopics()
        createReadingItems()
        createResourceRatings()
    }

    List<User> createUser() {
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
        if (Resource.count == 0) {
            User.getAll().each {
                if (it?.topics?.size() == 0) {
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
    }

    void createResource() {
        Topic.getAll().each { topic ->
            User topicCreator = topic.createdBy
            (1..2).each {
                DocumentResource documentResource = new DocumentResource(filePath: 'home', description: topic.name, createdBy: topicCreator, topic: topic)

                if (documentResource.save()) {
                    topic.resources.add(documentResource)
                    log.info('document resource add to Topic')
                } else {

                    log.error documentResource.errors
                }
            }
            (1..2).each {
                LinkResource linkResource = new LinkResource(url: 'https://www.google.com', description: topic.name, createdBy: topicCreator, topic: topic)
                if (linkResource.save()) {
                    topic.resources.add(linkResource)
                    log.info('link resource added to topic')
                } else {
                    log.error linkResource.errors
                }
            }
        }

    }

    void subscribeTopics() {
        List<Topic> topics = Topic.getAll()
        List<User> users = User.getAll()
        users.each { user ->
            (topics - user.topics).each {
                Subscription subscription = Subscription.findOrCreateWhere(topic: it, user: user, seriousness: Seriousness.CASUAL)
                if (subscription.save()) {
                    log.info("subscription created for user ${user} and topic ${it}")
                } else {
                    log.error("unable to create subscription for user ${user} and topic ${it}")
                }
            }
        }

    }

    void createReadingItems() {
        List<User> users = User.getAll()
        users.each { user ->
            Set subscribedTopics = user.topics
            subscribedTopics.each { subscribedTopic ->
                Set resources = subscribedTopic.resources
                resources.each { resource ->
                    if (resource.createdBy != user) {
                        ReadingItem readingItem = ReadingItem.findOrCreateWhere(user: user, resource: resource)
                        if (readingItem.save()) {
                            log.info("reading item created for ${user} and ${resource}")
                        } else {
                            log.error(readingItem.errors)
                        }
                    } else {
                        log.info("${resource.createdBy} == ${user}")
                    }

                }
            }
        }
    }

    void createResourceRatings() {
        Set readingItems = ReadingItem.findAllByIsRead(false)
        readingItems.each { readingItem ->
            User user = readingItem.user
            Resource resource = readingItem.resource
            ResourceRating resourceRating = new ResourceRating(user: user, resource: resource, score: 4)
            if (resourceRating.save()) {
                log.info("created resource rating for user ${user} and resource ${resource}")
            } else {
                log.error("unable to create resource rating for user ${user} and resource ${resource}")
            }
        }

    }


    def destroy = {
    }


}
