package linksharing

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UserService {


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
            user.confirmPassword=confirmPassword
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

    def register(UserCO co) {
        User user = new User()
        user.firstName = co.firstName
        user.lastName = co.lastName
        user.userName = co.userName
        user.email=co.email
        user.photo = co.photo.bytes
        user.photoType=co.photo.contentType
        user.password=co.password
        user.confirmPassword=co.confirmPassword
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
