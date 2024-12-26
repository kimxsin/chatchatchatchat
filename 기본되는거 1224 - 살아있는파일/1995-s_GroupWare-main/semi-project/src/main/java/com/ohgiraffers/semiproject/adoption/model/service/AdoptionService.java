package com.ohgiraffers.semiproject.adoption.model.service;

import com.ohgiraffers.semiproject.adoption.model.dao.AdoptionMapper;
import com.ohgiraffers.semiproject.adoption.model.dto.AdoptionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {

    private final AdoptionMapper adoptionMapper;

    public AdoptionService(AdoptionMapper adoptionMapper){
        this.adoptionMapper = adoptionMapper;
    }

    public List<AdoptionDTO> adopting() {
        return adoptionMapper.adopting();
    }

    public List<AdoptionDTO> adoptCompleted() {
        return  adoptionMapper.adoptCompleted();
    }

    public List<AdoptionDTO> adoptCanceled() {
        return adoptionMapper.adoptCanceled();
    }
}
