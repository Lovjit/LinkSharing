package com.ttnd.linksharing

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
}
