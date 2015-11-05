/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IProProducto;
import Concrete.ProProductoJpaController;
import ConcreteExtends.ProProductoJPAExtends;
import Model.ProProducto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryProProducto {
    
    private static FactoryProProducto factory;

    static {
        try {
            factory = FactoryProProducto.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryProProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static FactoryProProducto getInstance() {
        return factory;
    }

    public IProProducto getInstanceAbstract() {
        try {
            ProProductoJpaController ctrlProProducto = new ProProductoJpaController();
            return (IProProducto) ctrlProProducto;

        } catch (Exception _e) {
            return null;
        }
    }
    
     public List<ProProducto> getProductoLikeNombre(String _nom)
    {
        try
        {
            ProProductoJPAExtends ctrlProductoExtends = new ProProductoJPAExtends();
            return ctrlProductoExtends.findProductoLikeNombre(_nom);
        }
        catch(Exception ex)
        {
            System.out.println("Error FactoryProProducto(getProductoLikeNombre): "+ex.getMessage());
            return null;
        }
    }
}
