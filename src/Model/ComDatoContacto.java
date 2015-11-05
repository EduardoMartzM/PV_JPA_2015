/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chirr
 */
@Entity
@Table(name = "ComDatoContacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComDatoContacto.findAll", query = "SELECT c FROM ComDatoContacto c"),
    @NamedQuery(name = "ComDatoContacto.findById", query = "SELECT c FROM ComDatoContacto c WHERE c.id = :id"),
    @NamedQuery(name = "ComDatoContacto.findByStrNombreContacto", query = "SELECT c FROM ComDatoContacto c WHERE c.strNombreContacto = :strNombreContacto"),
    @NamedQuery(name = "ComDatoContacto.findByStrEmail", query = "SELECT c FROM ComDatoContacto c WHERE c.strEmail = :strEmail"),
    @NamedQuery(name = "ComDatoContacto.findByStrTelefonoMovil", query = "SELECT c FROM ComDatoContacto c WHERE c.strTelefonoMovil = :strTelefonoMovil"),
    @NamedQuery(name = "ComDatoContacto.findByStrTelefonoHome", query = "SELECT c FROM ComDatoContacto c WHERE c.strTelefonoHome = :strTelefonoHome")})
public class ComDatoContacto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strNombreContacto")
    private String strNombreContacto;
    @Column(name = "strEmail")
    private String strEmail;
    @Column(name = "strTelefonoMovil")
    private String strTelefonoMovil;
    @Column(name = "strTelefonoHome")
    private String strTelefonoHome;
    @OneToMany(mappedBy = "idComDatoContacto")
    private List<ProProveedor> proProveedorList;

    public ComDatoContacto() {
    }

    public ComDatoContacto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrNombreContacto() {
        return strNombreContacto;
    }

    public void setStrNombreContacto(String strNombreContacto) {
        this.strNombreContacto = strNombreContacto;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrTelefonoMovil() {
        return strTelefonoMovil;
    }

    public void setStrTelefonoMovil(String strTelefonoMovil) {
        this.strTelefonoMovil = strTelefonoMovil;
    }

    public String getStrTelefonoHome() {
        return strTelefonoHome;
    }

    public void setStrTelefonoHome(String strTelefonoHome) {
        this.strTelefonoHome = strTelefonoHome;
    }

    @XmlTransient
    public List<ProProveedor> getProProveedorList() {
        return proProveedorList;
    }

    public void setProProveedorList(List<ProProveedor> proProveedorList) {
        this.proProveedorList = proProveedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComDatoContacto)) {
            return false;
        }
        ComDatoContacto other = (ComDatoContacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ComDatoContacto[ id=" + id + " ]";
    }
    
}
