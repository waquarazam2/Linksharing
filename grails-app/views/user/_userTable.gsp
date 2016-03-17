<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Username</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Active</th>
        <th>Manage</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${users}" var="user">
        <g:if test="${user.active}">
            <g:set var="active" value="success"/>
        </g:if>
        <g:else>
            <g:set var="active" value="danger"/>
        </g:else>
        <g:if test="${user.id!=session.user.id || !user.admin}">
            <tr class="${active}">
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>
                    <g:if test="${user.active}">
                        Yes
                    </g:if>
                    <g:else>
                        No
                    </g:else>
                </td>
                <td><g:if test="${user.active}">
                    <div id="responseMessage"+${user.id}></div>
                    <a href="#" class="activateButtons" id="deactivate"
                       onclick="updateActivation(${user.id}, this.id)">Deactivate</a>
                </g:if>
                    <g:else>
                        <a href="#" class="activateButtons" id="activate"
                           onclick="updateActivation(${user.id}, this.id)">Activate</a>
                    </g:else></td>
            </tr>
        </g:if>
    </g:each>
    </tbody>
</table>
<script>
    function updateActivation(id, buttonid) {
        var active = ($("#" + buttonid).text()) == "Activate";
        $.ajax({
            url: "/user/activateUser",
            data: {"activate": active, "userId": id},
            method: "POST",
            success: function (data) {
                window.location.reload(true)
            },
            error: function (data) {
                window.location.reload(true)
            }
        });
    }
    function loadUserTable(callback) {
        $("#userTableSection").load("/user/loadUserTable", function () {
            if (callback) {
                callback();
            }
        });
    }
</script>