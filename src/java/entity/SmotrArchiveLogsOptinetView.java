/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "smotr_archive_logs_optinet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrArchiveLogsOptinetView.findAll", query = "SELECT s FROM SmotrArchiveLogsOptinetView s")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findById", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByHeader", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.header = :header")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByMessage", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.message = :message")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByMacUser", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.macUser = :macUser")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findIPByMacUser", query = "SELECT distinct s.ipUser FROM SmotrArchiveLogsOptinetView s WHERE s.macUser = :macUser and s.ipUser is not null")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findMACByIpUser", query = "SELECT distinct s.macUser FROM SmotrArchiveLogsOptinetView s WHERE s.ipUser = cast(?1 as inet)")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByIpUser", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE (s.ipUser = cast(?1 as inet) or s.macUser = ?2)")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByMacSwitch", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.macSwitch = :macSwitch")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByPortSwitch", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.portSwitch = :portSwitch")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByOption60", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.option60 = :option60")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByTime", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.time = :time")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByIPnTimeInterval", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE (s.ipUser = cast(?1 as inet)  or s.macUser = ?2) and s.time >= ?3 and s.time <= ?4")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByTimeInterval", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.time >= :time1 and s.time <= :time2")
    , @NamedQuery(name = "SmotrArchiveLogsOptinetView.findByServer", query = "SELECT s FROM SmotrArchiveLogsOptinetView s WHERE s.server = :server")})
public class SmotrArchiveLogsOptinetView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private BigInteger id;
    @Size(max = 20)
    @Column(name = "header")
    private String header;
    @Size(max = 2147483647)
    @Column(name = "message")
    private String message;
    @Size(max = 2147483647)
    @Column(name = "mac_user")
    private String macUser;
    @Size(max = 20)
    @Column(name = "ip_user")
    private String ipUser;
    @Size(max = 2147483647)
    @Column(name = "mac_switch")
    private String macSwitch;
    @Column(name = "port_switch")
    private BigInteger portSwitch;
    @Size(max = 2147483647)
    @Column(name = "option60")
    private String option60;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Size(max = 2147483647)
    @Column(name = "server")
    private String server;

    public SmotrArchiveLogsOptinetView() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMacUser() {
        return macUser;
    }

    public void setMacUser(String macUser) {
        this.macUser = macUser;
    }

    public String getIpUser() {
        return ipUser;
    }

    public void setIpUser(String ipUser) {
        this.ipUser = ipUser;
    }

    public String getMacSwitch() {
        return macSwitch;
    }

    public void setMacSwitch(String macSwitch) {
        this.macSwitch = macSwitch;
    }

    public BigInteger getPortSwitch() {
        return portSwitch;
    }

    public void setPortSwitch(BigInteger portSwitch) {
        this.portSwitch = portSwitch;
    }

    public String getOption60() {
        return option60;
    }

    public void setOption60(String option60) {
        this.option60 = option60;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
    
}
