/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shivangi
 */
@Entity
@Table(name = "adminorg", catalog = "chat", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adminorg.findAll", query = "SELECT a FROM Adminorg a"),
    @NamedQuery(name = "Adminorg.findByAdminId", query = "SELECT a FROM Adminorg a WHERE a.adminId = :adminId"),
    @NamedQuery(name = "Adminorg.findByJdoDetachedState", query = "SELECT a FROM Adminorg a WHERE a.jdoDetachedState = :jdoDetachedState")})
public class Adminorg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "adminId")
    private Integer adminId;
    @OneToMany(mappedBy = "adminEid")
    private Collection<Employees> employeesCollection;

    public Adminorg() {
    }

    public Adminorg(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @XmlTransient
    public Collection<Employees> getEmployeesCollection() {
        return employeesCollection;
    }

    public void setEmployeesCollection(Collection<Employees> employeesCollection) {
        this.employeesCollection = employeesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminId != null ? adminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adminorg)) {
            return false;
        }
        Adminorg other = (Adminorg) object;
        if ((this.adminId == null && other.adminId != null) || (this.adminId != null && !this.adminId.equals(other.adminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tcs.controller.Adminorg[ adminId=" + adminId + " ]";
    }
    
}
