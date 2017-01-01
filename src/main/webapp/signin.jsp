<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<%@ include file="head.jsp" %>
</head>

<body>

	<%@ include file="navbar.jsp" %>

    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <c:if test="${ !empty error }">
                	<div class="alert alert-danger" role="alert">${ error } </div>
                </c:if>
                	
                <div class="panel panel-primary">
                    <div class="panel-header">
                        <h3 class="text-center"> Please SIGN IN </h3>
                    </div>
                    <form class="form" method="post">
	                    <div class="panel-body">  
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" name="login" class="form-control" placeholder="Login" required autofocus/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="password" name="password" class="form-control" placeholder="Password" required/>
                                </div>
                            </div>
                    	</div>
                    	<input type="submit" class="btn btn-sm btn-primary btn-block" value="Signin"/>
                    </form>
                </div>
            </div>
        </div>

        <hr>

        <!-- Footer -->
        <footer>
			<%@ include file="footer.jsp" %>
        </footer>
        <!-- /.footer -->

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
