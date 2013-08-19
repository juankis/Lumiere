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
    private int costoLente;
    private int costoArmazon;
    private int costoConsulta;
    Operaciones operaciones=new Operaciones();
    
    public Pago(int monto_total,String estado,int a_cuenta,int descuento,int idPedido,
                int costoLente,int costoArmazon,int costoConsulta)
    {
        this.monto_total=monto_total;
        this.estado=estado;
        this.a_cuenta=a_cuenta;
        this.descuento=descuento;
        this.idPedido=idPedido;
        this.costoLente=costoLente;
        this.costoArmazon=costoArmazon;
        this.costoConsulta=costoConsulta;
    }
    public Pago(int idPedido)
    {
        this.idPedido=idPedido;
        String sql="select * from pago where pedido_id="+idPedido;
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        id=(Integer)fila[0];
        monto_total=Integer.parseInt(""+fila[2]);
        estado=""+fila[4];
        a_cuenta=Integer.parseInt(""+fila[5]);
        descuento=Integer.parseInt(""+fila[6]);
        costoLente=Integer.parseInt(""+fila[7]);
        costoArmazon=Integer.parseInt(""+fila[8]);
        costoConsulta=Integer.parseInt(""+fila[9]);
        }
    }
    public Pago(){}
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
    public int getCostoLente(){
        return costoLente;
    }
    public int getCostoArmazon(){
        return costoArmazon;
    }
    public int getCostoConsulta(){
        return costoConsulta;
    }
    public int getSaldo(){
        return getMontoTotal()-getDescuento()-getACuenta();
    }
    public void setIdPedido(int id){
       idPedido=id;
    }
    public void setMontoTotal(int monto){
        monto_total=monto;
    }
    public void setACuenta(int aCuenta){
        a_cuenta=aCuenta;
    }
    public void setDescuento(int descuento){
        this.descuento=descuento;
    }
    public void setCostoLente(int lente){
        costoLente=lente;
    }
    public void setCostoArmazon(int armazon){
        costoArmazon=armazon;
    }
    public void setCostoConsulta(int consulta){
        costoConsulta=consulta;
    }
    public void setSaldo(int nuevoSaldo){
        a_cuenta+=nuevoSaldo;
    }
    public void guardar_en_BD()
    {
        String sql="insert into pago(monto_total,estado,a_cuenta,descuento,pedido_id,costo_lente,"
                + "costo_armazon,costo_consulta)"
                + "values("+monto_total+",'"+estado+"',"+a_cuenta+","+descuento+","+idPedido+""
                + ","+costoLente+","+costoArmazon+","+costoConsulta+")";
        
        id=operaciones.guardarYRecuperarId(sql);
    }
    public void actualizar(){
        String sql="UPDATE pago "
                + "SET monto_total ="+monto_total+","
                + "estado = '"+estado+"',"
                + "a_cuenta ="+a_cuenta+","
                + "descuento ="+descuento+","
                + "pedido_id ="+idPedido+","
                + "costo_lente ="+costoLente+","
                + "costo_armazon ="+costoArmazon+","
                + "costo_consulta ="+costoConsulta
                + " WHERE id ="+id;
      operaciones.insertar(sql);
    }
}
