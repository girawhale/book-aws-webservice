package com.book.awswebservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // Entity 클래스가 상속된다면 필드로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 포함
public class BaseTimeEntity {

    @CreatedDate // 생성되어 저장될 때 시간이 자동으로 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 값 변경할 때 시간이 자동으로 저장
    private LocalDateTime modifiedDate;
}
