/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.service.Impl;

import com.mycompany.tcs.dao.ForumQuesDAO;
import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.model.Forumques;
import com.mycompany.tcs.service.ForumQuesService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumQuesServiceImpl implements ForumQuesService
{ 
    @Autowired
    private ForumQuesDAO fquesdao;
    
    @Override
    @Transactional
    public void addQues(Forumques ans) {
       fquesdao.addQues(ans);    }

    @Override
    @Transactional
    public void editQues(Forumques ans) {
        fquesdao.editQues(ans);
    }

    @Override
    @Transactional
    public void deleteQues(int quesId) {
          fquesdao.deleteQues(quesId);
    }

    @Override
    @Transactional
    public Forumques findQuesbyId(int quesId) {
         return fquesdao.findQuesbyId(quesId) ;   
    }

    @Override
    @Transactional
    public List<Forumques> getAllQuestions() {
         return fquesdao.getAllQuestions();
    }

    @Override
    @Transactional
    public Collection<Forumanswer> getAllAnswersByQuestion(Forumques qid) {
               return fquesdao.getAllAnswersByQuestion(qid);
    }
}
