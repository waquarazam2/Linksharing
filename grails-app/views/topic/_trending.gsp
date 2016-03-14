<div class="panel panel-default">
    <div class="panel-heading">Trending Topics
        <div class="pull-right">
            <a href="#" style="text-decoration:underline">View ALL</a></div>
    </div>
    <g:each in="${trendingTopics}" var="topicVO">

        <div class="panel-body">
            <g:if test="${topicVO.createdBy != session.user}">
                <div class="col-xs-2">
                    <img src="${g.createLink(controller: 'user', action: 'image', params:[id:topicVO?.createdBy?.id])}" height="65px" width="65px"/>
                    %{--<g:include controller="user" action="image" params="[id:topicVO?.createdBy?.id]"/>--}%
                </div>

                <div class="col-xs-10 pull-left">
                    <g:link value="topic" url="[controller: 'topic', action: 'index', params: [id: topicVO.id]]"
                            class="col-xs-8"
                            style="text-decoration:underline">${topicVO.topicName}

                    </g:link><br><br>

                    <div class="row">
                        <span class="col-xs-6 text-muted">@${topicVO.createdBy}</span>
                        <span class="col-xs-3" style="padding-left:1px"><small>Subscriptions</small></span>
                        <span class="col-xs-3" style="padding-left:1px"><small>Posts</small></span><br>
                    </div>

                    <div class="row">
                        <span class="col-xs-6" style="color:blue;"><small><ls:showSubscribe
                                topicId="${topicVO.id}"/></small>
                            %{--<div id="message"></div>--}%
                        </span>
                        <span class="col-xs-3" style="color:blue;padding-left:1px"><ls:subscriptionCount
                                topicId="${topicVO.id}"/></span>
                        <span class="col-xs-3" style="color:blue;padding-left:1px"><ls:resourceCount
                                topicId="${topicVO.id}"/></span>
                    </div>

                </div>
                <br><br><br><br>

            </g:if>
            <g:else>
                <
                <div class="col-xs-2">
                    <span class="glyphicon glyphicon-user" style="font-size:60px;border:solid black;"></span>
                </div>

                <div class="col-xs-10 pull-left">

                    <form class="form-inline">

                        <div class="col-xs-6"><input type="email" placeholder="Grails" size="10" class="form-control"
                                                     id="email"></div>
                        <div class="col-xs-3">
                            <button type="submit" class="btn btn-default active">Save</button>
                        </div>
                        <div class="col-xs-3">
                            <button type="submit" class="btn btn-default active">Cancel</button>
                        </div>
                    </form>

                </br>
                    <a href="#" class="col-xs-8" style="text-decoration:underline">{topic.name}</a></br></br>
                    <span class="col-xs-4 text-muted"> @Uday</span>
                    <span class="col-xs-4" style="padding-left:1px">Subscriptions</span>
                    <span class="col-xs-4" style="padding-left:1px">Topics</span><br>
                    <span class="col-xs-4" style="color:blue;"><a href="#"
                                                                  style="text-decoration:underline">Subscribe</a></span>
                    <span class="col-xs-4" style="color:blue;padding-left:1px">50</span>
                    <span class="col-xs-4" style="color:blue;padding-left:1px"> 50</span>

                </div>


                <div class="col-xs-4"><select class="pull-right form-control">
                    <option>Public</option>
                    <option>Private</option>
                    <option>Global</option>
                    <option>Mid</option>
                </select></div>

                <div class="col-xs-4"><select class="pull-right form-control">
                    <option>Public</option>
                    <option>Private</option>
                </select></div>

                <div class="col-xs-4">
                    <div class="glyphicon glyphicon-envelope"></div>
                    <div class="glyphicon glyphicon-edit"></div>
                    <div class="glyphicon glyphicon-trash"></div>

                </div>

            </g:else>
        </div>
    </g:each>
</div>
