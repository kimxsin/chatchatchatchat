package com.ohgiraffers.semiproject.adoption.controller;

import com.ohgiraffers.semiproject.adoption.model.dto.AdoptionDTO;
import com.ohgiraffers.semiproject.adoption.model.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdoptionController {

    private final AdoptionService adoptionService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService){
        this.adoptionService = adoptionService;
    }

    // 입양 페이지로 이동
    @GetMapping("/sidemenu/adoption")
    public String adoption(Model model){

        List<AdoptionDTO> adoptingList = adoptionService.adopting();
        model.addAttribute("adoptingList", adoptingList);

        List<AdoptionDTO> adoptCompletedList = adoptionService.adoptCompleted();
        model.addAttribute("adoptCompletedList", adoptCompletedList);

        List<AdoptionDTO> adoptCanceledList = adoptionService.adoptCanceled();
        model.addAttribute("adoptCanceledList", adoptCanceledList);

        return "sidemenu/adoption/adoption";
    }
}
