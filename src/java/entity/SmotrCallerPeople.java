/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrey-man
 */
@Entity
@Table(name = "watcher.smotr_caller_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrCallerPeople.findAll", query = "SELECT distinct s.id,s.fio,s.tel,s.post,s.call,s.groupid FROM SmotrCallerPeople s order by s.fio")
    , @NamedQuery(name = "SmotrCallerPeople.findByGr0", query = "SELECT distinct s.fio,s.tel,s.post,s.call FROM SmotrCallerPeople s order by s.fio")
    , @NamedQuery(name = "SmotrCallerPeople.findByFio", query = "SELECT s FROM SmotrCallerPeople s WHERE s.fio = :fio")
    , @NamedQuery(name = "SmotrCallerPeople.findByPost", query = "SELECT s FROM SmotrCallerPeople s WHERE s.post = :post")
    , @NamedQuery(name = "SmotrCallerPeople.findByTel", query = "SELECT s FROM SmotrCallerPeople s WHERE s.tel = :tel")
    , @NamedQuery(name = "SmotrCallerPeople.findByTelSet", query = "SELECT s FROM SmotrCallerPeople s WHERE s.tel in :ss")
    , @NamedQuery(name = "SmotrCallerPeople.findByGroupid", query = "SELECT s.id,s.fio,s.tel,s.post,s.call,s.groupid FROM SmotrCallerPeople s WHERE s.groupid = :groupid")
    , @NamedQuery(name = "SmotrCallerPeople.findByCall", query = "SELECT s FROM SmotrCallerPeople s WHERE s.call = :call")
    , @NamedQuery(name = "SmotrCallerPeople.findById", query = "SELECT s FROM SmotrCallerPeople s WHERE s.id = :id")})
public class SmotrCallerPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "fio")
    private String fio;
    @Size(max = 100)
    @Column(name = "post")
    private String post;
    @Size(max = 12)
    @Column(name = "tel")
    private String tel;
    @Column(name = "groupid")
    private Integer groupid;
    @Column(name = "call")
    private Boolean call;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public SmotrCallerPeople() {
    }

    public SmotrCallerPeople(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Boolean getCall() {
        return call;
    }

    public void setCall(Boolean call) {
        this.call = call;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmotrCallerPeople)) {
            return false;
        }
        SmotrCallerPeople other = (SmotrCallerPeople) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SmotrCallerPeople[ id=" + id + " ]";
    }
    
}
