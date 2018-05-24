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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrey-man
 */
@Entity
@Table(name = "watcher.smotr_whatsapp_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrWhatsappGroups.findAll", query = "SELECT s FROM SmotrWhatsappGroups s")
    , @NamedQuery(name = "SmotrWhatsappGroups.findAllId", query = "SELECT s.name,s.realGr FROM SmotrWhatsappGroups s")
    , @NamedQuery(name = "SmotrWhatsappGroups.findById", query = "SELECT s FROM SmotrWhatsappGroups s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrWhatsappGroups.findByName", query = "SELECT s FROM SmotrWhatsappGroups s WHERE s.name = :name")
    , @NamedQuery(name = "SmotrWhatsappGroups.findByRealGr", query = "SELECT s FROM SmotrWhatsappGroups s WHERE s.realGr = :realGr")})
public class SmotrWhatsappGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 150)
    @Column(name = "name")
    private String name;
    @Size(max = 80)
    @Column(name = "real_gr")
    private String realGr;

    public SmotrWhatsappGroups() {
    }

    public SmotrWhatsappGroups(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealGr() {
        return realGr;
    }

    public void setRealGr(String realGr) {
        this.realGr = realGr;
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
        if (!(object instanceof SmotrWhatsappGroups)) {
            return false;
        }
        SmotrWhatsappGroups other = (SmotrWhatsappGroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SmotrWhatsappGroups[ id=" + id + " ]";
    }
    
}
