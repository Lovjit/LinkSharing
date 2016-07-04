package com.ttnd.linksharing

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class ReadingItemSpec extends IntegrationSpec {

    void "Testing of valid insertion of reading item"() {

            setup:
            User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                    email: "bla@gmail.com")
            user.save()
            Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
            topic.save()
            DocumentResource documentResource = new DocumentResource(filePath: "/some/file/path",description : "File Path",
                    createdBy : user,topic:topic)
            documentResource.save()
            ReadingItem readingItem = new ReadingItem(user: user,resource: documentResource,isRead: false)
            when:
            readingItem.save()
            then:
            ReadingItem.count==1
    }

    void "Testing of invalid scenario where one user has the same reading item more than once"() {
        setup:
        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        DocumentResource documentResource = new DocumentResource(filePath: "/some/file/path",description : "File Path",
                createdBy : user,topic:topic)
        documentResource.save()
        ReadingItem readingItem = new ReadingItem(user: user,resource: documentResource,isRead: false)
        ReadingItem readingItem2 = new ReadingItem(user: user,resource: documentResource,isRead: false)
        when:
        readingItem.save()
        readingItem2.validate()
        println ">>>>>>>"+readingItem2.errors.allErrors+"<<<<<<<<<"
        readingItem2.save()
        then:
        ReadingItem.count==1
    }
}
