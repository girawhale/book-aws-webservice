package com.book.awswebservice.web.dto;

import com.book.awswebservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    // 사용하는 값만 필드로 적는다
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
