package linksharing

class ApplicationFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
        loginCheck(controller: '*', action: '*', controllerExclude: 'login') {
            before = {
                if (!session.user) {
                    redirect(controller: 'login', action: 'index')
                } else {
                    if (actionName.equals('index') && controllerName.equals('user')) {
                        render(session.user.userName)
                    }
                }
            }
        }
    }
}
