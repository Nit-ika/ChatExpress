package com.mycompany.tcs.dao;

import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.model.Forumques;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Simran
 */
public interface ForumQuesDAO {
    void addQues(Forumques ans);
    void editQues(Forumques ans);
    void deleteQues(int quesId);
    Forumques findQuesbyId(int quesId);    
    List<Forumques> getAllQuestions();  
    Collection<Forumanswer> getAllAnswersByQuestion(Forumques qid);
}
