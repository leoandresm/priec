/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.TipoMemoria;

/**
 *
 * @author leoandresm
 */
@Stateless
public class TipoMemoriaFacade extends AbstractFacade<TipoMemoria> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMemoriaFacade() {
        super(TipoMemoria.class);
    }
    
}
