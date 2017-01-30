package ktwtr.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
*
* @author rhidja
*/
@Entity
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String content;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postDate;
    @ManyToOne
    private Member author;
    private List<Comment> comments;

    // Methodes statics  ================================================================================

    // Getters and Setters
    // ==============================================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
