package com.mycompany.tcs.dao.impl;


import com.mycompany.tcs.dao.MessageDAO;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.service.EmployeeService;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MessageDAOImpl implements MessageDAO 
{
    @Autowired 
    private SessionFactory session;
    
    @Autowired
    private EmployeeService empservice;
    
    @Override
    @Transactional
    public Messages findMessageByEId(int empId) 
    {
        Criteria criteria = session.getCurrentSession().createCriteria(Messages.class);
	criteria.add(Restrictions.eq("receiveEid",empservice.findEmployeeById(empId)));		
	return (Messages) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public String senderEids(Messages msg) 
    {
        String sql = "select sendEids from Messages where mid = :mid ";
        Query query = session.getCurrentSession().createQuery(sql);
        query.setParameter("mid", msg.getMid());
        
        List result = query.list();
        return result.get(0).toString();
    }
}