package linksharing

class ResourceController {

    def index() { }

    def delete(){
        Resource resource=Resource.load(params.id)
        if(resource){
            resource.delete()
        }
        else{
            render 'resource not found'
        }
    }

    def search(ResourceSearchCO resourceSearchCO){
        if(resourceSearchCO.q){
            resourceSearchCO.visibility=Visibility.PUBLIC
        }
    }

    def show(long id){
        Resource resource=Resource.get(id)
        RatingInfoVO ratingInfoVO = resource.getRatingInfo()
        render "No of votes ${ratingInfoVO.totalVotes} total score ${ratingInfoVO.totalScore}" +
                " average score ${ratingInfoVO.averageScore}"

        render Topic.getTrendingTopics()

    }
}
