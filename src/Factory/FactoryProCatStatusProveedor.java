/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IProCatStatusProveedor;
import Concrete.ProCatStatusProveedorJpaController;
import ConcreteExtends.ProCatStatusProveedorJPAExtends;
import Model.ProCatStatusProveedor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryProCatStatusProveedor {
    
    private static FactoryProCatStatusProveedor factory;

    static {
        try {
            factory = FactoryProCatStatusProveedor.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryProCatStatusProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryProCatStatusProveedor getInstance() {
        return factory;
    }

    public IProCatStatusProveedor getInstanceAbstract() {
        try {
            ProCatStatusProveedorJpaController ctrlProCatStatusProveedor = new ProCatStatusProveedorJpaController();
            return (IProCatStatusProveedor) ctrlProCatStatusProveedor;

        } catch (Exception _e) {
            return null;
        }
    }
    
       public List<ProCatStatusProveedor> getAllStatusProveedor()
    {
        try
        {
            ProCatStatusProveedorJPAExtends ctrlStatusProveedorExtends = new ProCatStatusProveedorJPAExtends();
            return ctrlStatusProveedorExtends.findAllStatusProveedor();
        }
        catch(Exception ex)
        {
            System.out.println("Error FactoryProCatStatusProveedor(getAllStatusProveedor): "+ex.getMessage());
            return null;
        }
    }
    
}
