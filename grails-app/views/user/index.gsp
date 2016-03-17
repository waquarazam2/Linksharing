<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
</head>

<body>
<div class="container">

<div class="col-xs-6">
    <g:render template="profile"/>
    <g:render template="subscription"/>

</div>

<div class="col-xs-6">
    <g:render template="inbox"/>
    <ls:trendingTopics/>

</div>
</div>

</body>
</html>