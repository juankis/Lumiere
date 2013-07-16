/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;

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
    public int getId(){
        return id;
    }
    public void guardar_en_BD()
    {
        String sql="insert into montura(marca,color,tipo,tamanio,estado,pedido_id)"
                + "values('"+marca+"','"+color+"','"+tipo+"','"+tamanio+"','"+estado+"',"+idPedido+")";
        id=operaciones.guardarYRecuperarId(sql);
    }
}
