<div style="font-size:28px">${topicName}</div>
<br>

<div style="font-size:28px">Subscribed Users</div>

<g:each in="${users}" var="user">
    <div class="panel panel-default">
        <div class="panel-heading">${user.firstName} ${user.lastName}
        </div>

        <div class="panel-body">

            <div class="col-xs-4">
                <img src="${g.createLink(controller: 'user', action: 'image', params: [id: user.id])}" width="65px"
                     height="65px"/>

            </div>

            <div class="col-xs-8">${user.firstName}<span class="text-muted"><br>@${user.userName}<br>
                <span class="col-xs-6" style="padding-left:1px">Subscriptions</span><span class="col-xs-6"
                                                                                          style="padding-left:1px">Posts</span><br>
            </span>
                <span class="col-xs-6" style="color:blue;padding-left:1px"><ls:subscriptionCount
                        user="user"/></span><span class="col-xs-6"
                                                  style="color:blue;padding-left:1px"><ls:postCount
                        user="${user}"/></span>

            </div>
        </div>
    </div>
</g:each>