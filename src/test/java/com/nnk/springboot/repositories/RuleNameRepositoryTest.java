package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RuleNameRepositoryTest {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    void ruleNameCrudOperations_workAsExpected() {
        RuleName ruleName = new RuleName(null, "name", "description", "json", "template", "sqlStr", "sqlPart");

        RuleName saved = ruleNameRepository.save(ruleName);

        assertThat(saved.getId()).isNotNull();
        assertThat(ruleNameRepository.findById(saved.getId())).isPresent();
        assertThat(ruleNameRepository.findById(saved.getId()).get().getName()).isEqualTo("name");

        ruleNameRepository.deleteById(saved.getId());

        assertThat(ruleNameRepository.findById(saved.getId())).isNotPresent();
    }
}
