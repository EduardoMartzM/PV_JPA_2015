/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Proveedores;

//import Control.ctrlProveedores;
//import Modelo.Proveedores;
//import Modelo.catCategoriaProveedor;
//import Modelo.catStatusProveedor;
//import Modelo.contactoProveedor;
//import Modelo.direccionProveedor;
import Abstract.IComDatoContacto;
import Abstract.IComDireccion;
import Abstract.IProProveedor;
import Concrete.CatTipoProveedorJpaController;
import Concrete.ProCatStatusProveedorJpaController;
import Concrete.ProProveedorJpaController;
import Factory.FactoryCatTipoProveedor;
import Factory.FactoryComDatoContacto;
import Factory.FactoryComDireccion;
import Factory.FactoryProCatStatusProveedor;
import Factory.FactoryProProveedor;
import Model.CatTipoProveedor;
import Model.ComDatoContacto;
import Model.ComDireccion;
import Model.ComEstadoDireccion;
import Model.ProCatStatusProveedor;
import Model.ProProveedor;
import Vista.Notificaciones.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Eduardo
 */
public class JdProveedoresManager extends javax.swing.JDialog {
    
    private ProProveedor pro;
    private String rutaImagen = "";
    private boolean AccionAgregar;

    /**
     * Creates new form JdProveedoresManager
     */
    public JdProveedoresManager(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public JdProveedoresManager() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.lblAccion.setText("[AGREGAR]");
        this.AccionAgregar = true;
        this.setComboBoxInitial();
    }
    
    public JdProveedoresManager(Object _o) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pro = (ProProveedor) _o;
        this.lblAccion.setText("[EDITAR]");
        this.cargaImagen();
        this.setComboBoxInitial();
        this.loadInformation();
        this.AccionAgregar = false;
    }
    
    private void loadInformation() {
        try {
//            ProProveedorJpaController ctrlPro = new ProProveedorJpaController();
            this.txtCompañia.setText(this.pro.getStrNombreCompaia());
            this.txtRazonSocial.setText(this.pro.getStrRazonSocial());
            this.txtRfc.setText(this.pro.getStrRFC());
            this.cmbCategoria.setSelectedItem((CatTipoProveedor) this.pro.getIdCatTipoProveedor());

//            ArrayList<Object> listDireccion = ctrlPro.getDireccionProveedor(this.pro.getIdDireccionProveedor());
//            direccionProveedor direccionTemp = (direccionProveedor) listDireccion.get(0);
            this.cmbEstado.setSelectedItem((ComEstadoDireccion) this.pro.getIdComDireccion().getIdEstado());
            this.txtMunicipio.setText(this.pro.getIdComDireccion().getStrMunicipio());
            this.txtColonia.setText(this.pro.getIdComDireccion().getStrColonia());
            this.txtCalle.setText(this.pro.getIdComDireccion().getStrCalle());
            this.txtNumero.setText(this.pro.getIdComDireccion().getStrNumero());

//            ArrayList<Object> listContacto = ctrlPro.getContactoProveedor(this.pro.getIdContactoProveedor());
//            contactoProveedor contactoTemp = (contactoProveedor) listContacto.get(0);
            this.txtTelefono.setText(this.pro.getIdComDatoContacto().getStrTelefonoHome());
            this.txtEmail.setText(this.pro.getIdComDatoContacto().getStrEmail());
            
            this.cmbStatus.setSelectedItem(this.pro.getIdProCatStatusProveedor());
        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
        
    }
    
    private void setComboBoxInitial() {
        try {
            //Categoría
            DefaultComboBoxModel x = new DefaultComboBoxModel();
            //CatTipoProveedorJpaController ctrlPro = new CatTipoProveedorJpaController();
            List<CatTipoProveedor> listCategoria = FactoryCatTipoProveedor.getInstance().getAllTipoProveedor();
            x.addElement("Seleccionar...");
            for (int i = 0; i < listCategoria.size(); i++) {
                CatTipoProveedor categoriaTemp = listCategoria.get(i);
                x.addElement(categoriaTemp);
            }
            this.cmbCategoria.setModel(x);

            //Status
            DefaultComboBoxModel y = new DefaultComboBoxModel();
            //ProCatStatusProveedorJpaController ctrlPro = new ProCatStatusProveedorJpaController();
            List<ProCatStatusProveedor> listStatus = FactoryProCatStatusProveedor.getInstance().getAllStatusProveedor();
            y.addElement("Seleccionar...");
            for (int i = 0; i < listStatus.size(); i++) {
                ProCatStatusProveedor catTemp = listStatus.get(i);
                y.addElement(catTemp);
            }
            this.cmbStatus.setModel(y);
            
        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
    }
    
    private void cargaImagen() {
        try {
            //Ajusta imagen
            ImageIcon fot = new ImageIcon(this.pro.getImgFotoCompaia());
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblLogoProveedor.getWidth(), lblLogoProveedor.getHeight(), Image.SCALE_DEFAULT));
            lblLogoProveedor.setIcon(icono);
            //

        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblLogoProveedor = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtRfc = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        txtColonia = new javax.swing.JTextField();
        txtMunicipio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtCompañia = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblAccion = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblAceptar = new javax.swing.JLabel();
        lblCancelar = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        jLabel9.setFont(new java.awt.Font("Handy Andy", 1, 24)); // NOI18N
        jLabel9.setText("PROVEEDORES");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Imagen Corporativa:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/magnifier52 -16.png"))); // NOI18N
        jButton1.setText("Seleccionar...");
        jButton1.setToolTipText("Busca archivo de Imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblLogoProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoProveedor.setText("[logo]");
        lblLogoProveedor.setToolTipText("Logo de la Empresa");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("RFC:");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Categoría:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Razón Social:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Estado:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Municipio:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Colonia:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Calle:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Número:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Teléfono:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("e-mail:");

        jLabel18.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("[INFORMACIÓN]");

        jLabel24.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("[DIRECCIÓN]");

        jLabel25.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("[CONTACTO]");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("*");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText("*");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("*");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("*");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("*");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("*");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Móvil:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 0, 0));
        jLabel35.setText("*");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Compañía:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(70, 70, 70)
                        .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(34, 34, 34)
                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addComponent(jLabel24)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(54, 54, 54)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(38, 38, 38)
                        .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(51, 51, 51)
                        .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(68, 68, 68)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(49, 49, 49)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel29))
                    .addComponent(jLabel25)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(60, 60, 60)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel31))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel30)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCompañia, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel37)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtCompañia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10))
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel11))
                    .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel22))
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addComponent(jLabel24)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12))
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(20, 20, 20)
                .addComponent(jLabel25)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel31)))
                .addGap(31, 31, 31))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/customer-service-72.png"))); // NOI18N

        lblAccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAccion.setText("[...]");

        jLabel16.setFont(new java.awt.Font("Handy Andy", 1, 36)); // NOI18N
        jLabel16.setText("PROVEEDORES");

        jLabel26.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel26.setText("[STATUS]:");

        lblAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/check.png"))); // NOI18N
        lblAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAceptarMouseClicked(evt);
            }
        });

        lblCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/x.png"))); // NOI18N
        lblCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCancelarMouseClicked(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("*");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 0, 0));
        jLabel33.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblLogoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel33))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(lblAceptar)
                                                .addGap(64, 64, 64)
                                                .addComponent(lblCancelar)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(0, 40, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(lblAccion))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jButton1)
                            .addComponent(jLabel32))
                        .addGap(39, 39, 39)
                        .addComponent(lblLogoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel33))
                                .addGap(12, 12, 12)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(lblAceptar))
                            .addComponent(lblCancelar))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de Imagen", "JPG"));
            fileChooser.setCurrentDirectory(new java.io.File("c:/imagenes_temp/"));
            int result = fileChooser.showOpenDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {

                //ruta y nombre del archivo
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                this.rutaImagen = fileChooser.getSelectedFile().getAbsolutePath();
                String name = fileChooser.getSelectedFile().getName();
                
                ImageIcon imagenExterna = new ImageIcon(this.rutaImagen);
                this.lblLogoProveedor.setIcon(imagenExterna);
                
            } else {
                this.rutaImagen = "";
            }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAceptarMouseClicked
        // TODO add your handling code here:
        try {
            if (this.AccionAgregar == true) {
                if (this.validaVacios()) {
                    //ProProveedorJpaController pro = new ProProveedorJpaController();

                    ComDireccion direccion = new ComDireccion();
                    direccion.setIdEstado((ComEstadoDireccion) this.cmbEstado.getSelectedItem());
                    direccion.setStrMunicipio(this.txtMunicipio.getText().trim());
                    direccion.setStrColonia(this.txtColonia.getText().trim());
                    direccion.setStrCalle(this.txtCalle.getText().trim());
                    direccion.setStrNumero(this.txtNumero.getText().trim());
                    
                    IComDireccion factoryDireccion = FactoryComDireccion.getInstance().getInstanceAbstract();
                    factoryDireccion.create(direccion);
                    System.out.println("Dirección agregada :)");
                    
                    ComDatoContacto contacto = new ComDatoContacto();
                    contacto.setStrTelefonoMovil(this.txtTelefono1.getText().trim());
                    contacto.setStrTelefonoHome(this.txtTelefono.getText().trim());
                    contacto.setStrEmail(this.txtEmail.getText().trim());
                    
                    IComDatoContacto factoryContacto = FactoryComDatoContacto.getInstance().getInstanceAbstract();
                    factoryContacto.create(contacto);
                    System.out.println("Contacto Agregado :)");
                    
                    ProProveedor p = new ProProveedor();
                    p.setStrNombreCompaia(this.txtCompañia.getText().trim());
                    p.setStrRazonSocial(this.txtRazonSocial.getText().trim());
                    p.setStrRFC(this.txtRfc.getText().trim());
                    p.setIdCatTipoProveedor(((CatTipoProveedor) this.cmbCategoria.getSelectedItem()));
                    p.setIdProCatStatusProveedor(((ProCatStatusProveedor) this.cmbStatus.getSelectedItem()));
                    p.setIdComDireccion(direccion);
                    p.setIdComDatoContacto(contacto);
                    //p.setRutaImagen(this.rutaImagen);

                    IProProveedor factoryProveedor = FactoryProProveedor.getInstance().getInstanceAbstract();
                    factoryProveedor.create(p);
                    
                    frmNotificacionProveedoresInsert proInsert = new frmNotificacionProveedoresInsert();
                    this.sendEmail();
                    this.dispose();
                    
                }
                
            } else if (this.validaVaciosEdita()) {
                //ctrlProveedores pro = new ctrlProveedores();

                ComDireccion direccion = new ComDireccion();
                direccion = FactoryComDireccion.getInstance().getInstanceAbstract().findComDireccion(this.pro.getIdComDireccion().getId());
                direccion.setIdEstado((ComEstadoDireccion) this.cmbEstado.getSelectedItem());
                direccion.setStrMunicipio(this.txtMunicipio.getText().trim());
                direccion.setStrColonia(this.txtColonia.getText().trim());
                direccion.setStrCalle(this.txtCalle.getText().trim());
                direccion.setStrNumero(this.txtNumero.getText().trim());
                
                IComDireccion factoriaDireccion = FactoryComDireccion.getInstance().getInstanceAbstract();
                factoriaDireccion.edit(direccion);
                System.out.println("Dirección Actualizada :)");
                
                ComDatoContacto contacto = new ComDatoContacto();
                contacto = FactoryComDatoContacto.getInstance().getInstanceAbstract().findComDatoContacto(this.pro.getIdComDatoContacto().getId());
                contacto.setStrTelefonoMovil(this.txtTelefono1.getText().trim());
                contacto.setStrTelefonoHome(this.txtTelefono.getText().trim());
                contacto.setStrEmail(this.txtEmail.getText().trim());
                
                IComDatoContacto factoriaContacto = FactoryComDatoContacto.getInstance().getInstanceAbstract();
                factoriaContacto.edit(contacto);
                System.out.println("Contacto Actualizado :)");
                
                ProProveedor p = new ProProveedor();
                p = FactoryProProveedor.getInstance().getInstanceAbstract().findProProveedor(this.pro.getId());
                p.setStrNombreCompaia(this.txtCompañia.getText().trim());
                p.setStrRazonSocial(this.txtRazonSocial.getText().trim());
                p.setStrRFC(this.txtRfc.getText().trim());
                p.setIdCatTipoProveedor(((CatTipoProveedor) this.cmbCategoria.getSelectedItem()));
                p.setIdProCatStatusProveedor(((ProCatStatusProveedor) this.cmbStatus.getSelectedItem()));
                p.setIdComDireccion(this.pro.getIdComDireccion());
                p.setIdComDatoContacto(this.pro.getIdComDatoContacto());
//                p.setRutaImagen(this.rutaImagen);

                IProProveedor factoriaProveedor = FactoryProProveedor.getInstance().getInstanceAbstract();
                factoriaProveedor.edit(p);
                
                frmNotificacionProveedoresEdit proEdit = new frmNotificacionProveedoresEdit();
                this.sendEmail();
                this.dispose();
                
            }
        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
    }//GEN-LAST:event_lblAceptarMouseClicked

    private void lblCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancelarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_lblCancelarMouseClicked
    
    private boolean validaVacios() {
        try {
            if (this.txtRazonSocial.getText().isEmpty() || this.txtRfc.getText().isEmpty() || this.cmbCategoria.getSelectedIndex() == 0
                    || this.txtMunicipio.getText().isEmpty() || this.txtMunicipio.getText().isEmpty() || this.txtColonia.getText().isEmpty()
                    || this.txtCalle.getText().isEmpty() || this.txtNumero.getText().isEmpty() || this.txtTelefono.getText().isEmpty()
                    || this.txtEmail.getText().isEmpty() || this.rutaImagen.isEmpty() || this.cmbStatus.getSelectedIndex() == 0 || !this.validaEmail(this.txtEmail.getText().trim())) {
                frmNotificacionProveedoresCamposNull proveNull = new frmNotificacionProveedoresCamposNull();
                return false;
                
            }
            
        } catch (Exception e) {
        }
        return true;
        
    }
    
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private boolean validaEmail(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        
    }
    
    private boolean validaVaciosEdita() {
        try {
            if (this.txtRazonSocial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Razón Social vacía", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtRazonSocial.setFocusable(true);
                return false;
            }
            if (this.txtRfc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "RFC vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtRfc.setFocusable(true);
                return false;
            }
            if (this.cmbCategoria.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Categoría sin seleccionar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.cmbCategoria.setFocusable(true);
                return false;
            }
            if (this.txtMunicipio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Estado vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtMunicipio.setFocusable(true);
                return false;
            }
            if (this.txtMunicipio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Municipio vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtMunicipio.setFocusable(true);
                return false;
            }
            if (this.txtColonia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Colonia vacía", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtColonia.setFocusable(true);
                return false;
            }
            if (this.txtCalle.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Calle vacía", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtCalle.setFocusable(true);
                return false;
            }
            if (this.txtNumero.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Número vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtNumero.setFocusable(true);
                return false;
            }
            if (this.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Teléfono vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtTelefono.setFocusable(true);
                return false;
            }
            if (this.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "E-mail vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.txtEmail.setFocusable(true);
                return false;
            }
            if (!this.validaEmail(this.txtEmail.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Formato de email incorrecto", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                this.cmbStatus.setFocusable(true);
                return false;
            }
            
        } catch (Exception e) {
        }
        return true;
        
    }
    
    private void sendEmail() {
        try {
            final String userName = "e.mon.mar0403@gmail.com";
            final String password = "lalomontez";
            String messages = "Ahora eres un proveedor de nuestra abarrotera :3 \n¡FELICIDADES!";
            String to = this.txtEmail.getText().trim();
            String subject = "Proveedor Activo :)";

            //MAIL
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            
            Session session;
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });
            
            try {
                
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(userName));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(messages);
                
                Transport.send(message);
                //JOptionPane.showMessageDialog(this, "Correo enviado al Proveedor ;)");
                frmNotificacionProveedoresCorreo proCorreo = new frmNotificacionProveedoresCorreo();
                
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdProveedoresManager dialog = new JdProveedoresManager(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCategoria;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAccion;
    private javax.swing.JLabel lblAceptar;
    private javax.swing.JLabel lblCancelar;
    private javax.swing.JLabel lblLogoProveedor;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtCompañia;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    // End of variables declaration//GEN-END:variables
}
