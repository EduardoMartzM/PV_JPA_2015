/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chirr
 */
@Entity
@Table(name = "PedDetallePedidoProveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedDetallePedidoProveedor.findAll", query = "SELECT p FROM PedDetallePedidoProveedor p"),
    @NamedQuery(name = "PedDetallePedidoProveedor.findById", query = "SELECT p FROM PedDetallePedidoProveedor p WHERE p.id = :id"),
    @NamedQuery(name = "PedDetallePedidoProveedor.findByIdProducto", query = "SELECT p FROM PedDetallePedidoProveedor p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "PedDetallePedidoProveedor.findByIdProveedor", query = "SELECT p FROM PedDetallePedidoProveedor p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "PedDetallePedidoProveedor.findByIntCantidad", query = "SELECT p FROM PedDetallePedidoProveedor p WHERE p.intCantidad = :intCantidad"),
    @NamedQuery(name = "PedDetallePedidoProveedor.findByDecSubtotal", query = "SELECT p FROM PedDetallePedidoProveedor p WHERE p.decSubtotal = :decSubtotal")})
public class PedDetallePedidoProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idProveedor")
    private int idProveedor;
    @Basic(optional = false)
    @Column(name = "intCantidad")
    private int intCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "decSubtotal")
    private BigDecimal decSubtotal;
    @JoinColumn(name = "idPedPedidoProveedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PedPedidoProveedor idPedPedidoProveedor;

    public PedDetallePedidoProveedor() {
    }

    public PedDetallePedidoProveedor(Integer id) {
        this.id = id;
    }

    public PedDetallePedidoProveedor(Integer id, int idProducto, int idProveedor, int intCantidad, BigDecimal decSubtotal) {
        this.id = id;
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.intCantidad = intCantidad;
        this.decSubtotal = decSubtotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIntCantidad() {
        return intCantidad;
    }

    public void setIntCantidad(int intCantidad) {
        this.intCantidad = intCantidad;
    }

    public BigDecimal getDecSubtotal() {
        return decSubtotal;
    }

    public void setDecSubtotal(BigDecimal decSubtotal) {
        this.decSubtotal = decSubtotal;
    }

    public PedPedidoProveedor getIdPedPedidoProveedor() {
        return idPedPedidoProveedor;
    }

    public void setIdPedPedidoProveedor(PedPedidoProveedor idPedPedidoProveedor) {
        this.idPedPedidoProveedor = idPedPedidoProveedor;
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
        if (!(object instanceof PedDetallePedidoProveedor)) {
            return false;
        }
        PedDetallePedidoProveedor other = (PedDetallePedidoProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.PedDetallePedidoProveedor[ id=" + id + " ]";
    }
    
}
