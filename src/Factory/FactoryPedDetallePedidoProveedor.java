/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.IComEstadoDireccion;
import Abstract.IPedDetallePedidoProveedor;
import Concrete.ComEstadoDireccionJpaController;
import Concrete.PedDetallePedidoProveedorJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryPedDetallePedidoProveedor {
    
    private static FactoryPedDetallePedidoProveedor factory;

    static {
        try {
            factory = FactoryPedDetallePedidoProveedor.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryPedDetallePedidoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static FactoryPedDetallePedidoProveedor getInstance() {
        return factory;
    }

    public IPedDetallePedidoProveedor getInstanceAbstract() {
        try {
            PedDetallePedidoProveedorJpaController ctrlPedDetallePedidoProveedor = new PedDetallePedidoProveedorJpaController();
            return (IPedDetallePedidoProveedor) ctrlPedDetallePedidoProveedor;

        } catch (Exception _e) {
            return null;
        }
    }    
}
