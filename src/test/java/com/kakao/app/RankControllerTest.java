package com.kakao.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.app.Entity.UserEntity;
import com.kakao.app.Repository.UserRepository;
import com.kakao.app.dto.RankRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RankControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; //직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void MockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        userRepository.deleteAll();
    }


    @DisplayName("addUser: 유저정보를 DB에 저장")
    @Test
    public void addUser() throws Exception {
        //given 요청 객체 생성
        final String url = "/api/gameover";
        final Long userId = 1L;
        final String nickname = "nickname";
        final Long time = 254L;
        final RankRequest userRequest = new RankRequest(userId, nickname, time);

        //객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        //설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<UserEntity> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUserId()).isEqualTo(userId);
        assertThat(users.get(0).getNickname()).isEqualTo(nickname);
        assertThat(users.get(0).getTime()).isEqualTo(time);
    }
}
