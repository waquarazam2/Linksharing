<%@ page import="linksharing.*" %>
<div class="panel panel-default" >
    <div class="panel-heading">Subscriptions
        <div class="pull-right">
            <a href="#" style="text-decoration:underline">View ALL</a></div></div>


    <g:each in="${subscribedTopics}" var="subscription">

        <div class="panel-body">

        <div class="col-xs-2">
        <img src="${g.createLink(controller: 'user', action: 'image', params:[id:subscription.createdBy.id])}" style="width: 65px;height: 65px;"/>

    %{--<g:include controller="user" action="userImage" params='[username: "${subscription[2]}"]'/>--}%
        </div>

        <div class="col-xs-10 pull-left"><a href="#" class="col-xs-10"
        style="text-decoration:underline">${subscription.name}></a></br></br>
        <span class="col-xs-6 text-muted">@${subscription.createdBy}</span>
        <span class="col-xs-4" style="padding-left:1px">Subscriptions</span>
        <span class="col-xs-2" style="padding-left:1px">Topics</span><br>

        <span class="col-xs-6" style="color:blue;"><small><ls:showSubscribe topicId="${subscription.id}"/> </small>
        </span>
        <span class="col-xs-4" style="color:blue;padding-left:1px"><ls:subscriptionCount topicId="${subscription.id}"/> </span>
        <span class="col-xs-2" style="color:blue;padding-left:1px"><ls:resourceCount topicId="${subscription.id}" /></span>
        </div>


        <div class="col-xs-4"><select class="pull-right form-control">
            <option>Public</option>
            <option>Private</option>
            <option>Global</option>
            <option>Mid</option>
        </select></div>

        <div class="col-xs-4"><select class="pull-right form-control" id="sel1">
            <option>Public</option>
            <option>Private</option>
            <option>Global</option>
            <option>Mid</option>
        </select></div>

        <div class="col-xs-4">

            <div class="glyphicon glyphicon-envelope"></div>

            <div class="glyphicon glyphicon-edit"></div>

            <div class="glyphicon glyphicon-trash"></div>

        </div>
        </br></br></br></br></br></br>

        <hr>
        </div>

    </g:each>
</div>