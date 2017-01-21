/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import Cargando.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import opticx1.Conexionbd;
import inventario.ctrlinventario;
import static inventario.ctrlinventario.clave;
import static inventario.ctrlinventario.imprimir;
import static inventario.ctrlinventario.tbproducto;


public class Principal {
   
public Principal(){
    
        new Tarea("Tarea1").start();
        new Tarea("Tarea2").start();
    }

         public static void main(String[] args)
  {
    new Principal();
    
  }
}
class Tarea extends Thread {
 public PantallaCargandoMain hilo2; 
    public Tarea(String str) {
        super(str);
    }

        public void run() {
        int tar = 0;
        if (getName().equals("Tarea1")) {
            tar = 1;
        }
        if (getName().equals("Tarea2")) {
            tar = 2;
        }
        switch (tar) {
            case 1: // Tarea 1
                for (int a = 0; a < 5; a++) {
                    System.out.println(getName() + ": " + a);
                }
                
            AbrirModal(clave);
            System.out.println(clave+"calve en principal");

                break;
            case 2: // Tarea 2
                for (int b = 0; b < 5; b++) {
                    System.out.println(getName() + ": " + b);
                }
           
            hilo2 = new PantallaCargandoMain();
            
        }
    
        
}
     public void AbrirModal(String clave){
     Conexionbd cc = new Conexionbd();
        Connection cn = cc.conexion();
        try {

            
            
            
            
             new ctrlinventario().ConsultarCodigoBarras(clave);
             new ctrlinventario().ImprimirCbx();
            imprimir.setModal(true);
            imprimir.setSize(550, 320);
            imprimir.setResizable(false);
            imprimir.setLocationRelativeTo(null);
            imprimir.setVisible(true);
            
             
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "¡Error! no ha seleccionado ningun ID de la lista de abajo");
            System.out.println(e);
        }
       hilo2.screen.dispose();
     }
}

