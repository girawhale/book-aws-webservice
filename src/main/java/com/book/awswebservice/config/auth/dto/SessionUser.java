package com.book.awswebservice.config.auth.dto;

import com.book.awswebservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
// 인증된 사용자 정보만 필요. 그외의 정보는 필요 없음
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
