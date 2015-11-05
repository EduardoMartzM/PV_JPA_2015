/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteExtends;

import Model.ProProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class ProProductoJPAExtends {
    
    private EntityManagerFactory emf = null;
    
    public ProProductoJPAExtends() {
       this.emf = Persistence.createEntityManagerFactory("DataAccessPuntoVentaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
     public List<ProProducto> findProductoLikeNombre(String _nom)
    {
        try
        {
           EntityManager em = getEntityManager();
           List<ProProducto>  productos=em.createQuery("SELECT a FROM ProProducto a WHERE a.strNombre LIKE :nom")
                   .setParameter("nom","%"+_nom+"%").getResultList();
           return productos;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
