/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Equipo;
import jpa.entities.Sala;

/**
 *
 * @author leoandresm
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }
    
    public List<Equipo> findBySala(Sala sala) {
         javax.persistence.Query q = getEntityManager().createNamedQuery("Equipo.findByIdSala");
        q.setParameter("idSala", sala);
        return q.getResultList();
    }
    
}
