package com.mycompany.tcs.dao;
import com.mycompany.tcs.model.Messages;

public interface MessageDAO 
{
    Messages findMessageByEId(int empId);
    String senderEids(Messages msg);
}
