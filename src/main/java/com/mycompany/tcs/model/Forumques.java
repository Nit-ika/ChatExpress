/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcs.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shivangi
 */
@Entity
@Table(name = "forumques", catalog = "chat", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forumques.findAll", query = "SELECT f FROM Forumques f"),
    @NamedQuery(name = "Forumques.findByQid", query = "SELECT f FROM Forumques f WHERE f.qid = :qid"),
    @NamedQuery(name = "Forumques.findByDatePost", query = "SELECT f FROM Forumques f WHERE f.datePost = :datePost")})
public class Forumques implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qid")
    private Integer qid;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datePost")
    @Temporal(TemporalType.DATE)
    private Date datePost;
    @OneToMany(mappedBy = "qid")
    private Collection<Forumanswer> forumanswerCollection;
    @JoinColumn(name = "postEid", referencedColumnName = "eid")
    @ManyToOne
    private Employees postEid;

    public Forumques() {
    }

    public Forumques(Integer qid) {
        this.qid = qid;
    }

    public Forumques(Integer qid, String question, Date datePost) {
        this.qid = qid;
        this.question = question;
        this.datePost = datePost;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    @XmlTransient
    public Collection<Forumanswer> getForumanswerCollection() {
        return forumanswerCollection;
    }

    public void setForumanswerCollection(Collection<Forumanswer> forumanswerCollection) {
        this.forumanswerCollection = forumanswerCollection;
    }

    public Employees getPostEid() {
        return postEid;
    }

    public void setPostEid(Employees postEid) {
        this.postEid = postEid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qid != null ? qid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forumques)) {
            return false;
        }
        Forumques other = (Forumques) object;
        if ((this.qid == null && other.qid != null) || (this.qid != null && !this.qid.equals(other.qid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tcs.controller.Forumques[ qid=" + qid + " ]";
    }
    
}
