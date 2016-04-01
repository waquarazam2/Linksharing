package linksharing

class ResourceRating {
    Resource resource;
    User user
    int score

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof ResourceRating)) return false

        ResourceRating that = (ResourceRating) o

        if (score != that.score) return false
        if (dateCreated != that.dateCreated) return false
        if (id != that.id) return false
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
        result = 31 * result + score
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
    }
    Date dateCreated
    Date lastUpdated

    static  belongsTo = [resource:Resource]

    static constraints = {
        user(nullable: false)
        resource(nullable: false)
        score(nullable:false,max: 5,min: 1)

    }

    static List getTopPosts(){

        List resources=ResourceRating.createCriteria().list(max:5){
            projections{
                groupProperty('resource')
                avg('score','avgScore')
                property('resource.id')
            }

            order('avgScore','desc')
        }

        return resources;

    }

    @Override
    public String toString() {
        return "ResourceRating{" +
                "id=" + id +
                ", resource=" + resource +
                ", user=" + user +
                ", score=" + score +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", version=" + version +
                '}';
    }
}
