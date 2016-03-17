package linksharing

import grails.transaction.Transactional

@Transactional
class ResourceService {

    boolean save(Resource resource,String description) {
        resource.description=description
        resource.ide=UUID.randomUUID().toString()
        if(resource.save(flush: true)){
            return true
        }
        else{
            println resource.errors
            return false
        }
    }
}
