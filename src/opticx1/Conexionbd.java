/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticx1;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author LEO
 */
public class Conexionbd {

    Connection conect = null;

    public Connection conexion() {
        try {

            //Cargamos el Driver MySQL
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/opticx", "root", "leoncito");
        } catch (Exception e) {
            String nl = System.getProperty("line.separator");

            JOptionPane.showMessageDialog(null, "¡Error! no se pudo conectar a la base de datos" + nl + "Por favor asegurese que su servidor funciona correctamente");
            System.out.println(e);
        }
        return conect;

    }
    public int suma(int num1, int num2)
    {
       int total=num1+num2;
        return total;
    }
}
