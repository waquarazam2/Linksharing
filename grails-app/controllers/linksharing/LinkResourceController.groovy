package linksharing

class LinkResourceController {

    def saveLink(String link, String description, String topic) {
        println topic

        LinkResource linkResource = new LinkResource(url: link, description: description, topic: Topic.findByName(topic), createdBy: session.user)
        if (linkResource.save(failOnError: true)) {
            flash.message = 'resource shared'
        } else {
            flash.error = 'error sharing resource'
        }
        render linkResource.errors
    }

}
