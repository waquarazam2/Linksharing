
<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-body" >
            <div class="col-xs-4">
                <g:link url="[controller:'user',action:'index']" href="#" style="font-size:28px">Link Sharing</g:link>
            </div>

            <div class="col-xs-3">
                <g:form controller="resource" action="search" class="navbar-form">
                    <div class="input-group">
                        <g:textField type="text" class="form-control" placeholder="Search" name="q" id="srch-term"/>
                        <div class="input-group-btn">
                            <g:submitButton name="searchsubmit" class="btn btn-default" type="submit" value="Search"/>
                        </div>
                    </div>
                </g:form>
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
                            <li><g:link url="[controller: 'user',action: 'edit']" >Profiles</g:link></li>
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