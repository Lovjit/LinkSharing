<section class="panel panel-default">
    <header class="panel-heading">
        <h3 class="panel-title">Update Profile</h3>
    </header>
    <div class="panel-body">
        <form id="user-update-form" action="user/updateProfile" method="POST">
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-update-fn">FirstName*:</label>
                    <input type="text" class="form-control" id="user-update-fn" placeholder="Enter First Name" name="firstName"
                           value="${user.firstName}" >

                    </input>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-update-ln">Last Name*:</label>
                    <input type="text" class="form-control" id="user-update-ln" placeholder="Enter Last Name" name="lastName"
                           value="${user.lastName}" >

                </input>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-update-un">User Name*:</label>
                    <input type="text" class="form-control" id="user-update-un" placeholder="Enter User Name" name="username"
                           value="${user.userName}" >

                </input>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-update-fileupload">Profile Pic*:</label>
                    <input id="user-update-fileupload" class="form-control" type="file" name="profilePic">
                </div>
            </div>
            <div class="row">
                <div class="col-md-10">
                    <div class="col-md-7">
                        <button type="submit" class="btn btn-default btn-lg" style="float: right">Update</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>

<section class="panel panel-default">
    <header class="panel-heading">
        <h3 class="panel-title">Update Password</h3>
    </header>
    <div class="panel-body">
        <form id="user-password-update-form" action="user/updatePassword" method="POST">
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-update-password">Password:</label>
                    <input type="password" class="form-control" id="user-update-password" placeholder="Enter Password" name="password">
                </input>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-update-confirm-password">Confirm Password:</label>
                    <input type="password" class="form-control" id="user-update-confirm-password" placeholder="Re enter Password" name="confirmPassword">
                </input>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10">
                    <div class="col-md-7">
                        <button type="submit" class="btn btn-default btn-lg" style="float: right">Update Password</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>