package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class LinkResourceSpec extends IntegrationSpec{

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
}
