<div class="panel-body">
    <g:each in="${searchResources}" var="resource">
        <div>
            <div class="col-xs-2">
                <img src="${g.createLink(controller: 'user', action: 'image', params:[id:resource?.createdBy?.id])}" width="65px" height="65px"/>
            </div>

            <div class="col-xs-10">

                <div class="col-xs-6">
                    <span class="h5">${resource.createdBy.name}</span>
                    <small class="text-muted">@${resource.createdBy.userName}</small>
                </div>

                <div class="col-xs-3">
                </div>

                <div class="col-xs-3">
                    <a href="#" class="text-left">${resource.topic.name}</a>
                </div>


                <p>${resource.description}</p>


                <div class="col-xs-1 fa fa-facebook-official"></div>

                <div class="col-xs-1 fa fa-twitter"></div>

                <div class="col-xs-1 fa fa-google-plus"></div>

                <div class="col-xs-2"><small><a href="#">Download</a></small></div>

                <div class="col-xs-3"><small><a href="#">View Full Site</a></small></div>

                <div class="col-xs-2"><small><ls:markAsRead id="${resource.id}" /></small></div>

                <div class="col-xs-2"><small><a href="#">View Post</a></small></div>

            </div>
        </div>
    </g:each>
</div>