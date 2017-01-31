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
	<p>
          <i class="icon-user"></i> by <a href="#"><a href="#"><c:out value="${ requestScope.post.author.login }"/></a></a> 
          | <i class="icon-calendar"></i> <c:out value="${ requestScope.post.postDate }"/>
          | <i class="icon-comment"></i> 
            <a data-toggle="collapse" href="#comment-form-<c:out value="${ requestScope.post.id }"/>" aria-expanded="false" aria-controls="comment-form">
  				3 Comments
			</a>
          | <i class="icon-share"></i> <a href="#">39 Shares</a>
	</p>
    <hr>

	<c:import url="inc/comment-form.jsp"></c:import>
	<c:import url="inc/comments.jsp"></c:import>

</article>