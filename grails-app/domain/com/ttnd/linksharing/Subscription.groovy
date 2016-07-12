package com.ttnd.linksharing

class Subscription {

    Topic topic
    User user
    Seriousness seriousness = Seriousness.SERIOUS
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Topic,User]

    static constraints = {
        topic (nullable: false,unique: ['user'])
        user (nullable: false)
        seriousness (nullable: false)
    }

    static fetchMode = [topic : 'eager',user : 'eager']




}
