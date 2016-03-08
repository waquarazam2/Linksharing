<div id="myModal2" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Link</h4>
            </div>
            <div class="modal-body">

                <g:form class="form-horizontal" url="[controller:'linkResource',action:'saveLink']">

                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Link:</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="email" placeholder="Enter URL" name="link">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Description:</label>
                        <div class="col-xs-8">
                            <textarea name="description" placeholder="Description" id="comment"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-2 "></div>
                        <label class="col-xs-2">Topic:</label>
                        <div class="col-xs-8">

                            <g:select class="btn dropdown-toggle" data-toggle="dropdown" name="topic" id="doctopic" style="width:200px; " from="${subscribedTopics*.name}"/>

                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-xs-4"></div>
                        <div class="col-xs-4">
                            <input type="submit" class="form-control btn btn-default active" placeholder="Share" style="color:black;border:solid black;border-radius:7px"/>
                        </div>
                        <div class="col-xs-4">
                            <input type="Login" class="form-control btn btn-default active" placeholder="Cancel" style="color:black;border:solid black;border-radius:7px"/>
                        </div>
                    </div>

                </g:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>


    </div>
</div>