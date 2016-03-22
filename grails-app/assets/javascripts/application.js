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

function changeVisibility(visibility, id, panel) {
    $.ajax({
        url: "/topic/update",
        data: {"id": id, "visibility": visibility},
        method: "POST",
        success: function (data) {
            var response = data.message;
            if (response == "Success") {
                $('#'+panel+id).html(data.message).fadeIn().delay(1000).fadeOut();
                }

            else {

            }

        },
        error: function (data) {
        }
    });
}
function changeSeriousness(seriousness, id,panel) {
    $.ajax({
        url: "/subscription/update",
        data: {"id": id, "seriousness": seriousness},
        method: "POST",
        success: function (data) {
            $('#'+panel+id).html(data.message).fadeIn().delay(1000).fadeOut();
        },
        error: function (data) {

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
        },
        error: function (data) {
        }
    });
}
function deleteTopic(id) {
    $.ajax({
        url: "/topic/delete",
        data: {"id": id},
        method: "POST",
        success: function (data) {

        },
        error: function (data) {

        }
    });
}

function loadUserTable() {
    $("#userTableSection").load("/user/loadUserTable")
}

$("#srch-term").keyup(function () {
    $("#searchResults").load("/resource/loadSearchResults", {"q": $("#srch-term").val()});
});
