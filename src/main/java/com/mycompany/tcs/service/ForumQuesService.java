/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.service;

import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.model.Forumques;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author shivangi
 */
public interface ForumQuesService 
{
    void addQues(Forumques ans);
    void editQues(Forumques ans);
    void deleteQues(int quesId);
    Forumques findQuesbyId(int quesId);    
    List<Forumques> getAllQuestions();  
    Collection<Forumanswer> getAllAnswersByQuestion(Forumques qid);
}
