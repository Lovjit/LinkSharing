package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {

    void "User controller index test"() {

        when :
            controller.index()
        then:
            response.contentAsString == 'User Dashboard'

    }
}