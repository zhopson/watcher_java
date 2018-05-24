/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "watcher.logs_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogsView.findAll", query = "SELECT l FROM LogsView l")
    , @NamedQuery(name = "LogsView.findByDate", query = "SELECT l FROM LogsView l WHERE l.date = :date")
    , @NamedQuery(name = "LogsView.findByFacility", query = "SELECT l FROM LogsView l WHERE l.facility = :facility")
    , @NamedQuery(name = "LogsView.findByLevel", query = "SELECT l FROM LogsView l WHERE l.level = :level")
    , @NamedQuery(name = "LogsView.findByHost", query = "SELECT l FROM LogsView l WHERE l.host = :host")
    , @NamedQuery(name = "LogsView.findByProgram", query = "SELECT l FROM LogsView l WHERE l.program = :program")
    , @NamedQuery(name = "LogsView.findByPid", query = "SELECT l FROM LogsView l WHERE l.pid = :pid")
    , @NamedQuery(name = "LogsView.findByMessage", query = "SELECT l FROM LogsView l WHERE l.message = :message")
    , @NamedQuery(name = "LogsView.findByMsg", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l where l.date>=:date1  and l.date<=:date2 and l.message like :msg ORDER BY l.date DESC")
    , @NamedQuery(name = "LogsView.findByLev", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l where l.level=:level and l.date>=:date1  and l.date<=:date2 and l.message like :msg order by l.date DESC")
    , @NamedQuery(name = "LogsView.findByGr", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l, SmotrHostsView s where s.host = l.host "
                       + "and l.date>=:date1 and l.date<=:date2 and l.message like :msg "
                       + "and s.groupId = :gr_id and s.username = 'admin' order by l.date DESC")
//    , @NamedQuery(name = "LogsView.findByGr", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l "
//                       + "where l.date>=:date1 and l.date<=:date2 and l.message like :msg and l.host in (select s.host from SmotrHostsView s where "
//                       + "s.groupId = :gr_id and s.username = 'admin') order by l.date DESC")
    , @NamedQuery(name = "LogsView.findByGrNLev", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l, SmotrHostsView s where s.host = l.host "
                       + "and l.level=:level and l.date>=:date1 and l.date<=:date2 and l.message like :msg "
                       + "and s.groupId = :gr_id and s.username = 'admin' order by l.date DESC")
    , @NamedQuery(name = "LogsView.findByHostNMsg", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l where l.host=:host and l.date>=:date1  and l.date<=:date2 and l.message like :msg order by l.date DESC")
    , @NamedQuery(name = "LogsView.findByHostNLev", query = "select l.name,l.facility,l.level,l.host,l.date,l.message from LogsView l where l.host=:host and l.level=:level and l.date>=:date1  and l.date<=:date2 and l.message like :msg order by l.date DESC")
    , @NamedQuery(name = "LogsView.findByName", query = "SELECT l FROM LogsView l WHERE l.name = :name")})

public class LogsView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 30)
    @Column(name = "facility")
    private String facility;
    @Size(max = 30)
    @Column(name = "level")
    private String level;
    @Size(max = 30)
    @Column(name = "host")
    @Id
    private String host;
    @Size(max = 100)
    @Column(name = "program")
    private String program;
    @Column(name = "pid")
    private Integer pid;
    @Size(max = 2147483647)
    @Column(name = "message")
    private String message;
    @Size(max = 128)
    @Column(name = "name")
    private String name;
    
   @JoinTable(
         name="watcher.smotr_hosts_view",
         joinColumns = {@JoinColumn(name="host", referencedColumnName="host")},
         inverseJoinColumns = {@JoinColumn(name="host", referencedColumnName="host")}
   )
   @ManyToMany
   private final List<LogsView> dependencies = new ArrayList<LogsView>();

    public LogsView() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
