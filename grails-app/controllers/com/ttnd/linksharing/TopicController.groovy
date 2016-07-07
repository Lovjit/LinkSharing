package com.ttnd.linksharing

class TopicController {

    def index() { }

    def show(Long id){
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

}
