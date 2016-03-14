package linksharing

class LinksharingTagLib {
    static namespace = "ls"
    def markAsRead = { attrs, body ->
        User user = session.user
        ReadingItem readingItem = ReadingItem.findById((attrs.id))
        flash.query = attrs.query
        if (user && readingItem) {
            def id = readingItem.id
            def isRead = readingItem.isRead
            if (isRead) {

                out << "<span class='unread' data-id=${attrs.id} style='color:blue' > Mark as Unread </span>"
            } else {

                out << "<span class='read' data-id=${attrs.id} style='color:blue' > Mark as Read </span>"


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

    def recentShares = {
        List recentShares = Resource.recentShares()
        out << render(template: '/shared/recentShares', model: [recentShares: recentShares])
    }

    def canDeleteResouce = { attr, body ->
        User user = session.user
        Resource resource = attr.resource
        (user.canDeleteResource(resource)) ? out << link(controller: "resource", action: "delete", id: resource.id, "Delete") : out << ""

    }

    def showSubscribe = { attr, body ->
        User user = session.user
        if (user) {
            (user.isSubscribed(attr.topicId)) ? out << g.remoteLink(controller: "subscription", action: "delete",update:'message', id: "${attr.topicId}", "Unsubscribe") : out << g.remoteLink(controller: "subscription", action: "save",update: 'message',id: "${attr.topicId}", "Subscribe")
        }
    }

    def subscriptionCount = { attr, body ->
        if (session.user) {
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
    }
    def resourceCount = { attr, body ->
        out << Resource.countByTopic(Topic.read(attr.topicId))
    }
    def topicCount = { attr, body ->
        if (session.user) {
            out << Topic.countByCreatedBy(attr.user)
        }
    }

    def userImage = { attrs, body ->
        out << "<img src=\"/images/${attrs.id}.jpg\" width=\"65px\" height=\"65px\"/>"
    }

    def postCount = { attrs, body ->
        out << Subscription.countByUser(attrs.user)

    }


}

