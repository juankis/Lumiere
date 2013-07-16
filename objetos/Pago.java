/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;
import java.util.Date;

/**
 *
 * @author juanki
 */
public class Pago {
    private int id;
    private int monto_total;
    private Date fecha_pago= new Date();
    private String estado;
    private int a_cuenta;
    private int descuento;
    private int idPedido;
    Operaciones operaciones=new Operaciones();
    
    public Pago(int monto_total,String estado,int a_cuenta,int descuento,int idPedido)
    {
        this.monto_total=monto_total;
        this.estado=estado;
        this.a_cuenta=a_cuenta;
        this.descuento=descuento;
        this.idPedido=idPedido;
    }
       public int getId(){
        return id;
    }
    public void guardar_en_BD()
    {
        String sql="insert into pago(monto_total,estado,a_cuenta,descuento,pedido_id)"
                + "values("+monto_total+",'"+estado+"',"+a_cuenta+","+descuento+","+idPedido+")";
        id=operaciones.guardarYRecuperarId(sql);
    }
}
