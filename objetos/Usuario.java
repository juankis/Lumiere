/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author juanki
 */
public class Usuario {
    private int id=1;
    private String login;
    private String password;
    private String estado;
    
    public Usuario(String login,String password,String estado)
    {
        this.login=login;
        this.password=password;
        this.estado=estado;
    }
    public int get_id()
    {
        return id;
    }
}
