package linksharing

class UtilController {

    def index() {
        render view: 'pageNotFound'
    }
    def error()
    {
        render view: 'error'
    }
}