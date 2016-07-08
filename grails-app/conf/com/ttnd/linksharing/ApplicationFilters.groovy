package com.ttnd.linksharing

class ApplicationFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

                log.info('%d %5p %c{1} [%t] Request for /' + controllerName+"/"+actionName)
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }

        loginCheck(controller : '*',action:'*',controllerExclude : 'login'){
            before = {
                /*if(!session.user){
                    redirect(controller: 'login',action: 'index')
                }*/
            }
        }
    }


}
