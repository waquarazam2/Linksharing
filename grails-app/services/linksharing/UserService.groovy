package linksharing

import grails.transaction.Transactional

@Transactional
class UserService {

    def saveService() {
        return 'Hello'
    }

    def save(long id,String firstName,String lastName,String userName,byte[] photo) {
        User user = User.get(id)
        user.firstName = firstName
        user.lastName = lastName
        user.userName = userName
        user.photo = photo

        if(user.save(flush: true)){
            println user.errors
            return  true
        }else{
            println user.errors
            return false
        }
    }

    def updatePassword(long id,String password,String confirmPassword){
        User user=User.get(id)
        if(user){
            user.password=password
            user.confirmPassword=password
            if(user.save()){
                return true
            }else{
                return false
            }

        }else{
            return  false
        }

    }

    def changeActivation(long userId,Boolean active){
        User user=User.get(userId)
        user.confirmPassword=user.password
        String message
        if(user){
            user.active=active
            if(user.save(flush: true)){
                println user.errors
                return  true
            }else{
                println user.errors
               return  false

            }
        }else{
            println user.errors
            return  false

        }
    }

    def register(String firstName,String lastName,String userName,String email,org.springframework.web.multipart.commons.CommonsMultipartFile photo,String password,String confirmPassword) {
        User user = new User()
        user.firstName = firstName
        user.lastName = lastName
        user.userName = userName
        user.email=email
        user.photo = photo.bytes
        user.photoType=photo.contentType
        user.password=password
        user.confirmPassword=confirmPassword
        user.admin=false
        user.active=true

        if(user.save(flush: true)){
            println user.errors
            return  user
        }else{
            println user.errors
            return null
        }
    }

    def loadUserTable(String q,String sortBy,Map params){
        params.max = (params.max) ? params.max : 10;
        List<User> userList = User.list(params);
        if (q && !q.equals("")) {
            println "here"
            userList = User.createCriteria().list(params) {
                or {
                    ilike("userName", "%${q}%")
                    ilike("firstName", "%${q}%")
                }
            }
        } else if (sortBy) {
            if (sortBy.equalsIgnoreCase("activated")) {
                userList = User.findAllByActive(true);
            } else if (sortBy.equalsIgnoreCase("deactivated")) {
                userList = User.findAllByActive(false);
            }
        }

        return  userList
    }
}
