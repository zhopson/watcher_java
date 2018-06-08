/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "watcher.smotr_dslam_ports_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrDslamPortsView.findAll", query = "SELECT s FROM SmotrDslamPortsView s")
    , @NamedQuery(name = "SmotrDslamPortsView.findByHostDate", query = "SELECT c FROM SmotrDslamPortsView c WHERE c.host = :host and c.ipLine like :ipline and c.date>=:date1 and c.date<=:date2 order by c.portId")
    , @NamedQuery(name = "SmotrDslamPortsView.findByHostPortDate", query = "SELECT c FROM SmotrDslamPortsView c WHERE c.host = :host and c.port like :port and c.ipLine like :ipline and c.date>=:date1 and c.date<=:date2 order by c.portId")
    , @NamedQuery(name = "SmotrDslamPortsView.findByPortDate", query = "SELECT c FROM SmotrDslamPortsView c WHERE c.port like :port and c.ipLine like :ipline and c.date>=:date1 and c.date<=:date2 order by c.portId")
    , @NamedQuery(name = "SmotrDslamPortsView.findByDate1", query = "SELECT c FROM SmotrDslamPortsView c WHERE c.ipLine like :ipline and c.date>=:date1 and c.date<=:date2 order by c.portId")
    , @NamedQuery(name = "SmotrDslamPortsView.findMaxDateByHost", query = "SELECT max(c.date) FROM SmotrDslamPortsView c WHERE c.host = :host")
    , @NamedQuery(name = "SmotrDslamPortsView.findById", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrDslamPortsView.findByHostId", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.hostId = :hostId")
    , @NamedQuery(name = "SmotrDslamPortsView.findByHostName", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.hostName = :hostName")
    , @NamedQuery(name = "SmotrDslamPortsView.findByHost", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.host = :host")
    , @NamedQuery(name = "SmotrDslamPortsView.findByPortId", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.portId = :portId")
    , @NamedQuery(name = "SmotrDslamPortsView.findByPort", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.port = :port")
    , @NamedQuery(name = "SmotrDslamPortsView.findByPortStatus", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.portStatus = :portStatus")
    , @NamedQuery(name = "SmotrDslamPortsView.findByIpLine", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.ipLine = :ipLine")
    , @NamedQuery(name = "SmotrDslamPortsView.findByProfileLine", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.profileLine = :profileLine")
    , @NamedQuery(name = "SmotrDslamPortsView.findByProfileIptv", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.profileIptv = :profileIptv")
    , @NamedQuery(name = "SmotrDslamPortsView.findByVlanLine", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.vlanLine = :vlanLine")
    , @NamedQuery(name = "SmotrDslamPortsView.findByPvcLine", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.pvcLine = :pvcLine")
    , @NamedQuery(name = "SmotrDslamPortsView.findByVlanIptv", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.vlanIptv = :vlanIptv")
    , @NamedQuery(name = "SmotrDslamPortsView.findByPvcIptv", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.pvcIptv = :pvcIptv")
    , @NamedQuery(name = "SmotrDslamPortsView.findByEdsnr", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.edsnr = :edsnr")
    , @NamedQuery(name = "SmotrDslamPortsView.findByEda", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.eda = :eda")
    , @NamedQuery(name = "SmotrDslamPortsView.findByEusnr", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.eusnr = :eusnr")
    , @NamedQuery(name = "SmotrDslamPortsView.findByEua", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.eua = :eua")
    , @NamedQuery(name = "SmotrDslamPortsView.findByDate", query = "SELECT s FROM SmotrDslamPortsView s WHERE s.date = :date")})
public class SmotrDslamPortsView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private BigInteger id;
    @Column(name = "host_id")
    private BigInteger hostId;
    @Size(max = 50)
    @Column(name = "host_name")
    private String hostName;
    @Size(max = 15)
    @Column(name = "host")
    private String host;
    @Column(name = "port_id")
    private Integer portId;
    @Size(max = 15)
    @Column(name = "port")
    private String port;
    @Size(max = 20)
    @Column(name = "port_status")
    private String portStatus;
    @Size(max = 140)
    @Column(name = "ip_line")
    private String ipLine;
    @Size(max = 15)
    @Column(name = "profile_line")
    private String profileLine;
    @Size(max = 500)
    @Column(name = "profile_iptv")
    private String profileIptv;
    @Size(max = 5)
    @Column(name = "vlan_line")
    private String vlanLine;
    @Size(max = 20)
    @Column(name = "pvc_line")
    private String pvcLine;
    @Size(max = 5)
    @Column(name = "vlan_iptv")
    private String vlanIptv;
    @Size(max = 20)
    @Column(name = "pvc_iptv")
    private String pvcIptv;
    @Size(max = 255)
    @Column(name = "edsnr")
    private String edsnr;
    @Size(max = 255)
    @Column(name = "eda")
    private String eda;
    @Size(max = 255)
    @Column(name = "eusnr")
    private String eusnr;
    @Size(max = 255)
    @Column(name = "eua")
    private String eua;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 2147483647)


    public SmotrDslamPortsView() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getHostId() {
        return hostId;
    }

    public void setHostId(BigInteger hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
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

    public String getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus;
    }

    public String getIpLine() {
        return ipLine;
    }

    public void setIpLine(String ipLine) {
        this.ipLine = ipLine;
    }

    public String getProfileLine() {
        return profileLine;
    }

    public void setProfileLine(String profileLine) {
        this.profileLine = profileLine;
    }

    public String getProfileIptv() {
        return profileIptv;
    }

    public void setProfileIptv(String profileIptv) {
        this.profileIptv = profileIptv;
    }

    public String getVlanLine() {
        return vlanLine;
    }

    public void setVlanLine(String vlanLine) {
        this.vlanLine = vlanLine;
    }

    public String getPvcLine() {
        return pvcLine;
    }

    public void setPvcLine(String pvcLine) {
        this.pvcLine = pvcLine;
    }

    public String getVlanIptv() {
        return vlanIptv;
    }

    public void setVlanIptv(String vlanIptv) {
        this.vlanIptv = vlanIptv;
    }

    public String getPvcIptv() {
        return pvcIptv;
    }

    public void setPvcIptv(String pvcIptv) {
        this.pvcIptv = pvcIptv;
    }

    public String getEdsnr() {
        return edsnr;
    }

    public void setEdsnr(String edsnr) {
        this.edsnr = edsnr;
    }

    public String getEda() {
        return eda;
    }

    public void setEda(String eda) {
        this.eda = eda;
    }

    public String getEusnr() {
        return eusnr;
    }

    public void setEusnr(String eusnr) {
        this.eusnr = eusnr;
    }

    public String getEua() {
        return eua;
    }

    public void setEua(String eua) {
        this.eua = eua;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format( date );
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
