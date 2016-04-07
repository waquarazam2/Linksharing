package linksharing
class DocumentResource extends Resource{
    def grailsApplication
    String filePath

    static transients =['contentType']
    String ide = UUID.randomUUID().toString()

    static mapping = {
        ide generator: 'assigned'
    }

        static constraints = {
        filePath(blank: false)
            contentType(blank: false)
    }


    String toString() {
        return filePath
    }
    def getContentType(){
        return "application/pdf"
    }
    boolean  saveDocumenResource(DocumentResourceCO documentResourceCO, User user, def file) {

        if (file.getContentType() == contentType) {
            String filePath = grailsApplication.config.linksharing.documents.folderPath + documentResourceCO.topic.getId()
            File dir = new File(filePath)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            file.transferTo(new File(filePath, "${ide}${file.originalFilename}"))
            documentResourceCO.filePath = documentResourceCO.topic.id + "/" +ide+ "${file.originalFilename}"
            DocumentResource documentResource = new DocumentResource(documentResourceCO.properties)
            documentResource.createdBy = user

            if (documentResource.validate()) {
                documentResource.save(flush: true, failOnError: true)
                def subscriptions = documentResource.topic.subscriptions
                for (Subscription subscriptionObj : subscriptions) {
                    ReadingItem readingItem = new ReadingItem()
                    readingItem.user = subscriptionObj.user
                    readingItem.resource = documentResource
                    readingItem.save(flush: true, failOnError: true)

                }
                return true
            }
            return false
        }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof DocumentResource)) return false

        DocumentResource that = (DocumentResource) o

        if (filePath != that.filePath) return false
        if (grailsApplication != that.grailsApplication) return false
        if (id != that.id) return false
        if (ide != that.ide) return false
        if (ratings != that.ratings) return false
        if (readingItems != that.readingItems) return false
        if (version != that.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (grailsApplication != null ? grailsApplication.hashCode() : 0)
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0)
        result = 31 * result + (ide != null ? ide.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0)
        result = 31 * result + (readingItems != null ? readingItems.hashCode() : 0)
        return result
    }
}
