package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock(User)
class LoginControllerSpec extends Specification {

    def "Index action will forward to user controller index action if user's session exist"() {

        setup:
        User user = new User()
        session['user'] = user
        when:
        controller.index()
        then:
        response.redirectUrl == '/user/index'
    }

    def "Index action will render failure if user's session does not exist"() {

        when:
        controller.index()
        then:
        response.contentAsString == 'failure'
    }

    def "logout action will do session.invalidate and forward the request to login controller index action"() {
        when:
        controller.logout()
        then:
        response.redirectUrl == '/login/index'
    }

    /*If Loginhandler action finds user with given username and password then it will check user active or not if active set
    session.user to user and redirect request to login index action*/
    def "Login Handler checks user with user name and password and user being active then request redirected to login index"(){
        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "abc@gmail.com",isActive: true)
        user.save(flush: true)
        when:
        controller.loginHandler("vikas","1234675")
        then:
        response.redirectUrl == '/login/index'
    }

    def "Login Handler checks user with user name and password and user being NOT active then flash error that account is inactive"(){
        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "abc@gmail.com",isActive: false)
        user.save(flush: true)
        when:
        controller.loginHandler("vikas","1234675")
        then:
        flash.error == 'Account is in active'
    }

    def "Login Handler checks user with user name and password and if user not found then flash error user not found"(){
        setup:
        User user = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
                email: "abc@gmail.com",isActive: false)
        user.save(flush: true)
        when:
        controller.loginHandler("kvfpdfmk","nndso")
        then:
        flash.error == 'User not found'
    }
    
}
