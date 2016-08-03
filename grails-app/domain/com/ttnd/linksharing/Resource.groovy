package com.ttnd.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO
import grails.validation.ValidationException

abstract class Resource {

    String description
    User createdBy
    Topic topic
    RatingInfoVO ratingInfoVO
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy : User,topic : Topic]
    static transients = ['ratingInfoVO']
    static hasMany = [ratings:ResourceRating,readingItems:ReadingItem]
    static mapping = {
        description type: 'text'
    }

    def afterInsert = {
        ReadingItem readingItem = new ReadingItem(resource: this,user: this.createdBy,isRead: false)

        try {
            withNewSession {
                readingItem.save()
                log.info("Reading item for resource ${this.description} created")
            }


        } catch (ValidationException validationException) {
            log.error("Error while saving subscription")
        }
    }



    static namedQueries = {
        search { ResourceSearchCO rsCo ->
                createAlias('topic','t')
                eq('t.id', rsCo.topicId)
                eq('t.visibility',rsCo.visibility)
        }
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
