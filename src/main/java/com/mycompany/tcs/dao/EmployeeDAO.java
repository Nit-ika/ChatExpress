package com.mycompany.tcs.dao;

import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.model.Notifications;
import java.util.Collection;
import java.util.List;

public interface EmployeeDAO {
    void addEmployees(Employees emp);
    void editEmployees(Employees emp);
    void deleteEmployees(int empId);
    int EmployeeStatusById(int empId);
    void logoutEmployee(int empId);
    Employees findEmployeeById(int empId);
    Employees findEmployeeByName(String Name);
    Collection<Notifications> getEmployeeNotifications(int empId);
    Collection<Notifications> getEmployeeGeneratedNotifications(int empId);
    Collection<Messages> getEmployeeMessages(int empId);
    Collection<Messages> getEmployeeSentMessages(int empId);
    List<Employees> getAllEmployees();
}
