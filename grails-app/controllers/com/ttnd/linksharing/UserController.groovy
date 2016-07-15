package com.ttnd.linksharing

import com.ttnd.linksharing.co.SearchCO

class UserController {

    def messageSource

    def index() {
        //render "User Dashboard"

         /*Add Inbox feature on user/index when user is loggedin
        - Create method getUnReadResources in user domain which takes SearchCO argument
        and returns unreaditems of user from ReadingItem domain
        - The search should also work using user/index page, q parameter of SearchCO.
          If searchco.q is found then getUnReadResources method will search the items based on ilike of resource.description.
        - The pagination parameter should also be used in getUnReadResources criteria query.*/
        User user = User.findById(1)
        println User.getUnReadResources(new SearchCO(q: "Topic1"),user)



    }

    /*User user1 = new User(firstName: "Vikas",lastName: "Sharma",userName: "vikas",password: "1234675",
            email: email)*/
    def register(String firstName,String lastName,String userName,String password,String email){
        User user = new User(firstName: firstName,lastName: lastName,userName: userName,password: password,
                email: email)
        user.validate()
        user.errors.allErrors.each {
            println message(error: it)
        }
        /*println "Inside Register"
        if(user.errors.hasErrors()){
            println "Inside errors"
            String errorField = user.errors.fieldError.field
            println errorField
            if(errorField == 'email'){
                println "Before message source"
                println messageSource.getMessage('user.update.email.error',null,null)
               // println(messageSource.properties.user.update.email.error)
            }
        }*/
    }
}
