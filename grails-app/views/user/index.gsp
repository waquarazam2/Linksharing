<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>
<div class="col-xs-1"></div>

<div class="col-xs-4">
    <ls:trendingTopics/>
</div>

<div class="col-xs-6">
    <g:render template="inbox"/>
    <g:render template="subscription"/>

</div>

<div class="col-xs-1"></div>
</body>
</html>