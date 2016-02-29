package linksharing

abstract class Resource {
    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static transients = ['ratingInfo']

    static hasMany = [ratings:ResourceRating, readingItems:ReadingItem]
    static  belongsTo = [topic:Topic]
    static constraints = {
    }
 static namedQueries = {
   search{ResourceSearchCO co->
       if(co.topicId){
           eq('id',co.topicId)
       }
       if(co.visibility){
           eq('visibility',co.visibility)
       }
   }
 }
    RatingInfoVO getRatingInfo(){
        List result=ResourceRating.createCriteria().get {
            projections {
                count('id')
                sum('score')
                avg('score')
            }
            eq('resource',this)
        }

        return new RatingInfoVO(totalVotes: result[0],totalScore: result[1],averageScore: result[2])
    }
}
