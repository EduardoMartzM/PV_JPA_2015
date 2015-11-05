/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IVenDetalleVenta;
import Concrete.VenDetalleVentaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryVenDetalleVenta {
    
    private static FactoryVenDetalleVenta factory;

    static {
        try {
            factory = FactoryVenDetalleVenta.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryVenDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static FactoryVenDetalleVenta getInstance() {
        return factory;
    }
    
     public IVenDetalleVenta getInstanceAbstract() {
        try {
            VenDetalleVentaJpaController ctrlVenDetalleVenta = new VenDetalleVentaJpaController();
            return (IVenDetalleVenta) ctrlVenDetalleVenta;

        } catch (Exception _e) {
            return null;
        }
    }
}
