<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Start Bootstrap</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="about.html">About</a>
                </li>
                <li>
                    <a href="services.html">Services</a>
                </li>
                <li>
                    <a href="contact.html">Contact</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user" aria-hidden="true"></i> Member <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:choose>
	                        <c:when test="${ empty sessionScope.member.login }">
		                        <li>
		                            <a href="/login"><i class="fa fa-sign-in" aria-hidden="true"></i> Sign In</a>
		                        </li>
		                        <li>
		                            <a href="/register"><i class="fa fa-user-plus" aria-hidden="true"></i> Sign Up</a>
		                        </li>
	                        </c:when>
	                        <c:when test="${ !empty sessionScope.member.login }">
		                        <li>
		                            <a href="/profile"><i class="fa fa-bars" aria-hidden="true"></i> Profile</a>
		                        </li>
		                        <li>
		                            <a href="/signout"><i class="fa fa-sign-out" aria-hidden="true"></i> Sign Out</a>
		                        </li>
                        	</c:when>
                        </c:choose>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
