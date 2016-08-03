<div class="row">

    <div class="col-md-12">

        <div class="col-md-3">
            <img class="img-responsive img-rounded" src="user/getImage?userId=${topic.createdBy.id}"/>
        </div>

        <div class="col-md-9 padding-left-initial">
            <div class="container-fluid">
                <div class="row">
                    <!--<a href="#"><span style="text-decoration: underline">Grails </span></a>-->
                    <div class="col-md-6" style="margin-left: inherit">
                        <a href="topic/index?topicId=${topic.id}" target="_blank">
                            <span style="text-decoration: underline" class="topic-name display-box" value="${topic.id}">${topic.name} </span></a>
                        <input type="text" placeholder="${topic.name}" data-topic-id="${topic.id}" class="form-control input-topic edit-box" value="${topic.name}">
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-sm btn-success save-topic edit-box">Save</button>
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-sm btn-default cancel-topic edit-box">Cancel</button>
                    </div>
                </div>
                <br>
                <div class="row">
                    <g:if test="${session['user'] != null && session['user'].id == topic.createdBy.id}">
                    <span class="col-md-4" style="color : grey;margin-left: inherit">
                        <a href="user/getProfilePage">@${topic.createdBy.userName}</a>  </span>
                    </span>
                    </g:if>
                    <g:else>
                        <span class="col-md-4" style="color : grey;margin-left: inherit">
                         <a href="user/getPublicProfilePage?userId=${topic.createdBy.id}">@${topic.createdBy.userName}</a>
                        </span>
                    </g:else>
                    <div class="col-md-8">

                        <span style="color : grey;margin-left: inherit;" class="col-md-9"> Subscription</span>

                        <span style="color : grey;margin-left: inherit;" class="col-md-3"> Post</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3" style=" margin-left: inherit">
                        <g:if test="${session['user'] != null && session['user'].id != topic.createdBy.id}">
                            <g:if test="${subscribedTopicIds.contains(topic.id)}">
                                <a href="#" class="unsubscribed" ><span style="text-decoration: underline;">Unsubscribe</span></a>
                            </g:if>
                            <g:else>
                                <a href="#" class="subscribed"><span style="text-decoration: underline;">Subscribe</span></a>
                            </g:else>
                        </g:if>
                    </div>
                    <div class="col-md-offset-4">
                        <span style="color : grey;margin-left: inherit;" class="">${topic.totalSubscription}</span>
                        <span style="color : grey;margin-left: inherit;" class="">${topic.resourceCount}</span>
                    </div>
                </div>
                <br>

                <div class="row">
                    <div class="col-md-6" style="margin-left: inherit;">
                            <g:if test="${ session['user'] != null && subscribedTopicIds != null && subscribedTopicIds.contains(topic.id)}">
                                <select class="btn btn-sm btn-default dropdown seriousness-list" data-style="btn-info">
                                <g:if test="${(topic.seriousness == com.ttnd.linksharing.Seriousness.SERIOUS)}">
                                    <option value="serious" selected>Serious</option>
                                </g:if>
                                <g:else>
                                    <option value="serious">Serious</option>
                                </g:else>

                                <g:if test="${(topic.seriousness == com.ttnd.linksharing.Seriousness.CASUAL)}">
                                    <option value="casual" selected>Casual</option>
                                </g:if>
                                <g:else>
                                    <option value="casual">Casual</option>
                                </g:else>

                                <g:if test="${(topic.seriousness == com.ttnd.linksharing.Seriousness.VERY_SERIOUS)}">
                                    <option value="VERY_SERIOUS" selected>Very Serious</option>
                                </g:if>
                                <g:else>
                                    <option value="VERY_SERIOUS">Very Serious</option>
                                </g:else>
                            </g:if>




                            </select>
                    </div>
                    <div class="col-md-5" style="margin-left: inherit">

                        <g:if test="${session['user'] && session['user'].id == topic.createdBy.id}">
                            <select class="btn btn-sm btn-default visibility-list" data-style="btn-info">

                                <g:if test="${(topic.visibility == com.ttnd.linksharing.Visibility.PUBLIC)}">
                                    <option value="public" selected>Public</option>
                                </g:if>
                                <g:else>
                                    <option value="public">Public</option>
                                </g:else>

                                <g:if test="${(topic.visibility == com.ttnd.linksharing.Visibility.PRIVATE)}">
                                    <option value="private" selected>Private</option>
                                </g:if>
                                <g:else>
                                    <option value="private">Private</option>
                                </g:else>

                            </select>
                        </g:if>

                    </div>
                    <div class="buffer-padding-top">
                        <i class="fa fa-envelope send-invite-envelope" data-toggle="modal" data-target="#send-invitation" aria-hidden="true"></i>&nbsp;
                        <g:if test="${session['user'] != null && topic.createdBy.id ==session['user'].id}">
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                        </g:if>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<hr/>
