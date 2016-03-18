package linksharing

import grails.converters.JSON

class DocumentResourceController {

    def grailsApplication
    def saveDocument(DocumentResourceCO documentResourceCO) {

        if (documentResourceCO == null) {
            ([message: "Empty Resource"] as JSON)
        } else {

            User user = session.user
            def docFile = request.getFile("file")
            if (docFile.empty) {
                ([message: "File Cannot Be Empty"] as JSON)

            } else {
                DocumentResource documentResource = new DocumentResource();
                boolean result = documentResource.saveDocumenResource(documentResourceCO, user, docFile)
                if (result) {
                    ([message: "Resource Successfully Created"] as JSON)
                    redirect(controller: 'user', action: 'index')
                } else {
                    ([message: "Error Submitting Resource"] as JSON)
                    redirect(controller: 'user', action: 'index')
                }

            }
        }

    }

    def downloadDocument() {
        Resource document = Resource.get(params.id)
        if (document == null) {
            flash.message = "Resource file missing."
            return true
        } else {
            response.setContentType("application/pdf")
            def filePath = grailsApplication.config.linksharing.documents.folderPath + document.filePath

            response.setHeader("Content-Disposition", "Attachment")
            File file = new File(filePath)
            FileInputStream fileInputStream = new FileInputStream(file)
            OutputStream outputStream = response.getOutputStream()
            byte[] buffer = new byte[4096]
            int len
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len)
            }
            outputStream.flush()
            outputStream.close()
            fileInputStream.close()
        }
    }
}


