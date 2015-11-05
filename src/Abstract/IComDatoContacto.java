/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.ComDatoContacto;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IComDatoContacto {

    void create(ComDatoContacto comDatoContacto);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(ComDatoContacto comDatoContacto) throws NonexistentEntityException, Exception;

    ComDatoContacto findComDatoContacto(Integer id);

    List<ComDatoContacto> findComDatoContactoEntities();

    List<ComDatoContacto> findComDatoContactoEntities(int maxResults, int firstResult);

    int getComDatoContactoCount();
    
}
