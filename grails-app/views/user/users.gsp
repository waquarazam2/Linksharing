
<%@ page import="linksharing.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta content="main" name="layout">
    <title>Users</title>
</head>

<body>
<div class="container">
<div class="panel panel-default">
    <div class="panel-heading"><div class="row">
        <div class="col-xs-4">
            <strong>Users</strong></div>

        <div class="col-xs-4"></div>

        <div class="col-xs-2">
            <select class="form-control" id="activeSelect">
                <option>All Users</option>
                <option>Activated</option>
                <option>Deactivated</option>
            </select>
        </div>

        <div class="col-xs-2 pull-right">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search" name="srch-term" id="userSearch">

                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
            </div>
        </div>
    </div></div>

    <div class="panel-body">
        <div id="userTableSection">
        </div>

        <div class="pagination">
            <util:remotePaginate total="${User.count()}" controller="user" action="loadUserTable" max="20" update="userTableSection"/>
        </div>
    </div>
</div>
<script>
    loadUserTable();
    $("#activeSelect").change(function () {
        $("#userTableSection").load("/user/loadUserTable", {"sortBy": $("#activeSelect").val()});
    });
    $("#userSearch").keyup(function () {
        $("#userTableSection").load("/user/loadUserTable", {"q": $("#userSearch").val()});
    });
</script>
</div>
</body>
</html>