package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "tb_member")
public class Member implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String email;
    private String password;
    @OneToOne(mappedBy = "member", cascade = {CascadeType.ALL})
    private Profile profile;
    @OneToMany(mappedBy = "autor", cascade = {CascadeType.ALL})
    private List<Post> posts;
    @OneToMany(mappedBy = "autor", cascade = {CascadeType.ALL})
    private List<Comment> comments;
    @OneToMany(mappedBy = "expediteur", cascade = {CascadeType.ALL})
    private List<Message> messages_envoyes;
    @OneToMany(mappedBy = "destinataire", cascade = {CascadeType.ALL})
    private List<Message> messages_recus;
    @OneToOne(mappedBy = "member", cascade = {CascadeType.ALL})
    private List<Likes> likedPosts;
    @OneToOne(mappedBy = "member", cascade = {CascadeType.ALL})
    private List<Likes> likedComments;

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

    public List<Message> getMessages_envoyes() {
        return messages_envoyes;
    }

    public void setMessages_envoyes(List<Message> messages_envoyes) {
        this.messages_envoyes = messages_envoyes;
    }

    public List<Message> getMessages_recus() {
        return messages_recus;
    }

    public void setMessages_recus(List<Message> messages_recus) {
        this.messages_recus = messages_recus;
    }

    public List<Likes> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Likes> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<Likes> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(List<Likes> likedComments) {
        this.likedComments = likedComments;
    }

    // Methodes statics ====================================================================================
    public static List<Member> members() {
        return Ebean.find(Member.class).findList();
    }

    public static Member getMember(String login) {
        return Ebean.find(Member.class).where().eq("login", login).findUnique();
    }

    public static Boolean isMember(String login, String password) {
        return Ebean.find(Member.class).where().eq("login", login).eq("password", password).findRowCount() > 0;
    }
}
