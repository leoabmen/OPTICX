/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cargando;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class PantallaCargandoMain {
Ventana hilo;
  PantallaCargando screen;

  public PantallaCargandoMain() {
    inicioPantalla();
	screen.velocidadDeCarga();
          
    }
   
    
  private void inicioPantalla() {
    ImageIcon myImage = new ImageIcon("imagen/imagen.gif");
    screen = new PantallaCargando(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgresoMax(100);
    screen.setVisible(true);
      
  }

  public static void main(String[] args)
  {
    new PantallaCargandoMain();
    
  }
}