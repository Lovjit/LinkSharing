<html lang="en"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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
                        <h3 class="panel-title">Topics</h3>
                    </div>

                    <div class="col-md-9">
                        <div class="dropdown pull-right">
                        </div>
                    </div>
                </div>

            </header>

            <div class="panel-body">
                <g:if test="${userTopics == null || userTopics.size() == 0}">
                    <span>No topics created Yet!!</span>
                </g:if>
                <g:else>
                    <g:render template="../topic/info" collection="${userTopics}" var="topic"/>
                </g:else>
            </div>
        </section>


        <section class="panel panel-default">
            <header class="panel-heading">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="panel-title">Subscriptions</h3>
                    </div>
                </div>

            </header>

            <div class="panel-body">
                <g:if test="${subscribedTopics == null || subscribedTopics.size() == 0}">
                    <span>No Subscription found!!</span>
                </g:if>
                <g:else>
                    <g:render template="../topic/info" collection="${subscribedTopics}" var="topic"/>
                </g:else>
            </div>
        </section>

    </div>

    <div class="col-md-7">
        <section class="panel panel-default">
            <header class="panel-heading">
                <h3 class="panel-title">Post</h3>
            </header>
            <div class="panel-body">
                <g:render template="../resource/show" collection="${resources}" var="resource"/>
            </div>
        </section>
    </div>

</div>

</body>

</html>