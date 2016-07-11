package com.ttnd.linksharing

import grails.validation.ValidationException

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

    def afterInsert = {
        Subscription subscription = new Subscription(topic: this,user: this.createdBy,seriousness: Seriousness.VERY_SERIOUS)

        try {
            withNewSession {
                subscription.save()
                log.info("Subscription for topic ${this.name} created")
                }


        } catch (ValidationException validationException) {
            log.error("Error while saving subscription")
        }
    }

    String toString(){
        return "Topic name ${this.name} and created by ${this.createdBy.name}"
    }

}
