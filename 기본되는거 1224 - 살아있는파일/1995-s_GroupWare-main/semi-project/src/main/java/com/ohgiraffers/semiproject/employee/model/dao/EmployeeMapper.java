package com.ohgiraffers.semiproject.employee.model.dao;

import com.ohgiraffers.semiproject.employee.model.dto.CommentDTO;
import com.ohgiraffers.semiproject.employee.model.dto.EmployeeDTOJOB;
import com.ohgiraffers.semiproject.employee.model.dto.EmployeeJoinListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeDTOJOB> empAll(@Param("offset") int offset, @Param("size") int size);

    long countAll();

    List<EmployeeDTOJOB> empSearch(String query, @Param("offset") int offset,
                                                 @Param("size") int size);

    long countAll1(String query);

    EmployeeDTOJOB empSelect(Integer empCode);

    void saveComment(CommentDTO commentDTO);

    List<CommentDTO> comment(Integer empCode);

    List<EmployeeJoinListDTO> empAllSelect();

    void commentDelete(CommentDTO commentDTO);
}
