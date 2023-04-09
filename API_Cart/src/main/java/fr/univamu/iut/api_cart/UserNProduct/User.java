package fr.univamu.iut.api_cart.UserNProduct;;

/**
 * Classe représentant les utilisateurs
 */

public class User {

    /**
     * Nom de l'utilisateur
     */
    protected String username;

    /**
     * le mail et l'id de l'utilisateur
     */
    protected String mail;

    /**
     * Le mot de passe de l'utilisateur
     */
    protected String password;

    /**
     * La date de création du compte de l'utilisateur
     */
    protected String dateOfCreation;

    /**
     * Constructeur de l'utilisateur
     * @param username String nom de l'utilisateur
     * @param mail String mail et l'id de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @param dateOfCreation date de création du compte de l'utilisateur
     */
    public User(String username, String mail, String password, String dateOfCreation) {
        this.username = username;
        this.mail = mail;
        this.password = password;
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
