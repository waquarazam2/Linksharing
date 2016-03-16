package linksharing

import grails.transaction.Transactional

@Transactional
class CustomMailService {
    def mailService

    def sendMail(EmailDTO emailDTO) {
        mailService.sendMail {
            to emailDTO.to
            subject emailDTO.subject
            if (emailDTO.content) {
                body emailDTO.content
            }
            else {
                body(view: emailDTO.view, model: emailDTO.model)
            }

        }
    }
}