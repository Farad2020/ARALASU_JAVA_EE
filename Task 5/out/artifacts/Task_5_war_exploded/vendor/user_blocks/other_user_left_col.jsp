<div class="row">
    <div class="text-center col-12 p-0 profile-picture-frame">
        <img src="<%=other_user.getPicture_url()%>" class="rounded profile-picture-default" alt="profile_picture">
    </div>

    <ul class="list-group col-12 mt-4 p-0">
        <li class="list-group-item"><%=other_user.getFull_name()%>, <%=other_user.getUserAge()%>
        </li>
        <li class="list-group-item"><a class="text-primary" href="/remove_friend?id=<%=other_user.getId()%>">
            <i class="fas fa-trash"></i> Delete from friends </a></li>
        <!-- create if it is not a friend -->

        <li class="list-group-item">
            <a class="text-primary" href="#"
               data-toggle="modal" data-target="#sendMessageModal"  >
            <i class="fas fa-paper-plane"></i> Send Message </a></li>

        <li class="list-group-item"><a class="text-danger" href="/user_sign_out">
            <i class="fas fa-sign-out-alt"></i> Sign Out</a></li>
    </ul>
</div>

<div class="modal fade" id="sendMessageModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <form action="/send_message" method="post" accept-charset="UTF-8">

            <input hidden type="text" value="<%=current_user.getId()%>" name="author_id">
            <input hidden type="text" value="<%=other_user.getId()%>" name="opponent_id">

            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">SEND A MESSAGE</h5>
                </div>

                <div class="modal-body">

                    <div class="form-group">
                        <label>Content</label>
                        <textarea class="form-control" name="post_content" rows="18"></textarea>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        Cancel
                    </button>
                    <button class="btn btn-success">Send</button>
                </div>

            </div>
        </form>
    </div>
</div>