package linksharing

class ReadingItem {
    Resource resource
    User user
    Boolean isRead = false
    Date dateCreated
    Date lastUpdated

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof ReadingItem)) return false

        ReadingItem that = (ReadingItem) o

        if (dateCreated != that.dateCreated) return false
        if (id != that.id) return false
        if (isRead != that.isRead) return false
        if (lastUpdated != that.lastUpdated) return false
        if (resource != that.resource) return false
        if (user != that.user) return false
        if (version != that.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (resource != null ? resource.hashCode() : 0)
        result = 31 * result + (user != null ? user.hashCode() : 0)
        result = 31 * result + (isRead != null ? isRead.hashCode() : 0)
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
    }
    static constraints = {
        resource(nullable: false)
        user(nullable: false, unique: 'resource')
        isRead(nullable: false)
    }

    static boolean changeIsRead(long id, boolean isRead) {
        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id", [isRead: isRead, id: id])) {
            true
        } else {
            false
        }
    }

    static List getReadingItems(User user) {

        List list = ReadingItem.createCriteria().list {
            projections {
                createAlias('resource', 'r')
                property('id')
                property('r.description')
                property('r.url')
                property('r.createdBy')
                property('r.id')
            }
            eq('user', user)

        }
        return list
    }

    static List unreadPosts(User user){
         ReadingItem.findAllByIsReadAndUser(false,user)
    }

    String toString(){
        String name=this.user.name+this.resource.name
        return  name

    }
}
