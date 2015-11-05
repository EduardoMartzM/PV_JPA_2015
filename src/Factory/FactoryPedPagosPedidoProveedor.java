/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IPedPagosPedidoProveedor;
import Concrete.PedPagosPedidoProveedorJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryPedPagosPedidoProveedor {
    
    
    private static FactoryPedPagosPedidoProveedor factory;

    static {
        try {
            factory = FactoryPedPagosPedidoProveedor.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryPedPagosPedidoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPedPagosPedidoProveedor getInstance() {
        return factory;
    }

    public IPedPagosPedidoProveedor getInstanceAbstract() {
        try {
            PedPagosPedidoProveedorJpaController ctrlPedPagosPedidoProveedor = new PedPagosPedidoProveedorJpaController();
            return (IPedPagosPedidoProveedor) ctrlPedPagosPedidoProveedor;

        } catch (Exception _e) {
            return null;
        }
    }
}
