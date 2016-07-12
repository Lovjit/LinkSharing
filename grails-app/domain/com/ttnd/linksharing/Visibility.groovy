package com.ttnd.linksharing

enum Visibility {

    PUBLIC,PRIVATE


    static Visibility getVisibilityFromString(String name){
        return Visibility.valueOf(name.toUpperCase())
    }

}