package conexion;

/*
 * Pakage agenda
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
//import java.sql.*;

/**
 * Crea la connecion a la base de datos
 *
 * @author equipo de programacion Agil
 * @version 1.01
 */
public class Conexion {
  Connection conexion;
  Statement consulta;
  public String ruta;

  /**
   * Constructor del objeto de class Conexion
   */
  public Conexion() {
    /**
     * la Ruta para la base de datos
     */
    //ruta = "BD/registro.db";
  }

     public void conectar()
    {
        if (conexion != null)
            return;
        String url = "Jdbc:mysql://localhost:3306/lumiere";
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           conexion = DriverManager.getConnection(url,"lumiere","lumiere");
           consulta = conexion.createStatement();
           if (conexion !=null){
               System.out.println("Conexión a base de datos ... Ok");
               
           }
        } catch (Exception e) {
            System.out.println("Problema al establecer la Conexión a la base de datos lumiere ");
        }
        
    }
       public Connection cerrarConexion(){
        try {
            conexion.close();
             System.out.println("Cerrando conexion a lumiere . . . . . Ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        conexion=null;
        return conexion;
    }
       
  
}
