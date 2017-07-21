/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shivangi
 */
@Entity
@Table(name = "notifications", catalog = "chat", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n"),
    @NamedQuery(name = "Notifications.findByNid", query = "SELECT n FROM Notifications n WHERE n.nid = :nid")})
public class Notifications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nid")
    private Integer nid;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "notificationType")
    private String notificationType;
    @JoinColumn(name = "genEid", referencedColumnName = "eid")
    @ManyToOne
    private Employees genEid;
    @JoinColumn(name = "receiveEid", referencedColumnName = "eid")
    @ManyToOne
    private Employees receiveEid;

    public Notifications() {
    }

    public Notifications(Integer nid) {
        this.nid = nid;
    }

    public Notifications(Integer nid, String notificationType) {
        this.nid = nid;
        this.notificationType = notificationType;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Employees getGenEid() {
        return genEid;
    }

    public void setGenEid(Employees genEid) {
        this.genEid = genEid;
    }

    public Employees getReceiveEid() {
        return receiveEid;
    }

    public void setReceiveEid(Employees receiveEid) {
        this.receiveEid = receiveEid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nid != null ? nid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.nid == null && other.nid != null) || (this.nid != null && !this.nid.equals(other.nid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tcs.controller.Notifications[ nid=" + nid + " ]";
    }
    
}
