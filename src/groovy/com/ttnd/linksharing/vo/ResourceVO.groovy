package com.ttnd.linksharing.vo

import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User

/**
 * Created by ttnd on 28/7/16.
 */
class ResourceVO {

    Long resourceId
    String description
    User createdBy
    Topic topic
    String lastUpdated
    RatingInfoVO ratingInfoVO
    boolean isLinkResource
    int rating
    String url
    String filePath


}
