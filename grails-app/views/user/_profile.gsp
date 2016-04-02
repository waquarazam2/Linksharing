<div class="panel panel-default">

    <div class="panel-body">
        <div class="col-xs-2">
            %{--<g:include controller="user" action="image" params="[id:session.user.id]"/>--}%
            <img src="${g.createLink(controller: 'user', action: 'image', params: [id: session.user.id])}" width="65px"
                 height="65px"/>
        </div>

        <div class="col-xs-10 pull-left">
            <br><br>

            <div class="row">
                <span class="col-xs-6 text-muted">@${session.user.name}</span>
                <span class="col-xs-3" style="padding-left:1px"><small>Subscriptions</small></span>
                <span class="col-xs-3" style="padding-left:1px"><small>Posts</small></span><br>
            </div>

            <div class="row">
                <span class="col-xs-3" style="color:blue;padding-left:1px"><ls:subscriptionCount
                        user="${session.user}"/></span>



                <span class="col-xs-3" style="color:blue;padding-left:1px"><ls:postCount user="${session.user}"/></span>
            </div>

        </div>
        <br><br><br><br>

    </div>

</div>
