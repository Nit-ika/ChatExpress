/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.service.Impl;

import com.mycompany.tcs.dao.EmployeeDAO;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.model.Notifications;
import com.mycompany.tcs.service.EmployeeService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeDAO empdao;

    @Override
    @Transactional
    public void addEmployees(Employees emp) 
    {
        empdao.addEmployees(emp);
    }

    @Override
    @Transactional
    public void editEmployees(Employees emp) 
    {
            System.out.println("service edit 1");
        empdao.editEmployees(emp);
            System.out.println("service edit 2");
    }

    @Override
    @Transactional
    public void deleteEmployees(int empId) 
    {
        empdao.deleteEmployees(empId);
    }

    @Override
    @Transactional
    public int EmployeeStatusById(int empId) 
    {
        return empdao.EmployeeStatusById(empId);
    }

    @Override
    @Transactional
    public Employees findEmployeeById(int empId) 
    {
        return empdao.findEmployeeById(empId);
    }

    @Override
    @Transactional
    public Employees findEmployeeByName(String Name) 
    {
        return empdao.findEmployeeByName(Name);
    }

    @Override
    @Transactional
    public Collection<Notifications> getEmployeeNotifications(int empId) 
    {
        return empdao.getEmployeeNotifications(empId);
    }

    @Override
    @Transactional
    public Collection<Notifications> getEmployeeGeneratedNotifications(int empId) 
    {
        return empdao.getEmployeeGeneratedNotifications(empId);
    }

    @Override
    @Transactional
    public Collection<Messages> getEmployeeMessages(int empId) 
    {
        return empdao.getEmployeeMessages(empId);
    }

    @Override
    @Transactional
    public Collection<Messages> getEmployeeSentMessages(int empId) 
    {
        return empdao.getEmployeeSentMessages(empId);
    }

    @Override
    @Transactional
    public List<Employees> getAllEmployees() 
    {        
        return empdao.getAllEmployees();
    }    

    @Override
    public void logoutEmployee(int empId) 
    {
        empdao.logoutEmployee(empId);
    }
}