<article class="row">
	<!-- Blog Post -->
	
	<!-- Title -->
	<h1><c:out value="${ requestScope.post.title}" /></h1>
	
	<!-- Author -->
	<p class="lead">
	    by <a href="#"><c:out value="${ requestScope.post.author.login }"/></a>
	</p>
	
	<hr>
	
	<!-- Date/Time -->
	<p><span class="glyphicon glyphicon-time"></span> Posted on <c:out value="${ requestScope.post.postDate }"/></p>
	
	<hr>
	
	<!-- Preview Image -->
	<img class="img-responsive" src='<c:url value="/images/${ requestScope.post.image.name }"></c:url>' alt="">
	
	<hr>
	
	<!-- Post Content -->
	<p class="lead"><c:out value="${requestScope.post.content}"/></p>
	<p><c:out value="${requestScope.post.content}"/></p>
	
	<hr>
	
	<!-- Blog Comments -->
	
	<!-- Comments Form -->
	<div class="well">
	    <h4>Leave a Comment:</h4>
	    <form role="form">
	        <div class="form-group">
	            <textarea class="form-control" rows="3"></textarea>
	        </div>
	        <button type="submit" class="btn btn-primary">Submit</button>
	    </form>
	</div>
	
	<hr>

</article>