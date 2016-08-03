<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script><style type="text/css"></style>
    <script src="https://use.fontawesome.com/bddf2542cd.js"></script>
    <asset:stylesheet src="dashboard.css"/>
    <title>Link Sharing%{--<g:layoutTitle default="Link Sharing"/>--}%</title>
    %{--<g:layoutHead/>--}%

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <form class="form-inline pull-right">
                            <div class="form-group buffer-margin-top">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-search" aria-hidden="true"></i>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Search...">
                                </div>
                            </div>
                        </form>
                    </li>
                    <g:if test="${session['user'] != null}">
                    <li><a href="#"><i class="fa fa-comments" aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-envelope" data-toggle="modal" data-target="#send-invitation" aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-paperclip" aria-hidden="true"></i></a></li>
                    <li><a href="#"><i class="fa fa-file-text" aria-hidden="true"></i></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="bla"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;Lovjit<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Users</a></li>
                            <li><a href="#">Topics</a></li>
                            <li><a href="#">Posts</a></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </li>
                    </g:if>
                </ul>
            </div>
    </div><!--/.nav-collapse -->
</nav>

<div class="container">

    %{--<g:if test="${flash.message} != null">
        <p>${flash.message} </p>
    </g:if>

    <g:if test="${flash.error} != null">
        <p>${flash.error} </p>
    </g:if>--}%


</div>

</body>
</html>

