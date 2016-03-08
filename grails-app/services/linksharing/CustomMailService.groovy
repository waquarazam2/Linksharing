package linksharing

import grails.transaction.Transactional

@Transactional
class CustomMailService {
    def mailService

    def sendMail() {
        mailService.sendMail{
            to 'waquar.azam@tothenew.com'
            subject 'test mail'
            body 'Hi \n How arer ye'
        }

    }
}
