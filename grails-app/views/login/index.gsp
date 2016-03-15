<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>
<div class="container">

<div class="col-xs-6">
    <ls:recentShares/>
   <ls:topPosts/>
</div>

<div class="col-xs-6">
    <g:render template="login"/>
    <g:render template="register"/>
</div>
</div>
</body>
</html>