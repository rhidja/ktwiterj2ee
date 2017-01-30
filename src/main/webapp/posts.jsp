<!DOCTYPE html>
<html lang="en">

<head>
	<c:import url="inc/head.jsp"></c:import>
</head>

<body>

	<c:import url="inc/navbar.jsp"></c:import>

	<div class="container">
		<div class="row">

		    <!-- Blog Post Content Column -->
		    <div class="col-lg-8">
	    		
	    		<h1>Create post</h1>
	    		
	    		<form method="POST" enctype="multipart/form-data">   		    
	    		    <div class="form-group">
	    		        <label for="title">Title <span class="require">*</span></label>
	    		        <input type="text" class="form-control" name="title" />
	    		    </div>
	    		    
	    		    <div class="form-group">
	    		        <label for="content">Content</label>
	    		        <textarea rows="5" class="form-control" name="content" ></textarea>
	    		    </div>
	
	    		    <div class="form-group">
	    		        <label for="file">Title <span class="require">*</span></label>
	    		        <input type="file" class="form-control" name="file" />
	    		    </div>
	
	    		    <div class="form-group">
	    		        <button type="submit" class="btn btn-primary">
	    		            Create
	    		        </button>
	    		        <button class="btn btn-default">
	    		            Cancel
	    		        </button>
	    		    </div>
	    		</form>
		
			</div>
		    
		    <hr>

		    <!-- Blog Sidebar Widgets Column -->
		    <div class="col-md-4">
				<%@ include file="inc/sidebar.jsp" %>
		    </div>
   		
   		</div> 
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