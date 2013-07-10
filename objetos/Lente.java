/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

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
    
    public Lente(String material,
    String tipo,
    String color,
    String vision,
    String estado)
    {
        this.material=material;
        this.tipo=tipo;
        this.color=color;
        this.vision=vision;
        this.estado=estado;
    }
}
