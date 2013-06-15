/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Incidente;
import jpa.entities.Solucion;

/**
 *
 * @author leoandresm
 */
@Stateless
public class SolucionFacade extends AbstractFacade<Solucion> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolucionFacade() {
        super(Solucion.class);
    }
 
     public List<Solucion> findRangeByIdIncidente(Incidente incidente, int[] range) {
        javax.persistence.Query query = getEntityManager().createNamedQuery("Solucion.findByIdIncidente");        
        query.setParameter("idIncidente", incidente);
        query.setMaxResults(range[1] - range[0]);
        query.setFirstResult(range[0]);
        return query.getResultList();
    }
}
