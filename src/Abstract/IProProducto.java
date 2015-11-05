/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.ProProducto;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IProProducto {

    void create(ProProducto proProducto);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(ProProducto proProducto) throws NonexistentEntityException, Exception;

    ProProducto findProProducto(Integer id);

    List<ProProducto> findProProductoEntities();

    List<ProProducto> findProProductoEntities(int maxResults, int firstResult);

    int getProProductoCount();
    
}
