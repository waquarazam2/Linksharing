package linksharing

class LinkResource extends Resource{
    String url
    static constraints = {
        url(blank: false,url:true)
    }

    String toString(){
        return  url
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof LinkResource)) return false

        LinkResource that = (LinkResource) o

        if (id != that.id) return false
        if (ratings != that.ratings) return false
        if (readingItems != that.readingItems) return false
        if (url != that.url) return false
        if (version != that.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (url != null ? url.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0)
        result = 31 * result + (readingItems != null ? readingItems.hashCode() : 0)
        return result
    }
}
