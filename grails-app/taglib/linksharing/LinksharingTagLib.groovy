package linksharing

class LinksharingTagLib {
    static namespace = "ls"
    def markAsRead = { attrs, body ->
        User user = session.user
        Long itemId = attrs.id as Long
        ReadingItem readingItem = ReadingItem.findById(itemId)
        flash.query = attrs.query
        if (user && readingItem) {
            def isRead = readingItem.isRead
            if (isRead) {
                out << "<a class='unread' data-id=${attrs.id} style='text-decoration:underline;font-size:10px' > Mark as Unread </a>"
            } else {

                out << "<a class='read' data-id=${attrs.id} style='text-decoration:underline;font-size:10px'  > Mark as Read </a>"


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
            (user.isSubscribed(attr.topicId)) ? out << g.remoteLink(controller: "subscription", action: "delete", update: "${attr.topicId}", id: "${attr.topicId}", "Unsubscribe") : out << g.remoteLink(controller: "subscription", action: "save", update: "${attr.topicId}", id: "${attr.topicId}", "Subscribe")
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

    def canUpdateTopic = { attrs, body ->
        User user = session.user
        Topic topic = Topic.get(attrs.topicId)
        if (user?.admin || user==topic?.createdBy) {
            out << g.select(name: 'visibility',onchange: "changeVisibility(this.value,${attrs.topicId},${attrs.panel})", class:'visibility form-control',from: ['PUBLIC','PRIVATE'], value: "${topic.visibility}",selected:"${topic.visibility}")
        }

    }

    def showSeriousness = { attrs, body ->
        User user=session.user
        Subscription subscription=user.getSubscription(attrs.topicId)

        out << g.select(name: 'seriousness',class:'seriousness',from: ['SERIOUS', 'VERY SERIOUS', 'CASUAL'], value: "${subscription.seriousness}",selected:"${subscription.seriousness}")

    }

    def showActivate = { attr, body ->
        User user = session.user
        if (user) {
            (user.active) ? out << g.remoteLink(controller: "user", action: "activateUser", update: "${attr.id}", id: "${attr.topicId}", "Unsubscribe") : out << g.remoteLink(controller: "subscription", action: "save", update: "${attr.topicId}", id: "${attr.topicId}", "Subscribe")
        }
    }


}

