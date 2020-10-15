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

    <div class="container p-0 my-5 text-center">
        <h1 class="card-title font-italic">Create New Account</h1>
    </div>


    <div class="container mw-100 p-0 mt-5">
        <% String re_password_error = request.getParameter("re_password_error");
            String email_error = request.getParameter("email_error");
            if( re_password_error != null ){%>
        <div class="alert alert-danger col-6 offset-3" role="alert">
            Your passwords are different!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}
            if( email_error != null ){%>
        <div class="alert alert-danger col-6 offset-3" role="alert">
            Your email is already in use!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>

        <div class="row justify-content-center">
            <form class="col-6" action="/user_registration" method="post">
                <div class="form-group">
                    <label for="userEmailInput">Email address</label>
                    <input name="email" type="email" class="form-control" id="userEmailInput" aria-describedby="emailHelp" placeholder="Enter email" required>
                    <small class="form-text text-muted">Input your email.</small>
                </div>

                <div class="form-group">
                    <label for="userPasswordInput">Password</label>
                    <input name="password" type="password" class="form-control" id="userPasswordInput" placeholder="Password" required>
                    <small class="form-text text-muted">Input your password.</small>
                </div>

                <div class="form-group">
                    <label for="userRePasswordInput">Re-Password</label>
                    <input name="re_password" type="password" class="form-control" id="userRePasswordInput" placeholder="Re-Password" required>
                    <small class="form-text text-muted">Re-Input your password.</small>
                </div>

                <div class="form-group">
                    <label for="userFullNameInput">Full Name</label>
                    <input name="full_name" type="text" class="form-control" id="userFullNameInput" placeholder="Full Name" required>
                    <small class="form-text text-muted">Input your Full Name.</small>
                </div>

                <div class="form-group">
                    <label for="userBirthdateInput">Birthdate</label>
                    <input name="birthdate" type="date" class="form-control" id="userBirthdateInput" required>
                    <small class="form-text text-muted">Input your birthdate.</small>
                </div>

                <button type="submit" class="btn btn-outline-primary">Submit</button>
            </form>
        </div>
    </div>

</div>

<%@include file="../blocks/footer.jsp"%>
<%@include file="../blocks/footer_loader.jsp" %>
</body>
</html>
