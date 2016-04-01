package linksharing

abstract class Resource {
    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static transients = ['ratingInfo']
    static hasMany = [ratings: ResourceRating, readingItems: ReadingItem]
    static belongsTo = [topic: Topic]
    static constraints = {
    }
    static namedQueries = {
        search { ResourceSearchCO co ->
            if (co.topicId) {
                eq('id', co.topicId)
            }
            if (co.visibility) {
                eq('topic.visibility', co.visibility)
            }

            if (co.q) {
                like('description', "%${co.q}%")
            }
        }
    }

    RatingInfoVO getRatingInfo() {
        List result = ResourceRating.createCriteria().get {
            projections {
                count('id')
                sum('score')
                avg('score')
            }
            eq('resource', this)
        }

        return new RatingInfoVO(totalVotes: result[0], totalScore: result[1], averageScore: result[2])
    }

    static topPosts() {
        List resources = ResourceRating.createCriteria().list(max: 5) {
            projections {
                groupProperty('resource')
                avg('score', 'avgScore')
            }
            'resource' {
                property('id')
                property('createdBy')
                property('description')
            }
            order('avgScore', 'desc')
        }
        return resources
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Resource)) return false

        Resource resource = (Resource) o

        if (createdBy != resource.createdBy) return false
        if (dateCreated != resource.dateCreated) return false
        if (description != resource.description) return false
        if (id != resource.id) return false
        if (lastUpdated != resource.lastUpdated) return false
        if (ratings != resource.ratings) return false
        if (readingItems != resource.readingItems) return false
        if (topic != resource.topic) return false
        if (version != resource.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (description != null ? description.hashCode() : 0)
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0)
        result = 31 * result + (topic != null ? topic.hashCode() : 0)
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0)
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0)
        result = 31 * result + (readingItems != null ? readingItems.hashCode() : 0)
        return result
    }

    static recentShares() {
        List resources = Resource.createCriteria().list(max: 5) {
            projections {

                property('lastUpdated', 'date')
                property('id')
                property('createdBy')
                property('description')
            }
            order('date', 'desc')
        }
        return resources
    }

    String which() {
        String result = 'document'
        if (this instanceof LinkResource) {
            result = 'link'
        }
        return result
    }

    static boolean canViewedBy(User user,Resource resource) {
        if(!resource){
            return false
        }
        else{
            return resource.topic.canViewedBy(user)
        }

    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", version=" + version +
                ", ratings=" + ratings +
                ", readingItems=" + readingItems +
                '}';
    }
}
