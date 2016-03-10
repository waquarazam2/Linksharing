
<div class="panel panel-default ">
    <div class="panel-heading">Top Posts</div>
<g:each in="${topPosts}" var="post">
    <div class="panel-body bdy">
        <div class="row">
            <div class="col-xs-2">
                <div class="glyphicon glyphicon-user"></div>
            </div>

            <div class="col-xs-10">
                <div>${post[3]}

                    <div class="pull-right"><a href="#">Grails</a></div>

                    <p>${post[4]}</p>
                    <i class="fa fa-facebook-official"></i>
                    <i class="fa fa-tumblr"></i>
                    <i class="fa fa-google-plus"></i>
                </div>
            </div>
        </div>
        <br/>
    </div>
</g:each>
</div>

