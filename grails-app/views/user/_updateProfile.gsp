
   <div class="panel panel-default ">
    <div class="panel-heading bdy">Profile</div>
    <div class="panel-body bdy" margin="10px 10px 10px 10px">
<g:uploadForm url="[controller:'user',action:'save']" role="form" class="form-horizontal" id="editForm">
    <form role="form" class="form-horizontal">
        <div class="form-group">
            <div class="col-xs-5"><label class="control-label">First Name<sup>*</sup></label></div>
            <div class="col-xs-7"><input type="text" class="form-control" name="firstName" placeholder="${user.firstName}"></div>
        </div>

        <div class="form-group">
            <div class="col-xs-5"><label class="control-label">Last Name<sup>*</sup></label></div>
            <div class="col-xs-7"><input type="text" class="form-control" name="lastName" placeholder="${user.lastName}"></div>

        </div>

        <div class="form-group">
            <div class="col-xs-5"><label class="control-label">Username Name<sup>*</sup></label></div>
            <div class="col-xs-7 "><input type="text" class="form-control" name="userName"  placeholder="${user.userName}"></div>

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
