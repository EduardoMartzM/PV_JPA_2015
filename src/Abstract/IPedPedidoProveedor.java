/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.IllegalOrphanException;
import Concrete.exceptions.NonexistentEntityException;
import Model.PedPedidoProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IPedPedidoProveedor {

    void create(PedPedidoProveedor pedPedidoProveedor);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(PedPedidoProveedor pedPedidoProveedor) throws IllegalOrphanException, NonexistentEntityException, Exception;

    PedPedidoProveedor findPedPedidoProveedor(Integer id);

    List<PedPedidoProveedor> findPedPedidoProveedorEntities();

    List<PedPedidoProveedor> findPedPedidoProveedorEntities(int maxResults, int firstResult);

    int getPedPedidoProveedorCount();
    
}
