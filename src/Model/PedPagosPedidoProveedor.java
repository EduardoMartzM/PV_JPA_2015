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
@Table(name = "PedPagosPedidoProveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedPagosPedidoProveedor.findAll", query = "SELECT p FROM PedPagosPedidoProveedor p"),
    @NamedQuery(name = "PedPagosPedidoProveedor.findById", query = "SELECT p FROM PedPagosPedidoProveedor p WHERE p.id = :id"),
    @NamedQuery(name = "PedPagosPedidoProveedor.findByDecMontoPago", query = "SELECT p FROM PedPagosPedidoProveedor p WHERE p.decMontoPago = :decMontoPago")})
public class PedPagosPedidoProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "decMontoPago")
    private BigDecimal decMontoPago;
    @JoinColumn(name = "idCatTipoPago", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CatTipoPago idCatTipoPago;
    @JoinColumn(name = "idPedPedidoProveedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PedPedidoProveedor idPedPedidoProveedor;

    public PedPagosPedidoProveedor() {
    }

    public PedPagosPedidoProveedor(Integer id) {
        this.id = id;
    }

    public PedPagosPedidoProveedor(Integer id, BigDecimal decMontoPago) {
        this.id = id;
        this.decMontoPago = decMontoPago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDecMontoPago() {
        return decMontoPago;
    }

    public void setDecMontoPago(BigDecimal decMontoPago) {
        this.decMontoPago = decMontoPago;
    }

    public CatTipoPago getIdCatTipoPago() {
        return idCatTipoPago;
    }

    public void setIdCatTipoPago(CatTipoPago idCatTipoPago) {
        this.idCatTipoPago = idCatTipoPago;
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
        if (!(object instanceof PedPagosPedidoProveedor)) {
            return false;
        }
        PedPagosPedidoProveedor other = (PedPagosPedidoProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.PedPagosPedidoProveedor[ id=" + id + " ]";
    }
    
}
