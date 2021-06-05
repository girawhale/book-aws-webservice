package com.book.awswebservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트 진행시 Junit 외에 내장된 실행자 외에 다른 실행자를 실행.
// 스프링 부트 테스트와 Junit 사이에 연결자 역할
@ExtendWith(SpringExtension.class) // Junit5.
// Web(Srping MVC)에 집중할 수 있는 어노테이션.
// 선언시 @Controller, @ControllerAdvice 등을 사용 가능. @Service, @Component, @Repository 등은 사용 불가
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입
    private MockMvc mvc; // 웹 API 테스트시 사용. 스프링 MVC 테스트의 시작점. API 테스트가 가능해짐

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        // 체이닝이 지원되어 검증을 이어할 수 있음.
        mvc.perform(get("/hello")) // MockMVC를 통해 '/hello' 로 GET요청을 보냄.
                .andExpect(status().isOk()) // 결과를 검증. HTTP header의 Status를 검증
                .andExpect(content().string(hello)); // 결과를 검증. 응답 본문의 내용을 검증
    }

    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) // 요청 파라미터를 설정
                .param("amount", String.valueOf(amount)) // String만 허용되기 때문에 문자열로 변경해야 함
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        // jsonPath
        // JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $를 기준으로 필드명을 명시
    }

}