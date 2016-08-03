<section class="panel panel-default">
    <header class="panel-heading">
        <h3 class="panel-title">Login</h3>
    </header>
    <div class="panel-body">
        <form id="login-form" action="login/loginHandler">
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="email">Email\Username:</label>
                    <input type="text" class="form-control" id="email" placeholder="Enter email" name="nameEmail" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
                </div>
            </div>
            <p class="edit-box" id="login-error" style="color:red"></p>
            <div class="row">
                <div class="col-md-10">
                    <div class="col-md-5" style="padding-top: 13px">
                        <a href="user/resetPassword">Forgot Password</a>
                    </div>
                    <div class="col-md-7">
                        <button type="submit" class="btn btn-default btn-lg" style="float: right">Login</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>