package com.ttnd.linksharing

import com.ttnd.linksharing.vo.RatingInfoVO
import com.ttnd.linksharing.vo.TopicVO
import grails.validation.ValidationException
import org.grails.datastore.gorm.finders.MethodExpression
import org.hibernate.criterion.CriteriaSpecification

class Topic {

    String name
    User createdBy
    Visibility visibility
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy:  User]
    static hasMany = [subscriptions:Subscription,resources:Resource]

    static constraints = {
        name nullable: false,blank: false,unique: 'createdBy'
        createdBy nullable: false, blank:false
        visibility nullable: false
    }

    static mapping = {
        sort name: 'asc'
    }

    static fetchMode = [createdBy : 'eager']

    def afterInsert = {
        Subscription subscription = new Subscription(topic: this,user: this.createdBy,seriousness: Seriousness.VERY_SERIOUS)

        try {
            withNewSession {
                subscription.save()
                log.info("Subscription for topic ${this.name} created")
                }


        } catch (ValidationException validationException) {
            log.error("Error while saving subscription")
        }
    }

    //Create static method getTrendingTopics in Topic domain which will return list of TopicVO
    // Public Topic with maximum resources is considered as a trending topic
    /*- Use createalias and groupproperty in criteria
    - Use resourceCount for getting resourceCount of resources of a topic
    - Use multiple order statement first one ordered by resource resourceCount and second one
    ordered by topic name
    -Maximum 5 records should be shown
    - Topic with maximum resource should come first*/
    // Select id,resourceCount('resource') as res_count from topic
    //        groupby id orderby res_count,name
    static List<TopicVO> getTrendingTopics(List<Long> topicIds,User loggedInUser){
            /*List<TopicVO> result = Topic.createCriteria().list{
                resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                //setResultTransformer(new AliasToBeanConstructorResultTransformer(TopicVO.class))
                projections{
                    groupProperty("id")
                    property("id","id")
                    property("name","name")
                    property("visibility","visibility")
                    property("createdBy","createdBy")
                    property("resources","resourceCount")
                    property("subscriptions","totalSubscription")
                }
                if(topicIds){
                    inList("id",topicIds)
                }
                order "resourceCount", "desc"
                maxResults 5
            }*/

        List result = Resource.createCriteria().list{
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            createAlias("topic","t")
            projections{
                groupProperty("topic")
                property("topic","topic")
                count("id","count")
            }
            if(topicIds){
                inList('t.id',topicIds)
            }
            order "count", "desc"
            order "t.name"
            maxResults 5
        }

        List<TopicVO> data = result.collect {
            Topic t = it.topic
            new TopicVO([id:t.id,name:t.name,visibility:t.visibility,createdBy:t.createdBy
                         ,totalSubscription:t.subscriptions.size(),resourceCount : t.resources.size()])
        }
            def topicIdSeriousnessMap = [:]
            List<Topic> topics
            List<Subscription> subscriptionList
            if(topicIds){
                topics = Topic.findAllByIdInList(topicIds)
            }else{
                topics  = Topic.getAll()
            }
            subscriptionList = Subscription.findAllByTopicInListAndUser(topics,loggedInUser)
            subscriptionList.each{
              topicIdSeriousnessMap.put(it.topic.id,it.seriousness)
            }

            List<TopicVO> finalResult = data.collect {
                if(topicIdSeriousnessMap.containsKey(it.id)){
                    it.seriousness = topicIdSeriousnessMap.get(it.id)
                }
                return it
            }
            return finalResult
        /*List result = Topic.createCriteria().list {
            createAlias("resources","resources")
            projections {
                groupProperty('id')
                resourceCount('resources','resCount')
            }
            order 'resCount' , 'desc'
            maxResults 5
            firstResult 0
        }
        List<Long> topicIds = new ArrayList<Long>()
        def counts = []
        result.each {
            topicIds.add(it[0])
            counts.add(it[1])
        }
        List<Topic> topics = Topic.findAllByIdInList(topicIds)
        List<TopicVO> topicVOList = new ArrayList<TopicVO>()
        topics.eachWithIndex {topic,idx ->
            TopicVO topicVO = new TopicVO(id: topic.id,name: topic.name,visibility: topic.visibility
                                          ,resourceCount: counts[idx] , createdBy: topic.createdBy)
            println topicVO
        }

        return null*/
    }

    List<User> getSubscribedUsers(){
        List<Subscription> subscriptionList = Subscription.findByTopic(this)
        List<User> users = new ArrayList<User>()
        subscriptionList.each {
            users.add(it.user)
        }
        return users
    }

    String toString(){
        return "Topic name ${this.name} and created by ${this.createdBy.name}"
    }

   /* def getUserProfileTopics(List<Long> topicIds,User user){
        List<TopicVO> topicVO =
    }*/

}
