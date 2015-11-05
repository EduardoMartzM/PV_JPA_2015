/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.PedDetallePedidoProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IPedDetallePedidoProveedor {

    void create(PedDetallePedidoProveedor pedDetallePedidoProveedor);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(PedDetallePedidoProveedor pedDetallePedidoProveedor) throws NonexistentEntityException, Exception;

    PedDetallePedidoProveedor findPedDetallePedidoProveedor(Integer id);

    List<PedDetallePedidoProveedor> findPedDetallePedidoProveedorEntities();

    List<PedDetallePedidoProveedor> findPedDetallePedidoProveedorEntities(int maxResults, int firstResult);

    int getPedDetallePedidoProveedorCount();
    
}
