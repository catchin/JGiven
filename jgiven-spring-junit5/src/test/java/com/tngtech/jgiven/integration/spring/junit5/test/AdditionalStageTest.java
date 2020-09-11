package com.tngtech.jgiven.integration.spring.junit5.test;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import com.tngtech.jgiven.integration.spring.junit5.SimpleSpringScenarioTest;
import com.tngtech.jgiven.integration.spring.junit5.config.TestSpringConfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration( classes = TestSpringConfig.class )
public class AdditionalStageTest extends SimpleSpringScenarioTest<SimpleTestSpringSteps> {

    @ScenarioStage
    AdditionalStage additionalStage;

    @Test
    public void beans_are_injected_in_additional_stages() {
        given().a_step_that_is_a_spring_component();
        when().methods_on_this_component_are_called();
        additionalStage.then().beans_are_injected();
    }

    @JGivenStage
    static class AdditionalStage extends Stage<AdditionalStage> {

        @Autowired
        TestBean testBean;

//        AdditionalStage(TestBean testBean) {
//            this.testBean = testBean;
//        }

        public void beans_are_injected() {
            Assertions.assertThat( testBean ).isNotNull();
        }
    }
}
