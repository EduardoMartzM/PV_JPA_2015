/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Ventas;

import Control.ctrlProductos;
import Modelo.Producto;
import Modelo.Venta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo
 */
public class JdVentasManager extends javax.swing.JDialog {

    Producto productoSeleccionado;
    private int idProductoSeleccionado;
    DefaultTableModel modeloProducto, modeloVenta;
    ArrayList<Venta> listVenta = new ArrayList<Venta>();

    /**
     * Creates new form JdVentasManager
     */
    public JdVentasManager(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public JdVentasManager() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.lblAccion.setText("[AGREGAR]");

        String[] head = {"ID", "Clave", "Nombre"};
        Object[][] cuerpo = {};
        modeloProducto = new DefaultTableModel(cuerpo, head);
        this.setTableProducto();
        
        String[] headVenta = {"Producto", "Cantidad"};
        Object[][] cuerpoVenta = {};
        modeloVenta = new DefaultTableModel(cuerpoVenta, headVenta);
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos Varios">  
    private void setTableProducto() throws SQLException {
        try {
            ctrlProductos producto = new ctrlProductos();
            ArrayList<Object> lisPro = producto.getAllProducto();

            for (int i = 0; i < lisPro.size(); i++) {
                Object[] filaTemp1 = new Object[3];
                Producto tempObject = (Producto) lisPro.get(i);
                filaTemp1[0] = tempObject.getId();
                filaTemp1[1] = tempObject.getStrClave();
                filaTemp1[2] = tempObject.getStrNombre();
                modeloProducto.addRow(filaTemp1);
                this.tblArticulos.setModel(modeloProducto);

            }

            tblArticulos.getColumn("ID").setPreferredWidth(0);
            tblArticulos.getColumn("ID").setMinWidth(0);
            tblArticulos.getColumn("ID").setMaxWidth(0);
            tblArticulos.getColumn("ID").setWidth(0);

        } catch (Exception _e) {
            System.out.println("Error setTableProducto() " + _e.getMessage());
        }
    }

    private void setTableProductosQuery(String _clave) {
        try {
            ctrlProductos proveedor = new ctrlProductos();
            ArrayList<Object> lisPro = proveedor.getAllProductoQuery(_clave);

            for (int i = 0; i < lisPro.size(); i++) {
                Object[] filaTemp1 = new Object[3];
                Producto tempObject = (Producto) lisPro.get(i);
                filaTemp1[0] = tempObject.getId();
                filaTemp1[1] = tempObject.getStrClave();
                filaTemp1[2] = tempObject.getStrNombre();
                modeloProducto.addRow(filaTemp1);
                this.tblArticulos.setModel(modeloProducto);

                this.tblArticulos.getColumn("ID").setPreferredWidth(0);
                this.tblArticulos.getColumn("ID").setMinWidth(0);
                this.tblArticulos.getColumn("ID").setMaxWidth(0);
                this.tblArticulos.getColumn("ID").setWidth(0);
            }

        } catch (Exception _e) {
            System.out.println("Erros setTableProductosQuery() " + _e.getMessage());
        }
    }

    private boolean validaExistencia() {
        try {
            int existencia = this.productoSeleccionado.getCantidad();
            int solicitado = Integer.parseInt(this.txtCantidad.getText().trim());
            if (solicitado > existencia) {
                JOptionPane.showMessageDialog(this, "Producto Insuficiente");
                return false;
            } else {
                return true;
            }

        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
        return false;
    }

//    private void setTableVVenta() throws SQLException {
//        try {
//            ctrlVVentas venta = new ctrlVVentas();
//            ArrayList<Object> lisPro = venta.getAllVenta()
//
//            for (int i = 0; i < lisPro.size(); i++) {
//                Object[] filaTemp1 = new Object[3];
//                Producto tempObject = (Producto) lisPro.get(i);
//                filaTemp1[0] = tempObject.getId();
//                filaTemp1[1] = tempObject.getStrClave();
//                filaTemp1[2] = tempObject.getStrNombre();
//                modeloProducto.addRow(filaTemp1);
//                this.tblArticulos.setModel(modeloProducto);
//
//            }
//
//            tblArticulos.getColumn("ID").setPreferredWidth(0);
//            tblArticulos.getColumn("ID").setMinWidth(0);
//            tblArticulos.getColumn("ID").setMaxWidth(0);
//            tblArticulos.getColumn("ID").setWidth(0);
//
//        } catch (Exception _e) {
//            System.out.println("Error setTableProducto() " + _e.getMessage());
//        }
//    }
    
    private void limpiarTablaProductos() {
        while (modeloProducto.getRowCount() > 0) {
            modeloProducto.removeRow(0);
        }
    }
    // </editor-fold>    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel23 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtCriterioBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblClaveProducto = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();
        lblExistenciaProducto = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblPrecioVentaProducto = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblDescripcionProducto = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblProveedorProducto = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCarritoCompra = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtCantidad1 = new javax.swing.JTextField();
        txtCantidad2 = new javax.swing.JTextField();
        txtCantidad3 = new javax.swing.JTextField();
        lblAceptar = new javax.swing.JLabel();
        lblCancelar = new javax.swing.JLabel();
        lblAgregar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblAccion = new javax.swing.JLabel();

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Efectivo:  $");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel10.setText("Clave:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/magnifier52 -16.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel11.setText("[ARTÍCULOS DISPONIBLES]");

        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblArticulos);

        jLabel12.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel12.setText("[DESCRIPCIÓN]");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Clave:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Nombre:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Existencia:");

        lblClaveProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblClaveProducto.setText("...");

        lblNombreProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombreProducto.setText("...");

        lblExistenciaProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblExistenciaProducto.setText("...");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Precio venta:");

        lblPrecioVentaProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrecioVentaProducto.setText("...");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Descripción:");

        lblDescripcionProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDescripcionProducto.setText("...");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Proveedor:");

        lblProveedorProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblProveedorProducto.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClaveProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblExistenciaProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrecioVentaProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDescripcionProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProveedorProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblClaveProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblNombreProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblExistenciaProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioVentaProducto)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionProducto)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProveedorProducto)
                    .addComponent(jLabel18))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Cantidad:");

        jLabel20.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        jLabel20.setText("[CARITO DE COMPRA]");

        tblCarritoCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblCarritoCompra);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Total:      $");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Efectivo:  $");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Cambio:   $");

        lblAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/check.png"))); // NOI18N

        lblCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/x.png"))); // NOI18N
        lblCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCancelarMouseClicked(evt);
            }
        });

        lblAgregar.setFont(new java.awt.Font("Handy Andy", 1, 14)); // NOI18N
        lblAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/shopping-basket-add-72.png"))); // NOI18N
        lblAgregar.setText("AGREGAR");
        lblAgregar.setToolTipText("Agregar al Pedido");
        lblAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/shopping-basket-accept.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/coin-us-dollar-72.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Handy Andy", 1, 36)); // NOI18N
        jLabel25.setText("VENTAS");

        lblAccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAccion.setText("[...]");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCriterioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel19)))
                                        .addGap(31, 31, 31)
                                        .addComponent(lblAgregar))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(175, 175, 175)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtCantidad3, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtCantidad1))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addGap(8, 8, 8)
                                                .addComponent(txtCantidad2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(lblAceptar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblCancelar)
                                                .addGap(26, 26, 26))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel20)
                                .addGap(94, 94, 94)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(lblAccion)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(txtCriterioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(8, 8, 8))))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(txtCantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtCantidad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblAgregar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAceptar)
                    .addComponent(lblCancelar))
                .addContainerGap())
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

    private void lblCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancelarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_lblCancelarMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try {
            if (this.txtCriterioBusqueda.getText().isEmpty()) {
                this.limpiarTablaProductos();
                this.setTableProducto();
            } else {
                this.limpiarTablaProductos();
                this.setTableProductosQuery(this.txtCriterioBusqueda.getText().trim());
            }
        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticulosMouseClicked
        // TODO add your handling code here:
        try {
            int fila;
            fila = this.tblArticulos.getSelectedRow();
            if (fila != -1) {
                ctrlProductos ctrlPro = new ctrlProductos();
                this.idProductoSeleccionado = (int) tblArticulos.getValueAt(fila, 0);
                ArrayList<Object> list = ctrlPro.getProducto(this.idProductoSeleccionado);

                Producto tempObj = (Producto) list.get(0);
                this.productoSeleccionado = tempObj;
                this.lblClaveProducto.setText(tempObj.getStrClave());
                this.lblNombreProducto.setText(tempObj.getStrNombre());
                this.lblExistenciaProducto.setText(String.valueOf(tempObj.getCantidad()));
                this.lblPrecioVentaProducto.setText(String.valueOf(tempObj.getPrecioVenta()));
                this.lblDescripcionProducto.setText(tempObj.getStrDescripcion());
            }

        } catch (Exception _e) {
            Logger.getLogger(_e.getMessage());
        }
    }//GEN-LAST:event_tblArticulosMouseClicked

    private void lblAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarMouseClicked
        // TODO add your handling code here:
        try {
            //Venta v = new Venta();
            if (this.txtCantidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la cantidad de Producto");
            } else if (this.validaExistencia()) {
                Object[] filaTemp1 = new Object[2];
                Venta tempObject = new Venta();
                tempObject.setIdProducto(this.productoSeleccionado.getId());
                tempObject.setCantidad(Integer.parseInt(this.txtCantidad.getText().trim()));
                
                filaTemp1[0] = tempObject.getIdProducto();
                filaTemp1[1] = tempObject.getCantidad();
                modeloVenta.addRow(filaTemp1);
                this.tblCarritoCompra.setModel(modeloVenta);
                
               
                listVenta.add(tempObject);
            }

        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
    }//GEN-LAST:event_lblAgregarMouseClicked

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
            java.util.logging.Logger.getLogger(JdVentasManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdVentasManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdVentasManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdVentasManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdVentasManager dialog = new JdVentasManager(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscar;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAccion;
    private javax.swing.JLabel lblAceptar;
    private javax.swing.JLabel lblAgregar;
    private javax.swing.JLabel lblCancelar;
    private javax.swing.JLabel lblClaveProducto;
    private javax.swing.JLabel lblDescripcionProducto;
    private javax.swing.JLabel lblExistenciaProducto;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecioVentaProducto;
    private javax.swing.JLabel lblProveedorProducto;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTable tblCarritoCompra;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtCantidad2;
    private javax.swing.JTextField txtCantidad3;
    private javax.swing.JTextField txtCriterioBusqueda;
    // End of variables declaration//GEN-END:variables
}
