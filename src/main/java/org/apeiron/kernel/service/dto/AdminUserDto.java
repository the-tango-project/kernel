package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.validation.constraints.*;

import org.apeiron.kernel.configuration.Constants;
import org.apeiron.kernel.domain.Authority;
import org.apeiron.kernel.domain.User;

/**
 * The `AdminUserDto` class in Java represents a data transfer object for
 * administrative user information with fields such as login, name, email,
 * activation status, and authorities.
 */
public class AdminUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    /**
     * 
     * The above code is a Java constructor for a class named `AdminUserDto`. It
     * is a default constructor
     * with no parameters, and the comment indicates that it is needed for
     * Jackson, which is a popular Java
     * library for JSON serialization and deserialization. This constructor is
     * likely used by Jackson to
     * create instances of the `AdminUserDto` class when converting JSON data to
     * Java objects.
     * 
     */

    public AdminUserDto() {
        // Empty constructor needed for Jackson.
    }

    /**
     * This constructor `public AdminUserDto(User user)` in the `AdminUserDto` class
     * is used to create an `AdminUserDto` object from a `User` object. It
     * initializes the fields of the `AdminUserDto` object by extracting the
     * corresponding values from the provided `User` object.
     * 
     * @param user
     */
    public AdminUserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.isActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
    }

    /**
     * The `getId` function in Java returns the value of the `id` variable.
     * 
     * @return The method `getId()` is returning the value of the variable `id`.
     */
    public String getId() {
        return id;
    }

    /**
     * The setId function in Java sets the value of the id attribute for an object.
     * 
     * @param id The `setId` method takes a `String` parameter named `id` and sets
     *           the value of the
     *           instance variable `id` to the value passed as the parameter.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The `getLogin` function in Java returns the login value.
     * 
     * @return The method `getLogin()` is returning the value of the variable
     *         `login`.
     */
    public String getLogin() {
        return login;
    }

    /**
     * The function `setLogin` sets the value of the `login` variable in a Java
     * class.
     * 
     * @param login The `setLogin` method is used to set the value of the `login`
     *              property in a class. The
     *              `login` parameter is the value that will be assigned to the
     *              `login` property.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * The getFirstName() function in Java returns the value of the firstName
     * variable.
     * 
     * @return The method `getFirstName()` is returning the value of the variable
     *         `firstName`.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The function sets the first name of an object to the provided value.
     * 
     * @param firstName The parameter `firstName` is a String type parameter that
     *                  represents the first name
     *                  of a person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * The `getLastName` function in Java returns the last name of an object.
     * 
     * @return The `lastName` variable is being returned.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The function sets the last name of an object to the provided value.
     * 
     * @param lastName The parameter `lastName` is a String type parameter that
     *                 represents the last name of
     *                 a person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * The getEmail() function in Java returns the email address.
     * 
     * @return The getEmail() method is returning the email address as a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The setEmail function in Java sets the email instance variable to the
     * provided email value.
     * 
     * @param email The `setEmail` method is used to set the email address for a
     *              particular object. The
     *              `email` parameter is the new email address that will be assigned
     *              to the object's `email` attribute.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The `getImageUrl` function in Java returns the image URL.
     * 
     * @return The method `getImageUrl()` is returning the `imageUrl` variable.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * The function `setImageUrl` sets the image URL for an object in Java.
     * 
     * @param imageUrl The `imageUrl` parameter is a String type that represents the
     *                 URL of an image. The
     *                 `setImageUrl` method sets the value of the `imageUrl`
     *                 instance variable to the provided URL.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * The `isActivated` function in Java returns the value of the `activated`
     * boolean variable.
     * 
     * @return The method `isActivated()` returns the value of the `activated`
     *         variable.
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * This function sets the "activated" status of an object in Java.
     * 
     * @param activated The `activated` parameter is a boolean variable that
     *                  indicates whether an object is
     *                  currently activated or not. It is used in the `setActivated`
     *                  method to update the activation status
     *                  of an object.
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * The `getLangKey` function in Java returns the value of the `langKey`
     * variable.
     * 
     * @return The method `getLangKey()` is returning the value of the variable
     *         `langKey`.
     */
    public String getLangKey() {
        return langKey;
    }

    /**
     * The function `setLangKey` sets the language key for an object in Java.
     * 
     * @param langKey The `setLangKey` method is a setter method used to set the
     *                value of the `langKey`
     *                instance variable in a class. The `langKey` parameter is the
     *                value that will be assigned to the
     *                `langKey` instance variable when this method is called.
     */
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    /**
     * This Java function returns the value of the "createdBy" attribute.
     * 
     * @return The method `getCreatedBy()` is returning the value of the variable
     *         `createdBy`, which is
     *         likely a String representing the creator or author of something.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * The function `setCreatedBy` sets the value of the `createdBy` attribute in a
     * Java class.
     * 
     * @param createdBy The `setCreatedBy` method is used to set the value of the
     *                  `createdBy` attribute in
     *                  a class. The `createdBy` parameter is a String type that
     *                  represents the user or entity who created
     *                  the object or record. By calling this method and passing a
     *                  String value, you can update the
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * The function `getCreatedDate()` returns the created date as an Instant
     * object.
     * 
     * @return The method `getCreatedDate()` is returning the `createdDate` field of
     *         type `Instant`.
     */
    public Instant getCreatedDate() {
        return createdDate;
    }

    /**
     * The function sets the created date of an object to the provided Instant
     * value.
     * 
     * @param createdDate The `setCreatedDate` method is used to set the value of
     *                    the `createdDate`
     *                    property in a class. The parameter `createdDate` is of
     *                    type `Instant`, which represents a point in
     *                    time. When you call this method with an `Instant` object,
     *                    it will set the `
     */
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * The getLastModifiedBy() function in Java returns the last user who modified
     * the data.
     * 
     * @return The method `getLastModifiedBy()` is returning the value of the
     *         variable `lastModifiedBy`.
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * The function `setLastModifiedBy` sets the value of the `lastModifiedBy`
     * attribute in a Java class.
     * 
     * @param lastModifiedBy The `setLastModifiedBy` method is used to set the value
     *                       of the
     *                       `lastModifiedBy` attribute in a class. This attribute
     *                       typically stores the information about the
     *                       user who last modified the object or entity.
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * The function `getLastModifiedDate` returns the last modified date as an
     * `Instant` object.
     * 
     * @return The method `getLastModifiedDate()` returns the `lastModifiedDate` of
     *         type `Instant`.
     */
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * The function `setLastModifiedDate` sets the last modified date of an object
     * to the provided Instant
     * value.
     * 
     * @param lastModifiedDate The `lastModifiedDate` parameter is of type
     *                         `Instant`, which represents a
     *                         point in time in the UTC time zone. It is used to
     *                         store the date and time when a particular entity
     *                         was last modified.
     */
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * The `getAuthorities` function returns a Set of Strings representing
     * authorities.
     * 
     * @return A Set of String values named "authorities" is being returned.
     */

    /**
     * The `getAuthorities` function in Java returns a Set of String values
     * representing authorities.
     * 
     * @return A Set of Strings named "authorities" is being returned.
     */
    public Set<String> getAuthorities() {
        return authorities;
    }

    /**
     * The function `setAuthorities` sets the authorities for a given object.
     * 
     * @param authorities The `setAuthorities` method is used to set the authorities
     *                    for a particular user.
     *                    The `authorities` parameter is a `Set` of `String` values
     *                    that represent the roles or permissions
     *                    assigned to the user. These authorities typically
     *                    determine what actions the user is allowed to
     *                    perform within an application.
     */
    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    /**
     * The above code is overriding the `toString()` method for a class named
     * `AdminUserDto`. This method
     * returns a string representation of the object's state by concatenating
     * various fields such as login,
     * firstName, lastName, email, imageUrl, activated, langKey, createdBy,
     * createdDate, lastModifiedBy,
     * lastModifiedDate, and authorities. The values of these fields are included in
     * the returned string
     * for debugging or logging purposes.
     * 
     */
    // prettier-ignore
    @Override
    public String toString() {
        return "AdminUserDto{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", activated=" + activated +
                ", langKey='" + langKey + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", authorities=" + authorities +
                "}";
    }
}
