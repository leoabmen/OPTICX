package Clientes;
//Librerias para conectar a sql

import empleados.*;
import Atxy2k.CustomTextField.RestrictedTextField;
import ae.java.awt.Graphics2D;
import ae.java.awt.image.BufferedImage;
import com.itextpdf.text.Image;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import opticx1.Ventanaadmin;
import opticx1.Conexionbd;
import empleados.Nuevoempleado;
import static empleados.Nuevoempleado.txtsexo;
import static inventario.Agregar_Producto.calendariofin;
import jxl.read.biff.File;
import opticx1.login;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JLabel;
import java.text.DateFormat;
import Atxy2k.CustomTextField.RestrictedTextField;
import ExportarArchivo.LlamarPdf;
import Pacientes.Historia_clinica;
import Usuarios.MenuUsuarios;
import com.itextpdf.text.pdf.codec.CCITTG4Encoder;
import empleados.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import opticx1.Ventanaadmin;
import opticx1.Conexionbd;
import empleados.Nuevoempleado;
import static empleados.Nuevoempleado.txtsexo;
import static inventario.modificar_Producto.calendarioM;
import static inventario.modificar_Producto.combocategorian;
import static inventario.modificar_Producto.combomarcan;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.System.gc;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import opticx1.control_existencias;
import opticx1.login;
import validaciones.Limitar_caracteres;
import static inventario.Agregar_Producto.combocategoriafin;
import static inventario.Agregar_Producto.combomarcafin;
import static inventario.Agregar_Producto.txtid_profin;
import static inventario.Agregar_Producto.txtstockprofin;
import static inventario.Agregar_Producto.txtnombreprofin;
import static inventario.Agregar_Producto.txtcolorprofin;
import static inventario.Agregar_Producto.txtdescripcionprofin;
import static inventario.Agregar_Producto.calendariofin;
import static inventario.Agregar_Producto.txtpreciocompraprofin;
import static inventario.Agregar_Producto.txtprecioventaprofin;
import static inventario.modificarProducto.calendariomp;
import static inventario.modificarProducto.comboc;
import static inventario.modificarProducto.combom;
import static inventario.modificarProducto.txtastok;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.batik.gvt.filter.BackgroundRable8Bit;
import org.apache.log4j.BasicConfigurator;
import org.apache.xalan.xsltc.util.IntegerArray;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import jxl.format.Pattern;
import validaciones.solo_numeros;

public class Clientes extends javax.swing.JFrame {

    control_existencias con = new control_existencias();

    public Clientes() {

        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sistema de Gestion de Opticx Ópticas");
        pnlImag.setBackground(new java.awt.Color(153, 0, 0));
        chkEsta.setText("CLIENTE INACTIVO");
        tblCliente.setDefaultRenderer(Object.class, new FormatoTablaClientes());
        Validar();
        Limpiar();
        // CerrarDialogoCargarSeleccionTabla();
        try {
            mostrardatos("");
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Object[] llenar_combo_genero = con.combox("sexo", "SEXO_NOMB");
            cbxGenero.removeAllItems();
            cbxGenero.addItem("SELECCIONA:");
            for (int i = 0; i < llenar_combo_genero.length; i++) {

                cbxGenero.addItem((String) llenar_combo_genero[i]);
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error! no se ha podido acceder ala base de datos para cargar la lista");
        }
        obtenerfecha();
    }

    void mostrardatos(String valor) throws Exception {
        String tomarfecha;
        int[] anchos = {10, 60, 60, 60, 125, 35, 35, 60, 30, 30, 10, 25, 30};
        // Object[] newRow={"1", "2", "3", "4","5","6","7","8","9","10"};
        DefaultTableModel modelo = new DefaultTableModel();
       // modelo = new DefaultTableModel(newRow, 20);

        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("A.PATERNO");
        modelo.addColumn("A.MATERNO");
        modelo.addColumn("DIRECCIÓN");
        modelo.addColumn("TEL. CASA");
        modelo.addColumn("CELULAR");
        modelo.addColumn("CORREO");
        modelo.addColumn("FECHA DE REGISTRO");
        modelo.addColumn("GENERO");
        modelo.addColumn("EDAD");
        modelo.addColumn("ESTATUS");
        modelo.addColumn("OCUPACIÓN");
        tblCliente.setModel(modelo);
        for (int i = 0; i < tblCliente.getColumnCount(); i++) {
            tblCliente.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT PACI_ID,PACI_NOMB, PACI_PATE, PACI_MATE, PACI_DIRE,PACI_TELE_CASA, PACI_TELE_CELU, PACI_MAIL,"
                    + " PACI_FECH_REGI, SEXO_NOMB, PACI_EDAD, ESTA_PACI_NOMB, PACI_OCUP FROM PACIENTE INNER JOIN SEXO INNER JOIN ESTATUS_PACIENTE INNER JOIN CATEGORIA_PACIENTE"
                    + " ON PACI_SEXO_ID=SEXO_ID AND PACI_ESTA_ID=ESTA_PACI_ID AND PACI_CATE_ID=CATE_ID WHERE CATE_NOMB='CLIENTE'";
            String[] datos = new String[15];
            try {
                Conexionbd cc = new Conexionbd();
                Connection cn = cc.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    datos[5] = rs.getString(6);
                    datos[6] = rs.getString(7);
                    datos[7] = rs.getString(8);
                    tomarfecha = rs.getString(9);
                    System.out.println("Entrada->" + tomarfecha);

                    DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = null;

                    try {
                        date = inputFormatter.parse(tomarfecha);
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }

                    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    String salida = outputFormatter.format(date);
                    System.out.println("Salida->" + salida);
                    datos[8] = salida;
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);

                    modelo.addRow(datos);
                }
                tblCliente.setModel(modelo);
                cn.close();

            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        } else {
            sql = "SELECT PACI_ID,PACI_NOMB, PACI_PATE, PACI_MATE, PACI_DIRE,PACI_TELE_CASA, PACI_TELE_CELU, PACI_MAIL,"
                    + " PACI_FECH_REGI, SEXO_NOMB, PACI_EDAD, ESTA_PACI_NOMB, PACI_OCUP FROM PACIENTE INNER JOIN SEXO INNER JOIN ESTATUS_PACIENTE INNER JOIN CATEGORIA_PACIENTE"
                    + " ON PACI_SEXO_ID=SEXO_ID AND PACI_ESTA_ID=ESTA_PACI_ID AND PACI_CATE_ID=CATE_ID WHERE CATE_NOMB='CLIENTE' AND concat_ws(' ', PACI_NOMB, PACI_PATE, PACI_MATE) like'%" + valor + "%'";

            String[] datos = new String[15];
            try {
                Conexionbd cc = new Conexionbd();
                Connection cn = cc.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    datos[5] = rs.getString(6);
                    datos[6] = rs.getString(7);
                    datos[7] = rs.getString(8);
                    tomarfecha = rs.getString(9);
                    System.out.println("Entrada->" + tomarfecha);

                    DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = null;

                    try {
                        date = inputFormatter.parse(tomarfecha);
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }

                    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    String salida = outputFormatter.format(date);
                    System.out.println("Salida->" + salida);
                    datos[8] = salida;
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);

                    modelo.addRow(datos);
                }
                tblCliente.setModel(modelo);
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AgregarCliente = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPaternoCliente = new javax.swing.JTextField();
        txtMaternoCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDire = new javax.swing.JTextField();
        txtTele = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<String>();
        jLabel14 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtTeleCasa = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        pnlImag = new javax.swing.JPanel();
        chkEsta = new javax.swing.JCheckBox();
        lblEstatusCliente = new javax.swing.JLabel();
        txtOcupacion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblTituloCliente = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnGuardarEmpleado = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblIdNomb = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jlabelCategoria1 = new javax.swing.JLabel();
        lblCategoriaCliente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        AgregarCliente.setMinimumSize(new java.awt.Dimension(1030, 543));
        AgregarCliente.setType(java.awt.Window.Type.UTILITY);
        AgregarCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText(" NOMBRE:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("APELLIDO PATERNO:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("APELLIDO MATERNO:");

        txtPaternoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtPaternoCliente.setForeground(new java.awt.Color(0, 0, 153));
        txtPaternoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaternoClienteKeyReleased(evt);
            }
        });

        txtMaternoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtMaternoCliente.setForeground(new java.awt.Color(0, 0, 153));
        txtMaternoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaternoClienteKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("CORREO ELECTRÓNICO:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("TELEFONO DE CASA:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("DIRECCIÓN:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("GENERO:");

        txtDire.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtDire.setForeground(new java.awt.Color(0, 0, 153));
        txtDire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireActionPerformed(evt);
            }
        });

        txtTele.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTele.setForeground(new java.awt.Color(0, 0, 153));
        txtTele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeleActionPerformed(evt);
            }
        });

        cbxGenero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbxGenero.setForeground(new java.awt.Color(0, 51, 153));
        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("TELÉFONO MOVIL:");

        txtMail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtMail.setForeground(new java.awt.Color(0, 0, 153));
        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        txtFirstName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtFirstName.setForeground(new java.awt.Color(0, 0, 153));
        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });
        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyReleased(evt);
            }
        });

        txtTeleCasa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTeleCasa.setForeground(new java.awt.Color(0, 0, 153));
        txtTeleCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeleCasaActionPerformed(evt);
            }
        });

        txtEdad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(0, 0, 153));
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("EDAD:");

        pnlImag.setBackground(new java.awt.Color(153, 0, 0));
        pnlImag.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkEsta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        chkEsta.setForeground(new java.awt.Color(51, 51, 51));
        chkEsta.setText("EMPLEADO ACTIVO");
        chkEsta.setHideActionText(true);
        chkEsta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstaActionPerformed(evt);
            }
        });
        chkEsta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chkEstaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlImagLayout = new javax.swing.GroupLayout(pnlImag);
        pnlImag.setLayout(pnlImagLayout);
        pnlImagLayout.setHorizontalGroup(
            pnlImagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chkEsta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
        pnlImagLayout.setVerticalGroup(
            pnlImagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chkEsta, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        lblEstatusCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEstatusCliente.setText("ESTATUS DE CLIENTE:");

        txtOcupacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtOcupacion.setForeground(new java.awt.Color(0, 0, 153));
        txtOcupacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOcupacionActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("OCUPACIÓN:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTeleCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTele, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(pnlImag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(lblEstatusCliente)))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPaternoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaternoCliente)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtDire, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOcupacion)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(125, 125, 125))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaternoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaternoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDire, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOcupacion, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstatusCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTele, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTeleCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlImag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        AgregarCliente.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 970, 250));

        lblTituloCliente.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTituloCliente.setText("CLIENTES");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(lblTituloCliente)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblTituloCliente)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        AgregarCliente.getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 260, 20));

        jButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/exit2.png"))); // NOI18N
        jButton10.setMnemonic('S');
        jButton10.setText("SALIR");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setIconTextGap(20);
        jButton10.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/exit3.png"))); // NOI18N
        jButton10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/exit1.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/update2.png"))); // NOI18N
        btnActualizar.setMnemonic('A');
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setToolTipText("");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setIconTextGap(20);
        btnActualizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/update3.png"))); // NOI18N
        btnActualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/update1.png"))); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnGuardarEmpleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/add2.png"))); // NOI18N
        btnGuardarEmpleado.setMnemonic('G');
        btnGuardarEmpleado.setText("GUARDAR");
        btnGuardarEmpleado.setToolTipText("");
        btnGuardarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarEmpleado.setIconTextGap(20);
        btnGuardarEmpleado.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/add3.png"))); // NOI18N
        btnGuardarEmpleado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/add1.png"))); // NOI18N
        btnGuardarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(btnGuardarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        AgregarCliente.getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 970, 120));

        lblId.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        AgregarCliente.getContentPane().add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 40, 40));

        lblFechaCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFechaCliente.setText("FECHA:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("FECHA DE REGISTRO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(lblFechaCliente)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblFechaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        AgregarCliente.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 260, 30));

        lblIdNomb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblIdNomb.setText("ID:");
        AgregarCliente.getContentPane().add(lblIdNomb, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

        jlabelCategoria1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlabelCategoria1.setText("CATEGORÍA:");

        lblCategoriaCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCategoriaCliente.setText("aquí");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(jlabelCategoria1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCategoriaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jlabelCategoria1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblCategoriaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        AgregarCliente.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/FONDO CTRL EMPLEADO.png"))); // NOI18N
        AgregarCliente.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 540));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1025, 650));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCliente.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "A. PATERNO", "A.MATERNO", "DIRECCIÓN", "FECHA DE REGISTRO", "null", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13"
            }
        ));
        tblCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClienteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);
        if (tblCliente.getColumnModel().getColumnCount() > 0) {
            tblCliente.getColumnModel().getColumn(0).setMinWidth(10);
            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblCliente.getColumnModel().getColumn(0).setMaxWidth(10);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 1280, 280));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS2.png"))); // NOI18N
        jButton1.setText("ATRAS");
        jButton1.setAutoscrolls(true);
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS3.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS1.png"))); // NOI18N
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ADD2.png"))); // NOI18N
        jButton2.setText("AGREGAR NUEVO");
        jButton2.setAutoscrolls(true);
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setIconTextGap(1);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ADD3.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ADD1.png"))); // NOI18N
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 270, 90));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));

        txtBuscarCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarClienteActionPerformed(evt);
            }
        });
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Coloca el nombre del empleado");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/limpiar/limpiar2.png"))); // NOI18N
        jButton6.setText("LIMPIAR");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setIconTextGap(1);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/limpiar/limpiar3.png"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/limpiar/limpiar1.png"))); // NOI18N
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/MODIFICAR/modif2.png"))); // NOI18N
        jButton4.setText("MODIFICAR");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setIconTextGap(1);
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/MODIFICAR/modif3.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/MODIFICAR/modif1.png"))); // NOI18N
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/ELIMINAR/BASURA2.png"))); // NOI18N
        jButton5.setText("ELIMINAR");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setIconTextGap(1);
        jButton5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/ELIMINAR/BASURA3.png"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/ELIMINAR/BASURA1.png"))); // NOI18N
        jButton5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(52, 52, 52)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 730, 120));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("CONTROL DE CLIENTES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 280, 20));

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Clientes/img/COMPUTAR2.png"))); // NOI18N
        jButton7.setText("MOSTRAR REGISTROS");
        jButton7.setAutoscrolls(true);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setIconTextGap(-10);
        jButton7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Clientes/img/COMPUTAR3.png"))); // NOI18N
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Clientes/img/COMPUTAR1.png"))); // NOI18N
        jButton7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 180, 140));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/fondos grandes/fondoadminmenu1.png"))); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(1025, 650));
        jLabel3.setMinimumSize(new java.awt.Dimension(1025, 650));
        jLabel3.setPreferredSize(new java.awt.Dimension(1025, 650));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1320, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed

    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // boton de regreso al menu 2
        Ventanaadmin obj = new Ventanaadmin();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String formato = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechafin = sdf.format(date);
        System.out.println(fechafin);
        lblFechaCliente.setText(fechafin);
        lblIdNomb.setVisible(false);
        lblCategoriaCliente.setVisible(true);
        lblCategoriaCliente.setText("CLIENTE");
        btnActualizar.setEnabled(false);
        btnGuardarEmpleado.setEnabled(true);
        AgregarCliente.setModal(true);
        AgregarCliente.setLocationRelativeTo(null);
        AgregarCliente.setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // eliminar
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int fila = tblCliente.getSelectedRow();
            String id = "";
            id = tblCliente.getValueAt(fila, 0).toString();
            java.sql.Connection cn = cc.conexion();
            int dato = JOptionPane.showConfirmDialog(null, login.Guardausuario + "  ¿Estas seguro de eliminar este registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (dato == 0) {
                try {
                    java.sql.PreparedStatement pst = cn.prepareStatement("DELETE FROM PACIENTE WHERE PACI_ID='" + id + "'");
                    pst.executeUpdate();
                    mostrardatos("");
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ningun ID de la lista de abajo");

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //MODIFICAR
        lblId.setVisible(true);
        lblIdNomb.setVisible(true);
        lblCategoriaCliente.setVisible(true);
        lblCategoriaCliente.setText("CLIENTE");
        btnActualizar.setEnabled(true);
        btnGuardarEmpleado.setEnabled(false);
        modificar();


    }//GEN-LAST:event_jButton4ActionPerformed
    private boolean Validar(int fila, int columna) {

        String valor;

        if (tblCliente.getValueAt(fila, columna) == null) {

            return false;

        } else {

            valor = (String) tblCliente.getValueAt(fila, columna);

            return true;

        }

    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BOTON LIMPIAR
        txtBuscarCliente.setText("");
        txtBuscarCliente.requestFocus();
        try {
            mostrardatos("");
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn = cc.conexion();
        try {
            mostrardatos("");
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtDireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireActionPerformed

    private void txtTeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleActionPerformed

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    private void btnGuardarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpleadoActionPerformed
        //boton agregar
        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        String nom, pate, mate, dire, tele, mail, fnac, suel, edad, lastName, teleCasa,ocupacion;
        int gene, esta, puesId = 0;
        String formato = "yyyy-MM-dd";
        try {
            fnac = lblFechaCliente.getText();
            if (txtTeleCasa.equals(null)) {
                teleCasa = "";
            } else {
                teleCasa = txtTeleCasa.getText();
            }
    
            if (txtOcupacion.equals(null)) {
                ocupacion = "";
            } else {
                ocupacion = txtOcupacion.getText();
            }

            String sql, sqlIdP = "";

            nom = txtFirstName.getText();
            if (nom.length() == 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el nombre del cliente");
                txtFirstName.requestFocus();
            } else if (nom.length() < 3) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el nombre del cliente correctamente");
                txtFirstName.requestFocus();
            } else {
                pate = txtPaternoCliente.getText();
                if (pate.length() == 0) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido paterno del cliente");
                    txtPaternoCliente.requestFocus();
                } else if (pate.length() < 3) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido paterno correctamente");
                    txtPaternoCliente.requestFocus();
                } else {
                    mate = txtMaternoCliente.getText();
                    if (mate.length() == 0) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido materno del cliente");
                        txtMaternoCliente.requestFocus();
                    } else if (mate.length() < 3) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido materno correctamente");
                        txtMaternoCliente.requestFocus();
                    } else {
                        dire = txtDire.getText();
                        if (dire.length() == 0) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar la dirección del cliente");
                            txtDire.requestFocus();
                        } else if (dire.length() < 8) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar una dirección correcta" + nl + "mínimo 8 caracteres");
                            txtDire.requestFocus();
                        } else {
                            tele = txtTele.getText();
                            if (tele.length() == 0) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el télefono del cliente");
                                txtTele.requestFocus();
                            } else if (tele.length() < 10) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar un número de teléfono correcto");
                                txtTele.requestFocus();
                            } else {
                                mail = txtMail.getText();
                                ValidaCorreo(mail);

                                if (mail.length() == 0) {
                                    String nl = System.getProperty("line.separator");
                                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el correo electrónico del cliente" + nl + "si no cuenta con alguno, escribir la palabra '@Cero' o '@ninguno' o simplemente '@' y despues Guardar");
                                    txtMail.requestFocus();

                                } else {
                                    edad = txtEdad.getText();
                                    if (edad.length() == 0) {
                                        String nl = System.getProperty("line.separator");
                                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar la edad aproximada del cliente");
                                        txtEdad.requestFocus();
                                    } else {
                                        gene = cbxGenero.getSelectedIndex();
                                        if (cbxGenero.getSelectedItem().equals("SELECCIONA:")) {
                                            String nl = System.getProperty("line.separator");
                                            JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el genero del cliente");
                                            cbxGenero.requestFocus();

                                        } else {

                                            System.out.println("Entrada->" + fnac);
                                            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                                            java.util.Date date2 = null;

                                            try {
                                                date2 = inputFormatter.parse(fnac);
                                            } catch (ParseException pex) {
                                                pex.printStackTrace();
                                            }

                                            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                            String salida = outputFormatter.format(date2);
                                            System.out.println("Salida->" + salida);
                                            fnac = salida;

                                            sql = "INSERT INTO PACIENTE (PACI_NOMB,PACI_PATE,PACI_MATE,PACI_DIRE,PACI_TELE_CASA,PACI_TELE_CELU,PACI_MAIL,PACI_FECH_REGI,PACI_SEXO_ID,PACI_EDAD,PACI_ESTA_ID,PACI_CATE_ID,PACI_OCUP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                                            String[] datos = new String[12];
                                            if (datos[0] == null) {
                                                try {
                                                    PreparedStatement pst = cn.prepareStatement(sql);

                                                    pst.setString(1, nom);
                                                    pst.setString(2, pate);
                                                    pst.setString(3, mate);
                                                    pst.setString(4, dire);
                                                    pst.setString(5, teleCasa);
                                                    pst.setString(6, tele);
                                                    pst.setString(7, mail);
                                                    pst.setString(8, fnac);
                                                    pst.setString(9, Integer.toString(gene));
                                                    pst.setString(10, edad);
                                                    if (chkEsta.isSelected()) {

                                                        esta = 1;
                                                    } else {
                                                        esta = 2;
                                                        pnlImag.setBackground(new java.awt.Color(204, 0, 0));
                                                    }
                                                    if (esta == 2) {
                                                        JOptionPane.showMessageDialog(null, "Ha dejado el cliente en su estatus inactivo");
                                                    }

                                                    pst.setString(11, Integer.toString(esta));

                                                    if (lblCategoriaCliente.getText() == "PACIENTE") {
                                                        pst.setString(12, Integer.toString(1));
                                                    } else {
                                                        pst.setString(12, Integer.toString(2));
                                                    }
                                                    pst.setString(13, ocupacion);

                                                    int n = pst.executeUpdate();

                                                    if (n > 0) {
                                                        JOptionPane.showMessageDialog(null, "Registro guardado con éxito", "Alerta", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\empleados\\imagenes\\EmpleadoAgregado.png"));
                                                        try {
                                                            if (lblCategoriaCliente.getText() == "PACIENTE") {
                                                                 Historia_clinica hc=new Historia_clinica();
                                                                 AgregarCliente.dispose();
                                                                 hc.setVisible(true);
                                                                 hc.ColocarPaciente();
                                                                 Limpiar();
                                                            }
                                                            else{
                                                            mostrardatos(txtFirstName.getText() + " " + txtPaternoCliente.getText() + " " + txtMaternoCliente.getText());
                                                            Limpiar();
                                                            }
                                                        } catch (Exception ex) {
                                                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "¡Error! no se pudo guardar el registro");

                                                    }

                                                } catch (Exception e) {
                                                    JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");
                                                    System.out.println(e);
                                                }

                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnGuardarEmpleadoActionPerformed

    private void chkEstaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstaActionPerformed
        if (chkEsta.isSelected() == true) {
            pnlImag.setBackground(new java.awt.Color(0, 204, 0));
            chkEsta.setText("CLIENTE ACTIVO");

        } else {
            pnlImag.setBackground(new java.awt.Color(153, 0, 0));
            chkEsta.setText("CLIENTE INACTIVO");
        }
    }//GEN-LAST:event_chkEstaActionPerformed

    private void chkEstaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkEstaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEstaKeyReleased

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        String nombre;

        if (this.txtBuscarCliente != null) {

            nombre = txtBuscarCliente.getText().toString();
            try {
                mostrardatos(nombre);
            } catch (Exception ex) {
                Logger.getLogger(MenuUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void txtBuscarClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyTyped
        /*  char teclapresionada = evt.getKeyChar();

         if (teclapresionada == KeyEvent.VK_BACK_SPACE) {
         ruta_Imagen_Empleado=destino;
         ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
         Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                        
         lblFoto.setIcon(icono);

         clavetxt.setText("");
           
         }*/
    }//GEN-LAST:event_txtBuscarClienteKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        String nom, pate, mate, dire, tele, mail, fnac, suel, edad, lastName, teleCasa,ocupacion;
        int gene, pues, esta = 0;
        String formato = "yyyy-MM-dd";

        fnac = lblFechaCliente.getText();
        if (txtTeleCasa.equals(null)) {
            teleCasa = "";
        } else {
            teleCasa = txtTeleCasa.getText();
        }
        if (txtOcupacion.equals(null)) {
            ocupacion = "";
        } else {
            ocupacion = txtOcupacion.getText();
        }
        String sql = "";

        nom = txtFirstName.getText();
        if (nom.length() == 0) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el nombre del cliente");
            txtFirstName.requestFocus();
        } else {
            pate = txtPaternoCliente.getText();
            if (pate.length() == 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido paterno del cliente");
                txtPaternoCliente.requestFocus();
            } else {
                mate = txtMaternoCliente.getText();
                if (mate.length() == 0) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido materno del cliente");
                    txtMaternoCliente.requestFocus();
                } else {
                    dire = txtDire.getText();
                    if (dire.length() == 0) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar la dirección del cliente");
                        txtDire.requestFocus();
                    } else {
                        tele = txtTele.getText();
                        if (tele.length() == 0) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el telefono del cliente");
                            txtTele.requestFocus();
                        } else {
                            mail = txtMail.getText();
                            if (mail.length() == 0) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el correo electrónico del cliente" + nl + "si no cuenta con alguno, escribir la palabra 'ninguno' y despues actualizar");
                                txtMail.requestFocus();
                            } else {
                                edad = txtEdad.getText();
                                if (edad.length() == 0) {
                                    String nl = System.getProperty("line.separator");
                                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar la edad del cliente");
                                    txtEdad.requestFocus();
                                } else {
                                    gene = cbxGenero.getSelectedIndex();
                                    if (cbxGenero.getSelectedItem().equals("SELECCIONA:")) {
                                        String nl = System.getProperty("line.separator");
                                        JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el genero del cliente");
                                        cbxGenero.requestFocus();

                                    } else {

                                        System.out.println("Entrada->" + fnac);
                                        DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                                        java.util.Date date2 = null;

                                        try {
                                            date2 = inputFormatter.parse(fnac);
                                        } catch (ParseException pex) {
                                            pex.printStackTrace();
                                        }

                                        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                        String salida = outputFormatter.format(date2);
                                        System.out.println("Salida->" + salida);

                                        fnac = salida;

                                        String[] datos = new String[15];

                                        try {
                                            if (chkEsta.isSelected()) {

                                                esta = 1;
                                            } else {
                                                esta = 2;
                                                pnlImag.setBackground(new java.awt.Color(204, 0, 0));
                                            }
                                            if (esta == 2) {
                                                JOptionPane.showMessageDialog(null, "Ha dejado el cliente en su estatus inactivo");
                                            }
                                            PreparedStatement pst;
                                            pst = cn.prepareStatement("UPDATE paciente SET PACI_NOMB='" + nom + "',PACI_PATE='" + pate + "',PACI_MATE='" + mate + "',PACI_DIRE='" + dire + "',PACI_TELE_CASA='" + teleCasa + "',PACI_OCUP='" + ocupacion + "',"
                                                    + "PACI_TELE_CELU='" + tele + "',PACI_MAIL='" + mail + "',PACI_FECH_REGI='" + fnac + "',PACI_SEXO_ID='" + gene + "',PACI_EDAD='" + edad + "',PACI_ESTA_ID='" + esta + "' WHERE PACI_ID='" + lblId.getText() + "'");
                                            int n = pst.executeUpdate();
                                            if (n > 0) {
                                                JOptionPane.showMessageDialog(null, "Registro modificado con éxito");
                                                 if (lblCategoriaCliente.getText() == "PACIENTE") {
                                                                 Historia_clinica hc=new Historia_clinica();
                                                                 AgregarCliente.dispose();
                                                                 hc.setVisible(true);
                                                                 hc.ColocarPaciente();
                                                                 Limpiar();
                                                                 this.dispose();
                                                            }
                                                 else
                                                mostrardatosPorId(lblId.getText());
                                            } else {
                                                JOptionPane.showMessageDialog(null, "¡Error! no se pudo modificar el registro");
                                            }

                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");
                                            System.out.println(e);
                                        }

                                    }
                                }
                            }
                        }

                    }
                }
            }

        }
        try {
            // mostrardatosPorId(ele);
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //supuestamente debería de elegir automaticamente la celda del elemento que trae a id pero no lo hace
        /* tbEmpleado.getUpdateSelectionOnSort();
         for (int i = 0; i < tbEmpleado.getRowCount(); i++) {
         if (tbEmpleado.getValueAt(i, 1).equals(ele)) {
         tbEmpleado.changeSelection(i, 1, true, true);
         break;
         }
         }*/

        btnGuardarEmpleado.setEnabled(true);

        Limpiar();

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Limpiar();
        lblFechaCliente.setText("");
        AgregarCliente.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtPaternoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoClienteKeyReleased
        LetraCapital(evt, txtPaternoCliente);
    }//GEN-LAST:event_txtPaternoClienteKeyReleased

    private void txtMaternoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaternoClienteKeyReleased
        LetraCapital(evt, txtMaternoCliente);
    }//GEN-LAST:event_txtMaternoClienteKeyReleased

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed

    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyReleased
        LetraCapital(evt, txtFirstName);
    }//GEN-LAST:event_txtFirstNameKeyReleased

    private void txtTeleCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleCasaActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void tblClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClienteKeyReleased
        /*    tbEmpleado.addKeyListener( new KeyAdapter() {
         @Override
         public void keyReleased( KeyEvent e ) {
         if( tbEmpleado.getSelectedRows().length > 0 ) {
         ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
         Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
         System.out.println(ruta_Imagen_Empleado);
         lblFoto.setIcon(icono);
         }
         }
         });
         */
        tblCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Ask to be notified of selection changes.
        ListSelectionModel rowSM = tblCliente.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) {
                    return;
                }

                ListSelectionModel lsm
                        = (ListSelectionModel) e.getSource();

                if (lsm.isSelectionEmpty()) {
                    //no rows are selected
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    //selectedRow is selected
                    JOptionPane.showMessageDialog(tblCliente, "No son molestos los popups?");
                }
            }
        });
    }//GEN-LAST:event_tblClienteKeyReleased

    private void txtOcupacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOcupacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOcupacionActionPerformed
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
            java.util.logging.Logger.getLogger(Ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDialog AgregarCliente;
    public static javax.swing.JButton btnActualizar;
    public static javax.swing.JButton btnGuardarEmpleado;
    public javax.swing.JComboBox<String> cbxGenero;
    public javax.swing.JCheckBox chkEsta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel jlabelCategoria1;
    public static javax.swing.JLabel lblCategoriaCliente;
    public javax.swing.JLabel lblEstatusCliente;
    public static final javax.swing.JLabel lblFechaCliente = new javax.swing.JLabel();
    public javax.swing.JLabel lblId;
    public javax.swing.JLabel lblIdNomb;
    public javax.swing.JLabel lblTituloCliente;
    public javax.swing.JPanel pnlImag;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtBuscarCliente;
    public javax.swing.JTextField txtDire;
    public javax.swing.JTextField txtEdad;
    public javax.swing.JTextField txtFirstName;
    public javax.swing.JTextField txtMail;
    public javax.swing.JTextField txtMaternoCliente;
    public javax.swing.JTextField txtOcupacion;
    public javax.swing.JTextField txtPaternoCliente;
    public javax.swing.JTextField txtTele;
    public javax.swing.JTextField txtTeleCasa;
    // End of variables declaration//GEN-END:variables
 public String restar_fecha(String fnac) {
        String fechaInicio = fnac;
        String fechaActual = "09/01/2016";
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        fechaActual = df.format(date);
        String[] aFechaIng = fechaInicio.split("/");
        Integer diaInicio = Integer.parseInt(aFechaIng[0]);
        Integer mesInicio = Integer.parseInt(aFechaIng[1]);
        Integer anioInicio = Integer.parseInt(aFechaIng[2]);

        String[] aFecha = fechaActual.split("/");
        Integer diaActual = Integer.parseInt(aFecha[0]);
        Integer mesActual = Integer.parseInt(aFecha[1]);
        Integer anioActual = Integer.parseInt(aFecha[2]);

        System.out.println(diaActual);
        System.out.println(mesActual);
        System.out.println(anioActual);
        int b = 0;
        int dias = 0;
        int mes = 0;
        int anios = 0;
        int meses = 0;
        mes = mesInicio - 1;
        if (mes == 2) {
            if ((anioActual % 4 == 0) && ((anioActual % 100 != 0) || (anioActual % 400 == 0))) {
                b = 29;
            } else {
                b = 28;
            }
        } else if (mes <= 7) {
            if (mes == 0) {
                b = 31;
            } else if (mes % 2 == 0) {
                b = 30;
            } else {
                b = 31;
            }
        } else if (mes > 7) {
            if (mes % 2 == 0) {
                b = 31;
            } else {
                b = 30;
            }
        }
        if ((anioInicio > anioActual) || (anioInicio == anioActual && mesInicio > mesActual)
                || (anioInicio == anioActual && mesInicio == mesActual && diaInicio > diaActual)) {
            System.out.println("La fecha de inicio debe ser anterior a la fecha Actual");
        } else if (mesInicio <= mesActual) {
            anios = anioActual - anioInicio;
            if (diaInicio <= diaActual) {
                meses = mesActual - mesInicio;
                dias = b - (diaInicio - diaActual);
            } else {
                if (mesActual == mesInicio) {
                    anios = anios - 1;
                }
                meses = (mesActual - mesInicio - 1 + 12) % 12;
                dias = b - (diaInicio - diaActual);
            }
        } else {
            anios = anioActual - anioInicio - 1;
            System.out.println(anios);
            if (diaInicio > diaActual) {
                meses = mesActual - mesInicio - 1 + 12;
                dias = b - (diaInicio - diaActual);
            } else {
                meses = mesActual - mesInicio + 12;
                dias = diaActual - diaInicio;
            }
        }
        String a, m, d;

        System.out.println("Años: " + anios);
        System.out.println("Meses: " + meses);
        System.out.println("Días: " + dias);
        a = Integer.toString(anios);
        m = Integer.toString(meses);
        d = Integer.toString(dias);
        return a + " Años, " + m + " meses, " + d + " dias";
    }

    public static void obtenerfecha() {
        //Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema

        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        System.out.println("Fecha Actual: " + dia + "/" + (mes + 1) + "/" + año);

        System.out.printf("Hora Actual: %02d:%02d:%02d %n", hora, minuto, segundo);

    }

    public void modificar() {
        int fila = tblCliente.getSelectedRow();
        int columna = tblCliente.getSelectedRow();
        String tomarfecha;
        int esta;
        if (fila >= 0) {
            if (tblCliente.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {

                lblId.setText(tblCliente.getValueAt(fila, 0).toString());
                txtFirstName.setText(tblCliente.getValueAt(fila, 1).toString());
                txtPaternoCliente.setText(tblCliente.getValueAt(fila, 2).toString());
                txtMaternoCliente.setText(tblCliente.getValueAt(fila, 3).toString());
                txtDire.setText(tblCliente.getValueAt(fila, 4).toString());
                txtOcupacion.setText(tblCliente.getValueAt(fila, 12).toString());
                txtTeleCasa.setText(tblCliente.getValueAt(fila, 5).toString());
                txtTele.setText(tblCliente.getValueAt(fila, 6).toString());
                txtMail.setText(tblCliente.getValueAt(fila, 7).toString());
                tomarfecha = tblCliente.getValueAt(fila, 8).toString();
                System.out.println("Entrada->" + tomarfecha);

                DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = null;

                try {
                    date = inputFormatter.parse(tomarfecha);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                String salida = outputFormatter.format(date);
                System.out.println("Salida->" + salida);

                lblFechaCliente.setText(salida);
                txtEdad.setText(tblCliente.getValueAt(fila, 10).toString());
                try {
                    Object[] combo_llenar_sexo = con.combox("sexo", "SEXO_NOMB");
                    cbxGenero.removeAllItems();
                    cbxGenero.addItem("SELECCIONA:");
                    for (int i = 0; i < combo_llenar_sexo.length; i++) {
                        cbxGenero.addItem((String) combo_llenar_sexo[i]);
                    }
                } catch (Exception e) {
                    Object[] combo_llenar_sexo = con.combox("sexo", "SEXO_NOMB");
                    cbxGenero.removeAllItems();
                    cbxGenero.addItem("SELECCIONA:");
                    for (int i = 0; i < combo_llenar_sexo.length; i++) {
                        cbxGenero.addItem((String) combo_llenar_sexo[i]);
                    }
                }

                cbxGenero.setSelectedItem(tblCliente.getValueAt(fila, 9).toString());

                String chkEleccion = tblCliente.getValueAt(fila, 11).toString();
                if (chkEleccion.equals("ACTIVO")) {
                    chkEsta.setSelected(true);
                    chkEsta.setText("CLIENTE ACTIVO");
                    pnlImag.setBackground(new java.awt.Color(0, 204, 0));

                } else {
                    chkEsta.setSelected(false);
                    chkEsta.setText("CLIENTE INACTIVO");
                    pnlImag.setBackground(new java.awt.Color(204, 0, 0));
                }

                AgregarCliente.setModal(true);
                AgregarCliente.setLocationRelativeTo(null);
                AgregarCliente.setVisible(true);

            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
    }

    void Limpiar() {
        txtPaternoCliente.setText("");
        txtMaternoCliente.setText("");
        txtDire.setText("");
        txtOcupacion.setText("");
        txtTele.setText("");
        txtMail.setText("");
        txtFirstName.setText("");
        txtTeleCasa.setText("");
        txtEdad.setText("");
        cbxGenero.setSelectedItem("SELECCIONA:");
        lblId.setText("");
        lblId.setVisible(false);
        lblIdNomb.setVisible(false);
        lblCategoriaCliente.setVisible(true);
        chkEsta.setSelected(false);
        chkEsta.setText("CLIENTE INACTIVO");
        pnlImag.setBackground(new java.awt.Color(204, 0, 0));
        String formato = "dd/MM/yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechafin = sdf.format(date);
        lblFechaCliente.setText(fechafin);
    }

    public void Validar() {
        RestrictedTextField restricted = new RestrictedTextField(txtFirstName);
        restricted.setLimit(30);
        RestrictedTextField restricted1 = new RestrictedTextField(txtPaternoCliente);
        restricted1.setLimit(30);
        RestrictedTextField restricted2 = new RestrictedTextField(txtMaternoCliente);
        restricted2.setLimit(30);
        RestrictedTextField restricted3 = new RestrictedTextField(txtDire);
        restricted3.setLimit(60);
        RestrictedTextField restrictedTeleCasa = new RestrictedTextField(txtTeleCasa);
        restrictedTeleCasa.setLimit(15);
        RestrictedTextField restricted4 = new RestrictedTextField(txtTele);
        restricted4.setLimit(15);
        RestrictedTextField restricted5 = new RestrictedTextField(txtMail);
        restricted5.setLimit(50);
        RestrictedTextField restricted6 = new RestrictedTextField(txtEdad);
        restricted6.setLimit(3);
        sololetras(txtFirstName);
        sololetras(txtMaternoCliente);
        sololetras(txtPaternoCliente);
        solo_numeros.solonumeros(txtEdad);
        soloNumeros(txtTele);
        soloNumeros(txtTeleCasa);

    }

    public void LetraCapital(java.awt.event.KeyEvent evt, JTextField a) {
        a = (JTextField) evt.getComponent();

        String texto = a.getText();//.trim();//para evitar espacios en blanco antes y despues del texto
        if (texto.length() > 0) {
            char primero = texto.charAt(0);
            texto = Character.toUpperCase(primero) + texto.substring(1, texto.length());
            a.setText(texto);
        }
    }

    public void sololetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    getToolkit().beep();
                    e.consume();

                }

            }

        });
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

    void mostrardatosPorId(String valor) throws Exception {
        String tomarfecha;
        int[] anchos = {10, 60, 60, 60, 125, 35, 35, 60, 30, 30, 10, 25, 30};
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("A.PATERNO");
        modelo.addColumn("A.MATERNO");
        modelo.addColumn("DIRECCIÓN");
        modelo.addColumn("TEL. CASA");
        modelo.addColumn("CELULAR");
        modelo.addColumn("CORREO");
        modelo.addColumn("FECHA DE REGISTRO");
        modelo.addColumn("GENERO");
        modelo.addColumn("EDAD");
        modelo.addColumn("ESTATUS");
        modelo.addColumn("OCUPACIÓN");

        tblCliente.setModel(modelo);
        for (int i = 0; i < tblCliente.getColumnCount(); i++) {
            tblCliente.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "";
        sql = "SELECT PACI_ID,PACI_NOMB, PACI_PATE, PACI_MATE, PACI_DIRE,PACI_TELE_CASA, PACI_TELE_CELU, PACI_MAIL,"
                + " PACI_FECH_REGI, SEXO_NOMB, PACI_EDAD, ESTA_PACI_NOMB, PACI_OCUP FROM PACIENTE INNER JOIN SEXO INNER JOIN ESTATUS_PACIENTE INNER JOIN CATEGORIA_PACIENTE"
                + " ON PACI_SEXO_ID=SEXO_ID AND PACI_ESTA_ID=ESTA_PACI_ID AND PACI_CATE_ID=CATE_ID WHERE CATE_NOMB='CLIENTE' AND PACI_ID='" + valor + "'";

        String[] datos = new String[15];
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = cc.conexion();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                tomarfecha = rs.getString(9);
                System.out.println("Entrada->" + tomarfecha);

                DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = null;

                try {
                    date = inputFormatter.parse(tomarfecha);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                String salida = outputFormatter.format(date);
                System.out.println("Salida->" + salida);
                datos[8] = salida;
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                datos[12] = rs.getString(13);

                modelo.addRow(datos);
            }
            tblCliente.setModel(modelo);
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

    }

    void CerrarDialogoCargarSeleccionTabla() {
        AgregarCliente.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                otro();
            }

        });
    }

    void otro() {
        JOptionPane.showMessageDialog(null, "dezea zalir");
        String ele = lblId.getText();

    }

    void ValidaCorreo(String correo) {

        int longitud = correo.length();
        char valor;

        for (char i = 0; i < longitud; i++) {
            valor = correo.charAt(i);
            if (valor == '@' && i == 0) {
                System.out.println("El correo esta mal");

                break;
            }
            if (valor == '@') {
                System.out.println("Escribistes perfectamente el correo");
                break;
            } else if (i == longitud - 1) {
                System.out.println("El correo esta mal");
                JOptionPane.showMessageDialog(null, "Correo Inválido");
                break;

            }

        }

    }

}
