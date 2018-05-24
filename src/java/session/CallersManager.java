/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SmotrCallerPeople;
import entity.SmotrNtpWhatsapp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javax.persistence.Query;

/**
 *
 * @author andrey-man
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CallersManager {
    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer SetAbonCallFlag(final boolean pFlag,final String pPhone) {
        try {
            List resultList = null;
            resultList = em.createNamedQuery("SmotrCallerPeople.findByTel").setParameter("tel", pPhone).getResultList();
            if (resultList.size() != 0) {
                for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                    SmotrCallerPeople next = (SmotrCallerPeople) iterator.next();
                    if (pFlag != next.getCall()) {
                        update(next,pFlag);
                        //em.merge(next);
                        //em.flush();
                    }
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

public void update(SmotrCallerPeople obj, boolean pFlag) {
    obj.setCall(pFlag);
    //em.merge(obj);
}    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer SetAllAbonCallFlagByGr(final boolean pFlag,final String pPhoneList) {
        try {
            List phone_col = new ArrayList();
            phone_col.addAll(Arrays.asList(pPhoneList.split(",")));
            //Query query;
            List resultList = null;
            resultList = em.createNamedQuery("SmotrCallerPeople.findByTelSet").setParameter("ss", phone_col).getResultList();
            if (resultList.size() != 0) {
                for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                    SmotrCallerPeople next = (SmotrCallerPeople) iterator.next();
                    if (pFlag != next.getCall()) {
                        next.setCall(pFlag);
                        //em.merge(next);
                    }
                }
                return 0;
            }
            else return 2;
//            if (pFlag) query = em.createQuery("update SmotrCallerPeople t set t.call=true where t.tel in :s");
//            else query = em.createQuery("update SmotrCallerPeople t set t.call=false where t.tel in :s");
//            int updateCount = query.setParameter("s", phone_col).executeUpdate();
//            if (updateCount != 0) {
//                return 0;
//            }
//            else return 2;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer SendTlgMsg(final String pMsg,final String pGrList) {
        try {
            Date parsingDate = new Date();
            //SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //parsingDate = format1.parse(parsingDate.toString());                 
            List gr_col = new ArrayList(); //Массив
            gr_col.addAll(Arrays.asList(pGrList.split(","))); //Формирование массива из строки - список групп, разделенных запятыми
            if (gr_col.size() != 0) {
                for (Iterator iterator = gr_col.iterator(); iterator.hasNext();) {//Цикл по списку групп
                    String next = (String) iterator.next();
                    SmotrNtpWhatsapp item = new SmotrNtpWhatsapp();
                    item.setGroupId(next);
                    item.setText(pMsg);
                    item.setTimestamp(parsingDate);
                    item.setMessageProcessed(0);
                    em.persist(item);
                    //em.createNativeQuery("select watcher.call_perl_script(?1,?2)").setParameter(1, pMsg).setParameter(2, next).getSingleResult();
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer DoAutoCall(final String pMsg) {
        try {
            em.createNativeQuery("select watcher.call_perl_script_callers(?1,'1')").setParameter(1, pMsg).getSingleResult();
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer DoMysqlAsteriskCall(final String pfilter_phone,final boolean pIsArch,final String pst_d,final String pen_d, final String phash) {
        try {
            Query query = null;
            if (pIsArch) {
                if (pfilter_phone != null) 
                    query=em.createNativeQuery("select watcher.mysql_asterisk_call(?1,?2,?3,?4)")
                        .setParameter(1, pfilter_phone)
                        .setParameter(2, pst_d)
                        .setParameter(3, pen_d)
                        .setParameter(4, phash);
                else 
                    query=em.createNativeQuery("select watcher.mysql_asterisk_call(null,?1,?2,?3)")
                        .setParameter(1, pst_d)
                        .setParameter(2, pen_d)
                        .setParameter(3, phash);
            }
            else {
                if (pfilter_phone != null) 
                    query=em.createNativeQuery("select watcher.mysql_asterisk_call(?1,null,null,?2)")
                        .setParameter(1, pfilter_phone)
                        .setParameter(2, phash);
                else 
                    query=em.createNativeQuery("select watcher.mysql_asterisk_call(null,null,null,?1)")
                        .setParameter(1, phash);
            }
            //em.createNativeQuery("select watcher.mysql_asterisk_call(?1,?2,?3,?4)").setParameter(1, pMsg).getSingleResult();
            query.getSingleResult();
            return 0;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            return 1;
        }
    }
    
}
