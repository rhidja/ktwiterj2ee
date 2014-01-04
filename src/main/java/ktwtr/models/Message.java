/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.models;

import java.util.Date;
import javax.annotation.Generated;
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
@Table(name="tb_message")
public class Message {

    @Id
    @GeneratedValue
    long id;
    String title;
    String message;
    @ManyToOne
    Member expediteur;
    @ManyToOne
    Member recepteur;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateEnvoi;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Member getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Member expiditeur) {
        this.expediteur = expiditeur;
    }

    public Member getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(Member recepteur) {
        this.recepteur = recepteur;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
    
}
