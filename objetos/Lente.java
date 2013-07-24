/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;
import conexion.Conexion;
import conexion.Operaciones;
import javax.swing.DefaultComboBoxModel;
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
    public Lente(){
    
    }
    public DefaultComboBoxModel listaMateriales(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.material FROM lente l WHERE l.material LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaTipos(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.tipo FROM lente l WHERE l.tipo LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaColores(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.color FROM lente l WHERE l.color LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaVisiones(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.vision FROM lente l WHERE l.vision LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public void guardar_en_BD()
    {
        String sql="insert into lente(material,tipo,color,vision,estado,pedido_id)"
                + "values('"+material+"','"+tipo+"','"+color+"','"+vision+"','"+estado+"',"+idPedido+")";
        id=operaciones.guardarYRecuperarId(sql);
    }
      public int getId(){
        return id;
    }
}
