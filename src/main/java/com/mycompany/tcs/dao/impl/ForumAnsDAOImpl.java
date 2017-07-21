package com.mycompany.tcs.dao.impl;

import com.mycompany.tcs.dao.ForumAnsDAO;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Forumanswer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ForumAnsDAOImpl implements ForumAnsDAO 
{

    @Autowired 
    private SessionFactory session;
    
    @Override
    @Transactional
    public void addAnswer(Forumanswer ans) 
    {
        session.getCurrentSession().save(ans);
    }

    @Override
    @Transactional
    public void editAnswer(Forumanswer ans) 
    {
        session.getCurrentSession().update(ans);
    }

    @Override
    @Transactional
    public void deleteAnswer(int ansId) 
    {
        session.getCurrentSession().delete(findAnswerById(ansId));
    }

    @Override
    @Transactional
    public Forumanswer findAnswerById(int ansId) 
    {
        return (Forumanswer) session.getCurrentSession().get(Forumanswer.class, ansId);
    }

    @Override
    @Transactional
    public void editAnswerRate(int ansId, int rate) 
    {
        String sql = "update Forumanswer set rate = :rate where ansId = :ansId";
        Query query = session.getCurrentSession().createQuery(sql);
        query.setParameter("rate", rate);
        query.setParameter("ansId", ansId);
        
        int result = query.executeUpdate();
        System.out.println(result);
    }

    @Override
    @Transactional
    public int getAnwerRate(int ansId) 
    {
        String sql = "select rate from Forumanswer where ansId = :ansId ";
        Query query = session.getCurrentSession().createQuery(sql);
        query.setParameter("ansId", ansId);
        
        List result = query.list();
        return Integer.parseInt(result.get(0).toString());
    }

    @Override
    @Transactional
    public List<Forumanswer> getAllAnswers() 
    {
        return (List<Forumanswer>) session.getCurrentSession().createCriteria(Forumanswer.class).list();
    }
}
