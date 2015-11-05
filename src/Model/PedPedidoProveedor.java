/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chirr
 */
@Entity
@Table(name = "PedPedidoProveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedPedidoProveedor.findAll", query = "SELECT p FROM PedPedidoProveedor p"),
    @NamedQuery(name = "PedPedidoProveedor.findById", query = "SELECT p FROM PedPedidoProveedor p WHERE p.id = :id"),
    @NamedQuery(name = "PedPedidoProveedor.findByStrFolio", query = "SELECT p FROM PedPedidoProveedor p WHERE p.strFolio = :strFolio"),
    @NamedQuery(name = "PedPedidoProveedor.findByDteFechaPedido", query = "SELECT p FROM PedPedidoProveedor p WHERE p.dteFechaPedido = :dteFechaPedido"),
    @NamedQuery(name = "PedPedidoProveedor.findByDecTotal", query = "SELECT p FROM PedPedidoProveedor p WHERE p.decTotal = :decTotal")})
public class PedPedidoProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strFolio")
    private String strFolio;
    @Basic(optional = false)
    @Column(name = "dteFechaPedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dteFechaPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "decTotal")
    private BigDecimal decTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedPedidoProveedor")
    private List<PedPagosPedidoProveedor> pedPagosPedidoProveedorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedPedidoProveedor")
    private List<PedDetallePedidoProveedor> pedDetallePedidoProveedorList;
    @JoinColumn(name = "idCatStatusPedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CatStatusPedido idCatStatusPedido;

    public PedPedidoProveedor() {
    }

    public PedPedidoProveedor(Integer id) {
        this.id = id;
    }

    public PedPedidoProveedor(Integer id, String strFolio, Date dteFechaPedido, BigDecimal decTotal) {
        this.id = id;
        this.strFolio = strFolio;
        this.dteFechaPedido = dteFechaPedido;
        this.decTotal = decTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrFolio() {
        return strFolio;
    }

    public void setStrFolio(String strFolio) {
        this.strFolio = strFolio;
    }

    public Date getDteFechaPedido() {
        return dteFechaPedido;
    }

    public void setDteFechaPedido(Date dteFechaPedido) {
        this.dteFechaPedido = dteFechaPedido;
    }

    public BigDecimal getDecTotal() {
        return decTotal;
    }

    public void setDecTotal(BigDecimal decTotal) {
        this.decTotal = decTotal;
    }

    @XmlTransient
    public List<PedPagosPedidoProveedor> getPedPagosPedidoProveedorList() {
        return pedPagosPedidoProveedorList;
    }

    public void setPedPagosPedidoProveedorList(List<PedPagosPedidoProveedor> pedPagosPedidoProveedorList) {
        this.pedPagosPedidoProveedorList = pedPagosPedidoProveedorList;
    }

    @XmlTransient
    public List<PedDetallePedidoProveedor> getPedDetallePedidoProveedorList() {
        return pedDetallePedidoProveedorList;
    }

    public void setPedDetallePedidoProveedorList(List<PedDetallePedidoProveedor> pedDetallePedidoProveedorList) {
        this.pedDetallePedidoProveedorList = pedDetallePedidoProveedorList;
    }

    public CatStatusPedido getIdCatStatusPedido() {
        return idCatStatusPedido;
    }

    public void setIdCatStatusPedido(CatStatusPedido idCatStatusPedido) {
        this.idCatStatusPedido = idCatStatusPedido;
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
        if (!(object instanceof PedPedidoProveedor)) {
            return false;
        }
        PedPedidoProveedor other = (PedPedidoProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.PedPedidoProveedor[ id=" + id + " ]";
    }
    
}
