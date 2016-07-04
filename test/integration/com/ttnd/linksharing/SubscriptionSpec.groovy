package com.ttnd.linksharing

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class SubscriptionSpec extends IntegrationSpec {

    void "User should not be able to subscribe to single topic multiple times"() {

            setup:

            User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                    email: "bla@gmail.com")
            user.save()
            Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
            topic.save()
            Subscription subscription = new Subscription(user:user,topic: topic,seriousness: Seriousness.CASUAL)
            Subscription subscription2 = new Subscription(user:user,topic: topic,seriousness: Seriousness.CASUAL)

            when:

            subscription.save(flush: true)
            subscription2.save(flush: true)

            then:
            Subscription.count==1


    }
}
