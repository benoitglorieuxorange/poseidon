package com.nnk.springboot.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * JPA entity representing a User in the system.
 * 
 * Maps to the users database table and contains user authentication and profile information.
 */
@Entity
@Table(name = "users")
public class User {
    /** Unique identifier for the user */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /** Username for login */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /** Encrypted password for the user */
    @NotBlank(message = "Password is mandatory")
    private String password;

    /** Full name of the user */
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    /** Role assigned to the user (e.g., ADMIN, USER) */
    @NotBlank(message = "Role is mandatory")
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
