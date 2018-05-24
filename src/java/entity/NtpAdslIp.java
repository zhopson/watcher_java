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
@Table(name = "port_info.ntp_adsl_ip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NtpAdslIp.findAll", query = "SELECT n FROM NtpAdslIp n")
    , @NamedQuery(name = "NtpAdslIp.findByIp", query = "SELECT n FROM NtpAdslIp n WHERE n.ip = :ip")
    , @NamedQuery(name = "NtpAdslIp.findByChantype", query = "SELECT n FROM NtpAdslIp n WHERE n.chantype = :chantype")})
public class NtpAdslIp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ip")
    private String ip;
    @Size(max = 20)
    @Column(name = "chantype")
    private String chantype;

    public NtpAdslIp() {
    }

    public NtpAdslIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChantype() {
        return chantype;
    }

    public void setChantype(String chantype) {
        this.chantype = chantype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ip != null ? ip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NtpAdslIp)) {
            return false;
        }
        NtpAdslIp other = (NtpAdslIp) object;
        if ((this.ip == null && other.ip != null) || (this.ip != null && !this.ip.equals(other.ip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NtpAdslIp[ ip=" + ip + " ]";
    }
    
}
