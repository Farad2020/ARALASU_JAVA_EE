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
    <% User other_user = (User)request.getAttribute("other_user");//(User) request.getAttribute("user")
        if (other_user != null) {
    %>
    <div class="container mw-100 p-0 mt-5">
        <div class="row mw-100">
            <!-- user card -->
            <div class="col-3">
                <%@include file="../user_blocks/other_user_left_col.jsp" %>
            </div>

            <!-- List of posts of user -->
            <div class="col-6">
                <% ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
                    if( posts != null){
                        for (Post post: posts) {%>
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="#" class="btn btn-outline-dark  mr-2" data-toggle="modal"
                               data-target="#postdDetailsModal<%=post.getId()%>">
                                <i class="fas fa-caret-down"></i></a>
                            <!-- More <iclass="fas fa-long-arrow-alt-right"></i>-->
                            <%=post.getTitle()%>
                        </h5>
                        <p class="card-text"><%=post.getShort_content()%>
                        </p>


                    </div>
                    <div class="card-footer text-muted">
                        <div class="d-flex  justify-content-between align-items-center">
                            <div class="container m-0 p-0">
                                Posted on <%=post.getFormattedPost_date()%> by <a
                                    href="/#"><%=post.getAuthor().getFull_name()%> <!-- users?id=<%//=post.getAuthor().getId()%> -->
                            </a>
                            </div>

                            <% if (current_user.getId().equals(post.getAuthor().getId())){%>
                            <a href="#" class="btn btn-outline-primary" data-toggle="modal"
                               data-target="#postdEditModal<%=post.getId()%>"><i class="far fa-edit"></i></a>
                            <%}%>
                        </div>
                    </div>
                </div>

                <!-- modal for update -->
                <div class="modal fade" id="postdEditModal<%=post.getId()%>" tabindex="-1"
                     role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <form action="/post_update" method="post">
                            <input hidden name="post_author_id" value="<%=post.getAuthor().getId()%>">

                            <input type="hidden" name="post_id" value="<%=post.getId()%>">

                            <div class="modal-content">

                                <div class="modal-header">
                                    <h5 class="modal-title">EDIT POST</h5>
                                </div>

                                <div class="modal-body">

                                    <div class="form-group">
                                        <label>Post Title</label>
                                        <input type="text" class="form-control" name="post_title"
                                               value="<%=post.getTitle()%>">
                                    </div>

                                    <div class="form-group">
                                        <label>News Short Content</label>
                                        <textarea maxlength="150" class="form-control"
                                                  name="post_short_content"><%=post.getShort_content()%></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>News Short Content</label>
                                        <textarea maxlength="150" class="form-control"
                                                  name="post_content"><%=post.getContent()%></textarea>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">
                                            Cancel
                                        </button>
                                        <button class="btn btn-success" name="update">Save
                                            changes
                                        </button>
                                        <button class="btn btn-danger" name="delete">Delete
                                            Post
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- post details modal -->
                <div class="modal fade" id="postdDetailsModal<%=post.getId()%>" tabindex="-1" role="dialog"
                     aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">POST DETAILS</h5>
                            </div>

                            <div class="modal-body">
                                <div class="form-group">
                                    <small><%=post.getAuthor().getFull_name()%>
                                    </small><br>
                                    <h5 class="font-weight-bold"><%=post.getTitle()%>
                                    </h5>
                                    <small><%=post.getPost_date()%>
                                    </small>
                                </div>

                                <div class="form-group">
                                    <div class="font-weight-bold"><%=post.getShort_content()%>
                                    </div>
                                    <%=post.getContent()%>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                            data-dismiss="modal">
                                        Cancel
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
            </div>


            <!-- additional  -->
            <div class="col-3">
                <%@include file="../user_blocks/additional_info_right_col.jsp" %>
            </div>



        </div>
    </div>
    <%}%>
</div>

<%@include file="../blocks/footer.jsp"%>
<%@include file="../blocks/footer_loader.jsp" %>
</body>
</html>
