/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.ICatTipoProducto;
import Concrete.CatTipoProductoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryCatTipoProducto {
    
    private static FactoryCatTipoProducto factory;

    static {
        try {
            factory = FactoryCatTipoProducto.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryCatTipoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryCatTipoProducto getInstance() {
        return factory;
    }

    public ICatTipoProducto getInstanceAbstract() {
        try {
            CatTipoProductoJpaController ctrlCatTipoProducto = new CatTipoProductoJpaController();
            return (ICatTipoProducto) ctrlCatTipoProducto;

        } catch (Exception _e) {
            return null;
        }
    }
    
}
