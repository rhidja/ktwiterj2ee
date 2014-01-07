/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ktwtr.models.Person;

/**
 *
 * @author ram
 */
@Entity
@Table(name="tb_user")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String pwd;
    @OneToOne
    private Person person;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public static List<User> getUsers(){
        return Ebean.find(User.class).findList();
    }
}
