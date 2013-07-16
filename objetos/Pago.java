/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

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
}
