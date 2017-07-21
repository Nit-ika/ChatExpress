/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shivangi
 */
@Entity
@Table(name = "forumanswer", catalog = "chat", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forumanswer.findAll", query = "SELECT f FROM Forumanswer f"),
    @NamedQuery(name = "Forumanswer.findByAnsid", query = "SELECT f FROM Forumanswer f WHERE f.ansid = :ansid"),
    @NamedQuery(name = "Forumanswer.findByDatePost", query = "SELECT f FROM Forumanswer f WHERE f.datePost = :datePost"),
    @NamedQuery(name = "Forumanswer.findByRate", query = "SELECT f FROM Forumanswer f WHERE f.rate = :rate")})
public class Forumanswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ansid")
    private Integer ansid;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "answer")
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datePost")
    @Temporal(TemporalType.DATE)
    private Date datePost;
    @Column(name = "rate")
    private Integer rate;
    
    @JoinColumn(name = "qid", referencedColumnName = "qid")
    @ManyToOne
    private Forumques qid;
    
    @JoinColumn(name = "postEid", referencedColumnName = "eid")
    @ManyToOne
    private Employees postEid;
    @JoinColumn(name = "rateEid", referencedColumnName = "eid")
    @ManyToOne
    private Employees rateEid;

    public Forumanswer() {
    }

    public Forumanswer(Integer ansid) {
        this.ansid = ansid;
    }

    public Forumanswer(Integer ansid, String answer, Date datePost) {
        this.ansid = ansid;
        this.answer = answer;
        this.datePost = datePost;
    }

    public Integer getAnsid() {
        return ansid;
    }

    public void setAnsid(Integer ansid) {
        this.ansid = ansid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Forumques getQid() {
        return qid;
    }

    public void setQid(Forumques qid) {
        this.qid = qid;
    }

    public Employees getPostEid() {
        return postEid;
    }

    public void setPostEid(Employees postEid) {
        this.postEid = postEid;
    }

    public Employees getRateEid() {
        return rateEid;
    }

    public void setRateEid(Employees rateEid) {
        this.rateEid = rateEid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ansid != null ? ansid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forumanswer)) {
            return false;
        }
        Forumanswer other = (Forumanswer) object;
        if ((this.ansid == null && other.ansid != null) || (this.ansid != null && !this.ansid.equals(other.ansid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tcs.controller.Forumanswer[ ansid=" + ansid + " ]";
    }
    
}
