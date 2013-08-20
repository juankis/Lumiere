/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author juanki
 */
public class Pedido {
    private int id;
    private Date fecha_ingreso;
    private Date fecha_entrega;
    private Time hora_entrega;
    private String Der_lejos_esfer;
    private String Der_lejos_cilin;
    private String Der_lejos_eje;
    private String Izq_lejos_esfer;
    private String Izq_lejos_cilin;
    private String Izq_lejos_eje;
    private String Der_cerca_esfer;
    private String Der_cerca_cilin;
    private String Der_cerca_eje;
    private String Izq_cerca_esfer;
    private String Izq_cerca_cilin;
    private String Izq_cerca_eje;
    private String audicion;
    private String altura;
    private String d_p_lejos;
    private String d_p_cerca;
    private String observaciones;
    private String estado;
    private String doctor;
    private int idMontura;
    private int idUsuario;
    private int idCliente;
    private Operaciones operaciones=new Operaciones();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    private java.text.SimpleDateFormat sdfHora = new java.text.SimpleDateFormat("hh:mm:ss");
    
    private String ingresoFecha;
    private String entregaFecha;
    public Pedido(Date fecha_ingreso,
    Date fecha_entrega,
    Time hora_entrega,
    String Der_lejos_esfer,
    String Der_lejos_cilin,
    String Der_lejos_eje,
    String Izq_lejos_esfer,
    String Izq_lejos_cilin,
    String Izq_lejos_eje,
    String Der_cerca_esfer,
    String Der_cerca_cilin,
    String Der_cerca_eje,
    String Izq_cerca_esfer,
    String Izq_cerca_cilin,
    String Izq_cerca_eje,
    String audicion,
    String altura,
    String d_p_lejos,
    String d_p_cerca,
    String observaciones,
    String estado,
    String doctor,
    
    int idCliente,
    int idUsuario)
    {
        this.fecha_ingreso=fecha_ingreso;
        this.fecha_entrega=fecha_entrega;
        this.hora_entrega=hora_entrega;
        
        this.Der_lejos_esfer=Der_lejos_esfer;
        this.Der_lejos_cilin=Der_lejos_cilin;
        this.Der_lejos_eje=Der_lejos_eje;
        
        this.Izq_lejos_esfer=Izq_lejos_esfer;
        this.Izq_lejos_cilin=Izq_lejos_cilin;
        this.Izq_lejos_eje=Izq_lejos_eje;
        
        this.Der_cerca_esfer=Der_cerca_esfer;
        this.Der_cerca_cilin=Der_cerca_cilin;
        this.Der_cerca_eje=Der_cerca_eje;
       
        this.Izq_cerca_esfer=Izq_cerca_esfer; 
        this.Izq_cerca_cilin=Izq_cerca_cilin;
        this.Izq_cerca_eje=Izq_cerca_eje;
       
       
        this.audicion=audicion;
        this.altura=altura;
        this.d_p_lejos=d_p_lejos;
        this.d_p_cerca=d_p_cerca;
        this.observaciones=observaciones;
        this.estado=estado;
        this.doctor=doctor;
       
        this.idCliente=idCliente;
        this.idUsuario=idUsuario;
        
    }
    public Pedido(Date fecha_ingreso,
    Date fecha_entrega,
    Time hora_entrega,
    String Der_lejos_esfer,
    String Der_lejos_cilin,
    String Der_lejos_eje,
    String Izq_lejos_esfer,
    String Izq_lejos_cilin,
    String Izq_lejos_eje,
    String Der_cerca_esfer,
    String Der_cerca_cilin,
    String Der_cerca_eje,
    String Izq_cerca_esfer,
    String Izq_cerca_cilin,
    String Izq_cerca_eje,
    String audicion,
    String altura,
    String d_p_lejos,
    String d_p_cerca,
    String observaciones,
    String estado,
    String doctor,
    int idMontura,
    int idCliente,
    int idUsuario)
    {
        this.fecha_ingreso=fecha_ingreso;
        this.fecha_entrega=fecha_entrega;
        this.hora_entrega=hora_entrega;
        
        this.Der_lejos_esfer=Der_lejos_esfer;
        this.Der_lejos_cilin=Der_lejos_cilin;
        this.Der_lejos_eje=Der_lejos_eje;
        
        this.Izq_lejos_esfer=Izq_lejos_esfer;
        this.Izq_lejos_cilin=Izq_lejos_cilin;
        this.Izq_lejos_eje=Izq_lejos_eje;
        
        this.Der_cerca_esfer=Der_cerca_esfer;
        this.Der_cerca_cilin=Der_cerca_cilin;
        this.Der_cerca_eje=Der_cerca_eje;
       
        this.Izq_cerca_esfer=Izq_cerca_esfer; 
        this.Izq_cerca_cilin=Izq_cerca_cilin;
        this.Izq_cerca_eje=Izq_cerca_eje;
       
       
        this.audicion=audicion;
        this.altura=altura;
        this.d_p_lejos=d_p_lejos;
        this.d_p_cerca=d_p_cerca;
        this.observaciones=observaciones;
        this.estado=estado;
        this.doctor=doctor;
        this.idMontura=idMontura;
        this.idCliente=idCliente;
        this.idUsuario=idUsuario;
        
    }
    public Pedido(int idPedido){
        id=idPedido;
        String sql="select * from pedido where id="+idPedido;
        Object[] fila=operaciones.getObject(sql);
        
        if(fila.length!=1){
                    
        try{    
           this.fecha_ingreso=(sdf.parse(""+fila[1]));
           this.fecha_entrega=(sdf.parse(""+fila[2]));
           this.hora_entrega=new Time((sdfHora.parse(""+fila[3])).getTime());
          
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        this.ingresoFecha=""+fila[1];
        this.entregaFecha=""+fila[2];
                
        this.Der_lejos_esfer=""+fila[4];
        this.Der_lejos_cilin=""+fila[5];
        this.Der_lejos_eje=""+fila[6];
        
        this.Izq_lejos_esfer=""+fila[7];
        this.Izq_lejos_cilin=""+fila[8];
        this.Izq_lejos_eje=""+fila[9];
        
        this.Der_cerca_esfer=""+fila[10];
        this.Der_cerca_cilin=""+fila[11];
        this.Der_cerca_eje=""+fila[12];
       
        this.Izq_cerca_esfer=""+fila[13]; 
        this.Izq_cerca_cilin=""+fila[14];
        this.Izq_cerca_eje=""+fila[15];
       
       
        this.audicion=""+fila[16];
        this.altura=""+fila[17];
        this.d_p_lejos=""+fila[18];
        this.d_p_cerca=""+fila[19];
        this.observaciones=""+fila[20];
        this.estado=""+fila[21];
        this.doctor=""+fila[22];
        
        this.idCliente=Integer.parseInt(""+fila[23]);
        this.idUsuario=Integer.parseInt(""+fila[24]);
        this.idMontura=Integer.parseInt(""+fila[25]);
        }else{
            System.out.println("pedido es nulllll");
        }
    }
    public Pedido(){}
    public void guardarEnBD(){
        
        String sql="insert into pedido(fecha_ingreso,fecha_entrega,hora_entrega,der_cerca_cilin,der_cerca_eje,der_cerca_esfer,der_lejos_cilin,der_lejos_eje,der_lejos_esfer,"
                + "izq_cerca_cilin,izq_cerca_eje,izq_cerca_esfer,izq_lejos_cilin,izq_lejos_eje,izq_lejos_esfer,"
                + "adicion,altura, d_p_lejos, d_p_cerca,observaciones,estado,doctor, montura_id,persona_id, usuario_id_usuario )"
                + "values('"+new java.sql.Date(fecha_ingreso.getTime())+"','"+new java.sql.Date(fecha_entrega.getTime())+"','"+new java.sql.Time(hora_entrega.getTime())+"'"
                + ",'"+Der_cerca_cilin+"','"+Der_cerca_eje+"','"+Der_cerca_esfer+"','"+Der_lejos_cilin+"','"+Der_lejos_eje+"','"+Der_lejos_esfer+"'"
                + ",'"+Izq_cerca_cilin+"','"+Izq_cerca_eje+"','"+Izq_cerca_esfer+"','"+Izq_lejos_cilin+"','"+Izq_lejos_eje+"','"+Izq_lejos_esfer+"'"
                + ",'"+audicion+"','"+altura+"','"+d_p_lejos+"','"+d_p_cerca+"','"+observaciones+"','"+estado+"','"+doctor+"',"+idMontura+","+idCliente+","+idUsuario+")";
        id=operaciones.guardarYRecuperarId(sql);
   }
    public int getId(){
        return id;
    }
    public int getIdMontura(){
        return idMontura;
    }
    public Date getFechaEntrega(){
        return fecha_entrega;
    }
    public Date getFechaIngreso(){
        return fecha_ingreso;
    }
    public String getStringFechaEntrega(){
        return entregaFecha;
    }
    public String getStringFechaIngreso(){
        return ingresoFecha;
    }
    public Time getHoraEntrega(){
        return hora_entrega;
    }
    public String getDerCerEsf(){
        return Der_cerca_esfer;
    }
    public String getDerCerCil(){
        return Der_cerca_cilin;
    }
    public String getDerCerEje(){
        return Der_cerca_eje;
    }
    public String getIzqCerEsf(){
        return Izq_cerca_esfer;
    }
    public String getIzqCerCil(){
        return Izq_cerca_cilin;
    }
    public String getIzqCerEje(){
        return Izq_cerca_eje;
    }
    public String getDerLejEsf(){
        return Der_lejos_esfer;
    }
    public String getDerLejCil(){
        return Der_lejos_cilin;
    }
    public String getDerLejEje(){
        return Der_lejos_eje;
    }
    public String getIzqLejEsf(){
        return Izq_lejos_esfer;
    }
    public String getIzqLejCil(){
        return Izq_lejos_cilin;
    }
    public String getIzqLejEje(){
        return Izq_lejos_eje;
    }
    public String getAudicion(){
        return audicion;
    }
    public String getAltura(){
        return altura;
    }
    public String getDPLejos(){
        return d_p_lejos;
    }
    public String getDPCerca(){
        return d_p_cerca;
    }
    public String getObservaciones(){
        return observaciones;
    }
    public String getEstado(){
        return estado;
    }
    public String getDoctor(){
        return doctor;
    }
    public int getIdCliente(){
        return idCliente;
    }
    public void setFechaEntrega(Date fe){
        fecha_entrega=fe;
    }
    public void setFechaIngreso(Date fi){
        fecha_ingreso=fi;
    }
    public void setHoraEntrega(Time he){
        hora_entrega=he;
    }
    public void setDerCerEsf(String dce){
        Der_cerca_esfer=dce;
    }
    public void setDerCerCil(String dcc){
        Der_cerca_cilin=dcc;
    }
    public void setDerCerEje(String dce){
        Der_cerca_eje=dce;
    }
    public void setIzqCerEsf(String ice){
        Izq_cerca_esfer=ice;
    }
    public void setIzqCerCil(String icl){
        Izq_cerca_cilin=icl;
    }
    public void setIzqCerEje(String ice){
        Izq_cerca_eje=ice;
    }
    public void setDerLejEsf(String dle){
        Der_lejos_esfer=dle;
    }
    public void setDerLejCil(String dlc){
        Der_lejos_cilin=dlc;
    }
    public void setDerLejEje(String dle){
        Der_lejos_eje=dle;
    }
    public void setIzqLejEsf(String ile){
        Izq_lejos_esfer=ile;
    }
    public void setIzqLejCil(String ilc){
        Izq_lejos_cilin=ilc;
    }
    public void setIzqLejEje(String ile){
        Izq_lejos_eje=ile;
    }
    public void setAudicion(String aud){
        audicion=aud;
    }
    public void setAltura(String alt){
        altura=alt;
    }
    public void setDPLejos(String dpl){
        d_p_lejos=dpl;
    }
    public void setDPCerca(String dpc){
        d_p_cerca=dpc;
    }
    public void setObservaciones(String obs){
        observaciones=obs;
    }
    public void setEstado(String est){
        estado=est;
    }
    public void setDoctor(String doc){
       doctor=doc;
    }
    public void setIdCliente(int id){
        idCliente=id;
    }
    public void actualizar(){
        String sql="UPDATE pedido "
                + "SET"
        + " fecha_ingreso='"+new java.sql.Date(fecha_ingreso.getTime())+"',"
        + " fecha_entrega='"+new java.sql.Date(fecha_entrega.getTime())+"',"
        + " hora_entrega='"+new java.sql.Time(hora_entrega.getTime())+"',"
        
        + " Der_lejos_esfer='"+Der_lejos_esfer+"',"
        + " Der_lejos_cilin='"+Der_lejos_cilin+"',"
        + " Der_lejos_eje='"+Der_lejos_eje+"',"
        
        + " Izq_lejos_esfer='"+Izq_lejos_esfer+"',"
        + " Izq_lejos_cilin='"+Izq_lejos_cilin+"',"
        + " Izq_lejos_eje='"+Izq_lejos_eje+"',"
        
        + " Der_cerca_esfer='"+Der_cerca_esfer+"',"
        + " Der_cerca_cilin='"+Der_cerca_cilin+"',"
        + " Der_cerca_eje='"+Der_cerca_eje+"',"
       
        + " Izq_cerca_esfer='"+Izq_cerca_esfer+"'," 
        + " Izq_cerca_cilin='"+Izq_cerca_cilin+"',"
        + " Izq_cerca_eje='"+Izq_cerca_eje+"',"
       
       
        + " adicion='"+audicion+"',"
        + " altura='"+altura+"',"
        + " d_p_lejos='"+d_p_lejos+"',"
        + " d_p_cerca='"+d_p_cerca+"',"
        + " observaciones='"+observaciones+"',"
        + " estado='"+estado+"',"
        + " doctor='"+doctor+"',"
        + " persona_id="+idCliente+","
        + " usuario_id_usuario="+idUsuario
                + " WHERE id ="+id;
      operaciones.insertar(sql);
    }
    /*
     this.audicion=""+fila[16];
        this.altura=""+fila[17];
        this.d_p_lejos=""+fila[18];
        this.d_p_cerca=""+fila[19];
        this.observaciones=""+fila[20];
        this.estado=""+fila[21];
        this.doctor=""+fila[22];
        this.idCliente=Integer.parseInt(""+fila[23]);
        this.idUsuario=Integer.parseInt(""+fila[24]);
     */
}
