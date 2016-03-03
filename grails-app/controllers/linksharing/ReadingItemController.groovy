package linksharing

class ReadingItemController {

    def index() { }

   def "changeIsRead"(long id,boolean isRead){
       if(ReadingItem.changeIsRead(id,isRead)){
           render 'success'
       }

       else{
            render 'error'
       }
   }
}
