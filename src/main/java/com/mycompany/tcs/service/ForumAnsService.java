/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.service;

import com.mycompany.tcs.model.Forumanswer;
import java.util.List;

public interface ForumAnsService {
    void addAnswer(Forumanswer ans);
    void editAnswer(Forumanswer ans);
    void deleteAnswer(int ansId);
    Forumanswer findAnswerById(int ansId);
    void editAnswerRate(int ansId,int rate);
    int getAnwerRate(int ansId);
    List<Forumanswer> getAllAnswers();   
}
