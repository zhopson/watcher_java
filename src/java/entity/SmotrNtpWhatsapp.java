/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "watcher.smotr_ntp_whatsapp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmotrNtpWhatsapp.findAll", query = "SELECT s FROM SmotrNtpWhatsapp s")
    , @NamedQuery(name = "SmotrNtpWhatsapp.findById", query = "SELECT s FROM SmotrNtpWhatsapp s WHERE s.id = :id")
    , @NamedQuery(name = "SmotrNtpWhatsapp.findByText", query = "SELECT s FROM SmotrNtpWhatsapp s WHERE s.text = :text")
    , @NamedQuery(name = "SmotrNtpWhatsapp.findByGroupId", query = "SELECT s FROM SmotrNtpWhatsapp s WHERE s.groupId = :groupId")
    , @NamedQuery(name = "SmotrNtpWhatsapp.findByTimestamp", query = "SELECT s FROM SmotrNtpWhatsapp s WHERE s.timestamp = :timestamp")
    , @NamedQuery(name = "SmotrNtpWhatsapp.findByOperationTimestamp", query = "SELECT s FROM SmotrNtpWhatsapp s WHERE s.operationTimestamp = :operationTimestamp")
    , @NamedQuery(name = "SmotrNtpWhatsapp.findByMessageProcessed", query = "SELECT s FROM SmotrNtpWhatsapp s WHERE s.messageProcessed = :messageProcessed")})
public class SmotrNtpWhatsapp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "text")
    private String text;
    @Size(max = 30)
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "operation_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationTimestamp;
    @Column(name = "message_processed")
    private Integer messageProcessed;

    public SmotrNtpWhatsapp() {
    }

    public SmotrNtpWhatsapp(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getOperationTimestamp() {
        return operationTimestamp;
    }

    public void setOperationTimestamp(Date operationTimestamp) {
        this.operationTimestamp = operationTimestamp;
    }

    public Integer getMessageProcessed() {
        return messageProcessed;
    }

    public void setMessageProcessed(Integer messageProcessed) {
        this.messageProcessed = messageProcessed;
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
        if (!(object instanceof SmotrNtpWhatsapp)) {
            return false;
        }
        SmotrNtpWhatsapp other = (SmotrNtpWhatsapp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SmotrNtpWhatsapp[ id=" + id + " ]";
    }
    
}
