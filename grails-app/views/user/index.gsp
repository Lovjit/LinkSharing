<html lang="en"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
   %{-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script><style type="text/css"></style>
    <script src="https://use.fontawesome.com/bddf2542cd.js"></script>--}%
    %{--<link rel="stylesheet" href="dashboard.css" type="text/css">--}%
    <asset:stylesheet src="dashboard.css"/>
    <title>Link Sharing</title>
</head>

<body>

<div class="container body-padding-min-height">

    <div class="col-md-5">

        <section class="panel panel-default">
            <div class="panel-body">
                <g:render template="./info"/>
            </div>
        </section>

        <section class="panel panel-default">
            <header class="panel-heading">
                <div class="row">
                    <div class="col-md-3">
                        <h3 class="panel-title">Subscription</h3>
                    </div>
                    <div class="col-md-9">
                        <div class="dropdown pull-right">
                        </div>
                    </div>
                </div>

            </header>
            <div class="panel-body">
                <g:if test="${subscribedTopics == null || subscribedTopics.size() ==0}">
                    <span>No Subscribed Topics</span>
                </g:if>
                <g:else>
                    <g:render template="../topic/info" collection="${subscribedTopics}" var="topic" />
                </g:else>
               %{-- <div class="paginateButtons">
                    <g:paginate controller="user" action="test" max="5" offset="5" total="${subscribedTopics.size()}" />
                </div>--}%
            </div>
        </section>

        <!-- -->

        <section class="panel panel-default">
            <header class="panel-heading">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="panel-title">Trending Topics</h3>
                    </div>
                </div>

            </header>
            <div class="panel-body">
                <g:if test="${trendingTopics == null || trendingTopics.size() ==0}">
                    <span>No Trending Topics</span>
                </g:if>
                <g:else>
                    <g:render template="../topic/info" collection="${trendingTopics}" var="topic" />
                </g:else>
            </div>
        </section>

        <!-- -->


    </div>

    <div class="col-md-7">

        <section class="panel panel-default">
            <header class="panel-heading">
                <h3 class="panel-title">Inbox</h3>
            </header>
            <div class="panel-body">
                <!--<p>Body</p> -->

                <g:if test="${subscribedTopics == null || subscribedTopics.size() ==0}">
                    <span>No Unread Post</span>
                </g:if>
                <g:else>
                    <g:render template="../resource/show" collection="${resources}" var="resource" />
                </g:else>

            </div>





        </section>






    </div>


</div>

</body>

</html>