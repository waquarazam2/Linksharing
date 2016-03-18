package linksharing

class EmailJob {
    def customMailService
    static triggers = {
        //  simple name:'simpleTrigger', repeatInterval: 604800000, repeatCount: -1
        simple name: 'emailJob', startDelay: 300000
    }

    def execute() {
        List users=User.getUsersWithUnreadItems()
        users.each {user->
            List resources=ReadingItem.unreadPosts(user)
            customMailService.sendUnreadResourcesEmail(user,resources)
        }

    }
}
