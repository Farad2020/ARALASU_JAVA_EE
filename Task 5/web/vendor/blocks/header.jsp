<%@include file="user_online.jsp"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light-blue">
    <div class="container ">
        <a class="navbar-brand text-white" href="/home"><i class="fab fa-artstation"></i>ARALASU.ME</a>

        <%if(!ONLINE){%>
        <div class="ml-auto" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link text-white" href="/user_registration"><i class="fas fa-user-plus mr-2"></i> Register</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link text-white" href="/user_login"> <i class="fas fa-sign-in-alt mr-2"></i> Login </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="far fa-question-circle mr-2"></i> FAQ</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="fas fa-share-alt mr-2"></i>About ARALASU</a>
                </li>
            </ul>
        </div>

        <%}else{%>
        <div class="ml-auto" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <!--
                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="fas fa-user-circle mr-2"></i>Me</a>
                </li>
                -->

                <li class="nav-item active">
                    <a class="nav-link text-white" href="/feed"><i class="far fa-newspaper mr-2"></i>Feed</a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="fas fa-user-friends mr-2"></i> Friends </a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="fas fa-users mr-2"></i> Groups</a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link text-white" href="/user_posts"><i class="fas fa-comments mr-2"></i>My Posts</a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="fab fa-telegram-plane mr-2"></i>Messages</a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="far fa-images mr-2"></i>Pictures</a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link text-white" href="#"><i class="fas fa-video mr-2"></i>Videos</a>
                </li>
            </ul>
        </div>
        <%}%>
    </div>
</nav>