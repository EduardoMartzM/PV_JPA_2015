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
@Table(name = "ProProducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProProducto.findAll", query = "SELECT p FROM ProProducto p"),
    @NamedQuery(name = "ProProducto.findById", query = "SELECT p FROM ProProducto p WHERE p.id = :id"),
    @NamedQuery(name = "ProProducto.findByStrIdentificador", query = "SELECT p FROM ProProducto p WHERE p.strIdentificador = :strIdentificador"),
    @NamedQuery(name = "ProProducto.findByStrNombre", query = "SELECT p FROM ProProducto p WHERE p.strNombre = :strNombre"),
    @NamedQuery(name = "ProProducto.findByIntCantidad", query = "SELECT p FROM ProProducto p WHERE p.intCantidad = :intCantidad"),
    @NamedQuery(name = "ProProducto.findByDecPrecioCompra", query = "SELECT p FROM ProProducto p WHERE p.decPrecioCompra = :decPrecioCompra"),
    @NamedQuery(name = "ProProducto.findByDecPrecioVenta", query = "SELECT p FROM ProProducto p WHERE p.decPrecioVenta = :decPrecioVenta"),
    @NamedQuery(name = "ProProducto.findByStrDescripcion", query = "SELECT p FROM ProProducto p WHERE p.strDescripcion = :strDescripcion")})
public class ProProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strIdentificador")
    private String strIdentificador;
    @Column(name = "strNombre")
    private String strNombre;
    @Column(name = "intCantidad")
    private Integer intCantidad;
    @Column(name = "decPrecioCompra")
    private Long decPrecioCompra;
    @Column(name = "decPrecioVenta")
    private Long decPrecioVenta;
    @Column(name = "strDescripcion")
    private String strDescripcion;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne
    private CatTipoProducto idCategoria;
    @JoinColumn(name = "idProveedor", referencedColumnName = "id")
    @ManyToOne
    private ProProveedor idProveedor;
    @OneToMany(mappedBy = "idProducto")
    private List<VenDetalleVenta> venDetalleVentaList;

    public ProProducto() {
    }

    public ProProducto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrIdentificador() {
        return strIdentificador;
    }

    public void setStrIdentificador(String strIdentificador) {
        this.strIdentificador = strIdentificador;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public Integer getIntCantidad() {
        return intCantidad;
    }

    public void setIntCantidad(Integer intCantidad) {
        this.intCantidad = intCantidad;
    }

    public Long getDecPrecioCompra() {
        return decPrecioCompra;
    }

    public void setDecPrecioCompra(Long decPrecioCompra) {
        this.decPrecioCompra = decPrecioCompra;
    }

    public Long getDecPrecioVenta() {
        return decPrecioVenta;
    }

    public void setDecPrecioVenta(Long decPrecioVenta) {
        this.decPrecioVenta = decPrecioVenta;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public CatTipoProducto getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CatTipoProducto idCategoria) {
        this.idCategoria = idCategoria;
    }

    public ProProveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ProProveedor idProveedor) {
        this.idProveedor = idProveedor;
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
        if (!(object instanceof ProProducto)) {
            return false;
        }
        ProProducto other = (ProProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ProProducto[ id=" + id + " ]";
    }
    
}
