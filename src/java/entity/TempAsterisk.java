/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "watcher.temp_asterisk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TempAsterisk.findAll", query = "SELECT t FROM TempAsterisk t")
    , @NamedQuery(name = "TempAsterisk.findByCalldate", query = "SELECT t FROM TempAsterisk t WHERE t.calldate = :calldate")
    , @NamedQuery(name = "TempAsterisk.findBySrc", query = "SELECT t FROM TempAsterisk t WHERE t.src = :src")
    , @NamedQuery(name = "TempAsterisk.findByDst", query = "SELECT t FROM TempAsterisk t WHERE t.dst = :dst")
    , @NamedQuery(name = "TempAsterisk.findByDisposition", query = "SELECT t FROM TempAsterisk t WHERE t.disposition = :disposition")
    , @NamedQuery(name = "TempAsterisk.findByLastapp", query = "SELECT t FROM TempAsterisk t WHERE t.lastapp = :lastapp")
    , @NamedQuery(name = "TempAsterisk.findByUsrZabbix", query = "SELECT distinct s.fio,t.calldate,t.dst,t.disposition FROM TempAsterisk t, SmotrCallerPeople s WHERE t.dst=s.tel and t.usrZabbix = :usrZabbix order by t.calldate DESC")
    , @NamedQuery(name = "TempAsterisk.findById", query = "SELECT t FROM TempAsterisk t WHERE t.id = :id")})
public class TempAsterisk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "calldate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calldate;
    @Size(max = 12)
    @Column(name = "src")
    private String src;
    @Size(max = 12)
    @Column(name = "dst")
    private String dst;
    @Size(max = 50)
    @Column(name = "disposition")
    private String disposition;
    @Size(max = 100)
    @Column(name = "lastapp")
    private String lastapp;
    @Size(max = 50)
    @Column(name = "usr_zabbix")
    private String usrZabbix;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

   @JoinTable(
         name="watcher.smotr_caller_people",
         joinColumns = {@JoinColumn(name="tel", referencedColumnName="dst")},
         inverseJoinColumns = {@JoinColumn(name="dst", referencedColumnName="tel")}
   )
   @ManyToMany
   private final List<TempAsterisk> dependencies = new ArrayList<TempAsterisk>();
    
    
    public TempAsterisk() {
    }

    public TempAsterisk(Long id) {
        this.id = id;
    }

    public Date getCalldate() {
        return calldate;
    }

    public void setCalldate(Date calldate) {
        this.calldate = calldate;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getLastapp() {
        return lastapp;
    }

    public void setLastapp(String lastapp) {
        this.lastapp = lastapp;
    }

    public String getUsrZabbix() {
        return usrZabbix;
    }

    public void setUsrZabbix(String usrZabbix) {
        this.usrZabbix = usrZabbix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof TempAsterisk)) {
            return false;
        }
        TempAsterisk other = (TempAsterisk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TempAsterisk[ id=" + id + " ]";
    }
    
}
