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
public class Usuario {
    private int id;
    private String login;
    private String password;
    private String estado;
    private int idPersona;
    private Operaciones operaciones=new Operaciones();
    public Usuario(String login,String password,String estado,int idPersona)
    {
        this.login=login;
        this.password=password;
        this.estado=estado;
        this.idPersona=idPersona;
    }
    public Usuario(int id)
    {
        this.id=id;
        String sql="select * from usuario where id_usuario="+id;
        Object[] fila=operaciones.getObject(sql);
        
        login=""+fila[1];
        password=""+fila[2];
        estado=""+fila[3];
        idPersona=(Integer)fila[4];
    }
 
    public int get_id()
    {
        return id;
    }
    public void guardar_en_BD()
    {
        int id=0;
        String sql="insert into usuario(login,password,persona_id,estado) values('"+login+"','"+password+"',"+idPersona+",'"+estado+"')";
        id=operaciones.guardarYRecuperarId(sql);
        this.id=id;  
    }
    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login=login;
    }
    public void setContrasenia(String contrasenia){
        this.password=contrasenia;
    }
    public void actualizar(){
         String sql="UPDATE usuario "
                + "SET login ='"+login+"',"
               
                + " password ='"+password+"'"
                + " WHERE id_usuario ="+id;
        operaciones.insertar(sql);
    }
    public int getIdPersona(){
        return idPersona;
    }
    public boolean esAdmin(){
        if(login.equals("admin"))
            return true;
        else
            return false;
    }
}
