/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.TipoTeclado;

/**
 *
 * @author leoandresm
 */
@Stateless
public class TipoTecladoFacade extends AbstractFacade<TipoTeclado> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoTecladoFacade() {
        super(TipoTeclado.class);
    }
    
}
