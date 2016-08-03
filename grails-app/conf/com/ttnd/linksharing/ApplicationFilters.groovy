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


        /*Update filters and user should be able to access all the actions except those which require session user
        - Few actions which requires session users are /linkResource/save, readingItem/save,
                resource/delete, resourceRating/save, subscription controller, topic/save, /user/index*/
        accessCheck(uri:['/linkResource/save','/readingItem/save','/resource/delete'
                         ,'/resourceRating/save','/topic/save','/user/index']){
            before = {
                /*println("<<<<<<<<<<<<<Inside access check>>>>>>>>>>>>>>>>>>>")
                render '404'*/
            }
        }

    }


}
