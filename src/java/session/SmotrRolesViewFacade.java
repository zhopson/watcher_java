/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.SmotrRolesView;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andrey-man
 */
@Stateless
public class SmotrRolesViewFacade extends AbstractFacade<SmotrRolesView> {

    @PersistenceContext(unitName = "watcherPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SmotrRolesViewFacade() {
        super(SmotrRolesView.class);
    }
    
}
