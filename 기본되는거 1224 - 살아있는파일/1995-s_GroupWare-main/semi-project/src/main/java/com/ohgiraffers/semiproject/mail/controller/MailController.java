package com.ohgiraffers.semiproject.mail.controller;

import com.ohgiraffers.semiproject.employee.model.dto.EmployeeJoinListDTO;
import com.ohgiraffers.semiproject.employee.model.service.EmployeeService;
import com.ohgiraffers.semiproject.mail.model.dto.MailDTO;
import com.ohgiraffers.semiproject.mail.model.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class MailController {

    private final MailService mailService;
    private final EmployeeService employeeService;

    public MailController(MailService mailService, EmployeeService employeeService) {
        this.mailService = mailService;
        this.employeeService = employeeService;
    }

    @GetMapping("/sidemenu/mail")
    public String mailAllSelect(Model model) {
        List<MailDTO> mail = mailService.mailAllSelect();

        System.out.println("mail = " + mail);

        model.addAttribute("mail", mail);

        System.out.println("mail = " + mail);

        return "sidemenu/mail/mail";

    }
    // 직원정보 보내기
    @GetMapping("api/employee")
    public ResponseEntity<List<EmployeeJoinListDTO>> employee() {

        List<EmployeeJoinListDTO> employeeList = employeeService.empAllSelect();

        return ResponseEntity.ok(employeeList);
    }

//    @GetMapping("/sidemenu/mail/regist")
//    public void mailRegist(){}
//
//    @PostMapping("/sidemenu/mail/regist")
//    public String registMail(@ModelAttribute MailDTO mailDTO) {
//
//        Date now = new Date();
//
//        mailDTO.setSendDate(now);
//
//        mailService.registMail(mailDTO);
//
//        return "redirect:/sidemenu/mail";
//    }
}
