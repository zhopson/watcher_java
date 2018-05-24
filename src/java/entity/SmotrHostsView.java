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
@Table(name = "watcher.smotr_hosts_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrHostsView.findAll", query = "SELECT s FROM SmotrHostsView s")
    , @NamedQuery(name = "SmotrHostsView.findByUsername", query = "SELECT s FROM SmotrHostsView s WHERE s.username = :username")
    , @NamedQuery(name = "SmotrHostsView.findByName", query = "SELECT s FROM SmotrHostsView s WHERE s.name = :name")
    , @NamedQuery(name = "SmotrHostsView.findByPassword", query = "SELECT s FROM SmotrHostsView s WHERE s.password = :password")
    , @NamedQuery(name = "SmotrHostsView.findByPermission", query = "SELECT s FROM SmotrHostsView s WHERE s.permission = :permission")
    , @NamedQuery(name = "SmotrHostsView.findById", query = "SELECT s FROM SmotrHostsView s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrHostsView.findByGroupName", query = "SELECT s FROM SmotrHostsView s WHERE s.groupName = :groupName")
    , @NamedQuery(name = "SmotrHostsView.findByHost", query = "SELECT s FROM SmotrHostsView s WHERE s.host = :host")
    , @NamedQuery(name = "SmotrHostsView.findByDescription", query = "SELECT s FROM SmotrHostsView s WHERE s.description = :description")
    , @NamedQuery(name = "SmotrHostsView.findByUid", query = "SELECT s FROM SmotrHostsView s WHERE s.uid = :uid")
    , @NamedQuery(name = "SmotrHostsView.findByHostid", query = "SELECT s FROM SmotrHostsView s WHERE s.hostid = :hostid")
    , @NamedQuery(name = "SmotrHostsView.findByUserid", query = "SELECT s FROM SmotrHostsView s WHERE s.userid = :userid")
    , @NamedQuery(name = "SmotrHostsView.getGroups", query = "SELECT s.groupId,s.groupName FROM SmotrHostsView s WHERE s.username = 'admin' group by s.groupId,s.groupName order by s.groupName")
    , @NamedQuery(name = "SmotrHostsView.getHosts", query = "SELECT s.host,s.groupId,s.description FROM SmotrHostsView s group by s.host,s.groupId,s.description order by s.host")
    , @NamedQuery(name = "SmotrHostsView.findByGroupId", query = "SELECT s FROM SmotrHostsView s WHERE s.groupId = :groupId")})
public class SmotrHostsView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "username")
    @Id
    private String username;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 32)
    @Column(name = "password")
    private String password;
    @Column(name = "permission")
    private Integer permission;
    @Column(name = "id")
    private BigInteger id;
    @Size(max = 255)
    @Column(name = "group_name")
    private String groupName;
    @Size(max = 128)
    @Column(name = "host")
    private String host;
    @Size(max = 128)
    @Column(name = "description")
    private String description;
    @Column(name = "uid")
    private BigInteger uid;
    @Column(name = "hostid")
    private BigInteger hostid;
    @Column(name = "userid")
    private BigInteger userid;
    @Column(name = "group_id")
    private BigInteger groupId;

    public SmotrHostsView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
        this.uid = uid;
    }

    public BigInteger getHostid() {
        return hostid;
    }

    public void setHostid(BigInteger hostid) {
        this.hostid = hostid;
    }

    public BigInteger getUserid() {
        return userid;
    }

    public void setUserid(BigInteger userid) {
        this.userid = userid;
    }

    public BigInteger getGroupId() {
        return groupId;
    }

    public void setGroupId(BigInteger groupId) {
        this.groupId = groupId;
    }
    
}
