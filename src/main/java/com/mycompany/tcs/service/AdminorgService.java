/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.service;

import com.mycompany.tcs.model.Adminorg;
import com.mycompany.tcs.model.Employees;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author shivangi
 */
public interface AdminorgService {
    void addAdmin(Adminorg admin);
    void editAdmin(Adminorg admin);
    void deleteAdmin(int adminId);
    Adminorg findAdminById(int adminId);
    List<Adminorg> getAllAdmins();
    Collection<Employees> getAllEmployees(int adminId);
}
