/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;
import java.text.ParseException;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author juanki
 */
public class Montura {
    private int id;
    private String marca;
    private String modelo;
    private String tamanio;
    private String color;
    private int cantidad;
    private String proveedor;
    private double costoUnitario;
    private double precioDeVentaUnitario;
    private Date fechaIngreso;
    private String ingresoFecha;
    private String monturaGafa;
    private Operaciones operaciones=new Operaciones();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    
    public Montura(String marca,String modelo,String tamanio,String color)
    {
        this.marca=marca;
        this.modelo=modelo;
        this.tamanio=tamanio;
        this.color=color;
        cantidad=-1;
        costoUnitario=-1;
        precioDeVentaUnitario=-1;
        
    }
    public Montura(){
    
    }
    public Montura(int idMontura){
        this.id=idMontura;
        String sql="select * from montura where id="+idMontura;
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        
        marca=""+fila[1];
        modelo=""+fila[2];
        tamanio=""+fila[3];
        color=""+fila[4];
        cantidad=(Integer)fila[5];
        proveedor=""+fila[6];
        costoUnitario=(Double.valueOf(""+fila[7]));
        precioDeVentaUnitario=(Double.valueOf(""+fila[8]));//fila[8];
         try{    
           if(!(fila[9]==null))  
           this.fechaIngreso=(sdf.parse(""+fila[9]));
           
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        this.ingresoFecha=""+fila[9];
       
        monturaGafa=""+fila[10];
        }
    }
    public int buscarId(){
        String query="Select id from montura where marca='"+marca+"' and modelo='"+modelo+"' and tamanio='"+tamanio+"' and color='"+color+"'";
        id=operaciones.consultarId(query);
        return id;
    }
    public DefaultComboBoxModel listaMarcas(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.marca FROM montura m WHERE m.marca LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
   
    public DefaultComboBoxModel listaColores(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.color FROM montura m WHERE m.color LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaModelos(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.modelo FROM montura m WHERE m.modelo LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaTamanios(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.tamanio FROM montura m WHERE m.tamanio LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public int getId(){
        return id;
    }
    /*
    public void guardar_en_BD()
    {
        String sql="insert into montura(marca,color,tipo,tamanio,estado,codigo,pedido_id)"
                + "values('"+marca+"','"+color+"','"+tipo+"','"+tamanio+"','"+estado+"','"+codigo+"',"+idPedido+")";
        id=operaciones.guardarYRecuperarId(sql);
    }*/
    public String getMarca(){
        return marca;
    }
    
    public String getColor(){
        return color;
    }
    public String getModelo(){
        return modelo;
    }
    public String getTamanio(){
        return tamanio;
    }
    
    public void setMarca(String m){
        marca=m;
    }
    
    public void setColor(String c){
        color=c;
    }
    
    public void setTamanio(String t){
        tamanio=t;
    }
    
    public void descontarUno(){
        cantidad=cantidad-1;
         String sql="UPDATE montura "
                + "SET cantidad ="+cantidad+","
                + " WHERE id ="+id;
        operaciones.insertar(sql);
    }
    public int crearNegativo(){
        String sql="insert into montura(marca,modelo,tamanio,color,cantidad,proveedor,costoUnitario,precioDeVentaUnitario)"
                + "values('"+marca+"','"+modelo+"','"+tamanio+"','"+color+"',"+cantidad+",'"+proveedor+"',"+costoUnitario+","+precioDeVentaUnitario+")";
        id=operaciones.guardarYRecuperarId(sql);
        return id;
    }/*
    public void actualizar(){
        String sql="UPDATE montura "
                + "SET marca ='"+marca+"',"
                + "codigo ='"+codigo+"',"
                + "color ='"+color+"',"
                + "tipo ='"+tipo+"',"
                + "tamanio ='"+tamanio+"',"
                + "estado = '"+estado+"'"
                + " WHERE id ="+id;
      operaciones.insertar(sql);
    }*/
}
