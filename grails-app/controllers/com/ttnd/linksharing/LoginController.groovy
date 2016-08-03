package com.ttnd.linksharing

import com.ttnd.linksharing.co.RegisterUserCO
import com.ttnd.linksharing.vo.ResourceVO

class LoginController {

    def resourceService
    def userService

    def index() {
        if(session['user']){
            redirect(controller: 'User',action: 'index')
        }else{

            //session['user'] = User.findById(2)
            // Fetch top resources
            List<ResourceVO> topResources = resourceService.getTopResources()
            // Fetch recent shares
            List<ResourceVO> recentResources = resourceService.getRecentShares()
            render(view: 'home',model: ['recentResources' : recentResources,
                                        'topResources' : topResources])



        }
    }

    def loginHandler (String nameEmail,String password) {

        // If user present
        User user = User.findByUserNameAndPassword(nameEmail,password)

        if(!user){
            user = User.findByEmailAndPassword(nameEmail,password)
        }
        if(user){
            if(user.isActive){
                session['user'] = user
                render 'success'
                //redirect(action: 'index')
            }else{
                //response.sendError(500)
                render "User inactive"
            }
        }else{
            //response.sendError(500)
            render ''
        }

    }

    def registerHandler(RegisterUserCO registerUserCO){
        // Create user
        def result = userService.createUser(registerUserCO)
        // Put in session
        // Redirect user home page
        if(result){
            session['user'] =result
            redirect(controller: 'user',action : 'index')
        }else{
            render "Error while creating user.Try after some time!"
        }

    }

    def validateEmail(String email){
        User user = User.findByEmail(email)
        if(user){
            render contentType: "text/json", text: 'false' // Bad to register
        }else{
            render contentType: "text/json", text: 'true' // Good to register
        }
    }

    def validateUsername(String username){
        User user = User.findByUserName(username)
        if(user){
            render contentType: "text/json", text: 'false' // Bad to register
        }else{
            render contentType: "text/json", text: 'true' // Good to register
        }
    }

}
