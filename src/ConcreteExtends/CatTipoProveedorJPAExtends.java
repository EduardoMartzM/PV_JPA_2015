/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteExtends;

import Model.CatTipoProveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class CatTipoProveedorJPAExtends {
    
     private EntityManagerFactory emf = null;
    
    public CatTipoProveedorJPAExtends() {
       this.emf = Persistence.createEntityManagerFactory("DataAccessPuntoVentaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<CatTipoProveedor> findAllTipoProveedor()
    {
        try
        {
             EntityManager  em = getEntityManager();
             List<CatTipoProveedor> tipoProveedor =  em.createQuery("SELECT a FROM CatTipoProveedor a").getResultList();
             return tipoProveedor;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    
}
