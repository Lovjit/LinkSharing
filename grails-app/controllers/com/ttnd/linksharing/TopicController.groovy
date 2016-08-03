package com.ttnd.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.ResourceVO
import com.ttnd.linksharing.vo.TopicVO

class TopicController {

    def topicService
    def userService
    def utilService

    def index(Long topicId) {
        List<User> users = Subscription.createCriteria().list {
            createAlias("user","user")
            createAlias("topic","t")
            projections {
                property("user")
            }
            eq('t.id',topicId)
        }

        long loggedInUserId
        User loggedInUser
        if(session['user']){
            loggedInUserId = session['user'].id
            loggedInUser = User.findById(loggedInUserId)
        }

        List<Topic> topics = topicService.getAllTopics()
        Topic topic = Topic.findById(topicId)
        List<Resource> topicResources = topic.resources.toList()
        List<ReadingItem> readingItems = []
        List<ResourceVO> resourceVOList = []
        if(topicResources.size() > 0){
                readingItems = ReadingItem.createCriteria().list {
                createAlias('user','user')
                createAlias('resource','resource')
                if(loggedInUser != null){
                        eq("user",loggedInUser)
                }
                eq("isRead",false)
                'in'("resource",topicResources)
                order ("lastUpdated", "desc")
                maxResults 5
            }

            resourceVOList = readingItems.collect{

                ResourceVO resourceVO = new ResourceVO(createdBy : it.resource.createdBy, topic: it.resource.topic,
                        description: it.resource.description,resourceId: it.resource.id)
                if( (it.resource) instanceof LinkResource){
                    resourceVO.isLinkResource = true
                    resourceVO.url = it.resource.url
                }else{
                    if( (it.resource) instanceof DocumentResource){
                        resourceVO.filePath = it.resource.filePath
                    }
                }
                resourceVO.lastUpdated = utilService.getDateDiffInString(it.resource.lastUpdated,new Date())
                return resourceVO

            }

        }

        User topicCreator = topic.createdBy
        List<Long> subscribedTopicIds = userService.getSubscribedTopicIds(loggedInUser)
        Subscription subscription = Subscription.findByTopicAndUser(topic,loggedInUser)
        if(!subscription){
            subscription = new Subscription()
        }
        TopicVO topicVO = new TopicVO(id : topic.id,name:topic.name,visibility: topic.visibility,
                                      resourceCount: resourceVOList.size(),totalSubscription: users.size(),
                                      createdBy: topicCreator,seriousness: subscription.seriousness)

        render (view: 'index',model: ['users' : users,'resources' : resourceVOList,'topic' : topicVO,
                                      'subscribedTopicIds' : subscribedTopicIds,'topics' : topics])
    }

    def show(ResourceSearchCO resourceSearchCO, Long id){
        Topic topic = Topic.findById(id)
        if(topic){

            if(topic.visibility == Visibility.PUBLIC){
                // Render success
                render "success"
            }else{
                // else topic is private
                if(topic.subscriptions){
                    render "success"
                }else{
                    flash.error = "Topic not found"
                    redirect('controller' : 'login','action' : 'index')
                }
            }

        }else{
            // Topic doesn't exist
            flash.error = "Topic not found"
            redirect('controller' : 'login','action' : 'index')
        }
    }

    /*Add topic save action in TopicController
    -Add save action in topic controller, which takes a topic and string seriousness as an argument
    -Create a method in visibility enum to convert string into enum and write test case for the same
    -Session user should be createdBy of the topic
    -If a topic is saved without error flash message should be set and success should be rendered
    -If a topic is not saved errors should be logged flash error should be set and error text should be rendered
    -Write the test case for the Topic save.*/
    def save(String topicName,String visibility){
        try{

            Topic topic = new Topic(name: topicName,visibility: Visibility.getVisibilityFromString(visibility),
                                    createdBy: session['user'])
            if(topic.validate()){
                topic.save()
                //flash.message = 'Topic saved'
                render 'success'
            }else{
                //flash.error = 'Error while saving topic'
                render ""
            }
        }catch(IllegalArgumentException){
            //flash.error = 'Error while saving topic'
            render ""
        }
        //redirect(controller: 'user',action: 'test')

    }

    def read(Long topicId){
        render Topic.findById(topicId).toString()
    }

    def test(){
        //Topic.getTrendingTopics()
    }

    def update(Long topicId,String topicName){
        Topic topic = Topic.findById(topicId)
        topic.name = topicName
        if(topic.validate()){
            topic.save(flush: true)
            render "Topic updated successfully"
        }else{
            response.sendError(500)
        }
    }

    def updateVisibility(Long topicId,String visibility){
        def result = topicService.updateTopicVisibility(topicId,visibility)
        if(result){
            render "Topic updated successfully"
        }else{
            response.sendError(500)
            render "Error while updating"
        }
    }

    def getTrendingTopic(){
        List<TopicVO> topicVO = topicService.getTrendingTopics(session['user'].id)
        return topicVO
    }

    def deleteTopic(Long topicId){
        render topicService.delete(topicId)
    }


}
