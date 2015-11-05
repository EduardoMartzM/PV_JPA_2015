/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CatTipoPago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatTipoPago.findAll", query = "SELECT c FROM CatTipoPago c"),
    @NamedQuery(name = "CatTipoPago.findById", query = "SELECT c FROM CatTipoPago c WHERE c.id = :id"),
    @NamedQuery(name = "CatTipoPago.findByStrValor", query = "SELECT c FROM CatTipoPago c WHERE c.strValor = :strValor"),
    @NamedQuery(name = "CatTipoPago.findByStrDescripcion", query = "SELECT c FROM CatTipoPago c WHERE c.strDescripcion = :strDescripcion")})
public class CatTipoPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strValor")
    private String strValor;
    @Column(name = "strDescripcion")
    private String strDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatTipoPago")
    private List<PedPagosPedidoProveedor> pedPagosPedidoProveedorList;

    public CatTipoPago() {
    }

    public CatTipoPago(Integer id) {
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
    public List<PedPagosPedidoProveedor> getPedPagosPedidoProveedorList() {
        return pedPagosPedidoProveedorList;
    }

    public void setPedPagosPedidoProveedorList(List<PedPagosPedidoProveedor> pedPagosPedidoProveedorList) {
        this.pedPagosPedidoProveedorList = pedPagosPedidoProveedorList;
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
        if (!(object instanceof CatTipoPago)) {
            return false;
        }
        CatTipoPago other = (CatTipoPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CatTipoPago[ id=" + id + " ]";
    }
    
}
