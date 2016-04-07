package linksharing

import grails.test.spock.IntegrationSpec

class ReadingItemIntegrationSpec extends IntegrationSpec {

    void "change is read"() {
        User user = new User(email: 'mohanjhvfj.gupta@tothenew.com', userName: 'mohan1hffhf243', password: 'sa231dwerfewrc', firstName: 'Mohan', lastName: 'Gupta', admin: false, active: true,confirmPassword:'sa231dwerfewrc')
        Resource resource=new LinkResource(url: 'https://www.google.com',createdBy: user,description: 'abcedefgh',topic: Topic.get(1))
        user.save(validate: false,flush: true)
        resource.save(validate: false,flush: true)
        ReadingItem readingItem =new ReadingItem(user:user,resource: resource,isRead: false)
        readingItem.save(flush: true)

        when:
        ReadingItem.changeIsRead(readingItem.id,true)
        readingItem=ReadingItem.get(readingItem.id)

        then:
       readingItem.isRead
    }
}
