package  linksharing
class ResourceSearchCO extends SearchCO {
    long topicId
    Visibility visibility;

    void getVisibility(String visibility){
        this.visibility=Visibility.convertToEnum(visibility)
    }
}
