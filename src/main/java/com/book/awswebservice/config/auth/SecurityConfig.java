package com.book.awswebservice.config.auth;

import com.book.awswebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 옵션을 disable

            .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점. 선언해야만 antMatchers 옵션 사용 가능
                .antMatchers("/", "/css/**", "/image/**", // 권한을 지정하는 옵션. URL, HTTP 메소드별 관리가 가능
                        "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1").hasRole(Role.USER.name()) // USER 권한을 가진 사람만 입장 가능
                .anyRequest().authenticated() // 설정 이외의 URL들. 인증된 사용자(로그인한 사용자)들에게만 허용

            .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정의 진입점

            .and()
                .oauth2Login() // OAuth2 로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService); // 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록.
                                                       // 리소스서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능
    }
}
