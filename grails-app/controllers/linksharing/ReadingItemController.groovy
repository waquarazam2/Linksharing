package linksharing

class ReadingItemController {

    def index() { }

   def changeIsRead(long id,boolean isRead){
       if(ReadingItem.changeIsRead(id,isRead)){
           redirect(url: request.getHeader('referer'))
       }

       else{
            render 'error'
       }
   }

    def saveRating(){
        
    }
}
