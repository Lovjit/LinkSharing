package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ResourceController)
@Mock([User,Topic,LinkResource,Resource])
class ResourceControllerSpec extends Specification {

    void "Validation of resource delete method"() {

        setup:
        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        LinkResource linkResource = new LinkResource(url: "https://www.google.com",description : "Google Link",
                createdBy : user,topic:topic)
        LinkResource receivedLr = linkResource.save()
        when:
        controller.load(receivedLr.getId())
        then:
        response.contentAsString == 'Resource deleted successfully'
    }
}
