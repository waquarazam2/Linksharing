package linksharing

class DocumentResource extends Resource{
    String filePath
    static belongsTo = [resource:Resource]
    static constraints = {
    }
}
