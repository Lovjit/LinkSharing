package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([User,Topic,Subscription])
class SubscriptionControllerSpec extends Specification {

    //Write the test cases for subscription save, update, delete functionality.
    def "Validation of Subscription save method"() {

        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "lala@hahah.com")
        user.save()
        session['user'] = user
        Topic topic = new Topic(createdBy: user,name: 'Grails',visibility: Visibility.PUBLIC)
        Topic receivedTopic = topic.save()
        when:
        controller.save(receivedTopic.getId())
        then:
        Subscription.count == 1
    }

    def "Validation of Subscription update method"() {

        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "lala@hahah.com")
        user.save()
        Topic topic = new Topic(createdBy: user,name: 'Grails',visibility: Visibility.PUBLIC)
        Subscription subscription = new Subscription(user: user,topic: topic)
        Subscription receivedSubs = subscription.save()
        when:
        controller.update(receivedSubs.getId(),Seriousness.CASUAL)
        then:
                Subscription.count == 1
            and:
                response.getContentAsString() == 'success'
    }

    def "Validation of Subscription delete method"() {

        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "lala@hahah.com")
        user.save()
        Topic topic = new Topic(createdBy: user,name: 'Grails',visibility: Visibility.PUBLIC)
        Subscription subscription = new Subscription(user: user,topic: topic)
        Subscription receivedSubs = subscription.save()
        when:
        controller.delete(receivedSubs.getId())
        then:
        //Subscription.resourceCount == 1
        response.getContentAsString() == 'success'

    }

}
