<div class="panel panel-default ">
    <div class="panel-heading bdy">Register</div>
    <div class="panel-body bdy" margin="10px 10px 10px 10px">
        <g:uploadForm url="[controller:'user',action:'register']" role="form" class="form-horizontal">
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
                    <div class="col-xs-3">
                        <button type="submit" class="btn btn-default active">Browse</button>
                    </div>
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