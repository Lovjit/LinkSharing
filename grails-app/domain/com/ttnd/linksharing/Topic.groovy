package com.ttnd.linksharing

class Topic {

    String name
    User createdBy
    Visibility visibility
    Date dateCreated
    Date lastUpdated

    static belongsTo = User
    static hasMany = [subscriptions:Subscription,resources:Resource]

    static constraints = {
        name nullable: false,blank: false,unique: 'createdBy'
        createdBy nullable: false, blank:false
        visibility nullable: false
    }

}
