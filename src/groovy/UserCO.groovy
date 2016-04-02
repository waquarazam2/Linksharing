package linksharing

import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

class UserCO {
    String email
    String userName
    String password
    String confirmPassword
    String firstName
    String lastName
    boolean active
    boolean admin
    CommonsMultipartFile photo

}
