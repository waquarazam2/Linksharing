<div class="panel panel-default ">
    <div class="panel-heading">Login</div>
    <div class="panel-body bdy" margin="10px 10px 10px 10px">
        <g:form  role="form" class="form-horizontal" name="login" url="/j_spring_security_check
">
            <div class="form-group">
                <div class="col-xs-4"><label class="control-label">Email/Username<sup>*</sup></label></div>
                <div class="col-xs-8"><input type="text" class="form-control" name="j_username"></div>
            </div>

            <div class="form-group">
                <div class="col-xs-4"><label class="control-label">Password<sup>*</sup></label></div>
                <div class="col-xs-8"><input type="password" class="form-control" name="j_password"></div>

            </div>
            <div class="form-group">
                <div class="col-xs-2"></div>
                <div class="col-xs-5">
                    <a href="#" data-toggle="modal" data-target="#forgotPassword">
                        Forgot Password
                    </a>
                </div>
                <div class="col-xs-5">
                    <button type="submit" class="btn btn-default active" style="width:100%">Login</button>
                </div>
            </div>
        </g:form>
    </div>
</div>
