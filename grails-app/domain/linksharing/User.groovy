package linksharing

class User {
    String email
    String userName
    String password
    String firstName
    String lastName
    byte[] photo
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated

    static transients =["name"]
    transient confirmPassword

    static mapping = {
        type:'blob'
    }

    String getName(){
        firstName+" "+lastName
    }

    static hasMany=[topics:Topic,subscriptions:Subscription,readingItems:ReadingItem,resources:Resource]
    static constraints = {
        email(unique: true, email: true, nullable: false, blank: false)
        password(nullable: false,blank: false,minSize: 5)
        firstName(nullable: false,blank: false)
        lastName(nullable: false,blank: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword(bindable:true,validator:{val,obj->
            if(obj.id){
                if(val==obj.password){
                    return true
                }
                else {
                    return false
                }
            }
            else{
                return true
            }
        })
    }

    String toString(){
        return getName()
    }
}
