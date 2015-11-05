/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.PedPagosPedidoProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IPedPagosPedidoProveedor {

    void create(PedPagosPedidoProveedor pedPagosPedidoProveedor);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(PedPagosPedidoProveedor pedPagosPedidoProveedor) throws NonexistentEntityException, Exception;

    PedPagosPedidoProveedor findPedPagosPedidoProveedor(Integer id);

    List<PedPagosPedidoProveedor> findPedPagosPedidoProveedorEntities();

    List<PedPagosPedidoProveedor> findPedPagosPedidoProveedorEntities(int maxResults, int firstResult);

    int getPedPagosPedidoProveedorCount();
    
}
