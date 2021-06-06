package com.book.awswebservice.domain.posts; // Entity와 Repository는 함께 움직여야하기 때문에 같은 패키지

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DB Layer 접근자
// @Repository 를 추가할 필요가 없다
public interface PostsRepository extends JpaRepository<Posts, Long> { // <Entity 클래스, PK 타입>

//    @Query("select p from Posts p order by p.id desc") 동일!
    List<Posts> findAllByOrderByIdDesc();
}
