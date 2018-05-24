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
@Table(name = "port_info.dslam_nets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DslamNets.findAll", query = "SELECT d FROM DslamNets d")
    , @NamedQuery(name = "DslamNets.findByIpaddr", query = "SELECT d FROM DslamNets d WHERE d.ipaddr = :ipaddr")
    , @NamedQuery(name = "DslamNets.findByNet", query = "SELECT d FROM DslamNets d WHERE d.net = :net")
    , @NamedQuery(name = "DslamNets.findByHostName", query = "SELECT d FROM DslamNets d WHERE d.hostName = :hostName")
    , @NamedQuery(name = "DslamNets.findByOldNet", query = "SELECT d FROM DslamNets d WHERE d.oldNet = :oldNet")})
public class DslamNets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ipaddr")
    private String ipaddr;
    @Size(max = 40)
    @Column(name = "net")
    private String net;
    @Size(max = 80)
    @Column(name = "host_name_")
    private String hostName;
    @Size(max = 40)
    @Column(name = "old_net")
    private String oldNet;

    public DslamNets() {
    }

    public DslamNets(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOldNet() {
        return oldNet;
    }

    public void setOldNet(String oldNet) {
        this.oldNet = oldNet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ipaddr != null ? ipaddr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DslamNets)) {
            return false;
        }
        DslamNets other = (DslamNets) object;
        if ((this.ipaddr == null && other.ipaddr != null) || (this.ipaddr != null && !this.ipaddr.equals(other.ipaddr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DslamNets[ ipaddr=" + ipaddr + " ]";
    }
    
}
