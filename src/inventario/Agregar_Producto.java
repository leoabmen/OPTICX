/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;



import validaciones.limitar_carac_textarea;
import java.sql.Connection;
import java.sql.ResultSet;
import opticx1.Conexionbd;
import opticx1.control_existencias;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import validaciones.solo_numeros;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import validaciones.Limitar_caracteres;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Exception;
import static java.lang.System.gc;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import opticx1.login;
import java.util.logging.Level;
import java.util.logging.Logger;
import opticx1.conexion;


public class Agregar_Producto extends javax.swing.JFrame {
    
    

    Conexionbd cc= new Conexionbd();

    Connection cn= cc.conexion();

    

    control_existencias con = new control_existencias();
    
    ResultSet rs;
    String fehaactual;

//Variables para conectar
     public Agregar_Producto() {
        initComponents();
         Calendar c2 = new GregorianCalendar();
        calendariofin.setCalendar(c2);
        this. setLocationRelativeTo(null);

        //conexion
        setResizable(false);
        setTitle("SiGOO");

        try{

        Object[] llenar_combo_marca_producto = con.combox("marcas_productos","marca_producto");

        combomarcafin.removeAllItems();

        for(int i=0;i<llenar_combo_marca_producto.length;i++){

        combomarcafin.addItem((String) llenar_combo_marca_producto[i]);

        }

        Object[] llenar_combo_categoria_producto = con.combox("categoria_productos","categoria_producto");

        combocategoriafin.removeAllItems();

        for(int i=0;i<llenar_combo_categoria_producto.length;i++){

        combocategoriafin.addItem((String) llenar_combo_categoria_producto[i]);

        }

        
       // txtnombrepro.setDocument(new Solo_mayusculas()); Ya ni me esta funcionando este metodo ya que al limitar caracteres,

        //afectaba a este metodo y regresaban a minusculas es decir nunca funcionaban juntos.

        

        Limitar_caracteres_cajastexto(); //limitar caracteres en campos de texto

        
        //metodo nuevo para convertir en mayusculas sin que afecte el limite de caracteres

        mayusculas(txtid_profin);
        mayusculas(txtnombreprofin);
        mayusculas(txtcolorprofin);
        mayusculas_textarea(txtdescripcionprofin);
        //jDialog
        mayusculas(txt_D_A_Categoria);
        mayusculas(txt_D_A_marca);

        solo_numeros.solonumeros(txtpreciocompraprofin);
        solo_numeros.solonumeros(txtprecioventaprofin);
        
        soloNumeros_stock(txtstockprofin);

        sololetras(txtnombreprofin);
        sololetras(txtcolorprofin);
        
        //jDialog
        sololetras(txt_D_A_Categoria);
        sololetras(txt_D_A_marca);

        obtenerfecha();
        
        botonenter();
      
       }catch (Exception e){
             JOptionPane.showMessageDialog(null,"¡Error! no se ha podido acceder ala base de datos");
           }  
    }
      public void limpiar()

    {
       txtid_profin.setText("");
       txtnombreprofin.setText("");
       txtcolorprofin.setText("");
       txtprecioventaprofin.setText("");
       txtpreciocompraprofin.setText("");
       txtstockprofin.setText("");
       txtdescripcionprofin.setText("");
       combocategoriafin.setSelectedIndex(0);
       combomarcafin.setSelectedIndex(0);
       Calendar c2 = new GregorianCalendar();
       calendariofin.setCalendar(c2);
       txtid_profin.requestFocus();
    }
      public void Limitar_caracteres_cajastexto(){
           txtid_profin.setDocument(new Limitar_caracteres(txtid_profin,6));
           txtnombreprofin.setDocument(new Limitar_caracteres(txtnombreprofin,40));
           txtcolorprofin.setDocument(new Limitar_caracteres(txtcolorprofin,20));
           txtprecioventaprofin.setDocument(new Limitar_caracteres(txtprecioventaprofin,9));
           txtpreciocompraprofin.setDocument(new Limitar_caracteres(txtpreciocompraprofin,9));
           txtstockprofin.setDocument(new Limitar_caracteres(txtstockprofin,2));
           txtdescripcionprofin.setDocument(new limitar_carac_textarea(txtdescripcionprofin,120));
           
           //jdialog1
           txt_D_A_Categoria.setDocument(new limitar_carac_textarea(txtdescripcionprofin,30));
           txt_D_A_marca.setDocument(new limitar_carac_textarea(txtdescripcionprofin,30));
           
           
}
        public void sololetras(JTextField a){
           a.addKeyListener(new KeyAdapter(){
               
        public void keyTyped(KeyEvent e){
              char c=e.getKeyChar();
               if(Character.isDigit(c)){
               getToolkit().beep();
         e.consume();

     }

  }

   });
        }

         public void soloNumeros_stock(JTextField a){
      a.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e){
            char c=e.getKeyChar();
           if(!Character.isDigit(c)){
            getToolkit().beep();
         e.consume();

     }

  }

   });
        }

         public void mayusculas(JTextField e){
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
         public void mayusculas_textarea(JTextArea e){
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

        System.out.println("Fecha Actual: "+ dia + "/" + (mes+1) + "/" + año);

        System.out.printf("Hora Actual: %02d:%02d:%02d %n",hora, minuto, segundo);

    }
         
          private void rellenarCombomarca() {      
       try{
            while(rs.next()){
     combomarcafin.addItem(rs.getString(2));
       }
       }
    catch (Exception e){
        JOptionPane.showMessageDialog(null,"¡Error! no se pudo conectar a el registro de marcas");
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txt_D_A_Categoria = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Cancelar_catego = new javax.swing.JButton();
        G_catego = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        M_ELIMINAR_CATEGORIA = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jPanel4 = new javax.swing.JPanel();
        txt_D_A_marca = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Cancelar_marca = new javax.swing.JButton();
        G_marca = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        M_A_M_eliminar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ecategoria = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Cancelar_catego2 = new javax.swing.JButton();
        B_eliminar_categoria = new javax.swing.JButton();
        combo_eliminar = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        M_agregar_categoria = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        emarca = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        Cancelar_Eliminar_marca = new javax.swing.JButton();
        E_marca = new javax.swing.JButton();
        combo_eliminar_marca = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        astock = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtprostock = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        btnatras = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        ncategoria.setMinimumSize(new java.awt.Dimension(360, 290));
        ncategoria.setType(java.awt.Window.Type.UTILITY);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_D_A_Categoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_D_A_Categoria.setForeground(new java.awt.Color(0, 0, 102));
        txt_D_A_Categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_D_A_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_D_A_CategoriaActionPerformed(evt);
            }
        });
        txt_D_A_Categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_D_A_CategoriaKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("AGREGAR CATEGORÍA");

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

        G_catego.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        G_catego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar602.png"))); // NOI18N
        G_catego.setMnemonic('G');
        G_catego.setText("GUARDAR");
        G_catego.setBorderPainted(false);
        G_catego.setContentAreaFilled(false);
        G_catego.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        G_catego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G_catego.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        G_catego.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar601.png"))); // NOI18N
        G_catego.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G_catego.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G_catego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G_categoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar_catego)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G_catego)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txt_D_A_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(69, 69, 69))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_D_A_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_catego)
                    .addComponent(G_catego))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N

        jMenuBar1.setBackground(new java.awt.Color(102, 0, 204));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("ARCHIVO");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        M_ELIMINAR_CATEGORIA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        M_ELIMINAR_CATEGORIA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/BASURA3.png"))); // NOI18N
        M_ELIMINAR_CATEGORIA.setText("ELIMINAR CATEGORÍA");
        M_ELIMINAR_CATEGORIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_ELIMINAR_CATEGORIAActionPerformed(evt);
            }
        });
        jMenu1.add(M_ELIMINAR_CATEGORIA);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        ncategoria.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout ncategoriaLayout = new javax.swing.GroupLayout(ncategoria.getContentPane());
        ncategoria.getContentPane().setLayout(ncategoriaLayout);
        ncategoriaLayout.setHorizontalGroup(
            ncategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ncategoriaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel16)
        );
        ncategoriaLayout.setVerticalGroup(
            ncategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ncategoriaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel16)
        );

        nmarca.setMinimumSize(new java.awt.Dimension(360, 290));
        nmarca.setType(java.awt.Window.Type.UTILITY);
        nmarca.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar_marca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G_marca)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txt_D_A_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(83, 83, 83))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_D_A_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_marca)
                    .addComponent(G_marca))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        nmarca.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4M.png"))); // NOI18N
        nmarca.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 290));

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

        nmarca.setJMenuBar(jMenuBar2);

        ecategoria.setMinimumSize(new java.awt.Dimension(360, 290));
        ecategoria.setType(java.awt.Window.Type.UTILITY);
        ecategoria.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar_catego2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B_eliminar_categoria)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combo_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_catego2)
                    .addComponent(B_eliminar_categoria))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        ecategoria.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N
        ecategoria.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, 290));

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

        ecategoria.setJMenuBar(jMenuBar3);

        emarca.setMinimumSize(new java.awt.Dimension(360, 290));
        emarca.setType(java.awt.Window.Type.UTILITY);
        emarca.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 51));
        jLabel21.setText("ELIMINAR MARCA");

        Cancelar_Eliminar_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar_Eliminar_marca.setForeground(new java.awt.Color(204, 0, 51));
        Cancelar_Eliminar_marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR3.png"))); // NOI18N
        Cancelar_Eliminar_marca.setMnemonic('C');
        Cancelar_Eliminar_marca.setText("CANCELAR");
        Cancelar_Eliminar_marca.setBorderPainted(false);
        Cancelar_Eliminar_marca.setContentAreaFilled(false);
        Cancelar_Eliminar_marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar_Eliminar_marca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cancelar_Eliminar_marca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR4.png"))); // NOI18N
        Cancelar_Eliminar_marca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/SALIR2.png"))); // NOI18N
        Cancelar_Eliminar_marca.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cancelar_Eliminar_marca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cancelar_Eliminar_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_Eliminar_marcaActionPerformed(evt);
            }
        });

        E_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        E_marca.setForeground(new java.awt.Color(204, 0, 0));
        E_marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar602.png"))); // NOI18N
        E_marca.setMnemonic('E');
        E_marca.setText("ELIMINAR");
        E_marca.setBorderPainted(false);
        E_marca.setContentAreaFilled(false);
        E_marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        E_marca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        E_marca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        E_marca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar601.png"))); // NOI18N
        E_marca.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        E_marca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        E_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E_marcaActionPerformed(evt);
            }
        });

        combo_eliminar_marca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combo_eliminar_marca.setForeground(new java.awt.Color(0, 0, 153));
        combo_eliminar_marca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_eliminar_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                combo_eliminar_marcaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Cancelar_Eliminar_marca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E_marca))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel21)
                        .addGap(0, 77, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(combo_eliminar_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_eliminar_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_Eliminar_marca)
                    .addComponent(E_marca))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        emarca.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4M.png"))); // NOI18N
        emarca.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));

        jMenuBar4.setBackground(new java.awt.Color(0, 0, 204));
        jMenuBar4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu7.setForeground(new java.awt.Color(255, 255, 255));
        jMenu7.setText("ARCHIVO");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pacientes/pacientes img/guardar603.png"))); // NOI18N
        jMenuItem4.setText("AGREGAR MARCA");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem4);

        jMenuBar4.add(jMenu7);
        jMenuBar4.add(jMenu8);

        emarca.setJMenuBar(jMenuBar4);

        astock.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("PRODUCTO:");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        txtprostock.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtprostock.setForeground(new java.awt.Color(153, 0, 0));
        jPanel7.add(txtprostock, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 210, 17));

        jCheckBox1.setFont(new java.awt.Font("Arial", 1, 8)); // NOI18N
        jCheckBox1.setText("MODIFICAR STOCK ACTUAL");
        jPanel7.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 61, 28));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 62, 28));

        jButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/SALIR3.png"))); // NOI18N
        jButton6.setMnemonic('S');
        jButton6.setText("SALIR");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setIconTextGap(-4);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/SALIR4.png"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/SALIR2.png"))); // NOI18N
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/guardar602.png"))); // NOI18N
        jButton7.setText("GUARDAR");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setIconTextGap(-4);
        jButton7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/guardar603.png"))); // NOI18N
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/guardar601.png"))); // NOI18N
        jButton7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("EN STOCK:");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText("AGREGAR A STOCK:");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("ACTUALIZAR STOCK DE PRODUCTOS");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, 30));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 0, 0));
        jLabel28.setText("+");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        astock.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 340, 240));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGOASTOCK2.png"))); // NOI18N
        astock.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(650, 600));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setText("INGRESAR NUEVO PRODUCTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 270, 30));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("CLAVE:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 44, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("STOCK:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 95, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("CATEGORÍA:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 44, -1, -1));

        txtid_profin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtid_profin.setForeground(new java.awt.Color(204, 0, 0));
        txtid_profin.setToolTipText("COLOCA 6 CARACTERES ALFANUMÉRICOS");
        txtid_profin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_profinActionPerformed(evt);
            }
        });
        txtid_profin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtid_profinKeyTyped(evt);
            }
        });
        jPanel2.add(txtid_profin, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 38, 80, 30));

        txtstockprofin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtstockprofin.setForeground(new java.awt.Color(204, 0, 0));
        txtstockprofin.setToolTipText("MÁXIMO 99");
        txtstockprofin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockprofinKeyTyped(evt);
            }
        });
        jPanel2.add(txtstockprofin, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 89, 30, 30));

        combocategoriafin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combocategoriafin.setForeground(new java.awt.Color(0, 0, 102));
        combocategoriafin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(combocategoriafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 37, 131, 30));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/CATEGORIA.png"))); // NOI18N
        jButton1.setMnemonic('N');
        jButton1.setToolTipText("NUEVA CATEGORÍA");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 31, 60, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("NOMBRE:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("MARCA:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("COLOR:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("PRECIO DE COMPRA:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 150, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("PRECIO DE VENTA:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 130, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("FECHA DE COMPRA:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("DESCRIPCIÓN:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        txtnombreprofin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtnombreprofin.setForeground(new java.awt.Color(204, 0, 0));
        txtnombreprofin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreprofinKeyTyped(evt);
            }
        });
        jPanel2.add(txtnombreprofin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 138, 250, 30));

        txtcolorprofin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtcolorprofin.setForeground(new java.awt.Color(204, 0, 0));
        txtcolorprofin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcolorprofinKeyTyped(evt);
            }
        });
        jPanel2.add(txtcolorprofin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 170, 30));

        txtprecioventaprofin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtprecioventaprofin.setForeground(new java.awt.Color(204, 0, 0));
        txtprecioventaprofin.setToolTipText("FORMATO 00.00");
        txtprecioventaprofin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioventaprofinKeyTyped(evt);
            }
        });
        jPanel2.add(txtprecioventaprofin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 90, 30));

        txtpreciocompraprofin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtpreciocompraprofin.setForeground(new java.awt.Color(204, 0, 0));
        txtpreciocompraprofin.setToolTipText("FORMATO 00.00");
        txtpreciocompraprofin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreciocompraprofinKeyTyped(evt);
            }
        });
        jPanel2.add(txtpreciocompraprofin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 90, 30));

        calendariofin.setForeground(new java.awt.Color(0, 0, 102));
        calendariofin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.add(calendariofin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 170, 30));

        combomarcafin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combomarcafin.setForeground(new java.awt.Color(0, 0, 102));
        combomarcafin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combomarcafin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combomarcafinActionPerformed(evt);
            }
        });
        jPanel2.add(combomarcafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 170, 30));

        txtdescripcionprofin.setColumns(20);
        txtdescripcionprofin.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtdescripcionprofin.setForeground(new java.awt.Color(204, 0, 0));
        txtdescripcionprofin.setRows(5);
        txtdescripcionprofin.setToolTipText("MÁXIMO 200 CARACTERES");
        txtdescripcionprofin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionprofinKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtdescripcionprofin);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 330, 70));

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/MARCA.png"))); // NOI18N
        jButton2.setMnemonic('M');
        jButton2.setToolTipText("NUEVA MARCA");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 60, 40));

        btnatras.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnatras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS2.png"))); // NOI18N
        btnatras.setMnemonic('A');
        btnatras.setText("ATRAS");
        btnatras.setBorderPainted(false);
        btnatras.setContentAreaFilled(false);
        btnatras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnatras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnatras.setIconTextGap(-4);
        btnatras.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS3.png"))); // NOI18N
        btnatras.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS1.png"))); // NOI18N
        btnatras.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnatras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatrasActionPerformed(evt);
            }
        });
        jPanel2.add(btnatras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        btnguardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/AGREGAR2.png"))); // NOI18N
        btnguardar.setMnemonic('G');
        btnguardar.setText("GUARDAR");
        btnguardar.setBorderPainted(false);
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setIconTextGap(-4);
        btnguardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/AGREGAR3.png"))); // NOI18N
        btnguardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/AGREGAR1.png"))); // NOI18N
        btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnguardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("$");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("$");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, -1, -1));

        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BUSCAR2.png"))); // NOI18N
        jButton3.setMnemonic('B');
        jButton3.setText("BUSCAR");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(-4);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BUSCAR3.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/BUSCAR1.png"))); // NOI18N
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, -1, -1));

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif2.png"))); // NOI18N
        jButton4.setMnemonic('M');
        jButton4.setText("MODIFICAR");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setIconTextGap(-4);
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif3.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif1.png"))); // NOI18N
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, -1));

        jButton5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/limpiar2.png"))); // NOI18N
        jButton5.setMnemonic('L');
        jButton5.setText("LIMPIAR");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setIconTextGap(-4);
        jButton5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/limpiar3.png"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/limpiar1.png"))); // NOI18N
        jButton5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 610, 550));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/650 x 600.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtid_profinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_profinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_profinActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
             ncategoria.setModal(true);
             ncategoria.setLocationRelativeTo(null);
             ncategoria.setVisible(true);
             
    
             
  

        

  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnatrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatrasActionPerformed
        // boton de regreso al menu 2
     try{
        ctrlinventario obj=new ctrlinventario();
        obj.setVisible(true);
       // obj.pack();
        dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! "+e);
        }
    }//GEN-LAST:event_btnatrasActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        //boton agregar
        Conexionbd cc =new Conexionbd();
        Connection cn= cc.conexion();      
String id,nom,color,pv,pc,des,stock,catego,marca;
String formato="yyyy/MM/dd";

  Date date = calendariofin.getDate();
  SimpleDateFormat sdf = new SimpleDateFormat(formato); 
  String fechafin=sdf.format(date);
  String sqlcomparar="";
  String valor;
  String sql="";
  
   id=txtid_profin.getText();

          if(id.length()<6){
             String nl = System.getProperty("line.separator");
                     JOptionPane.showMessageDialog(null,"¡Error! La clave del producto debe contener 6 caracteres almanumericos"+nl+"                             "

           + "Por favor verifique el tamaño"); 

   txtid_profin.requestFocus();
}else{
stock=txtstockprofin.getText();
if(stock.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar la cantidad de productos que conformaran su stock"); 
    txtstockprofin.requestFocus();
}else{
nom=txtnombreprofin.getText();
if(nom.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el nombre del producto"); 
    txtnombreprofin.requestFocus();
}else{
color=txtcolorprofin.getText();
if(color.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el color del producto"); 
    txtcolorprofin.requestFocus();
}else{
des=txtdescripcionprofin.getText();
if(des.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar la descripción del producto"); 
    txtdescripcionprofin.requestFocus();
}else{
pc=txtpreciocompraprofin.getText();
if(pc.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de compra del producto"); 
    txtpreciocompraprofin.requestFocus();
}else{
pv=txtprecioventaprofin.getText();
if(pv.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de venta del producto"); 
    txtprecioventaprofin.requestFocus();
}else{
catego=combocategoriafin.getSelectedItem().toString();
marca=combomarcafin.getSelectedItem().toString();

sqlcomparar="SELECT id_producto FROM productos WHERE id_producto ='"+id+"'";

sql="INSERT INTO productos (id_producto,nombre,color,marca,precio_compra,precio_venta,fecha_compra,stock,descripcion,categoria) VALUES (?,?,?,?,?,?,?,?,?,?)";

    String []datos = new String [1];
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlcomparar);
            while(rs.next()){
                datos[0]=rs.getString(1);
                }
            valor=datos[0];
            
            System.out.println(valor);
            System.out.println(id);
            if (datos[0]==null){               
                try{
             PreparedStatement pst=cn.prepareStatement(sql);
             
             pst.setString(1,id);
             pst.setString(2,nom);
             pst.setString(3,color);
             pst.setString(4,marca);
             pst.setString(5,pc);
             pst.setString(6,pv);
             pst.setString(7,fechafin);
             pst.setString(8,stock);
             pst.setString(9,des);
             pst.setString(10,catego);

            int n=pst.executeUpdate();
            
               if(n>0){
            JOptionPane.showMessageDialog(null,"Registro guardado con éxito");
                          limpiar();
          }else{
               JOptionPane.showMessageDialog(null,"¡Error! no se pudo guardar el registro");

                    }
               
             }catch (Exception e){

            String nl = System.getProperty("line.separator");
            JOptionPane.showMessageDialog(null,"¡Error! no ha insertado correctamente los datos");

             }    

            }
            
        else{

           JOptionPane.showMessageDialog(null,"¡Error! La clave del producto que ha ingresado ya existe");

        }

        }catch (Exception e){

            String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null,"¡Error! no ha sido posible conectar ala base de datos");

        }

}   
}
}
}
}
}
}

    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtid_profinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_profinKeyTyped
        char teclapresionada=evt.getKeyChar();
        
         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtid_profinKeyTyped

    private void txtstockprofinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockprofinKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtstockprofinKeyTyped

    private void txtnombreprofinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreprofinKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtnombreprofinKeyTyped

    private void txtcolorprofinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcolorprofinKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtcolorprofinKeyTyped

    private void txtpreciocompraprofinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciocompraprofinKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtpreciocompraprofinKeyTyped

    private void txtprecioventaprofinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioventaprofinKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtprecioventaprofinKeyTyped

    private void txtdescripcionprofinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionprofinKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_TAB){
             txtpreciocompraprofin.requestFocus();

         }
    }//GEN-LAST:event_txtdescripcionprofinKeyTyped

    private void G_categoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G_categoActionPerformed
        try{
        Conexionbd cc =new Conexionbd();
        Connection cn= cc.conexion();      
          String categoria;
   
  String sql="";
  
   categoria=txt_D_A_Categoria.getText();

          if(categoria.length()==0){
             String nl = System.getProperty("line.separator");
                     JOptionPane.showMessageDialog(null,"¡Error! El campo de texto esta vacío."+nl + "Por favor ingrese el nombre de la categoria que desea registrar."); 
              txt_D_A_Categoria.requestFocus();
          }
          else{

            sql="INSERT INTO categoria_productos (categoria_producto) VALUES (?)";
            

                  
                try{
             PreparedStatement pst=cn.prepareStatement(sql);
             
             
             pst.setString(1,categoria);
            
            int n=pst.executeUpdate();
            
               if(n>0){
            JOptionPane.showMessageDialog(null,"Registro guardado con éxito");
                       txt_D_A_Categoria.setText("");
                      txt_D_A_Categoria.requestFocus();
                       Object[] llenar_combo_categoria_producto = con.combox("categoria_productos","categoria_producto");

                  combocategoriafin.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combocategoriafin.addItem((String) llenar_combo_categoria_producto[i]);
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
        
        
        

    }//GEN-LAST:event_G_categoActionPerformed

    private void txt_D_A_CategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_D_A_CategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_D_A_CategoriaActionPerformed

    private void Cancelar_categoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_categoActionPerformed
        ncategoria.dispose();
    }//GEN-LAST:event_Cancelar_categoActionPerformed

    private void M_ELIMINAR_CATEGORIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_ELIMINAR_CATEGORIAActionPerformed
             
        
              try{
                  Object[] llenar_combo_categoria_producto = con.combox("categoria_productos","categoria_producto");

                  combo_eliminar.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combo_eliminar.addItem((String) llenar_combo_categoria_producto[i]);
                   }
         
        ncategoria.dispose();
             ecategoria.setModal(true);
             ecategoria.setLocationRelativeTo(null);
             ecategoria.setVisible(true);
             
             
                   
                   }catch (Exception e){

            String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null,"¡Error! no ha sido posible conectar ala base de datos");

        }
    }//GEN-LAST:event_M_ELIMINAR_CATEGORIAActionPerformed

    private void txt_D_A_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_D_A_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_D_A_marcaActionPerformed

    private void Cancelar_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_marcaActionPerformed
        nmarca.dispose();
    }//GEN-LAST:event_Cancelar_marcaActionPerformed

    private void G_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G_marcaActionPerformed
         try{
        Conexionbd cc =new Conexionbd();
        Connection cn= cc.conexion();      
          String marca;
   
  String sql="";
  
   marca=txt_D_A_marca.getText();

          if(marca.length()==0){
             String nl = System.getProperty("line.separator");
                     JOptionPane.showMessageDialog(null,"¡Error! El campo de texto esta vacío."+nl + "Por favor ingrese el nombre de la marca que desea registrar."); 
              txt_D_A_marca.requestFocus();
          }
          else{

            sql="INSERT INTO marcas_productos (marca_producto) VALUES (?)";
            

                  
                try{
             PreparedStatement pst=cn.prepareStatement(sql);
             pst.setString(1,marca);
            
            int n=pst.executeUpdate();
            
               if(n>0){
            JOptionPane.showMessageDialog(null,"Registro guardado con éxito");
                       txt_D_A_marca.setText("");
                       txt_D_A_marca.requestFocus();
                      
                        Object[] llenar_combo_marca_producto = con.combox("marcas_productos","marca_producto");

                      combomarcafin.removeAllItems();

                      for(int i=0;i<llenar_combo_marca_producto.length;i++){

                      combomarcafin.addItem((String) llenar_combo_marca_producto[i]);

                   }
                   
                   
 
          }else{
               JOptionPane.showMessageDialog(null,"¡Error! no se pudo guardar el registro");

                    }
               
             }catch (Exception e){

            JOptionPane.showMessageDialog(null,"¡Error! Hubo un problema con la tabla que contiene la informacion de marcas en la base los datos");

             }    

          }
           }catch (Exception e){

            String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null,"¡Error! no ha sido posible conectar ala base de datos");

        }
        
    }//GEN-LAST:event_G_marcaActionPerformed

    private void M_A_M_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_A_M_eliminarActionPerformed
         try{
                  Object[] llenar_combo_categoria_producto = con.combox("marcas_productos","marca_producto");

                  combo_eliminar_marca.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combo_eliminar_marca.addItem((String) llenar_combo_categoria_producto[i]);
                   }
         
        nmarca.dispose();
             emarca.setModal(true);
             emarca.setLocationRelativeTo(null);
             emarca.setVisible(true);
             
             
                   
                   }catch (Exception e){

            String nl = System.getProperty("line.separator");
             JOptionPane.showMessageDialog(null,"¡Error! no ha sido posible conectar ala base de datos");

        }

        
    }//GEN-LAST:event_M_A_M_eliminarActionPerformed

    private void Cancelar_catego2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_catego2ActionPerformed
         
                   ecategoria.dispose();
    }//GEN-LAST:event_Cancelar_catego2ActionPerformed

    private void B_eliminar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_eliminar_categoriaActionPerformed
         Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(Agregar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    Connection cn= cc.conexion();
    int dato=JOptionPane.showConfirmDialog(null,login.Guardausuario +"  ¿Estas seguro de eliminar esta categoría?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (dato==0){
           try{
        String cat=combo_eliminar.getSelectedItem().toString();
    
    
    try {
        PreparedStatement pst = cn.prepareStatement("DELETE FROM categoria_productos WHERE categoria_producto='"+cat+"'");
        pst.executeUpdate();
        
        Object[] llenar_combo_categoria_producto = con.combox("categoria_productos","categoria_producto");

                  combo_eliminar.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combo_eliminar.addItem((String) llenar_combo_categoria_producto[i]);
                   }
                   
                   Object[] llenar_combo_eliminar = con.combox("categoria_productos","categoria_producto");

                  combocategoriafin.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combocategoriafin.addItem((String) llenar_combo_categoria_producto[i]);
                   }
                   
    } catch (Exception e) {
    }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! no ha seleccionado ninguna categoría de la lista");
            
        }
        }
    }//GEN-LAST:event_B_eliminar_categoriaActionPerformed

    private void M_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_agregar_categoriaActionPerformed
             ecategoria.dispose();
             ncategoria.setModal(true);
             ncategoria.setLocationRelativeTo(null);
             ncategoria.setVisible(true);

    }//GEN-LAST:event_M_agregar_categoriaActionPerformed

    private void Cancelar_Eliminar_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_Eliminar_marcaActionPerformed
        emarca.dispose();
    }//GEN-LAST:event_Cancelar_Eliminar_marcaActionPerformed

    private void E_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E_marcaActionPerformed
        Conexionbd cc = null;
        try {
            cc = new Conexionbd();
        } catch (Exception ex) {
            Logger.getLogger(Agregar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    Connection cn= cc.conexion();
    int dato=JOptionPane.showConfirmDialog(null,login.Guardausuario +"  ¿Estas seguro de eliminar esta marca?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (dato==0){
           try{
        String cat=combo_eliminar_marca.getSelectedItem().toString();
    
    
    try {
        PreparedStatement pst = cn.prepareStatement("DELETE FROM marcas_productos WHERE marca_producto='"+cat+"'");
        pst.executeUpdate();
        
        Object[] llenar_combo_marca_producto = con.combox("marcas_productos","marca_producto");

                  combo_eliminar_marca.removeAllItems();

                   for(int i=0;i<llenar_combo_marca_producto.length;i++){

                  combo_eliminar_marca.addItem((String) llenar_combo_marca_producto[i]);
                   }
                   
                   Object[] llenar_combo_eliminar = con.combox("marcas_productos","marca_producto");

                  combomarcafin.removeAllItems();

                   for(int i=0;i<llenar_combo_marca_producto.length;i++){

                  combomarcafin.addItem((String) llenar_combo_marca_producto[i]);
                   }
                   
    } catch (Exception e) {
    }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! no ha seleccionado ninguna marca de la lista");
            
        }
        }
    }//GEN-LAST:event_E_marcaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
             emarca.dispose();
             nmarca.setModal(true);
             nmarca.setLocationRelativeTo(null);
             nmarca.setVisible(true);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        emarca.dispose();
             nmarca.setModal(true);
             nmarca.setLocationRelativeTo(null);
             nmarca.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_D_A_CategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_D_A_CategoriaKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        G_catego.doClick();
         }
    }//GEN-LAST:event_txt_D_A_CategoriaKeyTyped

    private void txt_D_A_marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_D_A_marcaKeyTyped
       char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        G_marca.doClick();
         }
    }//GEN-LAST:event_txt_D_A_marcaKeyTyped

    private void combo_eliminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_eliminarKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        B_eliminar_categoria.doClick();
         }
    }//GEN-LAST:event_combo_eliminarKeyTyped

    private void combo_eliminar_marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combo_eliminar_marcaKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        E_marca.doClick();
         }
    }//GEN-LAST:event_combo_eliminar_marcaKeyTyped

    private void combo_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_eliminarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // boton buscar
       try {
            Connection cn2= cc.conexion();
           PreparedStatement pe = cn2.prepareStatement("SELECT id_producto, nombre, color, marca, precio_compra, precio_venta, fecha_compra, stock, categoria, descripcion FROM productos WHERE id_producto=?");
            pe.setString(1, txtid_profin.getText());
           ResultSet rs = pe.executeQuery();
            String tomarfecha;
            if (rs.next()) {
                txtid_profin.setText(rs.getString("id_producto"));
                txtnombreprofin.setText(rs.getString("nombre"));
                txtcolorprofin.setText(rs.getString("color"));
                combomarcafin.setSelectedItem(rs.getString("marca").toString());
                txtpreciocompraprofin.setText(rs.getString("precio_compra"));
                txtprecioventaprofin.setText(rs.getString("precio_venta"));
                
                tomarfecha=rs.getString("fecha_compra");
    System.out.println("Entrada->" + tomarfecha);
    DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = null;
 
    try {
        date = inputFormatter.parse(tomarfecha);
    }catch (ParseException pex) {
        pex.printStackTrace();
    }
 
    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
    String salida = outputFormatter.format(date);
    System.out.println("Salida->" + salida);
    calendariofin.setDate(date); 
                
                txtstockprofin.setText(rs.getString("stock"));
                combocategoriafin.setSelectedItem(rs.getString("categoria").toString());
                txtdescripcionprofin.setText(rs.getString("descripcion"));
                
                
                

            } else {
                JOptionPane.showMessageDialog(null, "No existe un producto con esta clave");
               
            }

            cn2.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       
        limpiar();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
String formato="yyyy/MM/dd";

  java.util.Date date = calendariofin.getDate();

  SimpleDateFormat sdf = new SimpleDateFormat(formato); 

  String fechafin=sdf.format(date);
  
  String ztok;
  
  String nom,color,des,pc,pv,id;

id=txtid_profin.getText();

          if(id.length()<6){
             String nl = System.getProperty("line.separator");
                     JOptionPane.showMessageDialog(null,"¡Error!  Por favor ingrese la clave del producto que desea modificar"); 

   txtid_profin.requestFocus();
   
          }else{
nom=txtnombreprofin.getText();
if(nom.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el nombre del producto"); 
    txtnombreprofin.requestFocus();
}else{
color=txtcolorprofin.getText();
if(color.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el color del producto"); 
    txtcolorprofin.requestFocus();
}else{
des=txtdescripcionprofin.getText();
if(des.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar la descripción del producto"); 
    txtdescripcionprofin.requestFocus();
}else{
pc=txtpreciocompraprofin.getText();
if(pc.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de compra del producto"); 
    txtpreciocompraprofin.requestFocus();
}else{
pv=txtprecioventaprofin.getText();
if(pv.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de venta del producto"); 
    txtprecioventaprofin.requestFocus();
}else{
 ztok=txtstockprofin.getText();
if(ztok.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el  stock del producto,"+nl+"si la cantidad es nula por favor coloque 0"); 
    txtstockprofin.requestFocus();
    return;
}
int ztokfin=Integer.parseInt(ztok);
 System.out.println(ztok);
 try {//boton agregar

        Conexionbd cc =new Conexionbd();

        Connection cn= cc.conexion();  

        PreparedStatement pst = cn.prepareStatement("UPDATE productos SET nombre='"+nom+"',color='"+color+"',marca='"+combomarcafin.getSelectedItem().toString()+"',precio_compra='"+pc+"',precio_venta='"+pv+"',fecha_compra='"+fechafin+"',stock='"+ztokfin+"',categoria='"+combocategoriafin.getSelectedItem().toString()+"',descripcion='"+des+"' WHERE id_producto='"+id+"'");

        int n=pst.executeUpdate();

if(n>0){

JOptionPane.showMessageDialog(null,"Registro modificado con éxito");

}else{

    JOptionPane.showMessageDialog(null,"¡Error! no se pudo modificar el registro");

}
 limpiar();

  }catch (Exception e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null,"¡Error! no se pudo modificar el registro");

        }   
}
}
}
}
}
}

       
                     
                       gc();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void combomarcafinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combomarcafinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combomarcafinActionPerformed
     
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
            java.util.logging.Logger.getLogger(Agregar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_eliminar_categoria;
    private javax.swing.JButton Cancelar_Eliminar_marca;
    private javax.swing.JButton Cancelar_catego;
    private javax.swing.JButton Cancelar_catego2;
    private javax.swing.JButton Cancelar_marca;
    private javax.swing.JButton E_marca;
    private javax.swing.JButton G_catego;
    private javax.swing.JButton G_marca;
    private javax.swing.JMenuItem M_A_M_eliminar;
    private javax.swing.JMenuItem M_ELIMINAR_CATEGORIA;
    private javax.swing.JMenuItem M_agregar_categoria;
    private javax.swing.JDialog astock;
    private javax.swing.JButton btnatras;
    public static javax.swing.JButton btnguardar;
    public static final com.toedter.calendar.JDateChooser calendariofin = new com.toedter.calendar.JDateChooser();
    private javax.swing.JComboBox<String> combo_eliminar;
    private javax.swing.JComboBox<String> combo_eliminar_marca;
    public static final javax.swing.JComboBox<String> combocategoriafin = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> combomarcafin = new javax.swing.JComboBox<>();
    private javax.swing.JDialog ecategoria;
    private javax.swing.JDialog emarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static final javax.swing.JDialog ncategoria = new javax.swing.JDialog();
    public static final javax.swing.JDialog nmarca = new javax.swing.JDialog();
    private javax.swing.JTextField txt_D_A_Categoria;
    private javax.swing.JTextField txt_D_A_marca;
    public static final javax.swing.JTextField txtcolorprofin = new javax.swing.JTextField();
    public static final javax.swing.JTextArea txtdescripcionprofin = new javax.swing.JTextArea();
    public static final javax.swing.JTextField txtid_profin = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtnombreprofin = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtpreciocompraprofin = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtprecioventaprofin = new javax.swing.JTextField();
    private javax.swing.JLabel txtprostock;
    public static final javax.swing.JTextField txtstockprofin = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables

 public void otro () throws SQLException

{

//Variables Globales

    JFrame contenedor = new JFrame();
JComboBox MiComboBox = new JComboBox();

//Para el manejo de excepciones utilizamos un try y catch

try
{

// Agregamos el contenedor y lo hacemos visible

contenedor.add(MiComboBox);
contenedor.setVisible(true);

// Cargamos el driver

Class.forName("com.mysql.jdbc.Driver");

// Establecemos la conexion

Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/opticx","root", "leoncito");

// Preparamos la consulta

Statement stat = conexion.createStatement();
// Se realiza la consulta

ResultSet resultado = stat.executeQuery (

"SELECT marca FROM marca_productos");

// Bucle while para la consulta

while (resultado.next())

{

// Comenzamos a rellenar el combobox a partir de la consulta

MiComboBox.addItem (resultado.getObject("marca"));

}
// Ajustamos el tamaño del frame

 contenedor.pack();

// Cerramos la conexión

conexion.close();

}

catch (Exception e)

{

System.out.println (e+"no jalo");

}

}

    public void otromaz () throws SQLException

{

//Para el manejo de excepciones utilizamos un try y catch

try

{

// Cargamos el driver

Class.forName("com.mysql.jdbc.Driver");
// Establecemos la conexion

Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/opticx","root", "leoncito");

// Preparamos la consulta

Statement stat = conexion.createStatement();

// Se realiza la consulta

ResultSet resultado = stat.executeQuery (

"SELECT marca FROM marca_productos");

// Bucle while para la consulta

while (resultado.next())

{
// Comenzamos a rellenar el combobox a partir de la consulta

    combocategoriafin.addItem ((String) resultado.getObject("marca"));

}
// Ajustamos el tamaño del frame

// Cerramos la conexión

conexion.close();

}

catch (Exception e)

{

System.out.println (e+"no jalo");

}

}

    public static void botonenter() {   //para que haga que cuando este seleccionado el boton guardar con enter funcione y no con espacio

        // set this panel's layout to null for absolute positioning of components
        btnguardar.registerKeyboardAction(btnguardar.getActionForKeyStroke(

                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),

                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),

                                      JComponent.WHEN_FOCUSED);

        btnguardar.registerKeyboardAction(btnguardar.getActionForKeyStroke(

                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),

                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),

                                      JComponent.WHEN_FOCUSED);

 

    

    }

}





    

