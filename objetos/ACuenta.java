/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Date;
import conexion.Operaciones;
/**
 *
 * @author juanki
 */
public class ACuenta {
    int id;
    int aCuenta;
    Date fechaDeposito;
    int pagoId;
    int usuarioId;
    private Operaciones operaciones=new Operaciones();
    public ACuenta(int aCuenta,Date fechaDeposito,int pagoId, int usuarioID){
        this.aCuenta=aCuenta;
        this.fechaDeposito=fechaDeposito;
        this.pagoId=pagoId;
        this.usuarioId=usuarioID;
    }
    public void guardarEnBD(){
        
        String sql="insert into a_cuenta(a_cuenta,fecha_deposito,pago_id,usuario_id_usuario) values("+aCuenta+",'"+new java.sql.Date(fechaDeposito.getTime())+"',"+pagoId+","+usuarioId+")";
        int id=operaciones.guardarYRecuperarId(sql);
        
        this.id=id;
   }
    public int getId(){
        return id;
    }
}
