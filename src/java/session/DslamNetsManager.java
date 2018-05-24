/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.DslamPortBinds;
import entity.DslamNets;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andrey-man
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DslamNetsManager {
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer UpdateNet(final String phost,final String pnet) {
        try {
            List resultList = em.createNamedQuery("DslamNets.findByIpaddr").setParameter("ipaddr", phost).getResultList();
            if (resultList.size() != 0) {
                for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                    DslamNets next = (DslamNets) iterator.next();
                    next.setNet(pnet);
                }
                return 0;
            }
            else return 2;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }    
    
    public Integer DelNet(final String phost,final String pnet) {
        try {
            List resultList = em.createNamedQuery("DslamNets.findByIpaddr").setParameter("ipaddr", phost).getResultList();
            if (resultList.size() != 0) {
                for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                    DslamNets next = (DslamNets) iterator.next();
                    em.remove(next);
                }
                return 0;
            }
            else return 2;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }    

    public Integer AddNet(final String phost,final String pnet) {
        try {
            DslamNets dn = new DslamNets();
            dn.setIpaddr(phost);
            dn.setNet(pnet);
            em.persist(dn);
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }    

    public Integer EmptyDslamBinds() {
        try {
            List resultList = em.createNamedQuery("DslamPortBinds.findAll").getResultList();
            if (resultList.size() != 0) {
                for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                    DslamPortBinds next = (DslamPortBinds) iterator.next();
                    em.remove(next);
                }
            }
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    } 

    public Integer AddDslamBinds(final String phost,int pport_id, final String pport, final String pbind ) {
        try {
            DslamPortBinds dn = new DslamPortBinds();
            dn.setHost(phost);
            dn.setPortId(pport_id);
            dn.setPort(pport);
            dn.setIpLine(pbind);
            em.persist(dn);
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }     
    
}
