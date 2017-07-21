package com.mycompany.tcs.service.Impl;

import com.mycompany.tcs.dao.MessageDAO;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService
{
    @Autowired 
    private MessageDAO msgdao;

    @Override
    @Transactional
    public Messages findMessageByEId(int empId) 
    {
        return msgdao.findMessageByEId(empId);
    }

    @Override
    @Transactional
    public String senderEids(Messages msg) 
    {
        return msgdao.senderEids(msg);
    }
}