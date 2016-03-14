<div class="panel panel-default">
    <div class="panel-heading">Inbox</div>
    <g:each in="${readingItems}" var="item">
        <div class="panel-body">

            <div class="col-xs-2">
                <img src="${g.createLink(controller: 'user', action: 'image', params:[id:item[3]?.id])}" width="65px" height="65px"/>
                %{--<g:include controller="user" action="image" params="[id:item[3]?.id]"/>--}%
            </div>

            <div class="col-xs-10">${item[3].firstName}<span class="text-muted"> @${item[3].userName} </span><span
                    class="pull-right"><a href="#">Grails</a></span>

                <p>${item[1]}</p>

                <div class="col-xs-2"><i class="fa fa-facebook-official"></i>
                    <i class="fa fa-tumblr"></i>
                    <i class="fa fa-google-plus"></i></div>

                <div class="col-xs-2"><small><g:link controller="documentResource" action="downloadDocument" params="[id:item[4]]">download</g:link></small>
                </div>

                <div class="col-xs-3"><a href="#" style="text-decoration:underline;font-size:10px">View full
                site</a></div>

                <div class="col-xs-3">
                    <ls:markAsRead id="${item[0]}"/>
                </div>

                <div class="col-xs-2"><small><g:link value="view" url="[controller:'resource',action:'show',params:[id:item[4]]]">View Post</g:link></small>
                </div>
            </div>

        </div>
    </g:each>
</div>