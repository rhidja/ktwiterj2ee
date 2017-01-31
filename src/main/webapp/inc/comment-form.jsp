<!-- Comments Form -->
<div class="collapse" id="comment-form-<c:out value="${ requestScope.post.id }"/>">
	<div class="well">
	    <h4>Leave a Comment:</h4>
	    <form class="comment-form" role="form" action='<c:url value="/comments"></c:url>' method="POST">
	        <div class="form-group">
	        	<input type="hidden" name="post_id" value="<c:out value="${ requestScope.post.id }"/>">
	        	<input type="hidden" name="author_id" value="<c:out value="${ requestScope.post.author.id }"/>">
	            <textarea name="content" class="form-control" rows="3"></textarea>
	        </div>
	        <button type="submit" class="btn btn-primary">Submit</button>
	    </form>
	</div>
	
	<hr>
</div>