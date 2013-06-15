/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import jpa.entities.Incidente;
import jpa.entities.Usuario;

/**
 *
 * @author leoandresm
 */
@Stateless
public class IncidenteFacade extends AbstractFacade<Incidente> {
    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IncidenteFacade() {
        super(Incidente.class);
    }
    
    public List<Incidente> findRangeByIdUsuario(Usuario user, int[] range) {
        javax.persistence.Query query = getEntityManager().createNamedQuery("Incidente.findByIdUsuario");        
        query.setParameter("idUsuario", user);
        query.setMaxResults(range[1] - range[0]);
        query.setFirstResult(range[0]);
        return query.getResultList();
    }
    
    public List<Incidente> findAllByIdUsuario(Usuario user) {
        javax.persistence.Query query = getEntityManager().createNamedQuery("Incidente.findByIdUsuario");        
        query.setParameter("idUsuario", user);        
        return query.getResultList();
    }
    
    public List<Incidente> findReporte(Date fechaInicial, Date fechaFinal) {
        javax.persistence.Query query = getEntityManager().createNamedQuery("Incidente.findByFechaInicialReporte");        
        query.setParameter("fechaInicialReporte", fechaInicial);    
        query.setParameter("fechaFinalReporte", fechaFinal); 
        return query.getResultList();
    }
}
