package com.ttnd.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO

class ResourceController {

    def index() { }

    def load(Long resourceId){
        Resource resource = Resource.findById(resourceId)
        if(resource){
            resource.delete()
            render "Resource deleted successfully"
        }else{
            render "Exception while deleting resource"
        }
    }

    /*Add search action in a resource controller,
    which will search if q parameter is set and
    it will set visibility of resourcesearchco to public*/
    def search(ResourceSearchCO resourceSearchCO){
        if(!resourceSearchCO.q){
            resourceSearchCO.visibility = Visibility.PUBLIC
        }
        println "Topic id is ${resourceSearchCO.topicId}"
        println "Visibility is ${resourceSearchCO.visibility}"
        def result = Resource.search(resourceSearchCO)
        List bla = result.list()
        println bla
    }

    //Call getRatingInfo method from resource show action
    def show(){
        Resource.getRatingInfo()
    }

}
