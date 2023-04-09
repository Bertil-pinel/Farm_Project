package fr.univamu.fr.api_userproduct;

import java.util.Date;

/**
 * Class representing a User
 */

public class User {

    /**
     * User name
     */
    protected String username;

    /**
     * Mail used for emails and identifier
     */
    protected String mail;
    /**
     * Password of the User's account
     */
    protected String password;

    /**
     * Specifies when the account was created
     */
    protected String dateOfCreation;

    /**
     * Constructor
     * @param username
     * @param mail
     * @param password
     * @param dateOfCreation
     */

    public User(String username, String mail, String password, String dateOfCreation) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.dateOfCreation = dateOfCreation;
    }

    /**
     * Return the username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the email
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     * Returns the password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the date of creation
     * @return
     */
    public String getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Modifies the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Modifies the mail
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Modifies the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the User class as a string
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", dateOfCreation='" + dateOfCreation +
                "'}";
    }
}
