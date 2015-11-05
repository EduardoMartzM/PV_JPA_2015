/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IProProveedor;
import Concrete.ProProveedorJpaController;
import ConcreteExtends.ProProveedorJPAExtends;
import Model.ProProveedor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryProProveedor {
    
    private static FactoryProProveedor factory;

    static {
        try {
            factory = FactoryProProveedor.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryProProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static FactoryProProveedor getInstance() {
        return factory;
    }
     
     public IProProveedor getInstanceAbstract() {
        try {
            ProProveedorJpaController ctrlProProveedor = new ProProveedorJpaController();
            return (IProProveedor) ctrlProProveedor;

        } catch (Exception _e) {
            return null;
        }
    }
     
     public List<ProProveedor> getProveedorLikeRZ(String _rz)
    {
        try
        {
            ProProveedorJPAExtends ctrlProveedorExtends = new ProProveedorJPAExtends();
            return ctrlProveedorExtends.findProveedorLikeRZ(_rz);
        }
        catch(Exception ex)
        {
            System.out.println("Error FactoryProProveedor(getProveedorLikeRZ): "+ex.getMessage());
            return null;
        }
    }
     
       public ProProveedor getProveedorId(int _id)
    {
        try
        {
            ProProveedorJPAExtends ctrlProveedorExtends = new ProProveedorJPAExtends();
            return ctrlProveedorExtends.findProveedorId(_id);
        }
        catch(Exception ex)
        {
            System.out.println("Error FactoryProProveedor(getProveedorId): "+ex.getMessage());
            return null;
        }
    }
    
}
