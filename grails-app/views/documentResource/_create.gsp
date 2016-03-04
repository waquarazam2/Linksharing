<div id="myModal3" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>
            <div class="modal-body">


                <form class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Document:</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="email" placeholder="Enter email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Description:</label>
                        <div class="col-xs-8">
                            <textarea name="comment" placeholder="Description" id="comment"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-2 "></div>
                        <label class="col-xs-2" for="sel1">Topic:</label>
                        <div class="col-xs-8"><select class="pull-right form-control" id="sel1">
                            <option>Public</option>
                            <option>Private</option>
                            <option>Global</option>
                            <option>Mid</option>
                        </select></div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-4"></div>
                        <div class="col-xs-4">
                            <input type="Login" class="form-control btn btn-default active" placeholder="Share" style="color:black;border:solid black;border-radius:7px"/>
                        </div>
                        <div class="col-xs-4">
                            <input type="Login" class="form-control btn btn-default active" placeholder="Cancel" style="color:black;border:solid black;border-radius:7px"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>


    </div>
</div>