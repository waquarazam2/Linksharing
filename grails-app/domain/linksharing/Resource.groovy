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
}
