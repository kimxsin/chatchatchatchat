package com.ohgiraffers.semiproject.animals.model.dao;

import com.ohgiraffers.semiproject.animals.model.dto.AnimalDTO;
import com.ohgiraffers.semiproject.animals.model.dto.BreedDTO;
import com.ohgiraffers.semiproject.animals.model.dto.InventoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AnimalsMapper {

// -----------------------------------------구조동물 페이지-----------------------------------------

    // 구조동물 전체조회
    List<AnimalDTO> AllAnimalAndSearchAnimals(int offset, int limit, String animalCode, String typeCode, String breedCode, String gender, Date rescueDate);
    // 구조동물 전체조회 - 페이징처리(전체동물 카운트)
    int getTotalAnimalCount(String animalCode, String typeCode, String breedCode, String gender, Date rescueDate);

    // 구조동물 상세페이지
    AnimalDTO detailAnimal(String animalCode);
    AnimalDTO healthAnimal(String animalCode);
    AnimalDTO inoculationAnimal(String animalCode);

    // 동물등록
    void newAnimal(AnimalDTO typeAndBreedAndEmpAndAnimalDTO);
    // 동물등록 - 품종 비동기처리
    List<BreedDTO> findBreed();
    // 동물등록 - 동물등록번호 자동입력
    String findLastAnimalCode();

    // 삭제(체크박스 선택)
    void deleteBoard(String id);

    // 수정(입양완료로 상태 update)
    void adoptComplete(String code);

// -----------------------------------------입양완료 페이지-----------------------------------------
    // 입양완료동물 전체조회
    List<AnimalDTO> adoptAnimalList(Map<String, Integer> params);
    // 입양완료동물 전체조회 - 페이징처리
    int getTotalAdoptAnimalCount();

    // 입양완료 상세페이지
    AnimalDTO adoptionDetailAnimal(String animalCode);
    AnimalDTO adoptionHealthAnimal(String animalCode);
    AnimalDTO adoptionInoculationAnimal(String animalCode);

    // 파양으로 상태 수정
    void giveUp(List<String> codeList);

// -----------------------------------------재고관리 페이지-----------------------------------------
    List<InventoryDTO> stock();


    void inventoryUpdate(Map<String, Object> params);
}
