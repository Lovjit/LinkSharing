<div class="row">

    <div class="col-md-12">
        <div class="col-md-3">
            <!--  <img src="https://image.freepik.com/free-icon/user-silhouette_318-79814.png" style="border: solid"> -->
            %{--<i class="fa fa-user fa-5x" class="solid-border" aria-hidden="true"></i>--}%
            <img class="img-responsive img-rounded" src="user/getImage?userId=${user.id}"/>

        </div>
        <div class="col-md-9 padding-left-initial">
            <section class="panel">
                <div class="row">
                    <h3 style="margin-top: 1px;">${user.firstName} ${user.lastName}</h3>
                </div>
                <div class="row">
                    <span style="color : grey"> @${user.userName}</span>
                </div>
                <br>
                <div class="row">

                    <span style="color : grey;margin-left: inherit;" class="col-md-5"> Subscription</span>

                    <span style="color : grey;margin-left: inherit;" class="col-md-4"> Topic</span>

                </div>
                <div class="row">
                    <div class="col-md-4">
                        <span style="color : grey;margin-left: inherit;" class="col-md-offset-8"> ${user.subscriptions.size()}</span>
                    </div>
                    <div class="col-md-4">
                        <span style="color : grey;margin-left: inherit;" class="col-md-offset-7"> ${user.topics.size()}</span>
                    </div>
                </div>
            </section>

        </div>

    </div>

</div>
