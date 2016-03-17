<div id="myModal1" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" url="[controller:'user',action:'invite']">

                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Email:</label>

                        <div class="col-xs-8">
                            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-xs-4" for="topic">Topic:</label>

                        <div class="col-xs-8">
                            <g:select class="btn dropdown-toggle" data-toggle="dropdown" name="topic" id="doctopic" style="width:200px; " from="${subscribedTopics*.name}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-4"></div>

                        <div class="col-xs-4">
                            <input type="submit" class="form-control btn btn-default active" placeholder="Invite"
                                   >
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                    <div class="col-xs-8"></div>


                </g:form>
            </div>
        </div>
    </div>
</div>