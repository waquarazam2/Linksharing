<%@ page import="linksharing.Subscription" %>
<div class="panel panel-default">
<div class="panel-heading clearfix">
    <h5 class="panel-title pull-left"
        style="font-size:15px;padding-top:7.5px;"><strong>Subscription</strong></h5>

</div>

<div class="panel-body">
    <div class="alert alert-success" hidden="hidden" id="responseMessageSubs">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <span class="visibilityText"><strong>Success!</strong> Indicates a successful or positive action.</span>
    </div>
    <g:set var="iteration" value="${0}"/>
    <g:each in="${subscribedTopics}" var="topic">
        <div>
            <div class="col-xs-2">
                    <img src="${g.createLink(controller: 'user', action: 'image', params:[id:topic.createdBy.id])}" style="width: 65px;height: 65px;"/>

            </div>

            <div class="col-xs-10">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group" style="padding-bottom:25px" hidden="hidden"
                             id="editTopicSubs${iteration}">
                            <div class="col-xs-5">
                                <input type="text" placeholder="Grails" class="form-control"
                                       id="topicEditBoxSubs${iteration}" value="${topic.name}">
                            </div>

                            <div class="col-xs-2">
                                <button type="button" class="btn btn-success buttonSave"
                                        id="topicsaveButtonSubs${iteration}"
                                        onclick="saveTopicName(${topic.id}, this.id)">Save</button>
                            </div>

                            <div class="col-xs-2">
                                <button type="button" class="btn btn-danger buttonCancel"
                                        id="topicCancelButtonSubs${iteration}">Cancel</button>
                            </div>
                        </div>
                        <g:link elementId="topicNameSubs${iteration}" controller="topic" action="show" id="${topic.id}"
                                class="text-left">${topic.name}</g:link>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-3">
                        <small class="text-muted">@${topic.createdBy.userName}</small>
                    </div>

                    <div class="col-xs-1"></div>

                    <div class="col-xs-3"><small class="text-muted">Subscription</small></div>

                    <div class="col-xs-1"></div>

                    <div class="col-xs-3"><small class="text-muted">Posts</small></div>

                    <div class="col-xs-1"></div>
                </div>

                <div class="row">
                    <div class="col-xs-3"><ls:showSubscribe topicId="${topic.id}"/> </div>

                    <div class="col-xs-1"></div>

                    <div class="col-xs-3"><p class="text-info"><ls:subscriptionCount
                            topicId="${topic.id}"/></p></div>

                    <div class="col-xs-1"></div>

                    <div class="col-xs-3"><p class="text-info"><ls:resourceCount
                            topicId="${topic.id}"/></p></div>

                    <div class="col-xs-1"></div>
                </div>

                <div style="visibility: ${visibility}; padding-bottom:7.5px" class="row">
                    <div class="col-xs-4">
                        <select class="form-control" id="SeriousnessSubs" onchange="changeSeriousness(this.value,${topic.id},'subscription')">
                            <option>Serious</option>
                            <option>Very Serious</option>
                            <option>Casual</option>
                        </select>
                    </div>

                    <div class="col-xs-4">
                        <select class="form-control" id="VisibilitySubs" onchange="changeVisibility(this.value,${topic.id},'subscription')">
                            <option>Public</option>
                            <option>Private</option>
                        </select>
                    </div>

                    <div><a href="javascript:void(0);" data-toggle="modal" data-target="#sendinvite"><i
                            class="glyphicon glyphicon-envelope col-xs-1"
                            style="font-size:20px;"></i></a></div>

                    <div><a href="javascript:void(0);" class="editButton" id="editTopicIconSubs${iteration++}"><i
                            class="glyphicon glyphicon-file col-xs-1"
                            style="font-size:20px;"></i></a></div>

                    <div><g:link url="[controller: 'subscription',action: 'delete',params: [id: Subscription.findByTopic(topic).id]]"><i
                            class="glyphicon glyphicon-trash col-xs-1"
                            style="font-size:20px;"></i></g:link></div>
                </div>
            </div>
        </div>
    </g:each>
</div>
</div>
<script>
    $(".editButton").click(function () {
        var currentElementId = (this.id).substr(17);
        console.log(currentElementId);
        $("#editTopicSubs" + currentElementId).show();
        $("#topicNameSubs" + currentElementId).hide();
    });
    $(".buttonCancel").click(function () {
        var currentElementId = (this.id).substr(21);
        console.log(currentElementId);
        $("#editTopicSubs" + currentElementId).hide();
        $("#topicNameSubs" + currentElementId).show();
    });
    function saveTopicName(topicId, buttonId) {
        var buttonClicked = $(".buttonSave > " + buttonId);
        var currentElementId = buttonId.substr(19);
        $("#editTopicSubs" + currentElementId).hide();
        $("#topicNameSubs" + currentElementId).show();
        $.ajax({
            url: "/topic/updateTopicName",
            data: {"id":topicId,"topic":$("#topicEditBoxSubs"+currentElementId).val()},
            method: "POST",
            success: function (data) {
                var response = data.message;
                if (response == "Topic Updated") {
                    loadSubscription(function () {
                        $("#responseMessageSubs").attr("class", "alert alert-success").show();
                        $("#responseMessageSubs > .visibilityText").text(response);
                    })
                    loadTrendingTopics();
                }
                else {
                    $("#responseMessageSubs").attr("class", "alert alert-danger").show();
                    $("#responseMessageSubs > .visibilityText").text(response);
                }
            },
            error: function (data) {
                $("#responseMessageSubs").attr("class", "alert alert-danger").show();
                $("#responseMessageSubs > .visibilityText").text(data.statusText);
            }
        });
    }
</script>