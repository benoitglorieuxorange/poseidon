package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void userCrudOperationsAndFinder_workAsExpected() {
        User user = new User();
        user.setUsername("tester");
        user.setPassword("encoded-password");
        user.setFullname("Test User");
        user.setRole("ADMIN");

        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(userRepository.findById(saved.getId())).isPresent();
        assertThat(userRepository.findByUsername("tester")).isPresent();
        assertThat(userRepository.findByUsername("tester").get().getFullname()).isEqualTo("Test User");

        userRepository.deleteById(saved.getId());

        assertThat(userRepository.findById(saved.getId())).isNotPresent();
    }
}
