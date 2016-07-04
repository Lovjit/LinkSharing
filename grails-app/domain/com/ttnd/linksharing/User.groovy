package com.ttnd.linksharing

class User {

    String firstName
    String lastName
    String email
    String userName
    String password
    Byte[] photo
    Boolean isAdmin
    Boolean isActive
    Date dateCreated
    Date lastUpdated

    static hasMany = [topics:Topic,subscriptions:Subscription,readingItems:ReadingItem,resources:Resource]

    static transients = ['name']

    static constraints = {
        firstName nullable: false , blank: false
        lastName nullable: false , blank: false
        email (email: true,unique: true,nullable: false,blank: false)
        userName unique: true, nullable: false , blank: false
        password minSize: 5,nullable: false,blank: false
        photo nullable : true
        isAdmin nullable : true
        isActive nullable : true
    }

    static mapping = {
        photo type: 'blob'
    }

    String getName(){
        return this.firstName+" "+this.lastName
    }
}
