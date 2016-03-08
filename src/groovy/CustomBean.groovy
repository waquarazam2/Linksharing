package linksharing
class CustomBean {
    static scope = 'prototype'
    int id
    String name

    CustomBean(int id,String name){
        println 'Constructor called'
        this.id=id
        this.name=name
    }

    CustomBean(){
        println 'Default Constructor Called'
    }

    String getName() {
        return name
    }

    void setName(String name) {
        println '<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>><,,,,,<<<<<<<<<>>>>>>>>>>>>'
        this.name = name
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }
}
