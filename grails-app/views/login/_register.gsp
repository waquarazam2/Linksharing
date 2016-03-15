<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

<script>
    $(function () {
        $("#registrationForm").validate({
            rules: {
                firstName: "required",
                lastName: "required",
                email: {
//                        unique: true,
                    required: true,
                    email: true,
                    remote: "${createLink(controller: 'user',action: 'isEmailIdValid')}"
                },
                password: {
                    required: true,
                    minlength: 5
                },
                confirmPassword: {
                    required: true,
                    minlength: 5
                },
                userName: {
                    remote: "${createLink(controller: 'user',action: 'isUsernameValid')}"
                }
            },
// Specify the validation error messages
            messages: {
                firstname: "Please enter your first name",
                lastname: "Please enter your last name",
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                email: {
                    required:  "Please enter a valid email address",
                    remote: jQuery.validator.format("Email ID already exists")
                },
                userName: {
                    remote: jQuery.validator.format("Username already exists")
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
</script>

<div class="panel panel-default ">
    <div class="panel-heading bdy">Register</div>
    <div class="panel-body bdy" margin="10px 10px 10px 10px">
        <g:uploadForm url="[controller:'user',action:'register']" role="form" class="form-horizontal" id="registrationForm">
            <form role="form" class="form-horizontal">
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">First Name<sup>*</sup></label></div>
                    <div class="col-xs-7"><input type="text" class="form-control" name="firstName"></div>
                </div>

                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Last Name<sup>*</sup></label></div>
                    <div class="col-xs-7"><input type="text" class="form-control" name="lastName"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Email<sup>*</sup></label></div>
                    <div class="col-xs-7 "><input type="text" class="form-control" name="email"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Username Name<sup>*</sup></label></div>
                    <div class="col-xs-7 "><input type="text" class="form-control" name="userName"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Password<sup>*</sup></label></div>
                    <div class="col-xs-7"><input type="password" class="form-control" name="password"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Confirm Password<sup>*</sup></label></div>
                    <div class="col-xs-7 "><input type="password" class="form-control" name="confirmPassword"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Photo<sup>*</sup></label></div>
                    <div class="col-xs-4 "><input type="file" class="form-control" name="photo" id="photo"></div>
                </div>
                <div class="form-group">
                    <div class="col-xs-8"></div>
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-default active" style="width:100%" value="upload">Login</button>
                    </div>
                </div>
            </form>
        </g:uploadForm>
    </div>
</div>