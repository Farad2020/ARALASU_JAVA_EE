<%@ page import="modules.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../blocks/head.jsp" %>
</head>
<body> <!-- maybe I'll add styling  class="<%//style_cookie_value%>" -->

<%@include file="../blocks/header.jsp" %>

<div class="container p-0">
    <% User page_user = (User)request.getSession().getAttribute("current_user");
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


                <div class="container p-0 my-5 text-center">
                    <h1 class="card-title font-italic">Update Account</h1>
                </div>


                <div class="container mw-100 p-0 mt-5">

                    <div class="row justify-content-center">
                        <form class="col-12" action="/user_edit" method="post">
                            <input hidden name="id" value="<%=current_user.getId()%>">
                            <div class="form-group">
                                <label for="userEmailInput">Email address</label>
                                <input name="email" type="email" class="form-control" id="userEmailInput"
                                       aria-describedby="emailHelp" value="<%=current_user.getEmail()%>" required>
                                <small class="form-text text-muted">Input your email.</small>
                            </div>

                            <div class="form-group">
                                <label for="userFullNameInput">Full Name</label>
                                <input name="full_name" type="text" class="form-control" id="userFullNameInput"
                                       value="<%=current_user.getFull_name()%>" required>
                                <small class="form-text text-muted">Input your Full Name.</small>
                            </div>

                            <div class="form-group">
                                <label for="userBirthdateInput">Birthdate</label>
                                <input name="birthdate" type="date" class="form-control" id="userBirthdateInput"
                                       value="<%=current_user.getBirth_date()%>" required>
                                <small class="form-text text-muted">Input your birthdate.</small>
                            </div>

                            <div class="form-group">
                                <label for="userPictureInput">Picture URL</label>
                                <input name="picture_url" type="text" class="form-control" id="userPictureInput"
                                       value="<%=current_user.getPicture_url()%>" required>
                                <small class="form-text text-muted">Input your Picture URL.</small>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                            <hr/>
                        </form>



                        <form class="col-12" action="/user_password_edit" method="post">
                            <% String re_password_error = request.getParameter("re_password_error");
                                String password_error = request.getParameter("password_error");
                                String password_success = request.getParameter("password_success");
                                if (re_password_error != null) {%>
                            <div class="alert alert-danger" role="alert">
                                Your passwords are different!
                            </div>
                            <%}%>
                            <% if (password_error != null) {%>
                            <div class="alert alert-danger" role="alert">
                                You've inputted incorrect password!
                            </div>
                            <%}%>
                            <% if (password_success != null) {%>
                            <div class="alert alert-success" role="alert">
                                You're password successfully updated!
                            </div>
                            <%}%>

                            <input hidden name="id" value="<%=current_user.getId()%>">
                            <div class="form-group">
                                <label for="userPasswordInput">Current Password</label>
                                <input name="password" type="password" class="form-control" id="userPasswordInput"
                                       placeholder="Password" required>
                                <small class="form-text text-muted">Input your password.</small>
                            </div>

                            <div class="form-group">
                                <label for="userNewPasswordInput">New Password</label>
                                <input name="new_password" type="password" class="form-control" id="userNewPasswordInput"
                                       placeholder="Password" required>
                                <small class="form-text text-muted">Input your password.</small>
                            </div>

                            <div class="form-group">
                                <label for="userRePasswordInput">Re-Password</label>
                                <input name="re_password" type="password" class="form-control" id="userRePasswordInput"
                                       placeholder="Re-Password" required>
                                <small class="form-text text-muted">Re-Input your password.</small>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
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
