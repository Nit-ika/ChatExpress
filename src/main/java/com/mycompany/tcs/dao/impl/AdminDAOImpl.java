package com.mycompany.tcs.dao.impl;

import com.mycompany.tcs.dao.AdminDAO;
import com.mycompany.tcs.model.Adminorg;
import com.mycompany.tcs.model.Employees;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdminDAOImpl implements AdminDAO 
{
    @Autowired 
    private SessionFactory session;
    
    @Override
    @Transactional
    public void addAdmin(Adminorg admin) 
    {
        session.getCurrentSession().save(admin);
    }

    @Override
    @Transactional
    public void editAdmin(Adminorg admin) 
    {
        session.getCurrentSession().update(admin);
    }

    @Override
    @Transactional
    public void deleteAdmin(int adminId) 
    {
        session.getCurrentSession().delete(findAdminById(adminId));
    }

    @Override
    @Transactional
    public Adminorg findAdminById(int adminId) 
    {
        return (Adminorg) session.getCurrentSession().get(Adminorg.class, adminId);
    }

    @Override
    @Transactional
    public List<Adminorg> getAllAdmins() 
    {
         return (List<Adminorg>) session.getCurrentSession().createCriteria(Adminorg.class).list();
    }

    @Override
    @Transactional
    public Collection<Employees> getAllEmployees(int adminId) 
    {
        Query query = session.getCurrentSession().createQuery("from Employees where adminEid = :adminId ");
        query.setParameter("adminId", adminId);
        
        return (Collection<Employees>) query.list();
    }    
}
