/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author juanki
 */
public class Montura {
    private int id;
    private String marca;
    private String codigo;
    private String color;
    private String tipo;
    private String tamanio;
    private String estado;
    private int idPedido;
    Operaciones operaciones=new Operaciones();
    public Montura(String marca,
    String codigo,
    String color,
    String tipo,
    String tamanio,
    String estado,
    int idPedido)
    {
        this.marca=marca;
        this.color=color;
        this.tipo=tipo;
        this.tamanio=tamanio;
        this.estado=estado;
        this.idPedido=idPedido;
    }
    public Montura(){
    
    }
    public Montura(int idPedido){
        this.idPedido=idPedido;
        String sql="select * from montura where pedido_id="+idPedido;
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        id=(Integer)fila[0];
        marca=""+fila[2];
        codigo=""+fila[3];
        color=""+fila[4];
        tipo=""+fila[5];
        tamanio=""+fila[6];
        estado=""+fila[7];
        }
    }
    public DefaultComboBoxModel listaMarcas(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.marca FROM montura m WHERE m.marca LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaCodigos(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.codigo FROM montura m WHERE m.codigo LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaColores(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.color FROM montura m WHERE m.color LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaTipos(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.tipo FROM montura m WHERE m.tipo LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaTamanios(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.tamanio FROM montura m WHERE m.tamanio LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public int getId(){
        return id;
    }
    public void guardar_en_BD()
    {
        String sql="insert into montura(marca,color,tipo,tamanio,estado,codigo,pedido_id)"
                + "values('"+marca+"','"+color+"','"+tipo+"','"+tamanio+"','"+estado+"','"+codigo+"',"+idPedido+")";
        id=operaciones.guardarYRecuperarId(sql);
    }
    public String getMarca(){
        return marca;
    }
    public String getCodigo(){
        return codigo;
    }
    public String getColor(){
        return color;
    }
    public String getTipo(){
        return tipo;
    }
    public String getTamanio(){
        return tamanio;
    }
    public String getEstado(){
        return estado;
    }
    public void setMarca(String m){
        marca=m;
    }
    public void setCodigo(String c){
        codigo=c;
    }
    public void setColor(String c){
        color=c;
    }
    public void setTipo(String t){
        tipo=t;
    }
    public void setTamanio(String t){
        tamanio=t;
    }
    public void setEstado(String e){
        estado=e;
    }
    public void actualizar(){
        String sql="UPDATE montura "
                + "SET marca ='"+marca+"',"
                + "codigo ='"+codigo+"',"
                + "color ='"+color+"',"
                + "tipo ='"+tipo+"',"
                + "tamanio ='"+tamanio+"',"
                + "estado = '"+estado+"'"
                + " WHERE id ="+id;
      operaciones.insertar(sql);
    }
}
