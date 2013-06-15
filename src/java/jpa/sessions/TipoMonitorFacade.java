/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.TipoMonitor;

/**
 *
 * @author leoandresm
 */
@Stateless
public class TipoMonitorFacade extends AbstractFacade<TipoMonitor> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMonitorFacade() {
        super(TipoMonitor.class);
    }
    
}
