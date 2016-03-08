package linksharing

class LinksharingTagLib {
    static namespace = "ls"
   // static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def markAsRead={attrs,body->

        User user=session.user
        if(user && ReadingItem.findByUser(user)){
            out<<"<g:link  style=\"text-decoration:underline;font-size:10px;\">Mark as Read</g:link>"
        }
    }
}
