<div class="panel panel-default ">
    <div class="panel-heading bdy">Register</div>
    <div class="panel-body bdy" margin="10px 10px 10px 10px">
        <g:uploadForm url="[controller:'user',action:'updatePassword']" role="form" class="form-horizontal" id="registrationForm">
            <form role="form" class="form-horizontal">

                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Password<sup>*</sup></label></div>
                    <div class="col-xs-7"><input type="password" class="form-control" name="password"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-5"><label class="control-label">Confirm Password<sup>*</sup></label></div>
                    <div class="col-xs-7 "><input type="password" class="form-control" name="confirmPassword"></div>

                </div>
                <div class="form-group">
                    <div class="col-xs-8"></div>
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-default active" style="width:100%" >Login</button>
                    </div>
                </div>
            </form>
        </g:uploadForm>
    </div>
</div>