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
@Table(name = "port_info.dslam_port_binds")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DslamPortBinds.findAll", query = "SELECT d FROM DslamPortBinds d")
    , @NamedQuery(name = "DslamPortBinds.findByHost", query = "SELECT d FROM DslamPortBinds d WHERE d.host = :host")
    , @NamedQuery(name = "DslamPortBinds.findByPortId", query = "SELECT d FROM DslamPortBinds d WHERE d.portId = :portId")
    , @NamedQuery(name = "DslamPortBinds.findByPort", query = "SELECT d FROM DslamPortBinds d WHERE d.port = :port")
//    , @NamedQuery(name = "DslamPortBinds.EmptyTable", query = "DELETE from DslamPortBinds d")
    , @NamedQuery(name = "DslamPortBinds.findByIpLine", query = "SELECT d FROM DslamPortBinds d WHERE d.ipLine = :ipLine")})
public class DslamPortBinds implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 15)
    @Column(name = "host")
    private String host;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "port_id")
    private Integer portId;
    @Size(max = 15)
    @Column(name = "port")
    private String port;
    @Size(max = 140)
    @Column(name = "ip_line")
    private String ipLine;

    public DslamPortBinds() {
    }

    public DslamPortBinds(Integer portId) {
        this.portId = portId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIpLine() {
        return ipLine;
    }

    public void setIpLine(String ipLine) {
        this.ipLine = ipLine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (portId != null ? portId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DslamPortBinds)) {
            return false;
        }
        DslamPortBinds other = (DslamPortBinds) object;
        if ((this.portId == null && other.portId != null) || (this.portId != null && !this.portId.equals(other.portId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DslamPortBinds[ portId=" + portId + " ]";
    }
    
}
