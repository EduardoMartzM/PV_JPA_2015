/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.IllegalOrphanException;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatStatusPedido;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface ICatStatusPedido {

    void create(CatStatusPedido catStatusPedido) throws PreexistingEntityException, Exception;

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(CatStatusPedido catStatusPedido) throws IllegalOrphanException, NonexistentEntityException, Exception;

    CatStatusPedido findCatStatusPedido(Integer id);

    List<CatStatusPedido> findCatStatusPedidoEntities();

    List<CatStatusPedido> findCatStatusPedidoEntities(int maxResults, int firstResult);

    int getCatStatusPedidoCount();
    
}
