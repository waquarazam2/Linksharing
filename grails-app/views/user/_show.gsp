<div style="font-size:28px">${topicName}</div>
<br>

<div style="font-size:28px">Subscribed Users</div>

<g:each in="${users}" var="user">
    <div class="panel panel-default">
        <div class="panel-heading">${user.firstName} ${user.lastName}
        </div>

        <div class="panel-body">

            <div class="col-xs-4">
                <span class="glyphicon glyphicon-user" style="font-size:60px;border:solid black;"></span>
            </div>

            <div class="col-xs-8">${user.firstName}<span class="text-muted"><br>@${user.userName}<br>
                <span class="col-xs-6" style="padding-left:1px">Subscriptions</span><span class="col-xs-6"
                                                                                          style="padding-left:1px">Topics</span><br>
            </span>
                <span class="col-xs-6" style="color:blue;padding-left:1px">50</span><span class="col-xs-6"
                                                                                          style="color:blue;padding-left:1px">50</span>

            </div>
        </div>
    </div>
</g:each>