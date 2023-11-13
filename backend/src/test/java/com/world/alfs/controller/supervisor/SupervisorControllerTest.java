package com.world.alfs.controller.supervisor;

import com.world.alfs.ControllerTestSupport;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import com.world.alfs.service.supervisor.SupervisorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(controllers = {SupervisorController.class})
class SupervisorControllerTest extends ControllerTestSupport {

    final String URI = "/supervisor";
    @MockBean
    private SupervisorService supervisorService;
    @MockBean
    private SupervisorRepository supervisorRepository;

    @DisplayName("관리자는 image url에서 원재료명을 추출할 수 있다.")
    @Test
    void getUrlIngredient() throws Exception{
        // given

        // when

        // then
    }

    @DisplayName("관리자는 image file에서 원재료명을 추출할 수 있다.")
    @Test
    void getFileIngredient() throws  Exception{
        // given

        // when

        // then
    }

}