package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CurvePointRepositoryTest {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    void curvePointCrudOperations_workAsExpected() {
        CurvePoint curvePoint = new CurvePoint(null, 10, null, 1.5, 2.5, null);

        CurvePoint saved = curvePointRepository.save(curvePoint);

        assertThat(saved.getId()).isNotNull();
        assertThat(curvePointRepository.findById(saved.getId())).isPresent();
        assertThat(curvePointRepository.findById(saved.getId()).get().getCurveId()).isEqualTo(10);

        curvePointRepository.deleteById(saved.getId());

        assertThat(curvePointRepository.findById(saved.getId())).isNotPresent();
    }
}
