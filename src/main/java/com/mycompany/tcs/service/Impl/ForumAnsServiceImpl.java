/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.service.Impl;

import com.mycompany.tcs.dao.ForumAnsDAO;
import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.service.ForumAnsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumAnsServiceImpl implements ForumAnsService{
     
    @Autowired
    private ForumAnsDAO fansdao;

    @Override
    @Transactional
    public void addAnswer(Forumanswer ans) 
    {
        fansdao.addAnswer(ans);
    }

    @Override
    @Transactional
    public void editAnswer(Forumanswer ans) 
    {
        fansdao.editAnswer(ans);
    }

    @Override
    @Transactional
    public void deleteAnswer(int ansId) 
    {
        fansdao.deleteAnswer(ansId);
    }

    @Override
    @Transactional
    public Forumanswer findAnswerById(int ansId) 
    {
        return fansdao.findAnswerById(ansId);
    }

    @Override
    @Transactional
    public void editAnswerRate(int ansId, int rate) 
    {
        fansdao.editAnswerRate(ansId, rate);
    }

    @Override
    @Transactional
    public int getAnwerRate(int ansId) 
    {
        return fansdao.getAnwerRate(ansId);
    }

    @Override
    @Transactional
    public List<Forumanswer> getAllAnswers() 
    {
        return fansdao.getAllAnswers();
    }      
}
