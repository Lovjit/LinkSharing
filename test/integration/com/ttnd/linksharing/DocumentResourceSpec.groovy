package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class DocumentResourceSpec extends IntegrationSpec{

    def "Testing of Valid Insertion of Document Resource"() {


            setup:
            User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                    email: "bla@gmail.com")
            user.save()
            Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
            topic.save()
            DocumentResource documentResource = new DocumentResource(filePath: "/some/file/path",description : "Google Link",
                                                         createdBy : user,topic:topic)
            when:
            documentResource.save()
            then:
            DocumentResource.count==1



    }

    def "Document resource file path should not be null"() {
        setup:
        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        DocumentResource documentResource = new DocumentResource(filePath: null,description : "Google Link",
                createdBy : user,topic:topic)
        when:
        documentResource.save()
        then:
        DocumentResource.count==0



    }

}
