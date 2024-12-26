package com.ohgiraffers.semiproject.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    // 마이페이지 페이지로 이동
    @GetMapping("/sidemenu/mypage")
    public String mypage() {
        return "sidemenu/mypage/mypage";
    }
}
