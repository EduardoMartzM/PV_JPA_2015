/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Proveedores;

//import Control.ctrlProveedores;
//import Modelo.Proveedores;
//import Modelo.catCategoriaProveedor;
//import Modelo.contactoProveedor;
//import Modelo.direccionProveedor;
import Abstract.IProProveedor;
import Vista.Notificaciones.*;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import Concrete.Geocoding;
import Factory.FactoryProProveedor;
import Model.ProProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public class JdProveedoresPrincipal extends javax.swing.JDialog {

    /**
     * Creates new form JdProveedoresPrincipal
     */
    DefaultTableModel modeloProveedor;
    private int idProveedorSeleccionado;
    private ProProveedor proveedorSeleccionado;
    private Geocoding ObjGeocoding = new Geocoding();

    public JdProveedoresPrincipal(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
    }

    public JdProveedoresPrincipal() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);

        String[] head = {"ID", "Razón Social", "RCF"};
        Object[][] cuerpo = {};
        modeloProveedor = new DefaultTableModel(cuerpo, head);
        

        this.setTableProveedores();
    }

    private void setTableProveedores() throws SQLException {
        try {
//            ctrlProveedores proveedor = new ctrlProveedores();
//            ArrayList<Object> lisPro = proveedor.getAllProveedor();
//
//            for (int i = 0; i < lisPro.size(); i++) {
//                Object[] filaTemp1 = new Object[3];
//                Proveedores tempObject = (Proveedores) lisPro.get(i);
//                filaTemp1[0] = tempObject.getId();
//                filaTemp1[1] = tempObject.getRazonSocial();
//                filaTemp1[2] = tempObject.getRfc();
//                modeloProveedor.addRow(filaTemp1);
//                tblResultados.setModel(modeloProveedor);
//
//            }
//            
            IProProveedor factoriaProveedor =  FactoryProProveedor.getInstance().getInstanceAbstract();
            for (int i = 0; i < factoriaProveedor.findProProveedorEntities().size(); i++) {
                Object[] filaTemp1 = new Object[3];
                
                filaTemp1[0] = factoriaProveedor.findProProveedorEntities().get(i).getId();
                filaTemp1[1] = factoriaProveedor.findProProveedorEntities().get(i).getStrRazonSocial();
                filaTemp1[2] = factoriaProveedor.findProProveedorEntities().get(i).getStrRFC();     
                
                modeloProveedor.addRow(filaTemp1);
                tblResultados.setModel(modeloProveedor);
                
            }
//            List<ProProveedorJpaController> listaProveedores =  f;
//            for (ProProveedorJpaController alumnoObj1 : alumnoObj) {
//            JOptionPane.showMessageDialog(null, alumnoObj1.getNombre() +"\n" + alumnoObj1.getIdDireccion().getCiudad());
//        }

            tblResultados.getColumn("ID").setPreferredWidth(0);
            tblResultados.getColumn("ID").setMinWidth(0);
            tblResultados.getColumn("ID").setMaxWidth(0);
            tblResultados.getColumn("ID").setWidth(0);

        } catch (Exception _e) {
            System.out.println("Error setTableProveedores() " + _e.getMessage());
        }
    }

    private void setTableProveedoresQuery(String _razonSocial) {
        try {
//            ctrlProveedores proveedor = new ctrlProveedores();
//            ArrayList<Object> lisPro = proveedor.getAllProveedorQuery(_razonSocial);
//
//            for (int i = 0; i < lisPro.size(); i++) {
//                Object[] filaTemp1 = new Object[3];
//                Proveedores tempObject = (Proveedores) lisPro.get(i);
//                filaTemp1[0] = tempObject.getId();
//                filaTemp1[1] = tempObject.getRazonSocial();
//                filaTemp1[2] = tempObject.getRfc();
//                modeloProveedor.addRow(filaTemp1);
//                tblResultados.setModel(modeloProveedor);
//
//                tblResultados.getColumn("ID").setPreferredWidth(0);
//                tblResultados.getColumn("ID").setMinWidth(0);
//                tblResultados.getColumn("ID").setMaxWidth(0);
//                tblResultados.getColumn("ID").setWidth(0);
//            }
            List<ProProveedor> listaProveedores= FactoryProProveedor.getInstance().getProveedorLikeRZ(_razonSocial);
           // IProProveedor factoriaProveedor =  FactoryProProveedor.getInstance().getInstanceAbstract();
            for (int i = 0; i < listaProveedores.size(); i++) {
                Object[] filaTemp1 = new Object[3];
                
                filaTemp1[0] = listaProveedores.get(i).getId();
                filaTemp1[1] = listaProveedores.get(i).getStrRazonSocial();
                filaTemp1[2] = listaProveedores.get(i).getStrRFC();  
                
                modeloProveedor.addRow(filaTemp1);
                tblResultados.setModel(modeloProveedor);
                
                tblResultados.getColumn("ID").setPreferredWidth(0);
                tblResultados.getColumn("ID").setMinWidth(0);
                tblResultados.getColumn("ID").setMaxWidth(0);
                tblResultados.getColumn("ID").setWidth(0);                
            }
            

        } catch (Exception _e) {
            System.out.println("Error setTableProveedoresQuery() " + _e.getMessage());
        }
    }

    private void limpiarTablaProveedores() {
        while (modeloProveedor.getRowCount() > 0) {
            modeloProveedor.removeRow(0);
        }
    }

    private void limpiar() {
        this.limpiarTablaProveedores();
        this.lblRZProveedor.setText("...");
        this.lblRZProveedor1.setText("...");
        this.lblRfcProveedor.setText("...");
        this.lblEstadoProveedor.setText("...");
        this.lblMunicipioProveedor.setText("...");
        this.lblColoniaProveedor.setText("...");
        this.lblCalleProveedor.setText("...");
        this.lblNumeroProveedor.setText("...");
        this.lblTelefonoProveedor.setText("...");
        this.lblEmailProveedor.setText("...");
        this.lblImagenProveedor.setIcon(null);
        this.lblStatusProveedor.setText("...");
        this.txtCriterioBusqueda.setText("");
        this.proveedorSeleccionado = null;
    }

    private boolean validaFechaCaptura() {
        try {
            Calendar calendario = new GregorianCalendar();

            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int day = calendario.get(Calendar.DAY_OF_WEEK);

            if (day >= 2 && day <= 6) {
                if (hora >= 11 && hora <= 18) {
                    System.out.println("EN HORARIO :D");
                    return true;
                } else {
                    //JOptionPane.showMessageDialog(this, "Horario inválido para realizar la captura. \nLos Horarios son: \n*Lunes-Viernes de 9am a 6pm \n*Sábados de 9am a 2pm", "PROVEEDORES", JOptionPane.INFORMATION_MESSAGE);
                    frmNotificacionProveedoresHorarioInvalido horario = new frmNotificacionProveedoresHorarioInvalido();
                    return false;
                }
            } else if (day == 1) {
                if (hora >= 9 && hora <= 14) {
                    System.out.println("EN HORARIO :D");
                    return true;
                } else {
                    //JOptionPane.showMessageDialog(this, "Horario inválido para realizar la captura \nLos Horarios son: \nLunes-Viernes de 9am a 6pm y sábados de 9am a 2pm", "PROVEEDORES", JOptionPane.INFORMATION_MESSAGE);
                    frmNotificacionProveedoresHorarioInvalido horario = new frmNotificacionProveedoresHorarioInvalido();
                    return false;
                }

            } else {
                //JOptionPane.showMessageDialog(this, "Horario inválido para realizar la captura \nLos Horarios son: \nLunes-Viernes de 9am a 6pm y sábados de 9am a 2pm", "PROVEEDORES", JOptionPane.INFORMATION_MESSAGE);
                frmNotificacionProveedoresHorarioInvalido horario = new frmNotificacionProveedoresHorarioInvalido();
                return false;
            }

        } catch (Exception _e) {
            System.out.println(_e.getMessage());
            _e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCriterioBusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblImagenProveedor = new javax.swing.JLabel();
        lblRZProveedor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblRZProveedor1 = new javax.swing.JLabel();
        lblRfcProveedor = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblEstadoProveedor = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblMunicipioProveedor = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblColoniaProveedor = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblCalleProveedor = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblNumeroProveedor = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblEmailProveedor = new javax.swing.JLabel();
        lblTelefonoProveedor = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblStatusProveedor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/customer-service-72.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        tblResultados.setBackground(new java.awt.Color(204, 204, 204));
        tblResultados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Razón Social", "RFC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.getTableHeader().setReorderingAllowed(false);
        tblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblResultados);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel8.setFont(new java.awt.Font("Handy Andy", 1, 18)); // NOI18N
        jLabel8.setText("AGREGAR");

        jLabel10.setFont(new java.awt.Font("Handy Andy", 1, 18)); // NOI18N
        jLabel10.setText("EDITAR");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/pencil.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/compose.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jLabel10))
                        .addComponent(jLabel3)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jLabel8)
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jLabel10)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Handy Andy", 1, 24)); // NOI18N
        jLabel9.setText("PROVEEDORES");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/magnifier52 -16.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/magicwand - 32.png"))); // NOI18N
        btnLimpiar.setToolTipText("Limpia todos los datos.");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lblImagenProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagenProveedor.setText("[LOGO]");
        lblImagenProveedor.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        lblRZProveedor.setFont(new java.awt.Font("Handy Andy", 1, 18)); // NOI18N
        lblRZProveedor.setText("...");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[INFORMACIÓN]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Handy Andy", 1, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Razón Social:");

        lblRZProveedor1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRZProveedor1.setText("...");

        lblRfcProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRfcProveedor.setText("...");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("RFC:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRZProveedor1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(lblRfcProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lblRZProveedor1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lblRfcProveedor))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[DIRECCIÓN]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Handy Andy", 1, 14))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Estado:");

        lblEstadoProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblEstadoProveedor.setText("...");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Municipio:");

        lblMunicipioProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMunicipioProveedor.setText("...");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Colonia:");

        lblColoniaProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblColoniaProveedor.setText("...");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Calle:");

        lblCalleProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCalleProveedor.setText("...");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Número:");

        lblNumeroProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNumeroProveedor.setText("...");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/location - 32.png"))); // NOI18N
        jButton2.setText("VER UBICACIÓN");
        jButton2.setToolTipText("Muestra mapa con la ubicación del Proveedor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(34, 34, 34)
                        .addComponent(lblEstadoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(29, 29, 29)
                        .addComponent(lblNumeroProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(lblMunicipioProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(31, 31, 31)
                        .addComponent(lblColoniaProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(48, 48, 48)
                        .addComponent(lblCalleProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lblEstadoProveedor))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblMunicipioProveedor))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(lblColoniaProveedor))
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(lblCalleProveedor))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(lblNumeroProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[CONTACTO]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Handy Andy", 1, 14))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Teléfono:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("e-mail:");

        lblEmailProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblEmailProveedor.setText("...");

        lblTelefonoProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTelefonoProveedor.setText("...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(0, 235, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblEmailProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblTelefonoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTelefonoProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(7, 7, 7)
                .addComponent(lblEmailProveedor)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[STATUS]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Handy Andy", 1, 14))); // NOI18N

        lblStatusProveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblStatusProveedor.setText("...");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(lblStatusProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatusProveedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblImagenProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCriterioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(404, 404, 404))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblRZProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1)
                                .addGap(241, 241, 241)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel9))
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCriterioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(lblRZProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblImagenProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
//        try {
//            IfrmProveedoresPrincipal proveedores = new IfrmProveedoresPrincipal();
//            proveedores.setVisible(true);
//
//            JdProveedoresManager p = new JdProveedoresManager();
//            p.setVisible(true);
//
//        } catch (Exception _e) {
//            System.out.println(_e.getMessage());
//            _e.printStackTrace();
//        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultadosMouseClicked
        // TODO add your handling code here:
        try {
            int fila;
            fila = this.tblResultados.getSelectedRow();
            if (fila != -1) {
//                ctrlProveedores ctrlPro = new ctrlProveedores();
                this.idProveedorSeleccionado = (int) tblResultados.getValueAt(fila, 0);
//                ArrayList<Object> list = ctrlPro.getProveedor(this.idProveedorSeleccionado);
                
                
                ProProveedor proveedor = FactoryProProveedor.getInstance().getProveedorId(this.idProveedorSeleccionado);
                
                

//                Proveedores tempObj = (Proveedores) list.get(0);
                this.proveedorSeleccionado = proveedor;
                this.lblRZProveedor.setText(proveedor.getStrRazonSocial());
                this.lblRZProveedor1.setText(proveedor.getStrRazonSocial());
                this.lblRfcProveedor.setText(proveedor.getStrRFC());

                //Ajusta imagen
                ImageIcon fot = new ImageIcon(proveedor.getImgFotoCompaia());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblImagenProveedor.getWidth(), lblImagenProveedor.getHeight(), Image.SCALE_DEFAULT));
                lblImagenProveedor.setIcon(icono);
                //

//                ArrayList<Object> listDireccion = ctrlPro.getDireccionProveedor(tempObj.getIdDireccionProveedor());
//                direccionProveedor direccionTemp = (direccionProveedor) listDireccion.get(0);
                this.lblEstadoProveedor.setText(proveedor.getIdComDireccion().getIdEstado().getStrValor());
                this.lblMunicipioProveedor.setText(proveedor.getIdComDireccion().getStrMunicipio());
                this.lblColoniaProveedor.setText(proveedor.getIdComDireccion().getStrColonia());
                this.lblCalleProveedor.setText(proveedor.getIdComDireccion().getStrCalle());
                this.lblNumeroProveedor.setText(proveedor.getIdComDireccion().getStrNumero());

//                ArrayList<Object> listContacto = ctrlPro.getContactoProveedor(tempObj.getIdContactoProveedor());
//                contactoProveedor contactoTemp = (contactoProveedor) listContacto.get(0);
                this.lblTelefonoProveedor.setText(proveedor.getIdComDatoContacto().getStrTelefonoHome());
                this.lblEmailProveedor.setText(proveedor.getIdComDatoContacto().getStrEmail());

                if (proveedor.getIdProCatStatusProveedor().getId() == 2) {
                    this.lblStatusProveedor.setText(":)");
                } else {
                    this.lblStatusProveedor.setText(":(");
                }

            }

        } catch (Exception _e) {
            Logger.getLogger(_e.getMessage());
        }
    }//GEN-LAST:event_tblResultadosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (this.txtCriterioBusqueda.getText().isEmpty()) {
            try {
                this.limpiarTablaProveedores();
                this.setTableProveedores();

            } catch (Exception _e) {
                Logger.getLogger(_e.getMessage());
            }

        } else {
            try {
                this.limpiarTablaProveedores();
                this.setTableProveedoresQuery(this.txtCriterioBusqueda.getText());

            } catch (Exception _e) {
                Logger.getLogger(_e.getMessage());
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        this.limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (this.lblMunicipioProveedor.getText().equals("...")) {
                //JOptionPane.showMessageDialog(this, "Seleccione primero un proveedor", "MOSTRAR UBICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                frmNotificacionProveedoresNoSelectUbication ubicacion= new frmNotificacionProveedoresNoSelectUbication();
            } else {

                Browser browser = new Browser();
                BrowserView browserView = new BrowserView(browser);

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(browserView, BorderLayout.CENTER);
                frame.setSize(900, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                //frame.setTitle(this.lblRZProveedor.getText() + " - Ubicación");

                //OBTENER LATITUD-LONGITUD
                double latitud = 0;
                double longitud = 0; 
                String direccion = this.lblCalleProveedor.getText() + " "+this.lblNumeroProveedor.getText()+" " + this.lblColoniaProveedor.getText() + " " + this.lblMunicipioProveedor.getText();
                if (!direccion.isEmpty()) {
                    //JText_CD_DireEnc.setText("");
                    Point2D.Double resultado = ObjGeocoding.getCoordinates(direccion);
                    latitud = (double) resultado.x;
                    longitud = (double) resultado.y;
                    String direccionCompleta = ObjGeocoding.getAddressFound();
                    frame.setTitle(this.lblRZProveedor.getText() + "    ->    "+ direccionCompleta);
                }

                //
                //browser.loadURL("http://maps.google.com");
                browser.loadURL("http://maps.google.es/maps?f=q&hl=es&geocode=&q=" + latitud + "," + longitud);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        try {
//            if(this.validaFechaCaptura())
//            {
            JdProveedoresManager manager = new JdProveedoresManager();
            manager.setVisible(true);
            this.limpiar();
            this.setTableProveedores();
//            }

        } catch (Exception e) {
            Logger.getLogger(JdProveedoresManager.class.toString());
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        try {
//            if(this.validaFechaCaptura())
//            {
            if (this.proveedorSeleccionado != null) {
                Object p = this.proveedorSeleccionado;
                JdProveedoresManager manager = new JdProveedoresManager(p);
                this.limpiar();
                this.setTableProveedores();

            } else {
                //JOptionPane.showMessageDialog(this, "Seleccione un proveedor para editar", "PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
                frmNotificacionProveedoresNoSelect prov = new frmNotificacionProveedoresNoSelect();
            }
//            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(JdProveedoresPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdProveedoresPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdProveedoresPrincipal dialog = null;
                try {
                    dialog = new JdProveedoresPrincipal(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(JdProveedoresPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCalleProveedor;
    private javax.swing.JLabel lblColoniaProveedor;
    private javax.swing.JLabel lblEmailProveedor;
    private javax.swing.JLabel lblEstadoProveedor;
    private javax.swing.JLabel lblImagenProveedor;
    private javax.swing.JLabel lblMunicipioProveedor;
    private javax.swing.JLabel lblNumeroProveedor;
    private javax.swing.JLabel lblRZProveedor;
    private javax.swing.JLabel lblRZProveedor1;
    private javax.swing.JLabel lblRfcProveedor;
    private javax.swing.JLabel lblStatusProveedor;
    private javax.swing.JLabel lblTelefonoProveedor;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtCriterioBusqueda;
    // End of variables declaration//GEN-END:variables
}
