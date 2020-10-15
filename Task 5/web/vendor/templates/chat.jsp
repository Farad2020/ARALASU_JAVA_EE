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
