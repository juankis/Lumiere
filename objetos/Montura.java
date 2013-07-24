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
}
