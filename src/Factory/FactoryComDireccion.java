/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IComDireccion;
import Concrete.ComDireccionJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryComDireccion {
    
    private static FactoryComDireccion factory;

    static {
        try {
            factory = FactoryComDireccion.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryComDireccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryComDireccion getInstance() {
        return factory;
    }

    public IComDireccion getInstanceAbstract() {
        try {
            ComDireccionJpaController ctrlComDireccion = new ComDireccionJpaController();
            return (IComDireccion) ctrlComDireccion;

        } catch (Exception _e) {
            return null;
        }
    }
    
}
