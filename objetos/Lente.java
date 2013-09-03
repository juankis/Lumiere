/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;
import conexion.Conexion;
import conexion.Operaciones;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author juanki
 */
public class Lente {
    private int id;
    private String material;
    private String tipo;
    private String color;
    private String vision;
    private int cantidadStock;
    private double precio;
    private int idPedido;
    private Operaciones operaciones=new Operaciones();
    
    public Lente(String material,
    String tipo,
    String color,
    String vision
    
    )
    {
        this.material=material;
        this.tipo=tipo;
        this.color=color;
        this.vision=vision;
       
       
    }
    public Lente(){
    
    }
    public Lente(int idPedido){
        this.idPedido=idPedido;
        if(existeLente()){
        String sql="SELECT l.id, l.material, l.tipo, l.color, l.vision"
                 +" FROM lente l, registroEntradasLente rel, lentePedido lp"
                 +" WHERE lp.pedido_id ="+idPedido
                 +" AND lp.registroEntradasLente_id = rel.id"
                 +" AND rel.lente_id = l.id";
        Object[] fila=operaciones.getObject(sql);
        if(fila.length!=1){
        id=(Integer)fila[0];
        material=""+fila[1];
        tipo=""+fila[2];
        color=""+fila[3];
        vision=""+fila[4];
        }
        }else{
        material="";
        tipo="";
        color="";
        vision="";    
        }
    }
    public void recuperarDatosLente(int idLente){
        this.id=idLente;
        
        String sql="SELECT l.id, l.material, l.tipo, l.color, l.vision, l.cantidadStock"
                 +" FROM lente l"
                 +" WHERE l.id ="+idLente;
        Object[] fila=operaciones.getObject(sql);
       
        //id=(Integer)fila[0];
        material=""+fila[1];
        tipo=""+fila[2];
        color=""+fila[3];
        vision=""+fila[4];
        cantidadStock=Integer.parseInt(""+fila[5]);
    }
    public int getCantidadStock(){
        return cantidadStock;
    }
    public boolean existeLente(){
        boolean res=false;
        String query = "SELECT lp.id FROM lentePedido lp WHERE lp.pedido_id="+idPedido;
        int id=operaciones.consultarGetInt(query);
        System.out.println(id+"********************************************");
        if(id!=0)
            res=true;
        return res;
    }
    public DefaultComboBoxModel listaMateriales(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.material FROM lente l WHERE l.material LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaTipos(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.tipo FROM lente l WHERE l.tipo LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaColores(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.color FROM lente l WHERE l.color LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public DefaultComboBoxModel listaVisiones(String cadenaEscrita){
        
        String query = "SELECT DISTINCT l.vision FROM lente l WHERE l.vision LIKE '" + cadenaEscrita + "%';";
        return operaciones.getModeloCombo(query);
     }
    public void guardarEnBD(){
        String sql="insert into lente(material,tipo,color,vision,cantidadStock)"
                + "values('"+material+"','"+tipo+"','"+color+"','"+vision+"',-1)";
        id=operaciones.guardarYRecuperarId(sql);
    }
    public boolean losDatosSonVacios(){
        boolean res=false;
        if(material.equals("")&&tipo.equals("")&&vision.equals("")&&color.equals("")){
            res=true;
        }
        return res;
    }
    public int getId(){
        return id;
    }
    public String getMaterial(){
        return material;
    }
    public String getTipo(){
        return tipo;
    }
    public String getColor(){
        return color;
    }
    public String getVision(){
        return vision;
    }
   
    public void setMaterial(String m){
        material=m;
    }
    public void setTipo(String t){
        tipo=t;
    }
    public void setColor(String c){
        color=c;
    }
    public void setVision(String v){
        vision=v;
    }
    public void reservar(){
         int idre=  operaciones.consultarGetInt("Select rel.registroEntradas_id from RegistroEntradasLente rel, registroEntradas re where re.id = rel.registroEntradas_id AND re.id="+idMasAntiguoEntradaEnInventario());   
         operaciones.insertar("Update RegistroEntradas set reservado = reservado + 1 where id="+idre);
                
    }
    public void actualizar(){
        String sql="UPDATE lente "
                + "SET material ='"+material+"',"
               
                + "tipo ='"+tipo+"',"
                + "color ='"+color+"',"
                + "vision ='"+vision+"'"
                + " WHERE id ="+id;
        operaciones.insertar(sql);
    }
    public int buscarId(){
        String query="Select id from lente where material='"+material+"' and tipo='"+tipo+"' and color='"+color+"' and vision='"+vision+"'";
        id=operaciones.consultarId(query);
        return id;
    }
    public void descontarUno(){
        
         String sql="UPDATE lente "
                + "SET cantidadStock = cantidadStock - 1 "
                + " WHERE id ="+id;
        operaciones.insertar(sql);
        //descontarEnProveedor();
    }
    public void descontarEnProveedor(){
        String sql="UPDATE registroEntradas "
                + "SET cantidad = cantidad -1 "
                + " WHERE "
                + " id ="+idMasAntiguoEntradaEnInventario();
        operaciones.insertar(sql);
    }
     
    public int idMasAntiguoEntradaEnInventario(){
        return operaciones.consultarId("SELECT re.id  FROM registroEntradas re, registroEntradasLente rel "
                +" WHERE rel.lente_id="+id
                +" AND rel.registroEntradas_id=re.id "
                +" AND re.cantidad>0 "
                +" ORDER BY re.fechaDeIngreso ASC LIMIT 1");
    }
    /*public void reservar(){
        reservarEnProveedor();
    }*/
    public void relacionarPedidoLente(int idPedido){
        int idREL=operaciones.consultarId("select rel.id from lente l, registroEntradasLente rel where rel.lente_id="+id+" AND rel.registroEntradas_id="+idMasAntiguoEntradaEnInventario() );
        String sql="insert into LentePedido(pedido_id,RegistroEntradasLente_id) values("+idPedido+","+idREL+")";
        operaciones.insertar(sql);
    }
    public void reservarEnProveedor(){
        String sql="UPDATE registroEntradas "
                + "SET reservado = reservado + 1 "
                + " WHERE "
                + " id ="+idMasAntiguoEntradaEnInventario();
        operaciones.insertar(sql);
    }
    public double getPrecio(){
        String sql="SELECT re.precioDeVentaUnitario  FROM registroEntradas re, registroEntradasLente rel "
                +" WHERE rel.lente_id="+id
                +" AND re.id=rel.registroEntradas_id "
                +" AND re.cantidad>0 "
                +" ORDER BY re.fechaDeIngreso ASC LIMIT 1";
        precio=operaciones.recuperarDouble(sql);
        return precio;
    }
}
