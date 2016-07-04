package com.ttnd.linksharing

class ResourceRating {

    Resource resource
    User user
    Integer score
    Date dateCreated
    Date lastUpdated

    static belongsTo = ['user','resource']

    static constraints = {
        resource nullable: false,unique: 'user'
        user nullable: false
        score nullable: false,min: 1,max: 5
    }
}
