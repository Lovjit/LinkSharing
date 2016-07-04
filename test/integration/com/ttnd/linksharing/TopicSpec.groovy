package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.spock.Integration
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class TopicSpec extends IntegrationSpec{

    def "Topic name cannot be null/blank"() {

        setup:
        User user = new User(firstName: "Bla", lastName: "Bla", userName: "bla", password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic1 = new Topic(createdBy: user, name: topicName, visibility: Visibility.PUBLIC)
        when:
        topic1.save()
        then:
        Topic.count==topicRows
        where:
        topicName|topicRows
        ""|0
        null|0
        "Topic-Name" | 1


    }

    void "Topic name should be unique per user"() {

        setup:
        User user = new User(firstName: "Bla", lastName: "Bla", userName: "bla", password: "1234675",
                email: "bla@gmail.com")
        user.save(validate: false)

        when:
        Topic topic1 = new Topic(createdBy: user, name: "Topic1", visibility: Visibility.PUBLIC)
        topic1.save()
        then:
        Topic.count() == 1

        when:
        Topic topic2 = new Topic(createdBy: user, name: "Topic1", visibility: Visibility.PUBLIC)
        topic2.validate()
        println "---${topic2.errors.allErrors}"
        topic2.save()

        then:
        Topic.count() == 1
    }
}
