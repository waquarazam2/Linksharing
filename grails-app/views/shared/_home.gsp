
<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-body" >
            <div class="col-xs-4">
                <a href="#" style="font-size:28px">Link Sharing</a>
            </div>

            <div class="col-xs-3">
                <div class="panel border" id="search">
                    <div class="glyphicon glyphicon-search" ></div>

                    <div class="badge pull-right">
                        <div class="glyphicon glyphicon-remove"></div>
                    </div>
                    <input type="text" placeholder="search" style="border:none">
                </div>
            </div>

            <div class="col-xs-5">
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal4"
                        style="border:none;background:none;color:#0b746c">
                    <div class="glyphicon glyphicon-comment"></div>
                </button>
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal1"
                        style="border:none;background:none;color:#1b137a">
                    <div class="glyphicon glyphicon-envelope"></div>
                </button>
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal2"
                        style="border:none;background:none;color:#1a0b19">
                    <div class="glyphicon glyphicon-paperclip"></div>
                </button>
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal3"
                        style="border:none;background:none;color:#50c040">
                    <div class="glyphicon glyphicon-edit"></div>
                </button>

                <div class=" pull-right">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle " type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                style="border:none">
                            <div class="glyphicon glyphicon-user"></div>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Profiles</a></li>
                            <g:if test="${session.user.admin}">
                            <li><a href="#">Users</a></li>
                            <li><a href="#">Topic</a></li>
                            <li><a href="#">Post</a></li>
                            </g:if>
                           <li> <g:link controller="login" action="logout">Logout</g:link></li>

                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
<g:render template="/documentResource/create"/>
<g:render template="/linkResource/create"/>
<g:render template="/topic/create"/>
<g:render template="/topic/email"/>