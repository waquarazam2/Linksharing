package linksharing

class LinksharingTagLib {
    static namespace = "ls"
    def markAsRead = { attrs, body ->
        User user = session.user
        ReadingItem readingItem = ReadingItem.findById(attrs.id)
        if (user && readingItem) {
            def isRead = readingItem.isRead
            if (isRead) {
                out << g.link(controller: "readingItem", action: "changeIsRead", params: [id: attrs.id, isRead: false], "style": "font-size:10px") {
                    "Mark as Unread "
                }

            } else {
                out << g.link(controller: "readingItem", action: "changeIsRead", params: [id: attrs.id, isRead: true], "style": "font-size:10px") {
                    "Mark as Read "
                }

            }
        }
    }

    def trendingTopics = {
        List<TopicVO> trendingTopics = Topic.getTrendingTopics()
        out << render(template: '/topic/trending', model: [trendingTopics: trendingTopics])
    }

    def topPosts = {
        List topPosts = Resource.topPosts()
        out << render(template: '/shared/topPosts', model: [topPosts: topPosts])
    }

    def canDeleteResouce = { attr, body ->
        User user = session.user
        Resource resource = attr.resource
        (user.canDeleteResource(resource)) ? out << link(controller: "resource", action: "delete", id: resource.id, "Delete") : out << ""

    }

    def showSubscribe = { attr, body ->
        User user = session.user
        (user.isSubscribed(attr.topicId)) ? out << g.link(controller: "subscription", action: "delete", id: "${attr.topicId}", "Unsubscribe") : out << g.link(controller: "subscription", action: "save", id: "${attr.topicId}", "Subscribe")
    }

    def subscriptionCount = { attr, body ->
        int subscription;
        if (attr.topicId) {
            subscription = Subscription.countByTopic(Topic.read(attr.topicId))
        } else if (attr.user) {
            subscription = Subscription.countByUser(session.user)
        } else if (attr.topicId && attr.user) {
            subscription = Subscription.countByUserAndTopic(session.user, Topic.read(attr.topicId))
        }
        out << subscription
    }
    def resourceCount = { attr, body ->
        out << Resource.countByTopic(Topic.read(attr.topicId))
    }
    def topicCount = { attr, body ->
        out << Topic.countByCreatedBy(attr.user)
    }


}

