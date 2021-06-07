package com.book.awswebservice.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션 생성 가능 위치. 여기선 파라미터
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
