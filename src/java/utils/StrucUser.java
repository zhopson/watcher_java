/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author andrey-man
 */
public class StrucUser {
//        public String ID;
        private String Fio;
        private String Name;
        private String Pass;
        private String Service;
        private String Right;
        private int search_rule;
        private int dhcp_role;
        private int syslog_role;
        private int vpn_role;
        private int raport_role; // добавил Михайлов для Вкладки Рапорта ЦСПД
        private int whatsapp_role; // добавил Михайлов для Вкладки Рассылка whatsapp
        private int gpon_role; // добавил Михайлов для Вкладки Порты gpon
        private int iptv_role; // добавил Михайлов для Вкладки управление iptv
        private int gpon_stend_role; // добавил Михайлов для Вкладки Порты gpon стенд
        private int alarms_role; // добавил Михайлов для Вкладки Аварии устройств
        private int export_role; // добавил Михайлов для Вкладки Аварии устройств
        private int ntp_role; // добавил Михайлов для Вкладки ntp
        private int admin_role; // ---
        private int ipvpn_role; // ---    
//        public String syslogListGroup;
        
    private static StrucUser instance = null;
    public static StrucUser getInstance() {
        if (instance == null) {
            instance = new StrucUser();
        }
        return instance;
    }        

    public StrucUser() {
    }
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StrucUser(String Fio, String Name, String Pass, String Service, String Right, int search_rule, int dhcp_role, int syslog_role, int vpn_role, int raport_role, int whatsapp_role, int gpon_role, int iptv_role, int gpon_stend_role, int alarms_role, int export_role, int ntp_role, int admin_role, int ipvpn_role) {
        this.Fio = Fio;
        this.Name = Name;
        this.Pass = Pass;
        this.Service = Service;
        this.Right = Right;
        this.search_rule = search_rule;
        this.dhcp_role = dhcp_role;
        this.syslog_role = syslog_role;
        this.vpn_role = vpn_role;
        this.raport_role = raport_role;
        this.whatsapp_role = whatsapp_role;
        this.gpon_role = gpon_role;
        this.iptv_role = iptv_role;
        this.gpon_stend_role = gpon_stend_role;
        this.alarms_role = alarms_role;
        this.export_role = export_role;
        this.ntp_role = ntp_role;
        this.admin_role = admin_role;
        this.ipvpn_role = ipvpn_role;
        this.instance = this;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getFio() {
        return Fio;
    }
    
    public String getName() {
        return Name;
    }

    public String getPass() {
        return Pass;
    }

    public String getService() {
        return Service;
    }

    public String getRight() {
        return Right;
    }

    public int getSearch_rule() {
        return search_rule;
    }

    public int getDhcp_role() {
        return dhcp_role;
    }

    public int getSyslog_role() {
        return syslog_role;
    }

    public int getVpn_role() {
        return vpn_role;
    }

    public int getRaport_role() {
        return raport_role;
    }

    public int getWhatsapp_role() {
        return whatsapp_role;
    }

    public int getGpon_role() {
        return gpon_role;
    }

    public int getIptv_role() {
        return iptv_role;
    }

    public int getGpon_stend_role() {
        return gpon_stend_role;
    }

    public int getAlarms_role() {
        return alarms_role;
    }

    public int getExport_role() {
        return export_role;
    }

    public int getNtp_role() {
        return ntp_role;
    }

    public int getAdmin_role() {
        return admin_role;
    }

    public int getIpvpn_role() {
        return ipvpn_role;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public void setFio(String Fio) {
        this.Fio = Fio;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public void setService(String Service) {
        this.Service = Service;
    }

    public void setRight(String Right) {
        this.Right = Right;
    }

    public void setSearch_rule(int search_rule) {
        this.search_rule = search_rule;
    }

    public void setDhcp_role(int dhcp_role) {
        this.dhcp_role = dhcp_role;
    }

    public void setSyslog_role(int syslog_role) {
        this.syslog_role = syslog_role;
    }

    public void setVpn_role(int vpn_role) {
        this.vpn_role = vpn_role;
    }

    public void setRaport_role(int raport_role) {
        this.raport_role = raport_role;
    }

    public void setWhatsapp_role(int whatsapp_role) {
        this.whatsapp_role = whatsapp_role;
    }

    public void setGpon_role(int gpon_role) {
        this.gpon_role = gpon_role;
    }

    public void setIptv_role(int iptv_role) {
        this.iptv_role = iptv_role;
    }

    public void setGpon_stend_role(int gpon_stend_role) {
        this.gpon_stend_role = gpon_stend_role;
    }

    public void setAlarms_role(int alarms_role) {
        this.alarms_role = alarms_role;
    }

    public void setExport_role(int export_role) {
        this.export_role = export_role;
    }

    public void setNtp_role(int ntp_role) {
        this.ntp_role = ntp_role;
    }

    public void setAdmin_role(int admin_role) {
        this.admin_role = admin_role;
    }

    public void setIpvpn_role(int ipvpn_role) {
        this.ipvpn_role = ipvpn_role;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        
        
}
