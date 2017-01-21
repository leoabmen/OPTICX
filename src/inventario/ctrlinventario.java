package inventario;

import Atxy2k.CustomTextField.RestrictedTextField;
import ExportarArchivo.LlamarPdf;
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
import java.io.File;
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
import opticx1.VentanaUsuario;
import static opticx1.VentanaUsuario.invi;
import static opticx1.login.invitado;
import org.apache.log4j.BasicConfigurator;

public class ctrlinventario extends javax.swing.JFrame {

    control_existencias con = new control_existencias();
    String comparaImpresora;
    int numImpre;
    public static String clave = "";
    public ctrlinventario() {
        initComponents();

        this.setLocationRelativeTo(null);

        setResizable(false);

        setTitle("Sistema de Gestion de Opticx Ópticas");

        CBX_SELECCIONAR_BUSQUEDA.setSelectedItem("SELECCIONA:");

        ayudafecha.setText("");

        ayudanombre.setText("");
        clavetxt.requestFocus();

        Limitar_caracteres_cajastexto();

        mayusculas(pornombre);

        mayusculas(clavetxt);
        mayusculas(txtbuxca);

        soloNumeros_stock(ztoka);
        soloNumeros_stock(ztoka2);

    }

    private boolean Validar(int fila, int columna) {
        String valor;
        if (tbproducto.getValueAt(fila, columna) == null) {

            return false;
        } else {

            valor = (String) tbproducto.getValueAt(fila, columna);

            return true;

        }
    }

    public void eliminar() {

        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        try {

            int fila = tbproducto.getSelectedRow();

            String clave = "";
            clave = tbproducto.getValueAt(fila, 0).toString();
            int dato = JOptionPane.showConfirmDialog(null, login.Guardausuario + "  ¿Estas seguro de eliminar este registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (dato == 0) {
                try {
                    PreparedStatement pst = cn.prepareStatement("DELETE FROM productos WHERE id_producto='" + clave + "'");
                    pst.executeUpdate();
                    mostrardatos("");
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ningun ID de la lista de abajo");
        }

    }

    public void modificar() {
        int fila = tbproducto.getSelectedRow();
        int columna = tbproducto.getSelectedRow();
        String tomarfecha;
        if (fila >= 0) {
            if (tbproducto.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {

                modificarProducto.lblid.setText(tbproducto.getValueAt(fila, 0).toString());
                modificarProducto.txtnom.setText(tbproducto.getValueAt(fila, 1).toString());
                modificarProducto.txtcolor.setText(tbproducto.getValueAt(fila, 2).toString());

                modificarProducto.txtpc.setText(tbproducto.getValueAt(fila, 4).toString());
                modificarProducto.txtpv.setText(tbproducto.getValueAt(fila, 5).toString());

                tomarfecha = tbproducto.getValueAt(fila, 6).toString();
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
                calendariomp.setDate(date);

                modificarProducto.lblstok.setText(tbproducto.getValueAt(fila, 7).toString());
                modificarProducto.txtdes.setText(tbproducto.getValueAt(fila, 9).toString());

                modificarProducto obj = null;
                try {
                    obj = new modificarProducto();
                    try {
                        Object[] combo_llenar_categoria = con.combox("categoria_productos", "categoria_producto");
                        comboc.removeAllItems();
                        for (int i = 0; i < combo_llenar_categoria.length; i++) {
                            comboc.addItem((String) combo_llenar_categoria[i]);
                        }
                    } catch (Exception e) {
                        Object[] combo_llenar_categoria = con.combox("categoria_productos", "categoria_producto");
                        comboc.removeAllItems();
                        for (int i = 0; i < combo_llenar_categoria.length; i++) {
                            comboc.addItem((String) combo_llenar_categoria[i]);
                        }
                    }
                    comboc.addItem("SELECCIONA:");
                    comboc.setSelectedItem(tbproducto.getValueAt(fila, 8).toString());
                    try {
                        Object[] combo_llenar_marca = con.combox("marcas_productos", "marca_producto");
                        combom.removeAllItems();
                        for (int i = 0; i < combo_llenar_marca.length; i++) {
                            combom.addItem((String) combo_llenar_marca[i]);
                        }
                    } catch (Exception e) {
                        Object[] combo_llenar_marca = con.combox("marcas_productos", "marca_producto");
                        combom.removeAllItems();
                        for (int i = 0; i < combo_llenar_marca.length; i++) {
                            combom.addItem((String) combo_llenar_marca[i]);
                        }
                    }
                    combom.addItem("SELECCIONA:");
                    combom.setSelectedItem(tbproducto.getValueAt(fila, 3).toString());
                } catch (Exception ex) {

                    Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
                }

                obj.setVisible(true);

                dispose();
            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }
    }

    public void Limitar_caracteres_cajastexto() {
        pornombre.setDocument(new Limitar_caracteres(pornombre, 30));
        clavetxt.setDocument(new Limitar_caracteres(clavetxt, 6));
    }

    public void mayusculas(JTextField e) {
        e.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (Character.isLowerCase(caracter)) {
                    e.setKeyChar(Character.toUpperCase(caracter));
                }
            }

        });
    }

    void mostrardatos(String valor) throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CLAVE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("COLOR");
        modelo.addColumn("MARCA");
        modelo.addColumn("PRECIO DE COMPRA");
        modelo.addColumn("PRECIO DE VENTA");
        modelo.addColumn("FECHA DE COMPRA");
        modelo.addColumn("STOCK");
        modelo.addColumn("CATEGORÍA");
        modelo.addColumn("DESCRIPCIÓN");

        tbproducto.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM productos";
        } else {
            sql = "SELECT * FROM productos WHERE id_producto ='" + valor + "'";
        }
        String[] datos = new String[10];
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

                modelo.addRow(datos);

            }

            tbproducto.setModel(modelo);

        } catch (SQLException ex) {

            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clickderecho = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jPanel15 = new javax.swing.JPanel();
        txt_D_A_marca = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Cancelar_marca = new javax.swing.JButton();
        G_marca = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        M_A_M_eliminar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ecategoria1 = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Cancelar_catego2 = new javax.swing.JButton();
        B_eliminar_categoria = new javax.swing.JButton();
        combo_eliminar = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        M_agregar_categoria = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jPanel10 = new javax.swing.JPanel();
        txt_D_A_Categoria1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        Cancelar_catego1 = new javax.swing.JButton();
        G_catego1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        M_ELIMINAR_CATEGORIA1 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        emar = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        Cancelar_Eliminar_marca2 = new javax.swing.JButton();
        E_marca2 = new javax.swing.JButton();
        combo_eliminar_marca2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        astock = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        lblpz = new javax.swing.JLabel();
        prostock = new javax.swing.JLabel();
        activatxt = new javax.swing.JCheckBox();
        ztoka2 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        btng1 = new javax.swing.JButton();
        lblztoka = new javax.swing.JLabel();
        ztoka = new javax.swing.JTextField();
        lblez = new javax.swing.JLabel();
        lblaz = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        maz = new javax.swing.JLabel();
        metiche = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        stockbus = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        txtbuxca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnb2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        imprimir = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        cbx = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnCambiarImpre = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        atrasIP = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        clavetxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        CBX_SELECCIONAR_BUSQUEDA = new javax.swing.JComboBox<>();
        porfecha = new com.toedter.calendar.JDateChooser();
        b_buscar = new javax.swing.JButton();
        ayudanombre = new javax.swing.JLabel();
        ayudafecha = new javax.swing.JLabel();
        pornombre = new javax.swing.JTextField();
        pormarcacatego = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproducto = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif3.png"))); // NOI18N
        jMenu1.setMnemonic('F');
        jMenu1.setText("Modificar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        clickderecho.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BASURA3.png"))); // NOI18N
        jMenu2.setMnemonic('L');
        jMenu2.setText("Eliminar");
        jMenu2.setToolTipText("");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenu2MouseEntered(evt);
            }
        });
        clickderecho.add(jMenu2);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/barra60px.png"))); // NOI18N
        jMenu8.setText("Imprimir CB");
        jMenu8.setToolTipText("");
        jMenu8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        clickderecho.add(jMenu8);

        nmarca1.setMinimumSize(new java.awt.Dimension(360, 290));
        nmarca1.setType(java.awt.Window.Type.UTILITY);
        nmarca1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_D_A_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_D_A_marca.setForeground(new java.awt.Color(0, 0, 102));
        txt_D_A_marca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_D_A_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_D_A_marcaActionPerformed(evt);
            }
        });
        txt_D_A_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_D_A_marcaKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("AGREGAR MARCA");

        Cancelar_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar_marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR3.png"))); // NOI18N
        Cancelar_marca.setMnemonic('S');
        Cancelar_marca.setText("SALIR");
        Cancelar_marca.setBorderPainted(false);
        Cancelar_marca.setContentAreaFilled(false);
        Cancelar_marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar_marca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar_marca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR4.png"))); // NOI18N
        Cancelar_marca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR2.png"))); // NOI18N
        Cancelar_marca.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cancelar_marca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_marcaActionPerformed(evt);
            }
        });

        G_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        G_marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar602.png"))); // NOI18N
        G_marca.setMnemonic('G');
        G_marca.setText("GUARDAR");
        G_marca.setBorderPainted(false);
        G_marca.setContentAreaFilled(false);
        G_marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        G_marca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G_marca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        G_marca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar601.png"))); // NOI18N
        G_marca.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G_marca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G_marcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar_marca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G_marca)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txt_D_A_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(83, 83, 83))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_D_A_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_marca)
                    .addComponent(G_marca))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        nmarca1.getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4M.png"))); // NOI18N
        nmarca1.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 290));

        jMenuBar2.setBackground(new java.awt.Color(0, 0, 204));
        jMenuBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("ARCHIVO");

        M_A_M_eliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        M_A_M_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA3.png"))); // NOI18N
        M_A_M_eliminar.setText("ELIMINAR MARCA");
        M_A_M_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_A_M_eliminarActionPerformed(evt);
            }
        });
        jMenu3.add(M_A_M_eliminar);

        jMenuBar2.add(jMenu3);
        jMenuBar2.add(jMenu4);

        nmarca1.setJMenuBar(jMenuBar2);

        ecategoria1.setMinimumSize(new java.awt.Dimension(360, 290));
        ecategoria1.setType(java.awt.Window.Type.UTILITY);
        ecategoria1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 0, 51));
        jLabel19.setText("ELIMINAR CATEGORÍA");

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

        B_eliminar_categoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_eliminar_categoria.setForeground(new java.awt.Color(204, 0, 51));
        B_eliminar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA2.png"))); // NOI18N
        B_eliminar_categoria.setMnemonic('E');
        B_eliminar_categoria.setText("ELIMINAR");
        B_eliminar_categoria.setBorderPainted(false);
        B_eliminar_categoria.setContentAreaFilled(false);
        B_eliminar_categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_eliminar_categoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        B_eliminar_categoria.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA3.png"))); // NOI18N
        B_eliminar_categoria.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA1.png"))); // NOI18N
        B_eliminar_categoria.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        B_eliminar_categoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B_eliminar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_eliminar_categoriaActionPerformed(evt);
            }
        });

        combo_eliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combo_eliminar.setForeground(new java.awt.Color(0, 0, 153));
        combo_eliminar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_eliminarActionPerformed(evt);
            }
        });
        combo_eliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                combo_eliminarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar_catego2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B_eliminar_categoria)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combo_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_catego2)
                    .addComponent(B_eliminar_categoria))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        ecategoria1.getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N
        ecategoria1.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, 290));

        jMenuBar3.setBackground(new java.awt.Color(102, 0, 204));
        jMenuBar3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setText("ARCHIVO");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        M_agregar_categoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        M_agregar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        M_agregar_categoria.setText("AGREGAR CATEGORÍA");
        M_agregar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_agregar_categoriaActionPerformed(evt);
            }
        });
        jMenu5.add(M_agregar_categoria);

        jMenuBar3.add(jMenu5);
        jMenuBar3.add(jMenu6);

        ecategoria1.setJMenuBar(jMenuBar3);

        ncategoria1.setMinimumSize(new java.awt.Dimension(360, 290));
        ncategoria1.setType(java.awt.Window.Type.UTILITY);

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_D_A_Categoria1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_D_A_Categoria1.setForeground(new java.awt.Color(0, 0, 102));
        txt_D_A_Categoria1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_D_A_Categoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_D_A_Categoria1ActionPerformed(evt);
            }
        });
        txt_D_A_Categoria1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_D_A_Categoria1KeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("AGREGAR CATEGORÍA");

        Cancelar_catego1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar_catego1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR3.png"))); // NOI18N
        Cancelar_catego1.setMnemonic('S');
        Cancelar_catego1.setText("SALIR");
        Cancelar_catego1.setBorderPainted(false);
        Cancelar_catego1.setContentAreaFilled(false);
        Cancelar_catego1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar_catego1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar_catego1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR4.png"))); // NOI18N
        Cancelar_catego1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR2.png"))); // NOI18N
        Cancelar_catego1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cancelar_catego1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar_catego1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_catego1ActionPerformed(evt);
            }
        });

        G_catego1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        G_catego1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar602.png"))); // NOI18N
        G_catego1.setMnemonic('G');
        G_catego1.setText("GUARDAR");
        G_catego1.setBorderPainted(false);
        G_catego1.setContentAreaFilled(false);
        G_catego1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        G_catego1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G_catego1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        G_catego1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar601.png"))); // NOI18N
        G_catego1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G_catego1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G_catego1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G_catego1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar_catego1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G_catego1)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txt_D_A_Categoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(69, 69, 69))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_D_A_Categoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_catego1)
                    .addComponent(G_catego1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N

        jMenuBar5.setBackground(new java.awt.Color(102, 0, 204));
        jMenuBar5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu9.setForeground(new java.awt.Color(255, 255, 255));
        jMenu9.setText("ARCHIVO");
        jMenu9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        M_ELIMINAR_CATEGORIA1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        M_ELIMINAR_CATEGORIA1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA3.png"))); // NOI18N
        M_ELIMINAR_CATEGORIA1.setText("ELIMINAR CATEGORÍA");
        M_ELIMINAR_CATEGORIA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_ELIMINAR_CATEGORIA1ActionPerformed(evt);
            }
        });
        jMenu9.add(M_ELIMINAR_CATEGORIA1);

        jMenuBar5.add(jMenu9);
        jMenuBar5.add(jMenu10);

        ncategoria1.setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout ncategoria1Layout = new javax.swing.GroupLayout(ncategoria1.getContentPane());
        ncategoria1.getContentPane().setLayout(ncategoria1Layout);
        ncategoria1Layout.setHorizontalGroup(
            ncategoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ncategoria1Layout.createSequentialGroup()
                .addGroup(ncategoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ncategoria1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        ncategoria1Layout.setVerticalGroup(
            ncategoria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ncategoria1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel24)
        );

        emar.setMinimumSize(new java.awt.Dimension(360, 290));
        emar.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 0, 51));
        jLabel27.setText("ELIMINAR MARCA");

        Cancelar_Eliminar_marca2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar_Eliminar_marca2.setForeground(new java.awt.Color(204, 0, 51));
        Cancelar_Eliminar_marca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR3.png"))); // NOI18N
        Cancelar_Eliminar_marca2.setMnemonic('C');
        Cancelar_Eliminar_marca2.setText("CANCELAR");
        Cancelar_Eliminar_marca2.setBorderPainted(false);
        Cancelar_Eliminar_marca2.setContentAreaFilled(false);
        Cancelar_Eliminar_marca2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar_Eliminar_marca2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar_Eliminar_marca2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR4.png"))); // NOI18N
        Cancelar_Eliminar_marca2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR2.png"))); // NOI18N
        Cancelar_Eliminar_marca2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cancelar_Eliminar_marca2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar_Eliminar_marca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_Eliminar_marca2ActionPerformed(evt);
            }
        });

        E_marca2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        E_marca2.setForeground(new java.awt.Color(204, 0, 0));
        E_marca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar602.png"))); // NOI18N
        E_marca2.setMnemonic('E');
        E_marca2.setText("ELIMINAR");
        E_marca2.setBorderPainted(false);
        E_marca2.setContentAreaFilled(false);
        E_marca2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        E_marca2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        E_marca2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        E_marca2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar601.png"))); // NOI18N
        E_marca2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E_marca2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        E_marca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_marca2ActionPerformed(evt);
            }
        });

        combo_eliminar_marca2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combo_eliminar_marca2.setForeground(new java.awt.Color(0, 0, 153));
        combo_eliminar_marca2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_eliminar_marca2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                combo_eliminar_marca2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Cancelar_Eliminar_marca2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E_marca2))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 50, Short.MAX_VALUE)
                .addComponent(combo_eliminar_marca2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_eliminar_marca2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_Eliminar_marca2)
                    .addComponent(E_marca2))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        emar.getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 300, 210));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4M.png"))); // NOI18N
        emar.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuBar7.setBackground(new java.awt.Color(0, 0, 204));
        jMenuBar7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu15.setForeground(new java.awt.Color(255, 255, 255));
        jMenu15.setText("ARCHIVO");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        jMenuItem6.setText("AGREGAR MARCA");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem6);

        jMenuBar7.add(jMenu15);
        jMenuBar7.add(jMenu16);

        emar.setJMenuBar(jMenuBar7);

        astock.setMinimumSize(new java.awt.Dimension(360, 310));
        astock.setType(java.awt.Window.Type.UTILITY);
        astock.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblpz.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblpz.setText("PRODUCTO:");
        jPanel11.add(lblpz, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        prostock.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        prostock.setForeground(new java.awt.Color(153, 0, 0));
        jPanel11.add(prostock, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 190, 17));

        activatxt.setFont(new java.awt.Font("Arial", 1, 8)); // NOI18N
        activatxt.setText("MODIFICAR STOCK ACTUAL");
        activatxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activatxtMouseClicked(evt);
            }
        });
        activatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activatxtActionPerformed(evt);
            }
        });
        activatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                activatxtKeyReleased(evt);
            }
        });
        jPanel11.add(activatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        ztoka2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ztoka2.setForeground(new java.awt.Color(204, 0, 0));
        ztoka2.setToolTipText("Coloca el nuevo stock formato 00");
        ztoka2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ztoka2ActionPerformed(evt);
            }
        });
        ztoka2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ztoka2KeyTyped(evt);
            }
        });
        jPanel11.add(ztoka2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 62, 28));

        jButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/SALIR3.png"))); // NOI18N
        jButton11.setMnemonic('S');
        jButton11.setText("SALIR");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setIconTextGap(-4);
        jButton11.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/SALIR4.png"))); // NOI18N
        jButton11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/SALIR2.png"))); // NOI18N
        jButton11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        btng1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btng1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/guardar602.png"))); // NOI18N
        btng1.setMnemonic('G');
        btng1.setText("GUARDAR");
        btng1.setBorderPainted(false);
        btng1.setContentAreaFilled(false);
        btng1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btng1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btng1.setIconTextGap(-4);
        btng1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/guardar603.png"))); // NOI18N
        btng1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/guardar601.png"))); // NOI18N
        btng1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btng1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btng1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btng1ActionPerformed(evt);
            }
        });
        jPanel11.add(btng1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        lblztoka.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPanel11.add(lblztoka, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 60, 30));

        ztoka.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ztoka.setForeground(new java.awt.Color(204, 51, 0));
        ztoka.setToolTipText("modifica el stock actual en la base de datos formato 00");
        ztoka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ztokaActionPerformed(evt);
            }
        });
        ztoka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ztokaKeyTyped(evt);
            }
        });
        jPanel11.add(ztoka, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 61, 28));

        lblez.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblez.setText("EN STOCK:");
        jPanel11.add(lblez, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        lblaz.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblaz.setText("AGREGAR A STOCK:");
        jPanel11.add(lblaz, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setText("ACTUALIZAR STOCK DE PRODUCTOS");
        jPanel11.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));

        maz.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        maz.setForeground(new java.awt.Color(204, 0, 0));
        maz.setText("+");
        jPanel11.add(maz, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        metiche.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        metiche.setText("Oprime Ctrl+ B para buscar y actualizar otro producto.");
        jPanel11.add(metiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 300, 20));

        astock.getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 230));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGOASTOCK2.png"))); // NOI18N
        astock.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 350, 290));

        jMenu7.setText("ARCHIVO");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/lupa.png"))); // NOI18N
        jMenuItem1.setText("BUSCAR");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem1);

        jMenuBar1.add(jMenu7);

        astock.setJMenuBar(jMenuBar1);

        stockbus.setMinimumSize(new java.awt.Dimension(360, 200));
        stockbus.setType(java.awt.Window.Type.UTILITY);
        stockbus.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtbuxca.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtbuxca.setForeground(new java.awt.Color(204, 51, 0));
        txtbuxca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuxcaActionPerformed(evt);
            }
        });
        txtbuxca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuxcaKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("COLOCA LA CLAVE DEL PRODUCTO");

        btnb2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/OK.png"))); // NOI18N
        btnb2.setText("ACEPTAR");
        btnb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnb2.setIconTextGap(15);
        btnb2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/OK3.png"))); // NOI18N
        btnb2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/OK1.png"))); // NOI18N
        btnb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnb2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbuxca, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnb2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(44, 44, 44))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbuxca, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnb2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stockbus.getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 130));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N
        stockbus.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -130, 360, -1));

        imprimir.setMinimumSize(new java.awt.Dimension(550, 430));
        imprimir.setType(java.awt.Window.Type.UTILITY);
        imprimir.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbx.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbx.setForeground(new java.awt.Color(0, 0, 153));
        cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx.setToolTipText("");
        cbx.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel11.setText("SELECCIONA IMPRESORA");

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
                        .addComponent(jLabel11)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        imprimir.getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 490, 320));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("CODIGO DE BARRAS PRODUCTOS");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel13)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        imprimir.getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 490, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/FONDOAGREMPLEADO3.png"))); // NOI18N
        jLabel9.setMaximumSize(new java.awt.Dimension(550, 430));
        jLabel9.setMinimumSize(new java.awt.Dimension(550, 430));
        jLabel9.setPreferredSize(new java.awt.Dimension(550, 430));
        imprimir.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INVENTARIO DE PRODUCTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 340, 30));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        atrasIP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        atrasIP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ATRAS2.png"))); // NOI18N
        atrasIP.setMnemonic('A');
        atrasIP.setText("ATRAS");
        atrasIP.setBorderPainted(false);
        atrasIP.setContentAreaFilled(false);
        atrasIP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atrasIP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        atrasIP.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ATRAS3.png"))); // NOI18N
        atrasIP.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ATRAS1.png"))); // NOI18N
        atrasIP.setVerifyInputWhenFocusTarget(false);
        atrasIP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        atrasIP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        atrasIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasIPActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/AGREGAR2.png"))); // NOI18N
        jButton2.setMnemonic('N');
        jButton2.setText("NUEVO PRODUCTO");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/AGREGAR3.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/AGREGAR1.png"))); // NOI18N
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(atrasIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atrasIP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(46, 46, 46))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 280, 120));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ACTUALIZAR2.png"))); // NOI18N
        jButton3.setMnemonic('T');
        jButton3.setText("ACTUALIZAR STOCK ");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ACTUALIZAR3.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ACTUALZAR1.png"))); // NOI18N
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 170, 120));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BUSCAR2.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("BUSCAR");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BUSCAR3.png"))); // NOI18N
        btnBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BUSCAR1.png"))); // NOI18N
        btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/limpiar2.png"))); // NOI18N
        jButton8.setMnemonic('L');
        jButton8.setText("LIMPIAR");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/limpiar3.png"))); // NOI18N
        jButton8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/limpiar1.png"))); // NOI18N
        jButton8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif2.png"))); // NOI18N
        jButton9.setMnemonic('M');
        jButton9.setText("MODIFICAR");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif3.png"))); // NOI18N
        jButton9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif1.png"))); // NOI18N
        jButton9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BASURA2.png"))); // NOI18N
        jButton10.setMnemonic('E');
        jButton10.setText("ELIMINAR");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BASURA3.png"))); // NOI18N
        jButton10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BASURA1.png"))); // NOI18N
        jButton10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        clavetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavetxtActionPerformed(evt);
            }
        });
        clavetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clavetxtKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("BUSQUEDA POR ID");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(clavetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(27, 27, 27)))
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8)
                            .addComponent(jButton9)
                            .addComponent(jButton10)
                            .addComponent(btnBuscar)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clavetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 590, 140));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/PRODUCTOS2.png"))); // NOI18N
        jButton6.setMnemonic('D');
        jButton6.setText("MOSTRAR PRODUCTOS");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/PRODUCTOS3.png"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/PRODUCTOS1.png"))); // NOI18N
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton6)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 300, 140));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CBX_SELECCIONAR_BUSQUEDA.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBX_SELECCIONAR_BUSQUEDA.setForeground(new java.awt.Color(0, 0, 102));
        CBX_SELECCIONAR_BUSQUEDA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA:", "FECHA", "NOMBRE", "CATEGORÍA", "MARCA", "FECHA AVANZADA", " " }));
        CBX_SELECCIONAR_BUSQUEDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBX_SELECCIONAR_BUSQUEDAActionPerformed(evt);
            }
        });

        porfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                porfechaKeyReleased(evt);
            }
        });

        b_buscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        b_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/folder2.png"))); // NOI18N
        b_buscar.setMnemonic('S');
        b_buscar.setText("BUSCAR");
        b_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_buscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/folder3.png"))); // NOI18N
        b_buscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/folder1.png"))); // NOI18N
        b_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_buscarActionPerformed(evt);
            }
        });

        ayudanombre.setText("AYUDANOMBRE");

        ayudafecha.setText("AYUDAFECHA");

        pornombre.setToolTipText("");
        pornombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pornombreActionPerformed(evt);
            }
        });
        pornombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pornombreKeyReleased(evt);
            }
        });

        pormarcacatego.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pormarcacatego.setForeground(new java.awt.Color(0, 0, 102));
        pormarcacatego.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pormarcacatego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pormarcacategoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ayudafecha)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ayudanombre)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBX_SELECCIONAR_BUSQUEDA, 0, 173, Short.MAX_VALUE)
                            .addComponent(b_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(porfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(pornombre))
                    .addComponent(pormarcacatego, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_buscar)
                            .addComponent(pormarcacatego, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(ayudafecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBX_SELECCIONAR_BUSQUEDA, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(porfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pornombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ayudanombre))
                        .addGap(0, 82, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 90, 390, 250));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("BÚSQUEDA AVANZADA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(37, 37, 37))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, 240, 30));

        tbproducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CLAVE", "NOMBRE", "COLOR", "MARCA", "PRECIO DE COMPRA", "PRECIO DE VENTA", "FECHA DE COMPRA", "STOCK", "CATEGORÍA", "DESCRIPCIÓN"
            }
        ));
        tbproducto.setComponentPopupMenu(clickderecho);
        tbproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductoMouseClicked(evt);
            }
        });
        tbproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbproductoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbproducto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1300, 370));

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/MARCA.png"))); // NOI18N
        jButton4.setMnemonic('R');
        jButton4.setText("MARCA");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/CATEGORIA.png"))); // NOI18N
        jButton5.setMnemonic('C');
        jButton5.setText("CATEGORIA");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(97, 97, 97))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 280, 90));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("AGREGAR");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 150, 30));

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/barcode.png"))); // NOI18N
        jButton1.setMnemonic('P');
        jButton1.setText("IMPRIMIR CB");
        jButton1.setToolTipText("Imprime Codigo de Barras");
        jButton1.setIconTextGap(5);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir/printnegro.png"))); // NOI18N
        jButton12.setMnemonic('I');
        jButton12.setText("IMPRIMIR TB");
        jButton12.setToolTipText("Imprime Tabla de productos");
        jButton12.setIconTextGap(5);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 140, 120));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/INVENTARIO_PRODUCTOS.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setAutoscrolls(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1380, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clavetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clavetxtActionPerformed

    private void pornombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pornombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pornombreActionPerformed

    private void CBX_SELECCIONAR_BUSQUEDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBX_SELECCIONAR_BUSQUEDAActionPerformed
        String emparejar;

        String campotext;

        emparejar = CBX_SELECCIONAR_BUSQUEDA.getSelectedItem().toString();

        if (emparejar == "SELECCIONA:") {

            porfecha.setEnabled(false);

            b_buscar.setEnabled(false);

            pormarcacatego.setEnabled(false);

            pornombre.setEnabled(false);

            pormarcacatego.removeAllItems();

            pormarcacatego.addItem("SELECCIONA:");

            pornombre.setText("");

            porfecha.setDate(null);

            ayudafecha.setEnabled(false);

            ayudafecha.setText("");

            ayudanombre.setEnabled(false);

            ayudanombre.setText("");

        } else if (emparejar == "FECHA") {

            porfecha.setEnabled(true);

            b_buscar.setEnabled(true);

            pormarcacatego.setEnabled(false);

            pornombre.setEnabled(false);

            pormarcacatego.removeAllItems();

            pormarcacatego.addItem("SELECCIONA:");

            pornombre.setText("");

            ayudafecha.setEnabled(true);

            ayudafecha.setText("Escribe o selecciona la fecha formato DD/MM/AAAA");

            ayudanombre.setEnabled(false);

            ayudanombre.setText("");

        } else if (emparejar == "MARCA") {

            porfecha.setEnabled(false);

            b_buscar.setEnabled(false);

            pormarcacatego.setEnabled(true);

            pornombre.setEnabled(false);

            pornombre.setText("");

            porfecha.setDate(null);

            ayudafecha.setEnabled(false);

            ayudafecha.setText("");

            ayudanombre.setEnabled(false);

            ayudanombre.setText("");

            try {

                Object[] llenar_combo_busca_categoria = con.combox("marcas_productos", "marca_producto");

                pormarcacatego.removeAllItems();

                for (int i = 0; i < llenar_combo_busca_categoria.length; i++) {

                    pormarcacatego.addItem((String) llenar_combo_busca_categoria[i]);

                }

            } catch (Exception e) {

                Object[] llenar_combo_busca_categoria = con.combox("marcas_productos", "marca_producto");

                pormarcacatego.removeAllItems();

                for (int i = 0; i < llenar_combo_busca_categoria.length; i++) {

                    pormarcacatego.addItem((String) llenar_combo_busca_categoria[i]);

                }

            }
            pormarcacatego.addItem("SELECCIONA:");

            pormarcacatego.setSelectedItem("SELECCIONA:");

        } else if (emparejar == "CATEGORÍA") {

            porfecha.setEnabled(false);

            b_buscar.setEnabled(false);

            pormarcacatego.setEnabled(true);

            pornombre.setEnabled(false);

            pormarcacatego.removeAllItems();

            pornombre.setText("");

            porfecha.setDate(null);

            ayudafecha.setEnabled(false);

            ayudafecha.setText("");

            ayudanombre.setEnabled(false);

            ayudanombre.setText("");

            try {

                Object[] llenar_combo_busca_categoria = con.combox("categoria_productos", "categoria_producto");

                pormarcacatego.removeAllItems();

                for (int i = 0; i < llenar_combo_busca_categoria.length; i++) {

                    pormarcacatego.addItem((String) llenar_combo_busca_categoria[i]);

                }

            } catch (Exception e) {

                Object[] llenar_combo_busca_categoria = con.combox("categoria_productos", "categoria_producto");

                pormarcacatego.removeAllItems();

                for (int i = 0; i < llenar_combo_busca_categoria.length; i++) {

                    pormarcacatego.addItem((String) llenar_combo_busca_categoria[i]);

                }

            }

            pormarcacatego.addItem("SELECCIONA:");

            pormarcacatego.setSelectedItem("SELECCIONA:");

        } else if (emparejar == "NOMBRE") {

            porfecha.setName("");

            porfecha.setEnabled(false);

            b_buscar.setEnabled(false);

            pormarcacatego.setEnabled(false);

            pornombre.setEnabled(true);

            pormarcacatego.removeAllItems();

            pormarcacatego.addItem("SELECCIONA:");

            pornombre.requestFocus();

            porfecha.setDate(null);

            pornombre.setText("");

            ayudafecha.setEnabled(false);

            ayudafecha.setText("");

            ayudanombre.setEnabled(true);

            ayudanombre.setText("Tip para nombre");

        } else if (emparejar == "FECHA AVANZADA") {

            porfecha.setName("");

            porfecha.setEnabled(false);

            b_buscar.setEnabled(false);

            pormarcacatego.setEnabled(false);

            pornombre.setEnabled(true);

            pormarcacatego.removeAllItems();

            pormarcacatego.addItem("SELECCIONA:");

            pornombre.requestFocus();

            porfecha.setDate(null);

            pornombre.setText("");

            ayudafecha.setEnabled(false);

            ayudafecha.setText("");

            ayudanombre.setEnabled(true);

            ayudanombre.setText("Tip formato AAAA-MM-DD");

        }

    }//GEN-LAST:event_CBX_SELECCIONAR_BUSQUEDAActionPerformed

    private void atrasIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasIPActionPerformed
          if(invi==true){
        VentanaUsuario obj = new VentanaUsuario();
        obj.setVisible(true);
         
           
         
        }else{
        Ventanaadmin obj2 = new Ventanaadmin();
        obj2.setVisible(true);
        
        }
        this.dispose();
        System.out.println(invitado+ " categoria usuario en inventario");
                                            
    }//GEN-LAST:event_atrasIPActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Agregar_Producto obj = new Agregar_Producto();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        CBX_SELECCIONAR_BUSQUEDA.setSelectedItem("SELECCIONA:");

        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();

        try {
            mostrardatos(clavetxt.getText());

        } catch (Exception ex) {

            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        modificar();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clavetxt.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        CBX_SELECCIONAR_BUSQUEDA.setSelectedItem("SELECCIONA:");
        clavetxt.setText("");
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn = cc.conexion();
        try {
            mostrardatos("");
        } catch (Exception ex) {
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void pormarcacategoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pormarcacategoActionPerformed
        gc();
        String comparar;

        comparar = CBX_SELECCIONAR_BUSQUEDA.getSelectedItem().toString();

        if (comparar == "CATEGORÍA") {

            try {

                String categoria;

                if (pormarcacatego != null) {

                    categoria = pormarcacatego.getSelectedItem().toString();

                    System.out.println(categoria);

                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("CLAVE");
                    modelo.addColumn("NOMBRE");
                    modelo.addColumn("COLOR");
                    modelo.addColumn("MARCA");
                    modelo.addColumn("PRECIO DE COMPRA");
                    modelo.addColumn("PRECIO DE VENTA");
                    modelo.addColumn("FECHA DE COMPRA");
                    modelo.addColumn("STOCK");
                    modelo.addColumn("CATEGORÍA");
                    modelo.addColumn("DESCRIPCIÓN");
                    tbproducto.setModel(modelo);
                    String sql = "";
                    sql = "SELECT * FROM productos WHERE categoria ='" + categoria + "'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);
                        }
                        tbproducto.setModel(modelo);
                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } catch (Exception e) {

            }

        } else if (comparar == "MARCA") {

            try {
                String marca;
                if (pormarcacatego != null) {
                    marca = pormarcacatego.getSelectedItem().toString();

                    DefaultTableModel modelo = new DefaultTableModel();

                    modelo.addColumn("CLAVE");
                    modelo.addColumn("NOMBRE");
                    modelo.addColumn("COLOR");
                    modelo.addColumn("MARCA");
                    modelo.addColumn("PRECIO DE COMPRA");
                    modelo.addColumn("PRECIO DE VENTA");
                    modelo.addColumn("FECHA DE COMPRA");
                    modelo.addColumn("STOCK");
                    modelo.addColumn("CATEGORÍA");
                    modelo.addColumn("DESCRIPCIÓN");

                    tbproducto.setModel(modelo);
                    String sql = "";
                    sql = "SELECT * FROM productos WHERE marca ='" + marca + "'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);
                        }
                        tbproducto.setModel(modelo);
                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } catch (Exception e) {
            }

        }

    }//GEN-LAST:event_pormarcacategoActionPerformed

    private void b_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_buscarActionPerformed
        String comparar;

        comparar = CBX_SELECCIONAR_BUSQUEDA.getSelectedItem().toString();

        if (comparar == "FECHA") {

            try {

                String formato = "yyyy/MM/dd";

                java.util.Date date = porfecha.getDate();

                SimpleDateFormat sdf = new SimpleDateFormat(formato);

                String fechafin = sdf.format(date);

                if (porfecha != null) {

                    DefaultTableModel modelo = new DefaultTableModel();

                    modelo.addColumn("CLAVE");

                    modelo.addColumn("NOMBRE");

                    modelo.addColumn("COLOR");

                    modelo.addColumn("MARCA");

                    modelo.addColumn("PRECIO DE COMPRA");

                    modelo.addColumn("PRECIO DE VENTA");

                    modelo.addColumn("FECHA DE COMPRA");

                    modelo.addColumn("STOCK");

                    modelo.addColumn("CATEGORÍA");

                    modelo.addColumn("DESCRIPCIÓN");

                    tbproducto.setModel(modelo);

                    String sql = "";

                    sql = "SELECT * FROM productos WHERE fecha_compra='" + fechafin + "'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);

                        }

                        tbproducto.setModel(modelo);

                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            } catch (Exception e) {

                String nl = System.getProperty("line.separator");

                JOptionPane.showMessageDialog(null, "¡Error! el campo de texto esta vacio" + nl + "Coloque la fecha manualmente o elija alguna del calendario");

            }

        } else if (comparar == "CATEGORÍA") {

            try {

                String categoria;

                if (pormarcacatego != null) {

                    categoria = pormarcacatego.getSelectedItem().toString();

                    System.out.println(categoria);

                    DefaultTableModel modelo = new DefaultTableModel();

                    modelo.addColumn("CLAVE");

                    modelo.addColumn("NOMBRE");

                    modelo.addColumn("COLOR");

                    modelo.addColumn("MARCA");

                    modelo.addColumn("PRECIO DE COMPRA");

                    modelo.addColumn("PRECIO DE VENTA");

                    modelo.addColumn("FECHA DE COMPRA");

                    modelo.addColumn("STOCK");

                    modelo.addColumn("CATEGORÍA");

                    modelo.addColumn("DESCRIPCIÓN");

                    tbproducto.setModel(modelo);

                    String sql = "";

                    sql = "SELECT * FROM productos WHERE categoria ='" + categoria + "'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);

                        }

                        tbproducto.setModel(modelo);

                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            } catch (Exception e) {

                String nl = System.getProperty("line.separator");

                JOptionPane.showMessageDialog(null, "¡Error! el campo de texto esta vacio" + nl + "Coloque la fecha manualmente o elija alguna del calendario");

            }

        }


    }//GEN-LAST:event_b_buscarActionPerformed

    private void pornombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pornombreKeyReleased
        String comparar;

        comparar = CBX_SELECCIONAR_BUSQUEDA.getSelectedItem().toString();

        if (comparar == "NOMBRE") {

            try {

                String nombre;

                if (this.pornombre != null) {

                    nombre = pornombre.getText().toString();

                    DefaultTableModel modelo = new DefaultTableModel();

                    modelo.addColumn("CLAVE");

                    modelo.addColumn("NOMBRE");

                    modelo.addColumn("COLOR");

                    modelo.addColumn("MARCA");

                    modelo.addColumn("PRECIO DE COMPRA");

                    modelo.addColumn("PRECIO DE VENTA");

                    modelo.addColumn("FECHA DE COMPRA");

                    modelo.addColumn("STOCK");

                    modelo.addColumn("CATEGORÍA");

                    modelo.addColumn("DESCRIPCIÓN");

                    tbproducto.setModel(modelo);

                    String sql = "";

                    sql = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre + "%'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);

                        }

                        tbproducto.setModel(modelo);

                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            } catch (Exception e) {

                String nl = System.getProperty("line.separator");

                JOptionPane.showMessageDialog(null, "¡Error! el campo de texto esta vacio" + nl + "Coloque la fecha manualmente o elija alguna del calendario");

            }

        } else if (comparar == "FECHA AVANZADA") {

            try {

                String nombre;

                if (this.pornombre != null) {

                    nombre = pornombre.getText().toString();

                    DefaultTableModel modelo = new DefaultTableModel();

                    modelo.addColumn("CLAVE");

                    modelo.addColumn("NOMBRE");

                    modelo.addColumn("COLOR");

                    modelo.addColumn("MARCA");

                    modelo.addColumn("PRECIO DE COMPRA");

                    modelo.addColumn("PRECIO DE VENTA");

                    modelo.addColumn("FECHA DE COMPRA");

                    modelo.addColumn("STOCK");

                    modelo.addColumn("CATEGORÍA");

                    modelo.addColumn("DESCRIPCIÓN");

                    tbproducto.setModel(modelo);

                    String sql = "";

                    sql = "SELECT * FROM productos WHERE fecha_compra LIKE '%" + nombre + "%'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);

                        }

                        tbproducto.setModel(modelo);

                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            } catch (Exception e) {

                String nl = System.getProperty("line.separator");

                JOptionPane.showMessageDialog(null, "¡Error! el campo de texto esta vacio" + nl + "Coloque la fecha manualmente o elija alguna del calendario");

            }

        }

    }//GEN-LAST:event_pornombreKeyReleased

    private void porfechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porfechaKeyReleased
        String comparar;

        comparar = CBX_SELECCIONAR_BUSQUEDA.getSelectedItem().toString();

        if (comparar == "FECHA") {

            try {

                String formato = "yyyy-MM-dd";

                java.util.Date date = porfecha.getDate();

                SimpleDateFormat sdf = new SimpleDateFormat(formato);

                String fechafin = sdf.format(date);

                if (porfecha != null) {

                    DefaultTableModel modelo = new DefaultTableModel();

                    modelo.addColumn("CLAVE");

                    modelo.addColumn("NOMBRE");

                    modelo.addColumn("COLOR");

                    modelo.addColumn("MARCA");

                    modelo.addColumn("PRECIO DE COMPRA");

                    modelo.addColumn("PRECIO DE VENTA");

                    modelo.addColumn("FECHA DE COMPRA");

                    modelo.addColumn("STOCK");

                    modelo.addColumn("CATEGORÍA");

                    modelo.addColumn("DESCRIPCIÓN");

                    tbproducto.setModel(modelo);

                    String sql = "";

                    sql = "SELECT * FROM productos WHERE fecha_compra LIKE '%" + fechafin + "%'";

                    String[] datos = new String[10];

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

                            modelo.addRow(datos);

                        }

                        tbproducto.setModel(modelo);

                    } catch (Exception ex) {

                        Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            } catch (Exception e) {

                String nl = System.getProperty("line.separator");

                JOptionPane.showMessageDialog(null, "¡Error! el campo de texto esta vacio" + nl + "Coloque la fecha manualmente o elija alguna del calendario");

            }

        }
    }//GEN-LAST:event_porfechaKeyReleased

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        if (evt.getClickCount() == 2) {
            modificar();
        }

        /* int fila = tbproducto.getSelectedRow();
            int columna = tbproducto.getSelectedRow();
            String tomarfecha;
            if (fila >= 0) {
                if (tbproducto.getValueAt(fila, columna) == null) {
                    JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
                } else if (fila >= 0 && columna >= 0) {

                    modificar_Producto.lblclave.setText(tbproducto.getValueAt(fila, 0).toString());
                    modificar_Producto.txtnombre.setText(tbproducto.getValueAt(fila, 1).toString());
                    modificar_Producto.txtapellido.setText(tbproducto.getValueAt(fila, 2).toString());

                    modificar_Producto.txtdireccion.setText(tbproducto.getValueAt(fila, 4).toString());
                    modificar_Producto.txtdesempeño.setText(tbproducto.getValueAt(fila, 5).toString());

                    tomarfecha = tbproducto.getValueAt(fila, 6).toString();
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
                    calendarioM.setDate(date);

                    modificar_Producto.txtstock.setText(tbproducto.getValueAt(fila, 7).toString());
                    modificar_Producto.txtedad.setText(tbproducto.getValueAt(fila, 9).toString());

                    modificar_Producto obj = null;
                    try {
                        obj = new modificar_Producto();
                        try {
                            Object[] combo_llenar_categoria = con.combox("categoria_productos", "categoria_producto");
                            combocategorian.removeAllItems();
                            for (int i = 0; i < combo_llenar_categoria.length; i++) {
                                combocategorian.addItem((String) combo_llenar_categoria[i]);
                            }
                        } catch (Exception e) {
                            Object[] combo_llenar_categoria = con.combox("categoria_productos", "categoria_producto");
                            combocategorian.removeAllItems();
                            for (int i = 0; i < combo_llenar_categoria.length; i++) {
                                combocategorian.addItem((String) combo_llenar_categoria[i]);
                            }
                        }
                        combocategorian.addItem("SELECCIONA:");
                        combocategorian.setSelectedItem(tbproducto.getValueAt(fila, 8).toString());
                        try {
                            Object[] combo_llenar_marca = con.combox("marcas_productos", "marca_producto");
                            combomarcan.removeAllItems();
                            for (int i = 0; i < combo_llenar_marca.length; i++) {
                                combomarcan.addItem((String) combo_llenar_marca[i]);
                            }
                        } catch (Exception e) {
                            Object[] combo_llenar_marca = con.combox("marcas_productos", "marca_producto");
                            combomarcan.removeAllItems();
                            for (int i = 0; i < combo_llenar_marca.length; i++) {
                                combomarcan.addItem((String) combo_llenar_marca[i]);
                            }
                        }
                        combomarcan.addItem("SELECCIONA:");
                        combomarcan.setSelectedItem(tbproducto.getValueAt(fila, 3).toString());
                    } catch (Exception ex) {

                        Logger.getLogger(Ctrl_inventario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    obj.setVisible(true);

                    dispose();
                }

            } else {

                JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
            }
        }   */

    }//GEN-LAST:event_jMenu1MouseClicked

    private void tbproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbproductoKeyPressed
        /*
        int fila =tbproducto.getSelectedRow();
        int f,c;
        
        f=tbproducto.getSelectionModel().getLeadSelectionIndex();
        c=tbproducto.getColumnModel().getSelectionModel().getLeadSelectionIndex();
        if(fila>=0){
            String valor=tbproducto.getValueAt(f,c).toString();  
            
            System.out.println(valor);
        }
         */
    }//GEN-LAST:event_tbproductoKeyPressed

    private void tbproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductoMouseClicked
        if (evt.getClickCount() == 2) {
            modificar();
        }
    }//GEN-LAST:event_tbproductoMouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        if (evt.getClickCount() == 2) {
            eliminar();
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void txt_D_A_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_D_A_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_D_A_marcaActionPerformed

    private void txt_D_A_marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_D_A_marcaKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            G_marca.doClick();
        }
    }//GEN-LAST:event_txt_D_A_marcaKeyTyped

    private void Cancelar_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_marcaActionPerformed
        nmarca1.dispose();
    }//GEN-LAST:event_Cancelar_marcaActionPerformed

    private void G_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G_marcaActionPerformed
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = cc.conexion();
            String marca;

            String sql = "";

            marca = txt_D_A_marca.getText();

            if (marca.length() == 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! El campo de texto esta vacío." + nl + "Por favor ingrese el nombre de la marca que desea registrar.");
                txt_D_A_marca.requestFocus();
            } else {

                sql = "INSERT INTO marcas_productos (marca_producto) VALUES (?)";

                try {
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, marca);

                    int n = pst.executeUpdate();

                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "Registro guardado con éxito");
                        txt_D_A_marca.setText("");
                        txt_D_A_marca.requestFocus();

                        Object[] llenar_combo_marca_producto = con.combox("marcas_productos", "marca_producto");

                        combomarcafin.removeAllItems();

                        for (int i = 0; i < llenar_combo_marca_producto.length; i++) {

                            combomarcafin.addItem((String) llenar_combo_marca_producto[i]);

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error! no se pudo guardar el registro");

                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "¡Error! Hubo un problema con la tabla que contiene la informacion de marcas en la base los datos");

                }

            }
        } catch (Exception e) {

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");

        }

    }//GEN-LAST:event_G_marcaActionPerformed

    private void M_A_M_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_A_M_eliminarActionPerformed
        try {
            Object[] llenar_combo_categoria_producto = con.combox("marcas_productos", "marca_producto");

            combo_eliminar_marca2.removeAllItems();

            for (int i = 0; i < llenar_combo_categoria_producto.length; i++) {

                combo_eliminar_marca2.addItem((String) llenar_combo_categoria_producto[i]);
            }

            nmarca1.dispose();
            emar.setModal(true);
            emar.setLocationRelativeTo(null);
            emar.setVisible(true);

        } catch (Exception e) {

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");

        }

    }//GEN-LAST:event_M_A_M_eliminarActionPerformed

    private void Cancelar_catego2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_catego2ActionPerformed

        ecategoria1.dispose();
    }//GEN-LAST:event_Cancelar_catego2ActionPerformed

    private void B_eliminar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_eliminar_categoriaActionPerformed
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(Agregar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn = cc.conexion();
        int dato = JOptionPane.showConfirmDialog(null, login.Guardausuario + "  ¿Estas seguro de eliminar esta categoría?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (dato == 0) {
            try {
                String cat = combo_eliminar.getSelectedItem().toString();

                try {
                    PreparedStatement pst = cn.prepareStatement("DELETE FROM categoria_productos WHERE categoria_producto='" + cat + "'");
                    pst.executeUpdate();

                    Object[] llenar_combo_categoria_producto = con.combox("categoria_productos", "categoria_producto");

                    combo_eliminar.removeAllItems();

                    for (int i = 0; i < llenar_combo_categoria_producto.length; i++) {

                        combo_eliminar.addItem((String) llenar_combo_categoria_producto[i]);
                    }

                    Object[] llenar_combo_eliminar = con.combox("categoria_productos", "categoria_producto");

                    combocategoriafin.removeAllItems();

                    for (int i = 0; i < llenar_combo_categoria_producto.length; i++) {

                        combocategoriafin.addItem((String) llenar_combo_categoria_producto[i]);
                    }

                } catch (Exception e) {
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ninguna categoría de la lista");

            }
        }
    }//GEN-LAST:event_B_eliminar_categoriaActionPerformed

    private void combo_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_eliminarActionPerformed

    private void combo_eliminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_eliminarKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            B_eliminar_categoria.doClick();
        }
    }//GEN-LAST:event_combo_eliminarKeyTyped

    private void M_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_agregar_categoriaActionPerformed
        ecategoria1.dispose();
        ncategoria1.setModal(true);
        ncategoria1.setLocationRelativeTo(null);
        ncategoria1.setVisible(true);
    }//GEN-LAST:event_M_agregar_categoriaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ncategoria1.setModal(true);
        ncategoria1.setLocationRelativeTo(null);
        ncategoria1.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        nmarca1.setModal(true);
        nmarca1.setLocationRelativeTo(null);
        nmarca1.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_D_A_Categoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_D_A_Categoria1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_D_A_Categoria1ActionPerformed

    private void txt_D_A_Categoria1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_D_A_Categoria1KeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            G_catego1.doClick();
        }
    }//GEN-LAST:event_txt_D_A_Categoria1KeyTyped

    private void Cancelar_catego1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_catego1ActionPerformed
        ncategoria1.dispose();
    }//GEN-LAST:event_Cancelar_catego1ActionPerformed

    private void G_catego1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G_catego1ActionPerformed
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn = cc.conexion();
            String categoria;

            String sql = "";

            categoria = txt_D_A_Categoria1.getText();

            if (categoria.length() == 0) {
                String nl = System.getProperty("line.separator");
                JOptionPane.showMessageDialog(null, "¡Error! El campo de texto esta vacío." + nl + "Por favor ingrese el nombre de la categoria que desea registrar.");
                txt_D_A_Categoria1.requestFocus();
            } else {

                sql = "INSERT INTO categoria_productos (categoria_producto) VALUES (?)";

                try {
                    PreparedStatement pst = cn.prepareStatement(sql);

                    pst.setString(1, categoria);

                    int n = pst.executeUpdate();

                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "Registro guardado con éxito");
                        txt_D_A_Categoria1.setText("");
                        txt_D_A_Categoria1.requestFocus();
                        Object[] llenar_combo_categoria_producto = con.combox("categoria_productos", "categoria_producto");

                        combocategoriafin.removeAllItems();

                        for (int i = 0; i < llenar_combo_categoria_producto.length; i++) {

                            combocategoriafin.addItem((String) llenar_combo_categoria_producto[i]);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error! no se pudo guardar el registro");

                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "¡Error! no ha insertado correctamente los datos");

                }

            }
        } catch (Exception e) {

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");

        }

    }//GEN-LAST:event_G_catego1ActionPerformed

    private void M_ELIMINAR_CATEGORIA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_ELIMINAR_CATEGORIA1ActionPerformed

        try {
            Object[] llenar_combo_categoria_producto = con.combox("categoria_productos", "categoria_producto");

            combo_eliminar.removeAllItems();

            for (int i = 0; i < llenar_combo_categoria_producto.length; i++) {

                combo_eliminar.addItem((String) llenar_combo_categoria_producto[i]);
            }

            ncategoria1.dispose();
            ecategoria1.setModal(true);
            ecategoria1.setLocationRelativeTo(null);
            ecategoria1.setVisible(true);

        } catch (Exception e) {

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null, "¡Error! no ha sido posible conectar ala base de datos");

        }
    }//GEN-LAST:event_M_ELIMINAR_CATEGORIA1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RestrictedTextField restricted = new RestrictedTextField(ztoka);
        restricted.setLimit(2);
        RestrictedTextField restricted2 = new RestrictedTextField(ztoka2);
        restricted2.setLimit(2);

        activatxt.setEnabled(true);
        lblztoka.setVisible(true);
        metiche.setVisible(false);
        activatxt.setSelected(false);
        ztoka.setVisible(true);
        ztoka2.setVisible(true);
        ztoka2.setText("0");
        lblez.setVisible(true);
        lblaz.setVisible(true);
        lblpz.setVisible(true);
        maz.setVisible(true);
        btng1.setVisible(true);
        activatxt.setVisible(true);
        metiche.setVisible(false);

        int fila = tbproducto.getSelectedRow();
        int columna = tbproducto.getSelectedRow();
        String tomarfecha;
        if (fila >= 0) {
            if (tbproducto.getValueAt(fila, columna) == null) {
                JOptionPane.showMessageDialog(null, "¡La fila que seleccionó esta vacía!");
            } else if (fila >= 0 && columna >= 0) {

                lblztoka.setText(tbproducto.getValueAt(fila, 7).toString());
                ztoka.setText(tbproducto.getValueAt(fila, 7).toString());
                ztoka.setVisible(false);
                prostock.setText(tbproducto.getValueAt(fila, 1).toString());

                try {
                    astock.setModal(true);
                    astock.setLocationRelativeTo(null);
                    astock.setVisible(true);
                } catch (Exception ex) {

                    Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else {

            JOptionPane.showMessageDialog(null, "¡No selecionó ninguna fila!");
        }

//astock.setModal(true);
        //     astock.setLocationRelativeTo(null);
        //   astock.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Cancelar_Eliminar_marca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_Eliminar_marca2ActionPerformed
        emar.dispose();
    }//GEN-LAST:event_Cancelar_Eliminar_marca2ActionPerformed

    private void E_marca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_marca2ActionPerformed
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(Agregar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cn = cc.conexion();
        int dato = JOptionPane.showConfirmDialog(null, login.Guardausuario + "  ¿Estas seguro de eliminar esta marca?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (dato == 0) {
            try {
                String cat = combo_eliminar_marca2.getSelectedItem().toString();

                try {
                    PreparedStatement pst = cn.prepareStatement("DELETE FROM marcas_productos WHERE marca_producto='" + cat + "'");
                    pst.executeUpdate();

                    Object[] llenar_combo_marca_producto = con.combox("marcas_productos", "marca_producto");

                    combo_eliminar_marca2.removeAllItems();

                    for (int i = 0; i < llenar_combo_marca_producto.length; i++) {

                        combo_eliminar_marca2.addItem((String) llenar_combo_marca_producto[i]);
                    }

                    Object[] llenar_combo_eliminar = con.combox("marcas_productos", "marca_producto");

                    combomarcafin.removeAllItems();

                    for (int i = 0; i < llenar_combo_marca_producto.length; i++) {

                        combomarcafin.addItem((String) llenar_combo_marca_producto[i]);
                    }

                } catch (Exception e) {
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ninguna marca de la lista");

            }
        }
    }//GEN-LAST:event_E_marca2ActionPerformed

    private void combo_eliminar_marca2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_eliminar_marca2KeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            E_marca2.doClick();
        }
    }//GEN-LAST:event_combo_eliminar_marca2KeyTyped

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        emar.dispose();
        nmarca1.setModal(true);
        nmarca1.setLocationRelativeTo(null);
        nmarca1.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void ztokaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ztokaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ztokaActionPerformed

    private void ztoka2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ztoka2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ztoka2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        RestrictedTextField res = new RestrictedTextField(txtbuxca);
        res.setLimit(6);
        stockbus.setModal(true);
        stockbus.setLocationRelativeTo(null);
        stockbus.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtbuxcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuxcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuxcaActionPerformed

    private void activatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activatxtActionPerformed
        if (activatxt.isEnabled()) {
            lblztoka.setVisible(false);
            ztoka.setVisible(true);
            ztoka2.setText("0");
            ztoka2.setVisible(false);
            activatxt.setEnabled(false);
            maz.setVisible(false);
            lblaz.setVisible(false);

        }
    }//GEN-LAST:event_activatxtActionPerformed

    private void activatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_activatxtKeyReleased

    }//GEN-LAST:event_activatxtKeyReleased

    private void activatxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activatxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_activatxtMouseClicked

    private void btng1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btng1ActionPerformed
        Conexionbd cc = new Conexionbd();

        Connection cn = cc.conexion();
        String ztokactual;

        if (ztoka2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "¡Error! debe colocar algun valor en el campo de texto, si el valor es nulo coloque cero");
            ztoka2.requestFocus();
        } else if (ztoka.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "¡Error! debe colocar algun valor en el campo de texto, si el valor es nulo coloque cero");
            ztoka.requestFocus();
        } else {
            if (activatxt.equals("")) {
                ztokactual = lblztoka.getText();
            } else {
                ztokactual = ztoka.getText();
            }
            String ztoknuevo;

            ztoknuevo = ztoka2.getText();

            if (ztoknuevo.length() == 0) {

                ztoknuevo = "0";

            }

            int n1 = Integer.parseInt(ztokactual);

            int n2 = Integer.parseInt(ztoknuevo);

            int total = n1 + n2;

            String ztokfin = Integer.toString(total);

            System.out.println(n2);

            try {

                PreparedStatement pst = cn.prepareStatement("UPDATE productos SET stock='" + ztokfin + "' WHERE nombre='" + prostock.getText() + "'");

                int n = pst.executeUpdate();

                if (n > 0) {

                    JOptionPane.showMessageDialog(null, "Registro modificado con éxito");

                    mostrardatos("");
                    ztoka.setText("");
                    lblztoka.setText("");
                    ztoka2.setText("");
                    prostock.setText("");
                    activatxt.setSelected(false);
                    activatxt.setEnabled(false);
                    ztoka.setVisible(false);
                    ztoka2.setVisible(false);
                    lblez.setVisible(false);
                    lblaz.setVisible(false);
                    lblpz.setVisible(false);
                    maz.setVisible(false);
                    btng1.setVisible(false);
                    activatxt.setVisible(false);
                    metiche.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(null, "¡Error! no se pudo modificar el registro");

                }

            } catch (Exception e) {

                System.out.println(e);

            }

            gc();

        }
    }//GEN-LAST:event_btng1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        astock.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void ztoka2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ztoka2KeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btng1.doClick();
        }
    }//GEN-LAST:event_ztoka2KeyTyped

    private void ztokaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ztokaKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btng1.doClick();
        }
    }//GEN-LAST:event_ztokaKeyTyped

    private void btnb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnb2ActionPerformed

        String ayuda;

        ayuda = txtbuxca.getText();
        if (ayuda.length() < 6) {
            JOptionPane.showMessageDialog(null, "La clave del producto debe contener 6 caracteres alfanumericos");
            txtbuxca.requestFocus();

        } else {
            try {
                Conexionbd cc = new Conexionbd();
                Connection cn2 = cc.conexion();
                PreparedStatement pe = cn2.prepareStatement("SELECT nombre, stock FROM productos WHERE id_producto=?");
                pe.setString(1, txtbuxca.getText());
                ResultSet rs = pe.executeQuery();
                String tomarfecha;
                if (rs.next()) {

                    prostock.setText(rs.getString("nombre"));
                    ztoka.setText(rs.getString("stock"));
                    lblztoka.setText(rs.getString("stock"));

                    activatxt.setEnabled(true);
                    lblztoka.setVisible(true);
                    metiche.setVisible(false);
                    activatxt.setSelected(false);
                    ztoka.setVisible(false);
                    ztoka2.setVisible(true);
                    ztoka2.setText("0");
                    lblez.setVisible(true);
                    lblaz.setVisible(true);
                    lblpz.setVisible(true);
                    maz.setVisible(true);
                    btng1.setVisible(true);
                    activatxt.setVisible(true);
                    metiche.setVisible(false);

                    txtbuxca.setText("");
                    stockbus.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No existe ningún producto con esa clave");

                }

                cn2.close();

            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Error, no ha sido posible realzar la busqueda");
            }

        }
    }//GEN-LAST:event_btnb2ActionPerformed

    private void txtbuxcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuxcaKeyTyped
        char teclapresionada = evt.getKeyChar();

        if (teclapresionada == KeyEvent.VK_ENTER) {

            btnb2.doClick();
        }
    }//GEN-LAST:event_txtbuxcaKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       
        Principal prin= new Principal();
        
        int fila = tbproducto.getSelectedRow();
              
            
            clave = tbproducto.getValueAt(fila, 0).toString();
             
           
        /*
        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        try {

            int fila = tbproducto.getSelectedRow();

            String clave = "";
            clave = tbproducto.getValueAt(fila, 0).toString();
             ctrlinventario inv = null;
            ConsultarCodigoBarras(clave);
            ImprimirCbx();
            imprimir.setModal(true);
            imprimir.setSize(550, 320);
            imprimir.setResizable(false);
            imprimir.setLocationRelativeTo(null);
            imprimir.setVisible(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ningun ID de la lista de abajo");
            System.out.println(e);
        }
       
       
       */
               

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        LlamarPdf lp = new LlamarPdf();
        LlamarPdf ca = new LlamarPdf();

        String origen = "CBARRA.PDF";

        try {
            lp.CopiarArchivo(origen, "");
        } catch (IOException ex) {
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("CBARRA.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "¡Error! no se pudo realizar la acción");
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
        imprimir.dispose();


    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnCambiarImpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarImpreActionPerformed
        try {
            Conexionbd cc = new Conexionbd();

            Connection cn = cc.conexion();
            String elegir;
            elegir = cbx.getSelectedItem().toString();
            System.out.println(elegir);

            PreparedStatement pst = cn.prepareStatement("UPDATE impresoras SET codi_barr ='" + elegir + "' WHERE impr_id='1'");

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

    }//GEN-LAST:event_btnCambiarImpreActionPerformed

    private void cbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxActionPerformed


    }//GEN-LAST:event_cbxActionPerformed

    private void jMenu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseEntered

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        try {

            int fila = tbproducto.getSelectedRow();

            String clave = "";
            clave = tbproducto.getValueAt(fila, 0).toString();

            ConsultarCodigoBarras(clave);
            ImprimirCbx();
            imprimir.setModal(true);
            imprimir.setSize(550, 320);
            imprimir.setResizable(false);
            imprimir.setLocationRelativeTo(null);
            imprimir.setVisible(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ningun ID de la lista de abajo");
            System.out.println(e);
        }


    }//GEN-LAST:event_jMenu8MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            tbproducto.print();
        } catch (PrinterException ex) {
            Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void clavetxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clavetxtKeyReleased
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnBuscar.doClick();
         }
    }//GEN-LAST:event_clavetxtKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ctrlinventario().setVisible(true);
                BasicConfigurator.configure();
                Properties props = new Properties();

                try {
                    props.load(new FileInputStream("log4j.xml"));
                } catch (IOException ex) {
                    Logger.getLogger(ctrlinventario.class.getName()).log(Level.SEVERE, null, ex);
                }

                props.setProperty("log4j.appender.File", "log4j.xml");
               // props.setProperty("log4j.appender.File.File", "propiedades/" +"log4j.xml");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_eliminar_categoria;
    private javax.swing.JComboBox<String> CBX_SELECCIONAR_BUSQUEDA;
    private javax.swing.JButton Cancelar_Eliminar_marca2;
    private javax.swing.JButton Cancelar_catego1;
    private javax.swing.JButton Cancelar_catego2;
    private javax.swing.JButton Cancelar_marca;
    private javax.swing.JButton E_marca2;
    private javax.swing.JButton G_catego1;
    private javax.swing.JButton G_marca;
    private javax.swing.JMenuItem M_A_M_eliminar;
    private javax.swing.JMenuItem M_ELIMINAR_CATEGORIA1;
    private javax.swing.JMenuItem M_agregar_categoria;
    private javax.swing.JCheckBox activatxt;
    private javax.swing.JDialog astock;
    private javax.swing.JButton atrasIP;
    private javax.swing.JLabel ayudafecha;
    private javax.swing.JLabel ayudanombre;
    private javax.swing.JButton b_buscar;
    public static javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCambiarImpre;
    private javax.swing.JButton btnb2;
    private javax.swing.JButton btng1;
    private javax.swing.JComboBox<String> cbx;
    private javax.swing.JTextField clavetxt;
    private javax.swing.JPopupMenu clickderecho;
    private javax.swing.JComboBox<String> combo_eliminar;
    private javax.swing.JComboBox<String> combo_eliminar_marca2;
    private javax.swing.JDialog ecategoria1;
    private javax.swing.JDialog emar;
    public static javax.swing.JDialog imprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblaz;
    private javax.swing.JLabel lblez;
    private javax.swing.JLabel lblpz;
    private javax.swing.JLabel lblztoka;
    private javax.swing.JLabel maz;
    private javax.swing.JLabel metiche;
    public static final javax.swing.JDialog ncategoria1 = new javax.swing.JDialog();
    public static final javax.swing.JDialog nmarca1 = new javax.swing.JDialog();
    private com.toedter.calendar.JDateChooser porfecha;
    private javax.swing.JComboBox<String> pormarcacatego;
    private javax.swing.JTextField pornombre;
    private javax.swing.JLabel prostock;
    private javax.swing.JDialog stockbus;
    public static javax.swing.JTable tbproducto;
    private javax.swing.JTextField txt_D_A_Categoria1;
    private javax.swing.JTextField txt_D_A_marca;
    private javax.swing.JTextField txtbuxca;
    private javax.swing.JTextField ztoka;
    private javax.swing.JTextField ztoka2;
    // End of variables declaration//GEN-END:variables

    public void soloNumeros_stock(JTextField a) {
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

    public void ImprimirCbx() {

        cbx.removeAllItems();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Tu impresora por default es: " + service.getName());
        for (PrintService printService : services) {
            System.out.println(" ---- IMPRESORA: " + printService.getName());
            if (printService.getName() == service.getName()) {
                cbx.addItem(printService.getName()/*+ " *"*/);
            } else {
                cbx.addItem(printService.getName());
            }

        }
        try {
            Conexionbd cc = new Conexionbd();
            Connection cn2 = cc.conexion();
            PreparedStatement pe = cn2.prepareStatement("SELECT codi_barr FROM impresoras WHERE impr_id=1");
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
                JOptionPane.showMessageDialog(null, "No existe un producto con esta clave");

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
            Connection cn = cc.conexion();

            //2. Se llena el reporte con la informacion necesaria
            Map pars = new HashMap();
            pars.put("crearBarras", id);

            System.out.println(id);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "CBARRA.jasper", pars, cn);

            //dialogocargando extends JDialog
            //3. Se exporta a PDF
            try {
                JasperExportManager.exportReportToPdfFile(
                        jasperPrint, "CBARRA.pdf");
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
     public static void botonenter(JButton uno) {   //para que haga que cuando este seleccionado el boton guardar con enter funcione y no con espacio

        uno.registerKeyboardAction(uno.getActionForKeyStroke(

                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),

                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),

                                      JComponent.WHEN_FOCUSED);

        uno.registerKeyboardAction(uno.getActionForKeyStroke(

                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),

                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),

                                      JComponent.WHEN_FOCUSED);
}
}