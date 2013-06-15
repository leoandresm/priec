/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Sala;
import jpa.entities.Sede;

/**
 *
 * @author leoandresm
 */
@Stateless
public class SalaFacade extends AbstractFacade<Sala> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalaFacade() {
        super(Sala.class);
    }
    
    public List<Sala> findBySede(Sede sede) {
         javax.persistence.Query q = getEntityManager().createNamedQuery("Sala.findByIdSede");
        q.setParameter("idSede", sede);
        return q.getResultList();
    }
    
}
