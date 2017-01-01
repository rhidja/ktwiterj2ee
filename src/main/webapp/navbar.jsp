<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <a class="navbar-brand" href="#">Start Bootstrap</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <%--  navbar right --%>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${ !empty sessionScope.member.login }">
                	<li><a href="#">Bonjour ${ sessionScope.member.login }! </a></li>
                </c:if>
                <li><a href="#">Home</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    	<span class="glyphicon glyphicon-user"></span> Member 
                    	<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                    	<c:choose>
                    	<c:when test="${ empty sessionScope.member.login }">
	                        <li><a href="#"><span class="glyphicon glyphicon-login"></span>  Signin</a></li>
	                        <li><a href="#"><span class="glyphicon glyphicon-pencil"></span> Signup</a></li>
	                    </c:when>
	                    <c:when test="${ !empty sessionScope.member.login }">  
	                        <li><a href="#">Profile</a></li>
	                        <li role="separator" class="divider"></li>
                        	<li><a href="#"><span class="glyphicon glyphicon-logout"></span> Signout</a></li>
                        </c:when>
                        </c:choose>
                    </ul>
                </li>
            </ul>
            <%--  ./ navbar right --%>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
