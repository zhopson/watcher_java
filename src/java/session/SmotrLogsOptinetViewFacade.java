/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SmotrLogsOptinetView;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andrey-man
 */
@Stateless
public class SmotrLogsOptinetViewFacade extends AbstractFacade<SmotrLogsOptinetView> {

    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SmotrLogsOptinetViewFacade() {
        super(SmotrLogsOptinetView.class);
    }
    
}
