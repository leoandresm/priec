/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.TipoMouse;

/**
 *
 * @author leoandresm
 */
@Stateless
public class TipoMouseFacade extends AbstractFacade<TipoMouse> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMouseFacade() {
        super(TipoMouse.class);
    }
    
}
