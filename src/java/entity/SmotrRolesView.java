/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "watcher.smotr_roles_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrRolesView.findAll", query = "SELECT s FROM SmotrRolesView s")
    , @NamedQuery(name = "SmotrRolesView.findByAlias", query = "SELECT s FROM SmotrRolesView s WHERE s.alias = :alias")
    , @NamedQuery(name = "SmotrRolesView.findByPasswd", query = "SELECT s FROM SmotrRolesView s WHERE s.passwd = :passwd")
    , @NamedQuery(name = "SmotrRolesView.findByRightUser", query = "SELECT s FROM SmotrRolesView s WHERE s.rightUser = :rightUser")
    , @NamedQuery(name = "SmotrRolesView.findBySearchRule", query = "SELECT s FROM SmotrRolesView s WHERE s.searchRule = :searchRule")
    , @NamedQuery(name = "SmotrRolesView.findByDhcpRole", query = "SELECT s FROM SmotrRolesView s WHERE s.dhcpRole = :dhcpRole")
    , @NamedQuery(name = "SmotrRolesView.findBySyslogRole", query = "SELECT s FROM SmotrRolesView s WHERE s.syslogRole = :syslogRole")
    , @NamedQuery(name = "SmotrRolesView.findByVpnRole", query = "SELECT s FROM SmotrRolesView s WHERE s.vpnRole = :vpnRole")
    , @NamedQuery(name = "SmotrRolesView.findByRaportRole", query = "SELECT s FROM SmotrRolesView s WHERE s.raportRole = :raportRole")
    , @NamedQuery(name = "SmotrRolesView.findByWhatsappRole", query = "SELECT s FROM SmotrRolesView s WHERE s.whatsappRole = :whatsappRole")
    , @NamedQuery(name = "SmotrRolesView.findByGponRole", query = "SELECT s FROM SmotrRolesView s WHERE s.gponRole = :gponRole")
    , @NamedQuery(name = "SmotrRolesView.findByIptvRole", query = "SELECT s FROM SmotrRolesView s WHERE s.iptvRole = :iptvRole")
    , @NamedQuery(name = "SmotrRolesView.findByGponStendRole", query = "SELECT s FROM SmotrRolesView s WHERE s.gponStendRole = :gponStendRole")
    , @NamedQuery(name = "SmotrRolesView.findByAlarmsRole", query = "SELECT s FROM SmotrRolesView s WHERE s.alarmsRole = :alarmsRole")
    , @NamedQuery(name = "SmotrRolesView.findByExportRole", query = "SELECT s FROM SmotrRolesView s WHERE s.exportRole = :exportRole")
    , @NamedQuery(name = "SmotrRolesView.findByMacRole", query = "SELECT s FROM SmotrRolesView s WHERE s.macRole = :macRole")
    , @NamedQuery(name = "SmotrRolesView.findByAdminRole", query = "SELECT s FROM SmotrRolesView s WHERE s.adminRole = :adminRole")
    , @NamedQuery(name = "SmotrRolesView.findByIpvpnRole", query = "SELECT s FROM SmotrRolesView s WHERE s.ipvpnRole = :ipvpnRole")})
public class SmotrRolesView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "alias")
    @Id
    private String alias;
    @Size(max = 32)
    @Column(name = "passwd")
    private String passwd;
    @Size(max = 2147483647)
    @Column(name = "right_user")
    private String rightUser;
    @Column(name = "search_rule")
    private BigInteger searchRule;
    @Column(name = "dhcp_role")
    private BigInteger dhcpRole;
    @Column(name = "syslog_role")
    private BigInteger syslogRole;
    @Column(name = "vpn_role")
    private BigInteger vpnRole;
    @Column(name = "raport_role")
    private BigInteger raportRole;
    @Column(name = "whatsapp_role")
    private BigInteger whatsappRole;
    @Column(name = "gpon_role")
    private BigInteger gponRole;
    @Column(name = "iptv_role")
    private BigInteger iptvRole;
    @Column(name = "gpon_stend_role")
    private BigInteger gponStendRole;
    @Column(name = "alarms_role")
    private BigInteger alarmsRole;
    @Column(name = "export_role")
    private BigInteger exportRole;
    @Column(name = "mac_role")
    private BigInteger macRole;
    @Column(name = "admin_role")
    private BigInteger adminRole;
    @Column(name = "ipvpn_role")
    private BigInteger ipvpnRole;

    public SmotrRolesView() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRightUser() {
        return rightUser;
    }

    public void setRightUser(String rightUser) {
        this.rightUser = rightUser;
    }

    public BigInteger getSearchRule() {
        return searchRule;
    }

    public void setSearchRule(BigInteger searchRule) {
        this.searchRule = searchRule;
    }

    public BigInteger getDhcpRole() {
        return dhcpRole;
    }

    public void setDhcpRole(BigInteger dhcpRole) {
        this.dhcpRole = dhcpRole;
    }

    public BigInteger getSyslogRole() {
        return syslogRole;
    }

    public void setSyslogRole(BigInteger syslogRole) {
        this.syslogRole = syslogRole;
    }

    public BigInteger getVpnRole() {
        return vpnRole;
    }

    public void setVpnRole(BigInteger vpnRole) {
        this.vpnRole = vpnRole;
    }

    public BigInteger getRaportRole() {
        return raportRole;
    }

    public void setRaportRole(BigInteger raportRole) {
        this.raportRole = raportRole;
    }

    public BigInteger getWhatsappRole() {
        return whatsappRole;
    }

    public void setWhatsappRole(BigInteger whatsappRole) {
        this.whatsappRole = whatsappRole;
    }

    public BigInteger getGponRole() {
        return gponRole;
    }

    public void setGponRole(BigInteger gponRole) {
        this.gponRole = gponRole;
    }

    public BigInteger getIptvRole() {
        return iptvRole;
    }

    public void setIptvRole(BigInteger iptvRole) {
        this.iptvRole = iptvRole;
    }

    public BigInteger getGponStendRole() {
        return gponStendRole;
    }

    public void setGponStendRole(BigInteger gponStendRole) {
        this.gponStendRole = gponStendRole;
    }

    public BigInteger getAlarmsRole() {
        return alarmsRole;
    }

    public void setAlarmsRole(BigInteger alarmsRole) {
        this.alarmsRole = alarmsRole;
    }

    public BigInteger getExportRole() {
        return exportRole;
    }

    public void setExportRole(BigInteger exportRole) {
        this.exportRole = exportRole;
    }

    public BigInteger getMacRole() {
        return macRole;
    }

    public void setMacRole(BigInteger macRole) {
        this.macRole = macRole;
    }

    public BigInteger getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(BigInteger adminRole) {
        this.adminRole = adminRole;
    }

    public BigInteger getIpvpnRole() {
        return ipvpnRole;
    }

    public void setIpvpnRole(BigInteger ipvpnRole) {
        this.ipvpnRole = ipvpnRole;
    }
    
}
