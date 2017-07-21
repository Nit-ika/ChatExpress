package com.mycompany.tcs.dao.impl;

import com.mycompany.tcs.dao.EmployeeDAO;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.model.Notifications;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO 
{
    @Autowired 
    private SessionFactory session;

    @Override
    @Transactional
    public void addEmployees(Employees emp) 
    {
        session.getCurrentSession().save(emp);
    }

    @Override
    @Transactional
    public void editEmployees(Employees emp) 
    {
            System.out.println("daoImpl edit 1");
        session.getCurrentSession().update(emp);
            System.out.println("daoImpl edit 2");
    }

    @Override
    @Transactional
    public void deleteEmployees(int empId) 
    {
        session.getCurrentSession().delete(findEmployeeById(empId));
    }

    @Override
    @Transactional
    public int EmployeeStatusById(int empId) 
    {
        String sql = "select loginStatus from Employees where empId = :empId ";
        Query query = session.getCurrentSession().createQuery(sql);
        query.setParameter("empId", empId);
        
        List result = query.list();
        return Integer.parseInt(result.get(0).toString());
    }

    @Override
    @Transactional
    public Employees findEmployeeById(int empId) 
    {
        Criteria criteria = session.getCurrentSession().createCriteria(Employees.class);
	criteria.add(Restrictions.eq("eid", empId));		
	return (Employees) criteria.uniqueResult(); 
    }

    @Override
    @Transactional
    public Employees findEmployeeByName(String Name) 
    {
        Criteria criteria = session.getCurrentSession().createCriteria(Employees.class);
	criteria.add(Restrictions.eq("Name", Name));		
	return (Employees) criteria.uniqueResult(); 
    }

    @Override
    @Transactional
    public Collection<Notifications> getEmployeeNotifications(int empId)  // change doubt
    {
        Query query = session.getCurrentSession().createQuery("from Notifications where genEid = :empId ");
        query.setParameter("empId", empId);
        
        return (Collection<Notifications>) query.list();
    }

    @Override
    @Transactional
    public Collection<Notifications> getEmployeeGeneratedNotifications(int empId) 
    {
        Query query = session.getCurrentSession().createQuery("from Notifications where genEid = :empId ");
        query.setParameter("empId", empId);
        
        return (Collection<Notifications>) query.list();
    }

    @Override
    @Transactional
    public Collection<Messages> getEmployeeMessages(int empId) // change doubt
    {
        Query query = session.getCurrentSession().createQuery("from Messages where sendEid = :empId ");
        query.setParameter("empId", empId);
        
        return (Collection<Messages>) query.list();
    }

    @Override
    @Transactional
    public Collection<Messages> getEmployeeSentMessages(int empId) 
    {
        Query query = session.getCurrentSession().createQuery("from Messages where sendEid = :empId ");
        query.setParameter("empId", empId);
        
        return (Collection<Messages>) query.list();
    }

    @Override
    @Transactional
    public List<Employees> getAllEmployees() 
    {
        return session.getCurrentSession().createQuery("from Employees").list();
//        return (List<Employees>) session.getCurrentSession().createCriteria(Employees.class).list();
    }

    @Override
    public void logoutEmployee(int empId) 
    {
        Query query = session.getCurrentSession().createQuery("update Employees set loginStatus = 0 where empId = :empId");
        query.setParameter("empId", empId);
        query.executeUpdate();
    }
}