package com.book.awswebservice.web;

import com.book.awswebservice.config.auth.LoginUser;
import com.book.awswebservice.config.auth.dto.SessionUser;
import com.book.awswebservice.service.posts.PostsService;
import com.book.awswebservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
                        @LoginUser SessionUser user) { // 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 받아옴
        model.addAttribute("posts", postsService.findAllDesc());

//        // 로그인 된 유저는 세션에 SessionUser를 저장. 성공시 가져올 수 있다.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index"; // View Resolver에 의해 앞에는 src/main/resources/templates, 뒤에는 .mustache가 붙는다다
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // posts-save.mustache 로 이동
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update"; // posts-save.mustache 로 이동
    }

}
