package com.ttnd.linksharing

import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

abstract class Resource {

    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static hasMany = [ratings:ResourceRating,readingItems:ReadingItem]
    static mapping = {
        description type: 'text'
    }
}
