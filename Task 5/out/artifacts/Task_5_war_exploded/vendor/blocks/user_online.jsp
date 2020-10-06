<%@ page import="modules.User" %><%
    User current_user = (User)request.getSession().getAttribute("current_user");
    boolean ONLINE = false;
    if( current_user != null ){
        ONLINE = true;
    }
%>