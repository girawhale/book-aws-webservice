package com.book.awswebservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링부트의 자동 설정, 스프링 Bean 자동 읽기와 생성을 자동으로 설정. 현재 위치부터 설정을 읽기 때문에 프로젝트 최상단에 위치해야 함
public class Application { // 프로젝트의 메인 클래스!
    public static void main(String[] args) {
        // 내장 WAS를 실행.
        // 내장 WAS? 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것
        //          톰캣을 설치할 필요가 없고 스프링 부트로 만들어진 jar파일을 실행하면 됨

        // Spring boot는 내장 WAS를 권장. 왜?? 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있음!
        // 외장 WAS를 사용하면 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야 함
        // 새로운 서버가 추가되면 모든 서버가 같은 WAS환경을 구축해야 함. 여러 대라면 실수가 존재할 수 있고, 시간도 많이 필요
        SpringApplication.run(Application.class, args);
    }
}
