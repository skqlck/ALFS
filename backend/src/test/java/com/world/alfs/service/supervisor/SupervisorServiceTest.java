package com.world.alfs.service.supervisor;

import com.world.alfs.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;


@Transactional
class SupervisorServiceTest extends IntegrationTestSupport {

    @MockBean
    SupervisorService supervisorService;

    @DisplayName("원재료 ocr 추출 잘 가져오는지")
    @Test
    void getUrlIngredient() {

        // given


        // when, then
    }

}