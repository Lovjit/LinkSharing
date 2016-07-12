package com.ttnd.linksharing

enum Seriousness {

    CASUAL,SERIOUS,VERY_SERIOUS

    /*String seriousNessInString

    Seriousness(String seriousnessInString){
        this.seriousNessInString = seriousnessInString
    }*/

    /*Create static method in seriuosness which take string as parameter and returns seriousness, it should be case insensitive*/
    static Seriousness getSeriousnessFromString(String name){
        return Seriousness.valueOf(name.toUpperCase())
    }

}