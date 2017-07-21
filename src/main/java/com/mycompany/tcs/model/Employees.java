/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shivangi
 */
@Entity
@Table(name = "employees", catalog = "chat", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findByEid", query = "SELECT e FROM Employees e WHERE e.eid = :eid"),
    @NamedQuery(name = "Employees.findByEname", query = "SELECT e FROM Employees e WHERE e.ename = :ename"),
    @NamedQuery(name = "Employees.findByDob", query = "SELECT e FROM Employees e WHERE e.dob = :dob"),
    @NamedQuery(name = "Employees.findByAddress", query = "SELECT e FROM Employees e WHERE e.address = :address"),
    @NamedQuery(name = "Employees.findByPhoneNo", query = "SELECT e FROM Employees e WHERE e.phoneNo = :phoneNo"),
    @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employees e WHERE e.email = :email"),
    @NamedQuery(name = "Employees.findByLoginStatus", query = "SELECT e FROM Employees e WHERE e.loginStatus = :loginStatus"),
    @NamedQuery(name = "Employees.findByVerifykey", query = "SELECT e FROM Employees e WHERE e.verifykey = :verifykey"),
    @NamedQuery(name = "Employees.findByDepName", query = "SELECT e FROM Employees e WHERE e.depName = :depName"),
    @NamedQuery(name = "Employees.findByJdoDetachedState", query = "SELECT e FROM Employees e WHERE e.jdoDetachedState = :jdoDetachedState")})
public class Employees implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eid")
    private int eid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ename")
    private String ename;
    @Size(max = 40)
    @Column(name = "dob")
    private String dob;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "phoneNo")
    private String phoneNo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "password")
    private String password;
    @Column(name = "loginStatus")
    private Integer loginStatus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "verifykey")
    private Integer verifykey;
    @Size(max = 20)
    @Column(name = "depName")
    private String depName;
    @OneToMany(mappedBy = "postEid")
    private Collection<Forumanswer> forumanswerCollection;
    @OneToMany(mappedBy = "rateEid")
    private Collection<Forumanswer> forumanswerCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiveEid")
    private Collection<Messages> messagesCollection;
    @OneToMany(mappedBy = "postEid")
    private Collection<Forumques> forumquesCollection;
    @JoinColumn(name = "adminEid", referencedColumnName = "adminId")
    @ManyToOne
    private Adminorg adminEid;
    @OneToMany(mappedBy = "genEid")
    private Collection<Notifications> notificationsCollection;
    @OneToMany(mappedBy = "receiveEid")
    private Collection<Notifications> notificationsCollection1;

    public Employees() {
    }

    public Employees(Integer verifykey) {
        this.verifykey = verifykey;
    }

    public Employees(Integer verifykey, int eid, String ename, String phoneNo, String email, String password) {
        this.verifykey = verifykey;
        this.eid = eid;
        this.ename = ename;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getVerifykey() {
        return verifykey;
    }

    public void setVerifykey(Integer verifykey) {
        this.verifykey = verifykey;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
    
    @XmlTransient
    public Collection<Forumanswer> getForumanswerCollection() {
        return forumanswerCollection;
    }

    public void setForumanswerCollection(Collection<Forumanswer> forumanswerCollection) {
        this.forumanswerCollection = forumanswerCollection;
    }

    @XmlTransient
    public Collection<Forumanswer> getForumanswerCollection1() {
        return forumanswerCollection1;
    }

    public void setForumanswerCollection1(Collection<Forumanswer> forumanswerCollection1) {
        this.forumanswerCollection1 = forumanswerCollection1;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @XmlTransient
    public Collection<Forumques> getForumquesCollection() {
        return forumquesCollection;
    }

    public void setForumquesCollection(Collection<Forumques> forumquesCollection) {
        this.forumquesCollection = forumquesCollection;
    }

    public Adminorg getAdminEid() {
        return adminEid;
    }

    public void setAdminEid(Adminorg adminEid) {
        this.adminEid = adminEid;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Collection<Notifications> notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection1() {
        return notificationsCollection1;
    }

    public void setNotificationsCollection1(Collection<Notifications> notificationsCollection1) {
        this.notificationsCollection1 = notificationsCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verifykey != null ? verifykey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.verifykey == null && other.verifykey != null) || (this.verifykey != null && !this.verifykey.equals(other.verifykey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tcs.controller.Employees[ verifykey=" + verifykey + " ]";
    }
    
}
