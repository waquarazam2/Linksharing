<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>
        Profile
    </title>
</head>

<body>
<div class="container">

    <div class="col-xs-6">
        <g:render template="profile"/>
    </div>

    <div class="col-xs-6">
        <g:render template="updateProfile"/>
        <g:render template="changePassword"/>
    </div>
</div>
</body>
</html>