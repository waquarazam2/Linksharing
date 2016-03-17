
<%@ page import="linksharing.Seriousness; linksharing.Subscription; linksharing.Topic" %>
<div class="panel panel-default" id="trendingTopic">
    <div class="panel-heading"><strong>Trending Topics</strong></div>

    <div class="panel-body">
        <div class="row">
            <div class="alert alert-success" hidden="hidden" id="responseMessage">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span class="visibilityText"><strong>Success!</strong> Indicates a successful or positive action.</span>
            </div>
        </div>
        <g:set var="iteration" value="${0}"/>
        <g:each in="${trendingTopics}" var="trendingTopic">
            <div>
                <div class="col-xs-2">
                    <img src="${g.createLink(controller: 'user', action: 'image', params:[id:trendingTopic.createdBy.id])}" style="width: 65px;height: 65px;"/>

                </div>

                <div class="col-xs-10">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="form-group" style="padding-bottom:25px" hidden="hidden"
                                 id="editTopic${iteration}">
                                <div class="col-xs-5">
                                    <input type="text" placeholder="Grails" class="form-control"
                                           id="topicEditBox${iteration}" value="${trendingTopic.topicName}">
                                </div>

                                <div class="col-xs-2">
                                    <button type="button" class="btn btn-success buttonSave"
                                            id="topicsaveButton${iteration}"
                                            onclick="saveTopicName(${trendingTopic.id}, this.id)">Save</button>
                                </div>

                                <div class="col-xs-2">
                                    <button type="button" class="btn btn-danger buttonCancel"
                                            id="topicCancelButton${iteration}">Cancel</button>
                                </div>
                            </div>
                            <g:link controller="topic" elementId="topicName${iteration}" action="show"
                                    id="${trendingTopic.id}"
                                    class="text-left">${trendingTopic.topicName}</g:link>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-3">
                            <small class="text-muted">@${trendingTopic.createdBy.userName}</small>
                        </div>

                        <div class="col-xs-1"></div>

                        <div class="col-xs-3"><small class="text-muted">Subscription</small></div>

                        <div class="col-xs-1"></div>

                        <div class="col-xs-3"><small class="text-muted">Posts</small></div>

                        <div class="col-xs-1"></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-3"><ls:showSubscribe topicId="${trendingTopic.id}"/></div>

                        <div class="col-xs-1"></div>

                        <div class="col-xs-3"><p class="text-info"><ls:subscriptionCount
                                topicId="${trendingTopic.id}"/></p>
                        </div>

                        <div class="col-xs-1"></div>

                        <div class="col-xs-3"><p class="text-info"><ls:resourceCount topicId="${trendingTopic.id}"/></p>
                        </div>

                        <div class="col-xs-1"></div>
                    </div>

                    <div style="padding-bottom:7.5px" class="row">
                        <div class="col-xs-4">
                            <g:if test="${session.user.admin || session.user.id == trendingTopic.createdBy.id}">
                                <g:select name="seriousness" from="${linksharing.Seriousness.values()}"
                                          class="form-control"
                                          onchange="changeSeriousness(this.value,${trendingTopic.id},'trendingTopics')"
                                          id="Seriousness"/>
                            </g:if>
                        </div>

                        <div class="col-xs-4">
                            <g:select name="visibility" from="${linksharing.Visibility.values()}"
                                      onchange="changeVisibility(this.value,${trendingTopic.id},'trendingTopics')"
                                      class="form-control"
                                      id="Visibility"/>
                        </div>

                        <div><a href="javascript:void(0);" data-toggle="modal" data-target="#sendinvite"><i
                                class="glyphicon glyphicon-envelope col-xs-1"
                                style="font-size:20px;"></i></a></div>
                        <g:if test="${session.user.admin || session.user.id == trendingTopic.createdBy.id}">

                            <div><a href="javascript:void(0);" class="editButton" id="editTopicIcon${iteration++}"><i
                                    class="glyphicon glyphicon-file col-xs-1"
                                    style="font-size:20px;"></i></a></div>    <div><a href="javascript:void(0);" class="editButton" id="editTopicIcon${iteration++}"><i
                                    class="glyphicon glyphicon-file col-xs-1"
                                    style="font-size:20px;"></i></a></div>

                            <div><g:link url="[controller: 'topic',action: 'delete',params: [id:trendingTopic.id]]"><i
                                    class="glyphicon glyphicon-trash col-xs-1"
                                    style="font-size:20px;"></i></g:link></div>
                        </g:if>
                    </div>
                </div>
            </div>
        </g:each>
    </div>
</div>
<script>
    $(".editButton").click(function () {
        var currentElementId = (this.id).substr(13);
        console.log(currentElementId);
        $("#editTopic" + currentElementId).show();
        $("#topicName" + currentElementId).hide();
    });
    $(".buttonCancel").click(function () {
        var currentElementId = (this.id).substr(17);
        console.log(currentElementId);
        $("#editTopic" + currentElementId).hide();
        $("#topicName" + currentElementId).show();
    });
    function saveTopicName(topicId, buttonId) {
        console.log("This was called");
        var buttonClicked = $(".buttonSave > " + buttonId);
        var currentElementId = buttonId.substr(15);
        $("#editTopic" + currentElementId).hide();
        $("#topicName" + currentElementId).show();

        $.ajax({
            url: "/topic/updateTopicName",
            data: {"id":topicId,"topic":$("#topicEditBox"+currentElementId).val()},
            method: "POST",
            success: function (data) {
                var response = data.message;
                if (response == "Topic Updated") {
                    loadTrendingTopics(function () {
                        $("#responseMessage").attr("class", "alert alert-success").show();
                        $("#responseMessage > .visibilityText").text(response);
                        loadSubscription();
                    })
                }
                else {
                    $("#responseMessage").attr("class", "alert alert-danger").show();
                    $("#responseMessage > .visibilityText").text(response);
                }
            },
            error: function (data) {
                $("#responseMessage").attr("class", "alert alert-danger").show();
                $("#responseMessage > .visibilityText").text(data.statusText);
            }
        });
    }
</script>