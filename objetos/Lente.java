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
    public Lente(int idPedido){
        this.idPedido=idPedido;
        String sql="select * from lente where pedido_id="+idPedido;
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        id=(Integer)fila[0];
        material=""+fila[2];
        tipo=""+fila[3];
        color=""+fila[4];
        vision=""+fila[5];
        estado=""+fila[6];
        }
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
    public String getMaterial(){
        return material;
    }
    public String getTipo(){
        return tipo;
    }
    public String getColor(){
        return color;
    }
    public String getVision(){
        return vision;
    }
    public String getEstado(){
        return estado;
    }
    public void setMaterial(String m){
        material=m;
    }
    public void setTipo(String t){
        tipo=t;
    }
    public void setColor(String c){
        color=c;
    }
    public void setVision(String v){
        vision=v;
    }
    public void setEstado(String e){
        estado=e;
    }
    public void actualizar(){
        String sql="UPDATE lente "
                + "SET material ='"+material+"',"
                + "estado = '"+estado+"',"
                + "tipo ='"+tipo+"',"
                + "color ='"+color+"',"
                + "vision ='"+vision+"'"
                + " WHERE id ="+id;
      operaciones.insertar(sql);
    }
}
