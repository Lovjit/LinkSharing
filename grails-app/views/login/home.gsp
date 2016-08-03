<html lang="en"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../web-app/css/util.css" type="text/css">
    <title>Link Sharing</title>
</head>
<body>

<div class="container-fluid body-padding-min-height">

    <div class="col-md-7">

        <section class="panel panel-default">
            <header class="panel-heading">
                <h3 class="panel-title">Recent Shares</h3>
            </header>
            <div class="panel-body">
                <g:render template="../resource/show" collection="${recentResources}" var="resource"/>
            </div>
        </section>

        <section class="panel panel-default">
            <header class="panel-heading">
                <div class="row">
                    <div class="col-md-2">
                    <h3 class="panel-title" style="padding-top: 10px">Top Post</h3>
                    </div>
                    <!--Drop Down-->
                    <div class="col-md-9">
                   %{-- <div class="dropdown pull-right">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" >Today
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Last 1 Week</a></li>
                            <li><a href="#">Last 1 month</a></li>
                            <li><a href="#">Last 1 Year</a></li>
                        </ul>
                    </div>--}%
                    </div>
                </div>

            </header>
            <div class="panel-body">
                <g:render template="../resource/show" collection="${topResources}" var="resource"/>
            </div>
        </section>
    </div>

    <div class="col-md-5">

        <g:render template="../user/login" />
        <g:render template="../user/register" />

    </div>


</div>


















</body></html>