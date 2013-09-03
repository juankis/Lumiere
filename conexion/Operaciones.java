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
  public void llenarCombo(JComboBox combo,String query){
      try {
        res=consultar(query);
        if (res != null) {
        while (res.next()) {
                combo.addItem(res.getObject(1));
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());
        }finally {
           cerrarConexion();
        }
  }
          
  public DefaultComboBoxModel getModeloCombo(String query){
      ResultSet res = null;
      DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("");
        try {
        res=consultar(query);
        if (res != null) {
        while (res.next()) {
                if(res.getMetaData().getColumnCount()>1)
                    modelo.addElement(res.getString(1)+" "+res.getString(2)+" ID:"+res.getString(3));
                       
                else
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
    System.out.println(sql);
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
    public int validarUsuario(String login,String password) {
    String sql="SELECT * FROM usuario WHERE  login='"+login+"'  AND password='"+password+"'";
    int id=0;
    res=consultar(sql);
    try {
      if(res.next())
        id=(Integer)res.getObject(1);        
    }catch(SQLException e){
      JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        cerrarConexion();
    }
    return id;
    }
  public ResultSet consultar(String sql) {
    System.out.println(sql);
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
  public int consultarId(String sql){
      int id=0;
    //System.out.println(sql);
    conectar();
    ResultSet resultado = null;
    try {
      resultado=consultar(sql);
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
  public int consultarGetInt(String sql){
    int id=0;
    
    conectar();
    ResultSet resultado = null;
    try {
      resultado=consultar(sql);
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
  public double recuperarDouble(String sql){
      double id=0;
   // System.out.println(sql);
    conectar();
    ResultSet resultado = null;
    try {
      resultado=consultar(sql);
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
   public void getPedidos(DefaultTableModel tableModel,String sql) {
        ResultSet resultado = null;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        
        try {
            resultado = consultar(sql);
            if (resultado != null) {
                int numeroColumna = resultado.getMetaData().getColumnCount();
                for (int j = 1; j <= numeroColumna; j++) {
                    tableModel.addColumn(resultado.getMetaData().getColumnName(j));
                }
                int count=0;
                while (resultado.next()) {
                    Object[] objetos = new Object[numeroColumna];
                    for (int i = 1; i <= numeroColumna; i++) {
                        objetos[i - 1] = resultado.getObject(i);
                    }
                    tableModel.addRow(objetos);
                    
                }
                
            }
        } catch (SQLException e) {
        } finally {
            cerrarConexion();
        }
    }
   public ArrayList<ArrayList<String>> getClientes(String sql){
       //String sql="";
   ArrayList<ArrayList<String>> clientes=new ArrayList<ArrayList<String>>();
   ResultSet resultado = null;
        
        try {
            resultado = consultar(sql);
            if (resultado != null) {
                int numeroColumna = resultado.getMetaData().getColumnCount();
                
                while (resultado.next()) {
                    ArrayList<String> objetos = new ArrayList<String>();
                    for (int i = 1; i <= numeroColumna; i++) {
                        objetos.add(""+resultado.getObject(i));
                    }
                    clientes.add(objetos);
                }
                
            }
        } catch (SQLException e) {
        } finally {
            cerrarConexion();
            
        }
        return clientes;
   }
   public Object[] getObject(String sql) 
   {
       Object[] objetos=new Object[1];
       res=consultar(sql);
       try{
       int numeroColumna = res.getMetaData().getColumnCount();
       objetos = new Object[numeroColumna];
       if (res.next()) {
                    
                    for (int i = 1; i <= numeroColumna; i++) {
                        objetos[i - 1] = res.getObject(i);
                    }
                    
                    
                }
            
       }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "" + e.getMessage());      
       }finally{
           cerrarConexion();
       }
       return objetos;
   }
   /*
public static void main(String [] args)
{
    Operaciones o=new Operaciones();
    o.insertar("insert into persona(nombre,apellidos,telefono)values('lalal','lalala','878')");
}
    * 
    */
}
