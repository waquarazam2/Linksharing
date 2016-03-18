<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
    <script type="text/javascript">
        var error = "${flash.error}";
        var success = "${flash.message}";
        if(error !== "") {
            alert(error)
        }
        if(success !== ""){
            alert(success)
        }
    </script>
</head>

<body>
<div class="container">

<div class="col-xs-6">
    <g:render template="profile"/>
    <ls:trendingTopics/>

</div>

<div class="col-xs-6">
    <g:render template="inbox"/>
    <g:render template="subscription"/>


</div>
</div>

</body>
</html>