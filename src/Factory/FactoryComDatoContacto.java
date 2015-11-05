/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IComDatoContacto;
import Concrete.ComDatoContactoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryComDatoContacto {
    
    private static FactoryComDatoContacto factory;

    static {
        try {
            factory = FactoryComDatoContacto.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryComDatoContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryComDatoContacto getInstance() {
        return factory;
    }

    public IComDatoContacto getInstanceAbstract() {
        try {
            ComDatoContactoJpaController ctrlComDatoContacto = new ComDatoContactoJpaController();
            return (IComDatoContacto) ctrlComDatoContacto;

        } catch (Exception _e) {
            return null;
        }
    }
    
}
