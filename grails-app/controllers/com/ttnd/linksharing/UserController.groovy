package com.ttnd.linksharing

import com.ttnd.linksharing.co.RegisterUserCO
import com.ttnd.linksharing.co.SearchCO
import com.ttnd.linksharing.vo.ResourceVO
import com.ttnd.linksharing.vo.TopicVO

class UserController {

    def messageSource
    def assetResourceLocator
    def emailService
    def topicService
    def utilService
    def userService

    def index() {
        List<Topic> topics = topicService.getAllTopics()
        long loggedInUserId = session['user'].id
        User loggedInUser = User.findById(loggedInUserId)
        List<TopicVO> trendingTopicVO = Topic.getTrendingTopics(null, loggedInUser)
        List<Long> subscribedTopicIds = userService.getSubscribedTopicIds(loggedInUser)
        List<TopicVO> subscribedTopicVO
        if (subscribedTopicIds) {
            /*subscribedTopicVO = Topic.getTrendingTopics(subscribedTopicIds, loggedInUser)*/
            subscribedTopicVO = topicService.getSubscribedTopics(loggedInUser)
        }

        List<Subscription> userSubscriptions = Subscription.findAllByUser(loggedInUser)
        subscribedTopicVO.eachWithIndex { TopicVO topicVO, int i ->
            boolean isModified = false
            userSubscriptions.eachWithIndex { Subscription subscription, int j ->
                if (subscription.topic.id == topicVO.id) {
                    isModified = true
                    topicVO.seriousness = subscription.seriousness
                }
            }
            if(!isModified){
                topicVO.seriousness = null;
            }
        }


        List<ReadingItem> readingItems = ReadingItem.createCriteria().list {
            createAlias('user', 'user')
            createAlias('resource', 'resource')
            eq("user", loggedInUser)
            eq("isRead", false)
            order "lastUpdated", "desc"
            maxResults 5
        }
        List<ResourceVO> resourceVOList = readingItems.collect {

            ResourceVO resourceVO = new ResourceVO(createdBy: it.resource.createdBy, topic: it.resource.topic,
                    description: it.resource.description, resourceId: it.resource.id)
            if ((it.resource) instanceof LinkResource) {
                resourceVO.isLinkResource = true
                resourceVO.url = it.resource.url
            } else {
                if ((it.resource) instanceof DocumentResource) {
                    resourceVO.filePath = it.resource.filePath
                }
            }
            resourceVO.lastUpdated = utilService.getDateDiffInString(it.resource.lastUpdated, new Date())
            return resourceVO

        }

        render('view': 'index',
                'model': ['trendingTopics'  : trendingTopicVO, 'subscribedTopicIds': subscribedTopicIds,
                          "subscribedTopics": subscribedTopicVO, 'user': loggedInUser, 'topics': topics,
                          "resources"       : resourceVOList])
    }


    def register(String firstName, String lastName, String userName, String password, String email) {
        User user = new User(firstName: firstName, lastName: lastName, userName: userName, password: password,
                email: email)
        user.validate()
        user.errors.allErrors.each {
            println message(error: it)
        }
    }

    def resetPassword() {
        println "Inside reset password"
        render(view: 'forgotPassword')
    }

    def test() {
        return [subscribedTopicVO: subscribedTopicVO, totalTrendingTopics: subscribedTopicVO.size()]
    }

    def sendEmailInvite(String emailId, Long topicId) {
        render emailService.sendMail(emailId, topicId)
    }

    def getProfilePage() {

        // Get users topics with seriousness
        Long loggedInUserId = session['user'].id
        User loggedInUser = userService.getUser(loggedInUserId)
        List<TopicVO> topicVOList = userService.getUserTopics(loggedInUserId)
        // Get subscribed topics with seriousness
        List<Long> subscribedTopicIds = userService.getSubscribedTopicIds(loggedInUser)
        /*
        List<TopicVO> subscribedTopicVO
        if(subscribedTopicIds){
            subscribedTopicVO = Topic.getTrendingTopics(subscribedTopicIds, loggedInUser)
        }*/
        List<Topic> topics = topicService.getAllTopics()
        render('view': 'home',
                'model': ['user'              : loggedInUser, 'userTopics': topicVOList/*,
                          'subscribedTopics'  : subscribedTopicVO*/,
                          'subscribedTopicIds': subscribedTopicIds,
                          'topics'            : topics
                ])
    }

    def getPublicProfilePage(Long userId) {
        // Get users topics with seriousness
        User user = userService.getUser(userId)
        List<TopicVO> topicVOList = userService.getUserTopics(userId)
        // Get subscribed topics with seriousness
        List<Long> subscribedTopicIds = []
        List<TopicVO> subscribedTopicVO
        subscribedTopicVO = topicService.getSubscribedTopics(user)
        if (session['user']) {
            Long loggedInUserId = session['user'].id
            User loggedInUser = User.findById(loggedInUserId)
            List<Subscription> userSubscriptions = Subscription.findAllByUser(loggedInUser)
            subscribedTopicVO.eachWithIndex { TopicVO topicVO, int i ->
                boolean isModified = false
                userSubscriptions.eachWithIndex { Subscription subscription, int j ->
                    if (subscription.topic.id == topicVO.id) {
                        topicVO.seriousness = subscription.seriousness
                        isModified = true
                    }
                }
                if(!isModified){
                    topicVO.seriousness = null
                }
            }
            topicVOList.eachWithIndex { TopicVO topicVO, int i ->
                boolean isModified = false
                userSubscriptions.eachWithIndex { Subscription subscription, int j ->
                    if (subscription.topic.id == topicVO.id) {
                        topicVO.seriousness = subscription.seriousness
                        isModified = true
                    }
                }
                if(!isModified){
                    topicVO.seriousness = null
                }
            }
            subscribedTopicIds = userService.getSubscribedTopicIds(loggedInUser)
        }
        List<Topic> topics = topicService.getAllTopics()

        List<ResourceVO> resourceVOList = user.resources.collect {

            ResourceVO resourceVO = new ResourceVO(createdBy: it.createdBy, topic: it.topic,
                    description: it.description, resourceId: it.id)
            if ((it) instanceof LinkResource) {
                resourceVO.isLinkResource = true
                resourceVO.url = it.url
            } else {
                if ((it) instanceof DocumentResource) {
                    resourceVO.filePath = it.filePath
                }
            }
            resourceVO.lastUpdated = utilService.getDateDiffInString(it.lastUpdated, new Date())
            return resourceVO

        }
        render('view': 'publicHome',
                'model': ['user'              : user, 'userTopics': topicVOList,
                          'subscribedTopics'  : subscribedTopicVO,
                          'subscribedTopicIds': subscribedTopicIds,
                          'topics'            : topics, "resources": resourceVOList/*,
                          'trendingTopics'  : trendingTopicVO*/
                ])
    }

    def getImage(Long userId) {
        User user = User.findById(userId)
        if (user != null && user.profilePic != null) {
            response.contentType = 'image/png' // or the appropriate image content type
            response.contentLength = user.profilePic.length
            response.outputStream << user.profilePic
            response.outputStream.flush()
        } else {
            def value = assetResourceLocator.findAssetForURI("defaults/user-image.png");
            println "default image " + value.contentLength();
            render file: value.inputStream, contentType: 'image/png'
        }
    }

    def logout() {
        session.invalidate()
        redirect(controller: 'login', action: 'index')
    }

    def validateUpdatedUserName(String username) {
        User user = userService.getUser(session['user'].id)
        if (user.userName == username) {
            render contentType: "text/json", text: 'true' // Good to register
        } else {
            user = User.findByUserName(username)
            if (user) {
                render contentType: "text/json", text: 'false' // Good to register
            } else {
                render contentType: "text/json", text: 'true' // Good to register
            }
        }


    }

    def updateProfile(RegisterUserCO registerUserCO) {
        render userService.updateProfile(registerUserCO, session['user'].id)
    }

    def updatePassword(String password, String confirmPassword) {
        render userService.updatePassword(password, confirmPassword, session['user'].id)
    }


}