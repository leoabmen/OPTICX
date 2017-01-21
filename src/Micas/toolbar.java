/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Micas;

import javax.swing.*;

class Menu extends JFrame {
 //Toolbar
 JToolBar TBarra=new JToolBar(); 
  JButton BNuevo=new JButton("N");
  JButton BAbrir=new JButton("A");
  JButton BCopiar=new JButton("C");
  JButton BCortar=new JButton("C");
  JButton BPegar=new JButton("P"); 
  JButton BGuardar=new JButton("G");
    public Menu() {

  //ToolBar
  TBarra.add(BNuevo);
  TBarra.add(BAbrir);
  TBarra.add(BGuardar);
  TBarra.addSeparator();
  TBarra.add(BCopiar);
  TBarra.add(BCortar);
  TBarra.add(BPegar); 
  BGuardar.setToolTipText ("Guardar");
  BNuevo.setToolTipText ("Nuevo");
  BAbrir.setToolTipText ("Abrir");
  BCopiar.setToolTipText ("Copiar");
  BCortar.setToolTipText ("BCortar");
  BPegar.setToolTipText ("Pegar");

  
  add(TBarra,"North");

  TBarra.setFloatable(false);  
  setTitle("Ejemplos JPopUpMenu"); 
     setSize(800,600);
  setVisible(true);
    }
    
 public static void main (String []args){
  new Menu();
 }   
    
}