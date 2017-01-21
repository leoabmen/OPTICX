
package empleados;
//Librerias para conectar a sql
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import opticx1.Conexionbd;
import opticx1.Ventanaadmin;


public class Nuevoempleado extends javax.swing.JFrame {
    Conexionbd cc=new Conexionbd();
    Connection cn= cc.conexion();
   
//Variables para conectar
  
    public Nuevoempleado(){
        
        initComponents();
        //Centrar formulario 
       this. setLocationRelativeTo(null);
        //conexion

        setResizable(false);
        setTitle("SiGOO");
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sexo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
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
        setMaximumSize(new java.awt.Dimension(550, 550));
        setMinimumSize(new java.awt.Dimension(550, 550));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(550, 550));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CLAVE");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 120, 39, 30);

        txtclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclaveActionPerformed(evt);
            }
        });
        getContentPane().add(txtclave);
        txtclave.setBounds(133, 122, 108, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("NOMBRE");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 160, 52, 30);
        getContentPane().add(txtnombre);
        txtnombre.setBounds(133, 160, 130, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("APELLIDOS");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 205, 69, 20);

        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });
        getContentPane().add(txtapellido);
        txtapellido.setBounds(133, 199, 239, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("TELEFONO");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(41, 249, 60, 15);
        getContentPane().add(txttelefono);
        txttelefono.setBounds(133, 237, 134, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("DIRECCIÓN");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 285, 68, 20);
        getContentPane().add(txtdireccion);
        txtdireccion.setBounds(133, 277, 239, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("DESEMPEÑO");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 320, 74, 20);
        getContentPane().add(txtdesempeño);
        txtdesempeño.setBounds(133, 315, 165, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("SALARIO");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 360, 56, 30);
        getContentPane().add(txtsalario);
        txtsalario.setBounds(130, 360, 105, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText(" SEMANAL");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(250, 360, 70, 30);

        txtsexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione >>", "Masculino", "Femenino", " " }));
        txtsexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsexoActionPerformed(evt);
            }
        });
        getContentPane().add(txtsexo);
        txtsexo.setBounds(420, 250, 94, 20);

        sexo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sexo.setText("SEXO");
        getContentPane().add(sexo);
        sexo.setBounds(380, 250, 32, 20);

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
        getContentPane().add(jButton1);
        jButton1.setBounds(50, 400, 92, 91);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONOS BOTONES/CONTROL DE EMPLEADOS/ADD2.png"))); // NOI18N
        jButton2.setText("AGREGAR");
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
        getContentPane().add(jButton2);
        jButton2.setBounds(420, 400, 85, 91);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("EDAD");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(380, 205, 34, 20);

        txtedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtedadActionPerformed(evt);
            }
        });
        getContentPane().add(txtedad);
        txtedad.setBounds(422, 200, 90, 30);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("INGRESAR NUEVO EMPLEADO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(160, 40, 220, 50);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 100, 500, 410);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LEO\\Documents\\NetBeansProjects\\opticx1\\src\\ICONOS BOTONES\\FONDOS\\FONDOAGREMPLEADO3.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 550, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveActionPerformed

    private void txtsexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsexoActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // boton de regreso al menu 2
        ctrlempleado obj=new ctrlempleado();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtedadActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //boton agregar
        try{
        Conexionbd cc= new Conexionbd();
Connection cn= cc.conexion();
String cla,nom,ape,tel,dir,des,sal,ed,sex;

String sql="";
cla=txtclave.getText();
nom=txtnombre.getText();
ape=txtapellido.getText();
tel=txttelefono.getText();
dir=txtdireccion.getText();
des=txtdesempeño.getText();
sal=txtsalario.getText();
ed=txtsexo.getSelectedItem().toString();
sex=txtedad.getText();

sql="INSERT INTO empleados(clave,nombre,apellidos,telefono,direccion,desempeño,salario,sexo,edad) VALUES (?,?,?,?,?,?,?,?,?)";
PreparedStatement pst=cn.prepareStatement(sql);
pst.setString(1,cla);
pst.setString(2,nom);
pst.setString(3,ape);
pst.setString(4,tel);
pst.setString(5,dir);
pst.setString(6,des);
pst.setString(7,sal);
pst.setString(8,ed);
pst.setString(9,sex);
int n=pst.executeUpdate();
if(n>0){
JOptionPane.showMessageDialog(null,"Registro guardado con éxito");
}else{
    JOptionPane.showMessageDialog(null,"¡Error! no se pudo guardar el registro");
}
        


        txtclave.setText("");
         txtnombre.setText("");
          txtapellido.setText("");
           txttelefono.setText("");
            txtdireccion.setText("");
             txtdesempeño.setText("");
              txtsalario.setText("");
               
                txtedad.setText("");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"¡Error! no ha insertado correctamente los datos");
            
        }

    }//GEN-LAST:event_jButton2ActionPerformed

  
   

        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel sexo;
    public static final javax.swing.JTextField txtapellido = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtclave = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtdesempeño = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtdireccion = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtedad = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtnombre = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtsalario = new javax.swing.JTextField();
    public static final javax.swing.JComboBox txtsexo = new javax.swing.JComboBox();
    public static final javax.swing.JTextField txttelefono = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables

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
                new Nuevoempleado().setVisible(true);
            }
        });
    }
}
