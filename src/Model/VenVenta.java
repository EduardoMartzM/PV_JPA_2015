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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "VenVenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VenVenta.findAll", query = "SELECT v FROM VenVenta v"),
    @NamedQuery(name = "VenVenta.findById", query = "SELECT v FROM VenVenta v WHERE v.id = :id"),
    @NamedQuery(name = "VenVenta.findByStrFolio", query = "SELECT v FROM VenVenta v WHERE v.strFolio = :strFolio"),
    @NamedQuery(name = "VenVenta.findByDteFechaVenta", query = "SELECT v FROM VenVenta v WHERE v.dteFechaVenta = :dteFechaVenta"),
    @NamedQuery(name = "VenVenta.findByDecTotal", query = "SELECT v FROM VenVenta v WHERE v.decTotal = :decTotal")})
public class VenVenta implements Serializable {
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
    @Column(name = "dteFechaVenta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dteFechaVenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "decTotal")
    private BigDecimal decTotal;
    @OneToMany(mappedBy = "idVenVenta")
    private List<VenDetalleVenta> venDetalleVentaList;

    public VenVenta() {
    }

    public VenVenta(Integer id) {
        this.id = id;
    }

    public VenVenta(Integer id, String strFolio, Date dteFechaVenta, BigDecimal decTotal) {
        this.id = id;
        this.strFolio = strFolio;
        this.dteFechaVenta = dteFechaVenta;
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

    public Date getDteFechaVenta() {
        return dteFechaVenta;
    }

    public void setDteFechaVenta(Date dteFechaVenta) {
        this.dteFechaVenta = dteFechaVenta;
    }

    public BigDecimal getDecTotal() {
        return decTotal;
    }

    public void setDecTotal(BigDecimal decTotal) {
        this.decTotal = decTotal;
    }

    @XmlTransient
    public List<VenDetalleVenta> getVenDetalleVentaList() {
        return venDetalleVentaList;
    }

    public void setVenDetalleVentaList(List<VenDetalleVenta> venDetalleVentaList) {
        this.venDetalleVentaList = venDetalleVentaList;
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
        if (!(object instanceof VenVenta)) {
            return false;
        }
        VenVenta other = (VenVenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.VenVenta[ id=" + id + " ]";
    }
    
}
