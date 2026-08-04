package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void ratingCrudOperations_workAsExpected() {
        Rating rating = new Rating(null, "Moodys", "SandP", "Fitch", 10);

        Rating saved = ratingRepository.save(rating);

        assertThat(saved.getId()).isNotNull();
        assertThat(ratingRepository.findById(saved.getId())).isPresent();
        assertThat(ratingRepository.findById(saved.getId()).get().getOrderNumber()).isEqualTo(10);

        ratingRepository.deleteById(saved.getId());

        assertThat(ratingRepository.findById(saved.getId())).isNotPresent();
    }
}
