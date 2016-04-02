<%@ page import="linksharing.Resource" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta content="main" name="layout">
    <title>Search</title>
</head>

<body>
<div class="container">
        <div class="col-xs-6">
            <ls:trendingTopics/>
        </div>


        <div class="col-xs-6">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-4">
                            Search Results</div>

                    </div>
                </div>
                <div id="searchResults">
                    <g:render template="searchResults"/>
                </div>
                <div class="pagination">
                    <util:remotePaginate total="${steps}" controller="resource" action="loadSearchResults" max="2" update="searchResults" next="next" prev="prev"/>
                </div>
                </div>
        </div>

            <div class="col-xs-6">

                <ls:topPosts/>
            </div>
    </div>
<script>

</script>
    </body>
    </html>