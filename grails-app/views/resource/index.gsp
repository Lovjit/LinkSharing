
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.1.1/jquery.rateyo.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.1.1/jquery.rateyo.min.js"></script>
</head>

<body>

<div class="container body-padding-min-height">
    <div class="col-md-7">
        <section class="panel panel-default">
            <div class="panel-body">
                <div class="row">

                    <div class="col-md-12">

                        <div class="col-md-2">
                            <i class="fa fa-user fa-5x" class="solid-border" aria-hidden="true"></i>
                        </div>

                        <div class="col-md-9">
                            <div class="row">
                                <p style="color : black">${resource.createdBy.firstName} ${resource.createdBy.lastName} &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
                                &nbsp;&nbsp; &nbsp;&nbsp;
                                    <span class="pull-center" style="color:blue">${resource.topic.name}</span>
                                    <span style="color : grey;" class="pull-right"> ${resource.lastUpdated} </span>
                                </p>

                                <input id="resource-id-index-page" type="hidden" name="resourceId" value=${resource.resourceId} />

                            </div>
                            <div class="row">
                                <div class="col-md-3 padding-left-initial">
                                    <span style="color : grey"> @${resource.createdBy.userName} </span>
                                </div>
                                <div class="col-md-8">

                                    <div class="col-md-8">
                                        <div id="rateYo" class="pull-right"></div>
                                    </div>
                                    <div class="col-md-4">
                                        <span id="resource-page-no-of-reviews"></span>
                                    </div>


                                </div>


                            </div>

                        </div>

                    </div>


                </div>
                <div class="container-fluid">
                    <div class="row">
                        <section class="panel">
                            <input type="hidden" id="resource-view-topic-id" value="${resource.topic.id}">
                            <p id="resource-description-view-page">${resource.description}</p>
                            <i class="fa fa-facebook" aria-hidden="true" style="float:left"> &nbsp;&nbsp;</i>
                            <i class="fa fa-google-plus" aria-hidden="true" style="float:left">&nbsp;&nbsp;</i>
                            <i class="fa fa-twitter" aria-hidden="true" style="float:left;margin-right:70px"></i>
                            <div class="pull-right">
                                    <g:if test="${session['user'] != null && session['user'].id == resource.createdBy.id}">
                                        <a href="#" aria-hidden="true" id="resource-link-delete"
                                           data-toggle="modal" data-target="#delete-link">Delete</a> &nbsp;&nbsp;&nbsp;
                                    </g:if>
                                <g:if test="${resource.isLinkResource}">
                                    <g:if test="${session['user'] != null && session['user'].id == resource.createdBy.id}">
                                        <a href="#" aria-hidden="true" id="resource-link-edit"
                                             data-toggle="modal" data-target="#edit-link">Edit</a>&nbsp;&nbsp;&nbsp;
                                    </g:if>
                                    <a href="${resource.url}" id="resource-link-view-site" target="_blank">View Full Site</a>
                                </g:if>
                                <g:else>
                                    <g:if test="${session['user'] != null && session['user'].id == resource.createdBy.id}">
                                        <a href="#" aria-hidden="true" id="resource-document-edit"
                                             data-toggle="modal" data-target="#edit-document">Edit</a>&nbsp;&nbsp;&nbsp;
                                    </g:if>
                                    <a href="resource/downloadResource?resourceId=${resource.resourceId}" id="document-download" data-path="${resource.filePath}">Download</a>
                                </g:else>
                            </div>
                        </section>
                    </div>
                </div>

            </div>
        </section>

    </div>
    <div class="col-md-5">
        <section class="panel panel-default">
            <header class="panel-heading">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="panel-title">Trending Topics</h3>
                    </div>
                </div>

            </header>
            <div class="panel-body">
                <g:if test="${trendingTopics==null || trendingTopics.size() == 0}">
                    <span>No Trending topics</span>
                </g:if>
                <g:else>
                    <g:render template="../topic/info" collection="${trendingTopics}" var="topic" />
                </g:else>

            </div>
        </section>
    </div>
</div>

<g:render template="link/edit" />
<g:render template="link/delete" />
<g:render template="document/edit" />


<script>

    $(function () {
        var resourceId = $('#resource-id-index-page').val()
        debugger;
        var successCallback = function(response){
            debugger;
            if(response['userRating'] >= 0){
                // Assign user rating and display total votes and average score till now
                var rating = response['userRating']
                $('#resource-page-no-of-reviews').html(' ('+response['totalVotes']+')')
                $("#rateYo").rateYo({
                    starWidth: "20px",
                    rating: rating,
                    fullStar: true,
                    onSet: function (rating, rateYoInstance) {
                        $.ajax({
                            url: "rating/saveResourceRating",
                            data: {'resourceId' : resourceId,'rating' : rating},
                            success: null
                        })
                    }
                });
            }else{
                $('#resource-page-no-of-reviews').html(' ('+response['totalVotes']+')')
                $("#rateYo").rateYo({
                    starWidth: "20px",
                    rating: response['averageScore'],
                    readOnly : true,
                    onSet: function (rating, rateYoInstance) {
                        $.ajax({
                            url: "rating/saveResourceRating",
                            data: {'resourceId' : resourceId,'rating' : rating},
                            success: null
                        })
                    }
                });
            }
        }
        // ajax request to get rating info for the resource under consideration
        $.ajax({
            url: "resource/getRatingInfo",
            data: {'resourceId' : resourceId},
            success: successCallback
        });
    });

</script>

</body>
</html>