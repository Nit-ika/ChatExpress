package com.mycompany.tcs.dao;

import com.mycompany.tcs.model.Adminorg;
import com.mycompany.tcs.model.Employees;
import java.util.Collection;
import java.util.List;

public interface AdminDAO {
    void addAdmin(Adminorg admin);
    void editAdmin(Adminorg admin);
    void deleteAdmin(int adminId);
    Adminorg findAdminById(int adminId);
    List<Adminorg> getAllAdmins();
    Collection<Employees> getAllEmployees(int adminId);
}
