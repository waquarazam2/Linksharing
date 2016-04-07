<div id="myModal4" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create Topic</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" name="topicCreate" url="[controller: 'topic', action: 'save']">

                    <div class="form-group">
                        <label class="control-label col-xs-4" for="email">Name:</label>

                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="email" name="topicName" placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-2 "></div>
                        <label class="col-xs-2" for="sel1">Visiblity:</label>

                        <div class="col-xs-8">
                            <select class="pull-right form-control" id="sel1" name="visibility">
                                <option>Public</option>
                                <option>Private</option>
                                <option>Global</option>
                                <option>Mid</option>
                            </select></div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-4"></div>

                        <div class="col-xs-4">
                            <input type="submit" class="form-control btn btn-default active" placeholder="Save"
                                   style="color:black;border:solid black;border-radius:7px"/>

                        </div>

                        <div class="col-xs-4">
                            <input type="Login" class="form-control btn btn-default active" id="submit"
                                   placeholder="Cancel" style="color:black;border:solid black;border-radius:7px">
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
