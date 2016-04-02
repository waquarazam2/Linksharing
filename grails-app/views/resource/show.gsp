<%@ page import="linksharing.Topic" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta content="main" name="layout">
    <title>Resource Show</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div>
                        <div class="col-xs-2">
                            <img src="${g.createLink(controller: 'user', action: 'image', params: [id: resource?.createdBy?.id])}"
                                 height="65px" width="65px"/>
                            %{--<g:include controller="user" action="image" params="[id:resource?.createdBy?.id]"/>--}%
                        </div>

                        <div class="col-xs-10">
                            <div class="row" style="padding-bottom:5px">
                                <div class="col-xs-6">
                                    <span class="h5">${resource?.createdBy?.name}</span>
                                </div>

                                <div class="col-xs-3">
                                </div>

                                <div class="col-xs-3">
                                    <a href="#" class="text-left">${resource?.topic?.name}</a>
                                </div>
                            </div>

                            <div class="row" style="padding-bottom:10px">
                                <div class="col-xs-4">
                                    <small class="text-muted">@${resource?.createdBy?.userName}</small>
                                </div>

                                <div class="col-xs-2"></div>

                                <div class="col-xs-6 text-muted">${resource?.dateCreated}</div>
                            </div>

                            <div class="row" style="padding-bottom:15px">
                                <div class="col-xs-4"></div>
                                <g:if test="${session.user}">
                                    <g:form name="ratingForm" controller="resourceRating" action="saveRating"
                                            class="form-horizontal">
                                        <div class="col-xs-4">
                                            <g:select name="rating" from="${1..5}"
                                                      value="${session?.user?.getScore(resource)}"
                                                      class="form-control"/>
                                        </div>
                                        <g:hiddenField name="id" value="${resource?.id}"/>
                                        <div class="col-xs-4">
                                            <g:submitButton name="vote" type="submit" value="Vote"
                                                            class="btn btn-success"/>
                                        </div>
                                    </g:form>
                                </g:if>
                            </div>

                            <p style="padding-bottom:5px">${resource?.description}</p>
                        </div>


                        <div class="row">
                            <div class="col-xs-1 fa fa-facebook-official" style="font-size:20px"></div>

                            <div class="col-xs-1 fa fa-twitter" style="font-size:20px"></div>

                            <div class="col-xs-1 fa fa-google-plus" style="font-size:20px"></div>

                            <div class="col-xs-2"></div>
                            <g:if test="${session.user}">

                                <div class="col-xs-1"><ls:canDeleteResouce resource="${resource}"/></div>

                                <div class="col-xs-1">
                                    <a href="#" data-toggle="modal" data-target="#forgotPassword">Edit
                                    </a>
                                </div>
                                <g:if test="${resource?.which()?.equals("document")}">
                                    <div class="col-xs-2"><a href="#">Download</a></div>
                                </g:if>
                                <g:elseif test="${resource?.which()?.equals("link")}">
                                    <div class="col-xs-3"><a href="${resource?.url}" target="_blank">View Full Site</a>
                                    </div>
                                </g:elseif>
                            </g:if>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="col-xs-6">
            <ls:trendingTopics/>
        </div>
    </div>
</div>
<g:render template="edit"/>
</body>
</html>