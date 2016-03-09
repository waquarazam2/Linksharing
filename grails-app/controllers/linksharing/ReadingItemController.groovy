package linksharing

class ReadingItemController {

    def index() { }

   def "changeIsRead"(long id,boolean isRead){
       if(ReadingItem.changeIsRead(id,isRead)){
           redirect(controller: 'user',action: 'index')
       }

       else{
            render 'error'
       }
   }
}
