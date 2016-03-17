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
            return true
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
}
