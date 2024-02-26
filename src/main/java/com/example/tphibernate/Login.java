package com.example.tphibernate;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@ManagedBean(name = "loginBean")
@SessionScoped
@Entity
public class Login implements Serializable {
    @Id
    private int id;
    private String username;
    private String password;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String user_name) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean validate() {
        try {
            System.out.println("user name " + username);
            System.out.println("password " + password);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Login where username = :username and password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);

            List list = query.list();
            System.out.println("list size " + list.size());

            session.getTransaction().commit();
            session.close();

            return list.size() == 1;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public Login() {
    }
}