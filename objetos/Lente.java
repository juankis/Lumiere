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
public class Lente {
    private int id;
    private String material;
    private String tipo;
    private String color;
    private String vision;
    private String estado;
    private int idPedido;
    private Operaciones operaciones=new Operaciones();
    
    public Lente(String material,
    String tipo,
    String color,
    String vision,
    String estado,
    int idPedido)
    {
        this.material=material;
        this.tipo=tipo;
        this.color=color;
        this.vision=vision;
        this.estado=estado;
        this.idPedido=idPedido;
    }
    public void guardar_en_BD()
    {
       // String sql="insert into persona(nombre,apellidos,telefono)values('"+nombre+"','"+apellido+"','"+telefono+"')";
        //operaciones.insertar(sql);
    }
      public int getId(){
        return id;
    }
}
