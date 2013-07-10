/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author juanki
 */
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    
    public Persona(String nombre,String apellido,String telefono)
    {
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
    }
}
