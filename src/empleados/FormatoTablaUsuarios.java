/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleados;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
public class FormatoTablaUsuarios extends DefaultTableCellRenderer
{
 public Component getTableCellRendererComponent
 (JTable table, Object value, boolean selected, boolean focused, int row, int column)
 {
         // SI EN CADA FILA DE LA TABLA LA CELDA 12 ES IGUAL A ACTIVO COLOR VERDE
  if(String.valueOf(table.getValueAt(row,12)).equals("ACTIVO")) {
      setForeground(Color.white);
  setBackground(new java.awt.Color(0, 204, 0));
                  // SI NO ES ACTIVO ENTONCES COLOR ROJO
  }  else{
  setForeground(Color.white);
  setBackground(new java.awt.Color(153, 0, 0));
                          } 
 super.getTableCellRendererComponent(table, value, selected, focused, row, column);
 return this;
 }
 }

