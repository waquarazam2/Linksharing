
<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-body" >
            <div class="col-xs-4">
                <a href="#" style="font-size:28px">Link Sharing</a>
            </div>
            <div class="col-xs-3"></div>

            <div class="col-xs-5">
                <g:form controller="resource" action="search" class="navbar-form">
                    <div class="input-group">
                        <g:textField type="text" class="form-control" placeholder="Search" name="q" id="srch-term"/>
                        <div class="input-group-btn">
                            <g:submitButton name="searchsubmit" class="btn btn-default" type="submit" value="Search"/>
                        </div>
                    </div>
                </g:form>
            </div>

        </div>
    </div>

</div>

<g:render template="/user/forgotPassword"/>