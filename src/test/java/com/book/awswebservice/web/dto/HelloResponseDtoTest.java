package com.book.awswebservice.web.dto;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test() throws Exception {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        // assertThat? 테스트 검증 라이브러리의 메소드. 검증하고 싶은 대상을 메소드 인자로 받음. 메소드 체이닝이 지원
        assertThat(dto.getName()).isEqualTo(name); // isEqualTo()? 동등 비교 메소드. 같을 때만 성공
        assertThat(dto.getAmount()).isEqualTo(amount);
        // JUnit의 assertThat이 아닌 assertj의 assertThat을 사용
        // 장점? CoreMatchers와 같은 추가적인 라이브러리가 필요하지 않음. 자동완성이 더 확실히 지원
    }

}