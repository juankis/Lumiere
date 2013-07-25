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
    public Pago(int idPedido)
    {
        this.idPedido=idPedido;
        String sql="select * from pago where pedido_id="+idPedido;
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        id=(Integer)fila[0];
        System.out.println(fila[2]);
        monto_total=Integer.parseInt(""+fila[2]);
        estado=""+fila[4];
        a_cuenta=Integer.parseInt(""+fila[5]);
        descuento=Integer.parseInt(""+fila[6]);
        }
    }        
    public int getId(){
        return id;
    }
    public int getIdPedido(){
        return idPedido;
    }
    public int getMontoTotal(){
        return monto_total;
    }
    public int getACuenta(){
        return a_cuenta;
    }
    public int getDescuento(){
        return descuento;
    }
    public void guardar_en_BD()
    {
        String sql="insert into pago(monto_total,estado,a_cuenta,descuento,pedido_id)"
                + "values("+monto_total+",'"+estado+"',"+a_cuenta+","+descuento+","+idPedido+")";
        
        id=operaciones.guardarYRecuperarId(sql);
    }
}
