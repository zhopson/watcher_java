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
@Table(name = "smotr_logs_optinet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrLogsOptinetView.findAll", query = "SELECT s FROM SmotrLogsOptinetView s")
    , @NamedQuery(name = "SmotrLogsOptinetView.findById", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByHeader", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.header = :header")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByIpUser", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.ipUser = cast(?1 as inet) or s.macUser = ?2")
    , @NamedQuery(name = "SmotrLogsOptinetView.findIPByMacUser", query = "SELECT distinct s.ipUser FROM SmotrLogsOptinetView s WHERE s.macUser = :macUser and s.ipUser is not null")
    , @NamedQuery(name = "SmotrLogsOptinetView.findMACByIpUser", query = "SELECT distinct s.macUser FROM SmotrLogsOptinetView s WHERE s.ipUser = cast(?1 as inet)")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByMessage", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.message = :message")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByMacUser", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.macUser = :macUser")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByMacSwitch", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.macSwitch = :macSwitch")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByPortSwitch", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.portSwitch = :portSwitch")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByOption60", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.option60 = :option60")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByTime", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.time = :time")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByIPnTimeInterval", query = "SELECT s FROM SmotrLogsOptinetView s WHERE (s.ipUser = cast(?1 as inet) or s.macUser = ?2) and s.time >= ?3 and s.time <= ?4")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByTimeInterval", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.time >= :time1 and s.time <= :time2")
    , @NamedQuery(name = "SmotrLogsOptinetView.findByServer", query = "SELECT s FROM SmotrLogsOptinetView s WHERE s.server = :server")})
public class SmotrLogsOptinetView implements Serializable {

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
    //@Lob
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

    public SmotrLogsOptinetView() {
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

    public Object getIpUser() {
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
