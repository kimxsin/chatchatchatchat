package com.ohgiraffers.semiproject.adoption.model.dao;

import com.ohgiraffers.semiproject.adoption.model.dto.AdoptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdoptionMapper {
    // 입양진행중 조회
    List<AdoptionDTO> adopting();
    // 입양완료 조회
    List<AdoptionDTO> adoptCompleted();
    // 입양취소 업데이트
    List<AdoptionDTO> adoptCanceled();
}
