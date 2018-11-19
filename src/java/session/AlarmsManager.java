/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CommentsImportTechnograd;
import entity.CommentsImportTechnogradArchive;
import entity.ImportTechnograd;
import java.util.Date;
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
import utils.StrucUser;

/**
 *
 * @author andrey-man
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AlarmsManager {
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public StrucUser structUser;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer AddComment(final String id,final String comment_alarms) {
        try {
//            Query query = em.createNativeQuery("delete from watcher.temp_ntp_ip_log where usr_zabbix=?1").setParameter(1, puser);
//            query.executeUpdate();

            ImportTechnograd tech = (ImportTechnograd) (em.createNamedQuery("ImportTechnograd.findById")
                                .setParameter("id", Integer.parseInt(id))
                                .getResultList()).get(0);

            structUser = StrucUser.getInstance();
            
            CommentsImportTechnograd cit = new CommentsImportTechnograd();
            cit.setFio(structUser.getFio());
            cit.setDate(new Date());
            cit.setTechId(tech);
            cit.setComments(comment_alarms);
            em.persist(cit);

            CommentsImportTechnogradArchive cita = new CommentsImportTechnogradArchive();
            cita.setFio(structUser.getFio());
            cita.setDate(new Date());
            cita.setTechId(tech);
            cita.setComments(comment_alarms);
            cita.setDateStartAlarm(tech.getDateAlarm());
            em.persist(cita);

            
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }        
}
