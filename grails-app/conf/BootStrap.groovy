class BootStrap {
def grailsApplication
    def init = {
        println "External file ins incl"+grailsApplication.config.myname

    }
    def destroy = {
    }
}
