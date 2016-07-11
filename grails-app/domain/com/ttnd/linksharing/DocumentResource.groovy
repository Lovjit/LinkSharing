package com.ttnd.linksharing

class DocumentResource extends Resource{

    String filePath


    static constraints = {
        filePath nullable: false,blank: false
    }

    String toString(){
        return "Document resource file path is ${filePath} and ${createdBy}"
    }



}
