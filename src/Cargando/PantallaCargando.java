/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cargando;

import javax.swing.*;
import java.awt.*;
/*DEMO POR INFORUX*/
public class PantallaCargando extends JWindow {
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel imageLabel = new JLabel();
  JPanel southPanel = new JPanel();
  FlowLayout southPanelFlowLayout = new FlowLayout();
  JProgressBar progressBar = new JProgressBar();
  ImageIcon imageIcon;

  public PantallaCargando(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
    dibujaVentana();
  }

  public void dibujaVentana() {
    imageLabel.setIcon(imageIcon);
    this.getContentPane().setLayout(borderLayout1);
   // southPanel.setLayout(southPanelFlowLayout);
    //southPanel.setBackground(Color.BLACK);
    this.getContentPane().add(imageLabel, BorderLayout.CENTER);
   // this.getContentPane().add(southPanel, BorderLayout.SOUTH);
  //  southPanel.add(progressBar, null);
    this.pack();
  }

  public void setProgresoMax(int maxProgress)
  {
    progressBar.setMaximum(maxProgress);}

  public void setProgreso(int progress)
  {
    final int progreso = progress;
        progressBar.setValue(progreso);}

  public void setProgreso(String message, int progress)
  {
    final int progreso = progress;
    final String theMessage = message;
    setProgreso(progress);
    progressBar.setValue(progreso);
    progressBar.setVisible(false);
    setMessage(theMessage);  
  }

  private void setMessage(String message)
 {
    if (message==null){
      message = "";
      progressBar.setStringPainted(false);}
    else{
      progressBar.setStringPainted(true);}

    progressBar.setString(message); }

public void velocidadDeCarga(){
   int valor=0;
   //valor=valor+cargado;
    for (int i = 0; i <= 100; i++)
    {
      for (long j=valor; j<1000000; ++j)//modifica el numero segun la velidad q desees
      {
        String poop = " " + (j + i);
      }
     setProgreso("" + i, i);  // si quieres q muestre los numeros y un mensaje
     //setProgreso(i);        //si no quieres q muestre nada, solo la barra
   }
    dispose();}

}