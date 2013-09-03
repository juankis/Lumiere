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
    private double monto_total;
    private Date fecha_pago= new Date();
    private String estado;
    
    private double descuento;
    private int idPedido;
    private double costoLente;
    private double costoArmazon;
    private double costoConsulta;
    private double costoGafa;
    
    private double saldo;
    Operaciones operaciones=new Operaciones();
    
    public Pago(double saldo,double descuento,int idPedido,
                double costoLente,double costoArmazon,double costoGafa,double costoConsulta)
    {
        this.saldo=saldo;
        this.costoGafa=costoGafa;
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
        saldo=Double.parseDouble(""+fila[2]);
        descuento=Double.parseDouble(""+fila[3]);
        costoArmazon=Double.parseDouble(""+fila[4]);
        costoConsulta=Double.parseDouble(""+fila[5]);
        costoLente=Double.parseDouble(""+fila[6]);
        costoGafa=Double.parseDouble(""+fila[7]);
        }
    }
    public Pago(){}
    public int getId(){
        return id;
    }
    public int getIdPedido(){
        return idPedido;
    }
    public double getMontoTotal(){
        return costoArmazon+costoConsulta+costoLente;
    }
    public int getACuenta(){
        String sql="SELECT SUM(a_cuenta) "
                + "FROM a_cuenta "
                + "WHERE pago_id = "+id;

        return operaciones.consultarGetInt(sql);
    }
    public double getDescuento(){
        return descuento;
    }
    public double getCostoLente(){
        return costoLente;
    }
    public double getCostoArmazon(){
        return costoArmazon;
    }
    public double getCostoConsulta(){
        return costoConsulta;
    }
    public double getCostogafa(){
        return costoConsulta;
    }
    public int getSaldo(){
        String sql="SELECT pag.saldo "
                + "FROM pago pag "
                + "WHERE pag.id = "+id;

        return operaciones.consultarGetInt(sql);
    }
    public void setIdPedido(int id){
       idPedido=id;
    }
    public void setMontoTotal(double monto){
        monto_total=monto;
    }
    public void setACuenta(double aCuenta,double idUsuario){
        
        String sql="insert into a_cuenta(a_cuenta,fecha_deposito,pago_id,usuario_id_usuario)"           
                + "values("+aCuenta+",CURDATE(),"+id+","+idUsuario+")";
              
        operaciones.guardarYRecuperarId(sql);
               sql="UPDATE pago "
                + "SET saldo =saldo - "+aCuenta
                + " WHERE id ="+id;
         operaciones.insertar(sql);      
    }
    public void setDescuento(double descuento){
        this.descuento=descuento;
    }
    public void setCostoLente(double lente){
        costoLente=lente;
    }
    public void setCostoArmazon(double armazon){
        costoArmazon=armazon;
    }
    public void setCostoConsulta(double consulta){
        costoConsulta=consulta;
    }
    public void setCostoGafa(double costoGafa){
        this.costoGafa=costoGafa;
    }
    public void setSaldo(double nuevoSaldo){
        //a_cuenta+=nuevoSaldo;
    }
    public void guardar_en_BD()
    {
        String sql="insert into pago(saldo,descuento,pedido_id,costo_lente,"
                + "costo_armazon,costo_consulta,costo_gafa)"
                + "values("+saldo+","+descuento+","+idPedido+""
                + ","+costoLente+","+costoArmazon+","+costoConsulta+","+costoGafa+")";
        
        id=operaciones.guardarYRecuperarId(sql);
    }
    public void actualizar(){
        String sql="UPDATE pago "
                + " SET "
                + "saldo = "+saldo+","
                
                + "descuento ="+descuento+","
                + "pedido_id ="+idPedido+","
                + "costo_lente ="+costoLente+","
                + "costo_armazon ="+costoArmazon+","
                + "costo_gafa ="+costoGafa+","
                + "costo_consulta ="+costoConsulta
                + " WHERE id ="+id;
      operaciones.insertar(sql);
    }
}
