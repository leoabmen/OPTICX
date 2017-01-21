/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticx1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import opticx1.Sentencias_sql;
/**
 *
 * @author LEO
 */
public class conexionmarca {
    Connection conect = null;
Connection conectar = null;

static conexionmarca instance=null;
public conexionmarca() throws Exception{
      Class.forName("com.mysql.jdbc.Driver");
           conectar = DriverManager.getConnection("jdbc:mysql://localhost/opticx","root","leoncito");
 }

 public static conexionmarca getInstance()  throws Exception {
        if(instance==null){
instance = new conexionmarca();
}

return instance;
    }
 
   

   
   public void finalize()throws Exception{
if(conectar.isClosed()){
conectar=null;
conectar.isClosed();

}

}

public Connection obtenConexion(){

return conectar;

}


}