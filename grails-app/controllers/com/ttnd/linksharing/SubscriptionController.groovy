package com.ttnd.linksharing

class SubscriptionController {

    def index() { }

    /*Subscription domain should have a default seriousness as Serious.
    "Implement subscription save, update, delete
    -Create subscription delete action which takes id as parameter, if it exist then delete and send success else render not found
    - Create save action which takes id as parameter for topic id, user for subscription should be read from the session,
      if subscription save render success else errors
    -Create update action which takes an id and serious as a parameter if subscription and
    seriousness found, then save else render not found, if saved then render success else errors"
    Use eager fetching for topic and user in subscription
    */
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

    // Verify the purpose
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
}
