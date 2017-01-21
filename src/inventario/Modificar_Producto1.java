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
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import opticx1.login;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Modificar_Producto1 extends javax.swing.JFrame {
    
    

    Conexionbd cc= new Conexionbd();

    Connection cn= cc.conexion();

    

    control_existencias con = new control_existencias();
    
    ResultSet rs;
    String fehaactual;

//Variables para conectar
     public Modificar_Producto1() {
        initComponents();
         Calendar c2 = new GregorianCalendar();
        calendario.setCalendar(c2);
        this. setLocationRelativeTo(null);

        //conexion
        setResizable(false);
        setTitle("SiGOO");

        try{

        Object[] llenar_combo_marca_producto = con.combox("marcas_productos","marca_producto");

        combomarca.removeAllItems();

        for(int i=0;i<llenar_combo_marca_producto.length;i++){

        combomarca.addItem((String) llenar_combo_marca_producto[i]);

        }

        Object[] llenar_combo_categoria_producto = con.combox("categoria_productos","categoria_producto");

        combocategoria.removeAllItems();

        for(int i=0;i<llenar_combo_categoria_producto.length;i++){

        combocategoria.addItem((String) llenar_combo_categoria_producto[i]);

        }

        
       // txtnombrepro.setDocument(new Solo_mayusculas()); Ya ni me esta funcionando este metodo ya que al limitar caracteres,

        //afectaba a este metodo y regresaban a minusculas es decir nunca funcionaban juntos.

        

        Limitar_caracteres_cajastexto(); //limitar caracteres en campos de texto

        
        //metodo nuevo para convertir en mayusculas sin que afecte el limite de caracteres

        mayusculas(txtid_pro);
        mayusculas(txtnombrepro);
        mayusculas(txtcolorpro);
        mayusculas_textarea(txtdescripcionpro);
        //jDialog
        mayusculas(txt_D_A_Categoria);
        mayusculas(txt_D_A_marca);

        solo_numeros.solonumeros(txtpreciocomprapro);
        solo_numeros.solonumeros(txtprecioventapro);
        
        soloNumeros_stock(txtstockpro);

        sololetras(txtnombrepro);
        sololetras(txtcolorpro);
        
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
       txtid_pro.setText("");
       txtnombrepro.setText("");
       txtcolorpro.setText("");
       txtprecioventapro.setText("");
       txtpreciocomprapro.setText("");
       txtstockpro.setText("");
       txtdescripcionpro.setText("");
       combocategoria.setSelectedItem("Selecciona:");
       combomarca.setSelectedItem("Selecciona:");
    }
      public void Limitar_caracteres_cajastexto(){
           txtid_pro.setDocument(new Limitar_caracteres(txtid_pro,6));
           txtnombrepro.setDocument(new Limitar_caracteres(txtnombrepro,40));
           txtcolorpro.setDocument(new Limitar_caracteres(txtcolorpro,20));
           txtprecioventapro.setDocument(new Limitar_caracteres(txtprecioventapro,9));
           txtpreciocomprapro.setDocument(new Limitar_caracteres(txtpreciocomprapro,9));
           txtstockpro.setDocument(new Limitar_caracteres(txtstockpro,2));
           txtdescripcionpro.setDocument(new limitar_carac_textarea(txtdescripcionpro,120));
           
           //jdialog1
           txt_D_A_Categoria.setDocument(new limitar_carac_textarea(txtdescripcionpro,30));
           txt_D_A_marca.setDocument(new limitar_carac_textarea(txtdescripcionpro,30));
           
           
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
     combomarca.addItem(rs.getString(2));
       }
       }
    catch (Exception e){
        JOptionPane.showMessageDialog(null,"¡Error! no se pudo conectar a el registro de marcas");
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ncategoria = new javax.swing.JDialog();
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
        nmarca = new javax.swing.JDialog();
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
        jLabel1 = new javax.swing.JLabel();

        ncategoria.setMinimumSize(new java.awt.Dimension(360, 290));
        ncategoria.setType(java.awt.Window.Type.UTILITY);
        ncategoria.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        ncategoria.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 210));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/DIALOGO4.png"))); // NOI18N
        ncategoria.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));

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
        Cancelar_catego2.setText("SALIR");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(358, 30));
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

        txtid_pro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtid_pro.setForeground(new java.awt.Color(204, 0, 0));
        txtid_pro.setToolTipText("COLOCA 6 CARACTERES ALFANUMÉRICOS");
        txtid_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_proActionPerformed(evt);
            }
        });
        txtid_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtid_proKeyTyped(evt);
            }
        });
        jPanel2.add(txtid_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 38, 80, 30));

        txtstockpro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtstockpro.setForeground(new java.awt.Color(204, 0, 0));
        txtstockpro.setToolTipText("MÁXIMO 99");
        txtstockpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockproKeyTyped(evt);
            }
        });
        jPanel2.add(txtstockpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 89, 30, 30));

        combocategoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combocategoria.setForeground(new java.awt.Color(0, 0, 102));
        combocategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(combocategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 37, 131, 30));

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

        txtnombrepro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtnombrepro.setForeground(new java.awt.Color(204, 0, 0));
        txtnombrepro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreproKeyTyped(evt);
            }
        });
        jPanel2.add(txtnombrepro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 138, 250, 30));

        txtcolorpro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtcolorpro.setForeground(new java.awt.Color(204, 0, 0));
        txtcolorpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcolorproKeyTyped(evt);
            }
        });
        jPanel2.add(txtcolorpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 170, 30));

        txtprecioventapro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtprecioventapro.setForeground(new java.awt.Color(204, 0, 0));
        txtprecioventapro.setToolTipText("FORMATO 00.00");
        txtprecioventapro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioventaproKeyTyped(evt);
            }
        });
        jPanel2.add(txtprecioventapro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 90, 30));

        txtpreciocomprapro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtpreciocomprapro.setForeground(new java.awt.Color(204, 0, 0));
        txtpreciocomprapro.setToolTipText("FORMATO 00.00");
        txtpreciocomprapro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreciocompraproKeyTyped(evt);
            }
        });
        jPanel2.add(txtpreciocomprapro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 90, 30));

        calendario.setForeground(new java.awt.Color(0, 0, 102));
        calendario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 170, 30));

        combomarca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combomarca.setForeground(new java.awt.Color(0, 0, 102));
        combomarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(combomarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 170, 30));

        txtdescripcionpro.setColumns(20);
        txtdescripcionpro.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtdescripcionpro.setForeground(new java.awt.Color(204, 0, 0));
        txtdescripcionpro.setRows(5);
        txtdescripcionpro.setToolTipText("MÁXIMO 200 CARACTERES");
        txtdescripcionpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionproKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtdescripcionpro);

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 610, 550));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/650 x 600.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtid_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_proActionPerformed

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
        obj.pack();
        dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! no se ha podido acceder ala base de datos");
        }
    }//GEN-LAST:event_btnatrasActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        //boton agregar
        Conexionbd cc =new Conexionbd();
        Connection cn= cc.conexion();      
String id,nom,color,pv,pc,des,stock,catego,marca;
String formato="yyyy-MM-dd";

  Date date = calendario.getDate();
  SimpleDateFormat sdf = new SimpleDateFormat(formato); 
  String fechafin=sdf.format(date);
  String sqlcomparar="";
  String valor;
  String sql="";
  
   id=txtid_pro.getText();

          if(id.length()<6){
             String nl = System.getProperty("line.separator");
                     JOptionPane.showMessageDialog(null,"¡Error! La clave del producto debe contener 6 caracteres almanumericos"+nl+"                             "

           + "Por favor verifique el tamaño"); 

   txtid_pro.requestFocus();
}else{
stock=txtstockpro.getText();
if(stock.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar la cantidad de productos que conformaran su stock"); 
    txtstockpro.requestFocus();
}else{
nom=txtnombrepro.getText();
if(nom.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el nombre del producto"); 
    txtnombrepro.requestFocus();
}else{
color=txtcolorpro.getText();
if(color.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el color del producto"); 
    txtcolorpro.requestFocus();
}else{
des=txtdescripcionpro.getText();
if(des.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar la descripción del producto"); 
    txtdescripcionpro.requestFocus();
}else{
pc=txtpreciocomprapro.getText();
if(pc.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de compra del producto"); 
    txtpreciocomprapro.requestFocus();
}else{
pv=txtprecioventapro.getText();
if(pv.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de venta del producto"); 
    txtprecioventapro.requestFocus();
}else{
catego=combocategoria.getSelectedItem().toString();
marca=combomarca.getSelectedItem().toString();

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

    private void txtid_proKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_proKeyTyped
        char teclapresionada=evt.getKeyChar();
        
         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtid_proKeyTyped

    private void txtstockproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockproKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtstockproKeyTyped

    private void txtnombreproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreproKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtnombreproKeyTyped

    private void txtcolorproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcolorproKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtcolorproKeyTyped

    private void txtpreciocompraproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciocompraproKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtpreciocompraproKeyTyped

    private void txtprecioventaproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioventaproKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_ENTER){

        btnguardar.doClick();
         }
    }//GEN-LAST:event_txtprecioventaproKeyTyped

    private void txtdescripcionproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionproKeyTyped
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_TAB){
             txtpreciocomprapro.requestFocus();

         }
    }//GEN-LAST:event_txtdescripcionproKeyTyped

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

                  combocategoria.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combocategoria.addItem((String) llenar_combo_categoria_producto[i]);
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

                      combomarca.removeAllItems();

                      for(int i=0;i<llenar_combo_marca_producto.length;i++){

                      combomarca.addItem((String) llenar_combo_marca_producto[i]);

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
            Logger.getLogger(Modificar_Producto1.class.getName()).log(Level.SEVERE, null, ex);
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

                  combocategoria.removeAllItems();

                   for(int i=0;i<llenar_combo_categoria_producto.length;i++){

                  combocategoria.addItem((String) llenar_combo_categoria_producto[i]);
                   }
                   
    } catch (Exception e) {
    }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! no ha seleccionado ninguna categoría de la lista");
            
        }
        }
    }//GEN-LAST:event_B_eliminar_categoriaActionPerformed

    private void M_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_agregar_categoriaActionPerformed
        
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
            Logger.getLogger(Modificar_Producto1.class.getName()).log(Level.SEVERE, null, ex);
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

                  combomarca.removeAllItems();

                   for(int i=0;i<llenar_combo_marca_producto.length;i++){

                  combomarca.addItem((String) llenar_combo_marca_producto[i]);
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
            java.util.logging.Logger.getLogger(Modificar_Producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_Producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_Producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_Producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_Producto1().setVisible(true);
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
    private javax.swing.JButton btnatras;
    public static javax.swing.JButton btnguardar;
    public static final com.toedter.calendar.JDateChooser calendario = new com.toedter.calendar.JDateChooser();
    private javax.swing.JComboBox<String> combo_eliminar;
    private javax.swing.JComboBox<String> combo_eliminar_marca;
    public static final javax.swing.JComboBox<String> combocategoria = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> combomarca = new javax.swing.JComboBox<>();
    private javax.swing.JDialog ecategoria;
    private javax.swing.JDialog emarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JDialog ncategoria;
    private javax.swing.JDialog nmarca;
    private javax.swing.JTextField txt_D_A_Categoria;
    private javax.swing.JTextField txt_D_A_marca;
    public static final javax.swing.JTextField txtcolorpro = new javax.swing.JTextField();
    public static final javax.swing.JTextArea txtdescripcionpro = new javax.swing.JTextArea();
    public static final javax.swing.JTextField txtid_pro = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtnombrepro = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtpreciocomprapro = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtprecioventapro = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtstockpro = new javax.swing.JTextField();
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

    combocategoria.addItem ((String) resultado.getObject("marca"));

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





    

