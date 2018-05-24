/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andrey-man
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NtpManager {
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer Run_ntp_ip_call_logs(final String pip,final String puser) {
        try {
            Query query = em.createNativeQuery("select watcher.c_ntp_ip_call(?1,?2)").setParameter(1, pip).setParameter(2, puser);
            query.getSingleResult(); //executeUpdate();
            
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer Run_ntp_ip_call_status(final String pip,final String puser) {
        try {
            Query query = em.createNativeQuery("select watcher.c_ntp_ip_call(?1,?2,1)").setParameter(1, pip).setParameter(2, puser);
            query.getSingleResult(); //executeUpdate();
            
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer del_ntp_ip_temp(final String puser) {
        try {
            Query query = em.createNativeQuery("delete from watcher.temp_ntp_ip_log where usr_zabbix=?1").setParameter(1, puser);
            query.executeUpdate();
            
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }    
    
}
