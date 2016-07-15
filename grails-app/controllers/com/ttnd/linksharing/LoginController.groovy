package com.ttnd.linksharing

class LoginController {

    def index() {
        if(session['user']){
            redirect(controller: 'User',action: 'index')
        }else{

            //render "failure"

            Resource.getTopResources()



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
        Resource.changeIsRead(1,true)
    }

}
