/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import opticx1.Conexionbd;
import opticx1.conexionmarca;

/**
 *
 * @author LEO
 */
public class cargar_combomarca {
    
     Connection conec;

    public ResultSet cargacombomarca()throws Exception{
   conec=conexionmarca.getInstance().obtenConexion();
String sql="SELECT * FROM marca_productos";
     PreparedStatement ps=conec.prepareStatement(sql);
     ResultSet rs=ps.executeQuery();
     return rs;
     }
}
