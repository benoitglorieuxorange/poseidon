package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Adapts the {@link User} entity to the {@link UserDetails} contract required by Spring Security.
 *
 * This class exposes the application's authentication and authorization data
 * in the format expected by the framework.
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Creates a security details object from a domain user.
     *
     * @param user the application user exposed to Spring Security
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities assigned to the user.
     *
     * This method converts the business role stored in the database into a
     * Spring Security authority prefixed with {@code ROLE_}.
     *
     * @return the collection of authorities granted to the authenticated user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
    }

    /**
     * Returns the encoded password of the user.
     *
     * This value is used by Spring Security to compare the submitted password
     * with the password stored in the database.
     *
     * @return the encoded password of the user
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used for authentication.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user account is still valid.
     *
     * The application does not manage account expiration, so this method
     * always returns {@code true}.
     *
     * @return {@code true} because the account is considered non-expired
     */
    @Override
    public boolean isAccountNonExpired() { return true; }

    /**
     * Indicates whether the user account is not locked.
     *
     * The application does not manage account locking, so this method always
     * returns {@code true}.
     *
     * @return {@code true} because the account is considered non-locked
     */
    @Override
    public boolean isAccountNonLocked() { return true; }

    /**
     * Indicates whether the user's credentials are still valid.
     *
     * The application does not manage credential expiration, so this method
     * always returns {@code true}.
     *
     * @return {@code true} because the credentials are considered valid
     */
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    /**
     * Indicates whether the user account is enabled.
     *
     * The application does not manage account disabling, so this method
     * always returns {@code true}.
     *
     * @return {@code true} because the user is considered enabled
     */
    @Override
    public boolean isEnabled() { return true; }
}