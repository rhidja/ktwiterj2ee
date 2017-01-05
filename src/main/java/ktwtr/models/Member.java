package ktwtr.models;

import com.avaje.ebean.validation.Email;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.Past;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "tb_member")
public class Member {

    @Id
    @GeneratedValue
    private long id;
    
    @NotEmpty
    private String login;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String password;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    private String sexe;
    
    private String roles;
    
    @Past
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registerDate;

    private List<Message> messages;
    private List<Comment> comments;
    private List<Post> posts;
    
    // Static methods =====================================================================================
    
    
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
