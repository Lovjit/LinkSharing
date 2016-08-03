package com.ttnd.linksharing

class ReadingItem {

    Resource resource
    User user
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user : User,resource : Resource]

    static constraints = {
        resource nullable: false,unique: 'user'
        user nullable: false
        isRead nullable: false
    }

    String toString(){
        return "Resource : ${resource.description} and user : ${user.name}"
    }
}
