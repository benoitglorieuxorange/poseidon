package com.nnk.springboot.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "Password must be at least 8 characters and contain a digit, an uppercase letter and a special character"
    )
    private String password;

    /** Full name of the user */
    @NotBlank(message = "FullName is mandatory")
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
