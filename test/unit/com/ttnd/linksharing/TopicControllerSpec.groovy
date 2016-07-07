package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
@Mock([Topic,User,Subscription])
class TopicControllerSpec extends Specification {

    void "User should be redirected to login index action if topic does not exist"() {

        when:
        controller.show(-1)
        then:
        response.redirectUrl == '/login/index'
    }

    void "Render success should be displayed when topic is public"() {

        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "lala@hahah.com")
        user.save()
        Topic topic = new Topic(createdBy: user,name: 'Grails',visibility: Visibility.PUBLIC)
        Topic receivedTopic = topic.save()
        when:
        controller.show(receivedTopic.id)
        then:
        response.contentAsString == 'success'
    }

    void "Render success when topic is private and subscription exist"() {

        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "lala@hahah.com")
        user.save()
        Topic topic = new Topic(createdBy: user,name: 'Grails',visibility: Visibility.PUBLIC)
        Topic receivedTopic = topic.save()
        Subscription subscription = new Subscription(topic: topic,user: user,seriousness: Seriousness.CASUAL)
        subscription.save()
        when:
        controller.show(receivedTopic.id)
        then:
        response.contentAsString == 'success'
    }

    void "Redirect user to login when topic is present but subscription does not exist"() {

        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "lala@hahah.com")
        user.save()
        Topic topic = new Topic(createdBy: user,name: 'Grails',visibility: Visibility.PRIVATE)
        Topic receivedTopic = topic.save()
        when:
        controller.show(receivedTopic.id)
        then:
        response.redirectUrl == '/login/index'
    }
}
