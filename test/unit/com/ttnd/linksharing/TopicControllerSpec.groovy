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


    /*Add topic save action in TopicController
    -Add save action in topic controller, which takes a topic and string seriousness as an argument
    -Create a method in visibility enum to convert string into enum and write test case for the same
    -Session user should be createdBy of the topic
    -If a topic is saved without error flash message should be set and success should be rendered
    -If a topic is not saved errors should be logged flash error should be set and error text should be rendered
    -Write the test case for the Topic save.*/
    def "Testing of save action"() {

        setup:
        User user = new User(firstName: "Bla", lastName: "Bla", userName: "bla", password: "1234675",
                email: "bla@gmail.com")
        user.save()
        session['user'] = user
        when:
        controller.save(topicName,visibility)
        then:
        response.contentAsString == resultAsString
        where:
        topicName | visibility | resultAsString
        "t1"      | "PUBLIC"   | "success"
        "t2"      | "pjodfsj"   | "Error while saving topic"



    }

}
