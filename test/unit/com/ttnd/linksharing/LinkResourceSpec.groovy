package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
@Mock([User,Topic])
class LinkResourceSpec extends Specification{

    def "Link resource having valid url"() {
            setup:
            User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                    email: "bla@gmail.com")
            user.save()
            Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
            topic.save()
            LinkResource linkResource = new LinkResource(url: "https://www.google.com",description : "Google Link",
                                                         createdBy : user,topic:topic)
            when:
            linkResource.save()
            then:
            LinkResource.count==1
    }

    def "Link resource having invalid url"() {
        setup:
        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        LinkResource linkResource = new LinkResource(url: "garbage-url",description : "Google Link",
                createdBy : user,topic:topic)
        when:
        linkResource.save()
        then:
        LinkResource.count==0
    }

    /*Link resource with url ${url} and createdBy ${createdBy}*/
    def "Testing of toString method"() {

        setup:
        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        Topic topic = new Topic(name: "Grails",createdBy: user,visibility: Visibility.PUBLIC)
        topic.save()
        LinkResource linkResource = new LinkResource(url: "garbage-url",description : "Google Link",
                createdBy : user,topic:topic)
        when:
        String linkResToString = linkResource.toString()
        then:
        linkResToString == 'Link resource with url garbage-url and User name is bla'


    }
}
