/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteExtends;

import Model.ProCatStatusProveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class ProCatStatusProveedorJPAExtends {
    
    private EntityManagerFactory emf = null;
    
    public ProCatStatusProveedorJPAExtends() {
       this.emf = Persistence.createEntityManagerFactory("DataAccessPuntoVentaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<ProCatStatusProveedor> findAllStatusProveedor()
    {
        try
        {
             EntityManager  em = getEntityManager();
             List<ProCatStatusProveedor> statusProveedor =  em.createQuery("SELECT a FROM ProCatStatusProveedor a").getResultList();
             return statusProveedor;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    
}
