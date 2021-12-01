package ru.stqa.jft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class EntryData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "middlename")
    private String middlename;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Transient
    private String allPhones;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmails;
    @Expose
    @Transient
    private String bday;
    @Expose
    @Transient
    private String bmonth;
    @Expose
    @Transient
    private String byear;

    @Transient
    private String group;

    //@Column(name="photo")
    //@Type(type = "text")
    //private String photo;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }
/*
    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }
*/
    public EntryData withId(int id) {
        this.id = id;
        return this;
    }

    public EntryData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public EntryData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public EntryData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public EntryData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public EntryData withTitle(String title) {
        this.title = title;
        return this;
    }

    public EntryData withCompany(String company) {
        this.company = company;
        return this;
    }

    public EntryData withAddress(String address) {
        this.address = address;
        return this;
    }

    public EntryData withHome(String home) {
        this.home = home;
        return this;
    }

    public EntryData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public EntryData withWork(String work) {
        this.work = work;
        return this;
    }

    public EntryData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public EntryData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public EntryData withEmail(String email) {
        this.email = email;
        return this;
    }

    public EntryData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public EntryData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public EntryData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public EntryData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public EntryData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public EntryData withGroup(String group) {
        this.group = group;
        return this;
    }
/*
    public EntryData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }*/

    @Override
    public String toString() {
        return "EntryData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryData entryData = (EntryData) o;

        if (id != entryData.id) return false;
        if (firstname != null ? !firstname.equals(entryData.firstname) : entryData.firstname != null) return false;
        return lastname != null ? lastname.equals(entryData.lastname) : entryData.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
