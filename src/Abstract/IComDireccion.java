/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.ComDireccion;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IComDireccion {

    void create(ComDireccion comDireccion);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(ComDireccion comDireccion) throws NonexistentEntityException, Exception;

    ComDireccion findComDireccion(Integer id);

    List<ComDireccion> findComDireccionEntities();

    List<ComDireccion> findComDireccionEntities(int maxResults, int firstResult);

    int getComDireccionCount();
    
}
