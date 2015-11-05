/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IVenVenta;
import Concrete.VenVentaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryVenVenta {
    
    private static FactoryVenVenta factory;

    static {
        try {
            factory = FactoryVenVenta.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryVenVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static FactoryVenVenta getInstance() {
        return factory;
    }
     
     public IVenVenta getInstanceAbstract() {
        try {
            VenVentaJpaController ctrlVenVenta = new VenVentaJpaController();
            return (IVenVenta) ctrlVenVenta;

        } catch (Exception _e) {
            return null;
        }
    }
    
}
