/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.System.gc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import opticx1.Conexionbd;
import validaciones.Limitar_caracteres;
import validaciones.limitar_carac_textarea;
import validaciones.solo_numeros;
import Atxy2k.CustomTextField.RestrictedTextField;

/**
 *
 * @author LEO
 */
public class modificarProducto extends javax.swing.JFrame {

    /**
     * Creates new form modificarProducto
     */
    public modificarProducto() {
        initComponents();
        this. setLocationRelativeTo(null);
        //conexion

        setResizable(false);
        setTitle("SiGOO");
        
        
        
        //metodo nuevo para convertir en mayusculas sin que afecte el limite de caracteres

        mayusculas(txtnom);
        mayusculas(txtcolor);
        mayusculas_textarea(txtdes);
        //jDialog
       // mayusculas(txt_D_A_Categoria);
       // mayusculas(txt_D_A_marca);

        solo_numeros.solonumeros(txtpc);
        solo_numeros.solonumeros(txtpv);
        
        soloNumeros_stock(txtastok);

        sololetras(txtnom);
        sololetras(txtcolor);
        
        //jDialog
        //sololetras(txt_D_A_Categoria);
        //sololetras(txt_D_A_marca);
       
      botonenter();
     


    }
       public void Limpiar(){
           lblid.setText("");

         txtnom.setText("");

          txtcolor.setText("");

           txtpc.setText("");

            txtpv.setText("");

             txtdes.setText("");

              lblstok.setText("");

              txtastok.setText("");

              combom.setSelectedIndex(0);

              comboc.setSelectedIndex(0);

       }
     public static void botonenter() {   //para que haga que cuando este seleccionado el boton guardar con enter funcione y no con espacio

        // set this panel's layout to null for absolute positioning of components
        btng.registerKeyboardAction(btng.getActionForKeyStroke(

                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),

                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),

                                      JComponent.WHEN_FOCUSED);

        btng.registerKeyboardAction(btng.getActionForKeyStroke(

                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),

                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),

                                      JComponent.WHEN_FOCUSED);

 

    

    }


    public void Limitar_caracteres_cajastexto(){
           
           txtnom.setDocument(new Limitar_caracteres(txtnom,40));
           txtcolor.setDocument(new Limitar_caracteres(txtcolor,20));
           txtpc.setDocument(new Limitar_caracteres(txtpc,9));
           txtpv.setDocument(new Limitar_caracteres(txtpv,9));
           txtastok.setDocument(new Limitar_caracteres(txtastok,2));
           txtdes.setDocument(new limitar_carac_textarea(txtdes,120));
           
           //jdialog1
         //  txt_D_A_Categoria.setDocument(new limitar_carac_textarea(txtdescripcionprofin,30));
          // txt_D_A_marca.setDocument(new limitar_carac_textarea(txtdescripcionprofin,30));
           
           
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("MODIFICAR PRODUCTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 310, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("CLAVE:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("NOMBRE:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("COLOR:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("MARCA:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("PRECIO DE COMPRA: $");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("PRECIO DE VENTA: $");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("FECHA DE COMPRA:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("STOCK ACTUAL:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("CATEGORÍA:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("DESCRIPCIÓN:");

        lblid.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblid.setForeground(new java.awt.Color(204, 0, 0));

        txtnom.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtnom.setForeground(new java.awt.Color(204, 0, 0));
        txtnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnomKeyTyped(evt);
            }
        });

        txtcolor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtcolor.setForeground(new java.awt.Color(204, 0, 0));
        txtcolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcolorActionPerformed(evt);
            }
        });
        txtcolor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcolorKeyTyped(evt);
            }
        });

        txtdes.setColumns(20);
        txtdes.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtdes.setForeground(new java.awt.Color(204, 0, 0));
        txtdes.setRows(5);
        txtdes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdesKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtdes);

        combom.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combom.setForeground(new java.awt.Color(0, 0, 102));
        combom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combomActionPerformed(evt);
            }
        });

        txtpc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtpc.setForeground(new java.awt.Color(204, 0, 0));
        txtpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpcActionPerformed(evt);
            }
        });
        txtpc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpcKeyTyped(evt);
            }
        });

        txtpv.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtpv.setForeground(new java.awt.Color(204, 0, 0));
        txtpv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpvKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/MARCA.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        calendariomp.setForeground(new java.awt.Color(0, 0, 102));
        calendariomp.setDateFormatString("dd/MM/yyyy");
        calendariomp.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        comboc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboc.setForeground(new java.awt.Color(0, 0, 102));
        comboc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/CATEGORIA.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblstok.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblstok.setForeground(new java.awt.Color(204, 0, 0));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("AGREGAR A STOCK: ");

        txtastok.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtastok.setForeground(new java.awt.Color(204, 0, 0));
        txtastok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtastokActionPerformed(evt);
            }
        });
        txtastok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtastokKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtastokKeyTyped(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ATRAS2.png"))); // NOI18N
        jButton3.setMnemonic('A');
        jButton3.setText("ATRAS");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ATRAS3.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/ATRAS1.png"))); // NOI18N
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btng.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif2.png"))); // NOI18N
        btng.setMnemonic('M');
        btng.setText("MODIFICAR");
        btng.setBorderPainted(false);
        btng.setContentAreaFilled(false);
        btng.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btng.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btng.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif3.png"))); // NOI18N
        btng.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif1.png"))); // NOI18N
        btng.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btng.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btngMouseClicked(evt);
            }
        });
        btng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel12))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addComponent(calendariomp, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblstok)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel15)
                        .addGap(4, 4, 4)
                        .addComponent(txtastok, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton3)
                        .addGap(384, 384, 384)
                        .addComponent(btng))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtpv, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(10, 10, 10)
                                .addComponent(txtpc, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel6)
                                .addGap(7, 7, 7)
                                .addComponent(combom, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel11)
                                .addGap(7, 7, 7)
                                .addComponent(comboc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jButton2)))))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3))
                    .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtpc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(combom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpv)
                            .addComponent(comboc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(calendariomp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblstok))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel15))
                            .addComponent(txtastok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10)))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(btng)))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 630, 510));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/650 x 600.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcolorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcolorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcolorActionPerformed

    private void txtpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpcActionPerformed

    private void combomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combomActionPerformed

    private void txtastokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtastokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtastokActionPerformed

    private void btngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngActionPerformed
 try {//boton agregar

        Conexionbd cc =new Conexionbd();

        Connection cn= cc.conexion();  

        String formato="yyyy/MM/dd";
        
        String nom,color,des,pc,pv;
   nom=txtnom.getText();
if(nom.length()== 0){
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el nombre del producto"); 
    txtnom.requestFocus();     
}else{

color=txtcolor.getText();
if(color.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el color del producto"); 
    txtcolor.requestFocus();
}else{
des=txtdes.getText();
if(des.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar la descripción del producto"); 
    txtdes.requestFocus();
}else{
pc=txtpc.getText();
if(pc.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de compra del producto"); 
    txtpc.requestFocus();
}else{
pv=txtpv.getText();
if(pv.length()== 0){
    String nl = System.getProperty("line.separator");
    JOptionPane.showMessageDialog(null,"¡Error! Debe ingresar el precio de venta del producto"); 
    txtpv.requestFocus();
    return;
}

        



  java.util.Date date = calendariomp.getDate();

  SimpleDateFormat sdf = new SimpleDateFormat(formato); 

  String fechafin=sdf.format(date);

  String ztokactual=lblstok.getText();

   String ztoknuevo;

    ztoknuevo=txtastok.getText();

if(ztoknuevo.length()== 0){

    ztoknuevo="0";

}

  int n1=Integer.parseInt(ztokactual);

    int n2=Integer.parseInt(ztoknuevo);

            

      int total=n1+n2;

       

      String ztokfin=Integer.toString(total);

            System.out.println(n2);

        PreparedStatement pst = cn.prepareStatement("UPDATE productos SET nombre='"+nom+"',color='"+color+"',marca='"+combom.getSelectedItem().toString()+"',precio_compra='"+pc+"',precio_venta='"+pv+"',fecha_compra='"+fechafin+"',stock='"+ztokfin+"',categoria='"+comboc.getSelectedItem().toString()+"',descripcion='"+des+"' WHERE id_producto='"+lblid.getText()+"'");

        int n=pst.executeUpdate();

if(n>0){

JOptionPane.showMessageDialog(null,"Registro modificado con éxito");
limpieza();


}else{

    JOptionPane.showMessageDialog(null,"¡Error! no se pudo modificar el registro");

}

}      
}
}
}

        
        }catch (Exception e){

            System.out.println(e);

        } 
        
 
    
      ctrlinventario obj=new ctrlinventario();

               obj.setVisible(true);

                     obj.pack();

                       this.dispose();
                       
                
                        
                       gc();
                            
            btng.resetKeyboardActions();
        
    }//GEN-LAST:event_btngActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      Limpiar();
        try{
        ctrlinventario obj=new ctrlinventario();
        obj.setVisible(true);
        dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! "+e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtastokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtastokKeyTyped
try{
RestrictedTextField restricted = new RestrictedTextField(txtastok);
        restricted.setLimit(2);

}catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Eeeee");
        
}
        char teclapresionada=evt.getKeyChar();
        if(teclapresionada==KeyEvent.VK_ENTER){
         btng.doClick();
          return;
        }
        
        gc();
    }//GEN-LAST:event_txtastokKeyTyped

    private void txtpcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpcKeyTyped
         int num1=9;
        if(txtpc.getText().length()>=num1){
       evt.consume();
     JOptionPane.showMessageDialog(rootPane, "El límite de caracteres para precio de compra es 0000000.00");
}
    }//GEN-LAST:event_txtpcKeyTyped

    private void txtpvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpvKeyTyped
         int num1=9;
        if(txtpv.getText().length()>=num1){
       evt.consume();
     JOptionPane.showMessageDialog(rootPane, "El límite de caracteres para precio de venta es 0000000.00");
}
    }//GEN-LAST:event_txtpvKeyTyped

    private void txtnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomKeyTyped
         int num1=40;
        if(txtnom.getText().length()>=num1){
       evt.consume();
     JOptionPane.showMessageDialog(rootPane, "El límite de caracteres para nombre es 40");
}
    }//GEN-LAST:event_txtnomKeyTyped

    private void txtcolorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcolorKeyTyped
         int num1=20;
        if(txtcolor.getText().length()>=num1){
       evt.consume();
     JOptionPane.showMessageDialog(rootPane, "El límite de caracteres para color es 20");
        }
    }//GEN-LAST:event_txtcolorKeyTyped

    private void txtdesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyTyped
         int num1=120;
        if(txtdes.getText().length()>=num1){
       evt.consume();
     JOptionPane.showMessageDialog(rootPane, "El límite de caracteres para descripcion es 120");
        }
        char teclapresionada=evt.getKeyChar();

         if(teclapresionada==KeyEvent.VK_TAB){
             txtcolor.requestFocus();
         }

    }//GEN-LAST:event_txtdesKeyTyped

    private void txtastokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtastokKeyPressed
       
    }//GEN-LAST:event_txtastokKeyPressed

    private void btngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngMouseClicked

           
    }//GEN-LAST:event_btngMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
        Agregar_Producto.nmarca.setModal(true);
        Agregar_Producto.nmarca.setLocationRelativeTo(null);
        Agregar_Producto.nmarca.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Agregar_Producto.ncategoria.setModal(true);
        Agregar_Producto.ncategoria.setLocationRelativeTo(null);
        Agregar_Producto.ncategoria.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
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
  
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modificarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JButton btng = new javax.swing.JButton();
    public static final com.toedter.calendar.JDateChooser calendariomp = new com.toedter.calendar.JDateChooser();
    public static final javax.swing.JComboBox<String> comboc = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> combom = new javax.swing.JComboBox<>();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JLabel lblid = new javax.swing.JLabel();
    public static final javax.swing.JLabel lblstok = new javax.swing.JLabel();
    public static final javax.swing.JTextField txtastok = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtcolor = new javax.swing.JTextField();
    public static final javax.swing.JTextArea txtdes = new javax.swing.JTextArea();
    public static final javax.swing.JTextField txtnom = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtpc = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtpv = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
void limpieza(){
    lblid.setText("");

         txtnom.setText("");

          txtcolor.setText("");

           txtpc.setText("");

            txtpv.setText("");

             txtdes.setText("");

              lblstok.setText("");

              txtastok.setText("");

              combom.setSelectedIndex(0);

              comboc.setSelectedIndex(0);
}
}
