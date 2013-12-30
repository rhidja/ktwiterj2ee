package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
@Table(name="Comment")
public class Comment{
	@Id
    @GeneratedValue
	private long id;
	private long likeComment;
	private String content;
	@ManyToOne
	private Post post;
	@ManyToOne
	private Member autor;
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date commentDate;
	
	// Getters and Setters =======================================================================

	public long getId() { // id
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getLikeComment() { //like
		return likeComment;
	}
	public void setLikeComment() {
		this.likeComment++;
	}
	
	public String getContent() {// Content.
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	public Member getAutor() {
		return autor;
	}
	public void setAutor(Member autor) {
		this.autor = autor;
	}
	
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	
	// Methodes statiques  ======================================================================
	

	
	public static List<Comment> all(){
		
		return Ebean.find(Comment.class).findList();
	}
	
	public static List<Comment> getComments(Post post){
		return Ebean.find(Comment.class).where().eq("post",post).findList();
	}
	
	public static Comment getComment(long id){
		return Ebean.find(Comment.class).where().eq("id",id).findUnique();
	}
	
	public static void setComment(Comment comment){
		comment.commentDate = new Date();
                Ebean.save(comment);
	}	
}
