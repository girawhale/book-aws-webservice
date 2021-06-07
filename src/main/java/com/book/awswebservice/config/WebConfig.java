package com.book.awswebservice.config;

import com.book.awswebservice.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 스프링에서 인식될 수 있도록 추가. 다른 HandlerMethodArgumentResolver를 등록할 때 같은 방식으로 추가
       resolvers.add(loginUserArgumentResolver);
    }
}
