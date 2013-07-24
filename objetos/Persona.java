/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Conexion;
import conexion.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author juanki
 */
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private Operaciones operaciones=new Operaciones();
    
    public Persona(String nombre,String apellido,String telefono)
    {
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
    }
    public Persona(){
    
    }
    public DefaultComboBoxModel listaNombres(String cadenaEscrita){
        
        String query = "SELECT DISTINCT p.nombre FROM persona p WHERE p.nombre LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaApellidos(String cadenaEscrita){
                
        String query = "SELECT DISTINCT p.apellidos FROM persona p WHERE p.apellidos LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
      public void guardar_en_BD()
    {
        int id=0;
        String sql="insert into persona(nombre,apellidos,telefono) values('"+nombre+"','"+apellido+"','"+telefono+"')";
        id=operaciones.guardarYRecuperarId(sql);
        this.id=id;
        
    }
      public int getId(){
      return id;
      }
      /*
    public static void main(String [] arg)
    {
        Persona p=new Persona("maria","panza fria" ,"70394520");
        p.guardar_en_BD();
        System.out.println(p.getId());
    }*/
}
