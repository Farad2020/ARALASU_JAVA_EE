<div class="row">
    <div class="text-center col-12 p-0 profile-picture-frame">
        <img src="<%=page_user.getPicture_url()%>" class="rounded profile-picture-default" alt="profile_picture">
    </div>

    <ul class="list-group col-12 mt-4 p-0">
        <li class="list-group-item"><%=page_user.getFull_name()%>, <%=page_user.getUserAge()%>
        </li>
        <li class="list-group-item"><a class="text-primary" href="/feed"><!-- users?id=<%//current_user.getId()%> -->
            <i class="fas fa-user-circle mr-2"></i>My Profile </a></li>

        <li class="list-group-item"><a class="text-primary" href="/user_edit?id=<%=current_user.getId()%>">
            <i class="fas fa-cogs mr-2"></i> Settings </a></li>

        <li class="list-group-item"><a class="text-danger" href="/user_sign_out">
            <i class="fas fa-sign-out-alt"></i> Sign Out</a></li>
    </ul>
</div>
