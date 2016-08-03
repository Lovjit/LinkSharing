<section class="panel panel-default">
    <header class="panel-heading">
        <h3 class="panel-title">Register</h3>
    </header>
    <div class="panel-body">
        <form id="registerForm" action="login/registerHandler" method="POST" enctype="multipart/form-data">
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="first-name">First Name*:</label>
                    <input type="text" class="form-control" id="first-name" placeholder="Enter First Name" name="firstName" required/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="last-name">Last Name*:</label>
                    <input type="text" class="form-control" id="last-name" placeholder="Enter Last Name" name="lastName"required/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="email-register">Email:</label>
                    <input type="email" class="form-control" id="email-register" placeholder="Enter Email Id" name="email" required/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="user-name">User Name*:</label>
                    <input type="text" class="form-control" id="user-name" placeholder="Enter User Name" name="username" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="password-register">Password*:</label>
                    <input type="password" class="form-control" id="password-register" placeholder="Enter password" name="password" required/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <label for="confirmPassword">Confirm Password*:</label>
                    <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm password" name="confirmPassword" required/>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-10">
                    <label for="fileupload">Photo</label>
                    <input id="fileupload" class="form-control" type="file" name="profilePic" />
                </div>
            </div>




            <div class="row">

                <button type="submit" class="btn btn-default btn-lg center-block">Register</button>

            </div>
        </form>
    </div>
</section>