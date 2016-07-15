package com.ttnd.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO

abstract class Resource {

    String description
    User createdBy
    Topic topic
    RatingInfoVO ratingInfoVO
    Date dateCreated
    Date lastUpdated

    static transients = ['ratingInfoVO']
    static hasMany = [ratings:ResourceRating,readingItems:ReadingItem]
    static mapping = {
        description type: 'text'
    }



    static namedQueries = {
        search { ResourceSearchCO rsCo ->
                createAlias('topic','t')
                eq('t.id', rsCo.topicId)
                eq('t.visibility',rsCo.visibility)
        }
    }

    //totalVotes, averageScore, totalScore
    static RatingInfoVO getRatingInfo(){
        List result = ResourceRating.createCriteria().get {
            projections {
                count()
                avg("score")
                sum("score")
            }
        }
        RatingInfoVO ratingInfoVO = new RatingInfoVO(totalVotes: result[0],averageScore: result[1],totalScore: result[2])
        println(ratingInfoVO)
        return ratingInfoVO
    }

    /*Add top post on / when user is not logged in
       -Resource with maximum number of votes(Rating) will be  top post
       -Only 5 posts should be shown in order of maximum vote count
       -Use groupProperty with id of resource otherwise lots of queries will be fired
       -Collect Resource list with resource id using getall rather then finder otherwise ordering will not be maintained*/
    // Select count(ratings) , id from Resource
    // groupby id
    static List<Resource> getTopResources(){
        List result = Resource.createCriteria().list {
            createAlias('ratings','ratings')
            projections {
                groupProperty('id')
                count('ratings','ratingsCount')
            }
            order('ratingsCount','desc')
            maxResults 5
            firstResult 0

        }

        List<Long> resourceIds = new ArrayList<Long>()
        def counts = []
        result.each {
            resourceIds.add(it[0])
            counts.add(it[1])
        }
        List<Resource> resources = Resource.findAllByIdInList(resourceIds)
        println resources
        return null
    }

    /*Create readingItem/changeIsRead which takes Long id and Boolean isRead
    - User executeUpdate to change the isRead of readingItem with given id
    - If value returned by executeUpdate is 0 then render error else render success*/

    static void changeIsRead(Long resourceId,Boolean isRead){

        //List usersInfo=User.executeQuery("select firstName, lastName from User where age >:age ",[age:18])

       int executeCode =  Resource.executeUpdate(" update ReadingItem set isRead = :isRead WHERE resource.id = :resourceId"
                                                        ,[isRead : isRead , resourceId : resourceId] )

        if(executeCode > 0){
            println "<<<<Success>>>>>"
        }else{
            println "<<<<<Error>>>>"
        }

    }


    String toString(){
        return "Description : ${this.description} , createdBy : ${this.createdBy} , topic name : ${this.topic.name}"
    }
}
