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
import javax.persistence.Lob;
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
@Table(name = "ProProveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProProveedor.findAll", query = "SELECT p FROM ProProveedor p"),
    @NamedQuery(name = "ProProveedor.findById", query = "SELECT p FROM ProProveedor p WHERE p.id = :id"),
    @NamedQuery(name = "ProProveedor.findByStrRazonSocial", query = "SELECT p FROM ProProveedor p WHERE p.strRazonSocial = :strRazonSocial"),
    @NamedQuery(name = "ProProveedor.findByStrNombreCompaia", query = "SELECT p FROM ProProveedor p WHERE p.strNombreCompaia = :strNombreCompaia"),
    @NamedQuery(name = "ProProveedor.findByStrRFC", query = "SELECT p FROM ProProveedor p WHERE p.strRFC = :strRFC")})
public class ProProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strRazonSocial")
    private String strRazonSocial;
    @Column(name = "strNombreCompa\u00a4ia")
    private String strNombreCompaia;
    @Column(name = "strRFC")
    private String strRFC;
    @Lob
    @Column(name = "imgFotoCompa\u00a4ia")
    private byte[] imgFotoCompaia;
    @OneToMany(mappedBy = "idProveedor")
    private List<ProProducto> proProductoList;
    @JoinColumn(name = "idCatTipoProveedor", referencedColumnName = "id")
    @ManyToOne
    private CatTipoProveedor idCatTipoProveedor;
    @JoinColumn(name = "idComDatoContacto", referencedColumnName = "id")
    @ManyToOne
    private ComDatoContacto idComDatoContacto;
    @JoinColumn(name = "idComDireccion", referencedColumnName = "id")
    @ManyToOne
    private ComDireccion idComDireccion;
    @JoinColumn(name = "idProCatStatusProveedor", referencedColumnName = "id")
    @ManyToOne
    private ProCatStatusProveedor idProCatStatusProveedor;

    public ProProveedor() {
    }

    public ProProveedor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrRazonSocial() {
        return strRazonSocial;
    }

    public void setStrRazonSocial(String strRazonSocial) {
        this.strRazonSocial = strRazonSocial;
    }

    public String getStrNombreCompaia() {
        return strNombreCompaia;
    }

    public void setStrNombreCompaia(String strNombreCompaia) {
        this.strNombreCompaia = strNombreCompaia;
    }

    public String getStrRFC() {
        return strRFC;
    }

    public void setStrRFC(String strRFC) {
        this.strRFC = strRFC;
    }

    public byte[] getImgFotoCompaia() {
        return imgFotoCompaia;
    }

    public void setImgFotoCompaia(byte[] imgFotoCompaia) {
        this.imgFotoCompaia = imgFotoCompaia;
    }

    @XmlTransient
    public List<ProProducto> getProProductoList() {
        return proProductoList;
    }

    public void setProProductoList(List<ProProducto> proProductoList) {
        this.proProductoList = proProductoList;
    }

    public CatTipoProveedor getIdCatTipoProveedor() {
        return idCatTipoProveedor;
    }

    public void setIdCatTipoProveedor(CatTipoProveedor idCatTipoProveedor) {
        this.idCatTipoProveedor = idCatTipoProveedor;
    }

    public ComDatoContacto getIdComDatoContacto() {
        return idComDatoContacto;
    }

    public void setIdComDatoContacto(ComDatoContacto idComDatoContacto) {
        this.idComDatoContacto = idComDatoContacto;
    }

    public ComDireccion getIdComDireccion() {
        return idComDireccion;
    }

    public void setIdComDireccion(ComDireccion idComDireccion) {
        this.idComDireccion = idComDireccion;
    }

    public ProCatStatusProveedor getIdProCatStatusProveedor() {
        return idProCatStatusProveedor;
    }

    public void setIdProCatStatusProveedor(ProCatStatusProveedor idProCatStatusProveedor) {
        this.idProCatStatusProveedor = idProCatStatusProveedor;
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
        if (!(object instanceof ProProveedor)) {
            return false;
        }
        ProProveedor other = (ProProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ProProveedor[ id=" + id + " ]";
    }
    
}
