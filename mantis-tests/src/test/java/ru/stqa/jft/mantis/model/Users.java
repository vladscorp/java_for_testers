package ru.stqa.jft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class Users {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "username")
    public String name;

    @Column(name = "email")
    public String email;


    public int getId() {
        return id;
    }

    public Users withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Users withEmail(String email) {
        this.email = email;
        return this;
    }


}
