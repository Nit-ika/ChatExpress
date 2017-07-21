package com.mycompany.tcs.dao.impl;

import com.mycompany.tcs.dao.ForumQuesDAO;
import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.model.Forumques;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ForumQuesDAOImpl implements ForumQuesDAO 
{
    @Autowired 
    private SessionFactory session;
    
    @Override
    @Transactional
    public void addQues(Forumques ans) 
    {
        session.getCurrentSession().save(ans);
    }

    @Override
    @Transactional
    public void editQues(Forumques ans) 
    {
        session.getCurrentSession().update(ans);
    }

    @Override
    @Transactional
    public void deleteQues(int quesId) 
    {
        session.getCurrentSession().delete(findQuesbyId(quesId));
    }

    @Override
    @Transactional
    public Forumques findQuesbyId(int quesId) 
    {
        return (Forumques) session.getCurrentSession().get(Forumques.class, quesId);
    }

    @Override
    @Transactional
    public List<Forumques> getAllQuestions() 
    { 
        return (List<Forumques>) session.getCurrentSession().createCriteria(Forumques.class).list(); 
    }

    @Override
    @Transactional
    public List<Forumanswer> getAllAnswersByQuestion(Forumques qid) 
    {
        System.out.println("in getAllAnswersByQuestion 1 ");
        Query query = session.getCurrentSession().createQuery("from Forumanswer where qid = :quesId ");
        System.out.println("in getAllAnswersByQuestion 2 ");
        query.setParameter("quesId", qid);
        System.out.println("in getAllAnswersByQuestion 3 ");
        List<Forumanswer> ans = (List<Forumanswer>) query.list();
        System.out.println("in getAllAnswersByQuestion 4 "+ans.toString());
        
        return ans;
    }  
}