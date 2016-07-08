package com.ttnd.linksharing

class UserController {

    def messageSource

    def index() {
        render "User Dashboard"
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
