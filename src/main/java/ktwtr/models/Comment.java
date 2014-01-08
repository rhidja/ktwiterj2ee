package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_comment")
public class Comment implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    private String content;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date commentDate;
    
    @ManyToOne
    private Post post;
    @ManyToOne
    private Member autor;
//    @OneToMany(cascade = {CascadeType.ALL})
//    private List<Likes> likes;


//    // Getters and Setters =======================================================================
    public long getId() { // id
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
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
    
//	public List<Likes> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(List<Likes> likes) {
//		this.likes = likes;
//	}
//
//    // Methodes statiques  ======================================================================
//    public long getNbrLikes() {
//        return this.likes.size();
//    }
//    
    public static List<Comment> all() {

        return Ebean.find(Comment.class).findList();
    }

    public static List<Comment> getComments(Post post) {
        return Ebean.find(Comment.class).where().eq("post", post).findList();
    }

    public static Comment getComment(long id) {
        return Ebean.find(Comment.class).where().eq("id", id).findUnique();
    }

    public static void setComment(Comment comment) {
        comment.commentDate = new Date();
        Ebean.save(comment);
    }
}
