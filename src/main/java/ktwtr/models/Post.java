package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.util.Date;
import java.util.List;

import ktwtr.models.Member;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@Table(name="Post")
public class Post{
    @Id
    @GeneratedValue
	private long id;
    private long likePost;
    private String title;
    private String content;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postDate;
    @ManyToOne
    private Member autor;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Comment> comments;

    // Getters and Setters  ==============================================================================
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLikePost() {
		return likePost;
	}
	public void setLikePost(long likePost) {
		this.likePost = likePost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Member getAutor() {
		return autor;
	}
	public void setAutor(Member autor) {
		this.autor = autor;
	}
	
	// Methodes statics  ================================================================================
	
	public static List<Post> all(){
		return Ebean.find(Post.class).order().desc("postDate").findList();
	}
	
	public static List<Post> listPosts (String login, String wall){
		return Ebean.find(Post.class).where().eq("login",login).eq("wall",wall).findList();
	}

	public static List<Post> getPostsByM (Member member){
		return Ebean.find(Post.class).where().eq("autor",member).findList();
	}
	
	public static Post getPost(int postId){
		return Ebean.find(Post.class).where().eq("id",postId).findUnique();
	}
	
	public static void setPost(Post post){
		post.postDate = new Date();
		Ebean.save(post);
	}	
}