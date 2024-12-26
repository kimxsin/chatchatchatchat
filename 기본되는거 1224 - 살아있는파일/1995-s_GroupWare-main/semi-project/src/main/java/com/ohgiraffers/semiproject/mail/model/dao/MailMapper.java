package com.ohgiraffers.semiproject.mail.model.dao;

import com.ohgiraffers.semiproject.mail.model.dto.MailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailMapper {
    List<MailDTO> mailAllSelect();

//    void registMail(MailDTO mailDTO);
}
