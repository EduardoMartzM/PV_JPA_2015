/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ConcreteExtends;

import Model.ProProveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Erik Guerrero Bravo Version 1.0.0
 */
public class ProProveedorJPAExtends 
{
    private EntityManagerFactory emf = null;
    
    public ProProveedorJPAExtends() {
       this.emf = Persistence.createEntityManagerFactory("DataAccessPuntoVentaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
 
//    public ProProveedor findAlumnoByNombre(String nombre, String paterno)
//    {
//        
//        try
//        {
//           EntityManager  em = getEntityManager();
//           Alumno alumno =  (Alumno) em.createQuery
//        ("SELECT a FROM Alumno a WHERE a.nombre = :nombre and a.apaterno = :paterno")
//                   .setParameter("nombre",nombre)
//                   .setParameter("paterno",paterno)
//                   .getSingleResult();
//           return alumno;
//        }
//        catch(Exception ex)
//        {
//            System.out.println(ex.getMessage());
//            return null;
//        }    
//    }
  
    
    public ProProveedor findProveedorId(int _id)
    {
        
        try
        {
           EntityManager  em = getEntityManager();
           ProProveedor proveedor =  (ProProveedor) em.createQuery
        ("SELECT a FROM ProProveedor a WHERE a.id = :id")
                   .setParameter("id",_id)
                   .getSingleResult();
           return proveedor;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }    
    }
     
    public List<ProProveedor> findProveedorLikeRZ(String _rz)
    {
        try
        {
           EntityManager em = getEntityManager();
           List<ProProveedor>  proveedores=em.createQuery("SELECT a FROM ProProveedor a WHERE a.strRazonSocial LIKE :rz")
                   .setParameter("rz","%"+_rz+"%").getResultList();
           return proveedores;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }
//    public List<Alumno> findAlumnoDistinct()
//    {
//        try
//        {
//            EntityManager em = getEntityManager();
//            List<Alumno> alumnos = em.createQuery("SELECT DISTINCT a FROM Alumno a").getResultList();
//            return alumnos;
//        }
//        catch(Exception ex)
//        {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//    }
    
//    public Alumno findAlumnoByNumeroControl( Alumno _alumno)
//    {
//        try
//        {
//            EntityManager  em = getEntityManager();
//            Alumno alumno = (Alumno) em.createQuery("SELECT a FROM Alumno a WHERE a.ncontrol = :ncontrol")
//                                     .setParameter("ncontrol",_alumno.getNcontrol())
//                                     .getSingleResult();
//            return alumno;
//        }
//        catch(Exception ex)
//        {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//    }
    
//    public List<Alumno> findAllAlumnos()
//    {
//        try
//        {
//             EntityManager  em = getEntityManager();
//             List<Alumno> alumnos =  em.createQuery("SELECT a FROM Alumno a").getResultList();
//             return alumnos;
//        }
//        catch(Exception ex)
//        {
//            return null;
//        }
//    }
  
//    public List<Alumno> findAlumnoCriteriaQuery()
//    {
//        try
//        {
//            EntityManager  em = getEntityManager();
//            TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a",Alumno.class);
//            return query.getResultList();
//        }
//        catch(Exception ex)
//        {
//            System.out.println("El Error esta Aquí"+ ex.getMessage());
//            return null;
//        }
//    }
//    
//    public List<Alumno> findAlumnoBetweenId(int idInicial, int idFinal)
//    {
//        try
//        {
//            //SELECT a FROM Alumno a , Alumno b WHERE a.id BETWEEN a.id and b.id
//             EntityManager  em = getEntityManager();
//             List<Alumno> alumnos =em.createNamedQuery("SELECT a FROM Alumno a WHERE a.id >= :inicio AND  a.id <= :final")
//             .setParameter("inicio", String.valueOf(idInicial))
//                     .setParameter("final", String.valueOf(idFinal)).getResultList();
//             return alumnos;
//                     
//        }
//         catch(Exception ex)
//        {
//            System.out.println("El Error esta Aquí"+ ex.getMessage());
//            return null;
//        }
//    }
    
    
    
}
