<% String full_name = request.getParameter("full_name_search");
    if (full_name != null){%>
<div class="card p-2">
    <h3>Search Result For: "<%=full_name%>"</h3>
</div>
<%}%>

<!-- People that User send Request -->
<% ArrayList<User> searched_and_requested_users = (ArrayList<User>) request.getAttribute("searched_and_requested_users");
    if (searched_and_requested_users != null && searched_and_requested_users.size() > 0) {%>
<div class="card my-4 p-2">
    <h3 class="text-primary">People that You've sent Request:</h3>
</div>
<%
    for (User searched_and_requested_user : searched_and_requested_users) {
        if (!searched_and_requested_user.getId().equals(current_user.getId())) {
%>
<div class="search-result">
    <div class="card mb-3 friends-card">
        <div class="card-body">
            <div class="row">

                <div class="col-3 mx-2 friends-img-frame">
                    <!--<a href="/users?id=<%//=searched_and_requested_user.getId()%>">-->
                        <div class="friends-img-frame">
                            <img class="friend-search-img "
                                 src="<%=searched_and_requested_user.getPicture_url()%>">
                        </div>
                    <!--</a>-->
                </div>

                <div class="col">
                    <h5 class="card-title">
                        <%=searched_and_requested_user.getFull_name()%>
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        AGE: <%=searched_and_requested_user.getUserAge()%>
                    </h6>

                    <button class="btn btn-outline-info btn-sm">
                        <i class="fas fa-check"></i> Sent
                    </button>
                </div>

            </div>

        </div>
    </div>
</div>
<% }
}
}
%>



<!-- Friends -->
<% ArrayList<User> searched_user_friends = (ArrayList<User>) request.getAttribute("searched_user_friends");
    if (searched_user_friends != null && searched_user_friends.size() > 0) {%>
<div class="card my-4 p-2">
    <h3 class="text-primary">Friends:</h3>

</div>
<%
    for (User searched_friend : searched_user_friends) {
        if (!searched_friend.getId().equals(current_user.getId())) {
%>
<div class="search-result">
    <div class="card mb-3 friends-card">
        <div class="card-body">
            <div class="row">

                <div class="col-3 mx-2 friends-img-frame">
                    <a href="/users?id=<%=searched_friend.getId()%>">
                        <div class="friends-img-frame">
                            <img class="friend-search-img "
                                 src="<%=searched_friend.getPicture_url()%>">
                        </div>
                    </a>
                </div>

                <div class="col">
                    <h5 class="card-title">
                        <%=searched_friend.getFull_name()%>
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        AGE: <%=searched_friend.getUserAge()%>
                    </h6>

                    <button class="btn btn-sm btn-outline-info"><i class="fas fa-paper-plane"></i>
                        Send Message
                    </button>
                </div>

            </div>

        </div>
    </div>
</div>
<% }
}
}
%>

<!-- Other People -->
<% ArrayList<User> searched_users = (ArrayList<User>) request.getAttribute("searched_users");
    if (searched_users != null && searched_users.size() > 0) {%>
<div class="card my-4 p-2">
    <h3 class="text-primary">Other People:</h3>
</div>
<%
    for (User searched_user : searched_users) {
        if (!searched_user.getId().equals(current_user.getId())) {
%>
<div class="search-result">
    <div class="card mb-3 friends-card">
        <div class="card-body">
            <div class="row">

                <div class="col-3 mx-2 friends-img-frame">
                    <!--<a href="/users?id=<%//=searched_user.getId()%>">-->
                        <div class="friends-img-frame">
                            <img class="friend-search-img "
                                 src="<%=searched_user.getPicture_url()%>">
                        </div>
                    <!--</a>-->
                </div>

                <div class="col">
                    <h5 class="card-title">
                        <%=searched_user.getFull_name()%>
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        AGE: <%=searched_user.getUserAge()%>
                    </h6>

                    <form method="post" action="/handle_request">
                        <input hidden name="acceptor_id" value="<%=searched_user.getId()%>">
                        <input hidden name="requester_id" value="<%=current_user.getId()%>">
                        <button name="send" class="btn btn-outline-info btn-sm">
                            <i class="fas fa-user-plus"></i> Send Friend Request
                        </button>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>
<% }
}
}
%>