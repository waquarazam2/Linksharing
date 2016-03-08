<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>
<div class="col-xs-1"></div>
<div class="col-xs-6">
    <g:render template="/shared/recentShares"/>
    <g:render template="/shared/topPosts"/>
</div>

<div class="col-xs-4">
    <g:render template="login"/>
    <g:render template="register"/>
</div>
<div class="col-xs-1"></div>
</body>
</html>