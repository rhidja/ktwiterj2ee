package ktwtr.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_comment")
public class Comment{

    @Id
    @GeneratedValue
    private long id;
    private String content;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date commentDate;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Member author;

    // Getters and Setters =======================================================================
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

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

}
