<%@ page import="modules.User" %>
<%@ page import="java.util.ArrayList" %>
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

    <div class="container p-0 my-5 ">
        <h1 class="card-title font-weight-light font-italic">ARALASU USERS RN</h1>
    </div>


    <div class="container mw-100 p-0 mt-5">
        <div class="row mw-100">

            <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {

            %>
            <div class="col-6">
                <div class="card m-3">
                    <img class="card-img-top" src="<%=user.getPicture_url()%>" alt="Card image cap">
                    <!-- here I'll put singular_news.pic_url later-->
                    <div class="card-body">
                        <a href="#"
                           class="card-text font-weight-bold main_link"><%=user.getFull_name()%>
                        </a>
                        <blockquote class="blockquote mb-0">
                            <h5 class="card-title"><%=user.getEmail()%>
                            </h5>
                            <h6 class="card-subtitle mb-2 text-muted"><%=user.getBirth_date()%>
                            </h6>
                        </blockquote>
                    </div>
                </div>
            </div>
            <%
                    }
                }%>
        </div>
    </div>

</div>

<%@include file="../blocks/footer.jsp"%>
<%@include file="../blocks/footer_loader.jsp" %>
</body>
</html>
