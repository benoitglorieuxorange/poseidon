package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class CustomUserDetailsTest {

    @Test
    void exposesUserCredentialsAndRoleAuthority() {
        User user = new User();
        user.setUsername("alice");
        user.setPassword("encoded-password");
        user.setRole("admin");

        CustomUserDetails userDetails = new CustomUserDetails(user);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        assertThat(userDetails.getUsername()).isEqualTo("alice");
        assertThat(userDetails.getPassword()).isEqualTo("encoded-password");
        assertThat(authorities).extracting(GrantedAuthority::getAuthority).containsExactly("ROLE_ADMIN");
    }

    @Test
    void accountFlagsAreAlwaysTrue() {
        CustomUserDetails userDetails = new CustomUserDetails(new User());

        assertThat(userDetails.isAccountNonExpired()).isTrue();
        assertThat(userDetails.isAccountNonLocked()).isTrue();
        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
        assertThat(userDetails.isEnabled()).isTrue();
    }
}
