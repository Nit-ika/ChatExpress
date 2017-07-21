package com.mycompany.tcs.service.Impl;

import com.mycompany.tcs.dao.AdminDAO;
import com.mycompany.tcs.model.Adminorg;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.service.AdminorgService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminorgServiceImpl implements AdminorgService
{
    @Autowired 
    private AdminDAO admindao;

    @Override
    @Transactional
    public void addAdmin(Adminorg admin) 
    {
        admindao.addAdmin(admin);
    }

    @Override
    @Transactional
    public void editAdmin(Adminorg admin) 
    {
        admindao.editAdmin(admin);
    }

    @Override
    @Transactional
    public void deleteAdmin(int adminId) 
    {
        admindao.deleteAdmin(adminId);
    }

    @Override
    @Transactional
    public Adminorg findAdminById(int adminId) 
    {
        return admindao.findAdminById(adminId);
    }

    @Override
    @Transactional
    public List<Adminorg> getAllAdmins() 
    {
        return admindao.getAllAdmins();
    }

    @Override
    @Transactional
    public Collection<Employees> getAllEmployees(int adminId) 
    {
        return admindao.getAllEmployees(adminId);
    }
}