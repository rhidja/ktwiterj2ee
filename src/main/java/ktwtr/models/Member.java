package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="Member")
public class Member{
	@Id
	@GeneratedValue
	private long id;
	private String login;
	private String email;
	private String motPasse;
	@OneToOne(mappedBy="member",cascade = {CascadeType.ALL})
	private Profile profile;
	@OneToMany(mappedBy="autor",cascade = {CascadeType.ALL})
	private List<Post> posts;
	@OneToMany(mappedBy="autor",cascade = {CascadeType.ALL})
    private List<Comment> comments;
	
	// Getters and Setters ================================================================================

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotPasse() {
		return motPasse;
	}
	
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	// Methodes statics ====================================================================================
	
	public static List<Member> all(){
		return Ebean.find(Member.class).findList();  
	}
	
	public static Member getMember(String login){
		return Ebean.find(Member.class).where().eq("login",login).findUnique();
	}
	
	public static Boolean isMember (String login, String motPasse){
		return Ebean.find(Member.class).where().eq("login",login).eq("motPasse",motPasse).findRowCount()>0;
	}	
}
