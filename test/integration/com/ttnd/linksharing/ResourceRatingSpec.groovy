package com.ttnd.linksharing

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class ResourceRatingSpec extends IntegrationSpec {

    void "Testing of valid insertion of resource rating"() {

            setup:

            User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                    email: "bla@gmail.com")
            user.save()
            Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
            topic.save()
            DocumentResource documentResource = new DocumentResource(filePath: "/some/file/path",description : "File Path",
                    createdBy : user,topic:topic)
            documentResource.save()
            ResourceRating resourceRating = new ResourceRating(resource: documentResource,user:user,score: 3)
            when:
            resourceRating.save()
            then:
            ResourceRating.count==1
    }

    void "Testing of invalid scenario in which user gives rating for the same resource more than once"() {

        setup:

        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        DocumentResource documentResource = new DocumentResource(filePath: "/some/file/path",description : "File Path",
                createdBy : user,topic:topic)
        documentResource.save()
        ResourceRating resourceRating = new ResourceRating(resource: documentResource,user:user,score: 3)
        ResourceRating resourceRating2 = new ResourceRating(resource: documentResource,user:user,score: 5)
        when:
        resourceRating.save()
        resourceRating2.validate()
        println ">>>>>>>"+resourceRating2.errors.allErrors+"<<<<<<<<<"
        resourceRating2.save()
        then:
        ResourceRating.count==1
    }

    def "Resource rating should be between 1 and 5"() {

        setup:

        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        DocumentResource documentResource = new DocumentResource(filePath: "/some/file/path",description : "File Path",
                createdBy : user,topic:topic)
        documentResource.save()
        ResourceRating resourceRating = new ResourceRating(resource: documentResource,user:user,score: 6)
        when:
        resourceRating.save()
        then:
        ResourceRating.count==0


    }

}
