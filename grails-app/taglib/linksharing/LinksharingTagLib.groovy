package linksharing

class LinksharingTagLib {
    static namespace = "ls"
//     static defaultEncodeAs = [taglib:'html']
//    static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def markAsRead = { attrs, body ->
        User user = session.user
        ReadingItem readingItem = ReadingItem.findById(Long.parseLong(attrs.id))
        if (user && readingItem) {
            def id = readingItem.id
            def isRead = readingItem.isRead
            if (isRead) {
//                out << "<g:link  value=\"markAsRead\" url=\"[controller:'resource',action:'changeIsRead',params:[id:id,isRead:false]]\" style=\"text-decoration:underline;font-size:10px;\">Mark as Unread</g:link>"
                out << g.link(controller: "readingItem", action: "changeIsRead", params: [id: id, isRead: false], "style": "font-size:10px") {
                    "Mark as Unread "
                }

            } else {
//                out << "<g:link  value=\"markAsUnread\" url=\"[controller:'resource',action:'changeIsRead',params:[id:id,isRead:true]]\" style=\"text-decoration:underline;font-size:10px;\">Mark as Read</g:link>"
                out << g.link(controller: "readingItem", action: "changeIsRead", params: [id: id, isRead: true], "style": "font-size:10px") {
                    "Mark as Read "
                }

            }
        }
    }

}