
package Clientes;

import empleados.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
public class FormatoTablaClientes extends DefaultTableCellRenderer
{
 public Component getTableCellRendererComponent
 (JTable table, Object value, boolean selected, boolean focused, int row, int column)
 {
         // SI EN CADA FILA DE LA TABLA LA CELDA 12 ES IGUAL A ACTIVO COLOR VERDE
  if(String.valueOf(table.getValueAt(row,11)).equals("ACTIVO")) {
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

