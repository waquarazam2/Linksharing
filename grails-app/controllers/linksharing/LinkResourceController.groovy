package linksharing

class LinkResourceController {

    def index() {}

    def saveLink(String link, String description, String topic) {
        println topic

        LinkResource linkResource = new LinkResource(url: link, description: description, topic: Topic.findByName(topic), createdBy: session.user)
        linkResource.save(flush: true)
        if(linkResource.hasErrors()){
            flash.error = 'error sharing resource'
        } else{
            flash.message = 'resource shared'
        }
        redirect(controller: 'user',action: 'index')
    }

}
