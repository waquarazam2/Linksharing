package linksharing
class Subscription {
    Topic topic
    User user
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness=Seriousness.SERIOUS;

    static mapping = {
        topic lazy: false
        user lazy: false
    }
    static constraints = {
        user(nullable: false)
        topic(nullable :false)
        seriousness(nullable: false)
    }

    boolean equals(o) {

        if (this.is(o)) return true
        if (!(o instanceof Subscription)) return false

        Subscription that = (Subscription) o

        if (dateCreated != that.dateCreated) return false
        if (id != that.id) return false
        if (lastUpdated != that.lastUpdated) return false
        if (seriousness != that.seriousness) return false
        if (topic != that.topic) return false
        if (user != that.user) return false
        if (version != that.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (topic != null ? topic.hashCode() : 0)
        result = 31 * result + (user != null ? user.hashCode() : 0)
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0)
        result = 31 * result + (seriousness != null ? seriousness.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", topic=" + topic +
                ", user=" + user +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", seriousness=" + seriousness +
                ", version=" + version +
                '}';
    }
}
