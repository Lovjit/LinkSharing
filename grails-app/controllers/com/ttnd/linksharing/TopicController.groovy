package com.ttnd.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO

class TopicController {

    def index() { }

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
                flash.message = 'Topic saved'
                render 'success'
            }else{
                flash.error = 'Error while saving topic'
                render "${flash.error}"
            }
        }catch(IllegalArgumentException){
            flash.error = 'Error while saving topic'
            render "${flash.error}"
        }

    }

    def read(Long topicId){
        render Topic.findById(topicId).toString()
    }

    def test(){
        Topic.getTrendingTopics()
    }

}
