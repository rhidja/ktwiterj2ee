<!DOCTYPE html>
<html lang="en">

<head>
	<c:import url="inc/head.jsp"></c:import>
</head>

<body>

	<c:import url="inc/navbar.jsp"></c:import>

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-md-4 col-md-offset-4">
            	<c:if test="${ !empty error }">
                	<div class="alert alert-danger" role="alert">${ error } </div>
                </c:if>
                <div class="panel panel-primary">
                    <div class="panel-header"><h3 class="text-center"> Please SIGN UP </h3></div>
                    <form class="form form-signup" method="post">
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-user"></span>
                                    </span>
                                    <input type="text" name="login" class="form-control" placeholder="Login" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-envelope"></span>
                                    </span>
                                    <input type="Email" name="email" class="form-control" placeholder="Email"  required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-lock"></span>
                                    </span>
                                    <input type="password" name="password" class="form-control" placeholder="Password" required/>
                                </div>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-sm btn-primary btn-block" value="Signup"/>
                    </form>
                </div>
            </div>
        </div>

        <hr>

        <!-- Footer -->
        <footer>
			<c:import url="inc/footer.jsp"></c:import>
        </footer>
        <!-- /.footer -->

    </div>
    <!-- /.container -->

	<c:import url="inc/scripts.jsp"></c:import>

</body>

</html>
