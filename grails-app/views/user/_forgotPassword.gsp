<div id="forgotPassword" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Forgot Password</h4>
            </div>

            <div class="modal-body">

                <g:form class="form-horizontal" name="forgotPassword" url="[controller:'user',action:'forgotPassword']">

                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Email:</label>

                        <div class="col-xs-8">
                            <input type="text" name="email" class="form-control" id="email" placeholder="Enter email">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>

                </g:form>
            </div>

        </div>

    </div>
</div>