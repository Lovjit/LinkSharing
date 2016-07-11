package com.ttnd.linksharing

class LoginController {

    def index() {
        if(session['user']){
            redirect(controller: 'User',action: 'index')
        }else{
            render "failure"
        }
    }

    def loginHandler (String userName,String password) {

        // If user present
        User user = User.findByUserNameAndPassword(userName,password)
        if(user){
            if(user.isActive){
            session['user'] = user
            redirect(action: 'index')
            }else{
            flash.error = "Account is in active"
            }
        }else{
            flash.error = "User not found"
        }
        render flash.error;

    }

    def logout(){
        session.invalidate()
        redirect(action: 'index')
    }

    def dummy(){
        println("Inside dummy")
        User user1 = User.findByEmail("vikas@gmail.com")
        /*user1.setIsActive(true)*/
        user1.isActive = true
       // println "------------------ ${user1.firstName}-------------------"
        user1.save(flush: true)
       // println ">>>>>>>>>>>>>>>>>>>>>>>>>>${user1.errors.getAllErrors().size()}<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
    }

}
