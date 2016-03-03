package linksharing

class ReadingItem {
    Resource resource
    User user
    Boolean isRead=false
    Date dateCreated
    Date lastUpdated


    static constraints = {
        resource(nullable: false)
        user(nullable: false,unique: 'resource')
        isRead(nullable: false)
    }

    static boolean changeIsRead(long  id,boolean isRead) {
        if(ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id",[isRead:isRead,id:id])){
          return true
        }
        else{
          return   false
        }
    }
}
