package linksharing

import grails.converters.JSON

class ResourceController {

    def index(long id) {
        render(view:'show',model: [resource:Resource.get(id)])
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
        if(Resource.canViewedBy(session.user,resource)) {
            List trendingTopics = Topic.getTrendingTopics()
            render(view: "show", model: [resource: resource, trendingTopics: trendingTopics])
        }
        else {
            flash.error = "User Cannot view Topic"
        }
    }
    def resourceService

    def save(long id,String description){
        Resource resource =Resource.get(id)
        println "called                 ssd "+id+"  "+description
        if(resource){
            if(resourceService.save(resource,description)) {
                render([message: 'Save successfully']) as JSON
            }
            else{
                render([message:'unable to save']) as JSON
            }
        }
        else{
            render([error:'Resource not found']) as JSON
        }
    }
}
