package com.ttnd.linksharing

import grails.converters.JSON

class SubscriptionController {

    def subscriptionService

    def index() { }

    def save(Long topicId){
        Topic topic = Topic.findById(topicId)
        Subscription subscription = new Subscription(topic: topic,user: session['user'])
        if(subscription.validate()){
            subscription.save()
            render 'success'
        }else{
            render 'errors'
        }
    }

    def update(Long subscriptionId,Seriousness seriousness){
        Subscription subscription = Subscription.findById(subscriptionId)
        if(subscription){
            subscription.seriousness = seriousness
            if(subscription.validate()){
                subscription.save()
                render 'success'
            }else{
                render 'errors'
            }
        }else{
            render 'Subscription not found'
        }
    }

    def delete(Long subscriptionId){
        Subscription subscription = Subscription.findById(subscriptionId)
        if(subscription){
            subscription.delete()
            render 'success'
        }else{
            render 'Subscription not found'
        }
    }

    def updateSubscription(Long topicId,boolean isSubscription){
        Topic topic = Topic.findById(topicId)
        User loggedInUser = User.findById(session['user'].id)
        Subscription subscription
        if(isSubscription){
            subscription = new Subscription(topic : topic,user : loggedInUser)
            if( subscription.validate()){
                subscription.save(failOnError: true)
            }
        }else{
            subscription = Subscription.findByTopicAndUser(topic,loggedInUser)
            subscription.delete(flush: true)
        }

        render "success"
    }

    def updateSubscriptionSeriousness(Long topicId,String seriousnessVal){

        render subscriptionService.updateSubscriptionSeriousness(topicId,seriousnessVal,session['user'].id)

    }

    def deleteSubscription(Long topicId){
        String result = subscriptionService.deleteSubscription(topicId,session['user'].id)
        if(result){
            redirect(controller: 'user',action: 'index')
        }else{
            render ""
        }
    }

    def subscribeViaMail(String emailId,Long topicId){
        render subscriptionService.createSubscriptionViaMailInvite(emailId,topicId)
       // println "Here"
    }

}
