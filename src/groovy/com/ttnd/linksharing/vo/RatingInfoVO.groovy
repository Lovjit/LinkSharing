package com.ttnd.linksharing.vo

/**
 * Created by ttnd on 14/7/16.
 */

/*RatingInfoVO will have fields totalVotes, averageScore, totalScore*/
class RatingInfoVO {

    int totalVotes
    long averageScore
    int totalScore

    String toString(){
        return "totalVotes : ${this.totalVotes} , averageScore : ${this.averageScore} , totalScore : ${this.totalScore}"
    }

}
