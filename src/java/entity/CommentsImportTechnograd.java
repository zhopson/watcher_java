/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrey-man
 */
@Entity
@Table(name = "port_info.comments_import_technograd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentsImportTechnograd.findAll", query = "SELECT c FROM CommentsImportTechnograd c")
    , @NamedQuery(name = "CommentsImportTechnograd.findById", query = "SELECT c FROM CommentsImportTechnograd c WHERE c.id = :id")
    , @NamedQuery(name = "CommentsImportTechnograd.findByFio", query = "SELECT c FROM CommentsImportTechnograd c WHERE c.fio = :fio")
    , @NamedQuery(name = "CommentsImportTechnograd.findByDate", query = "SELECT c FROM CommentsImportTechnograd c WHERE c.date = :date")
    , @NamedQuery(name = "CommentsImportTechnograd.findByComments", query = "SELECT c FROM CommentsImportTechnograd c WHERE c.comments = :comments")})
public class CommentsImportTechnograd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "fio")
    private String fio;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 2147483647)
    @Column(name = "comments")
    private String comments;
    @JoinColumn(name = "tech_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImportTechnograd techId;

    public CommentsImportTechnograd() {
    }

    public CommentsImportTechnograd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ImportTechnograd getTechId() {
        return techId;
    }

    public void setTechId(ImportTechnograd techId) {
        this.techId = techId;
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
        if (!(object instanceof CommentsImportTechnograd)) {
            return false;
        }
        CommentsImportTechnograd other = (CommentsImportTechnograd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CommentsImportTechnograd[ id=" + id + " ]";
    }
    
}
