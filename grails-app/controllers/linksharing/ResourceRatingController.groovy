package linksharing

class ResourceRatingController {

    def saveRating(int rating,long id)
    {
        User user = session.user
        Resource resource = Resource.read(id)
        ResourceRating resourceRating = ResourceRating.findOrCreateWhere(user:user,resource:resource)
       resourceRating.score=rating
        if(resourceRating.save(flush: true)){
            flash.message='Rating saved'
        }else{
            flash.message='Unable to save rating'
        }
        redirect(controller:"user", action:"index")
    }
}