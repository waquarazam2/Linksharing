<!DOCTYPE html>
%{--<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->--}%
%{--<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->--}%
%{--<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->--}%
%{--<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->--}%
%{--<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->--}%
<head>
    %{--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--}%
    %{--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">--}%
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <asset:stylesheet href="application.css"/>
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="bootstrap.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <style>
    .top {
        border: solid black;
        border-radius: 8px;
    }

    a {
        text-decoration: underline;
    }

    #search {
        border: solid black;
        border-radius: 8px;
    }

    .panel{
        border:none;
        padding:0;
    }

    .panel-default >.panel-heading,.panel-body{
        border:2px ridge #92b6bc;
        padding:10px;


    }

    .panel-default>.panel-heading{
        border-bottom: 0;
        background: #3c8dbc;
        font-size:20px;
        color:#fff;


    }

    .panel-default>.panel-body{
        background: #fffcfe;

    }
    select{
        color: #0e0e0e;
    }

    body{
        background: #ffffff;
    }


    </style>
    <g:layoutHead/>
</head>

<body>
<div class="container">
    <div class="row">
        <g:if test="${session.user}">
            <g:render template="/shared/home"/>
        </g:if>
        <g:else>
            <g:render template="/shared/login"/>
        </g:else>
    </div>
</div>


<g:layoutBody/>
</body>
</html>
