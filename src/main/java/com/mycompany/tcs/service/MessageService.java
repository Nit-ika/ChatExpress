package com.mycompany.tcs.service;

import com.mycompany.tcs.model.Messages;


public interface MessageService
{
    Messages findMessageByEId(int empId);
    String senderEids(Messages msg);
}

