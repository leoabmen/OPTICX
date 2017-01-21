/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author LEO
 */
public class solo_numeros {
    public static void solonumeros(final JTextField a){
a.addKeyListener(new KeyAdapter(){

public void keyTyped(KeyEvent evt){

int k=(int)evt.getKeyChar();
if (k>=46 && k<=57){
    if(k==46){
    String dato=a.getText();
    int tama=dato.length();
      for(int i=0;i<=tama;i++){
          if(dato.contains("."))
           evt.setKeyChar((char)KeyEvent.VK_CLEAR);
      }
      if (k==47){
          evt.setKeyChar((char)KeyEvent.VK_CLEAR);
      }
    }

}
else {
   evt.setKeyChar((char)KeyEvent.VK_CLEAR);
   evt.consume();
   
}
}
});
}
}
