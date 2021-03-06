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
            	<c:choose>
            		<c:when test="${ !empty form.result }"><div class="alert alert-danger" role="alert">${form.result} </div></c:when>
            		<%-- <c:when test="${ empty form.errors }"><div class="alert alert-success" role="alert">${form.result} </div></c:when> --%>
            	</c:choose>
            	<div class="panel panel-primary">
                    <div class="panel-header">
                        <h3 class="text-center"> Please SIGN IN </h3>
                    </div>
                    <form class="form" method="post">
	                    <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="email" name="email" class="form-control" placeholder="Email" required autofocus ${ member.email }/>
									<p class="help-block">${form.errors['email']}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="password" name="password" class="form-control" placeholder="Password" required/>
									<p class="help-block">${form.errors['password']}</p>
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
        	<c:import url="inc/footer.jsp"></c:import>
        </footer>
        <!-- /.footer -->

    </div>
    <!-- /.container -->

	<c:import url="inc/scripts.jsp"></c:import>

</body>

</html>
