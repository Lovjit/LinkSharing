import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Seriousness
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility
import grails.validation.ValidationException

class BootStrap {

    static List<String> onStartDefaultTopicList = ['Topic1', 'Topic2', 'Topic3', 'Topic4', 'Topic5']

    def init = { servletContext ->

        createUsers()
        createTopics()
        createResources()
        addSubscribeTopics()
        createReadingItems()
        createResourceRatings()
        }

    def createUsers (){

        // Creating default admin user
        User adminUser = new User(firstName: "Admin", lastName: "Ttn", userName: "admin", password: "admin@ttn",
                email: "admin@ttnd.com",isActive: true)
        // Creating default normal user
        User normalUser = new User(firstName: "Aam", lastName: "Aadmi", userName: "aam", password: "aam@aadmi",
                email: "aam@aap.com",isActive: true)
        if (!User.count) {
            try {
                adminUser.save(failOnError: true)
                normalUser.save(failOnError: true)
                log.info("Admin and normal user created")
            } catch (ValidationException validationException) {
                log.error("Error while creating users in bootstrap")
            }
        }


    }

    /*Add createTopics method in bootstrap
    - 5 topics per user needs to be created if Topic resourceCount is 0
    - Creator of topic should automatically be subscribed to topic (Use after insert event of topic)
    - Errors should be logged if topic or subscriptions is not saved
    - WithNewSession in after insert because it will not work without it
    - Seriousness should be very serious for auto subscribed topic in after insert*/
    def createTopics(){
        if(!Topic.count){
            User.getAll().eachWithIndex {user,userIdx ->
                onStartDefaultTopicList.eachWithIndex {defaultTopic,defaultTopicIdx ->
                    Topic topic = new Topic(name: defaultTopic, createdBy: user, visibility: Visibility.PUBLIC)
                    try {
                        topic.save(flush: true)
                        log.info("Topic ${defaultTopic} created for user ${user}")
                    } catch (ValidationException validationException) {
                        log.error("Error while saving topic")
                    }
                }

            }
        }
    }

    /*Add createResources method which create 2 link resource and 2 document resource in each topic
    - It should create resource only if there is not data in resource table
    - Description of the resource should include topic name
    - Error should be logged
    - Creator of the resource should be same as creator of the topic*/
    def createResources(){
        if(!Resource.count){

            Topic.getAll().each {
                LinkResource lr1 = new LinkResource(description: "Link Resource for topic ${it.name}",
                                                    createdBy: it.createdBy,topic: it,url: "http://www.${it.name}.com")
                LinkResource lr2 = new LinkResource(description: "Link Resource for topic ${it.name}",
                        createdBy: it.createdBy,topic: it,url: "http://www.${it.name}.com")
                DocumentResource dr1 = new DocumentResource(description: "Document Resource for topic ${it.name}",
                        createdBy: it.createdBy,topic: it,filePath: "/file/path/for/${it.name}")
                DocumentResource dr2 = new DocumentResource(description: "Document Resource for topic ${it.name}",
                        createdBy: it.createdBy,topic: it,filePath: "/file/path/for/${it.name}")
                try{
                    lr1.save(failOnError: true)
                    lr2.save(failOnError: true)
                    dr1.save()
                    dr2.save()
                    log.info("Link resources and document resources created")
                }catch (ValidationException validationException){
                    log.error("Error while saving link and document resources")
                }
            }


        }
    }

    /*Add subscribeTopics for user to subscribe all the topics which are not created by user
    - Subscription should be created only if the subscription do not exit for user and topic
    - Errors should be logged
    - log statement when subscription is created with user and topic object
    - toString should be implemented for topic with topic name and for user with username*/
    def addSubscribeTopics (){
        User.getAll().eachWithIndex {user,userIdx ->
            Topic.getAll().each {
                String topicCreatorName = it.createdBy
                if(user != topicCreatorName){
                    Subscription subscription = new Subscription(topic: it,user : user,seriousness: Seriousness.CASUAL)
                    try {
                        subscription.save()
                        log.info("Subscription saved for ${user.name} and topic ${it.name}")
                    }catch(ValidationException validationException){
                        log.error("Error while saving subscription")
                    }

                }
            }
        }

    }

    /*Add createReadingItems in bootstrap to create dummy reading items
    - Resources which are not created by the user in the topics subscribed by him/her
      should have that resource in his/her reading item.
    - Reading item of resource should be created only if it does not already exit in users reading item*/
    def createReadingItems(){
        User.getAll().eachWithIndex{ User user, int userIdx ->
            List<Subscription> subscriptions = Subscription.findAllByUser(user)
            subscriptions.eachWithIndex{ Subscription subscription, int subscriptionIdx ->
                Topic topic = subscription.topic
                List<Resource> resources = Resource.findAllByTopic(topic)
                resources.each {
                    String resourceCreator = it.createdBy
                    println("Resource Creator ${resourceCreator} and user name ${user.userName}")
                    if(user.userName != resourceCreator){
                        ReadingItem readingItem = new ReadingItem(resource: it,user: user,isRead: false)
                        readingItem.save()
                    }
                }
            }

        }
    }


    /*Add createResourceRatings to add dummy ratings
    - Add rating for all the unread reading items of the user
    - createdBy of resource rating should be createdby of reading item and resource of resourcerating should be
    resource of readingitem*/

    def createResourceRatings(){
        User.getAll().eachWithIndex {user,userIdx ->
            List<ReadingItem> readingItems = ReadingItem.findAllByUserAndIsRead(user,false)
            readingItems.eachWithIndex{ ReadingItem readingItem, int readingItemIdx ->
                ResourceRating resourceRating = new ResourceRating(score: 3,user: readingItem.user,resource: readingItem.resource)
                resourceRating.save()
            }
        }

    }

    def destroy = {
    }
}
