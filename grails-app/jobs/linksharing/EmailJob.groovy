package linksharing

class EmailJob {
    def customMailService
    static triggers = {
        //  simple name:'simpleTrigger', repeatInterval: 604800000, repeatCount: -1
       // cron name: 'myTrigger', cronExpression: " 0 42 23 ? ? ?"
    }

    def execute() {
        List users=User.getUsersWithUnreadItems()
        users.each {user->
            List resources=ReadingItem.unreadPosts(user)
            customMailService.sendUnreadResourcesEmail(user,resources)
        }

    }
}
