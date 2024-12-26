package com.ohgiraffers.semiproject.approval.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApprovalController {

    // 결재 페이지로 이동
    @GetMapping("/sidemenu/approval")
    public String approval() {
        return "sidemenu/approval/approval";
    }
}
