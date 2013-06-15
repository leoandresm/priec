/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.entities.Usuario;
import jpa.entities.Usuario_;

/**
 *
 * @author leoandresm
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "priec-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByIdGenesis(int id_genesis_uniminuto) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> e = cq.from(Usuario.class);
        cq.select(e).where(cb.equal(e.get(Usuario_.idGenesisUniminuto), id_genesis_uniminuto));
        TypedQuery<Usuario> q = em.createQuery(cq);
        List<Usuario> result = q.getResultList();
        
//        javax.persistence.Query query = getEntityManager().createNamedQuery("Usuario.findByIdGenesisUniminuto");
//        query.setParameter("idGenesisUniminuto", id_genesis_uniminuto);
//        List results = query.getResultList();
        
        Usuario foundEntity = null;
        if (!result.isEmpty()) {
            // ignores multiple results
            foundEntity = (Usuario) result.get(0);
        }
        return foundEntity;
    }    
    
}
