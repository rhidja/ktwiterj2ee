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

            	<div class="panel panel-primary">
                    <div class="panel-header">
                        <h3 class="text-center"> Upload file form </h3>
                    </div>
                    <form class="form" method="post" enctype="multipart/form-data">
	                    <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" name="description" class="form-control" placeholder="Description" autofocus/>
									<p class="help-block">${form.erreurs['description']}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="file" name="fichier" class="form-control" required/>
									<p class="help-block">${form.erreurs['fichier']}</p>
                                </div>
                            </div>
                            
                            <p class="help-block">${ fichier }</p>
                            <p class="help-block">${ description }</p>
                    	</div>
                    	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                    	<input type="submit" class="btn btn-sm btn-primary btn-block" value="Upload"/>
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
