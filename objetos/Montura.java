/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author juanki
 */
public class Montura {
    private int id;
    private String marca;
    private int codigo;
    private String color;
    private String tipo;
    private String tamanio;
    private String estado;
    private int idPedido;
    
    public Montura(String marca,
    int codigo,
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
}
