package linksharing

class ResourceController {

    def index(long id) {

    }

    def delete(){
        Resource resource=Resource.load(params.id)
        if(resource && session.user.canDeleteResource){
            resource.delete()
            flash.message 'deleted successfully'
            redirect(controller: 'user',action: 'index')
        }
        else{
            render 'resource not found'
        }
    }



    def search(ResourceSearchCO co) {
        List<Resource> list = Resource.search(co).list();
        render(view:"search",model:[searchResources:list])

    }

//    def search(ResourceSearchCO co)
//    {
//        if(co.q)
//        {
//            co.visibility = Visibility.PUBLIC
//            List<Resource> resources = Resource.search(co).list([max:5]);
//            render(view:"search",model:[searchResources:resources])
//        }
//        else
//            flash.message = "No input in query"
//    }

    def show(long id)
    {
        Resource resource = Resource.get(id)
        if(resource.canViewedBy(session.user)) {
            List trendingTopics = Topic.getTrendingTopics()
            render(view: "show", model: [resource: resource, trendingTopics: trendingTopics])
        }
        else {
            flash.error = "User Cannot view Topic"
        }
    }
}
