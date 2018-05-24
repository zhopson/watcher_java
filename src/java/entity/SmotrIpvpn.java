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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrey-man
 */
@Entity
@Table(name = "watcher.smotr_ipvpn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrIpvpn.findAll", query = "SELECT s FROM SmotrIpvpn s")
    , @NamedQuery(name = "SmotrIpvpn.findById", query = "SELECT s FROM SmotrIpvpn s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrIpvpn.findByLc", query = "SELECT s FROM SmotrIpvpn s WHERE s.lc = :lc")
    , @NamedQuery(name = "SmotrIpvpn.findByNameClt", query = "SELECT s FROM SmotrIpvpn s WHERE s.nameClt = :nameClt")
    , @NamedQuery(name = "SmotrIpvpn.findByUslugaId", query = "SELECT s FROM SmotrIpvpn s WHERE s.uslugaId = :uslugaId")
    , @NamedQuery(name = "SmotrIpvpn.findByAdresUslugi", query = "SELECT s FROM SmotrIpvpn s WHERE s.adresUslugi = :adresUslugi")
    , @NamedQuery(name = "SmotrIpvpn.findByBandwich", query = "SELECT s FROM SmotrIpvpn s WHERE s.bandwich = :bandwich")
    , @NamedQuery(name = "SmotrIpvpn.findByIpNetAbon", query = "SELECT s FROM SmotrIpvpn s WHERE s.ipNetAbon = :ipNetAbon")
    , @NamedQuery(name = "SmotrIpvpn.findByTypeConnect", query = "SELECT s FROM SmotrIpvpn s WHERE s.typeConnect = :typeConnect")
    , @NamedQuery(name = "SmotrIpvpn.findByTypeTerminate", query = "SELECT s FROM SmotrIpvpn s WHERE s.typeTerminate = :typeTerminate")
    , @NamedQuery(name = "SmotrIpvpn.findByPort", query = "SELECT s FROM SmotrIpvpn s WHERE s.port = :port")
    , @NamedQuery(name = "SmotrIpvpn.findByNumVlanQinq", query = "SELECT s FROM SmotrIpvpn s WHERE s.numVlanQinq = :numVlanQinq")})
public class SmotrIpvpn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "lc")
    private String lc;
    @Size(max = 200)
    @Column(name = "name_clt")
    private String nameClt;
    @Size(max = 50)
    @Column(name = "usluga_id")
    private String uslugaId;
    @Size(max = 200)
    @Column(name = "adres_uslugi")
    private String adresUslugi;
    @Column(name = "bandwich")
    private Integer bandwich;
    @Size(max = 20)
    @Column(name = "ip_net_abon")
    private String ipNetAbon;
    @Size(max = 50)
    @Column(name = "type_connect")
    private String typeConnect;
    @Size(max = 50)
    @Column(name = "type_terminate")
    private String typeTerminate;
    @Size(max = 50)
    @Column(name = "port")
    private String port;
    @Column(name = "num_vlan_qinq")
    private Integer numVlanQinq;

    public SmotrIpvpn() {
    }

    public SmotrIpvpn(Long id) {
        this.id = id;
    }

    public SmotrIpvpn(Long id, String lc) {
        this.id = id;
        this.lc = lc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getNameClt() {
        return nameClt;
    }

    public void setNameClt(String nameClt) {
        this.nameClt = nameClt;
    }

    public String getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(String uslugaId) {
        this.uslugaId = uslugaId;
    }

    public String getAdresUslugi() {
        return adresUslugi;
    }

    public void setAdresUslugi(String adresUslugi) {
        this.adresUslugi = adresUslugi;
    }

    public Integer getBandwich() {
        return bandwich;
    }

    public void setBandwich(Integer bandwich) {
        this.bandwich = bandwich;
    }

    public String getIpNetAbon() {
        return ipNetAbon;
    }

    public void setIpNetAbon(String ipNetAbon) {
        this.ipNetAbon = ipNetAbon;
    }

    public String getTypeConnect() {
        return typeConnect;
    }

    public void setTypeConnect(String typeConnect) {
        this.typeConnect = typeConnect;
    }

    public String getTypeTerminate() {
        return typeTerminate;
    }

    public void setTypeTerminate(String typeTerminate) {
        this.typeTerminate = typeTerminate;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getNumVlanQinq() {
        return numVlanQinq;
    }

    public void setNumVlanQinq(Integer numVlanQinq) {
        this.numVlanQinq = numVlanQinq;
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
        if (!(object instanceof SmotrIpvpn)) {
            return false;
        }
        SmotrIpvpn other = (SmotrIpvpn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SmotrIpvpn[ id=" + id + " ]";
    }
    
}
