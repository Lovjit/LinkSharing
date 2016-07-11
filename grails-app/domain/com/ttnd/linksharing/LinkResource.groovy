package com.ttnd.linksharing

class LinkResource extends Resource{

    String url


    static constraints = {
        url url:true
    }

    String toString(){
        return "Link resource with url ${url} and ${createdBy}"
    }

}
