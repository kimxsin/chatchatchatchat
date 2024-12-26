package com.ohgiraffers.semiproject.mail.model.service;

import com.ohgiraffers.semiproject.mail.model.dao.MailMapper;
import com.ohgiraffers.semiproject.mail.model.dto.MailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    private final MailMapper mailMapper;

    public MailService(MailMapper mailMapper) {
        this.mailMapper = mailMapper;
    }

    public List<MailDTO> mailAllSelect() {

        return mailMapper.mailAllSelect();
    }

//    public void registMail(MailDTO mailDTO) {
//
//        mailMapper.registMail(mailDTO);
//    }
}
