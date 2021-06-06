package com.book.awswebservice.domain.posts;

import com.book.awswebservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Setter가 없다! Entity 클래스에서는 절대 만들지 않는다. 언제 변경되었는지 알 수 없기 때문. 필요에 따라 메소드를 추가
@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스. 카멜케이스를 언더스코어 네이밍으로 매칭
public class Posts extends BaseTimeEntity {
    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙. IDENTITY로 설정해야 auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 선언하지 않아도 모든 필드는 칼럼임. 기본값이 아닌 변경할 옵션이 있다면 작성
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성. 생성자에 선언시 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
