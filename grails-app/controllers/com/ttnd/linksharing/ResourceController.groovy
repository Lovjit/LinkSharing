package com.ttnd.linksharing

import com.ttnd.linksharing.co.DocumentResourceCO
import com.ttnd.linksharing.co.LinkResourceCO
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO
import com.ttnd.linksharing.vo.ResourceVO
import com.ttnd.linksharing.vo.TopicVO
import grails.converters.JSON

class ResourceController {

    def resourceService
    def linkResourceService
    def documentResourceService
    def topicService
    def userService
    def resourceRatingService

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

    def saveLinkRes(LinkResourceCO linkResourceCO){
        render linkResourceService.save(linkResourceCO,session['user'].id)
    }

    def saveDocResource(DocumentResourceCO documentResourceCO){
        render documentResourceService.save(documentResourceCO,session['user'].id)
    }

    def editLinkRes(LinkResourceCO linkResourceCO){
        render linkResourceService.save(linkResourceCO,session['user'].id)
    }

    def editDocResource(DocumentResourceCO documentResourceCO){
        render documentResourceService.save(documentResourceCO,session['user'].id)
    }

    def deleteLinkRes(Long resourceId){
        resourceService.deleteResource(resourceId)
        redirect(controller: 'user',action: 'index')
    }

    def showPost(Long resourceId){
        List<TopicVO> topicVOList
        ResourceVO resourceVO
        User loggedInUser
        resourceVO = resourceService.getResourceInfo(resourceId)
        topicVOList = Topic.getTrendingTopics(null,null)
        if(session['user']){
            /*topicVOList = topicService.getTrendingTopics(session['user'].id)*/
            loggedInUser = userService.getUser(session['user'].id)
            resourceService.markAsRead(resourceId,session['user'].id)
            // Mark resource as read
            resourceService.markAsRead(resourceId,session['user'].id)
        }
        List<Topic> topics = topicService.getAllTopics()
        List<Long> subscribedTopicIds = userService.getSubscribedTopicIds(loggedInUser)
        render ('view' : 'index' ,
                'model' : ['trendingTopics' : topicVOList,"resource" : resourceVO
                            ,"subscribedTopicIds" : subscribedTopicIds,
                            'topics' : topics,user : loggedInUser])
    }

    def getRatingInfo(Long resourceId){
        RatingInfoVO ratingInfoVO = resourceRatingService.getRatingInfo(resourceId)
        int score
        if(session['user']){
            score  = resourceRatingService.getUserSpecificRating(resourceId,session['user'].id)
            ratingInfoVO.userRating = score
        }
        render ratingInfoVO as JSON

    }

    def downloadResource(Long resourceId){
        Resource resource = Resource.findById(resourceId)
        String filePath = resource.filePath
        String[] arrays = filePath.split("/")
        String fileName = arrays[arrays.length-1]
        response.setContentType('APPLICATION/OCTET-STREAM')
        response.setHeader('Content-Disposition', 'Attachment;Filename="'+fileName+'"'+"."+resource.contentType)

        def outputStream = response.getOutputStream()
        outputStream << new File(filePath).bytes
        outputStream.flush()
        outputStream.close()
    }

    def markAsRead(Long resourceId){
        render resourceService.markAsRead(resourceId,session['user'].id)
    }

}
