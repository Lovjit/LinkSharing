package com.ttnd.linksharing.vo

import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility

/**
 * Created by ttnd on 14/7/16.
 */

//TopicVO will have id,name,visibility,count,createdBy fields
class TopicVO {

    Long id
    String name
    Visibility visibility
    int count
    User createdBy

    String toString() {
        return "id : ${this.id} , name : ${this.name} , count : ${this.count} , createdBy : ${this.createdBy.name}"
    }

}
