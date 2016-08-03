package com.ttnd.linksharing

class UtilController {

    def grailsApplication



    def index() {

        /*User user = new User(firstName: "Lovjit",lastName: "Bedi",userName: "lovjitbedi",password: "12345",
                email: "lovjitbedi@gmail.com")*/
        /*User user = new User(firstName: "Jalaj",lastName: "Tagra",userName: "jalajTagra",password: "1234675",
                email: "jalajTagra@gmail.com")*/
        User user = new User(firstName: "Bla",lastName: "B;a",userName: "bla",password: "1234675",
                email: "bla@gmail.com")
        user.save()
        render "Inside Setup"
        /*log.trace("trace")*/

        /*def bar = grailsApplication.config.grails.project.class.dir
        println(bar)*/


    }


    def test(){
        flash.error = "Have pateince"
        flash.message = "Just like I said"
        render view: '/Temp'
    }
}
