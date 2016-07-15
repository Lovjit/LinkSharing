package com.ttnd.linksharing

import com.ttnd.linksharing.co.SearchCO

class User {

    String firstName
    String lastName
    String name
    String email
    String userName
    String password
    String confirmPassword
    Byte[] photo
    Boolean isAdmin
    Boolean isActive
    Date dateCreated
    Date lastUpdated

    static hasMany = [topics:Topic,subscriptions:Subscription,readingItems:ReadingItem,resources:Resource]

    static transients = ['name','confirmPassword']

    static constraints = {
        firstName nullable: false , blank: false
        lastName nullable: false , blank: false
        email (email: true,unique: true,nullable: false,blank: false)
        userName unique: true, nullable: false , blank: false
        password minSize: 5,nullable: false,blank: false
        photo nullable : true
        isAdmin nullable : true
        isActive nullable : true
        // Closure with three arguments, the third being the errors object
        confirmPassword validator: { val, obj, errors ->
            if (!(obj.confirmPassword == val)) errors.rejectValue('password', 'noMatch')
        }

    }

   /* - Create method getUnReadResources in user domain which takes SearchCO argument
    and returns unreaditems of user from ReadingItem domain
    - The search should also work using user/index page, q parameter of SearchCO.
    If searchco.q is found then getUnReadResources method will search the items based on ilike of resource.description.
    - The pagination parameter should also be used in getUnReadResources criteria query.*/
    // Select * from reading_item where user = user and isread = false
    static List<ReadingItem> getUnReadResources(SearchCO searchCO,User user){

        List<ReadingItem> unreadReadingItems = ReadingItem.createCriteria().list {
            createAlias("resource","res")
            createAlias("user","user")

            if(searchCO.q){
                ilike("res.description","%${searchCO.q}%")
            }
            eq("user",user)
            eq("isRead",false)

            maxResults searchCO.maxResult
            firstResult searchCO.offset
        }

    }

    static mapping = {
        photo type: 'blob'
        sort id: 'desc'
    }

    String getName(){
        return this.firstName+" "+this.lastName
    }

    String toString(){
        return "User name is ${userName}"
    }
}
