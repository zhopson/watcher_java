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
@Table(name = "comments_import_technograd_archive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentsImportTechnogradArchive.findAll", query = "SELECT c FROM CommentsImportTechnogradArchive c")
    , @NamedQuery(name = "CommentsImportTechnogradArchive.findById", query = "SELECT c FROM CommentsImportTechnogradArchive c WHERE c.id = :id")
    , @NamedQuery(name = "CommentsImportTechnogradArchive.findByFio", query = "SELECT c FROM CommentsImportTechnogradArchive c WHERE c.fio = :fio")
    , @NamedQuery(name = "CommentsImportTechnogradArchive.findByDate", query = "SELECT c FROM CommentsImportTechnogradArchive c WHERE c.date = :date")
    , @NamedQuery(name = "CommentsImportTechnogradArchive.findByComments", query = "SELECT c FROM CommentsImportTechnogradArchive c WHERE c.comments = :comments")
    , @NamedQuery(name = "CommentsImportTechnogradArchive.findByDateEndAlarm", query = "SELECT c FROM CommentsImportTechnogradArchive c WHERE c.dateEndAlarm = :dateEndAlarm")
    , @NamedQuery(name = "CommentsImportTechnogradArchive.findByDateStartAlarm", query = "SELECT c FROM CommentsImportTechnogradArchive c WHERE c.dateStartAlarm = :dateStartAlarm")})
public class CommentsImportTechnogradArchive implements Serializable {

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
    @Column(name = "date_end_alarm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEndAlarm;
    @Column(name = "date_start_alarm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStartAlarm;
    @JoinColumn(name = "tech_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImportTechnograd techId;

    public CommentsImportTechnogradArchive() {
    }

    public CommentsImportTechnogradArchive(Long id) {
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

    public Date getDateEndAlarm() {
        return dateEndAlarm;
    }

    public void setDateEndAlarm(Date dateEndAlarm) {
        this.dateEndAlarm = dateEndAlarm;
    }

    public Date getDateStartAlarm() {
        return dateStartAlarm;
    }

    public void setDateStartAlarm(Date dateStartAlarm) {
        this.dateStartAlarm = dateStartAlarm;
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
        if (!(object instanceof CommentsImportTechnogradArchive)) {
            return false;
        }
        CommentsImportTechnogradArchive other = (CommentsImportTechnogradArchive) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CommentsImportTechnogradArchive[ id=" + id + " ]";
    }
    
}
