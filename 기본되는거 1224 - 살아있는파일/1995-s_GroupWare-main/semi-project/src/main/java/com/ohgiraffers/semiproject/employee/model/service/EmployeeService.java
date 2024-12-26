package com.ohgiraffers.semiproject.employee.model.service;

import com.ohgiraffers.semiproject.board.model.dto.BoardDTO;
import com.ohgiraffers.semiproject.employee.model.dao.EmployeeMapper;
import com.ohgiraffers.semiproject.employee.model.dto.CommentDTO;
import com.ohgiraffers.semiproject.employee.model.dto.EmployeeDTOJOB;
import com.ohgiraffers.semiproject.employee.model.dto.EmployeeJoinListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTOJOB> empAll(int offset, int size) {

        List<EmployeeDTOJOB> result = employeeMapper.empAll(offset, size);

        return result;
    }

    public EmployeeDTOJOB empSelect(Integer empCode) {

        return employeeMapper.empSelect(empCode);
    }
    @Transactional
    public void saveComment(CommentDTO commentDTO) {
        System.out.println("Saving comment with empCode: " + commentDTO.getEmpCode());  // 로그 추가
        employeeMapper.saveComment(commentDTO);
    }

    public List<CommentDTO> comment(Integer empCode) {

        return employeeMapper.comment(empCode);  // Mapper에서 댓글 목록 가져오기
    }


    public long getTotalProducts() {

        return employeeMapper.countAll();
    }

    public List<EmployeeDTOJOB> empSearch(String query, int offset, int size) {

        List<EmployeeDTOJOB> result = employeeMapper.empSearch(query, offset, size);

        return result;
    }

    public long getTotalProducts1(String query) {

        return employeeMapper.countAll1(query);
    }

    public List<EmployeeJoinListDTO> empAllSelect() {
        return employeeMapper.empAllSelect();
    }

    @Transactional
    public void commentDelete(CommentDTO commentDTO) {

        employeeMapper.commentDelete(commentDTO);
    }
}
