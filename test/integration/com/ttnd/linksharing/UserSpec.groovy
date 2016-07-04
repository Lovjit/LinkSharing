package com.ttnd.linksharing

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class UserSpec extends IntegrationSpec {

    def "Email should be unique,not null/blank and email name should satisfy basic email format"() {

        setup:
        User user1 = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: email)
        when:
        user1.save()
        then:
        User.count==userRows
        where:
        email|userRows
        null | 0
        ""|0
        "invalidMailFormat" | 0
        "validmail@format.com"|1
    }

    def "Password should not be null/blank and it should have minimum 5 characters"() {

        setup:
        User user1 = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: pwd,
                email: "validMail@Format.com")
        when:
        user1.save()
        then:
        User.count==userRows
        where:
        pwd|userRows
        null | 0
        ""|0
        "1234" | 0
        "12345"|1
    }

    def "First name and last name should not be null/blank"() {

        setup:
        User user1 = new User(firstName: fName,lastName: lName,userName: "vikas",password: "1234556",
                email: "validMail@Format.com")
        when:
        user1.save()
        then:
        User.count==userRows
        where:
        fName|lName|userRows
        ""|"" | 0
        ""|null|0
        "First-Name"|null | 0
        "First-Name" | "Last-Name" | 1
    }

    def "Combination of user name and email should be unique"() {
        setup:
        User user1 = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                             email: "vikas@gmail.com")
        User user2 = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                             email: "vikas@gmail.com")
        when:
        user1.save(flush: true)
        user2.save(flush: true)

        then:
        User.count == 1


    }
}
