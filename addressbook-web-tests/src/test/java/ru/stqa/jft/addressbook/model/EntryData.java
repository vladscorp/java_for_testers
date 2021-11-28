package ru.stqa.jft.addressbook.model;

public class EntryData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String home;
    private final String mobile;
    private final String email;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String group;

    public EntryData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String mobile, String email, String bday, String bmonth, String byear, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.group = group;
    }

    public EntryData(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = null;
        this.lastname = lastname;
        this.nickname = null;
        this.title = null;
        this.company = null;
        this.address = null;
        this.home = null;
        this.mobile = null;
        this.email = null;
        this.bday = null;
        this.bmonth = null;
        this.byear = null;
        this.group = null;
    }

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

    public String getEmail() {
        return email;
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

        if (firstname != null ? !firstname.equals(entryData.firstname) : entryData.firstname != null) return false;
        return lastname != null ? lastname.equals(entryData.lastname) : entryData.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
