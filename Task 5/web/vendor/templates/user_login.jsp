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

<div class="container p-0" style="margin: 7.3rem auto;">

    <% String remember_user_email = (String) request.getAttribute("remember_user_email");
        String remember_user_password = (String) request.getAttribute("remember_user_password");
        %>

    <div class="container p-0 my-5 text-center">
        <h1 class="card-title font-italic">SIGN IN</h1>
    </div>



    <div class="container mw-100 p-0 mt-5">
        <% String error = request.getParameter("error");
            if( error != null ){%>
        <div class="alert alert-danger col-6 offset-3" role="alert">
            Your email or password are incorrect!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>
        <div class="row justify-content-center">


            <form class="col-6" action="/user_login" method="post">
                <div class="form-group">
                    <label for="userEmailInput">Email address</label>
                    <input name="email" type="email" class="form-control" id="userEmailInput" aria-describedby="emailHelp" placeholder="Enter email"
                           value="<%=remember_user_email != null? remember_user_email:"" %>" required>
                    <small class="form-text text-muted">Input your email.</small>
                </div>

                <div class="form-group">
                    <label for="userPasswordInput">Password</label>
                    <input name="password" type="password" class="form-control" id="userPasswordInput" placeholder="Password"
                           value="<%=remember_user_password != null? remember_user_password:"" %>" required>
                    <small class="form-text text-muted">Input your password.</small>
                </div>
                <div class="form-check">
                    <input name="remember_user" type="checkbox" class="form-check-input" id="userRememberCheck">
                    <label class="form-check-label" for="userRememberCheck">Remember me</label>
                </div>
                <button type="submit" class="btn btn-outline-primary mt-2">Submit</button>
            </form>
        </div>
    </div>

</div>

<%@include file="../blocks/footer.jsp"%>
<%@include file="../blocks/footer_loader.jsp" %>
</body>
</html>
