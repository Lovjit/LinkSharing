<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<base href="${request.getServletContext().getContextPath()}/">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script><style type="text/css"></style>
	<script src="https://use.fontawesome.com/bddf2542cd.js"></script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.js"></script>
	<asset:stylesheet src="dashboard.css"/>
	<asset:javascript src="notify.js" />
	<asset:javascript src="main.js" />
	<title><g:layoutTitle default="Link Sharing"/></title>
	<g:layoutHead/>

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
			<a class="navbar-brand" href="#">Link Sharing</a>
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
					<li><a href="#" data-toggle="modal" data-target="#create-topic" aria-hidden="true"><i class="fa fa-comments"
										></i></a></li>
					<li><a href="#" data-toggle="modal" class="send-invite-envelope"
						   data-target="#send-invitation" aria-hidden="true"><i class="fa fa-envelope"></i></a></li>
					<li><a href="#" aria-hidden="true"
						   data-toggle="modal" data-target="#share-link"><i class="fa fa-paperclip"></i></a></li>
					<li><a href="#" aria-hidden="true"
						   data-toggle="modal" data-target="#share-document"><i class="fa fa-file-text"></i></a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="bla"><i class="fa fa-user" aria-hidden="true"></i>
							&nbsp;${session['user'].userName}<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="user/getProfilePage">Profile</a></li>
							<li><a href="#">Users</a></li>
							<li><a href="#">Topics</a></li>
							<li><a href="#">Posts</a></li>
							<li><a href="user/logout">Logout</a></li>
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


<g:layoutBody/>

<g:render template="../topic/create" />
<g:render template="../topic/email" />
<g:render template="../resource/link/create" />
<g:render template="../resource/document/create" />

<script>

	/*$("#topic-save").click(function(){
		alert("Inside topic save");
		debugger;
		var topicName = $("#create-topic-name")
		var visibility = $("#create-topic-visibility")
		$.get( "/topic/save", { topicName: topicName, visibility: visibility } )
				.done(function(  ) {
					alert("Topic Saved")
					//alert( "Data Loaded: " + data );
				});
		$('#create-topic').modal('toggle');
		console.log("Out of topic save click function")

	});*/


</script>

</body>
</html>

