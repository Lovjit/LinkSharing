<div class="row">

    <div class="col-md-12">

        <div class="col-md-3">
            <!-- <img src="https://image.freepik.com/free-icon/user-silhouette_318-79814.png" style="border: solid"> -->
           %{-- <i class="fa fa-user fa-5x" class="solid-border" aria-hidden="true"></i>--}%
            <img class="img-responsive img-rounded" src="user/getImage?userId=${resource.createdBy.id}"/>
        </div>

        <div class="col-md-9">
            <section class="panel">

                <p style="color : black">
                    <g:if test="${session['user'] != null && session['user'].id == resource.createdBy.id}">
                        <a href="user/getProfilePage"> ${resource.createdBy.firstName} ${resource.createdBy.lastName}</a>
                        <span style="color : grey">  <a href="user/getProfilePage">@${resource.createdBy.userName}</a>  ${resource.lastUpdated}</span>
                    </g:if>
                    <g:else>
                        <a href="user/getPublicProfilePage?userId=${resource.createdBy.id}"> ${resource.createdBy.firstName} ${resource.createdBy.lastName}</a>
                        &nbsp; &nbsp; &nbsp; &nbsp;
                        <span style="color : grey"> <a href="user/getPublicProfilePage?userId=${resource.createdBy.id}">@${resource.createdBy.userName}</a>
                            ${resource.lastUpdated}</span>
                    </g:else>

                    <span class="pull-right" style="color:blue">${resource.topic.name}</span></p>
                <p>
                    ${resource.description}
                </p>
                <i class="fa fa-facebook" aria-hidden="true" style="float:left"> &nbsp;&nbsp;</i>
                <i class="fa fa-google-plus" aria-hidden="true" style="float:left">&nbsp;&nbsp;</i>
                <i class="fa fa-twitter" aria-hidden="true" style="float:left;margin-right:70px"></i>
                <a href="resource/showPost?resourceId=${resource.resourceId}" target="_blank">View Post</a> &nbsp;&nbsp;&nbsp;
                <g:if test="${session['user']}">
                    <a href="#" id="mark-resource-as-read" data-resource-id="${resource.resourceId}">Mark read</a>&nbsp;&nbsp;&nbsp;
                </g:if>
                    <g:if test="${resource.isLinkResource == true}" >
                        <a href="${resource.url}" id="resource-link-view-site" target="_blank">View site</a>
                    </g:if>
                    <g:else>
                        <a href="resource/downloadResource?resourceId=${resource.resourceId}" id="document-download" data-path="${resource.filePath}">Download</a>
                    </g:else>


            </section>



        </div>

    </div>


</div>