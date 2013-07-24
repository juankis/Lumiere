package conexion;

/*
 * Package agenda.
 */


//import Objetos.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.*;
import javax.swing.table.*;
/**
 * Clase Operaciones
 * @author equipo de programacion Agil
 */
public class Operaciones extends Conexion {

    

  /**
   * Constructor for objects of class Operaciones
   */
  ResultSet res;  
  public Operaciones() {
    // initialise instance variables
  }

  public DefaultComboBoxModel getModeloCombo(String query){
      ResultSet res = null;
      DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
        res=consultar(query);
        if (res != null) {
        while (res.next()) {
                modelo.addElement(res.getString(1));
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());
        }finally {
           cerrarConexion();
        }
        return modelo;
  }    
  public boolean insertar(String sql) {
     
    boolean valor = true;
    conectar();
    try {
      consulta.executeUpdate(sql);
    } catch (SQLException e) {
      valor = false;
      JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        cerrarConexion();
    }
    return valor;
  }
  
  public ResultSet consultar(String sql) {
    conectar();
    ResultSet resultado = null;
    try {
      resultado = consulta.executeQuery(sql);

    } catch (SQLException e) {
      System.out.println("Mensaje:" + e.getMessage());
      System.out.println("Estado:" + e.getSQLState());
      System.out.println("Codigo del error:" + e.getErrorCode());
      JOptionPane.showMessageDialog(null, "" + e.getMessage());
    } 
    return resultado;
  }
  public int guardarYRecuperarId(String sql){
    int id=0;
    System.out.println(sql);
    conectar();
    ResultSet resultado = null;
    try {
      consulta.executeUpdate(sql,consulta.RETURN_GENERATED_KEYS);
      resultado=consulta.getGeneratedKeys();
      if (resultado.next()){
            id=resultado.getInt(1);
        }
    } catch (SQLException e) {
      System.out.println("Mensaje:" + e.getMessage());
      System.out.println("Estado:" + e.getSQLState());
      System.out.println("Codigo del error:" + e.getErrorCode());
      JOptionPane.showMessageDialog(null, "" + e.getMessage());
    } finally {
      cerrarConexion();
    }
      return id;
  }
          
public static void main(String [] args)
{
    Operaciones o=new Operaciones();
    o.insertar("insert into persona(nombre,apellidos,telefono)values('lalal','lalala','878')");
}
}
