/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Conexion;
import conexion.Operaciones;

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
