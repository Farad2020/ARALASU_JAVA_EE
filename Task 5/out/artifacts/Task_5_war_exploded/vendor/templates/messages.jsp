<%@ page import="modules.User" %>
<%@ page import="modules.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.DBManager" %>
<%@ page import="modules.Message" %>
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
                <div class="card mb-2">
                    <div class="card-body overflow-auto" style="height: 70vh; max-height: 70vh;">
                <% ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messages");
                    User opponent_user = (User) request.getAttribute("opponent_user");
                    User message_user;
                    if (messages != null && messages.size() > 0) {%>

                        <%for (Message message : messages) {
                            message_user = message.getSender_id().equals(opponent_user.getId()) ? opponent_user : current_user;
                            if( !current_user.getId().equals(message_user.getId())){
                                message.setRead_by_receiver(true);
                                DBManager.readChatMessage(message.getId());
                            }
                        %>
                        <div class="row my-1">
                            <div class="col-3 mx-2 ">
                                <div class="friends-img-frame">
                                    <img class="friend-search-img"
                                         src="<%=message_user.getPicture_url()%>">
                                </div>
                            </div>

                            <div class="col">
                                <h5 class="card-title">
                                    <%=message_user.getFull_name()%>
                                </h5>
                                <h6 class="card-subtitle mb-2 text-muted">
                                    <%=message.getFormattedSent_date()%>
                                </h6>
                                <p>
                                    <%=message.getMessage_text()%>
                                </p>

                            </div>

                        </div>

                        <%
                            }%>
                <%
                    }
                %>
                    </div>
                </div>



                <div class="searchbar mb-3">
                    <form class="form-inline d-flex justify-content-center md-form form-sm mt-0" action="/send_message"
                          method="post">
                        <input hidden value="<%=opponent_user.getId()%>" name="opponent_id">
                        <input class="form-control ml-3 w-50 rounded-0 my-search-bar" type="text"
                               name="message_text" placeholder="Write here...">

                        <button class="btn btn-outline-primary mx-3 btn-sm" type="submit">
                            Send
                        </button>
                    </form>
                </div>
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
