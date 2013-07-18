/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;
import java.sql.Time;
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
    private int idUsuario;
    private int idCliente;
    private Operaciones operaciones=new Operaciones();
    
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
        this.Der_cerca_cilin=Der_cerca_cilin;
        this.Der_cerca_eje=Der_cerca_eje;
        this.Der_cerca_esfer=Der_cerca_esfer;
        this.Der_lejos_cilin=Der_lejos_cilin;
        this.Der_lejos_eje=Der_lejos_eje;
        this.Der_lejos_esfer=Der_lejos_esfer;
        this.Izq_cerca_cilin=Izq_cerca_cilin;
        this.Izq_cerca_eje=Izq_cerca_eje;
        this.Izq_cerca_esfer=Izq_cerca_esfer;
        this.Izq_lejos_cilin=Izq_lejos_cilin;
        this.Izq_lejos_eje=Izq_lejos_eje;
        this.Izq_lejos_esfer=Izq_lejos_esfer;
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
    public void guardarEnBD(){
        
        String sql="insert into pedido(fecha_ingreso,fecha_entrega,hora_entrega, persona_id, usuario_id_usuario )values('"+new java.sql.Date(fecha_ingreso.getTime())+"','"+new java.sql.Date(fecha_entrega.getTime())+"','"+new java.sql.Time(hora_entrega.getTime())+"',"+idCliente+",1)";
        id=operaciones.guardarYRecuperarId(sql);
   }
    public int getId(){
        return id;
    }
            
}
