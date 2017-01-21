

package inventario;

//Librerias para conectar a sql

import empleados.*;

import static empleados.Nuevoempleado.txtapellido;

import static empleados.Nuevoempleado.txtclave;

import static empleados.Nuevoempleado.txtdesempeño;

import static empleados.Nuevoempleado.txtdireccion;

import static empleados.Nuevoempleado.txtedad;

import static empleados.Nuevoempleado.txtnombre;

import static empleados.Nuevoempleado.txtsalario;

import static empleados.Nuevoempleado.txtsexo;

import static empleados.Nuevoempleado.txttelefono;


import java.sql.*;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import opticx1.Conexionbd;
import static inventario.Agregar_Producto.calendariofin;





public class modificar_Producto extends javax.swing.JFrame {

    Conexionbd cc=new Conexionbd();

    Connection cn= cc.conexion();

   

//Variables para conectar

  

    public modificar_Producto(){

        

        initComponents();

        //Centrar formulario 

       this. setLocationRelativeTo(null);

        //conexion



        setResizable(false);

        setTitle("SiGOO");

       

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

   

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents

    private void initComponents() {



        jDialog1 = new javax.swing.JDialog();

        jPanel1 = new javax.swing.JPanel();

        jLabel13 = new javax.swing.JLabel();

        jPanel2 = new javax.swing.JPanel();

        jLabel2 = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        jLabel8 = new javax.swing.JLabel();

        jLabel6 = new javax.swing.JLabel();

        jLabel7 = new javax.swing.JLabel();

        jLabel11 = new javax.swing.JLabel();

        jScrollPane1 = new javax.swing.JScrollPane();

        jLabel9 = new javax.swing.JLabel();

        jLabel10 = new javax.swing.JLabel();

        nztock = new javax.swing.JTextField();

        jButton1 = new javax.swing.JButton();

        jButton2 = new javax.swing.JButton();

        jLabel12 = new javax.swing.JLabel();

        jLabel1 = new javax.swing.JLabel();



        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());

        jDialog1.getContentPane().setLayout(jDialog1Layout);

        jDialog1Layout.setHorizontalGroup(

            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 400, Short.MAX_VALUE)

        );

        jDialog1Layout.setVerticalGroup(

            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGap(0, 300, Short.MAX_VALUE)

        );



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setMaximumSize(new java.awt.Dimension(650, 600));

        setMinimumSize(new java.awt.Dimension(650, 500));

        setUndecorated(true);

        setPreferredSize(new java.awt.Dimension(650, 600));

        setResizable(false);

        setType(java.awt.Window.Type.UTILITY);

        getContentPane().setLayout(null);



        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));



        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel13.setText("MODIFICAR PRODUCTO");



        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);

        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(

            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel1Layout.createSequentialGroup()

                .addGap(33, 33, 33)

                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)

                .addContainerGap())

        );

        jPanel1Layout.setVerticalGroup(

            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel1Layout.createSequentialGroup()

                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)

                .addContainerGap())

        );



        getContentPane().add(jPanel1);

        jPanel1.setBounds(220, 10, 220, 40);



        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());



        lblclave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblclave.setForeground(new java.awt.Color(204, 0, 0));

        jPanel2.add(lblclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 125, 30));



        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setText("CLAVE:");

        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 30));



        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setText("NOMBRE:");

        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));



        txtnombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtnombre.setForeground(new java.awt.Color(0, 0, 102));

        txtnombre.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                txtnombreActionPerformed(evt);

            }

        });

        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 145, 30));



        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel4.setText("COLOR:");

        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, 20));



        txtapellido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtapellido.setForeground(new java.awt.Color(0, 0, 102));

        txtapellido.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                txtapellidoActionPerformed(evt);

            }

        });

        jPanel2.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 147, 30));



        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setText("MARCA:");

        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 60, -1));



        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setText("FECHA DE COMPRA:");

        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 130, 30));



        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel6.setText("PRECIO DE COMPRA:");

        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));



        txtdireccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtdireccion.setForeground(new java.awt.Color(0, 0, 102));

        jPanel2.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 100, 30));



        txtdesempeño.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtdesempeño.setForeground(new java.awt.Color(0, 0, 102));

        jPanel2.add(txtdesempeño, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 100, 30));



        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel7.setText("PRECIO DE VENTA:");

        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 120, 20));



        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel11.setText("DESCRIPCION:");

        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, 20));



        txtedad.setColumns(20);

        txtedad.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N

        txtedad.setForeground(new java.awt.Color(0, 0, 102));

        txtedad.setRows(5);

        jScrollPane1.setViewportView(txtedad);



        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 230, 60));



        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel9.setText("STOCK ACTUAL:");

        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));



        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel10.setText("CATEGORÍA:");

        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, 20));



        calendarioM.setForeground(new java.awt.Color(0, 0, 102));

        calendarioM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanel2.add(calendarioM, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 160, 30));



        combomarcan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        combomarcan.setForeground(new java.awt.Color(0, 0, 102));

        combomarcan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel2.add(combomarcan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 150, 30));



        nztock.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        nztock.setForeground(new java.awt.Color(0, 0, 102));

        nztock.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                nztockActionPerformed(evt);

            }

        });

        jPanel2.add(nztock, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 50, 30));



        combocategorian.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        combocategorian.setForeground(new java.awt.Color(0, 0, 102));

        combocategorian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel2.add(combocategorian, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 150, 30));



        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ATRAS2.png"))); // NOI18N

        jButton1.setText("ATRAS");

        jButton1.setBorder(null);

        jButton1.setBorderPainted(false);

        jButton1.setContentAreaFilled(false);

        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 92, 91));



        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif2.png"))); // NOI18N

        jButton2.setText("MODIFICAR");

        jButton2.setBorder(null);

        jButton2.setBorderPainted(false);

        jButton2.setContentAreaFilled(false);

        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton2.setIconTextGap(1);

        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif3.png"))); // NOI18N

        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario/PRODUCTOS/modif1.png"))); // NOI18N

        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton2ActionPerformed(evt);

            }

        });

        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, -1, 91));



        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel12.setText("AGREGAR A STOCK:");

        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 130, -1));



        txtstock.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtstock.setForeground(new java.awt.Color(153, 0, 0));

        jPanel2.add(txtstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 50, 30));



        getContentPane().add(jPanel2);

        jPanel2.setBounds(30, 60, 580, 480);



        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/FONDOS/650 x 600.png"))); // NOI18N

        getContentPane().add(jLabel1);

        jLabel1.setBounds(0, 0, 650, 570);



        pack();

    }// </editor-fold>//GEN-END:initComponents



    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_txtapellidoActionPerformed



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // boton de regreso al menu 2

        try{

        ctrlinventario obj=new ctrlinventario();

        obj.setVisible(true);

        obj.pack();

        dispose();

        }catch (Exception e){

            JOptionPane.showMessageDialog(null,"¡Error! no se ha podido acceder ala base de datos");

        }

    }//GEN-LAST:event_jButton1ActionPerformed



    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {//boton agregar

        Conexionbd cc =new Conexionbd();

        Connection cn= cc.conexion();  

        String formato="yyyy/MM/dd";



  java.util.Date date = calendarioM.getDate();

  SimpleDateFormat sdf = new SimpleDateFormat(formato); 

  String fechafin=sdf.format(date);

  String ztokactual=txtstock.getText();

   String ztoknuevo;

    ztoknuevo=nztock.getText();

if(ztoknuevo.length()== 0){

    ztoknuevo="0";

}

  int n1=Integer.parseInt(ztokactual);

      

        

    int n2=Integer.parseInt(ztoknuevo);

            

      int total=n1+n2;

       

      String ztokfin=Integer.toString(total);

            System.out.println(n2);

        PreparedStatement pst = cn.prepareStatement("UPDATE productos SET nombre='"+txtnombre.getText()+"',color='"+txtapellido.getText()+"',marca='"+combomarcan.getSelectedItem().toString()+"',precio_compra='"+txtdireccion.getText()+"',precio_venta='"+txtdesempeño.getText()+"',fecha_compra='"+fechafin+"',stock='"+ztokfin+"',categoria='"+combocategorian.getSelectedItem().toString()+"',descripcion='"+txtedad.getText()+"' WHERE id_producto='"+lblclave.getText()+"'");

        int n=pst.executeUpdate();

if(n>0){

JOptionPane.showMessageDialog(null,"Registro modificado con éxito");

}else{

    JOptionPane.showMessageDialog(null,"¡Error! no se pudo modificar el registro");

}

   

      

        txtclave.setText("");

         txtnombre.setText("");

          txtapellido.setText("");

           txttelefono.setText("");

            txtdireccion.setText("");

             txtdesempeño.setText("");

              nztock.setText("");

              txtstock.setText("");

              combocategorian.setSelectedIndex(0);

              combomarcan.setSelectedIndex(0);

              

               

                txtedad.setText("");

                 ctrlinventario obj=new ctrlinventario();

               obj.setVisible(true);

                     obj.pack();

                       dispose();

                

        }catch (Exception e){

            System.out.println(e);

            JOptionPane.showMessageDialog(null,"¡Error! no ha insertado correctamente los datos");}

    }//GEN-LAST:event_jButton2ActionPerformed



    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_txtnombreActionPerformed



    private void nztockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nztockActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_nztockActionPerformed

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

                new modificar_Producto().setVisible(true);

            }

        });

    }

  

   



        

    

    // Variables declaration - do not modify//GEN-BEGIN:variables

    public static final com.toedter.calendar.JDateChooser calendarioM = new com.toedter.calendar.JDateChooser();

    public static final javax.swing.JComboBox<String> combocategorian = new javax.swing.JComboBox<>();

    public static final javax.swing.JComboBox<String> combomarcan = new javax.swing.JComboBox<>();

    private javax.swing.JButton jButton1;

    private javax.swing.JButton jButton2;

    private javax.swing.JDialog jDialog1;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

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

    public static final javax.swing.JLabel lblclave = new javax.swing.JLabel();

    private javax.swing.JTextField nztock;

    public static final javax.swing.JTextField txtapellido = new javax.swing.JTextField();

    public static final javax.swing.JTextField txtdesempeño = new javax.swing.JTextField();

    public static final javax.swing.JTextField txtdireccion = new javax.swing.JTextField();

    public static final javax.swing.JTextArea txtedad = new javax.swing.JTextArea();

    public static final javax.swing.JTextField txtnombre = new javax.swing.JTextField();

    public static final javax.swing.JLabel txtstock = new javax.swing.JLabel();

    // End of variables declaration//GEN-END:variables



    void setModal(boolean b) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}

