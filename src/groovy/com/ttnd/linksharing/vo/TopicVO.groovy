package com.ttnd.linksharing.vo

import com.ttnd.linksharing.Seriousness
import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility

/**
 * Created by ttnd on 14/7/16.
 */

//TopicVO will have id,name,visibility,resourceCount,createdBy fields
class TopicVO {

    Long id
    String name
    Visibility visibility
    int resourceCount
    int totalSubscription
    User createdBy
    Seriousness seriousness

    String toString() {
        return "id : ${this.id} , name : ${this.name} , post resourceCount : ${this.resourceCount} , createdBy : ${this.createdBy.name}" +
                "total subscription : ${this.totalSubscription}"
    }

}
