<br><br><br><br>

<div class="panel panel-default">
    <div class="panel-heading">Resources</div>
    <g:each in="${resources}" var="resource">
        <div class="panel-body">

            <div class="col-xs-2">
                <img src="${g.createLink(controller: 'user', action: 'image', params:[id:resource.createdBy.id])}" height="65px" width="65px"/>
                %{--<g:include controller="user" action="image" params="[id:post[3].id]"/>--}%
            </div>

            <div class="col-xs-10">${resource.createdBy.firstName}<span class="text-muted"> @${resource.createdBy.userName} </span><span
                    class="pull-right"><a href="#">Grails</a></span>

                <p>${resource.description}</p>

                <div class="col-xs-2"><i class="fa fa-facebook-official"></i>
                    <i class="fa fa-tumblr"></i>
                    <i class="fa fa-google-plus"></i></div>

                <div class="col-xs-2">
                </div>

                <div class="col-xs-3"></div>

                <div class="col-xs-3">

                </div>

                <div class="col-xs-2"><small><g:link value="view" url="[controller:'resource',action:'show',params:[id:resource.id]]">View Post</g:link></small>
                </div>
            </div>

        </div>
    </g:each>
</div>