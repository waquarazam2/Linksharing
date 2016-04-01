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

    def sendUnreadResourcesEmail(User user, List<Resource> resourceList){
        EmailDTO emailDTO = new EmailDTO(to:[user.email],view:'/email/unreadResources',model: [name:user.name,resources:resourceList*.description])
        sendMail(emailDTO)
    }
}