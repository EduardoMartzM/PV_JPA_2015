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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ComDireccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComDireccion.findAll", query = "SELECT c FROM ComDireccion c"),
    @NamedQuery(name = "ComDireccion.findById", query = "SELECT c FROM ComDireccion c WHERE c.id = :id"),
    @NamedQuery(name = "ComDireccion.findByStrNumero", query = "SELECT c FROM ComDireccion c WHERE c.strNumero = :strNumero"),
    @NamedQuery(name = "ComDireccion.findByStrCalle", query = "SELECT c FROM ComDireccion c WHERE c.strCalle = :strCalle"),
    @NamedQuery(name = "ComDireccion.findByStrColonia", query = "SELECT c FROM ComDireccion c WHERE c.strColonia = :strColonia"),
    @NamedQuery(name = "ComDireccion.findByStrMunicipio", query = "SELECT c FROM ComDireccion c WHERE c.strMunicipio = :strMunicipio")})
public class ComDireccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strNumero")
    private String strNumero;
    @Column(name = "strCalle")
    private String strCalle;
    @Column(name = "strColonia")
    private String strColonia;
    @Column(name = "strMunicipio")
    private String strMunicipio;
    @JoinColumn(name = "idEstado", referencedColumnName = "id")
    @ManyToOne
    private ComEstadoDireccion idEstado;
    @OneToMany(mappedBy = "idComDireccion")
    private List<ProProveedor> proProveedorList;

    public ComDireccion() {
    }

    public ComDireccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrNumero() {
        return strNumero;
    }

    public void setStrNumero(String strNumero) {
        this.strNumero = strNumero;
    }

    public String getStrCalle() {
        return strCalle;
    }

    public void setStrCalle(String strCalle) {
        this.strCalle = strCalle;
    }

    public String getStrColonia() {
        return strColonia;
    }

    public void setStrColonia(String strColonia) {
        this.strColonia = strColonia;
    }

    public String getStrMunicipio() {
        return strMunicipio;
    }

    public void setStrMunicipio(String strMunicipio) {
        this.strMunicipio = strMunicipio;
    }

    public ComEstadoDireccion getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(ComEstadoDireccion idEstado) {
        this.idEstado = idEstado;
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
        if (!(object instanceof ComDireccion)) {
            return false;
        }
        ComDireccion other = (ComDireccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ComDireccion[ id=" + id + " ]";
    }
    
}
