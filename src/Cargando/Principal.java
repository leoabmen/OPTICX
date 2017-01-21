/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cargando;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public static void main(String[] args) {
        new Tarea("Tarea1").start();
        new Tarea("Tarea2").start();
    }
}
class Tarea extends Thread {

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
                
             Ventana hilo;
            hilo = new Ventana();
            hilo.setVisible(true);
        

                break;
            case 2: // Tarea 2
                for (int b = 0; b < 5; b++) {
                    System.out.println(getName() + ": " + b);
                }
            PantallaCargandoMain hilo2;
            hilo2 = new PantallaCargandoMain();
        }
    
        
}
       
}

