/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IPedPedidoProveedor;
import Concrete.PedPedidoProveedorJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryPedPedidoProveedor {
    
    private static FactoryPedPedidoProveedor factory;

    static {
        try {
            factory = FactoryPedPedidoProveedor.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryPedPedidoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPedPedidoProveedor getInstance() {
        return factory;
    }

    public IPedPedidoProveedor getInstanceAbstract() {
        try {
            PedPedidoProveedorJpaController ctrlPedPedidoProveedor = new PedPedidoProveedorJpaController();
            return (IPedPedidoProveedor) ctrlPedPedidoProveedor;

        } catch (Exception _e) {
            return null;
        }
    }
}
