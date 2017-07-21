package com.mycompany.tcs.dao;

import com.mycompany.tcs.model.Forumanswer;
import java.util.List;

public interface ForumAnsDAO {
    void addAnswer(Forumanswer ans);
    void editAnswer(Forumanswer ans);
    void deleteAnswer(int ansId);
    Forumanswer findAnswerById(int ansId);
    void editAnswerRate(int ansId,int rate);
    int getAnwerRate(int ansId);
    List<Forumanswer> getAllAnswers();   
}
