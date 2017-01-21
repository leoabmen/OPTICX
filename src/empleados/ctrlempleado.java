package empleados;
//Librerias para conectar a sql

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

public class ctrlempleado extends javax.swing.JFrame {

//Variables para conectar
    private java.io.File ruta_origen = null;
    private java.io.File ruta_origen_final = null;
    private java.io.File rutaGuardarImagen = null;
    public static String destino = "ImgEmpleados\\default2.png";
    control_existencias con = new control_existencias();
    public static String ruta_Imagen_Empleado = "";
    String ele;

    public ctrlempleado() {

        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sistema de Gestion de Opticx Ópticas");
        ImageIcon imgEmpleadoDefault = new ImageIcon("ImgEmpleados\\default2.png");
        lblFoto.removeAll();
        lblImag.removeAll();
        lblFoto.setIcon(imgEmpleadoDefault);
        lblImag.setIcon(imgEmpleadoDefault);
        Calendar c2 = new GregorianCalendar();
        jfchFnac.setCalendar(c2);
        pnlImag.setBackground(new java.awt.Color(153, 0, 0));
        chkEsta.setText("EMPLEADO INACTIVO");
        tbEmpleado.setDefaultRenderer(Object.class, new FormatoTablaUsuarios());//color de filas
        Validar();
        Limpiar();
        //CerrarDialogoCargarSeleccionTabla();
        mayusculas(txtAgregarPuesto);
        try {
            mostrardatos("");
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Object[] llenar_combo_puesto = con.combox("puesto_empleado", "PUES_NOMB");

            cbxPues.removeAllItems();
            cbxPues.addItem("SELECCIONA:");
            for (int i = 0; i < llenar_combo_puesto.length; i++) {

                cbxPues.addItem((String) llenar_combo_puesto[i]);

            }

            Object[] llenar_combo_genero = con.combox("sexo", "SEXO_NOMB");

            cbxGene.removeAllItems();
            cbxGene.addItem("SELECCIONA:");
            for (int i = 0; i < llenar_combo_genero.length; i++) {

                cbxGene.addItem((String) llenar_combo_genero[i]);

            }

            // txtnombrepro.setDocument(new Solo_mayusculas()); Ya ni me esta funcionando este metodo ya que al limitar caracteres,
            //afectaba a este metodo y regresaban a minusculas es decir nunca funcionaban juntos.
            obtenerfecha();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error! no se ha podido acceder ala base de datos");
        }
        try {
            tbEmpleado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            ListSelectionModel rowSM = tbEmpleado.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent e) {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    ListSelectionModel lsm
                            = (ListSelectionModel) e.getSource();

                    if (lsm.isSelectionEmpty()) {
                        //fila no seleccionada
                        ruta_Imagen_Empleado = destino;

                    } else {
                        int selectedRow = lsm.getMinSelectionIndex();
                        //fila seleccionada

                        try {
                            int fila = tbEmpleado.getSelectedRow();
                            String id = "";
                            id = tbEmpleado.getValueAt(fila, 0).toString();
                            String[] dato = new String[1];
                            String sql = "SELECT EMPL_IMAG FROM empleados WHERE EMPL_ID='" + id + "'";

                            Conexionbd cc = new Conexionbd();
                            Connection cn = cc.conexion();
                            Statement st = cn.createStatement();
                            ResultSet rs = st.executeQuery(sql);
                            while (rs.next()) {
                                dato[0] = rs.getString(1);
                                ruta_Imagen_Empleado = dato[0];

                                // ruta_Imagen_Empleado=rs.getString(14);
                            }
                        } catch (Exception ex) {
                            ruta_Imagen_Empleado = destino;
                            System.out.println("+++++++++++" + ruta_Imagen_Empleado + "-------------------");

                        }

                        ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
                        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                        System.out.println(ruta_Imagen_Empleado);
                        lblFoto.setIcon(icono);

                    }
                }

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error!");
        }

    }

    void mostrardatos(String valor) throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO PATERNO");
        modelo.addColumn("APELLIDO MATERNO");
        modelo.addColumn("DIRECCIÓN");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO ELECTRÓNICO");
        modelo.addColumn("FECHA DE NACIMIENTO");
        modelo.addColumn("PUESTO EN LA EMPRESA");
        modelo.addColumn("SUELDO");
        modelo.addColumn("GENERO");
        modelo.addColumn("EDAD");
        modelo.addColumn("ESTATUS");

        tbEmpleado.setModel(modelo);
        String sql = "";
        String fotoDefault = "ImgEmpleados\\default2.png";
        if (valor.equals("")) {
            sql = "SELECT EMPL_ID, EMPL_NOMB, EMPL_APAT, EMPL_AMAT, EMPL_DIRE, EMPL_TELE,EMPL_MAIL, EMPL_FNAC, PUES_NOMB,"
                    + " EMPL_SUEL, SEXO_NOMB, EMPL_EDAD, ESTA_NOMB, EMPL_IMAG FROM EMPLEADOS INNER JOIN SEXO INNER JOIN PUESTO_EMPLEADO"
                    + " INNER JOIN ESTATUS_EMPLEADO ON EMPL_SEXO_ID=SEXO_ID AND EMPL_ESTA_ID=ESTA_ID AND EMPL_PUES_ID=PUES_ID";
            String[] datos = new String[14];
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
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);

                    modelo.addRow(datos);
                }
                tbEmpleado.setModel(modelo);
                cn.close();
                ImageIcon imagen = new ImageIcon(fotoDefault);
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));

                lblFoto.setIcon(icono);

            } catch (SQLException ex) {
                Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        } else {
            sql = "SELECT EMPL_ID, EMPL_NOMB, EMPL_APAT, EMPL_AMAT, EMPL_DIRE, EMPL_TELE,EMPL_MAIL, EMPL_FNAC, PUES_NOMB, "
                    + "EMPL_SUEL, SEXO_NOMB, EMPL_EDAD, ESTA_NOMB, EMPL_IMAG FROM EMPLEADOS"
                    + " INNER JOIN SEXO INNER JOIN PUESTO_EMPLEADO INNER JOIN ESTATUS_EMPLEADO ON EMPL_SEXO_ID=SEXO_ID AND EMPL_ESTA_ID=ESTA_ID AND EMPL_PUES_ID=PUES_ID WHERE EMPL_NOMB='" + valor + "'";

            String[] datos = new String[14];
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
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);

                    ruta_Imagen_Empleado = rs.getString(14);

                    modelo.addRow(datos);
                }
                tbEmpleado.setModel(modelo);
                cn.close();
                System.out.println(ruta_Imagen_Empleado);
                ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                System.out.println(ruta_Imagen_Empleado);
                lblFoto.setIcon(icono);
            } catch (SQLException ex) {
                Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ex");
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CargarFoto = new javax.swing.JDialog();
        jfchCargarFoto = new javax.swing.JFileChooser();
        AgregarEmpleado = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNomb = new javax.swing.JTextField();
        txtPate = new javax.swing.JTextField();
        txtMate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDire = new javax.swing.JTextField();
        txtTele = new javax.swing.JTextField();
        txtSuel = new javax.swing.JTextField();
        cbxGene = new javax.swing.JComboBox<String>();
        jLabel14 = new javax.swing.JLabel();
        jfchFnac = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbxPues = new javax.swing.JComboBox<String>();
        txtMail = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblImag = new javax.swing.JLabel();
        btnCambiarImg = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        pnlImag = new javax.swing.JPanel();
        chkEsta = new javax.swing.JCheckBox();
        lblEdad = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnGuardarEmpleado = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        lblIdNomb = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelGuardarPuesto = new javax.swing.JPanel();
        txtAgregarPuesto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Cancelar_catego = new javax.swing.JButton();
        btnGuardarPuesto = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuEliminarPuesto = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        EliminarPuesto = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        Cancelar_catego2 = new javax.swing.JButton();
        B_Eliminar_Puesto = new javax.swing.JButton();
        combo_eliminar_puesto = new javax.swing.JComboBox<String>();
        jLabel21 = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        M_agregar_categoria = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmpleado = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        clavetxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jfchCargarFoto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout CargarFotoLayout = new javax.swing.GroupLayout(CargarFoto.getContentPane());
        CargarFoto.getContentPane().setLayout(CargarFotoLayout);
        CargarFotoLayout.setHorizontalGroup(
            CargarFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jfchCargarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CargarFotoLayout.setVerticalGroup(
            CargarFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CargarFotoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jfchCargarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        AgregarEmpleado.setMinimumSize(new java.awt.Dimension(1030, 543));
        AgregarEmpleado.setType(java.awt.Window.Type.UTILITY);
        AgregarEmpleado.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("NOMBRE:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("APELLIDO PATERNO:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("APELLIDO MATERNO:");

        txtNomb.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNomb.setForeground(new java.awt.Color(0, 0, 153));
        txtNomb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombActionPerformed(evt);
            }
        });
        txtNomb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombKeyReleased(evt);
            }
        });

        txtPate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtPate.setForeground(new java.awt.Color(0, 0, 153));
        txtPate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPateKeyReleased(evt);
            }
        });

        txtMate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtMate.setForeground(new java.awt.Color(0, 0, 153));
        txtMate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMateKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("CORREO ELECTRÓNICO:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("TELEFONO:");

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

        txtSuel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtSuel.setForeground(new java.awt.Color(0, 0, 153));
        txtSuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSuelActionPerformed(evt);
            }
        });

        cbxGene.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbxGene.setForeground(new java.awt.Color(0, 51, 153));
        cbxGene.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("FECHA DE NACIMIENTO:");

        jfchFnac.setForeground(new java.awt.Color(0, 0, 153));
        jfchFnac.setDateFormatString("dd/MM/yyyy");
        jfchFnac.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jfchFnac.setMaximumSize(new java.awt.Dimension(6, 23));
        jfchFnac.setMinimumSize(new java.awt.Dimension(6, 23));
        jfchFnac.setPreferredSize(new java.awt.Dimension(6, 23));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("SUELDO:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("PUESTO:");

        cbxPues.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cbxPues.setForeground(new java.awt.Color(0, 51, 153));
        cbxPues.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));

        txtMail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtMail.setForeground(new java.awt.Color(0, 0, 153));
        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/addPlace.png"))); // NOI18N
        jButton15.setMnemonic('S');
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("AGREGAR PUESTO");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel5)
                        .addGap(135, 135, 135)
                        .addComponent(jLabel6)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(txtNomb, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPate, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtMate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jLabel10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(txtDire, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel9)
                        .addGap(130, 130, 130)
                        .addComponent(jLabel8)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel14))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(txtTele, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfchFnac, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel13))
                            .addComponent(cbxPues, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtSuel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxGene, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel11)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomb, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel10)
                .addGap(6, 6, 6)
                .addComponent(txtDire, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTele, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfchFnac, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(cbxPues, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxGene, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSuel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5))
        );

        AgregarEmpleado.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 750, 320));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("GESTIONAR EMPLEADOS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel16)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(jLabel16))
        );

        AgregarEmpleado.getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 260, 20));

        jPanel7.setMaximumSize(new java.awt.Dimension(128, 128));
        jPanel7.setMinimumSize(new java.awt.Dimension(128, 128));
        jPanel7.setPreferredSize(new java.awt.Dimension(128, 128));

        lblImag.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblImag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImag.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        lblImag.setDoubleBuffered(true);
        lblImag.setMaximumSize(new java.awt.Dimension(134, 159));
        lblImag.setMinimumSize(new java.awt.Dimension(134, 159));
        lblImag.setPreferredSize(new java.awt.Dimension(134, 159));

        btnCambiarImg.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCambiarImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/upload.png"))); // NOI18N
        btnCambiarImg.setMnemonic('I');
        btnCambiarImg.setText("CARGAR IMAGEN");
        btnCambiarImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambiarImg.setIconTextGap(10);
        btnCambiarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarImgActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("EDAD:");

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
            .addComponent(chkEsta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblEdad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEdad.setForeground(new java.awt.Color(0, 0, 153));
        lblEdad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/update3.png"))); // NOI18N
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCambiarImg)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblImag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(pnlImag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblImag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCambiarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlImag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        AgregarEmpleado.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 210, 320));

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
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        AgregarEmpleado.getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 970, 100));

        lblId.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        AgregarEmpleado.getContentPane().add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 40, 20));

        lblIdNomb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblIdNomb.setText("ID:");
        AgregarEmpleado.getContentPane().add(lblIdNomb, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/FONDO CTRL EMPLEADO.png"))); // NOI18N
        AgregarEmpleado.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 540));

        AgregarPuesto.setMinimumSize(new java.awt.Dimension(360, 290));
        AgregarPuesto.setType(java.awt.Window.Type.UTILITY);
        AgregarPuesto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelGuardarPuesto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtAgregarPuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAgregarPuesto.setForeground(new java.awt.Color(0, 0, 102));
        txtAgregarPuesto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAgregarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgregarPuestoActionPerformed(evt);
            }
        });
        txtAgregarPuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgregarPuestoKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("AGREGAR PUESTO EN LA EMPRESA");

        Cancelar_catego.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar_catego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR3.png"))); // NOI18N
        Cancelar_catego.setMnemonic('S');
        Cancelar_catego.setText("SALIR");
        Cancelar_catego.setBorderPainted(false);
        Cancelar_catego.setContentAreaFilled(false);
        Cancelar_catego.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar_catego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar_catego.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR4.png"))); // NOI18N
        Cancelar_catego.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR2.png"))); // NOI18N
        Cancelar_catego.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cancelar_catego.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar_catego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_categoActionPerformed(evt);
            }
        });

        btnGuardarPuesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGuardarPuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar602.png"))); // NOI18N
        btnGuardarPuesto.setMnemonic('G');
        btnGuardarPuesto.setText("GUARDAR");
        btnGuardarPuesto.setBorderPainted(false);
        btnGuardarPuesto.setContentAreaFilled(false);
        btnGuardarPuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPuesto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarPuesto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        btnGuardarPuesto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar601.png"))); // NOI18N
        btnGuardarPuesto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnGuardarPuesto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPuestoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGuardarPuestoLayout = new javax.swing.GroupLayout(jPanelGuardarPuesto);
        jPanelGuardarPuesto.setLayout(jPanelGuardarPuestoLayout);
        jPanelGuardarPuestoLayout.setHorizontalGroup(
            jPanelGuardarPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGuardarPuestoLayout.createSequentialGroup()
                .addGroup(jPanelGuardarPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGuardarPuestoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Cancelar_catego)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardarPuesto))
                    .addGroup(jPanelGuardarPuestoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanelGuardarPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txtAgregarPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelGuardarPuestoLayout.setVerticalGroup(
            jPanelGuardarPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGuardarPuestoLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAgregarPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelGuardarPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_catego)
                    .addComponent(btnGuardarPuesto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AgregarPuesto.getContentPane().add(jPanelGuardarPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 200));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N
        AgregarPuesto.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuBar1.setBackground(new java.awt.Color(102, 0, 204));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("ARCHIVO");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        MenuEliminarPuesto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        MenuEliminarPuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA3.png"))); // NOI18N
        MenuEliminarPuesto.setText("ELIMINAR PUESTO");
        MenuEliminarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEliminarPuestoActionPerformed(evt);
            }
        });
        jMenu1.add(MenuEliminarPuesto);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        AgregarPuesto.setJMenuBar(jMenuBar1);

        EliminarPuesto.setMinimumSize(new java.awt.Dimension(360, 290));
        EliminarPuesto.setType(java.awt.Window.Type.UTILITY);
        EliminarPuesto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 51));
        jLabel20.setText("ELIMINAR PUESTO EN LA EMPRESA");

        Cancelar_catego2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar_catego2.setForeground(new java.awt.Color(204, 0, 51));
        Cancelar_catego2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR3.png"))); // NOI18N
        Cancelar_catego2.setMnemonic('C');
        Cancelar_catego2.setText("CANCELAR");
        Cancelar_catego2.setBorderPainted(false);
        Cancelar_catego2.setContentAreaFilled(false);
        Cancelar_catego2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar_catego2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar_catego2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR4.png"))); // NOI18N
        Cancelar_catego2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR2.png"))); // NOI18N
        Cancelar_catego2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cancelar_catego2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar_catego2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_catego2ActionPerformed(evt);
            }
        });

        B_Eliminar_Puesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Eliminar_Puesto.setForeground(new java.awt.Color(204, 0, 51));
        B_Eliminar_Puesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA2.png"))); // NOI18N
        B_Eliminar_Puesto.setMnemonic('E');
        B_Eliminar_Puesto.setText("ELIMINAR");
        B_Eliminar_Puesto.setBorderPainted(false);
        B_Eliminar_Puesto.setContentAreaFilled(false);
        B_Eliminar_Puesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_Eliminar_Puesto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        B_Eliminar_Puesto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA3.png"))); // NOI18N
        B_Eliminar_Puesto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA1.png"))); // NOI18N
        B_Eliminar_Puesto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        B_Eliminar_Puesto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B_Eliminar_Puesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Eliminar_PuestoActionPerformed(evt);
            }
        });

        combo_eliminar_puesto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combo_eliminar_puesto.setForeground(new java.awt.Color(0, 0, 153));
        combo_eliminar_puesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_eliminar_puesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_eliminar_puestoActionPerformed(evt);
            }
        });
        combo_eliminar_puesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                combo_eliminar_puestoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(Cancelar_catego2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(B_Eliminar_Puesto))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo_eliminar_puesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_eliminar_puesto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_catego2)
                    .addComponent(B_Eliminar_Puesto))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        EliminarPuesto.getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N
        EliminarPuesto.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, 290));

        jMenuBar3.setBackground(new java.awt.Color(102, 0, 204));
        jMenuBar3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setText("ARCHIVO");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        M_agregar_categoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        M_agregar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        M_agregar_categoria.setText("AGREGAR PUESTO");
        M_agregar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_agregar_categoriaActionPerformed(evt);
            }
        });
        jMenu5.add(M_agregar_categoria);

        jMenuBar3.add(jMenu5);
        jMenuBar3.add(jMenu6);

        EliminarPuesto.setJMenuBar(jMenuBar3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 630));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "", "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbEmpleadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbEmpleado);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 1170, 170));

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
                .addGap(8, 8, 8)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 230, 90));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblFoto.setToolTipText("");
        lblFoto.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        lblFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFoto.setMaximumSize(new java.awt.Dimension(134, 159));
        lblFoto.setMinimumSize(new java.awt.Dimension(134, 159));
        lblFoto.setPreferredSize(new java.awt.Dimension(134, 159));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 50, 190, 220));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));

        clavetxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        clavetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavetxtActionPerformed(evt);
            }
        });
        clavetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clavetxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clavetxtKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Coloca el nombre del empleado");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/ELIMINAR/BUSCAR2.png"))); // NOI18N
        jButton3.setText("BUSCAR");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(1);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/ELIMINAR/BUSCAR3.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/ELIMINAR/BUSCAR1.png"))); // NOI18N
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
                    .addComponent(clavetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clavetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 760, 120));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("CONTROL DE EMPLEADOS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 310, 20));

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/COMPUTAR2.png"))); // NOI18N
        jButton7.setText("MOSTRAR REGISTROS");
        jButton7.setAutoscrolls(true);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setIconTextGap(-10);
        jButton7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/COMPUTAR3.png"))); // NOI18N
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/empleados/imagenes/COMPUTAR1.png"))); // NOI18N
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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 170, 140));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/INVENTARIO_PRODUCTOS.png"))); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(1350, 630));
        jLabel3.setMinimumSize(new java.awt.Dimension(1350, 630));
        jLabel3.setPreferredSize(new java.awt.Dimension(1350, 630));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clavetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavetxtActionPerformed

    }//GEN-LAST:event_clavetxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // boton de regreso al menu 2
        Ventanaadmin obj = new Ventanaadmin();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        btnActualizar.setEnabled(false);
        AgregarEmpleado.setModal(true);
        AgregarEmpleado.setLocationRelativeTo(null);
        AgregarEmpleado.setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // boton buscar
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn = cc.conexion();
        try {
            mostrardatos(clavetxt.getText());
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // eliminar
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int fila = tbEmpleado.getSelectedRow();
            String id = "";
            id = tbEmpleado.getValueAt(fila, 0).toString();
            java.sql.Connection cn = cc.conexion();
            int dato = JOptionPane.showConfirmDialog(null, login.Guardausuario + "  ¿Estas seguro de eliminar este registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (dato == 0) {
                try {
                    java.sql.PreparedStatement pst = cn.prepareStatement("DELETE FROM empleados WHERE EMPL_ID='" + id + "'");
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
        btnActualizar.setEnabled(true);
        btnGuardarEmpleado.setEnabled(false);
        modificar();


    }//GEN-LAST:event_jButton4ActionPerformed
    private boolean Validar(int fila, int columna) {

        String valor;

        if (tbEmpleado.getValueAt(fila, columna) == null) {

            return false;

        } else {

            valor = (String) tbEmpleado.getValueAt(fila, columna);

            return true;

        }

    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BOTON LIMPIAR
        clavetxt.setText("");

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn = cc.conexion();
        try {
            mostrardatos("");
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtNombActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombActionPerformed

    private void txtDireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireActionPerformed

    private void txtTeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleActionPerformed

    private void txtSuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSuelActionPerformed

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    private void btnCambiarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarImgActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JPG y PNG", "jpg", "png");

        jfchCargarFoto.setFileFilter(filtro);
        int estado = jfchCargarFoto.showOpenDialog(null);

        if (estado == jfchCargarFoto.APPROVE_OPTION) {
            ruta_origen = jfchCargarFoto.getSelectedFile().getAbsoluteFile();
            String ruta = ruta_origen.getPath();

            if (ruta_origen.exists()) {
                System.out.println("bien");

            } else {
                System.out.println("no bien");
            }
            destino = ("ImgEmpleados/") + jfchCargarFoto.getName(ruta_origen);
            Path FROM = Paths.get(ruta);
            Path TO = Paths.get(destino);
            System.out.println(destino);
            if (this.ruta_origen != null) {
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                try {
                    Files.copy(FROM, TO, options);
                } catch (IOException ex) {
                    Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            try {
                ImageIcon imagen = new ImageIcon(destino);
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                System.out.println(TO);
                System.out.println(destino + "ezte ez el deztino");
                lblImag.setIcon(icono);
            } catch (Exception ex) {

            }
        }

        if (estado == jfchCargarFoto.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_btnCambiarImgActionPerformed

    private void btnGuardarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpleadoActionPerformed
        //boton agregar
        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        String nom, pate, mate, dire, tele, mail, fnac, suel, edad,pues;
        int gene,esta,puesId = 0;
        String formato = "yyyy-MM-dd";
        try {
            Date date = jfchFnac.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fnac = sdf.format(date);

            String sql, sqlIdP = "";

            nom = txtNomb.getText();
            if (nom.length() == 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el nombre del empleado");
                txtNomb.requestFocus();
            } else if (nom.length() < 3) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el nombre del empleado correctamente");
                txtNomb.requestFocus();
            } else {
                pate = txtPate.getText();
                if (pate.length() == 0) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido paterno del empleado");
                    txtPate.requestFocus();
                } else if (pate.length() < 3) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido paterno correctamente");
                    txtPate.requestFocus();
                } else {
                    mate = txtMate.getText();
                    if (mate.length() == 0) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido materno del empleado");
                        txtMate.requestFocus();
                    } else if (mate.length() < 3) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido materno correctamente");
                        txtMate.requestFocus();
                    } else {
                        dire = txtDire.getText();
                        if (dire.length() == 0) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar la dirección del empleado");
                            txtDire.requestFocus();
                        } else if (dire.length() < 8) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar una dirección correcta" + nl + "mínimo 8 caracteres");
                            txtDire.requestFocus();
                        } else {
                            tele = txtTele.getText();
                            if (tele.length() == 0) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el télefono del empleado");
                                txtTele.requestFocus();
                            } else if (tele.length() < 10) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar un número de teléfono correcto");
                                txtTele.requestFocus();
                            } else {
                                  mail = txtMail.getText();
                                  ValidaCorreo(mail);
                                 
                                   if(mail.length() == 0){ 
                                    String nl = System.getProperty("line.separator");
                                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el correo electrónico del empleado" + nl + "si no cuenta con alguno, escribir la palabra '@Cero' o '@ninguno' o simplemente '@' y despues Guardar");
                                    txtMail.requestFocus();
                                   
                                        
                                        
                                   } else {
                                    suel = txtSuel.getText();
                                    if (suel.length() == 0) {
                                        String nl = System.getProperty("line.separator");
                                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el sueldo acordado que se reflejara en la nomina del empleado");
                                        txtSuel.requestFocus();
                                    } else if (suel.length() < 2) {
                                        String nl = System.getProperty("line.separator");
                                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar un sueldo lógico al empleado");
                                        txtSuel.requestFocus();
                                    } else {
                                        pues = cbxPues.getSelectedItem().toString();
                                        sqlIdP = "SELECT PUES_ID FROM puesto_empleado WHERE PUES_NOMB='" + pues + "'";
                                      String[] datosIdNivel = new String[1];
                    try {
                        com.mysql.jdbc.Statement stN = (com.mysql.jdbc.Statement) cn.createStatement();
                        ResultSet rsP = stN.executeQuery(sqlIdP);
                        while (rsP.next()) {
                            datosIdNivel[0] = rsP.getString(1);
                            puesId = Integer.parseInt(datosIdNivel[0]);
                        }

                        System.out.println(puesId + " este ez el id del nivel");

                    } catch (SQLException ex) {
                        Logger.getLogger(MenuUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex);
                    }
                                        if (cbxPues.getSelectedItem().equals("SELECCIONA:")) {
                                            String nl = System.getProperty("line.separator");
                                            JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el puesto del empleado en la empresa");
                                            cbxPues.requestFocus();
                                        } else {
                                            gene = cbxGene.getSelectedIndex();
                                            if (cbxGene.getSelectedItem().equals("SELECCIONA:")) {
                                                String nl = System.getProperty("line.separator");
                                                JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el genero del empleado");
                                                cbxGene.requestFocus();

                                            } else {

                                                System.out.println("Entrada->" + fnac);
                                                DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                                java.util.Date date2 = null;

                                                try {
                                                    date2 = inputFormatter.parse(fnac);
                                                } catch (ParseException pex) {
                                                    pex.printStackTrace();
                                                }

                                                DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                                                String salida = outputFormatter.format(date2);
                                                System.out.println("Salida->" + salida);

                                                edad = restar_fecha(salida);

                                                sql = "INSERT INTO empleados (EMPL_NOMB,EMPL_APAT,EMPL_AMAT,EMPL_DIRE,EMPL_TELE,EMPL_MAIL,EMPL_FNAC,EMPL_PUES_ID,EMPL_SUEL,EMPL_SEXO_ID,EMPL_EDAD,EMPL_ESTA_ID,EMPL_IMAG) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                                                String[] datos = new String[15];
                                                if (datos[0] == null) {
                                                    try {
                                                        PreparedStatement pst = cn.prepareStatement(sql);

                                                        pst.setString(1, nom);
                                                        pst.setString(2, pate);
                                                        pst.setString(3, mate);
                                                        pst.setString(4, dire);
                                                        pst.setString(5, tele);
                                                        pst.setString(6, mail);
                                                        pst.setString(7, fnac);
                                                        pst.setString(8, Integer.toString(puesId));
                                                        pst.setString(9, suel);
                                                        pst.setString(10, Integer.toString(gene));
                                                        pst.setString(11, edad);
                                                        lblEdad.setText(edad);
                                                        if (chkEsta.isSelected()) {

                                                            esta = 1;
                                                        } else {
                                                            esta = 2;
                                                            pnlImag.setBackground(new java.awt.Color(204, 0, 0));
                                                        }
                                                        if (esta == 2) {
                                                            JOptionPane.showMessageDialog(null, "Ha dejado el empleado en su estatus inactivo");
                                                        }

                                                        pst.setString(12, Integer.toString(esta));
                                                        pst.setString(13, destino);

                                                        int n = pst.executeUpdate();

                                                        if (n > 0) {
                                                            JOptionPane.showMessageDialog(null,"Registro guardado con éxito", "About", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src\\empleados\\imagenes\\EmpleadoAgregado.png"));
                                                            try {
                                                                mostrardatos(txtNomb.getText());
                                                                Limpiar();
                                                            } catch (Exception ex) {
                                                                Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            System.out.println(destino);
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

            }
        } catch (NullPointerException ex) {

            JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarEmpleadoActionPerformed

    private void chkEstaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstaActionPerformed
        if (chkEsta.isSelected() == true) {
            pnlImag.setBackground(new java.awt.Color(0, 204, 0));
            chkEsta.setText("EMPLEADO ACTIVO");

        } else {
            pnlImag.setBackground(new java.awt.Color(153, 0, 0));
            chkEsta.setText("EMPLEADO INACTIVO");
        }
    }//GEN-LAST:event_chkEstaActionPerformed

    private void chkEstaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkEstaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEstaKeyReleased

    private void tbEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEmpleadoKeyReleased
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
        tbEmpleado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//Ask to be notified of selection changes.
        ListSelectionModel rowSM = tbEmpleado.getSelectionModel();
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
                    JOptionPane.showMessageDialog(tbEmpleado, "No son molestos los popups?");
                }
            }
        });


    }//GEN-LAST:event_tbEmpleadoKeyReleased

    private void clavetxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clavetxtKeyReleased
        try {

            String nombre;

            if (this.clavetxt != null) {

                nombre = clavetxt.getText().toString();

                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO PATERNO");
                modelo.addColumn("APELLIDO MATERNO");
                modelo.addColumn("DIRECCIÓN");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("CORREO ELECTRÓNICO");
                modelo.addColumn("FECHA DE NACIMIENTO");
                modelo.addColumn("PUESTO EN LA EMPRESA");
                modelo.addColumn("SUELDO");
                modelo.addColumn("GENERO");
                modelo.addColumn("EDAD");
                modelo.addColumn("ESTATUS");

                tbEmpleado.setModel(modelo);
                String sql = "";

                sql = "SELECT EMPL_ID, EMPL_NOMB, EMPL_APAT, EMPL_AMAT, EMPL_DIRE, EMPL_TELE,EMPL_MAIL, EMPL_FNAC, PUES_NOMB, "
                        + "EMPL_SUEL, SEXO_NOMB, EMPL_EDAD, ESTA_NOMB, EMPL_IMAG FROM EMPLEADOS"
                        + " INNER JOIN SEXO INNER JOIN PUESTO_EMPLEADO INNER JOIN ESTATUS_EMPLEADO ON EMPL_SEXO_ID=SEXO_ID AND EMPL_ESTA_ID=ESTA_ID AND EMPL_PUES_ID=PUES_ID WHERE EMPL_NOMB LIKE '%" + nombre + "%'";
                String[] datos = new String[14];
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
                        datos[8] = rs.getString(9);
                        datos[9] = rs.getString(10);
                        datos[10] = rs.getString(11);
                        datos[11] = rs.getString(12);
                        datos[12] = rs.getString(13);
                        // ruta_Imagen_Empleado=rs.getString(14);
                        modelo.addRow(datos);

                    }

                    tbEmpleado.setModel(modelo);
                    ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
                    Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                    System.out.println(ruta_Imagen_Empleado);
                    lblFoto.setIcon(icono);

                } catch (Exception ex) {

                    Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        } catch (Exception e) {

            String nl = System.getProperty("line.separator");

            JOptionPane.showMessageDialog(null, "¡Error! el campo de texto esta vacio" + nl + "Coloque el nombre manualmente");

        }

    }//GEN-LAST:event_clavetxtKeyReleased

    private void clavetxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clavetxtKeyTyped
        /*  char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_BACK_SPACE) {
            ruta_Imagen_Empleado=destino;
            ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
                        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                        
                        lblFoto.setIcon(icono);

           clavetxt.setText("");
           
        }*/
    }//GEN-LAST:event_clavetxtKeyTyped

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String edad;
        String formato = "dd/MM/yyyy";

        Date date = jfchFnac.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fRestar = sdf.format(date);
        edad = restar_fecha(fRestar);
        lblEdad.setText(edad);
        System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}" + fRestar);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        String nom, pate, mate, dire, tele, mail, fnac, suel, edad;
        int gene, pues, esta = 0;
        String formato = "yyyy-MM-dd";

        Date date = jfchFnac.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        fnac = sdf.format(date);
        String sql = "";

        nom = txtNomb.getText();
        if (nom.length() == 0) {
            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el nombre del empleado");
            txtNomb.requestFocus();
        } else {
            pate = txtPate.getText();
            if (pate.length() == 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido paterno del empleado");
                txtPate.requestFocus();
            } else {
                mate = txtMate.getText();
                if (mate.length() == 0) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el apellido materno del empleado");
                    txtMate.requestFocus();
                } else {
                    dire = txtDire.getText();
                    if (dire.length() == 0) {
                        String nl = System.getProperty("line.separator");
                        JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar la dirección del empleado");
                        txtDire.requestFocus();
                    } else {
                        tele = txtTele.getText();
                        if (tele.length() == 0) {
                            String nl = System.getProperty("line.separator");
                            JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el telefono del empleado");
                            txtTele.requestFocus();
                        } else {
                            mail = txtMail.getText();
                            if (mail.length() == 0) {
                                String nl = System.getProperty("line.separator");
                                JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el correo electrónico del empleado" + nl + "si no cuenta con alguno, escribir la palabra 'ninguno' y despues actualizar");
                                txtMail.requestFocus();
                            } else {
                                suel = txtSuel.getText();
                                if (suel.length() == 0) {
                                    String nl = System.getProperty("line.separator");
                                    JOptionPane.showMessageDialog(null, "¡Error! Debe ingresar el sueldo acordado que se reflejara en su nomina");
                                    txtSuel.requestFocus();
                                } else {
                                    pues = cbxPues.getSelectedIndex();
                                    if (cbxPues.getSelectedItem().equals("SELECCIONA:")) {
                                        String nl = System.getProperty("line.separator");
                                        JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el puesto del empleado en la empresa");
                                        cbxPues.requestFocus();
                                    } else {
                                        gene = cbxGene.getSelectedIndex();
                                        if (cbxGene.getSelectedItem().equals("SELECCIONA:")) {
                                            String nl = System.getProperty("line.separator");
                                            JOptionPane.showMessageDialog(null, "¡Error! Debe seleccionar el genero del empleado");
                                            cbxGene.requestFocus();

                                        } else {

                                            System.out.println("Entrada->" + fnac);
                                            DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                            java.util.Date date2 = null;

                                            try {
                                                date2 = inputFormatter.parse(fnac);
                                            } catch (ParseException pex) {
                                                pex.printStackTrace();
                                            }

                                            DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                                            String salida = outputFormatter.format(date2);
                                            System.out.println("Salida->" + salida);

                                            edad = restar_fecha(salida);

                                            String[] datos = new String[15];

                                            try {
                                                if (chkEsta.isSelected()) {

                                                    esta = 1;
                                                } else {
                                                    esta = 2;
                                                    pnlImag.setBackground(new java.awt.Color(204, 0, 0));
                                                }
                                                if (esta == 2) {
                                                    JOptionPane.showMessageDialog(null, "Ha dejado el empleado en su estatus inactivo");
                                                }
                                                System.out.println(destino + "********************update deztino*******antezzzzzzz********************+");
                                                PreparedStatement pst;
                                                pst = cn.prepareStatement("UPDATE empleados SET EMPL_NOMB='" + nom + "',EMPL_APAT='" + pate + "',EMPL_AMAT='" + mate + "',EMPL_DIRE='" + dire + "',EMPL_TELE='" + tele + "',EMPL_MAIL='" + mail + "',"
                                                        + "EMPL_FNAC='" + fnac + "',EMPL_PUES_ID='" + pues + "',EMPL_SUEL='" + suel + "',EMPL_SEXO_ID='" + gene + "',EMPL_EDAD='" + edad + "',EMPL_ESTA_ID='" + esta + "',EMPL_IMAG='" + destino + "' WHERE EMPL_ID='" + lblId.getText() + "'");
                                                int n = pst.executeUpdate();
                                                if (n > 0) {
                                                    JOptionPane.showMessageDialog(null, "Registro modificado con éxito");
                                                    System.out.println(destino + "********************update deztino***************************+");
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

        }
        try {
            mostrardatosPorId(ele);
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        //supuestamente debería de elegir automaticamente la celda del elemento que trae a id pero no lo hace
        /* tbEmpleado.getUpdateSelectionOnSort();
        for (int i = 0; i < tbEmpleado.getRowCount(); i++) {
            if (tbEmpleado.getValueAt(i, 1).equals(ele)) {
                tbEmpleado.changeSelection(i, 1, true, true);
                break;
            }
        }*/
        System.out.println(ele + "ezte ez ele");

        btnGuardarEmpleado.setEnabled(true);

        Limpiar();
        System.out.println(destino);
        destino = "";
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Limpiar();
        AgregarEmpleado.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtNombKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombKeyReleased
        LetraCapital(evt, txtNomb);
    }//GEN-LAST:event_txtNombKeyReleased

    private void txtPateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPateKeyReleased
        LetraCapital(evt, txtPate);
    }//GEN-LAST:event_txtPateKeyReleased

    private void txtMateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMateKeyReleased
        LetraCapital(evt, txtMate);
    }//GEN-LAST:event_txtMateKeyReleased

    private void txtAgregarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgregarPuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgregarPuestoActionPerformed

    private void txtAgregarPuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarPuestoKeyTyped
        char teclapresionada=evt.getKeyChar();

        if(teclapresionada==KeyEvent.VK_ENTER){

            btnGuardarPuesto.doClick();
        }
    }//GEN-LAST:event_txtAgregarPuestoKeyTyped

    private void Cancelar_categoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_categoActionPerformed
        AgregarPuesto.dispose();
    }//GEN-LAST:event_Cancelar_categoActionPerformed

    private void btnGuardarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPuestoActionPerformed
        try{
            Conexionbd cc =new Conexionbd();
            Connection cn= cc.conexion();
            String categoria;

            String sql="";

            categoria=txtAgregarPuesto.getText();

            if(categoria.length()==0){
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null,"¡Error! El campo de texto esta vacío."+nl + "Por favor ingrese el nombre de la categoria que desea registrar.");
                txtAgregarPuesto.requestFocus();
            }
            else{

                sql="INSERT INTO puesto_empleado (PUES_NOMB) VALUES (?)";

                try{
                    PreparedStatement pst=cn.prepareStatement(sql);

                    pst.setString(1,categoria);

                    int n=pst.executeUpdate();

                    if(n>0){
                        JOptionPane.showMessageDialog(null,"Registro guardado con éxito");
                        txtAgregarPuesto.setText("");
                        txtAgregarPuesto.requestFocus();
                        Object[] llenar_combo_Pues = con.combox("puesto_empleado","PUES_NOMB");

                        cbxPues.removeAllItems();
                        cbxPues.addItem("SELECCIONA:");

                        for(int i=0;i<llenar_combo_Pues.length;i++){

                            cbxPues.addItem((String) llenar_combo_Pues[i]);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"¡Error! no se pudo guardar el registro");

                    }

                }catch (Exception e){

                    JOptionPane.showMessageDialog(null,"¡Error! no ha insertado correctamente los datos");

                }

            }
        }catch (Exception e){

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null,"¡Error! no ha sido posible conectar ala base de datos");

        }

    }//GEN-LAST:event_btnGuardarPuestoActionPerformed

    private void MenuEliminarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEliminarPuestoActionPerformed

        try{
            Object[] llenar_combo_categoria_producto = con.combox("puesto_empleado","PUES_NOMB");

            combo_eliminar_puesto.removeAllItems();
            cbxPues.addItem("SELECCIONA:");

            for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                combo_eliminar_puesto.addItem((String) llenar_combo_categoria_producto[i]);
            }

            AgregarPuesto.dispose();
            EliminarPuesto.setModal(true);
            EliminarPuesto.setLocationRelativeTo(null);
            EliminarPuesto.setVisible(true);

        }catch (Exception e){

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null,"¡Error! no ha sido posible conectar ala base de datos");

        }
    }//GEN-LAST:event_MenuEliminarPuestoActionPerformed

    private void Cancelar_catego2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_catego2ActionPerformed

        EliminarPuesto.dispose();
    }//GEN-LAST:event_Cancelar_catego2ActionPerformed

    private void B_Eliminar_PuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Eliminar_PuestoActionPerformed
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn= cc.conexion();
        String nl = System.getProperty("line.separator");
        int dato=JOptionPane.showConfirmDialog(null,login.Guardausuario +"  ¿Estas seguro de eliminar este regístro?"+nl+"Esta acción eliminará a los empleados que están dentro de esta categoría."+
             nl+"Para más información consulte su manual de usuario o a su administrador"," Alerta!", JOptionPane.YES_NO_OPTION);
        if (dato==0){
            try{
                String cat=combo_eliminar_puesto.getSelectedItem().toString();

                try {
                    PreparedStatement pst = cn.prepareStatement("DELETE FROM puesto_empleado WHERE PUES_NOMB='"+cat+"'");
                    pst.executeUpdate();

                    Object[] llenar_combo_categoria_producto = con.combox("puesto_empleado","PUES_NOMB");

                    combo_eliminar_puesto.removeAllItems();
                    cbxPues.addItem("SELECCIONA:");

                    for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                        combo_eliminar_puesto.addItem((String) llenar_combo_categoria_producto[i]);
                    }

                    Object[] llenar_combo_eliminar_pu = con.combox("puesto_empleado","PUES_NOMB");

                    cbxPues.removeAllItems();
                    cbxPues.addItem("SELECCIONA:");

                    for(int i=0;i<llenar_combo_eliminar_pu.length;i++){

                        cbxPues.addItem((String) llenar_combo_eliminar_pu[i]);
                    }

                } catch (Exception e) {
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"¡Error! no ha seleccionado ningun puesto de la lista");

            }
        }
    }//GEN-LAST:event_B_Eliminar_PuestoActionPerformed

    private void combo_eliminar_puestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_eliminar_puestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_eliminar_puestoActionPerformed

    private void combo_eliminar_puestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_eliminar_puestoKeyTyped
        char teclapresionada=evt.getKeyChar();

        if(teclapresionada==KeyEvent.VK_ENTER){

            B_Eliminar_Puesto.doClick();
        }
    }//GEN-LAST:event_combo_eliminar_puestoKeyTyped

    private void M_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_agregar_categoriaActionPerformed
        EliminarPuesto.dispose();
        AgregarPuesto.setModal(true);
        AgregarPuesto.setLocationRelativeTo(null);
        AgregarPuesto.setVisible(true);
    }//GEN-LAST:event_M_agregar_categoriaActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        AgregarPuesto.setModal(true);
        AgregarPuesto.setLocationRelativeTo(null);
        AgregarPuesto.setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed
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
                new ctrlempleado().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AgregarEmpleado;
    public static final javax.swing.JDialog AgregarPuesto = new javax.swing.JDialog();
    private javax.swing.JButton B_Eliminar_Puesto;
    private javax.swing.JButton Cancelar_catego;
    private javax.swing.JButton Cancelar_catego2;
    private javax.swing.JDialog CargarFoto;
    private javax.swing.JDialog EliminarPuesto;
    private javax.swing.JMenuItem M_agregar_categoria;
    private javax.swing.JMenuItem MenuEliminarPuesto;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCambiarImg;
    private javax.swing.JButton btnGuardarEmpleado;
    private javax.swing.JButton btnGuardarPuesto;
    private javax.swing.JComboBox<String> cbxGene;
    private javax.swing.JComboBox<String> cbxPues;
    private javax.swing.JCheckBox chkEsta;
    private javax.swing.JTextField clavetxt;
    private javax.swing.JComboBox<String> combo_eliminar_puesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGuardarPuesto;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JFileChooser jfchCargarFoto;
    private com.toedter.calendar.JDateChooser jfchFnac;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdNomb;
    private javax.swing.JLabel lblImag;
    private javax.swing.JPanel pnlImag;
    private javax.swing.JTable tbEmpleado;
    private javax.swing.JTextField txtAgregarPuesto;
    private javax.swing.JTextField txtDire;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtMate;
    private javax.swing.JTextField txtNomb;
    private javax.swing.JTextField txtPate;
    private javax.swing.JTextField txtSuel;
    private javax.swing.JTextField txtTele;
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
        int fila = tbEmpleado.getSelectedRow();
        int columna = tbEmpleado.getSelectedRow();
        String tomarfecha;
        int esta;
        if (fila >= 0) {
            if (tbEmpleado.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {
                ele = tbEmpleado.getValueAt(fila, 0).toString();
                lblId.setText(tbEmpleado.getValueAt(fila, 0).toString());
                txtNomb.setText(tbEmpleado.getValueAt(fila, 1).toString());
                txtPate.setText(tbEmpleado.getValueAt(fila, 2).toString());
                txtMate.setText(tbEmpleado.getValueAt(fila, 3).toString());
                txtDire.setText(tbEmpleado.getValueAt(fila, 4).toString());

                txtTele.setText(tbEmpleado.getValueAt(fila, 5).toString());
                txtMail.setText(tbEmpleado.getValueAt(fila, 6).toString());
                tomarfecha = tbEmpleado.getValueAt(fila, 7).toString();
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
                jfchFnac.setDate(date);

                txtSuel.setText(tbEmpleado.getValueAt(fila, 9).toString());

                try {
                    Object[] combo_llenar_puesto = con.combox("puesto_empleado", "PUES_NOMB");
                    cbxPues.removeAllItems();
                    cbxPues.addItem("SELECCIONA:");
                    for (int i = 0; i < combo_llenar_puesto.length; i++) {
                        cbxPues.addItem((String) combo_llenar_puesto[i]);
                    }
                } catch (Exception e) {
                    Object[] combo_llenar_puesto = con.combox("puesto_empleado", "PUES_NOMB");
                    cbxPues.removeAllItems();
                    cbxPues.addItem("SELECCIONA:");
                    for (int i = 0; i < combo_llenar_puesto.length; i++) {
                        cbxPues.addItem((String) combo_llenar_puesto[i]);
                    }
                }

                cbxPues.setSelectedItem(tbEmpleado.getValueAt(fila, 8).toString());
                try {
                    Object[] combo_llenar_sexo = con.combox("sexo", "SEXO_NOMB");
                    cbxGene.removeAllItems();
                    cbxGene.addItem("SELECCIONA:");
                    for (int i = 0; i < combo_llenar_sexo.length; i++) {
                        cbxGene.addItem((String) combo_llenar_sexo[i]);
                    }
                } catch (Exception e) {
                    Object[] combo_llenar_sexo = con.combox("sexo", "SEXO_NOMB");
                    cbxGene.removeAllItems();
                    cbxGene.addItem("SELECCIONA:");
                    for (int i = 0; i < combo_llenar_sexo.length; i++) {
                        cbxGene.addItem((String) combo_llenar_sexo[i]);
                    }
                }

                cbxGene.setSelectedItem(tbEmpleado.getValueAt(fila, 10).toString());
                lblEdad.setText(tbEmpleado.getValueAt(fila, 11).toString());
                ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                System.out.println(ruta_Imagen_Empleado + "------------------------------------------------------------------------");
                lblImag.setIcon(icono);
                destino = ruta_Imagen_Empleado;

                String chkEleccion = tbEmpleado.getValueAt(fila, 12).toString();
                if (chkEleccion.equals("ACTIVO")) {
                    chkEsta.setSelected(true);
                    chkEsta.setText("EMPLEADO ACTIVO");
                    pnlImag.setBackground(new java.awt.Color(0, 204, 0));

                } else {
                    chkEsta.setSelected(false);
                    chkEsta.setText("EMPLEADO INACTIVO");
                    pnlImag.setBackground(new java.awt.Color(204, 0, 0));
                }

                AgregarEmpleado.setModal(true);
                AgregarEmpleado.setLocationRelativeTo(null);
                AgregarEmpleado.setVisible(true);

            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
    }

    void Limpiar() {
        txtNomb.setText("");
        txtPate.setText("");
        txtMate.setText("");
        txtDire.setText("");
        txtTele.setText("");
        txtMail.setText("");
        Calendar c2 = new GregorianCalendar();
        jfchFnac.setCalendar(c2);
        cbxPues.setSelectedItem("SELECCIONA:");
        txtSuel.setText("");
        cbxGene.setSelectedItem("SELECCIONA:");
        lblEdad.setText("");
        lblId.setText("");
        lblId.setVisible(false);
        lblIdNomb.setVisible(false);
        chkEsta.setSelected(false);
        chkEsta.setText("EMPLEADO INACTIVO");
        pnlImag.setBackground(new java.awt.Color(204, 0, 0));
        String fotoDefault = "ImgEmpleados\\default2.png";
        ImageIcon imagen = new ImageIcon(fotoDefault);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));

        lblImag.setIcon(icono);

    }

    public void Validar() {
        RestrictedTextField restricted0 = new RestrictedTextField(txtNomb);
        restricted0.setLimit(30);
        RestrictedTextField restricted1 = new RestrictedTextField(txtPate);
        restricted1.setLimit(30);
        RestrictedTextField restricted2 = new RestrictedTextField(txtMate);
        restricted2.setLimit(30);
        RestrictedTextField restricted3 = new RestrictedTextField(txtDire);
        restricted3.setLimit(60);
        RestrictedTextField restricted4 = new RestrictedTextField(txtTele);
        restricted4.setLimit(15);
        RestrictedTextField restricted5 = new RestrictedTextField(txtMail);
        restricted5.setLimit(50);
        RestrictedTextField restricted6 = new RestrictedTextField(txtSuel);
        restricted6.setLimit(8);
        sololetras(txtNomb);
        sololetras(txtPate);
        sololetras(txtMate);
        solo_numeros.solonumeros(txtSuel);
        soloNumeros(txtTele);

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
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO PATERNO");
        modelo.addColumn("APELLIDO MATERNO");
        modelo.addColumn("DIRECCIÓN");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CORREO ELECTRÓNICO");
        modelo.addColumn("FECHA DE NACIMIENTO");
        modelo.addColumn("PUESTO EN LA EMPRESA");
        modelo.addColumn("SUELDO");
        modelo.addColumn("GENERO");
        modelo.addColumn("EDAD");
        modelo.addColumn("ESTATUS");

        tbEmpleado.setModel(modelo);
        String sql = "";
        String fotoDefault = "ImgEmpleados\\default2.png";
        if (valor.equals("")) {
            sql = "SELECT EMPL_ID, EMPL_NOMB, EMPL_APAT, EMPL_AMAT, EMPL_DIRE, EMPL_TELE,EMPL_MAIL, EMPL_FNAC, PUES_NOMB,"
                    + " EMPL_SUEL, SEXO_NOMB, EMPL_EDAD, ESTA_NOMB, EMPL_IMAG FROM EMPLEADOS INNER JOIN SEXO INNER JOIN PUESTO_EMPLEADO"
                    + " INNER JOIN ESTATUS_EMPLEADO ON EMPL_SEXO_ID=SEXO_ID AND EMPL_ESTA_ID=ESTA_ID AND EMPL_PUES_ID=PUES_ID";
            String[] datos = new String[14];
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
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);

                    modelo.addRow(datos);
                }
                tbEmpleado.setModel(modelo);
                cn.close();
                ImageIcon imagen = new ImageIcon(fotoDefault);
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));

                lblFoto.setIcon(icono);

            } catch (SQLException ex) {
                Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        } else {
            sql = "SELECT EMPL_ID, EMPL_NOMB, EMPL_APAT, EMPL_AMAT, EMPL_DIRE, EMPL_TELE,EMPL_MAIL, EMPL_FNAC, PUES_NOMB, "
                    + "EMPL_SUEL, SEXO_NOMB, EMPL_EDAD, ESTA_NOMB, EMPL_IMAG FROM EMPLEADOS"
                    + " INNER JOIN SEXO INNER JOIN PUESTO_EMPLEADO INNER JOIN ESTATUS_EMPLEADO ON EMPL_SEXO_ID=SEXO_ID AND EMPL_ESTA_ID=ESTA_ID AND EMPL_PUES_ID=PUES_ID WHERE EMPL_ID='" + valor + "'";

            String[] datos = new String[14];
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
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);

                    ruta_Imagen_Empleado = rs.getString(14);

                    modelo.addRow(datos);
                }
                tbEmpleado.setModel(modelo);
                cn.close();
                System.out.println(ruta_Imagen_Empleado);
                ImageIcon imagen = new ImageIcon(ruta_Imagen_Empleado);
                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.ALIGN_CENTER));
                System.out.println(ruta_Imagen_Empleado);
                lblFoto.setIcon(icono);
            } catch (SQLException ex) {
                Logger.getLogger(ctrlempleado.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        }
    }

    void CerrarDialogoCargarSeleccionTabla() {
        AgregarEmpleado.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

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
     
 
    
   void ValidaCorreo(String correo){
       
       int longitud = correo.length();
    char valor;
    
    for(char i = 0; i < longitud; i++)
    {
    valor = correo.charAt(i);
        if(valor == '@' && i == 0){
        System.out.println("El correo esta mal");
        
        break;
        }
    	if(valor == '@')
    	{
    	System.out.println("Escribistes perfectamente el correo");
    	break;
    	}
    	else if(i == longitud-1){
    	 System.out.println("El correo esta mal");	
         JOptionPane.showMessageDialog(null, "Correo Inválido");
    	 break;
         
    		
    	}
    	
    	
      }
		
   }
   

}
