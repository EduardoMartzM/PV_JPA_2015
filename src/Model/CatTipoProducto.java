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
@Table(name = "CatTipoProducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatTipoProducto.findAll", query = "SELECT c FROM CatTipoProducto c"),
    @NamedQuery(name = "CatTipoProducto.findById", query = "SELECT c FROM CatTipoProducto c WHERE c.id = :id"),
    @NamedQuery(name = "CatTipoProducto.findByStrValor", query = "SELECT c FROM CatTipoProducto c WHERE c.strValor = :strValor"),
    @NamedQuery(name = "CatTipoProducto.findByStrDescripcion", query = "SELECT c FROM CatTipoProducto c WHERE c.strDescripcion = :strDescripcion")})
public class CatTipoProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strValor")
    private String strValor;
    @Column(name = "strDescripcion")
    private String strDescripcion;
    @OneToMany(mappedBy = "idCategoria")
    private List<ProProducto> proProductoList;

    public CatTipoProducto() {
    }

    public CatTipoProducto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrValor() {
        return strValor;
    }

    public void setStrValor(String strValor) {
        this.strValor = strValor;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    @XmlTransient
    public List<ProProducto> getProProductoList() {
        return proProductoList;
    }

    public void setProProductoList(List<ProProducto> proProductoList) {
        this.proProductoList = proProductoList;
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
        if (!(object instanceof CatTipoProducto)) {
            return false;
        }
        CatTipoProducto other = (CatTipoProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CatTipoProducto[ id=" + id + " ]";
    }
    
}
