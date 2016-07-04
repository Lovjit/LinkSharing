package com.ttnd.linksharing

class Subscription {

    Topic topic
    User user
    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Topic,User]

    static constraints = {
        topic (nullable: false,unique: ['user'])
        user (nullable: false)
        seriousness (nullable: false)
    }
}
