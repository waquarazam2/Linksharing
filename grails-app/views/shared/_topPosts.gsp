<div class="panel panel-default">
    <div class="panel-heading">Top posts</div>
    <g:each in="${topPosts}" var="post">
        <div class="panel-body">

            <div class="col-xs-2">
                <img src="${g.createLink(controller: 'user', action: 'image', params:[id:post[3].id])}" height="65px" width="65px"/>
                %{--<g:include controller="user" action="image" params="[id:post[3].id]"/>--}%
            </div>

            <div class="col-xs-10">${post[3].firstName}<span class="text-muted"> @${post[3].userName} </span><span
                    class="pull-right"><a href="#">Grails</a></span>

                <p>${post[4]}</p>

                <div class="col-xs-2"><i class="fa fa-facebook-official"></i>
                    <i class="fa fa-tumblr"></i>
                    <i class="fa fa-google-plus"></i></div>

                <div class="col-xs-2">
                </div>

                <div class="col-xs-3"></div>

                <div class="col-xs-3">

                </div>

                <div class="col-xs-2"><small><g:link value="view" url="[controller:'resource',action:'show',params:[id:post[1]]]">View Post</g:link></small>
                </div>
            </div>

        </div>
    </g:each>
</div>