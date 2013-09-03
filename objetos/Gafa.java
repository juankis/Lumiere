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
public class Gafa {
    private int id;
    private String marca="";
    private String modelo="";
    private String tamanio="";
    private String color="";
    private int cantidadStock;
    private String proveedor;
    private double costoUnitario;
    private double precioDeVentaUnitario;
    private Date fechaIngreso;
    private String ingresoFecha;
    private String monturaGafa;
    
    private double precio;
    int idProveedor;
    int idPedido;
    private Operaciones operaciones=new Operaciones();
    private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    
    public Gafa(String marca,String modelo,String tamanio,String color,String monturaGafa)
    {
        this.marca=marca;
        this.modelo=modelo;
        this.tamanio=tamanio;
        this.color=color;
        this.monturaGafa=monturaGafa;
        cantidadStock=-1;
        costoUnitario=-1;
        precioDeVentaUnitario=-1;
        
    }
    public Gafa(){
    
    }
    public Gafa(int idPedido){
        this.idPedido=idPedido;
        if(existeMontura()){
        String sql="SELECT m.id, m.marca, m.modelo, m.tamanio, m.color, m.montura_gafa"
                 +" FROM montura m, registroEntradasMontura rem, monturaPedido mp"
                 +" WHERE mp.pedido_id ="+idPedido
                 +" AND mp.registroEntradasMontura_id = rem.id"
                 +" AND rem.montura_id = m.id";
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        id=(Integer)fila[0];
        marca=""+fila[1];
        modelo=""+fila[2];
        tamanio=""+fila[3];
        color=""+fila[4];
        monturaGafa=""+fila[5];
        //cantidadStok=(Integer)fila[6];
        }
        }else{
        marca="";
        modelo="";
        tamanio="";
        color="";    
        }
    }
    public void recuperarDatosGafa(int idGafa){
        //this.idPedido=idPedido;
        id=idGafa;
        
        String sql="SELECT m.id, m.marca, m.modelo, m.tamanio, m.color, m.montura_gafa, m.cantidadStock"
                 +" FROM montura m"
                 +" WHERE m.id ="+id;
        Object[] fila=operaciones.getObject(sql);
        
        id=(Integer)fila[0];
        marca=""+fila[1];
        modelo=""+fila[2];
        tamanio=""+fila[3];
        color=""+fila[4];
        monturaGafa=""+fila[5];
        cantidadStock=Integer.parseInt(""+fila[6]);
        
    }
    public int getCantidadStock(){
        return cantidadStock;
    }
    public boolean existeMontura(){
        boolean res=false;
        String query = "SELECT mp.id FROM monturaPedido mp WHERE mp.pedido_id="+idPedido;
        int id=operaciones.consultarGetInt(query);
        if(id!=0)
            res=true;
        return res;
    }
    public int buscarIdMontura(){
        String query="Select id from montura where marca='"+marca+"' and modelo='"+modelo+"' and tamanio='"+tamanio+"' and color='"+color+"' and montura_gafa='MONTURA'";
        id=operaciones.consultarId(query);
        return id;
    }
    public int buscarIdGafa(){
        String query="Select id from montura where marca='"+marca+"' and modelo='"+modelo+"' and tamanio='"+tamanio+"' and color='"+color+"' and montura_gafa='GAFA DE SOL'";
        id=operaciones.consultarId(query);
        return id;
    }
    public DefaultComboBoxModel listaMarcas(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.marca FROM montura m WHERE m.montura_gafa='"+monturaGafa+"' and m.marca LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
   
    public DefaultComboBoxModel listaColores(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.color FROM montura m WHERE m.montura_gafa='"+monturaGafa+"' and m.color LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaModelos(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.modelo FROM montura m WHERE m.montura_gafa='"+monturaGafa+"' and m.modelo LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaTamanios(String cadenaEscrita){
        
        String query = "SELECT DISTINCT m.tamanio FROM montura m WHERE m.montura_gafa='"+monturaGafa+"' and m.tamanio LIKE '" + cadenaEscrita + "%';";
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
    public void setMontutaGafa(String monturaGafa){
        this.monturaGafa=monturaGafa;
    }
    public int idMasAntiguoEntradaEnInventario(){
        return operaciones.consultarId("SELECT re.id  FROM registroEntradas re, registroEntradasMontura rem "
                +" WHERE rem.montura_id="+id
                +" AND rem.registroEntradas_id=re.id "
                +" AND re.cantidad>0 "
                +" ORDER BY re.fechaDeIngreso ASC LIMIT 1");
    }
    public void descontarEnProveedor(){
        String sql="UPDATE registroEntradas "
                + "SET cantidad = cantidad -1 "
                + " WHERE "
                + " id ="+idMasAntiguoEntradaEnInventario();
        operaciones.insertar(sql);
    }
    public void descontarUno(){
        
         String sql="UPDATE montura "
                + "SET cantidadStock = cantidadStock - 1 "
                + " WHERE id ="+id;
        operaciones.insertar(sql);
    //    descontarEnProveedor();
    }
    public boolean losDatosSonVacios(){
        boolean res=false;
        if(marca.equals("")&&modelo.equals("")&&tamanio.equals("")&&color.equals("")){
            res=true;
        }
        return res;
    }
    public void reservar(){
       int idre=  operaciones.consultarGetInt("Select rem.registroEntradas_id from RegistroEntradasMontura rem, registroEntradas re where re.id = rem.registroEntradas_id AND re.id="+idMasAntiguoEntradaEnInventario());   
       operaciones.insertar("Update RegistroEntradas set reservado = reservado + 1 where id="+idre);
    }
    public double getPrecio(){
        String sql="SELECT re.precioDeVentaUnitario  FROM registroEntradas re, registroEntradasMontura rem "
                +" WHERE rem.montura_id="+id
                +" AND re.id=rem.registroEntradas_id "
                +" AND re.cantidad>0 "
                +" ORDER BY re.fechaDeIngreso ASC LIMIT 1";
        precio=operaciones.recuperarDouble(sql);
        return precio;
    }
    public int guardarEnBD(){
        String sql="insert into montura(marca,modelo,tamanio,color,cantidadStock,montura_gafa)"
                + "values('"+marca+"','"+modelo+"','"+tamanio+"','"+color+"',-1,'"+monturaGafa+"')";
        id=operaciones.guardarYRecuperarId(sql);
       // descontarUno();
        return id;
    }
    public void crearProveedorNegativo(){
        String sql="insert into proveedor(nombre,cantidad,costoUnitario,precioDeVentaUnitario,fechaDeIngreso,montura_id)"
                + "values('ninguno',-1,-1,-1,CURDATE(),"+id+")";
        idProveedor=operaciones.guardarYRecuperarId(sql);
        
    }
    /*
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
