package linksharing

class LinkResource extends Resource{
    String url
    static constraints = {
        url(blank: false,url:true)
    }

    String toString(){
        return  url
    }
}
