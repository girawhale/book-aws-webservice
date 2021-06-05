package com.book.awswebservice.web;

import com.book.awswebservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController // JSON을 반환하는 컨트롤러. @ResponseBody를 각 메소드마다 선언했던 작업을 한번에!
public class HelloController {

    @GetMapping("/hello") // HTTP Method인 Get 요청을 받을 수 있는 API를 만듦
//    @RequestMapping(method = RequestMethod.GET) 동일
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            // @RequestParam 외부에서 넘긴 API로 넘긴 파라미터를 가져오는 어노테이션
            @RequestParam("name") String name,
            @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
