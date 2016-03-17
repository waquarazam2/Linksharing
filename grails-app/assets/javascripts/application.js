// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$body = $("body");

$(document).on({
    ajaxStart: function() { $body.addClass("loading");    },
    ajaxStop: function() { $body.removeClass("loading"); }
});

function changeVisibility(visibility, id, panelName) {
    $.ajax({
        url: "/topic/update",
        data: {"id": id, "visibility": visibility},
        method: "POST",
        success: function (data) {
            var response = data.message;
            if (response == "Success") {
                if (panelName == "trendingTopics") {
                    loadTrendingTopics(function () {
                        $("#responseMessage").attr("class", "alert alert-success").fadeIn();
                        $("#responseMessage > .visibilityText").text(response);
                    });
                    loadSubscription();
                }
                else if (panelName == "createdTopics") {
                    loadCreatedTopics(function () {
                        $("#responseMessage").attr("class", "alert alert-success").fadeIn();
                        $("#responseMessage > .visibilityText").text(response);
                    })
                }
                else if (panelName == "subscription") {
                    loadSubscription(function () {
                        $("#responseMessageSubs").attr("class", "alert alert-success").fadeIn();
                        $("#responseMessageSubs > .visibilityText").text(response);
                    })
                    loadTrendingTopics();
                }
            }
            else {
                if (panelName == "subscription") {
                    loadSubscription(function () {
                        $("#responseMessageSubs").attr("class", "alert alert-success").fadeIn();
                        $("#responseMessageSubs > .visibilityText").text(response);
                    })
                }
                else {
                    $("#responseMessage").attr("class", "alert alert-danger").fadeIn();
                    $("#responseMessage > .visibilityText").text(response);
                }
            }

        },
        error: function (data) {
            if (panelName == "subscription") {
                loadSubscription(function () {
                    $("#responseMessageSubs").attr("class", "alert alert-success").show();
                    $("#responseMessageSubs > .visibilityText").text(response);
                })
            }
            else {
                $("#responseMessage").attr("class", "alert alert-danger").show();
                $("#responseMessage > .visibilityText").text(data.statusText);
            }
        }
    });
}
function changeSeriousness(seriousness, id) {
    var alertPanel = $("#alertPanel");
    $.ajax({
        url: "/subscription/update",
        data: {"id": id, "seriousness": seriousness},
        method: "POST",
        success: function (data) {
            var response = data.message
            if (response == "Success") {
                        $("#responseMessage").attr("class", "alert alert-success").fadeIn();
                        $("#responseMessage > .visibilityText").text(response);
                            $("#trendingTopic").attr("class","panel panel-success")

            }
            else {

                    $("#responseMessage").attr("class", "alert alert-danger").fadeIn();
                    $("#responseMessage > .visibilityText").text(response);

            }

        },
        error: function (data) {
                    $("#responseMessageSubs").attr("class", "alert alert-success").fadeIn();
                    $("#responseMessageSubs > .visibilityText").text(response);

        }
    });
}


function changePassword() {
    var password = $("#password").val();
    var confirm = $("#confirmPassword").val();
    $.ajax({
        url: "/user/updatePassword",
        data: {"password": password, "confirmPassword": confirm},
        method: "POST",
        success: function (data) {
            var response = data.message
            if (response == "Successfully Updated") {
                $("#responseMessagePassword").attr("class", "alert alert-success").fadeIn();
                $("#responseMessagePassword > .visibilityText").text(response);
                $("#password").val("")
                $("#confirmPassword").val("")
            }
            else {
                $("#responseMessagePassword").attr("class", "alert alert-danger").fadeIn();
                $("#responseMessagePassword > .visibilityText").text(response);
            }

        },
        error: function (data) {
            $("#responseMessagePassword").attr("class", "alert alert-danger").fadeIn();
            $("#responseMessagePassword > .visibilityText").text(data.statusText);
        }
    });
}
function deleteTopic(id) {
    $.ajax({
        url: "/topic/delete",
        data: {"id": id},
        method: "POST",
        success: function (data) {
            var response = data.message;
            if (response == "Deleted") {
                    location.reload();
            }
            else {
                $("#responseMessage").attr("class", "alert alert-danger").fadeIn();
                $("#responseMessage > .visibilityText").text(response);
            }

        },
        error: function (data) {
            $("#responseMessage").attr("class", "alert alert-danger").fadeIn();
            $("#responseMessage > .visibilityText").text(data.statusText);
        }
    });
}