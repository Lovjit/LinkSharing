<div class="col-md-5">
    <section class="panel panel-default">
        <header class="panel-heading">
            <div class="row">
                <div class="col-md-12">
                <h3 class="panel-title">Topic : "${topic.name}"</h3>
                </div>
            </div>

        </header>
        <div class="panel-body">

            <g:render template="info" />

        </div>
    </section>

    <section class="panel panel-default">
        <header class="panel-heading">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="panel-title">Users</h3>
                </div>
            </div>

        </header>
        <div class="panel-body">
            <g:render template="../user/info" collection="${users}" var = "user"/>
        </div>
    </section>
</div>

<div class="col-md-7">
    <section class="panel panel-default">
        <header class="panel-heading">
            <h3 class="panel-title">Post : "${topic.name}"</h3>
        </header>
        <div class="panel-body">
            <g:if test="${resources.size() == 0}">
                <span>No unread post</span>
            </g:if>
            <g:else>
                <g:render template="../resource/show" collection="${resources}" var="resource" />
            </g:else>
        </div>
    </section>
</div>




