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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ktwtr.models.Person;

/**
 *
 * @author ram
 */
@Entity
@Table(name="tb_user")
public class User{
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String pwd;
    @OneToMany
    private List<Person> persons;

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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
    public static List<User> getUsers(){
        return Ebean.find(User.class).findList();
    }
    
    public static User getUser(String login){
        return Ebean.find(User.class).where().eq("login", login).findUnique();
    }
}
