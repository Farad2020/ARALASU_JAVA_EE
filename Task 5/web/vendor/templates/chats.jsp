<%@ page import="java.util.ArrayList" %>
<%@ page import="db.DBManager" %>
<%@ page import="modules.*" %>
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
                    <form class="form-inline d-flex justify-content-center md-form form-sm mt-0" action="/search_chats"
                          method="get">
                        <input class="form-control ml-3 w-50 rounded-0 my-search-bar" type="text"
                               name="chat_search" placeholder="Search..." aria-label="Search">

                        <button class="btn btn-outline-primary mx-3 btn-sm" type="submit">
                            <i class="fas fa-search" aria-hidden="true"></i>
                            Search
                        </button>
                    </form>
                </div>


                <% ArrayList<Chat> chats = (ArrayList<Chat>) request.getAttribute("chats");
                    if (chats != null && chats.size() > 0) {
                        for (Chat chat : chats) {
                            Message last_message = DBManager.getLastChatMessage(chat.getOpponent_user_id(), chat.getUser_id());
                            User opponent = DBManager.getUser(chat.getUser_id().equals(current_user.getId()) ? chat.getOpponent_user_id() : chat.getUser_id());
                %>
                <div class="card mb-2">
                    <div class="card-body">
                        <div class="row my-1">
                            <div class="col-3 mx-2 ">
                                <!--<a href="/users?id=<%=opponent.getId()%>">-->
                                <div class="friends-img-frame">
                                    <img class="friend-search-img"
                                         src="<%=opponent.getPicture_url()%>">
                                </div>
                                <!--</a>-->
                            </div>

                            <div class="col">

                                <h5 class="card-title">
                                    <%=(!last_message.isRead_by_receiver() && last_message.getSender_id().equals(opponent.getId())
                                            ? ("***" + opponent.getFull_name() + "***") : opponent.getFull_name())%>
                                </h5>
                                <h6 class="card-subtitle mb-2 text-muted">
                                    <a href="/messages?id=<%=opponent.getId()%>" class="stretched-link">
                                            <%=last_message.getFormattedSent_date()%>
                                    </a>
                                </h6>
                                <p>
                                    <%=last_message.getMessage_text()%>
                                </p>

                            </div>

                        </div>

                    </div>
                </div>
                <%
                        }
                    }
                %>


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
