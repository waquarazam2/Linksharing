package linksharing

import grails.converters.JSON

class ReadingItemController {

    def changeIsRead(long id, boolean isRead) {
        ReadingItem readingItem = ReadingItem.get(id)
        if (readingItem.executeUpdate("update ReadingItem as RI set RI.isRead=:isRead where RI.id=:id", [isRead: isRead, id: id])) {
            String newMessage=ls.markAsRead(id: id)
            render (["message":newMessage,"status": true]as JSON)
        } else
            render(["message":"Some Error Please Refresh the Page","status":false] as JSON)

    }

}
