/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SmotrArchiveLogsOptinetView;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andrey-man
 */
@Stateless
public class SmotrArchiveLogsOptinetViewFacade extends AbstractFacade<SmotrArchiveLogsOptinetView> {

    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SmotrArchiveLogsOptinetViewFacade() {
        super(SmotrArchiveLogsOptinetView.class);
    }
    
}
