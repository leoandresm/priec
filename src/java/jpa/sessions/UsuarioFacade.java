/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Usuario;

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
        javax.persistence.Query query = getEntityManager().createNamedQuery("Usuario.findByIdGenesisUniminuto");
        query.setParameter("idGenesisUniminuto", id_genesis_uniminuto);
        List results = query.getResultList();
        Usuario foundEntity = null;
        if (!results.isEmpty()) {
            // ignores multiple results
            foundEntity = (Usuario) results.get(0);
        }
        return foundEntity;
    }    
    
}
