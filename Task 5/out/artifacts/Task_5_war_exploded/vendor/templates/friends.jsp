<%@ page import="modules.User" %>
<%@ page import="modules.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.DBManager" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../blocks/head.jsp" %>
</head>
<body> <!-- maybe I'll add styling  class="<%//style_cookie_value%>" -->

<!-- <%//@include file="../blocks/signed_user_header.jsp" %>
<%//@include file="../blocks/not_signed_user_header.jsp" %>-->
<%@include file="../blocks/header.jsp" %>

<div class="container p-0">
    <% User page_user = current_user;//(User) request.getAttribute("user")
        if (page_user != null) {
    %>
    <div class="container mw-100 p-0 mt-5">
        <div class="row mw-100">
            <!-- user card -->
            <div class="col-3">
                <%@include file="../user_blocks/user_left_col.jsp" %>
            </div>

            <!-- List of posts of user -->
            <div class="col-6">
                <div class="searchbar mb-3">
                    <form class="form-inline d-flex justify-content-center md-form form-sm mt-0" action="/search_users"
                          method="get">
                        <input class="form-control ml-3 w-50 rounded-0 my-search-bar" type="text"
                               name="full_name_search" placeholder="Search..." aria-label="Search">

                        <button class="btn btn-outline-primary mx-3 btn-sm" type="submit">
                            <i class="fas fa-search" aria-hidden="true"></i>
                            Search
                        </button>
                    </form>
                </div>

                <!-- if new requests present -->
                <% ArrayList<User> requesting_users = (ArrayList<User>) request.getAttribute("requesting_users");
                    if (requesting_users != null && requesting_users.size() > 0) {%>
                <div class="card mb-2">
                    <div class="card-header">
                        New Requests: <%=requesting_users.size()%>
                    </div>
                    <div class="card-body">
                        <%for (User req_user : requesting_users) {%>
                        <div class="row my-1">
                            <div class="col-3 mx-2 ">
                                <!--<a href="/users?id=<%=req_user.getId()%>">-->
                                    <div class="friends-img-frame">
                                        <img class="friend-search-img"
                                             src="<%=req_user.getPicture_url()%>">
                                    </div>
                                <!--</a>-->
                            </div>

                            <div class="col">
                                <h5 class="card-title">
                                    <%=req_user.getFull_name()%>
                                </h5>
                                <h6 class="card-subtitle mb-2 text-muted">
                                    AGE: <%=req_user.getUserAge()%>
                                </h6>

                                <form method="post" action="/handle_request">
                                    <input hidden name="acceptor_id" value="<%=current_user.getId()%>">
                                    <input hidden name="requester_id" value="<%=req_user.getId()%>">
                                    <button name="confirm" class="btn btn-sm btn-outline-info"><i
                                            class="fas fa-user-plus"></i>
                                        Confirm
                                    </button>
                                    <button name="reject" class="btn btn-sm btn-outline-info"><i
                                            class="fas fa-trash-alt"></i>
                                        Reject
                                    </button>
                                </form>
                            </div>

                        </div>

                        <%
                            }%>
                    </div>
                </div>
                <%
                    }
                %>

                <% ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
                    if (friends != null) {
                        for (User friend : friends) {%>

                <!-- if default -->
                <div class="default-result">
                    <div class="card mb-3 friends-card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-3 mx-2 ">
                                    <a href="/users?id=<%=friend.getId()%>">
                                        <div class="friends-img-frame">
                                            <img class="friend-search-img"
                                                 src="<%=friend.getPicture_url()%>">
                                        </div>
                                    </a>
                                </div>

                                <div class="col">
                                    <h5 class="card-title"><%=friend.getFull_name()%>
                                    </h5>
                                    <h6 class="card-subtitle mb-2 text-muted">AGE: <%=friend.getUserAge()%>
                                    </h6>

                                    <button class="btn btn-sm btn-outline-info"><i class="fas fa-paper-plane"></i>
                                        Send Message
                                    </button>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

                <%
                        }
                    }
                %>


                <!-- if searched -->
                <%@include file="../user_blocks/search_list_block.jsp" %>

            </div>


            <!-- additional  -->
            <div class="col-3">
                <%@include file="../user_blocks/additional_info_right_col.jsp" %>
            </div>


        </div>
    </div>
    <%}%>
</div>

<%@include file="../blocks/footer.jsp" %>
<%@include file="../blocks/footer_loader.jsp" %>
</body>
</html>
