/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import Atxy2k.CustomTextField.RestrictedTextField;
import Clientes.Clientes;
import ExportarArchivo.LlamarPdf;
import Tickets.Ticket;
import Tickets.TicketTres;
import Usuarios.MenuUsuarios;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import inventario.ctrlinventario;
import java.awt.event.KeyEvent;
import opticx1.Conexionbd;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import static mondrian.olap.Category.Level;
import opticx1.Ventanaadmin;
import org.apache.log4j.Logger;
import javax.swing.table.TableColumn;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import mondrian.rolap.RolapConnectionProperties;
import java.lang.Object;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.PrinterInfo;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterLocation;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterState;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.print.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.ServiceUI;
import javax.print.attribute.*;
import opticx1.control_existencias;
import opticx1.login;
import static opticx1.login.Guardausuario;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.log4j.BasicConfigurator;
import validaciones.solo_numeros;
import java.util.logging.Level;
import org.apache.log4j.BasicConfigurator;
import static org.hsqldb.lib.java.JavaSystem.gc;
import org.olap4j.metadata.Property;
import java.lang.Object;
import java.util.Formatter;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import opticx1.VentanaUsuario;
import static opticx1.VentanaUsuario.invi;
import static opticx1.login.invitado;

/**
 *
 * @author LEO
 */
public class MenuVentas extends javax.swing.JFrame {

    control_existencias con = new control_existencias();
    public int idEmpleado;
    Statement sentencia = null;
    Statement sentenciaStock = null;
    Statement sentenciaRestar = null;
    int cantidad, productoStock, nuevoStock;
    String comparaImpresora;
    int numImpre;
    double descuentoVentadirecta;
    public String query = null;
    public String query1;

    public MenuVentas() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sistema de Gestion de Opticx Ópticas");
        AutoCompleteDecorator.decorate(cbxCliente);
        AutoCompleteDecorator.decorate(cbxBuscarPorCliente);
        Validar();
        lblInfolike.setVisible(false);
        desde.setVisible(false);
        hasta.setVisible(false);
        txtBuscarId.requestFocus();
        cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");
        VentasHoy();
        BotonesReporteVisibles();
        try {

            Object[] llenar_combo_puesto = con.combox("clientes", "concat_ws(' ', CLIENTE_FIRST_NAME, CLIENTE_PATERNO,CLIENTE_MATERNO)");

            cbxCliente.removeAllItems();
            for (int i = 0; i < llenar_combo_puesto.length; i++) {

                cbxCliente.addItem((String) llenar_combo_puesto[i]);

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NuevaVenta = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblNumeroVenta = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        lblNombreVendedor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbxCliente = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        panelTranslucido1 = new org.edisoncor.gui.panel.PanelTranslucido();
        jLabel15 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        txtDescuentoProducto = new javax.swing.JTextField();
        btnOkDescuentoProducto = new javax.swing.JButton();
        txtDescuentoVenta = new javax.swing.JTextField();
        btnOkDescuentoVenta = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnAgregarVentaLista = new javax.swing.JButton();
        btnEliminarFila = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        btnOkCantidad = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnMasCantidad = new javax.swing.JButton();
        btnMenosCantidad = new javax.swing.JButton();
        tglRegalo = new javax.swing.JToggleButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPaga = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        chkIva = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaObservaciones = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        lblDescuentoVenta = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ConfiguraImpresora = new javax.swing.JDialog();
        cbxSelecImpre = new javax.swing.JComboBox<String>();
        btnGuardarImpre = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ClickDerecho = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        Detalles = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblClienteNombre = new javax.swing.JLabel();
        lblVendedorDetalle = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblFechaCompraCleinte = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblNumeroVentaDeta = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblClienteNombre1 = new javax.swing.JLabel();
        lblClienteNombre2 = new javax.swing.JLabel();
        lblsubtotalventa = new javax.swing.JLabel();
        lblIVAdetalle = new javax.swing.JLabel();
        lblDescaVent = new javax.swing.JLabel();
        lblClienteNombre5 = new javax.swing.JLabel();
        lblDetaTotalfinal = new javax.swing.JLabel();
        lblClienteNombre7 = new javax.swing.JLabel();
        lblDetaTotal1 = new javax.swing.JLabel();
        lblDetaTotal2 = new javax.swing.JLabel();
        lblcambiodeta = new javax.swing.JLabel();
        lblDineroRecibi = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ImprimirNormal = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        cbx = new javax.swing.JComboBox<String>();
        jLabel25 = new javax.swing.JLabel();
        btnCambiarImpre = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ImprimirReporte = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        cbxReporte = new javax.swing.JComboBox<String>();
        jLabel29 = new javax.swing.JLabel();
        btnCambiarImpre1 = new javax.swing.JButton();
        btnXtodo = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        btnXFechaEntre = new javax.swing.JButton();
        btnXfechaEspecifica = new javax.swing.JButton();
        btnXEmpleado = new javax.swing.JButton();
        btnXID = new javax.swing.JButton();
        btnXCliente = new javax.swing.JButton();
        btnXHoy = new javax.swing.JButton();
        btnXAyer = new javax.swing.JButton();
        GuardarReporte = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnCrearReporte = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtBuscarId = new javax.swing.JTextField();
        btnBuscarVenta = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbxSeleccionarBusqueda = new javax.swing.JComboBox<String>();
        datefechaUno = new com.toedter.calendar.JDateChooser();
        dateFechaDos = new com.toedter.calendar.JDateChooser();
        desde = new javax.swing.JLabel();
        hasta = new javax.swing.JLabel();
        txtBuscarLikeFecha = new javax.swing.JTextField();
        lblInfolike = new javax.swing.JLabel();
        btnBuscaAvanzado = new javax.swing.JButton();
        cbxBuscarPorCliente = new javax.swing.JComboBox<String>();
        lblInfolike1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clockFace1 = new org.edisoncor.gui.varios.ClockFace();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        jLabel2 = new javax.swing.JLabel();
        btnHoyMenuVenta = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        NuevaVenta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("VENTA NÚMERO:");

        lblNumeroVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNumeroVenta.setForeground(new java.awt.Color(153, 0, 0));
        lblNumeroVenta.setText("VENTA NÚMERO:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("FECHA DE EMISIÓN:");

        lblFecha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFecha.setText("FECHA DE EMISIÓN:");

        lblVendedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblVendedor.setText("NOMBRE");

        lblNombreVendedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreVendedor.setText("VENDEDOR:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroVenta)
                .addGap(172, 172, 172)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha)
                .addGap(184, 184, 184)
                .addComponent(lblNombreVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVendedor)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(lblNumeroVenta)
                    .addComponent(lblFecha)
                    .addComponent(lblVendedor)
                    .addComponent(lblNombreVendedor))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        NuevaVenta.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1170, 60));

        cbxCliente.setEditable(true);
        cbxCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbxCliente.setMaximumRowCount(10000);
        cbxCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("CLIENTE:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addGap(31, 31, 31)
                .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        NuevaVenta.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1170, 70));

        panelRect1.setColorPrimario(new java.awt.Color(204, 204, 204));
        panelRect1.setColorSecundario(new java.awt.Color(204, 204, 204));
        panelRect1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "DESCRIPCIÓN", "PRECIO", "% DESCUENTO", "CANTIDAD", "IMPORTE", "STOCK"
            }
        ));
        tabla.setIntercellSpacing(new java.awt.Dimension(2, 2));
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMinWidth(80);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla.getColumnModel().getColumn(1).setMinWidth(400);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(1).setMaxWidth(400);
            tabla.getColumnModel().getColumn(2).setMinWidth(90);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(2).setMaxWidth(90);
            tabla.getColumnModel().getColumn(3).setMinWidth(70);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(3).setMaxWidth(70);
            tabla.getColumnModel().getColumn(4).setMinWidth(70);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(4).setMaxWidth(70);
            tabla.getColumnModel().getColumn(5).setMinWidth(90);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(5).setMaxWidth(90);
            tabla.getColumnModel().getColumn(6).setMinWidth(70);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(6).setMaxWidth(70);
        }

        panelRect1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 870, 260));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(153, 0, 0));
        panelRect1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 220, 160, 40));

        txtIva.setEditable(false);
        txtIva.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtIva.setForeground(new java.awt.Color(0, 0, 102));
        panelRect1.add(txtIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 170, 160, 30));

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtSubTotal.setForeground(new java.awt.Color(0, 0, 102));
        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });
        panelRect1.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 160, 30));

        panelTranslucido1.setColorPrimario(new java.awt.Color(51, 0, 153));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ID DEL PRODUCTO");

        txtClave.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(0, 0, 102));
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveKeyTyped(evt);
            }
        });

        txtDescuentoProducto.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDescuentoProducto.setForeground(new java.awt.Color(0, 0, 102));
        txtDescuentoProducto.setToolTipText("Selecciona producto y agrega el porcentaje de descuento");
        txtDescuentoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoProductoKeyTyped(evt);
            }
        });

        btnOkDescuentoProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnOkDescuentoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/OK.png"))); // NOI18N
        btnOkDescuentoProducto.setMnemonic('C');
        btnOkDescuentoProducto.setText("OK");
        btnOkDescuentoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkDescuentoProductoActionPerformed(evt);
            }
        });

        txtDescuentoVenta.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDescuentoVenta.setForeground(new java.awt.Color(0, 0, 102));
        txtDescuentoVenta.setToolTipText("Agrega el porcentaje de descuento directamente ala venta");
        txtDescuentoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoVentaKeyTyped(evt);
            }
        });

        btnOkDescuentoVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnOkDescuentoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/OK.png"))); // NOI18N
        btnOkDescuentoVenta.setMnemonic('C');
        btnOkDescuentoVenta.setText("OK");
        btnOkDescuentoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkDescuentoVentaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("% DESCUENTO PRODUCTO");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("% DESCUENTO VENTA ");

        btnAgregarVentaLista.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAgregarVentaLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/carrito.png"))); // NOI18N
        btnAgregarVentaLista.setMnemonic('C');
        btnAgregarVentaLista.setText("AGREGAR");
        btnAgregarVentaLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarVentaLista.setIconTextGap(10);
        btnAgregarVentaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVentaListaActionPerformed(evt);
            }
        });

        btnEliminarFila.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarFila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/SALIR.png"))); // NOI18N
        btnEliminarFila.setText("ELIMINAR");
        btnEliminarFila.setIconTextGap(10);
        btnEliminarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFilaActionPerformed(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(0, 0, 102));
        txtCantidad.setToolTipText("Selecciona producto y agrega la cantidad de productos");
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        btnOkCantidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnOkCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/OK.png"))); // NOI18N
        btnOkCantidad.setMnemonic('C');
        btnOkCantidad.setText("OK");
        btnOkCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkCantidadActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CANTIDAD");

        btnMasCantidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnMasCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/maz.png"))); // NOI18N
        btnMasCantidad.setMnemonic('C');
        btnMasCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasCantidadActionPerformed(evt);
            }
        });

        btnMenosCantidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnMenosCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/menoz.png"))); // NOI18N
        btnMenosCantidad.setMnemonic('C');
        btnMenosCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosCantidadActionPerformed(evt);
            }
        });

        tglRegalo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/regalo.png"))); // NOI18N
        tglRegalo.setMnemonic('R');
        tglRegalo.setToolTipText("");
        tglRegalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglRegaloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTranslucido1Layout = new javax.swing.GroupLayout(panelTranslucido1);
        panelTranslucido1.setLayout(panelTranslucido1Layout);
        panelTranslucido1Layout.setHorizontalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel15))
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarVentaLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarFila)))
                .addGap(10, 10, 10)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOkCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMasCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMenosCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucido1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtDescuentoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOkDescuentoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tglRegalo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                        .addComponent(txtDescuentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOkDescuentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(93, 93, 93))
        );
        panelTranslucido1Layout.setVerticalGroup(
            panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglRegalo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescuentoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOkDescuentoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescuentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOkDescuentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregarVentaLista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarFila)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOkCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMasCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenosCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelRect1.add(panelTranslucido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1150, 100));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("OBSERVACIONES:");
        panelRect1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 130, 30));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("TOTAL:");
        panelRect1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("SUBTOTAL:");
        panelRect1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 80, -1));

        txtPaga.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPaga.setForeground(new java.awt.Color(0, 0, 102));
        txtPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagaActionPerformed(evt);
            }
        });
        txtPaga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagaKeyReleased(evt);
            }
        });
        panelRect1.add(txtPaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 290, 160, 30));

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtCambio.setForeground(new java.awt.Color(0, 0, 102));
        panelRect1.add(txtCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 330, 160, 30));
        panelRect1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1150, 10));
        panelRect1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, 310, 10));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("IVA:");
        panelRect1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 170, 40, 30));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("PAGA CON:");
        panelRect1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 290, 80, 30));

        btnCobrar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/money2.png"))); // NOI18N
        btnCobrar.setText("COBRAR");
        btnCobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCobrar.setIconTextGap(20);
        btnCobrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/money3.png"))); // NOI18N
        btnCobrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/money1.png"))); // NOI18N
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });
        panelRect1.add(btnCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 280, 80));
        panelRect1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, 310, 10));

        chkIva.setToolTipText("Agregar IVA ");
        chkIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIvaActionPerformed(evt);
            }
        });
        panelRect1.add(chkIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, 20, 30));

        areaObservaciones.setColumns(20);
        areaObservaciones.setRows(5);
        areaObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                areaObservacionesKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(areaObservaciones);

        panelRect1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 406, 340, 70));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("CAMBIO:");
        panelRect1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, 70, 30));

        lblDescuentoVenta.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        lblDescuentoVenta.setForeground(new java.awt.Color(204, 0, 0));
        panelRect1.add(lblDescuentoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 160, 20));

        NuevaVenta.getContentPane().add(panelRect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1170, 490));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/printer.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        NuevaVenta.getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 90, 60));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/fondos grandes/fondoadminmenu1.png"))); // NOI18N
        NuevaVenta.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 1350, 650));

        ConfiguraImpresora.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxSelecImpre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbxSelecImpre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ConfiguraImpresora.getContentPane().add(cbxSelecImpre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 370, 40));

        btnGuardarImpre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarImpre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/printnegro.png"))); // NOI18N
        btnGuardarImpre.setText("GUARDAR");
        btnGuardarImpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarImpreActionPerformed(evt);
            }
        });
        ConfiguraImpresora.getContentPane().add(btnGuardarImpre, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 120, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel11.setText("SELECCIONA IMPRESORA POR DEFAULT PARA ESTE MODULO");
        ConfiguraImpresora.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/650 x 600.png"))); // NOI18N
        ConfiguraImpresora.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 160));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/detalles.png"))); // NOI18N
        jMenu1.setText("Detalles de Venta");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        ClickDerecho.add(jMenu1);

        Detalles.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblDetalles);

        Detalles.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 920, 190));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("DETALLES DE LA VENTA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel5)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        Detalles.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 300, 20));

        jButton6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/SALIR.png"))); // NOI18N
        jButton6.setMnemonic('C');
        jButton6.setText("CERRAR");
        jButton6.setToolTipText("");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setIconTextGap(15);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Detalles.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 160, 60));

        jButton9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/ticket.png"))); // NOI18N
        jButton9.setText("IMP. TICKET");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setIconTextGap(15);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        Detalles.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 170, 60));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("CLIENTE:");

        lblClienteNombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblClienteNombre.setText("CLIENTE:");

        lblVendedorDetalle.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblVendedorDetalle.setText("VENDEDOR");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText("VENDEDOR:");

        lblFechaCompraCleinte.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFechaCompraCleinte.setText("FECHA");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("FECHA:");

        lblNumeroVentaDeta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNumeroVentaDeta.setForeground(new java.awt.Color(153, 0, 0));
        lblNumeroVentaDeta.setText("VENTA");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("No. DE VENTA:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(lblNumeroVentaDeta)
                .addGap(35, 35, 35)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClienteNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaCompraCleinte)
                .addGap(30, 30, 30)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVendedorDetalle)
                .addGap(162, 162, 162))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel24)
                    .addContainerGap(820, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblFechaCompraCleinte)
                    .addComponent(jLabel28)
                    .addComponent(jLabel26)
                    .addComponent(lblClienteNombre)
                    .addComponent(lblVendedorDetalle)
                    .addComponent(lblNumeroVentaDeta))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel24)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );

        Detalles.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 920, 30));

        lblClienteNombre1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblClienteNombre1.setText("SUBTOTAL:");

        lblClienteNombre2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblClienteNombre2.setText("IVA:");

        lblsubtotalventa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblsubtotalventa.setText("SUBTOTAL");

        lblIVAdetalle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIVAdetalle.setText("IVA");

        lblDescaVent.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDescaVent.setText("DESC");

        lblClienteNombre5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblClienteNombre5.setText("TOTAL:");

        lblDetaTotalfinal.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblDetaTotalfinal.setForeground(new java.awt.Color(153, 0, 0));
        lblDetaTotalfinal.setText("TOTAL");

        lblClienteNombre7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblClienteNombre7.setText("DESC. A VENTA:");

        lblDetaTotal1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDetaTotal1.setText("DINERO RECIBIDO:");

        lblDetaTotal2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDetaTotal2.setText("CAMBIO:");

        lblcambiodeta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblcambiodeta.setText("CAM");

        lblDineroRecibi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDineroRecibi.setText("DR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(lblClienteNombre5))
                            .addComponent(lblClienteNombre7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblClienteNombre2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblClienteNombre1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescaVent)
                            .addComponent(lblDetaTotalfinal)
                            .addComponent(lblIVAdetalle)
                            .addComponent(lblsubtotalventa)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDetaTotal2)
                            .addComponent(lblDetaTotal1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDineroRecibi)
                            .addComponent(lblcambiodeta))))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblsubtotalventa)
                    .addComponent(lblClienteNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteNombre2)
                    .addComponent(lblIVAdetalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteNombre7)
                    .addComponent(lblDescaVent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteNombre5)
                    .addComponent(lblDetaTotalfinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDetaTotal1)
                    .addComponent(lblDineroRecibi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDetaTotal2)
                    .addComponent(lblcambiodeta))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Detalles.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 250, 230));

        jButton12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/printnegro.png"))); // NOI18N
        jButton12.setText("IMPRIMIR");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.setIconTextGap(15);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        Detalles.getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 170, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/fondos grandes/fondoadminmenu.png"))); // NOI18N
        Detalles.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 390));

        ImprimirNormal.setType(java.awt.Window.Type.UTILITY);
        ImprimirNormal.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbx.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbx.setForeground(new java.awt.Color(0, 0, 153));
        cbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx.setToolTipText("");
        cbx.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel25.setText("SELECCIONA IMPRESORA");

        btnCambiarImpre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCambiarImpre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/printnegro.png"))); // NOI18N
        btnCambiarImpre.setMnemonic('D');
        btnCambiarImpre.setText("GUARDAR IMPRESORA");
        btnCambiarImpre.setToolTipText("Selecciona impresora y oprime guardar para hacerla predeterminada");
        btnCambiarImpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarImpreActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/PDF.png"))); // NOI18N
        jButton14.setMnemonic('G');
        jButton14.setText("GUARDAR COMO");
        jButton14.setToolTipText("Exportar archivo a un directorio.");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        jButton15.setMnemonic('I');
        jButton15.setText("IMPRIMIR");
        jButton15.setToolTipText("Imprimir en la impresora guardada");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/atras.png"))); // NOI18N
        jButton16.setMnemonic('S');
        jButton16.setText("SALIR");
        jButton16.setToolTipText("Ir a inventario de productos");
        jButton16.setIconTextGap(10);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCambiarImpre))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jButton14))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel25)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCambiarImpre, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        ImprimirNormal.getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 490, 320));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("CODIGO DE BARRAS PRODUCTOS");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel27)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        ImprimirNormal.getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 490, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/FONDOAGREMPLEADO3.png"))); // NOI18N
        jLabel10.setMaximumSize(new java.awt.Dimension(550, 430));
        jLabel10.setMinimumSize(new java.awt.Dimension(550, 430));
        jLabel10.setPreferredSize(new java.awt.Dimension(550, 430));
        ImprimirNormal.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ImprimirReporte.setUndecorated(true);
        ImprimirReporte.setType(java.awt.Window.Type.UTILITY);
        ImprimirReporte.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxReporte.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbxReporte.setForeground(new java.awt.Color(0, 0, 153));
        cbxReporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxReporte.setToolTipText("");
        cbxReporte.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbxReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxReporteActionPerformed(evt);
            }
        });
        jPanel19.add(cbxReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 252, 44));

        jLabel29.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel29.setText("SELECCIONA IMPRESORA");
        jPanel19.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 18, -1, 13));

        btnCambiarImpre1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCambiarImpre1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/printnegro.png"))); // NOI18N
        btnCambiarImpre1.setMnemonic('D');
        btnCambiarImpre1.setText("GUARDAR IMPRESORA");
        btnCambiarImpre1.setToolTipText("Selecciona impresora y oprime guardar para hacerla predeterminada");
        btnCambiarImpre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarImpre1ActionPerformed(evt);
            }
        });
        jPanel19.add(btnCambiarImpre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 42, -1, 44));

        btnXtodo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXtodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXtodo.setMnemonic('I');
        btnXtodo.setText("IMPRIMIR");
        btnXtodo.setToolTipText("Imprimir en la impresora guardada");
        btnXtodo.setEnabled(false);
        btnXtodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXtodoActionPerformed(evt);
            }
        });
        jPanel19.add(btnXtodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        jButton19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/atras.png"))); // NOI18N
        jButton19.setMnemonic('S');
        jButton19.setText("SALIR");
        jButton19.setToolTipText("Ir a inventario de productos");
        jButton19.setIconTextGap(10);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 149, 50));

        btnXFechaEntre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXFechaEntre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXFechaEntre.setMnemonic('I');
        btnXFechaEntre.setText("IMPRIMIR");
        btnXFechaEntre.setToolTipText("Imprimir en la impresora guardada");
        btnXFechaEntre.setEnabled(false);
        btnXFechaEntre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXFechaEntreActionPerformed(evt);
            }
        });
        jPanel19.add(btnXFechaEntre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        btnXfechaEspecifica.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXfechaEspecifica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXfechaEspecifica.setMnemonic('I');
        btnXfechaEspecifica.setText("IMPRIMIR");
        btnXfechaEspecifica.setToolTipText("Imprimir en la impresora guardada");
        btnXfechaEspecifica.setEnabled(false);
        btnXfechaEspecifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXfechaEspecificaActionPerformed(evt);
            }
        });
        jPanel19.add(btnXfechaEspecifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        btnXEmpleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXEmpleado.setMnemonic('I');
        btnXEmpleado.setText("IMPRIMIR");
        btnXEmpleado.setToolTipText("Imprimir en la impresora guardada");
        btnXEmpleado.setEnabled(false);
        btnXEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXEmpleadoActionPerformed(evt);
            }
        });
        jPanel19.add(btnXEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        btnXID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXID.setMnemonic('I');
        btnXID.setText("IMPRIMIR");
        btnXID.setToolTipText("Imprimir en la impresora guardada");
        btnXID.setEnabled(false);
        btnXID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXIDActionPerformed(evt);
            }
        });
        jPanel19.add(btnXID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        btnXCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXCliente.setMnemonic('I');
        btnXCliente.setText("IMPRIMIR");
        btnXCliente.setToolTipText("Imprimir en la impresora guardada");
        btnXCliente.setEnabled(false);
        btnXCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXClienteActionPerformed(evt);
            }
        });
        jPanel19.add(btnXCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        btnXHoy.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXHoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXHoy.setMnemonic('I');
        btnXHoy.setText("IMPRIMIR");
        btnXHoy.setToolTipText("Imprimir en la impresora guardada");
        btnXHoy.setEnabled(false);
        btnXHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXHoyActionPerformed(evt);
            }
        });
        jPanel19.add(btnXHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        btnXAyer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnXAyer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/print.png"))); // NOI18N
        btnXAyer.setMnemonic('I');
        btnXAyer.setText("IMPRIMIR");
        btnXAyer.setToolTipText("Imprimir en la impresora guardada");
        btnXAyer.setEnabled(false);
        btnXAyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXAyerActionPerformed(evt);
            }
        });
        jPanel19.add(btnXAyer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 151, 44));

        GuardarReporte.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        GuardarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/PDF.png"))); // NOI18N
        GuardarReporte.setMnemonic('G');
        GuardarReporte.setText("GUARDAR COMO");
        GuardarReporte.setToolTipText("Exportar archivo a un directorio.");
        GuardarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarReporteActionPerformed(evt);
            }
        });
        jPanel19.add(GuardarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, 44));

        ImprimirReporte.getContentPane().add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 490, 320));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("IMPRIMIR REPORTES DE VENTA");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(147, 147, 147))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        ImprimirReporte.getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 490, 20));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/FONDOAGREMPLEADO3.png"))); // NOI18N
        jLabel31.setMaximumSize(new java.awt.Dimension(550, 430));
        jLabel31.setMinimumSize(new java.awt.Dimension(550, 430));
        jLabel31.setPreferredSize(new java.awt.Dimension(550, 430));
        ImprimirReporte.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID VENTA", "FECHA", "TOTAL", "CLIENTE", "VENDEDOR"
            }
        ));
        tblVenta.setComponentPopupMenu(ClickDerecho);
        jScrollPane1.setViewportView(tblVenta);
        if (tblVenta.getColumnModel().getColumnCount() > 0) {
            tblVenta.getColumnModel().getColumn(0).setMinWidth(100);
            tblVenta.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblVenta.getColumnModel().getColumn(0).setMaxWidth(100);
            tblVenta.getColumnModel().getColumn(1).setMinWidth(120);
            tblVenta.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblVenta.getColumnModel().getColumn(1).setMaxWidth(120);
            tblVenta.getColumnModel().getColumn(2).setMinWidth(120);
            tblVenta.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblVenta.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 1290, 320));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/SALIR.png"))); // NOI18N
        jButton1.setMnemonic('E');
        jButton1.setText("ELIMINAR");
        jButton1.setToolTipText("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setIconTextGap(20);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 160, 70));

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/AUDITAR.png"))); // NOI18N
        jButton2.setMnemonic('T');
        jButton2.setText("MOSTRAR TODO");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setIconTextGap(10);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 170, 70));

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/ALTA.png"))); // NOI18N
        jButton3.setMnemonic('N');
        jButton3.setText("NUEVA VENTA");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setIconTextGap(20);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 170, 70));

        btnCrearReporte.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCrearReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/REPORTE.png"))); // NOI18N
        btnCrearReporte.setMnemonic('R');
        btnCrearReporte.setText("CREAR REPORTE");
        btnCrearReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearReporte.setEnabled(false);
        btnCrearReporte.setIconTextGap(15);
        btnCrearReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearReporteActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 180, 70));

        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/ATRAS.png"))); // NOI18N
        jButton5.setMnemonic('M');
        jButton5.setText("MENU");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setIconTextGap(20);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 150, 70));

        txtBuscarId.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtBuscarId.setForeground(new java.awt.Color(0, 0, 153));
        txtBuscarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarIdActionPerformed(evt);
            }
        });
        txtBuscarId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarIdKeyTyped(evt);
            }
        });
        getContentPane().add(txtBuscarId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 210, 50));

        btnBuscarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/CONSULTA.png"))); // NOI18N
        btnBuscarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, 50));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/limpiar.png"))); // NOI18N
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, 50));

        cbxSeleccionarBusqueda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbxSeleccionarBusqueda.setForeground(new java.awt.Color(0, 0, 153));
        cbxSeleccionarBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONA:", "POR FECHA ESPECÍFICA", "POR FECHA AVANZADA", "DESDE - HASTA", "POR EMPLEADO", "POR CLIENTE" }));
        cbxSeleccionarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeleccionarBusquedaActionPerformed(evt);
            }
        });

        datefechaUno.setToolTipText("Escribe un fecha formato dd/MM/yyyy");
        datefechaUno.setEnabled(false);

        dateFechaDos.setEnabled(false);

        desde.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        desde.setText("DESDE:");

        hasta.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        hasta.setText("HASTA:");

        txtBuscarLikeFecha.setEnabled(false);
        txtBuscarLikeFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarLikeFechaActionPerformed(evt);
            }
        });
        txtBuscarLikeFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarLikeFechaKeyReleased(evt);
            }
        });

        lblInfolike.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        lblInfolike.setText("COLOCAR FECHA FORMATO: yyyy-MM-dd HH:mm");

        btnBuscaAvanzado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/lupa.png"))); // NOI18N
        btnBuscaAvanzado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscaAvanzado.setEnabled(false);
        btnBuscaAvanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaAvanzadoActionPerformed(evt);
            }
        });

        cbxBuscarPorCliente.setEditable(true);
        cbxBuscarPorCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbxBuscarPorCliente.setForeground(new java.awt.Color(0, 0, 153));
        cbxBuscarPorCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONA:", "POR FECHA ESPECÍFICA", "POR FECHA AVANZADA", "DESDE - HASTA", "POR EMPLEADO", "POR CLIENTE", " " }));
        cbxBuscarPorCliente.setEnabled(false);
        cbxBuscarPorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBuscarPorClienteActionPerformed(evt);
            }
        });

        lblInfolike1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        lblInfolike1.setText("BUSQUEDA AVANZADA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSeleccionarBusqueda, 0, 223, Short.MAX_VALUE)
                                    .addComponent(txtBuscarLikeFecha)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblInfolike)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInfolike1)
                        .addGap(75, 75, 75)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(datefechaUno, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dateFechaDos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(desde)
                                .addGap(139, 139, 139)
                                .addComponent(hasta)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscaAvanzado, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxBuscarPorCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(desde)
                            .addComponent(hasta)
                            .addComponent(lblInfolike1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(datefechaUno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSeleccionarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(dateFechaDos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInfolike))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscaAvanzado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxBuscarPorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarLikeFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 680, 130));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("BUSCAR POR NÚMERO DE VENTA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));
        getContentPane().add(clockFace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 140, 150, 150));
        getContentPane().add(panelCurves1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 1380, 90));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("VENTAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, -1));

        btnHoyMenuVenta.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnHoyMenuVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/Hoy.png"))); // NOI18N
        btnHoyMenuVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoyMenuVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnHoyMenuVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 70, 180, 70));

        jButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventas/Imagenes/botones/Ayer.png"))); // NOI18N
        jButton11.setMnemonic('Y');
        jButton11.setText("AYER");
        jButton11.setToolTipText("Ventas de ayer");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setIconTextGap(10);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 140, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/fondos grandes/fondoadminmenu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Conexionbd cc = new Conexionbd();
        java.sql.Connection cn = cc.conexion();
        String sql = "SELECT MAX(VENT_ID) AS id FROM ventas";
        String sqlEmpleado = "SELECT e.EMPL_ID, concat_ws(' ', E.empl_nomb, E.empl_apat, E.empl_amat) FROM empleados as e INNER JOIN usuarios as u ON e.EMPL_ID=u.USUA_EMPL_ID WHERE u.USUA_NOMB='" + Guardausuario + "'";
        int valorId, idNuevo;
        String recibir;
        String formato = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechafin = sdf.format(date);
        System.out.println(fechafin);
        lblFecha.setText(fechafin);
        String nombreEmpleado;

        try {
            String[] datosId = new String[1];

            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datosId[0] = rs.getString(1);
            }
            recibir = datosId[0];

            System.out.println(recibir + " valor obtenido bd");
            if (recibir.equals(null)) {
                idNuevo = 1;
                lblNumeroVenta.setText(Integer.toString(idNuevo));
            } else {
                valorId = Integer.parseInt(recibir);
                idNuevo = valorId + 1;
                // Formatter fmt = new Formatter();
                // fmt.format("%08d",idNuevo);
                //System.out.println(fmt+"numero formateado");
                lblNumeroVenta.setText(Integer.toString(idNuevo));
            }
        } catch (Exception ex) {
            System.out.println(ex);
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡No ha sido posible obtener el ultimo Id de venta"
                    + nl + "Si es su primera venta ignore este mensaje" + nl + "sino consulte su manual de usuario o o directamente con su administrador");
            lblNumeroVenta.setText(Integer.toString(1));
        }
        try {
            String[] datosEmpleado = new String[2];

            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlEmpleado);
            while (rs.next()) {
                datosEmpleado[0] = rs.getString(1);
                datosEmpleado[1] = rs.getString(2);

            }
            idEmpleado = Integer.parseInt(datosEmpleado[0]);
            System.out.println(idEmpleado + " id de empleado");
            nombreEmpleado = datosEmpleado[1];
            System.out.println(nombreEmpleado + "nombre del empleado");
            lblVendedor.setText(nombreEmpleado);

        } catch (Exception ex) {
            System.out.println(ex);
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡Error! no ha sido posible obtener el Id y nombre del empleado o vendedor"
                    + nl + "verifica que tu servidor BD funciona correctamente");
        }
        System.out.println(Guardausuario + "uzuario");
        ImprimirCbx();
        NuevaVenta.setModal(true);
        NuevaVenta.setSize(1320, 690);
        NuevaVenta.setLocationRelativeTo(null);
        NuevaVenta.setVisible(true);
        System.out.println(query + "query en mostrar datos");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnBuscarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVentaActionPerformed
        try {
            MostrarVentas(txtBuscarId.getText());
            String compara = txtBuscarId.getText().toString();
            if (compara.length() == 0) {
                System.out.println(query + "query en buscar id nulo");
                btnCrearReporte.setEnabled(false);
            } else {
                query = txtBuscarId.getText().toString();

                System.out.println(query + "query en buscar id");
                btnXID.setVisible(true);
                btnXID.setEnabled(true);
                btnXfechaEspecifica.setVisible(false);
                btnXAyer.setVisible(false);
                btnXHoy.setVisible(false);
                GuardarReporte.setVisible(false);
                btnXEmpleado.setVisible(false);
                btnXCliente.setVisible(false);
                btnXtodo.setVisible(false);
                btnXFechaEntre.setVisible(false);
                btnCrearReporte.setEnabled(true);
                cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");
            }

        } catch (Exception ex) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡No se pudo cargar la lista de ventas!" + nl + "Asegurate que tu servidor de base de datos esta encendido");
        }

        cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");

    }//GEN-LAST:event_btnBuscarVentaActionPerformed

    private void txtBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarIdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            MostrarVentas("");
        } catch (Exception ex) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡No se pudo cargar la lista de ventas!" + nl + "Asegurate que tu servidor de base de datos esta encendido");
        }
        txtBuscarId.setText("");
        btnXtodo.setVisible(true);
        btnXtodo.setEnabled(true);

        btnXfechaEspecifica.setVisible(false);
        btnXAyer.setVisible(false);
        btnXHoy.setVisible(false);
        btnXEmpleado.setVisible(false);
        btnXCliente.setVisible(false);
        btnXID.setVisible(false);
        btnXFechaEntre.setVisible(false);
        btnCrearReporte.setEnabled(true);
        GuardarReporte.setVisible(true);
        cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        txtBuscarId.setText("");
        txtBuscarLikeFecha.setText("");
        if (txtBuscarLikeFecha.isEnabled() == true) {
            txtBuscarLikeFecha.requestFocus();
        } else {
            txtBuscarId.requestFocus();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtBuscarLikeFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarLikeFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarLikeFechaActionPerformed

    private void btnBuscaAvanzadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaAvanzadoActionPerformed
        gc();
        String comparar;

        comparar = cbxSeleccionarBusqueda.getSelectedItem().toString();

        if (comparar == "POR EMPLEADO") {

            try {

                String empleado;

                if (cbxBuscarPorCliente != null) {

                    empleado = cbxBuscarPorCliente.getSelectedItem().toString();

                    System.out.println(empleado);
                    MostrarVentas("concat_ws(' ', E.empl_nomb, E.empl_apat, E.empl_amat)", "=", empleado, "");

                }
            } catch (Exception e) {

            }
            btnXEmpleado.setVisible(true);
            btnXEmpleado.setEnabled(true);
            btnXfechaEspecifica.setVisible(false);
            btnXAyer.setVisible(false);
            btnXHoy.setVisible(false);
            btnXtodo.setVisible(false);
            btnXCliente.setVisible(false);
            btnXID.setVisible(false);
            btnXFechaEntre.setVisible(false);
            cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");

        } else if (comparar == "POR CLIENTE") {

            try {
                String cliente;
                if (cbxBuscarPorCliente != null) {
                    cliente = cbxBuscarPorCliente.getSelectedItem().toString();

                    MostrarVentas("concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO)", "=", cliente, "");
                }
            } catch (Exception e) {
            }
            btnXCliente.setVisible(true);
            btnXCliente.setEnabled(true);
            btnXfechaEspecifica.setVisible(false);
            btnXAyer.setVisible(false);
            btnXtodo.setVisible(false);
            btnXHoy.setVisible(false);
            btnXEmpleado.setVisible(false);
            btnXID.setVisible(false);
            btnXFechaEntre.setVisible(false);

        } else if (comparar == "POR FECHA ESPECÍFICA") {

            try {
                if (datefechaUno != null) {
                    String formato = "yyyy-MM-dd";

                    java.util.Date date = datefechaUno.getDate();

                    SimpleDateFormat sdf = new SimpleDateFormat(formato);

                    String fechafin = sdf.format(date);

                    MostrarVentas("vent_fech", "=", fechafin, "");
                    System.out.println(fechafin);
                }
                btnXfechaEspecifica.setVisible(true);
                btnXfechaEspecifica.setEnabled(true);
                btnXAyer.setVisible(false);
                btnXHoy.setVisible(false);
                btnXEmpleado.setVisible(false);
                btnXtodo.setVisible(false);
                btnXCliente.setVisible(false);
                btnXID.setVisible(false);
                btnXFechaEntre.setVisible(false);
            } catch (Exception e) {
            }
        } else if (comparar == "DESDE - HASTA") {

            try {
                String fecha;
                if (datefechaUno != null) {
                    String formato = "yyyy-MM-dd";

                    java.util.Date date = datefechaUno.getDate();

                    SimpleDateFormat sdf = new SimpleDateFormat(formato);

                    String fechafin = sdf.format(date);
                    java.util.Date date2 = dateFechaDos.getDate();

                    SimpleDateFormat sdf2 = new SimpleDateFormat(formato);

                    String fechafin2 = sdf2.format(date2);

                    MostrarVentasFinal("vent_fech between '" + fechafin + "' and '" + fechafin2 + "'");
                    System.out.println(fechafin);
                    System.out.println(fechafin2);
                }
                btnXFechaEntre.setVisible(true);
                btnXFechaEntre.setEnabled(true);
                btnXfechaEspecifica.setVisible(false);
                btnXAyer.setVisible(false);
                btnXHoy.setVisible(false);
                btnXEmpleado.setVisible(false);
                btnXtodo.setVisible(false);
                btnXCliente.setVisible(false);
                btnXID.setVisible(false);
            } catch (Exception e) {
            }
        }
        GuardarReporte.setVisible(false);
        btnCrearReporte.setEnabled(true);
    }//GEN-LAST:event_btnBuscaAvanzadoActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        try {
            // imprimir();
            //imprimirFactura();
            GuardarVenta();
        } catch (SQLException ex) {
            System.out.println("No se pudo completar la venta");
        }

    }//GEN-LAST:event_btnCobrarActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased

    }//GEN-LAST:event_tablaKeyReleased

    private void btnMenosCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosCantidadActionPerformed
        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        double descuento;
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                String valor = tabla.getValueAt(fila, 4).toString();
                String valorDescuento = tabla.getValueAt(fila, 3).toString();
                descuento = Double.parseDouble(valorDescuento);
                String valorprecio = tabla.getValueAt(fila, 2).toString();
                System.out.println(valor + "---------------------cantidad");
                System.out.println(valorprecio + "---------------------precio");
                int valoruno = Integer.parseInt(valor);
                if (valoruno < 2) {
                    JOptionPane.showMessageDialog(null, "¡Error no puede existir venta en ceros!");
                } else {
                    double valordoz = Double.parseDouble(valorprecio);
                    System.out.println(valoruno + "---------------------cantidaddoz");
                    System.out.println(valordoz + "---------------------preciodoz");
                    tabla.setValueAt(valoruno - 1, fila, 4);
                    valor = tabla.getValueAt(fila, 4).toString();
                    valoruno = Integer.parseInt(valor);
                    tabla.setValueAt(valordoz * valoruno, fila, 5);
                    DescuentoProducto(descuento);
                    double sumatoria1 = 0.0;
                    int totalRow = tabla.getRowCount();
                    totalRow -= 1;
                    for (int i = 0; i <= (totalRow); i++) {
                        double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                        //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                        sumatoria1 += sumatoria;

                        System.out.println("" + sumatoria1);
                        txtSubTotal.setText(Double.toString(sumatoria1));
                        double total;
                        total = IVATotal(sumatoria1);
                        txtTotal.setText(Double.toString(total));
                        lblDescuentoVenta.setText("");

                    }

                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
    }//GEN-LAST:event_btnMenosCantidadActionPerformed

    private void btnMasCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasCantidadActionPerformed

        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        double descuento;
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                String valor = tabla.getValueAt(fila, 4).toString();
                String valorDescuento = tabla.getValueAt(fila, 3).toString();
                descuento = Double.parseDouble(valorDescuento);
                String valorprecio = tabla.getValueAt(fila, 2).toString();
                System.out.println(valor + "---------------------cantidad");
                System.out.println(valorprecio + "---------------------precio");
                int valoruno = Integer.parseInt(valor);
                double valordoz = Double.parseDouble(valorprecio);
                System.out.println(valoruno + "---------------------cantidaddoz");
                System.out.println(valordoz + "---------------------preciodoz");
                if (valoruno + 1 > Integer.parseInt(tabla.getValueAt(fila, 6).toString())) {
                    JOptionPane.showMessageDialog(null, "¡No puede rebasar la cantidad de productos que existen en el stock!");
                } else {
                    tabla.setValueAt(valoruno + 1, fila, 4);
                    valor = tabla.getValueAt(fila, 4).toString();
                    valoruno = Integer.parseInt(valor);
                    tabla.setValueAt(valordoz * valoruno, fila, 5);
                    DescuentoProducto(descuento);
                    double sumatoria1 = 0.0;
                    int totalRow = tabla.getRowCount();
                    totalRow -= 1;
                    for (int i = 0; i <= (totalRow); i++) {
                        double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                        //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                        sumatoria1 += sumatoria;

                        System.out.println("" + sumatoria1);
                        txtSubTotal.setText(Double.toString(sumatoria1));
                        double total;
                        total = IVATotal(sumatoria1);
                        txtTotal.setText(Double.toString(total));
                        lblDescuentoVenta.setText("");
                    }
                }

            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
    }//GEN-LAST:event_btnMasCantidadActionPerformed

    private void btnOkCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkCantidadActionPerformed
        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        double descuento;
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                String valor = txtCantidad.getText();
                String valorDescuento = tabla.getValueAt(fila, 3).toString();
                descuento = Double.parseDouble(valorDescuento);
                String valorprecio = tabla.getValueAt(fila, 2).toString();
                System.out.println(valor + "---------------------cantidad");
                System.out.println(valorprecio + "---------------------precio");
                int valoruno = Integer.parseInt(valor);
                if (valoruno < 1) {
                    JOptionPane.showMessageDialog(null, "¡La cantidad de productos debe ser mayor a 1!");
                } else {
                    double valordoz = Double.parseDouble(valorprecio);
                    System.out.println(valoruno + "---------------------cantidaddoz");
                    System.out.println(valordoz + "---------------------preciodoz");
                    if (valoruno > Integer.parseInt(tabla.getValueAt(fila, 6).toString())) {
                        JOptionPane.showMessageDialog(null, "¡No puede rebasar la cantidad de productos que existen en el stock!");
                    } else {
                        tabla.setValueAt(valoruno, fila, 4);
                        valor = tabla.getValueAt(fila, 4).toString();
                        valoruno = Integer.parseInt(valor);
                        tabla.setValueAt(valordoz * valoruno, fila, 5);
                        DescuentoProducto(descuento);
                        double sumatoria1 = 0.0;
                        int totalRow = tabla.getRowCount();
                        totalRow -= 1;
                        for (int i = 0; i <= (totalRow); i++) {
                            double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                            //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                            sumatoria1 += sumatoria;

                            System.out.println("" + sumatoria1);
                            txtSubTotal.setText(Double.toString(sumatoria1));
                            double total;
                            total = IVATotal(sumatoria1);
                            txtTotal.setText(Double.toString(total));
                            lblDescuentoVenta.setText("");
                        }
                    }

                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
        txtCantidad.setText("");
    }//GEN-LAST:event_btnOkCantidadActionPerformed

    private void btnEliminarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFilaActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                /*   String valor= tabla.getValueAt(fila, 4).toString();
                 String valorprecio= tabla.getValueAt(fila, 2).toString();
                 System.out.println(valor+"---------------------cantidad");
                 System.out.println(valorprecio+"---------------------precio");
                 int valoruno=Integer.parseInt(valor);
                 double valordoz=Double.parseDouble(valorprecio);
                 System.out.println(valoruno+"---------------------cantidaddoz");
                 System.out.println(valordoz+"---------------------preciodoz");
                 tabla.setValueAt(valoruno-1, fila, 4);
                 valor= tabla.getValueAt(fila, 4).toString();
                 valoruno=Integer.parseInt(valor);

                 tabla.setValueAt(valordoz*valoruno, fila, 5);
                 */

                String num2 = tabla.getValueAt(fila, 5).toString();

                double valorDoz = Double.parseDouble(num2);
                double resta = valorDoz - valorDoz;
                String total = Double.toString(resta);
                tabla.setValueAt(resta, fila, 5);

                double sumatoria1 = 0.0;
                int totalRow = tabla.getRowCount();
                totalRow -= 1;    //ELIMINA FILA
                for (int i = 0; i <= (totalRow); i++) {
                    double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                    //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                    sumatoria1 += sumatoria;

                    System.out.println("" + sumatoria1);
                    txtSubTotal.setText(Double.toString(sumatoria1));
                    double totalFin;
                    totalFin = IVATotal(sumatoria1);
                    txtTotal.setText(Double.toString(totalFin));

                }

            }

            modelo.removeRow(tabla.getSelectedRow());
        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
    }//GEN-LAST:event_btnEliminarFilaActionPerformed

    private void btnAgregarVentaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVentaListaActionPerformed
        String clave = txtClave.getText();
        if (clave.length() == 0) {
            JOptionPane.showMessageDialog(null, "¡Por favor Ingresa la clave del producto!");
        } else {
            try {
                agregarFila(txtClave.getText());
            } catch (Throwable ex) {
                java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            double sumatoria1 = 0.0;
            int totalRow = tabla.getRowCount();
            totalRow -= 1;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                sumatoria1 += sumatoria;

                System.out.println("" + sumatoria1);
                txtSubTotal.setText(Double.toString(sumatoria1));
                double total;
                total = IVATotal(sumatoria1);
                txtTotal.setText(Double.toString(total));
                txtDescuentoVenta.setText("0.0");
                txtClave.setText("");
            }
        }
    }//GEN-LAST:event_btnAgregarVentaListaActionPerformed

    private void btnOkDescuentoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkDescuentoVentaActionPerformed
        double precio, total, guardaTotal, num1, num2;
        precio = Double.parseDouble(txtTotal.getText());
        guardaTotal = precio;
        descuentoVentadirecta = Double.parseDouble(txtDescuentoVenta.getText());
        if (precio == 0) {
            JOptionPane.showMessageDialog(null, "¡No es posible agregar descuento a cero!");
            lblDescuentoVenta.setText("");
        } else if (descuentoVentadirecta > 100) {
            JOptionPane.showMessageDialog(null, "¡Error! el descuento no puede rebasar el 100%");
            lblDescuentoVenta.setText("");
        } else if (descuentoVentadirecta == 0) {
            num1 = Double.parseDouble(txtSubTotal.getText());
            num2 = Double.parseDouble(txtIva.getText());
            txtTotal.setText(Double.toString(num1 + num2));
            lblDescuentoVenta.setText("");
        } else {
            total = (precio * descuentoVentadirecta) / 100;
            total = precio - total;
            txtTotal.setText(Double.toString(total));
            lblDescuentoVenta.setText("Descuento " + descuentoVentadirecta + "% Venta general");
            txtDescuentoVenta.setText("0.0");
        }
    }//GEN-LAST:event_btnOkDescuentoVentaActionPerformed

    private void btnOkDescuentoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkDescuentoProductoActionPerformed
        DescuentoProducto();
    }//GEN-LAST:event_btnOkDescuentoProductoActionPerformed

    private void txtClaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyReleased


    }//GEN-LAST:event_txtClaveKeyReleased

    private void txtClaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btnAgregarVentaLista.doClick();
        }
    }//GEN-LAST:event_txtClaveKeyTyped

    private void tglRegaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglRegaloActionPerformed
        if (tglRegalo.isSelected()) {
            double descuento = 100.0;
            DescuentoProducto(descuento);
        } else {
            double descuento = 0.0;
            DescuentoProducto(descuento);
        }
    }//GEN-LAST:event_tglRegaloActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (invi == true) {
            VentanaUsuario obj = new VentanaUsuario();
            obj.setVisible(true);

        } else {
            Ventanaadmin obj2 = new Ventanaadmin();
            obj2.setVisible(true);

        }
        this.dispose();
        System.out.println(invitado + " categoria usuario en inventario");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void chkIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIvaActionPerformed
        double subTotal = Double.parseDouble(txtSubTotal.getText());
        /*
         double total;
         if(chkIva.isSelected()==true){
         total=IVATotal(subTotal); 
         txtTotal.setText(Double.toString(total));
         }
         else
         total=subTotal; 
         txtTotal.setText(Double.toString(total));
         txtIva.setText("0.0");
         */
        double iva;
        double total = 0;
        if (chkIva.isSelected() == true) {
            iva = (subTotal * 16) / 100;
            txtIva.setText(Double.toString(iva));
            total = subTotal + iva;
            txtTotal.setText(Double.toString(total));
            lblDescuentoVenta.setText("");

        } else {
            total = subTotal;
            txtIva.setText("0.0");
            txtTotal.setText(Double.toString(total));
            lblDescuentoVenta.setText("");

        }

    }//GEN-LAST:event_chkIvaActionPerformed

    private void txtPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagaActionPerformed
        double total;
        double paga;
        double cambio = 0;

        total = Double.parseDouble(txtTotal.getText());
        paga = Double.parseDouble(txtPaga.getText());
        if (paga < total) {
            JOptionPane.showMessageDialog(null, "¡La cantidad debe ser mayor al total!");
            txtPaga.requestFocus();
        } else {
            cambio = paga - total;
            txtCambio.setText(Double.toString(cambio));
            btnCobrar.requestFocus();
        }
    }//GEN-LAST:event_txtPagaActionPerformed

    private void txtPagaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagaKeyReleased

    private void areaObservacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaObservacionesKeyReleased
        LetraCapital(evt, areaObservaciones);
    }//GEN-LAST:event_areaObservacionesKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btnOkCantidad.doClick();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtDescuentoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoProductoKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btnOkDescuentoProducto.doClick();
        }
    }//GEN-LAST:event_txtDescuentoProductoKeyTyped

    private void txtDescuentoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoVentaKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btnOkDescuentoVenta.doClick();
        }
    }//GEN-LAST:event_txtDescuentoVentaKeyTyped

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ConfiguraImpresora.setModal(true);
        ConfiguraImpresora.setSize(565, 200);
        ConfiguraImpresora.setLocationRelativeTo(null);
        ConfiguraImpresora.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnGuardarImpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarImpreActionPerformed
        try {
            Conexionbd cc = new Conexionbd();

            Connection cn = (Connection) cc.conexion();
            String elegir;
            elegir = cbxSelecImpre.getSelectedItem().toString();
            System.out.println(elegir);

            PreparedStatement pst = cn.prepareStatement("UPDATE impresoras SET codi_barr ='" + elegir + "' WHERE impr_id='2'");

            int n = pst.executeUpdate();
            ImprimirCbx();
            if (n > 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "Impresora guardada como predeterminada para este módulo ");

            } else {

                JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");

            }
        } catch (Exception e) {
            System.out.println(e);

        }


    }//GEN-LAST:event_btnGuardarImpreActionPerformed

    private void cbxSeleccionarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeleccionarBusquedaActionPerformed
        String emparejar;

        String campotext;

        emparejar = cbxSeleccionarBusqueda.getSelectedItem().toString();
        if (emparejar != "SELECCIONA:") {
            txtBuscarId.setText("");
        }

        if (emparejar == "SELECCIONA:") {

            datefechaUno.setEnabled(false);
            dateFechaDos.setEnabled(false);
            btnBuscaAvanzado.setEnabled(false);
            txtBuscarLikeFecha.setEnabled(false);
            cbxBuscarPorCliente.setSelectedIndex(-1);
            cbxBuscarPorCliente.setEnabled(false);
            txtBuscarLikeFecha.setText("");
            datefechaUno.setDate(null);
            dateFechaDos.setDate(null);
            lblInfolike.setVisible(false);
            desde.setVisible(false);
            hasta.setVisible(false);

        } else if (emparejar == "POR FECHA ESPECÍFICA") {

            datefechaUno.setEnabled(true);
            btnBuscaAvanzado.setEnabled(true);
            dateFechaDos.setEnabled(false);
            txtBuscarLikeFecha.setEnabled(false);
            cbxBuscarPorCliente.setSelectedIndex(-1);
            cbxBuscarPorCliente.setEnabled(false);
            txtBuscarLikeFecha.setText("");
            txtBuscarLikeFecha.setEnabled(false);
            dateFechaDos.setDate(null);
            datefechaUno.setDate(null);
            lblInfolike.setVisible(false);
            desde.setVisible(false);
            hasta.setVisible(false);
        } else if (emparejar == "POR FECHA AVANZADA") {

            datefechaUno.setEnabled(false);
            dateFechaDos.setEnabled(false);
            btnBuscaAvanzado.setEnabled(true);
            cbxBuscarPorCliente.setEnabled(false);
            cbxBuscarPorCliente.setSelectedIndex(-1);
            txtBuscarLikeFecha.setText("");
            txtBuscarLikeFecha.setEnabled(true);
            btnBuscaAvanzado.setEnabled(false);
            dateFechaDos.setDate(null);
            datefechaUno.setDate(null);
            lblInfolike.setVisible(true);
            desde.setVisible(false);
            hasta.setVisible(false);

        } else if (emparejar == "DESDE - HASTA") {

            datefechaUno.setEnabled(true);
            dateFechaDos.setEnabled(true);
            btnBuscaAvanzado.setEnabled(true);
            txtBuscarLikeFecha.setEnabled(false);
            cbxBuscarPorCliente.setEnabled(false);
            cbxBuscarPorCliente.setSelectedIndex(-1);
            txtBuscarLikeFecha.setText("");
            txtBuscarLikeFecha.setEnabled(false);
            dateFechaDos.setDate(null);
            datefechaUno.setDate(null);
            lblInfolike.setVisible(false);
            desde.setVisible(true);
            hasta.setVisible(true);
        } else if (emparejar == "POR EMPLEADO") {
            try {

                Object[] llenar_combo_puesto = con.combox("empleados", "concat_ws(' ', empl_nomb, empl_apat,empl_amat)");

                cbxBuscarPorCliente.removeAllItems();
                for (int i = 0; i < llenar_combo_puesto.length; i++) {

                    cbxBuscarPorCliente.addItem((String) llenar_combo_puesto[i]);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡Error! no se ha podido acceder ala base de datos para rellenar las listas");
            }
            datefechaUno.setEnabled(false);
            dateFechaDos.setEnabled(false);
            btnBuscaAvanzado.setEnabled(true);
            txtBuscarLikeFecha.setEnabled(false);
            cbxBuscarPorCliente.setSelectedIndex(-1);
            cbxBuscarPorCliente.setEnabled(true);
            cbxBuscarPorCliente.setEnabled(true);
            txtBuscarLikeFecha.setText("");
            txtBuscarLikeFecha.setEnabled(false);
            dateFechaDos.setDate(null);
            datefechaUno.setDate(null);
            lblInfolike.setVisible(false);
            desde.setVisible(false);
            hasta.setVisible(false);

        } else if (emparejar == "POR CLIENTE") {
            try {

                Object[] llenar_combo_puesto = con.combox("clientes", "concat_ws(' ', CLIENTE_FIRST_NAME, CLIENTE_PATERNO,CLIENTE_MATERNO)");

                cbxBuscarPorCliente.removeAllItems();
                for (int i = 0; i < llenar_combo_puesto.length; i++) {

                    cbxBuscarPorCliente.addItem((String) llenar_combo_puesto[i]);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡Error! no se ha podido acceder ala base de datos para rellenar las listas");
            }
            datefechaUno.setEnabled(false);
            dateFechaDos.setEnabled(false);
            btnBuscaAvanzado.setEnabled(true);
            txtBuscarLikeFecha.setEnabled(false);
            cbxBuscarPorCliente.setSelectedIndex(-1);
            cbxBuscarPorCliente.setEnabled(true);
            cbxBuscarPorCliente.setEnabled(true);
            txtBuscarLikeFecha.setText("");
            txtBuscarLikeFecha.setEnabled(false);
            dateFechaDos.setDate(null);
            datefechaUno.setDate(null);
            lblInfolike.setVisible(false);
            desde.setVisible(false);
            hasta.setVisible(false);

        }
    }//GEN-LAST:event_cbxSeleccionarBusquedaActionPerformed

    private void cbxBuscarPorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarPorClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarPorClienteActionPerformed

    private void txtBuscarLikeFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarLikeFechaKeyReleased
        String comparar;
        comparar = cbxSeleccionarBusqueda.getSelectedItem().toString();

        if (comparar == "POR FECHA AVANZADA") {

            try {

                String fecha;

                if (this.txtBuscarLikeFecha != null) {

                    fecha = txtBuscarLikeFecha.getText().toString();
                    System.out.println("Entrada->" + fecha);
                    MostrarVentasLike("vent_fech", fecha);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡Error desconocido!");
            }
        }

    }//GEN-LAST:event_txtBuscarLikeFechaKeyReleased

    private void txtBuscarIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIdKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btnBuscarVenta.doClick();
        }
    }//GEN-LAST:event_txtBuscarIdKeyTyped

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        if (evt.getClickCount() == 2) {
            int fila = tblVenta.getSelectedRow();
            int columna = tblVenta.getSelectedRow();

            if (fila >= 0) {
                if (tblVenta.getValueAt(fila, columna) == null) {
                    JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
                } else if (fila >= 0 && columna >= 0) {
                    String id;
                    id = tblVenta.getValueAt(fila, 0).toString();

                    try {
                        MostrarDetalles("vent_id='" + id + "'");
                    } catch (Exception ex) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, " ¡No se pudo cargar la lista de ventas!" + nl + "Asegurate que tu servidor de base de datos esta encendido");
                    }
                    Detalles.setModal(true);
                    Detalles.setSize(1235, 400);
                    Detalles.setLocationRelativeTo(null);
                    Detalles.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Detalles.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {

            String clave = "";
            clave = lblNumeroVentaDeta.getText();
            System.out.println(clave + "++++++++++++ezta ez la clave para imprimir---------------");
            ImprimirTicket(clave);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "¡Ocurrio un error al exportar el archivo imprimible!");
            System.out.println(e);
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("ticketOpticx.pdf");
        } catch (FileNotFoundException ex) {
        }

        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        //Nombre de la impresora
        String printerName = comparaImpresora;
        System.out.println("+++++++++++++++++" + comparaImpresora);
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

        System.out.println("Imprimiendo en : " + services[numImpre].getName());

        DocPrintJob printJob = services[numImpre].createPrintJob();
        try {
            //Envio a la impresora
            printJob.print(document, new HashPrintRequestAttributeSet());
            JOptionPane.showMessageDialog(null, "¡Listo!");
        } catch (PrintException ex) {

            JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        txtBuscarId.setText("");
        String formato = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        Date hoy = new Date();
        Date ayer = new Date(hoy.getTime() - 86400000);
        cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");
        String ayerDia = sdf.format(ayer);
        System.out.println("Ayer fue: " + ayerDia);

        try {
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateAyer = null;
            try {
                dateAyer = inputFormatter.parse(ayerDia);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String salidaAyer = outputFormatter.format(dateAyer);

            MostrarVentasFinal("vent_fech = '" + salidaAyer + "' order By concat_ws(' ', v.VENT_FECH, v.VENT_HORA) DESC");
            System.out.println("Ayer fue YA EN BOTON: " + salidaAyer);

            btnXAyer.setVisible(true);
            btnXAyer.setEnabled(true);
            btnXfechaEspecifica.setVisible(false);
            btnXHoy.setVisible(false);
            btnXEmpleado.setVisible(false);
            btnXCliente.setVisible(false);
            btnXID.setVisible(false);
            GuardarReporte.setVisible(false);
            btnXFechaEntre.setVisible(false);
            btnCrearReporte.setEnabled(true);
            cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");
            query = salidaAyer;
        } catch (Exception ex) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡No se pudo cargar la lista de ventas!" + nl + "Asegurate que tu servidor de base de datos esta encendido");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnHoyMenuVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoyMenuVentaActionPerformed
        txtBuscarId.setText("");
        String formato = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechafin = sdf.format(date);
        btnHoyMenuVenta.setText(fechafin);
        query = fechafin;
        btnXHoy.setVisible(true);
        btnXHoy.setEnabled(true);
        btnXtodo.setVisible(false);
        btnXfechaEspecifica.setVisible(false);
        btnXAyer.setVisible(false);
        btnXEmpleado.setVisible(false);
        btnXtodo.setVisible(false);
        btnXCliente.setVisible(false);
        btnXID.setVisible(false);
        btnXFechaEntre.setVisible(false);
        GuardarReporte.setVisible(false);
        btnCrearReporte.setEnabled(true);
        cbxSeleccionarBusqueda.setSelectedItem("SELECCIONA:");

        try {
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateHoy = null;
            try {
                dateHoy = inputFormatter.parse(fechafin);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            String salidaHoy = outputFormatter.format(dateHoy);
            MostrarVentasFinal("vent_fech ='" + salidaHoy + "'");
            System.out.println("Hoy es YA EN BUSCAR: " + salidaHoy);
        } catch (Exception ex) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡No se pudo cargar la lista de ventas!" + nl + "Asegurate que tu servidor de base de datos esta encendido");
        }
    }//GEN-LAST:event_btnHoyMenuVentaActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        EstablecerImpresora();
        ImprimirNormal.setModal(true);
        ImprimirNormal.setSize(550, 420);
        ImprimirNormal.setResizable(false);
        ImprimirNormal.setLocationRelativeTo(null);
        ImprimirNormal.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void cbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxActionPerformed

    }//GEN-LAST:event_cbxActionPerformed

    private void btnCambiarImpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarImpreActionPerformed
        try {
            Conexionbd cc = new Conexionbd();

            Connection cn = (Connection) cc.conexion();
            String elegir;
            elegir = cbx.getSelectedItem().toString();
            System.out.println(elegir);

            PreparedStatement pst = cn.prepareStatement("UPDATE impresoras SET codi_barr ='" + elegir + "' WHERE impr_id='3'");

            int n = pst.executeUpdate();
            EstablecerImpresora();
            if (n > 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "Impresora guardada como predeterminada para este módulo ");

            } else {

                JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");

            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }//GEN-LAST:event_btnCambiarImpreActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            String clave = "";
            clave = lblNumeroVentaDeta.getText();
            GuardarNota(clave);
            LlamarPdf lp = new LlamarPdf();
            LlamarPdf ca = new LlamarPdf();

            String origen = "Nota.PDF";

            try {
                lp.CopiarArchivo(origen, "");

            // String nl = System.getProperty("line.separator");
                // JOptionPane.showMessageDialog(null, "Archivo guardado en: " + nl + destinoFinal);
                //txtRuta.setText(destinoFinal); //se cacha la ruta a la caja de texto
            } catch (IOException ex) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "No se pudo hacer una copia del archivo fuente" + nl + "Elije cualquier biblioteca con excepción directa a unidad C:");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo hacer crear del archivo fuente");
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String clave = "";
        clave = lblNumeroVentaDeta.getText();
        System.out.println(clave + "++++++++++++ezta ez la clave para imprimir---------------");
        ImprimirNota(clave);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("Nota.pdf");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
        }

        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        //Nombre de la impresora
        String printerName = comparaImpresora;
        System.out.println("+++++++++++++++++" + comparaImpresora);
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

        System.out.println("Imprimiendo en : " + services[numImpre].getName());

        DocPrintJob printJob = services[numImpre].createPrintJob();
        try {
            //Envio a la impresora
            printJob.print(document, new HashPrintRequestAttributeSet());
            JOptionPane.showMessageDialog(null, "¡Listo!");
        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        /*    LlamarPdf lp = new LlamarPdf();
         String destino = txtRuta.getText().toString();//se borro el JtextField para usar codigo crear otro nuevo
         String origen = "CBARRA.PDF";
         System.out.println(destino);
         try {
         destino=
         lp.CopiarArchivo(origen, destino);
         } catch (IOException ex) {
         Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        ImprimirNormal.dispose();

    }//GEN-LAST:event_jButton16ActionPerformed

    private void cbxReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxReporteActionPerformed

    private void btnCambiarImpre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarImpre1ActionPerformed
        try {
            Conexionbd cc = new Conexionbd();

            Connection cn = (Connection) cc.conexion();
            String elegir;
            elegir = cbxReporte.getSelectedItem().toString();
            System.out.println(elegir);

            PreparedStatement pst = cn.prepareStatement("UPDATE impresoras SET codi_barr ='" + elegir + "' WHERE impr_id='4'");

            int n = pst.executeUpdate();
            EstablecerImpresora();
            if (n > 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "Impresora guardada como predeterminada para este módulo ");

            } else {

                JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");

            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }//GEN-LAST:event_btnCambiarImpre1ActionPerformed

    private void btnXtodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXtodoActionPerformed
        System.out.println(query + " este es query antes de mandar a imprimir");
        ImprimirReporte(query);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("ReporteGeneral.pdf");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
        }

        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        //Nombre de la impresora
        String printerName = comparaImpresora;
        System.out.println("+++++++++++++++++" + comparaImpresora);
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

        System.out.println("Imprimiendo en : " + services[numImpre].getName());

        DocPrintJob printJob = services[numImpre].createPrintJob();
        try {
            //Envio a la impresora
            printJob.print(document, new HashPrintRequestAttributeSet());
            JOptionPane.showMessageDialog(null, "¡Listo!");
        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
        }
    }//GEN-LAST:event_btnXtodoActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        BotonesReporteVisibles();
        ImprimirReporte.dispose();

    }//GEN-LAST:event_jButton19ActionPerformed

    private void btnCrearReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearReporteActionPerformed
        EstablecerImpresoraReporte();
        ImprimirReporte.setModal(true);
        ImprimirReporte.setSize(550, 420);
        ImprimirReporte.setResizable(false);
        ImprimirReporte.setLocationRelativeTo(null);
        ImprimirReporte.setVisible(true);
    }//GEN-LAST:event_btnCrearReporteActionPerformed

    private void btnXFechaEntreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXFechaEntreActionPerformed
        if (cbxSeleccionarBusqueda.getSelectedItem() == "DESDE - HASTA") {

            try {
                String fecha;
                if (datefechaUno != null) {
                    String formato = "yyyy-MM-dd";

                    java.util.Date date = datefechaUno.getDate();

                    SimpleDateFormat sdf = new SimpleDateFormat(formato);

                    String fechafin = sdf.format(date);
                    java.util.Date date2 = dateFechaDos.getDate();

                    SimpleDateFormat sdf2 = new SimpleDateFormat(formato);

                    String fechafin2 = sdf2.format(date2);

                    MostrarVentasFinal("vent_fech between '" + fechafin + "' and '" + fechafin2 + "'");
                    System.out.println(fechafin);
                    System.out.println(fechafin2);
                    ImprimirFechaEntre(fechafin, fechafin2);

                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream("REPOFECHAENTRE.pdf");
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
                    }

                    //Formato de Documento
                    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    //Lectura de Documento
                    Doc document = new SimpleDoc(inputStream, docFormat, null);

                    //Nombre de la impresora
                    String printerName = comparaImpresora;
                    System.out.println("+++++++++++++++++" + comparaImpresora);
                    //Inclusion del nombre de impresora y sus atributos
                    AttributeSet attributeSet = new HashAttributeSet();
                    attributeSet.add(new PrinterName(printerName, null));
                    attributeSet = new HashAttributeSet();
                    //Soporte de color o no
                    attributeSet.add(ColorSupported.NOT_SUPPORTED);

                    //Busqueda de la impresora por el nombre asignado en attributeSet
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                    System.out.println("Imprimiendo en : " + services[numImpre].getName());

                    DocPrintJob printJob = services[numImpre].createPrintJob();
                    try {
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                    } catch (PrintException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
                    }

                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
                    }
                }
            } catch (Exception e) {
            }

        }


    }//GEN-LAST:event_btnXFechaEntreActionPerformed

    private void btnXfechaEspecificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXfechaEspecificaActionPerformed
        if (cbxSeleccionarBusqueda.getSelectedItem() == "POR FECHA ESPECÍFICA") {

            try {
                if (datefechaUno != null) {
                    String formato = "yyyy-MM-dd";

                    java.util.Date date = datefechaUno.getDate();

                    SimpleDateFormat sdf = new SimpleDateFormat(formato);

                    String fechafin = sdf.format(date);
                    System.out.println(fechafin);

                    ImprimirPorFecha(fechafin);

                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream("REPOFECHA.pdf");
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
                    }

                    //Formato de Documento
                    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    //Lectura de Documento
                    Doc document = new SimpleDoc(inputStream, docFormat, null);

                    //Nombre de la impresora
                    String printerName = comparaImpresora;
                    System.out.println("+++++++++++++++++" + comparaImpresora);
                    //Inclusion del nombre de impresora y sus atributos
                    AttributeSet attributeSet = new HashAttributeSet();
                    attributeSet.add(new PrinterName(printerName, null));
                    attributeSet = new HashAttributeSet();
                    //Soporte de color o no
                    attributeSet.add(ColorSupported.NOT_SUPPORTED);

                    //Busqueda de la impresora por el nombre asignado en attributeSet
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                    System.out.println("Imprimiendo en : " + services[numImpre].getName());

                    DocPrintJob printJob = services[numImpre].createPrintJob();
                    try {
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                    } catch (PrintException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
                    }

                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
                    }
                }
            } catch (Exception e) {
            }

        }

    }//GEN-LAST:event_btnXfechaEspecificaActionPerformed

    private void btnXEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXEmpleadoActionPerformed
        if (cbxSeleccionarBusqueda.getSelectedItem() == "POR EMPLEADO") {

            try {
                if (cbxBuscarPorCliente.getSelectedItem() != null) {
                    query = cbxBuscarPorCliente.getSelectedItem().toString();
                    System.out.println(query + " query en buzcar por empleado");

                    ImprimirPorEmpleado(query);

                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream("REPOEMPLEADO.pdf");
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
                    }

                    //Formato de Documento
                    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    //Lectura de Documento
                    Doc document = new SimpleDoc(inputStream, docFormat, null);

                    //Nombre de la impresora
                    String printerName = comparaImpresora;
                    System.out.println("+++++++++++++++++" + comparaImpresora);
                    //Inclusion del nombre de impresora y sus atributos
                    AttributeSet attributeSet = new HashAttributeSet();
                    attributeSet.add(new PrinterName(printerName, null));
                    attributeSet = new HashAttributeSet();
                    //Soporte de color o no
                    attributeSet.add(ColorSupported.NOT_SUPPORTED);

                    //Busqueda de la impresora por el nombre asignado en attributeSet
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                    System.out.println("Imprimiendo en : " + services[numImpre].getName());

                    DocPrintJob printJob = services[numImpre].createPrintJob();
                    try {
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                    } catch (PrintException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
                    }

                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
                    }
                }
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_btnXEmpleadoActionPerformed

    private void btnXIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXIDActionPerformed
        ImprimirPorIdVenta(query);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("REPOIDVENTA.pdf");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
        }

        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        //Nombre de la impresora
        String printerName = comparaImpresora;
        System.out.println("+++++++++++++++++" + comparaImpresora);
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

        System.out.println("Imprimiendo en : " + services[numImpre].getName());

        DocPrintJob printJob = services[numImpre].createPrintJob();
        try {
            //Envio a la impresora
            printJob.print(document, new HashPrintRequestAttributeSet());
            JOptionPane.showMessageDialog(null, "¡Listo!");
        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
        }


    }//GEN-LAST:event_btnXIDActionPerformed

    private void btnXClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXClienteActionPerformed
        if (cbxSeleccionarBusqueda.getSelectedItem() == "POR CLIENTE") {

            try {
                if (cbxBuscarPorCliente.getSelectedItem() != null) {
                    query = cbxBuscarPorCliente.getSelectedItem().toString();
                    System.out.println(query + " query en buzcar por CLIENTE");

                    ImprimirPorCliente(query);

                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream("REPOCLIENTE.pdf");
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
                    }

                    //Formato de Documento
                    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    //Lectura de Documento
                    Doc document = new SimpleDoc(inputStream, docFormat, null);

                    //Nombre de la impresora
                    String printerName = comparaImpresora;
                    System.out.println("+++++++++++++++++" + comparaImpresora);
                    //Inclusion del nombre de impresora y sus atributos
                    AttributeSet attributeSet = new HashAttributeSet();
                    attributeSet.add(new PrinterName(printerName, null));
                    attributeSet = new HashAttributeSet();
                    //Soporte de color o no
                    attributeSet.add(ColorSupported.NOT_SUPPORTED);

                    //Busqueda de la impresora por el nombre asignado en attributeSet
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                    System.out.println("Imprimiendo en : " + services[numImpre].getName());

                    DocPrintJob printJob = services[numImpre].createPrintJob();
                    try {
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                    } catch (PrintException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
                    }

                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
                    }
                }
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_btnXClienteActionPerformed

    private void btnXHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXHoyActionPerformed
        String formato = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechafin = sdf.format(date);
        btnHoyMenuVenta.setText(fechafin);
        DateFormat inputFormatter = new SimpleDateFormat(formato);
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateHoy = null;
        try {
            dateHoy = inputFormatter.parse(fechafin);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        String salidaHoy = outputFormatter.format(dateHoy);
        System.out.println("Hoy es INICIO: " + salidaHoy);

        ImprimirPorFecha(salidaHoy);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("REPOFECHA.pdf");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
        }

        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        //Nombre de la impresora
        String printerName = comparaImpresora;
        System.out.println("+++++++++++++++++" + comparaImpresora);
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

        System.out.println("Imprimiendo en : " + services[numImpre].getName());

        DocPrintJob printJob = services[numImpre].createPrintJob();
        try {
            //Envio a la impresora
            printJob.print(document, new HashPrintRequestAttributeSet());
            JOptionPane.showMessageDialog(null, "¡Listo!");
        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
        }
    }//GEN-LAST:event_btnXHoyActionPerformed

    private void btnXAyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXAyerActionPerformed
        ImprimirPorFecha(query);
        System.out.println(query + " query en reporte de ayer");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("REPOFECHA.pdf");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo fuente");
        }

        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        //Nombre de la impresora
        String printerName = comparaImpresora;
        System.out.println("+++++++++++++++++" + comparaImpresora);
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

        System.out.println("Imprimiendo en : " + services[numImpre].getName());

        DocPrintJob printJob = services[numImpre].createPrintJob();
        try {
            //Envio a la impresora
            printJob.print(document, new HashPrintRequestAttributeSet());
            JOptionPane.showMessageDialog(null, "¡Listo!");
        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Se encontro un problema al tratar de imprimir");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "¡Error! Ocurrio un problema en el flujo de entrada del archivo");
        }


    }//GEN-LAST:event_btnXAyerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // eliminar
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "¡Error! no se pudo acceder ala Base de datos");
        }
        try {
            int fila = tblVenta.getSelectedRow();
            String id = "";
            id = tblVenta.getValueAt(fila, 0).toString();
            java.sql.Connection cn = cc.conexion();
            int dato = JOptionPane.showConfirmDialog(null, login.Guardausuario + "  ¿Estas seguro de eliminar este registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (dato == 0) {
                try {
                    java.sql.PreparedStatement pst = cn.prepareStatement("DELETE FROM ventas WHERE vent_id='" + id + "'");
                    pst.executeUpdate();
                    MostrarVentas("");
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ningun ID de la lista de abajo");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void GuardarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarReporteActionPerformed
        try {
            GuardarReporte(query);
            LlamarPdf lp = new LlamarPdf();
            LlamarPdf ca = new LlamarPdf();

            String origen = "ReporteGeneral.PDF";

            try {
                lp.CopiarArchivo(origen, "");

                // String nl = System.getProperty("line.separator");
                // JOptionPane.showMessageDialog(null, "Archivo guardado en: " + nl + destinoFinal);
                //txtRuta.setText(destinoFinal); //se cacha la ruta a la caja de texto
            } catch (IOException ex) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "No se pudo hacer una copia del archivo fuente" + nl + "Elije cualquier biblioteca con excepción directa a unidad C:");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo hacer crear del archivo fuente");
        }

    }//GEN-LAST:event_GuardarReporteActionPerformed

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
            java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuVentas().setVisible(true);
                BasicConfigurator.configure();
                Properties props = new Properties();

                try {
                    props.load(new FileInputStream("log4j.xml"));
                } catch (IOException ex) {

                }

                props.setProperty("log4j.appender.File", "log4j.xml");
                // props.setProperty("log4j.appender.File.File", "propiedades/" +"log4j.xml");
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu ClickDerecho;
    private javax.swing.JDialog ConfiguraImpresora;
    private javax.swing.JDialog Detalles;
    private javax.swing.JButton GuardarReporte;
    private javax.swing.JDialog ImprimirNormal;
    private javax.swing.JDialog ImprimirReporte;
    private javax.swing.JDialog NuevaVenta;
    private javax.swing.JTextArea areaObservaciones;
    private javax.swing.JButton btnAgregarVentaLista;
    private javax.swing.JButton btnBuscaAvanzado;
    private javax.swing.JButton btnBuscarVenta;
    private javax.swing.JButton btnCambiarImpre;
    private javax.swing.JButton btnCambiarImpre1;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnCrearReporte;
    private javax.swing.JButton btnEliminarFila;
    private javax.swing.JButton btnGuardarImpre;
    private javax.swing.JButton btnHoyMenuVenta;
    private javax.swing.JButton btnMasCantidad;
    private javax.swing.JButton btnMenosCantidad;
    private javax.swing.JButton btnOkCantidad;
    private javax.swing.JButton btnOkDescuentoProducto;
    private javax.swing.JButton btnOkDescuentoVenta;
    private javax.swing.JButton btnXAyer;
    private javax.swing.JButton btnXCliente;
    private javax.swing.JButton btnXEmpleado;
    private javax.swing.JButton btnXFechaEntre;
    private javax.swing.JButton btnXHoy;
    private javax.swing.JButton btnXID;
    private javax.swing.JButton btnXfechaEspecifica;
    private javax.swing.JButton btnXtodo;
    private javax.swing.JComboBox<String> cbx;
    private javax.swing.JComboBox<String> cbxBuscarPorCliente;
    private javax.swing.JComboBox<String> cbxCliente;
    private javax.swing.JComboBox<String> cbxReporte;
    private javax.swing.JComboBox<String> cbxSelecImpre;
    private javax.swing.JComboBox<String> cbxSeleccionarBusqueda;
    private javax.swing.JCheckBox chkIva;
    private org.edisoncor.gui.varios.ClockFace clockFace1;
    private com.toedter.calendar.JDateChooser dateFechaDos;
    private com.toedter.calendar.JDateChooser datefechaUno;
    private javax.swing.JLabel desde;
    private javax.swing.JLabel hasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblClienteNombre;
    private javax.swing.JLabel lblClienteNombre1;
    private javax.swing.JLabel lblClienteNombre2;
    private javax.swing.JLabel lblClienteNombre5;
    private javax.swing.JLabel lblClienteNombre7;
    private javax.swing.JLabel lblDescaVent;
    private javax.swing.JLabel lblDescuentoVenta;
    private javax.swing.JLabel lblDetaTotal1;
    private javax.swing.JLabel lblDetaTotal2;
    private javax.swing.JLabel lblDetaTotalfinal;
    private javax.swing.JLabel lblDineroRecibi;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaCompraCleinte;
    private javax.swing.JLabel lblIVAdetalle;
    private javax.swing.JLabel lblInfolike;
    private javax.swing.JLabel lblInfolike1;
    private javax.swing.JLabel lblNombreVendedor;
    private javax.swing.JLabel lblNumeroVenta;
    private javax.swing.JLabel lblNumeroVentaDeta;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JLabel lblVendedorDetalle;
    private javax.swing.JLabel lblcambiodeta;
    private javax.swing.JLabel lblsubtotalventa;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    private org.edisoncor.gui.panel.PanelTranslucido panelTranslucido1;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblVenta;
    private javax.swing.JToggleButton tglRegalo;
    private javax.swing.JTextField txtBuscarId;
    private javax.swing.JTextField txtBuscarLikeFecha;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtDescuentoProducto;
    private javax.swing.JTextField txtDescuentoVenta;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtPaga;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
 int filas = 0;

    private void agregarFila(String valor) throws Throwable {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String sql = "";
        boolean si = false;
        String sqlcomparar = "SELECT id_producto FROM productos WHERE id_producto ='" + valor + "'";
        String[] compara = new String[1];
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = (Connection) cc.conexion();
            java.sql.Statement stc = cn.createStatement();
            ResultSet rsc = stc.executeQuery(sqlcomparar);
            while (rsc.next()) {
                compara[0] = rsc.getString(1);
                System.out.println(compara[0] + "-------------------------+++***este es antes que comparar id");
                System.out.println(valor + ".,.--..,.--.,este es valor que se manda");
            }
            if (valor.equals(compara[0])) {

                String text = txtClave.getText();

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, 0).equals(text)) {
                        si = true;
                        tabla.changeSelection(i, 1, false, false);
                        incrementarCantidad();
                        break;

                    }
                }
                if (si == false) {

                    sql = "SELECT id_producto, descripcion, precio_venta from productos where id_producto='" + valor + "'";

                    String sqlStock = "Select stock from productos where id_producto='" + valor + "'";
                    String[] datosStock = new String[1];
                    java.sql.Statement sts = cn.createStatement();
                    ResultSet rss = sts.executeQuery(sqlStock);
                    while (rss.next()) {
                        datosStock[0] = rss.getString(1);

                        productoStock = Integer.parseInt(datosStock[0]);
                    }
                    if (productoStock == 0) {
                        JOptionPane.showMessageDialog(null, "¡UPSS! Por el momento no tenemos el producto solicitado", "Alerta", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\Ventas\\Imagenes\\botones\\triste.png"));
                    } else {

                        String[] datos = new String[20];
                        try {

                            Statement st = (Statement) cn.createStatement();
                            ResultSet rs = st.executeQuery(sql);
                            while (rs.next()) {
                                datos[0] = rs.getString(1);
                                datos[1] = rs.getString(2);
                                datos[2] = rs.getString(3);
                                datos[3] = "0";
                                datos[4] = "1";
                                datos[5] = rs.getString(3);
                                datos[6] = Integer.toString(productoStock);

                            }

                            Object[] fila = new Object[7];// dos elementos,poruqe  tenemos dos filas
                            fila[0] = datos[0].toString();
                            fila[1] = datos[1].toString();
                            fila[2] = datos[2].toString();
                            fila[3] = datos[3].toString();
                            fila[4] = datos[4].toString();
                            fila[5] = datos[5].toString();
                            fila[6] = datos[6].toString();
                            /*TableColumn columna;
                             columna=tabla.getColumnModel().getColumn(3);
                             columna.setPreferredWidth(90);
                             columna.setMaxWidth(90);
                             columna.setMinWidth(90);
                             */
                            tabla.setRowHeight(35);

                            // finalemente agregamos este array al model
                            modelo.addRow(fila);

                            // para terminar agregamos es te model a ala tabla
                            tabla.setModel(modelo);

                        } catch (SQLException ex) {

                            System.out.println(ex);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡UPSS! No existe ningún próducto en el inventario con esa código", "Alerta", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\Ventas\\Imagenes\\botones\\tri.png"));
            }
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    public void imprimir() {
        String formato = "yyyy-MM-dd";
        try {
            Date date = new Date();
            SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss aa");
            Ticket ticket = new Ticket();
            ticket.AddCabecera("             SANDALS RESTAURANT");
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddCabecera("     tlf: 222222  r.u.c: 22222222222");
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("     Ticket Factura No:'003-000011'");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("        " + fecha.format(date) + " " + hora.format(date));
            ticket.AddSubCabecera(ticket.DarEspacio());
            //  ticket.AddSubCabecera("         Mesa "+jTextField7.getText()+" Mozo "+jTextField8.getText()+" Pers "+jTextField9.getText());
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("cant      producto         p.u     total");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            for (int y = 0; y < tabla.getRowCount(); y++) {
                //cantidad de decimales
                NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
                DecimalFormat form = (DecimalFormat) nf;
                form.applyPattern("#,###.00");
                //cantidad
                String cantidad = tabla.getValueAt(y, 4).toString();
                if (cantidad.length() < 4) {
                    int cant = 4 - cantidad.length();
                    String can = "";
                    for (int f = 0; f < cant; f++) {
                        can += " ";
                    }
                    cantidad += can;
                }
                //items
                String item = tabla.getValueAt(y, 1).toString();
                if (item.length() > 17) {
                    item = item.substring(0, 16) + ".";
                } else {
                    int c = 17 - item.length();
                    String comple = "";
                    for (int y1 = 0; y1 < c; y1++) {
                        comple += " ";
                    }
                    item += comple;
                }
                //precio
                String precio = tabla.getValueAt(y, 2).toString();
                double pre1 = Double.parseDouble(precio);
                precio = form.format(pre1);
                precio = Double.toString(pre1);
                if (precio.length() < 8) {
                    int p = 8 - precio.length();
                    String pre = "";
                    for (int y1 = 0; y1 < p; y1++) {
                        pre += " ";
                    }
                    precio = pre + precio;
                }
                //total
                String total = tabla.getValueAt(y, 3).toString();
                total = form.format(Double.parseDouble(total));
                if (total.length() < 8) {
                    int t = 8 - total.length();
                    String tota = "";
                    for (int y1 = 0; y1 < t; y1++) {
                        tota += " ";
                    }
                    total = tota + total;
                }
                //agrego los items al detalle
                ticket.AddItem(cantidad, item, precio, total);
                //ticket.AddItem("","","",ticket.DarEspacio());
            }
            ticket.AddItem(ticket.DibujarLinea(40), "", "", "");
            ticket.AddTotal("", ticket.DarEspacio());
            ticket.AddTotal("total                   ", txtSubTotal.getText());
            ticket.AddTotal("", ticket.DarEspacio());
            ticket.AddTotal("Cantidad                    ", txtCantidad.getText());
            ticket.AddTotal("", ticket.DarEspacio());
            ticket.AddTotal("total venta             ", "90");
            ticket.AddTotal("", ticket.DarEspacio());
            ticket.AddTotal("paga con                ", "100");
            ticket.AddTotal("", ticket.DarEspacio());
            ticket.AddTotal("vuelto                  ", "10");
            ticket.AddPieLinea(ticket.DarEspacio());
            ticket.AddPieLinea("Gracias por su Preferencia");
            ticket.ImprimirDocumento("Generic / Text Only 2", true);

            System.out.println("zi pazo x aki");
        } catch (Exception e) {
            System.out.print("\nerror " + e.toString());
        }
    }

    void imprimirFactura() {

        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);

        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(60, 80);
        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
        printer.printCharAtCol(1, 1, 80, "=");
        //Imprimir Encabezado nombre del La EMpresa
        printer.printTextWrap(1, 2, 30, 80, "FACTURA DE VENTA");
        //printer.printTextWrap(linI, linE, colI, colE, null);
        printer.printTextWrap(2, 3, 1, 22, "Num. Boleta : " + "1");//txtVentaNumeroFactura.getText());
        printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + "11/06/1985");//dateFechaVenta.getDate());
        printer.printTextWrap(2, 3, 60, 80, "Hora: 12:22:51");
        printer.printTextWrap(3, 3, 1, 80, "Vendedor.  : " + "Leonel");//txtVentaIdVendedor.getText() +" - " + txtVentaNombreVendedor.getText());
        printer.printTextWrap(4, 4, 1, 80, "CLIENTE: " + "Juanito");//txtVentaNombreCliente.getText());
        printer.printTextWrap(5, 5, 1, 80, "RUC/CI.: " + "0");//txtVentaRucCliente.getText());
        printer.printTextWrap(6, 6, 1, 80, "DIRECCION: " + "");
        printer.printCharAtCol(7, 1, 80, "=");
        printer.printTextWrap(7, 8, 1, 80, "Codigo          Descripcion                Cant.      P  P.Unit.      P.Total");
        printer.printCharAtCol(9, 1, 80, "-");
        int filas = tabla.getRowCount();

        for (int i = 0; i < filas; i++) {
            printer.printTextWrap(9 + i, 10, 1, 80, tabla.getValueAt(i, 0).toString() + "|" + tabla.getValueAt(i, 1).toString() + "|" + tabla.getValueAt(i, 2).toString() + "|" + tabla.getValueAt(i, 3).toString() + "|" + tabla.getValueAt(i, 4).toString() + "|" + tabla.getValueAt(i, 5).toString());
        }
        if (filas > 15) {

            printer.printCharAtCol(filas + 1, 1, 80, "=");
            printer.printTextWrap(filas + 1, filas + 2, 1, 80, "TOTAL A PAGAR " + txtSubTotal.getText());
            printer.printCharAtCol(filas + 2, 1, 80, "=");
            printer.printTextWrap(filas + 2, filas + 3, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
        } else {
            printer.printCharAtCol(25, 1, 80, "=");
            printer.printTextWrap(26, 26, 1, 80, "TOTAL A PAGAR " + txtSubTotal.getText());
            printer.printCharAtCol(27, 1, 80, "=");
            printer.printTextWrap(27, 28, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
            CortarHoja();

        }
        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    void DescuentoProducto() {
        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                String descuento = txtDescuentoProducto.getText();
                if (descuento.length() != 0) {
                    String precio;
                    double preciocaden;
                    double preciocadenatotal;
                    String precioCadena = tabla.getValueAt(fila, 2).toString();
                    String numeroDeProductos = tabla.getValueAt(fila, 4).toString();
                    double compararCantidad = Double.parseDouble(numeroDeProductos);
                    if (compararCantidad > 1) {
                        preciocaden = Double.parseDouble(precioCadena);
                        preciocadenatotal = preciocaden * compararCantidad;
                        precio = Double.toString(preciocadenatotal);
                        System.out.println(precio + "---------------------+++++++++++++++++++++++++++++++++++++++precio original");
                    } else {
                        precio = tabla.getValueAt(fila, 2).toString();
                    }
                    System.out.println(descuento + "---------------------descuento");
                    System.out.println(precio + "---------------------precio");
                    double valorDescuento = Double.parseDouble(descuento);
                    double valorPrecio = Double.parseDouble(precio);
                    System.out.println(valorDescuento + "---------------------dezcuentofloat");
                    System.out.println(valorPrecio + "---------------------preciodozdouble");
                    tabla.setValueAt(valorDescuento, fila, 3);
                    double cantidadDescuento = (valorPrecio * valorDescuento) / 100;
                    System.out.println(cantidadDescuento + "---------------------precio a reztar");
                    tabla.setValueAt(valorPrecio - cantidadDescuento, fila, 5);

                    double sumatoria1 = 0.0;
                    int totalRow = tabla.getRowCount();
                    totalRow -= 1;
                    for (int i = 0; i <= (totalRow); i++) {
                        double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                        //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                        sumatoria1 += sumatoria;

                        System.out.println("" + sumatoria1);
                        txtSubTotal.setText(Double.toString(sumatoria1));
                        double total;
                        total = IVATotal(sumatoria1);
                        txtTotal.setText(Double.toString(total));
                        lblDescuentoVenta.setText("");

                    }
                }

            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
        txtDescuentoProducto.setText("");

    }

    void DescuentoProducto(double descuento) {
        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {

                String precio;
                double preciocaden;
                double preciocadenatotal;
                String precioCadena = tabla.getValueAt(fila, 2).toString();
                String numeroDeProductos = tabla.getValueAt(fila, 4).toString();
                double compararCantidad = Double.parseDouble(numeroDeProductos);
                if (compararCantidad > 1) {
                    preciocaden = Double.parseDouble(precioCadena);
                    preciocadenatotal = preciocaden * compararCantidad;
                    precio = Double.toString(preciocadenatotal);
                    System.out.println(precio + "---------------------+++++++++++++++++++++++++++++++++++++++precio original");
                } else {
                    precio = tabla.getValueAt(fila, 2).toString();
                }
                System.out.println(descuento + "---------------------descuento");
                System.out.println(precio + "---------------------precio");
                double valorDescuento = descuento;
                double valorPrecio = Double.parseDouble(precio);
                System.out.println(valorDescuento + "---------------------dezcuentofloat");
                System.out.println(valorPrecio + "---------------------preciodozdouble");
                tabla.setValueAt(valorDescuento, fila, 3);
                double cantidadDescuento = (valorPrecio * valorDescuento) / 100;
                System.out.println(cantidadDescuento + "---------------------precio a reztar");
                tabla.setValueAt(valorPrecio - cantidadDescuento, fila, 5);

                double sumatoria1 = 0.0;
                int totalRow = tabla.getRowCount();
                totalRow -= 1;
                for (int i = 0; i <= (totalRow); i++) {
                    double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                    //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                    sumatoria1 += sumatoria;

                    System.out.println("" + sumatoria1);
                    txtSubTotal.setText(Double.toString(sumatoria1));
                    double total;
                    total = IVATotal(sumatoria1);
                    txtTotal.setText(Double.toString(total));
                    lblDescuentoVenta.setText("");
                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
        txtDescuentoProducto.setText("");

    }

    void incrementarCantidad() {
        int fila = tabla.getSelectedRow();
        int columna = tabla.getSelectedRow();
        double descuento;
        if (fila >= 0) {
            if (tabla.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                String valor = tabla.getValueAt(fila, 4).toString();
                String valorDescuento = tabla.getValueAt(fila, 3).toString();
                descuento = Double.parseDouble(valorDescuento);
                String valorprecio = tabla.getValueAt(fila, 2).toString();
                System.out.println(valor + "---------------------cantidad");
                System.out.println(valorprecio + "---------------------precio");
                int valoruno = Integer.parseInt(valor);
                double valordoz = Double.parseDouble(valorprecio);
                System.out.println(valoruno + "---------------------cantidaddoz");
                System.out.println(valordoz + "---------------------preciodoz");
                if (valoruno + 1 > Integer.parseInt(tabla.getValueAt(fila, 6).toString())) {
                    JOptionPane.showMessageDialog(null, "¡No puede rebasar la cantidad de productos que existen en el stock!");
                } else {
                    tabla.setValueAt(valoruno + 1, fila, 4);

                    valor = tabla.getValueAt(fila, 4).toString();
                    valoruno = Integer.parseInt(valor);
                    tabla.setValueAt(valordoz * valoruno, fila, 5);
                    DescuentoProducto(descuento);
                    double sumatoria1 = 0.0;
                    int totalRow = tabla.getRowCount();
                    totalRow -= 1;
                    for (int i = 0; i <= (totalRow); i++) {
                        double sumatoria = Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
                        //en la parte de arriba indica el primer parametro la fila y el segundo la columna la cual estaras //manejando
                        sumatoria1 += sumatoria;

                        System.out.println("" + sumatoria1);
                        txtSubTotal.setText(Double.toString(sumatoria1));
                    }
                }

            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }

    }

    public String CortarHoja() {
        return (char) 27 + "m";
    }

    private double IVATotal(double subTotal) {
        double iva;
        double total = 0;
        if (chkIva.isSelected() == true) {
            iva = (subTotal * 16) / 100;
            txtIva.setText(Double.toString(iva));
            total = subTotal + iva;
            return total;
        } else {
            total = subTotal;
            txtIva.setText("0.0");
            return total;
        }
    }

    public String obtenerHora() {

        String horaActual;
        Calendar fecha = new GregorianCalendar();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        System.out.printf("Hora Actual: %02d:%02d:%02d %n", hora, minuto, segundo);
        horaActual = hora + ":" + minuto + ":" + segundo;
        System.out.println(horaActual);
        return horaActual;

    }

    void Limpiar() {
        txtSubTotal.setText("");
        txtTotal.setText("");
        txtIva.setText("");
        txtDescuentoVenta.setText("");
        txtPaga.setText("");
        txtCambio.setText("");
        cbxCliente.setSelectedIndex(-1);
        chkIva.setSelected(false);
        areaObservaciones.setText("");
        lblDescuentoVenta.setText("");
        Conexionbd cc = new Conexionbd();
        java.sql.Connection cn = cc.conexion();
        String sql = "SELECT MAX(VENT_ID) AS id FROM ventas";
        int valorId, idNuevo;
        String recibir;
        String formato = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechafin = sdf.format(date);
        System.out.println(fechafin);
        lblFecha.setText(fechafin);
        String nombreEmpleado;

        try {
            String[] datosId = new String[1];

            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datosId[0] = rs.getString(1);
            }
            recibir = datosId[0];

            System.out.println(recibir + " valor obtenido bd");
            if (recibir.equals(null)) {
                idNuevo = 1;
                lblNumeroVenta.setText(Integer.toString(idNuevo));
            } else {
                valorId = Integer.parseInt(recibir);
                idNuevo = valorId + 1;
                System.out.println(idNuevo);
                lblNumeroVenta.setText(Integer.toString(idNuevo));
            }
        } catch (Exception ex) {
            System.out.println(ex);
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, ex + " ¡No ha sido posible obtener el ultimo Id de venta"
                    + nl + "Si es su primera venta ignore este mensaje" + nl + "sino consulte su manual de usuario o o directamente con su administrador");
            lblNumeroVenta.setText(Integer.toString(1));

        }
    }

    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public static boolean Vacia(JTable jTable) {
        if (jTable != null && jTable.getModel() != null) {
            return jTable.getModel().getRowCount() <= 0 ? true : false;
        }
        return false;
    }

    public void LetraCapital(java.awt.event.KeyEvent evt, JTextArea a) {
        a = (JTextArea) evt.getComponent();

        String texto = a.getText();//.trim();//para evitar espacios en blanco antes y despues del texto
        if (texto.length() > 0) {
            char primero = texto.charAt(0);
            texto = Character.toUpperCase(primero) + texto.substring(1, texto.length());
            a.setText(texto);
        }
    }

    public void Validar() {
        RestrictedTextField restricted = new RestrictedTextField(txtClave);
        restricted.setLimit(6);
        RestrictedTextField restricted1 = new RestrictedTextField(txtDescuentoProducto);
        restricted1.setLimit(5);
        RestrictedTextField restricted2 = new RestrictedTextField(txtDescuentoVenta);
        restricted2.setLimit(5);
        RestrictedTextField restricted3 = new RestrictedTextField(txtSubTotal);
        restricted3.setLimit(12);
        RestrictedTextField restrictedTeleCasa = new RestrictedTextField(txtTotal);
        restrictedTeleCasa.setLimit(12);
        RestrictedTextField restricted4 = new RestrictedTextField(txtIva);
        restricted4.setLimit(12);
        RestrictedTextField restricted5 = new RestrictedTextField(txtPaga);
        restricted5.setLimit(12);
        RestrictedTextField restricted6 = new RestrictedTextField(txtCantidad);
        restricted6.setLimit(3);
        solo_numeros.solonumeros(txtDescuentoProducto);
        solo_numeros.solonumeros(txtDescuentoVenta);
        solo_numeros.solonumeros(txtSubTotal);
        solo_numeros.solonumeros(txtIva);
        solo_numeros.solonumeros(txtTotal);
        solo_numeros.solonumeros(txtPaga);
        soloNumeros(txtCantidad);
        mayusculas(txtClave);

    }

    public void soloNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    getToolkit().beep();
                    e.consume();

                }

            }

        });
    }

    public void mayusculas(JTextField e) {
        e.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (Character.isLowerCase(caracter)) {
                    e.setKeyChar(Character.toUpperCase(caracter));
                }
            }
        });
    }

    void GuardarVenta() throws SQLException {
        Conexionbd cc = new Conexionbd();
        java.sql.Connection cn = cc.conexion();
        String subtotal, total, paga, cambio, descuento_venta, fnac, cliente;
        int ClienteId = 0;
        String formato = "yyyy-MM-dd";
        try {

            String sql, sqlIdCliente = "";

            cliente = cbxCliente.getSelectedItem().toString();
            System.out.println(cliente);
            sqlIdCliente = "SELECT CLIENTE_ID FROM clientes WHERE concat_ws(' ', CLIENTE_FIRST_NAME, CLIENTE_PATERNO,CLIENTE_MATERNO)='" + cliente + "'";
            ClienteId = 0;
            String[] datosClienteId = new String[1];
            try {
                Statement stE = (Statement) cn.createStatement();
                ResultSet rsE = stE.executeQuery(sqlIdCliente);
                while (rsE.next()) {
                    datosClienteId[0] = rsE.getString(1);

                    ClienteId = Integer.parseInt(datosClienteId[0]);
                }

                System.out.println(ClienteId + " este ez el id del Cliente");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "¡Error! al obtener id de cliente " + ex);
                System.out.println(ex);
            }

            if (cbxCliente.getSelectedItem().equals("")) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el nombre del cliente");
                cbxCliente.requestFocus();

            } else if (Vacia(tabla) == true) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Para realizar una venta primero tienes que ingresar productos");
                txtSubTotal.requestFocus();
            } else {
                subtotal = txtSubTotal.getText();
                if (subtotal.length() == 0) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! subtotal debe tener una cifra mayor a cero");
                    txtSubTotal.requestFocus();
                } else {
                    total = txtTotal.getText();
                    if (total.length() == 0) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! total debe tener una cifra mayor a cero");
                        txtTotal.requestFocus();

                    } else {
                        paga = txtPaga.getText();
                        if (paga.length() == 0) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Ingrese la cantidad recibida para liberar el pago");
                            txtPaga.requestFocus();
                        } else {
                            cambio = txtCambio.getText();
                            if (cambio.length() == 0) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Antes de cerrar la venta calcula el cambio" + nl + "Ingresa cantidad recibida y oprime enter");
                                txtPaga.requestFocus();
                            } else {

                                String formato2 = "yyyy-MM-dd";
                                Date date = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat(formato2);
                                String fechafin = sdf.format(date);
                                System.out.println(fechafin);

                                sql = "INSERT INTO ventas (VENT_ID,VENT_CLIE_ID,VENT_FECH,VENT_HORA,VENT_SUBT,VENT_IVA,VENT_TOTA,VENT_PAGA,VENT_CAMB,VENT_DESC_DIRE,VENT_OBSE,VENT_EMPL_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

                                String[] datos = new String[14];
                                if (datos[0] == null) {
                                    try {
                                        PreparedStatement pst = cn.prepareStatement(sql);
                                        pst.setString(1, lblNumeroVenta.getText());
                                        pst.setString(2, Integer.toString(ClienteId));
                                        pst.setString(3, fechafin);
                                        String HoraActual = Hora();
                                        pst.setString(4, HoraActual);
                                        pst.setString(5, subtotal);
                                        pst.setString(6, txtIva.getText());
                                        pst.setString(7, total);
                                        pst.setString(8, txtPaga.getText());
                                        pst.setString(9, txtCambio.getText());
                                        pst.setString(10, Double.toString(descuentoVentadirecta));
                                        pst.setString(11, areaObservaciones.getText());
                                        pst.setString(12, Integer.toString(idEmpleado));
                                        int n = pst.executeUpdate();

                                        if (n > 0) {
                                            JOptionPane.showMessageDialog(null, "Venta realizada con éxito", "Alerta", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\empleados\\imagenes\\EmpleadoAgregado.png"));
                                            // try {
                                            //   mostrardatos(txtFirstName.getText() + " " + txtPaternoCliente.getText() + " " + txtMaternoCliente.getText());
                                            //Limpiar();
                                            //limpiarTabla(tabla);
                                            //  } catch (Exception ex) {
                                            // java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(Level.SEVERE, null, ex);
                                            //  }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "¡Error! no se pudo guardar el registro");

                                        }

                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");
                                        System.out.println(e);
                                    }

                                }
                                GuardarDetalleventa();
                                try {

                                    String clave = "";
                                    clave = lblNumeroVenta.getText();
                                    System.out.println(clave + "++++++++++++ezta ez la clave para imprimir---------------");

                                    ConsultarCodigoBarras(clave);

                                } catch (Exception e) {

                                    JOptionPane.showMessageDialog(null, "¡Ocurrio un error al exportar el archivo imprimible!");
                                    System.out.println(e);
                                }
                                FileInputStream inputStream = null;
                                try {
                                    inputStream = new FileInputStream("ticketOpticx.pdf");
                                } catch (FileNotFoundException ex) {
                                }

                                //Formato de Documento
                                DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                                //Lectura de Documento
                                Doc document = new SimpleDoc(inputStream, docFormat, null);

                                //Nombre de la impresora
                                String printerName = comparaImpresora;
                                System.out.println("+++++++++++++++++" + comparaImpresora);
                                //Inclusion del nombre de impresora y sus atributos
                                AttributeSet attributeSet = new HashAttributeSet();
                                attributeSet.add(new PrinterName(printerName, null));
                                attributeSet = new HashAttributeSet();
                                //Soporte de color o no
                                attributeSet.add(ColorSupported.NOT_SUPPORTED);

                                //Busqueda de la impresora por el nombre asignado en attributeSet
                                PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                                System.out.println("Imprimiendo en : " + services[numImpre].getName());

                                DocPrintJob printJob = services[numImpre].createPrintJob();
                                try {
                                    //Envio a la impresora
                                    printJob.print(document, new HashPrintRequestAttributeSet());
                                    JOptionPane.showMessageDialog(null, "¡Listo!");
                                } catch (PrintException ex) {

                                    JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");
                                }

                                try {
                                    inputStream.close();
                                } catch (IOException ex) {

                                    JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "¡Ha ocurrido un error verifica tu información y comunica a tu administrador el suceso!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        VentasHoy();
        cn.close();
    }

    void GuardarDetalleventa() throws SQLException {
        Conexionbd cc = new Conexionbd();
        java.sql.Connection cn = cc.conexion();
        String extraerCantidad;

        try {
            sentencia = (Statement) cn.createStatement();
            sentenciaStock = (Statement) cn.createStatement();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        if (tabla.getRowCount() > 0) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                try {
                    sentencia.execute("insert into detalles_ventas (DETA_VENT_PROD_ID,DETA_VENT_VENT_ID,DETA_VENT_CANT_PROD,DETA_VENT_DESC_PROD,DETA_VENT_IMPO)values('" + tabla.getValueAt(i, 0) + "','" + lblNumeroVenta.getText() + "','" + tabla.getValueAt(i, 4) + "','" + tabla.getValueAt(i, 3) + "','" + tabla.getValueAt(i, 5) + "')");
                    extraerCantidad = tabla.getValueAt(i, 4).toString();
                    cantidad = Integer.parseInt(extraerCantidad);

                    System.out.println(extraerCantidad + "CANTIDAD AUN EN STRING");
                    System.out.println(cantidad + "CANTIDAD YA EN INT");
                    String sql = "Select stock from productos where id_producto='" + tabla.getValueAt(i, 0) + "'";
                    String[] datos = new String[1];
                    java.sql.Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        datos[0] = rs.getString(1);

                        productoStock = Integer.parseInt(datos[0]);
                        System.out.println(productoStock + "Stock obtenido");

                    }
                    nuevoStock = productoStock - cantidad;
                    System.out.println(nuevoStock + " NUEVO STOCK");

                    PreparedStatement pst;
                    pst = (PreparedStatement) cn.prepareStatement("UPDATE productos SET stock='" + nuevoStock + "' WHERE id_producto='" + tabla.getValueAt(i, 0) + "'");
                    int n = pst.executeUpdate();
                    if (n > 0) {
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error! no se pudo actualizar el stock de productos");

                    }

                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(MenuVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "tabla vacía");
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexión ala Base de Datos");
        }
        cn.close();
        Limpiar();
        limpiarTabla(tabla);

    }

    public void ImprimirCbx() {

        cbxSelecImpre.removeAllItems();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Tu impresora por default es: " + service.getName());
        for (PrintService printService : services) {
            System.out.println(" ---- IMPRESORA: " + printService.getName());
            if (printService.getName() == service.getName()) {
                cbxSelecImpre.addItem(printService.getName() /*+ " *"*/);
            } else {
                cbxSelecImpre.addItem(printService.getName());
            }

        }
        try {
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn2 = cc.conexion();
            PreparedStatement pe = cn2.prepareStatement("SELECT codi_barr FROM impresoras WHERE impr_id=2");
            ResultSet rs = pe.executeQuery();

            if (rs.next()) {
                //          cbx.setSelectedItem(rs.getString("codi_barr"));
                comparaImpresora = rs.getString("codi_barr");
                System.out.println("???????????????????---------------" + comparaImpresora + "--------------?????????????????");
                try {
                    cbxSelecImpre.setSelectedItem(comparaImpresora);
                    numImpre = cbxSelecImpre.getSelectedIndex();
                } catch (Exception ex) {
                    cbxSelecImpre.setSelectedItem(comparaImpresora);
                    numImpre = cbxSelecImpre.getSelectedIndex();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Por el momento no hay impresora guardada elije una predeterminada y oprime guardar");

            }

            cn2.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void ConsultarCodigoBarras(String id) {
        try {
            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            int convertId = Integer.parseInt(id);
            int idfinal = convertId - 1;

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("idVenta", idfinal);

            System.out.println(idfinal + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "ticketOpticx.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "ticketOpticx.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    void MostrarVentas(String valor) throws Exception {
        String tomarfecha;
        DefaultTableModel modelo = new DefaultTableModel();
        int[] anchos = {40, 40, 40, 200, 200};
        modelo.addColumn("ID DE VENTA");
        modelo.addColumn("FECHA");
        modelo.addColumn("TOTAL");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("EMPLEADO");
        tblVenta.setModel(modelo);
        for (int i = 0; i < tblVenta.getColumnCount(); i++) {
            tblVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        String sql = "";
        if (valor.equals("")) {
            sql = "select v.vent_id,concat_ws(' ', v.VENT_FECH, v.VENT_HORA),v.vent_tota,concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO),"
                    + "concat_ws(' ', e.empl_nomb, e.empl_apat,e.empl_amat) from ventas as v inner Join empleados as e inner join clientes as c on vent_empl_id=empl_id and vent_clie_id=cliente_id order By concat_ws(' ', v.VENT_FECH, v.VENT_HORA) DESC";
            String[] datos = new String[6];
            try {
                Conexionbd cc = new Conexionbd();
                Connection cn = (Connection) cc.conexion();
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    datos[0] = rs.getString(1);
                    tomarfecha = rs.getString(2);
                    System.out.println("Entrada->" + tomarfecha);

                    DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    java.util.Date date = null;

                    try {
                        date = inputFormatter.parse(tomarfecha);
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }

                    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String salida = outputFormatter.format(date);
                    System.out.println("Salida->" + salida);
                    datos[1] = salida;
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);

                    modelo.addRow(datos);
                }
                tblVenta.setModel(modelo);
                cn.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            query = "";
        } else {
            sql = "select v.vent_id,concat_ws(' ', v.VENT_FECH, v.VENT_HORA),v.vent_tota,concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO),concat_ws(' ', e.empl_nomb, e.empl_apat,e.empl_amat) from ventas as v inner Join empleados as e inner join clientes as c on vent_empl_id=empl_id and vent_clie_id=cliente_id where vent_id='" + valor + "'";

            String[] datos = new String[14];
            try {
                Conexionbd cc = new Conexionbd();
                Connection cn = (Connection) cc.conexion();
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    datos[0] = rs.getString(1);
                    tomarfecha = rs.getString(2);
                    System.out.println("Entrada->" + tomarfecha);

                    DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    java.util.Date date = null;

                    try {
                        date = inputFormatter.parse(tomarfecha);
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }

                    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String salida = outputFormatter.format(date);
                    System.out.println("Salida->" + salida);
                    datos[1] = salida;
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);

                    modelo.addRow(datos);
                }
                tblVenta.setModel(modelo);
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            query = valor;

            System.out.println(query + " query en mostrar ventas por id");
        }

    }

    void MostrarVentas(String campo, String signo, String valor, String signoDos) throws Exception {
        String tomarfecha;
        DefaultTableModel modelo = new DefaultTableModel();
        int[] anchos = {40, 40, 40, 200, 200};
        modelo.addColumn("ID DE VENTA");
        modelo.addColumn("FECHA");
        modelo.addColumn("TOTAL");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("EMPLEADO");
        tblVenta.setModel(modelo);
        for (int i = 0; i < tblVenta.getColumnCount(); i++) {
            tblVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        String sql = "";
        sql = "select v.vent_id,concat_ws(' ', v.VENT_FECH, v.VENT_HORA),v.vent_tota,concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO),concat_ws(' ', e.empl_nomb, e.empl_apat,e.empl_amat)"
                + " from ventas as v inner Join empleados as e inner join clientes as c on vent_empl_id=empl_id and vent_clie_id=cliente_id where " + campo + "" + signo + "'" + valor + "'" + signoDos;

        String[] datos = new String[14];
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = (Connection) cc.conexion();
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                tomarfecha = rs.getString(2);
                System.out.println("Entrada->" + tomarfecha);

                DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date date = null;

                try {
                    date = inputFormatter.parse(tomarfecha);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String salida = outputFormatter.format(date);
                System.out.println("Salida->" + salida);
                datos[1] = salida;
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }
            tblVenta.setModel(modelo);
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    void MostrarVentasLike(String campo, String valor) throws Exception {
        String tomarfecha;
        DefaultTableModel modelo = new DefaultTableModel();
        int[] anchos = {40, 40, 40, 200, 200};
        modelo.addColumn("ID DE VENTA");
        modelo.addColumn("FECHA");
        modelo.addColumn("TOTAL");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("EMPLEADO");
        tblVenta.setModel(modelo);
        for (int i = 0; i < tblVenta.getColumnCount(); i++) {
            tblVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        String sql = "";
        sql = "select v.vent_id,concat_ws(' ', v.VENT_FECH, v.VENT_HORA),v.vent_tota,concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO),concat_ws(' ', e.empl_nomb, e.empl_apat,e.empl_amat)from ventas as v inner Join empleados as e inner join clientes as c on vent_empl_id=empl_id and vent_clie_id=cliente_id where " + campo + " LIKE '%" + valor + "%'";

        String[] datos = new String[14];
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = (Connection) cc.conexion();
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                tomarfecha = rs.getString(2);
                System.out.println("Entrada->" + tomarfecha);

                DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date date = null;

                try {
                    date = inputFormatter.parse(tomarfecha);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String salida = outputFormatter.format(date);
                System.out.println("Salida->" + salida);
                datos[1] = salida;
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }
            tblVenta.setModel(modelo);
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        query = "where " + campo + " LIKE '%" + valor + "%'";
    }

    void MostrarVentasFinal(String cadena) throws Exception {
        String tomarfecha;
        DefaultTableModel modelo = new DefaultTableModel();
        int[] anchos = {40, 40, 40, 200, 200};
        modelo.addColumn("ID DE VENTA");
        modelo.addColumn("FECHA");
        modelo.addColumn("TOTAL");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("EMPLEADO");
        tblVenta.setModel(modelo);
        for (int i = 0; i < tblVenta.getColumnCount(); i++) {
            tblVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        String sql = "";
        sql = "select v.vent_id,concat_ws(' ', v.VENT_FECH, v.VENT_HORA),v.vent_tota,concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO),concat_ws(' ', e.empl_nomb, e.empl_apat,e.empl_amat)from ventas as v inner Join empleados as e inner join clientes as c on vent_empl_id=empl_id and vent_clie_id=cliente_id where " + cadena + "";

        String[] datos = new String[14];
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = (Connection) cc.conexion();
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                tomarfecha = rs.getString(2);
                System.out.println("Entrada->" + tomarfecha);

                DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date date = null;

                try {
                    date = inputFormatter.parse(tomarfecha);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String salida = outputFormatter.format(date);
                System.out.println("Salida->" + salida);
                datos[1] = salida;
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }
            tblVenta.setModel(modelo);
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        query = "where " + cadena + "";
    }

    public String Hora() {
        Date Hora = new Date();
        SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
        String hora = sdfH.format(Hora);
        System.out.println(Hora + ".......esta es la hora");
        return hora;

    }

    void MostrarDetalles(String cadena) throws Exception {
        String tomarfecha;
        DefaultTableModel modelo = new DefaultTableModel();
        int[] anchos = {40, 40, 300, 40, 40, 70, 40};
        modelo.addColumn("ID DETALLE VENTA");
        modelo.addColumn("ID DE PRODUCTO");
        modelo.addColumn("DESCRIPCIÓN");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO");
        modelo.addColumn("DESCUENTO AL PRODUCTO");
        modelo.addColumn("IMPORTE");
        tblDetalles.setModel(modelo);
        for (int i = 0; i < tblDetalles.getColumnCount(); i++) {
            tblDetalles.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        String sql = "";
        sql = "select d.deta_vent_id,v.vent_id,concat_ws(' ', v.VENT_FECH, v.VENT_HORA),p.id_producto,p.descripcion,d.deta_vent_cant_prod,p.precio_venta,d.deta_vent_desc_prod,d.deta_vent_impo,v.vent_subt,v.vent_iva,v.vent_desc_dire,v.vent_tota,v.vent_paga,v.vent_camb,concat_ws(' ', c.CLIENTE_FIRST_NAME, c.CLIENTE_PATERNO,c.CLIENTE_MATERNO),concat_ws(' ', e.empl_nomb, e.empl_apat,e.empl_amat) from detalles_ventas as d inner Join productos as p inner join ventas as v inner join clientes as c inner join empleados as e on id_producto=deta_vent_prod_id and vent_id=deta_vent_vent_id and cliente_id = vent_clie_id and empl_id=vent_empl_id where " + cadena + "";

        String[] datos = new String[18];
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = (Connection) cc.conexion();
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lblNumeroVentaDeta.setText(rs.getString(2));
                tomarfecha = rs.getString(3);
                System.out.println("Entrada->" + tomarfecha);

                DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date date = null;

                try {
                    date = inputFormatter.parse(tomarfecha);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String salida = outputFormatter.format(date);
                System.out.println("Salida->" + salida);

                lblFechaCompraCleinte.setText(salida);
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(4);
                datos[2] = rs.getString(5);
                datos[3] = rs.getString(6);
                datos[4] = rs.getString(7);
                datos[5] = rs.getString(8);
                datos[6] = rs.getString(9);
                lblsubtotalventa.setText(rs.getString(10));
                lblIVAdetalle.setText(rs.getString(11));
                lblDescaVent.setText(rs.getString(12));
                lblDetaTotalfinal.setText(rs.getString(13));
                lblDineroRecibi.setText(rs.getString(14));
                lblcambiodeta.setText(rs.getString(15));
                lblClienteNombre.setText(rs.getString(16));
                lblVendedorDetalle.setText(rs.getString(17));

                modelo.addRow(datos);
            }
            tblDetalles.setModel(modelo);
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    void VentasHoy() {
        try {
            String formato = "dd/MM/yyyy";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String fechafin = sdf.format(date);
            btnHoyMenuVenta.setText(fechafin);
            DateFormat inputFormatter = new SimpleDateFormat(formato);
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateHoy = null;
            try {
                dateHoy = inputFormatter.parse(fechafin);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            String salidaHoy = outputFormatter.format(dateHoy);
            MostrarVentasFinal("vent_fech ='" + salidaHoy + "'order By concat_ws(' ', v.VENT_FECH, v.VENT_HORA) DESC");
            System.out.println("Hoy es INICIO: " + salidaHoy);
        } catch (Exception ex) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, " ¡No se pudo cargar la lista de ventas!" + nl + "Asegurate que tu servidor de base de datos esta encendido");
        }

    }

    public void ImprimirTicket(String id) {
        try {
            try {
                ImprimirCbx();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            int convertId = Integer.parseInt(id);

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("idVenta", convertId);

            System.out.println(convertId + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "ticketOpticx.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "ticketOpticx.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void EstablecerImpresora() {

        cbx.removeAllItems();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Tu impresora por default es: " + service.getName());
        for (PrintService printService : services) {
            System.out.println(" ---- IMPRESORA: " + printService.getName());
            if (printService.getName() == service.getName()) {
                cbx.addItem(printService.getName()/* + " *"*/);
            } else {
                cbx.addItem(printService.getName());
            }

        }
        try {
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn2 = cc.conexion();
            PreparedStatement pe = cn2.prepareStatement("SELECT codi_barr FROM impresoras WHERE impr_id=3");
            ResultSet rs = pe.executeQuery();

            if (rs.next()) {
                //          cbx.setSelectedItem(rs.getString("codi_barr"));
                comparaImpresora = rs.getString("codi_barr");
                System.out.println("???????????????????---------------" + comparaImpresora + "--------------?????????????????");
                try {
                    cbx.setSelectedItem(comparaImpresora);
                    numImpre = cbx.getSelectedIndex();
                } catch (Exception ex) {
                    cbx.setSelectedItem(comparaImpresora);
                    numImpre = cbx.getSelectedIndex();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Por el momento no hay impresora guardada elije una predeterminada y oprime guardar");

            }

            cn2.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void ImprimirNota(String id) {
        try {
            try {
                EstablecerImpresora();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            int convertId = Integer.parseInt(id);

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("idVenta", convertId);

            System.out.println(convertId + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "Nota.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "Nota.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void GuardarNota(String id) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            int convertId = Integer.parseInt(id);

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("idVenta", convertId);

            System.out.println(convertId + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "Nota.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "Nota.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void EstablecerImpresoraReporte() {

        cbxReporte.removeAllItems();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Tu impresora por default es: " + service.getName());
        for (PrintService printService : services) {
            System.out.println(" ---- IMPRESORA: " + printService.getName());
            if (printService.getName() == service.getName()) {
                cbxReporte.addItem(printService.getName()/* + " *"*/);
            } else {
                cbxReporte.addItem(printService.getName());
            }

        }
        try {
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn2 = cc.conexion();
            PreparedStatement pe = cn2.prepareStatement("SELECT codi_barr FROM impresoras WHERE impr_id=4");
            ResultSet rs = pe.executeQuery();

            if (rs.next()) {
                //          cbx.setSelectedItem(rs.getString("codi_barr"));
                comparaImpresora = rs.getString("codi_barr");
                System.out.println("???????????????????---------------" + comparaImpresora + "--------------?????????????????");
                try {
                    cbxReporte.setSelectedItem(comparaImpresora);
                    numImpre = cbxReporte.getSelectedIndex();
                } catch (Exception ex) {
                    cbxReporte.setSelectedItem(comparaImpresora);
                    numImpre = cbxReporte.getSelectedIndex();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Por el momento no hay impresora guardada elije una predeterminada y oprime guardar");

            }

            cn2.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void ImprimirReporte(String id) {

        try {
            EstablecerImpresoraReporte();
        } catch (Exception e) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                    + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
        }
        //1. Se compila el reporte
        try {
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            //JOptionPane.showMessageDialog(null, "si quiera conecto");

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", query1);

            System.out.println(query1 + "aqui ezta la cadena que va a jazper*******_______________------------");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "ReporteGeneral.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("ReporteGeneral.pdf"));
                exporter.exportReport();
                // JasperExportManager.exportReportToPdfFile(
                //        jasperPrint, "ReporteGeneral.pdf");
            } catch (JRException e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "!Error¡ Posibles causas: " + nl + "* Verifica que el archivo no esta siendo utilizado por otro programa" + nl + "* Ejecuta la aplicacion como administrador");
                return;
            }
        } catch (Exception i) {
            JOptionPane.showMessageDialog(null, "!Error¡ ni si quiera conecto");
        }
        /*   System.out.println("Done!");
         String nl = System.getProperty("line.separator");
         JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
         */
        //} catch (JRException e) {
        //    e.printStackTrace();
        //    String nl = System.getProperty("line.separator");
        //     JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
        //     System.out.println(e);
        // }
    }

    public void GuardarReporte(String id) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", query1);

            System.out.println(query1 + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "ReporteGeneral.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "ReporteGeneral.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void ImprimirPorFecha(String id) {
        try {
            try {
                EstablecerImpresoraReporte();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta la cadena que va a jazper*******_______________------------por fecha");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOFECHA.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOFECHA.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void GuardarPorFecha(String id) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOFECHA.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOFECHA.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void ImprimirPorEmpleado(String id) {
        try {
            try {
                EstablecerImpresoraReporte();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta la cadena que va a jazper*******_______________------------");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOEMPLEADO.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOEMPLEADO.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void GuardarPorEmpleado(String id) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOEMPLEADO.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOEMPLEADO.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void ImprimirPorCliente(String id) {
        try {
            try {
                EstablecerImpresoraReporte();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta la cadena que va a jazper*******_______________------------");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOCLIENTE.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOCLIENTE.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void GuardarPorCliente(String id) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOCLIENTE.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOCLIENTE.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }

    }

    public void ImprimirPorIdVenta(String id) {
        try {
            try {
                EstablecerImpresoraReporte();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta la cadena que va a jazper*******_______________------------");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOIDVENTA.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOIDVENTA.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void GuardarPorIdVenta(String id) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);

            System.out.println(id + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOIDVENTA.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOIDVENTA.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void ImprimirFechaEntre(String id, String id2) {
        try {
            try {
                EstablecerImpresoraReporte();
            } catch (Exception e) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo obtener información de la impresora" + nl + "Porfavor vaya a Nueva Venta en la parte superior derecha"
                        + nl + "haga click en el icono impresora; seleccione y guarde impresora predeterminada");
            }

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();
            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);
            Map pars2 = new HashMap();
            pars.put("query2", id2);

            System.out.println(id + "aqui ezta la cadena que va a jazper*******_______________------------");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOFECHAENTRE.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOFECHAENTRE.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    public void GuardarFechaEntre(String id, String id2) {
        try {

            //1. Se compila el reporte
            Conexionbd cc = new Conexionbd();
            java.sql.Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("query", id);
            Map pars2 = new HashMap();
            pars.put("query2", id2);

            System.out.println(id + "aqui ezta el id que va a jazper*******");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "REPOFECHAENTRE.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "REPOFECHAENTRE.pdf");
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "verifica que el archivo no esta siendo utilizado por otro programa");
                return;
            }

            /*   System.out.println("Done!");
             String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null, "Reporte creado con éxito " + nl + "Guardado en:" + nl + "escritorio/reportes/CBARRAS.pdf");
             */
        } catch (JRException e) {
            e.printStackTrace();
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! archivo no creado" + nl + "Consulta al administrador");
            System.out.println(e);
        }
    }

    void BotonesReporteVisibles() {
        btnXtodo.setVisible(false);
        btnXfechaEspecifica.setVisible(false);
        btnXtodo.setVisible(false);
        btnXAyer.setVisible(false);
        btnXHoy.setVisible(false);
        btnXEmpleado.setVisible(false);
        btnXCliente.setVisible(false);
        btnXID.setVisible(false);
        btnXFechaEntre.setVisible(false);
        btnCrearReporte.setEnabled(false);
        txtBuscarId.setText("");
        GuardarReporte.setVisible(false);

    }

}
