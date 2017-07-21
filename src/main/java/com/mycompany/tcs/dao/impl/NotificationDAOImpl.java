package com.mycompany.tcs.dao.impl;

import com.mycompany.tcs.dao.NotificationDAO;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Notifications;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NotificationDAOImpl implements NotificationDAO 
{
    @Autowired
    private SessionFactory session;

    @Override
    @Transactional
    public void addNotifications(Notifications not) 
    {
        session.getCurrentSession().save(not);
    }

    @Override
    @Transactional
    public void editNotifications(Notifications not) 
    {
        session.getCurrentSession().update(not);
    }

    @Override
    @Transactional
    public void deleteNotifications(int notId) 
    {
        session.getCurrentSession().delete(findNotificationbyId(notId));
    }

    @Override
    @Transactional
    public int getNotificationSenderId(int notId) 
    {
        String sql = "select genEid from Notifications where nid = :notId ";
        Query query = session.getCurrentSession().createQuery(sql);
        query.setParameter("notId", notId);
        
        List result = query.list();
        return Integer.parseInt(result.get(0).toString());
    }
    
    @Override
    @Transactional
    public Employees getNotificationSender(int notId) 
    {
        Query query = session.getCurrentSession().createQuery("from Employees where empId = :sendEid ");
        query.setParameter("sendEid", getNotificationSenderId(notId));
        
        return (Employees) query.list();    
    }
     
    @Override
    @Transactional
    public int getNotificationReceiverId(int notId) 
    {
        String sql = "select receiveEid from Notifications where nid = :notId ";
        Query query = session.getCurrentSession().createQuery(sql);
        query.setParameter("notId", notId);
        
        List result = query.list();
        return Integer.parseInt(result.get(0).toString());
    }

    @Override
    @Transactional
    public Employees getNotificationReceiver(int notId) 
    {
        Query query = session.getCurrentSession().createQuery("from Employees where empId = :receiveEid ");
        query.setParameter("receiveEid", getNotificationReceiverId(notId));
        
        return (Employees) query.list();    
    }

    @Override
    @Transactional
    public Notifications findNotificationbyId(int notId) 
    {    
        return (Notifications) session.getCurrentSession().get(Notifications.class, notId);
    }

    @Override
    @Transactional
    public List<Notifications> getAllNotifications() 
    {
        return (List<Notifications>) session.getCurrentSession().createCriteria(Notifications.class).list();
    }   
}