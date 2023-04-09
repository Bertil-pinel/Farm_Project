package fr.univamu.iut.api_cart.UserNProduct;;

import java.util.Date;

public class User {

    protected String username;

    protected String mail;

    protected String password;

    protected String dateOfCreation;

    public User(String username, String mail, String password, String dateOfCreation) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.dateOfCreation = dateOfCreation;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
