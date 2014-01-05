/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ram
 */
@Entity
@Table(name="tb_likes")
public class Likes implements Serializable{

    @Id
    @GeneratedValue
    long Id;
    @ManyToOne
    Member member;
    @ManyToOne
    Post post;
    @ManyToOne
    Comment comment;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateLike;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Date getDateLike() {
        return dateLike;
    }

    public void setDateLike(Date dateLike) {
        this.dateLike = dateLike;
    }
}