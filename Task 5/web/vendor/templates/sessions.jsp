
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Home Page</title>
    <%@include file="../blocks/head.jsp"%>
</head>
<body>
<% String text = (String)request.getAttribute("text"); %>
<div class="container">
    <div class="container p-0 mt-5">
        <div class="row justify-content-center">
            <div class="col-6">
            <form action="/set_session" method="post">
                <div class="form-group">
                    <label for="userInput">Session Value <%=text%></label>
                    <input name="session_value" type="text" class="form-control" id="userInput">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <form action="/delete_session" method="post">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
            </div>
        </div>
    </div>
</div>

<%@include file="../blocks/footer_loader.jsp"%>
</body>
</html>
