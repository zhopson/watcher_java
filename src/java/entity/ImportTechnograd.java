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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "port_info.import_technograd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportTechnograd.findAll", query = "SELECT i FROM ImportTechnograd i")
//    , @NamedQuery(name = "ImportTechnograd.findNoPPPoE", query = "SELECT i.marka,i.uzel,a.chantype,i.ipaddr,i.numPorts,i.usedPorts,f.net FROM ImportTechnograd i "
//                        +"left join NtpAdslIp a inner join DslamNets f WHERE a.chantype!=?1 and i.uzel!=?2 and i.uzel!=?3 order by i.ipaddr")
//    , @NamedQuery(name = "ImportTechnograd.findPPPoE", query = "SELECT i.marka,i.uzel,a.chantype,i.ipaddr,i.numPorts,i.usedPorts,f.net FROM ImportTechnograd i "
//                        +"LEFT join NtpAdslIp a inner join DslamNets f order by i.ipaddr")
    , @NamedQuery(name = "ImportTechnograd.findMarkaByIP", query = "SELECT i.marka FROM ImportTechnograd i WHERE i.ipaddr = :ipaddr")
    , @NamedQuery(name = "ImportTechnograd.findById", query = "SELECT i FROM ImportTechnograd i WHERE i.id = :id")
    , @NamedQuery(name = "ImportTechnograd.findByFifial", query = "SELECT i FROM ImportTechnograd i WHERE i.fifial = :fifial")
    , @NamedQuery(name = "ImportTechnograd.findByUzel", query = "SELECT i FROM ImportTechnograd i WHERE i.uzel = :uzel")
    , @NamedQuery(name = "ImportTechnograd.findByNetType", query = "SELECT i FROM ImportTechnograd i WHERE i.netType = :netType")
    , @NamedQuery(name = "ImportTechnograd.findByNomOborud", query = "SELECT i FROM ImportTechnograd i WHERE i.nomOborud = :nomOborud")
    , @NamedQuery(name = "ImportTechnograd.findByVidOborud", query = "SELECT i FROM ImportTechnograd i WHERE i.vidOborud = :vidOborud")
    , @NamedQuery(name = "ImportTechnograd.findByTip", query = "SELECT i FROM ImportTechnograd i WHERE i.tip = :tip")
    , @NamedQuery(name = "ImportTechnograd.findByMarka", query = "SELECT i FROM ImportTechnograd i WHERE i.marka = :marka")
    , @NamedQuery(name = "ImportTechnograd.findByLogSost", query = "SELECT i FROM ImportTechnograd i WHERE i.logSost = :logSost")
    , @NamedQuery(name = "ImportTechnograd.findByTechSost", query = "SELECT i FROM ImportTechnograd i WHERE i.techSost = :techSost")
    , @NamedQuery(name = "ImportTechnograd.findByNumPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.numPorts = :numPorts")
    , @NamedQuery(name = "ImportTechnograd.findByAbonPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.abonPorts = :abonPorts")
    , @NamedQuery(name = "ImportTechnograd.findByUsedPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.usedPorts = :usedPorts")
    , @NamedQuery(name = "ImportTechnograd.findByFreePorts", query = "SELECT i FROM ImportTechnograd i WHERE i.freePorts = :freePorts")
    , @NamedQuery(name = "ImportTechnograd.findByHurtsPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.hurtsPorts = :hurtsPorts")
    , @NamedQuery(name = "ImportTechnograd.findByBronPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.bronPorts = :bronPorts")
    , @NamedQuery(name = "ImportTechnograd.findByOtherPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.otherPorts = :otherPorts")
    , @NamedQuery(name = "ImportTechnograd.findByTechPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.techPorts = :techPorts")
    , @NamedQuery(name = "ImportTechnograd.findByMonitPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.monitPorts = :monitPorts")
    , @NamedQuery(name = "ImportTechnograd.findByClosedPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.closedPorts = :closedPorts")
    , @NamedQuery(name = "ImportTechnograd.findByInetPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.inetPorts = :inetPorts")
    , @NamedQuery(name = "ImportTechnograd.findByIptvPorts", query = "SELECT i FROM ImportTechnograd i WHERE i.iptvPorts = :iptvPorts")
    , @NamedQuery(name = "ImportTechnograd.findByAdrUstan", query = "SELECT i FROM ImportTechnograd i WHERE i.adrUstan = :adrUstan")
    , @NamedQuery(name = "ImportTechnograd.findByDom", query = "SELECT i FROM ImportTechnograd i WHERE i.dom = :dom")
    , @NamedQuery(name = "ImportTechnograd.findByPodyezd", query = "SELECT i FROM ImportTechnograd i WHERE i.podyezd = :podyezd")
    , @NamedQuery(name = "ImportTechnograd.findByEtazh", query = "SELECT i FROM ImportTechnograd i WHERE i.etazh = :etazh")
    , @NamedQuery(name = "ImportTechnograd.findByMestoUstan", query = "SELECT i FROM ImportTechnograd i WHERE i.mestoUstan = :mestoUstan")
    , @NamedQuery(name = "ImportTechnograd.findByPredelObsl", query = "SELECT i FROM ImportTechnograd i WHERE i.predelObsl = :predelObsl")
    , @NamedQuery(name = "ImportTechnograd.findByIpaddr", query = "SELECT i FROM ImportTechnograd i WHERE i.ipaddr = :ipaddr")
    , @NamedQuery(name = "ImportTechnograd.findByNumInvestProj", query = "SELECT i FROM ImportTechnograd i WHERE i.numInvestProj = :numInvestProj")
    , @NamedQuery(name = "ImportTechnograd.findBySerial", query = "SELECT i FROM ImportTechnograd i WHERE i.serial = :serial")
    , @NamedQuery(name = "ImportTechnograd.findByInventNum", query = "SELECT i FROM ImportTechnograd i WHERE i.inventNum = :inventNum")
    , @NamedQuery(name = "ImportTechnograd.findByInventNumSpr", query = "SELECT i FROM ImportTechnograd i WHERE i.inventNumSpr = :inventNumSpr")
    , @NamedQuery(name = "ImportTechnograd.findByNumInvestLastAkt", query = "SELECT i FROM ImportTechnograd i WHERE i.numInvestLastAkt = :numInvestLastAkt")
    , @NamedQuery(name = "ImportTechnograd.findByDateLastAkt", query = "SELECT i FROM ImportTechnograd i WHERE i.dateLastAkt = :dateLastAkt")
    , @NamedQuery(name = "ImportTechnograd.findByNumLastInvSpr", query = "SELECT i FROM ImportTechnograd i WHERE i.numLastInvSpr = :numLastInvSpr")
    , @NamedQuery(name = "ImportTechnograd.findByDateLastInvSpr", query = "SELECT i FROM ImportTechnograd i WHERE i.dateLastInvSpr = :dateLastInvSpr")
    , @NamedQuery(name = "ImportTechnograd.findByDateTechGotovn", query = "SELECT i FROM ImportTechnograd i WHERE i.dateTechGotovn = :dateTechGotovn")
    , @NamedQuery(name = "ImportTechnograd.findByPrin", query = "SELECT i FROM ImportTechnograd i WHERE i.prin = :prin")
    , @NamedQuery(name = "ImportTechnograd.findByAts", query = "SELECT i FROM ImportTechnograd i WHERE i.ats = :ats")
    , @NamedQuery(name = "ImportTechnograd.findByKross", query = "SELECT i FROM ImportTechnograd i WHERE i.kross = :kross")
    , @NamedQuery(name = "ImportTechnograd.findByKrossSeti", query = "SELECT i FROM ImportTechnograd i WHERE i.krossSeti = :krossSeti")
    , @NamedQuery(name = "ImportTechnograd.findBySupportHighSpeed", query = "SELECT i FROM ImportTechnograd i WHERE i.supportHighSpeed = :supportHighSpeed")
    , @NamedQuery(name = "ImportTechnograd.findByDopInf", query = "SELECT i FROM ImportTechnograd i WHERE i.dopInf = :dopInf")
    , @NamedQuery(name = "ImportTechnograd.findByHostname", query = "SELECT i FROM ImportTechnograd i WHERE i.hostname = :hostname")
    , @NamedQuery(name = "ImportTechnograd.findByDateModify", query = "SELECT i FROM ImportTechnograd i WHERE i.dateModify = :dateModify")
    , @NamedQuery(name = "ImportTechnograd.findByHouseId", query = "SELECT i FROM ImportTechnograd i WHERE i.houseId = :houseId")
    , @NamedQuery(name = "ImportTechnograd.findByModeId", query = "SELECT i FROM ImportTechnograd i WHERE i.modeId = :modeId")
    , @NamedQuery(name = "ImportTechnograd.findBySysDeleted", query = "SELECT i FROM ImportTechnograd i WHERE i.sysDeleted = :sysDeleted")
    , @NamedQuery(name = "ImportTechnograd.findBySysActive", query = "SELECT i FROM ImportTechnograd i WHERE i.sysActive = :sysActive")
    , @NamedQuery(name = "ImportTechnograd.findByDateAlarm", query = "SELECT i FROM ImportTechnograd i WHERE i.dateAlarm = :dateAlarm")
    , @NamedQuery(name = "ImportTechnograd.findByIsAlarm", query = "SELECT i FROM ImportTechnograd i WHERE i.isAlarm = :isAlarm")
    , @NamedQuery(name = "ImportTechnograd.findByDateLongPing", query = "SELECT i FROM ImportTechnograd i WHERE i.dateLongPing = :dateLongPing")
    , @NamedQuery(name = "ImportTechnograd.findByIsLongPing", query = "SELECT i FROM ImportTechnograd i WHERE i.isLongPing = :isLongPing")
    , @NamedQuery(name = "ImportTechnograd.findByPingLength", query = "SELECT i FROM ImportTechnograd i WHERE i.pingLength = :pingLength")})
public class ImportTechnograd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 50)
    @Column(name = "fifial")
    private String fifial;
    @Size(max = 50)
    @Column(name = "uzel")
    private String uzel;
    @Size(max = 60)
    @Column(name = "net_type")
    private String netType;
    @Size(max = 50)
    @Column(name = "nom_oborud")
    private String nomOborud;
    @Size(max = 50)
    @Column(name = "vid_oborud")
    private String vidOborud;
    @Size(max = 60)
    @Column(name = "tip")
    private String tip;
    @Size(max = 50)
    @Column(name = "marka")
    private String marka;
    @Size(max = 40)
    @Column(name = "log_sost")
    private String logSost;
    @Size(max = 40)
    @Column(name = "tech_sost")
    private String techSost;
    @Column(name = "num_ports")
    private Integer numPorts;
    @Column(name = "abon_ports")
    private Integer abonPorts;
    @Column(name = "used_ports")
    private Integer usedPorts;
    @Column(name = "free_ports")
    private Integer freePorts;
    @Column(name = "hurts_ports")
    private Integer hurtsPorts;
    @Column(name = "bron_ports")
    private Integer bronPorts;
    @Column(name = "other_ports")
    private Integer otherPorts;
    @Column(name = "tech_ports")
    private Integer techPorts;
    @Column(name = "monit_ports")
    private Integer monitPorts;
    @Column(name = "closed_ports")
    private Integer closedPorts;
    @Column(name = "inet_ports")
    private Integer inetPorts;
    @Column(name = "iptv_ports")
    private Integer iptvPorts;
    @Size(max = 80)
    @Column(name = "adr_ustan")
    private String adrUstan;
    @Size(max = 20)
    @Column(name = "dom")
    private String dom;
    @Size(max = 5)
    @Column(name = "podyezd")
    private String podyezd;
    @Size(max = 5)
    @Column(name = "etazh")
    private String etazh;
    @Size(max = 2147483647)
    @Column(name = "mesto_ustan")
    private String mestoUstan;
    @Size(max = 2147483647)
    @Column(name = "predel_obsl")
    private String predelObsl;
    @Size(max = 25)
    @Column(name = "ipaddr")
    private String ipaddr;
    @Size(max = 25)
    @Column(name = "num_invest_proj")
    private String numInvestProj;
    @Size(max = 50)
    @Column(name = "serial")
    private String serial;
    @Size(max = 40)
    @Column(name = "invent_num")
    private String inventNum;
    @Size(max = 40)
    @Column(name = "invent_num_spr")
    private String inventNumSpr;
    @Size(max = 40)
    @Column(name = "num_invest_last_akt")
    private String numInvestLastAkt;
    @Column(name = "date_last_akt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastAkt;
    @Size(max = 40)
    @Column(name = "num_last_inv_spr")
    private String numLastInvSpr;
    @Column(name = "date_last_inv_spr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastInvSpr;
    @Column(name = "date_tech_gotovn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTechGotovn;
    @Size(max = 50)
    @Column(name = "prin")
    private String prin;
    @Size(max = 40)
    @Column(name = "ats")
    private String ats;
    @Size(max = 40)
    @Column(name = "kross")
    private String kross;
    @Size(max = 40)
    @Column(name = "kross_seti")
    private String krossSeti;
    @Size(max = 20)
    @Column(name = "support_high_speed")
    private String supportHighSpeed;
    @Size(max = 200)
    @Column(name = "dop_inf")
    private String dopInf;
    @Size(max = 50)
    @Column(name = "hostname")
    private String hostname;
    @Column(name = "date_modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Size(max = 30)
    @Column(name = "house_id")
    private String houseId;
    @Column(name = "mode_id")
    private Integer modeId;
    @Column(name = "sys_deleted")
    private Boolean sysDeleted;
    @Column(name = "sys_active")
    private Boolean sysActive;
    @Column(name = "date_alarm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAlarm;
    @Column(name = "is_alarm")
    private Boolean isAlarm;
    @Column(name = "date_long_ping")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLongPing;
    @Column(name = "is_long_ping")
    private Boolean isLongPing;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ping_length")
    private Float pingLength;

    @JoinTable(
            name = "port_info.ntp_adsl_ip",
            joinColumns = {
                @JoinColumn(name = "ip", referencedColumnName = "ipaddr")},
            inverseJoinColumns = {
                @JoinColumn(name = "ipaddr", referencedColumnName = "ip")}
    )
    @OneToOne
    private final List<ImportTechnograd> dependenciesNtpAdslIp = new ArrayList<ImportTechnograd>();

    @JoinTable(
            name = "port_info.dslam_nets",
            joinColumns = {
                @JoinColumn(name = "ipaddr", referencedColumnName = "ipaddr")},
            inverseJoinColumns = {
                @JoinColumn(name = "ipaddr", referencedColumnName = "ipaddr")}
    )
    @OneToOne
    private final List<ImportTechnograd> dependenciesDslamNets = new ArrayList<ImportTechnograd>();
    
    public ImportTechnograd() {
    }

    public ImportTechnograd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFifial() {
        return fifial;
    }

    public void setFifial(String fifial) {
        this.fifial = fifial;
    }

    public String getUzel() {
        return uzel;
    }

    public void setUzel(String uzel) {
        this.uzel = uzel;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getNomOborud() {
        return nomOborud;
    }

    public void setNomOborud(String nomOborud) {
        this.nomOborud = nomOborud;
    }

    public String getVidOborud() {
        return vidOborud;
    }

    public void setVidOborud(String vidOborud) {
        this.vidOborud = vidOborud;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getLogSost() {
        return logSost;
    }

    public void setLogSost(String logSost) {
        this.logSost = logSost;
    }

    public String getTechSost() {
        return techSost;
    }

    public void setTechSost(String techSost) {
        this.techSost = techSost;
    }

    public Integer getNumPorts() {
        return numPorts;
    }

    public void setNumPorts(Integer numPorts) {
        this.numPorts = numPorts;
    }

    public Integer getAbonPorts() {
        return abonPorts;
    }

    public void setAbonPorts(Integer abonPorts) {
        this.abonPorts = abonPorts;
    }

    public Integer getUsedPorts() {
        return usedPorts;
    }

    public void setUsedPorts(Integer usedPorts) {
        this.usedPorts = usedPorts;
    }

    public Integer getFreePorts() {
        return freePorts;
    }

    public void setFreePorts(Integer freePorts) {
        this.freePorts = freePorts;
    }

    public Integer getHurtsPorts() {
        return hurtsPorts;
    }

    public void setHurtsPorts(Integer hurtsPorts) {
        this.hurtsPorts = hurtsPorts;
    }

    public Integer getBronPorts() {
        return bronPorts;
    }

    public void setBronPorts(Integer bronPorts) {
        this.bronPorts = bronPorts;
    }

    public Integer getOtherPorts() {
        return otherPorts;
    }

    public void setOtherPorts(Integer otherPorts) {
        this.otherPorts = otherPorts;
    }

    public Integer getTechPorts() {
        return techPorts;
    }

    public void setTechPorts(Integer techPorts) {
        this.techPorts = techPorts;
    }

    public Integer getMonitPorts() {
        return monitPorts;
    }

    public void setMonitPorts(Integer monitPorts) {
        this.monitPorts = monitPorts;
    }

    public Integer getClosedPorts() {
        return closedPorts;
    }

    public void setClosedPorts(Integer closedPorts) {
        this.closedPorts = closedPorts;
    }

    public Integer getInetPorts() {
        return inetPorts;
    }

    public void setInetPorts(Integer inetPorts) {
        this.inetPorts = inetPorts;
    }

    public Integer getIptvPorts() {
        return iptvPorts;
    }

    public void setIptvPorts(Integer iptvPorts) {
        this.iptvPorts = iptvPorts;
    }

    public String getAdrUstan() {
        return adrUstan;
    }

    public void setAdrUstan(String adrUstan) {
        this.adrUstan = adrUstan;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getPodyezd() {
        return podyezd;
    }

    public void setPodyezd(String podyezd) {
        this.podyezd = podyezd;
    }

    public String getEtazh() {
        return etazh;
    }

    public void setEtazh(String etazh) {
        this.etazh = etazh;
    }

    public String getMestoUstan() {
        return mestoUstan;
    }

    public void setMestoUstan(String mestoUstan) {
        this.mestoUstan = mestoUstan;
    }

    public String getPredelObsl() {
        return predelObsl;
    }

    public void setPredelObsl(String predelObsl) {
        this.predelObsl = predelObsl;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getNumInvestProj() {
        return numInvestProj;
    }

    public void setNumInvestProj(String numInvestProj) {
        this.numInvestProj = numInvestProj;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getInventNum() {
        return inventNum;
    }

    public void setInventNum(String inventNum) {
        this.inventNum = inventNum;
    }

    public String getInventNumSpr() {
        return inventNumSpr;
    }

    public void setInventNumSpr(String inventNumSpr) {
        this.inventNumSpr = inventNumSpr;
    }

    public String getNumInvestLastAkt() {
        return numInvestLastAkt;
    }

    public void setNumInvestLastAkt(String numInvestLastAkt) {
        this.numInvestLastAkt = numInvestLastAkt;
    }

    public Date getDateLastAkt() {
        return dateLastAkt;
    }

    public void setDateLastAkt(Date dateLastAkt) {
        this.dateLastAkt = dateLastAkt;
    }

    public String getNumLastInvSpr() {
        return numLastInvSpr;
    }

    public void setNumLastInvSpr(String numLastInvSpr) {
        this.numLastInvSpr = numLastInvSpr;
    }

    public Date getDateLastInvSpr() {
        return dateLastInvSpr;
    }

    public void setDateLastInvSpr(Date dateLastInvSpr) {
        this.dateLastInvSpr = dateLastInvSpr;
    }

    public Date getDateTechGotovn() {
        return dateTechGotovn;
    }

    public void setDateTechGotovn(Date dateTechGotovn) {
        this.dateTechGotovn = dateTechGotovn;
    }

    public String getPrin() {
        return prin;
    }

    public void setPrin(String prin) {
        this.prin = prin;
    }

    public String getAts() {
        return ats;
    }

    public void setAts(String ats) {
        this.ats = ats;
    }

    public String getKross() {
        return kross;
    }

    public void setKross(String kross) {
        this.kross = kross;
    }

    public String getKrossSeti() {
        return krossSeti;
    }

    public void setKrossSeti(String krossSeti) {
        this.krossSeti = krossSeti;
    }

    public String getSupportHighSpeed() {
        return supportHighSpeed;
    }

    public void setSupportHighSpeed(String supportHighSpeed) {
        this.supportHighSpeed = supportHighSpeed;
    }

    public String getDopInf() {
        return dopInf;
    }

    public void setDopInf(String dopInf) {
        this.dopInf = dopInf;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public Boolean getSysDeleted() {
        return sysDeleted;
    }

    public void setSysDeleted(Boolean sysDeleted) {
        this.sysDeleted = sysDeleted;
    }

    public Boolean getSysActive() {
        return sysActive;
    }

    public void setSysActive(Boolean sysActive) {
        this.sysActive = sysActive;
    }

    public Date getDateAlarm() {
        return dateAlarm;
    }

    public void setDateAlarm(Date dateAlarm) {
        this.dateAlarm = dateAlarm;
    }

    public Boolean getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(Boolean isAlarm) {
        this.isAlarm = isAlarm;
    }

    public Date getDateLongPing() {
        return dateLongPing;
    }

    public void setDateLongPing(Date dateLongPing) {
        this.dateLongPing = dateLongPing;
    }

    public Boolean getIsLongPing() {
        return isLongPing;
    }

    public void setIsLongPing(Boolean isLongPing) {
        this.isLongPing = isLongPing;
    }

    public Float getPingLength() {
        return pingLength;
    }

    public void setPingLength(Float pingLength) {
        this.pingLength = pingLength;
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
        if (!(object instanceof ImportTechnograd)) {
            return false;
        }
        ImportTechnograd other = (ImportTechnograd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ImportTechnograd[ id=" + id + " ]";
    }
    
}
