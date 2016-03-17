<%@ page import="linksharing.Topic" %>
<span style="font-size:28px">${topicName}</span>
<span><a href="javascript:void(0);" class="editButton"><i
        class="glyphicon glyphicon-file col-xs-1"
        style="font-size:20px;"></i></a></span>
<div class="form-group" style="padding-bottom:25px" hidden="hidden">
    <div class="col-xs-5">
        <input type="text" placeholder="topic Name" class="form-control"
               id="editTopicName" value="${topicName}">
    </div>

    <div class="col-xs-2">
        <button type="button" class="btn btn-success buttonSave" id="saveTopicName"
                onclick="saveTopicName(${linksharing.Topic.findByName(topicName).id})">Save</button>
    </div>
    <div id="responseMessage"></div>

</div>
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
<script>
    function saveTopicName(topicId) {
        console.log("This was called");
        $("#editTopicName").hide();
        $("#saveTopicName").show();

        $.ajax({
            url: "/topic/updateTopicName",
            data: {"id":topicId,"topic":$("#editTopicName").val()},
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