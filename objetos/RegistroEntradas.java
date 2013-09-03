/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import conexion.Operaciones;

/**
 *
 * @author juanki
 */
public class RegistroEntradas {
   private int id;
   private int idDefecto;
   private Operaciones operaciones=new Operaciones(); 
   public RegistroEntradas(){
   
   }
   public int getIdRegistroEntradaLente(int idLente){
       String sql="SELECT rel.id FROM RegistroEntradas re,RegistroEntradasLente rel  where rel.lente_id="+idLente+" AND re.nombre='ninguno'";
       int idREL=operaciones.consultarId(sql);
       return idREL;
   }
   public int getIdRegistroEntradaLenteDefecto(int idLente){
       String sql="SELECT rel.id FROM RegistroEntradas re,RegistroEntradasLente rel  where rel.lente_id="+idLente+" AND rel.registroEntradas_id="+idMasAntiguoEntradaEnInventarioLente(idLente);
       int idREL=operaciones.consultarId(sql);
       return idREL;
   }
   public int idMasAntiguoEntradaEnInventarioLente(int idLente){
        return operaciones.consultarId("SELECT re.id  FROM registroEntradas re, registroEntradasLente rel "
                +" WHERE rel.lente_id="+idLente
                +" AND rel.registroEntradas_id=re.id "
                +" AND re.cantidad>0 "
                +" ORDER BY re.fechaDeIngreso ASC LIMIT 1");
    }
   public int getIdRegistroEntradaMontura(int idMontura){
       String sql="SELECT rem.id FROM RegistroEntradas re,RegistroEntradasMontura rem  where rem.montura_id="+idMontura+" AND re.nombre='ninguno'";
       int idREM=operaciones.consultarId(sql);
       return idREM;
   }
   public int getIdRegistroEntradaMonturaDefecto(int idMontura){
       String sql="SELECT rem.id FROM RegistroEntradas re,RegistroEntradasMontura rem  where rem.montura_id="+idMontura+" AND rem.registroEntradas_id="+idMasAntiguoEntradaEnInventarioMontura(idMontura);
       int idREM=operaciones.consultarId(sql);
       return idREM;
   }
   public int idMasAntiguoEntradaEnInventarioMontura(int idMontura){
        return operaciones.consultarId("SELECT re.id  FROM registroEntradas re, registroEntradasMontura rem "
                +" WHERE rem.montura_id="+idMontura
                +" AND rem.registroEntradas_id=re.id "
                +" AND re.cantidad>0 "
                +" ORDER BY re.fechaDeIngreso ASC LIMIT 1");
    }
   public int crearRegistroEntradaPorDefecto(){
       String sql="insert into RegistroEntradas(nombre) values('ninguno')";
       idDefecto=operaciones.guardarYRecuperarId(sql);
       return idDefecto;
   }
   public int existeDefecto(){
       String sql="SELECT re.id FROM RegistroEntradas re where nombre='ninguno'";
       idDefecto=operaciones.consultarId(sql);
       return idDefecto;
   }
   public int getIdDefecto(){
       return idDefecto;
   }
   public int nuevoRegistroMontura(int idRegistroEntradas,int idMontura){
       String sql="insert into RegistroEntradasMontura(registroEntradas_id, montura_id) values("+idRegistroEntradas+","+idMontura+")";
       return operaciones.guardarYRecuperarId(sql);
        
   }
   public int nuevoRegistroLente(int idRegistroEntradas,int idLente){
       String sql="insert into RegistroEntradasLente(registroEntradas_id, lente_id) values("+idRegistroEntradas+","+idLente+")";
       return operaciones.guardarYRecuperarId(sql);
       
   }
   public int nuevoMonturaPedido(int idRegistroEntradasMontura,int idPedido){
       String sql="insert into monturaPedido(registroEntradasMontura_id, pedido_id) values("+idRegistroEntradasMontura+","+idPedido+")";
       return  operaciones.guardarYRecuperarId(sql);
       
   }
   
   public int nuevoLentePedido(int idRegistroEntradasLente,int idPedido){
       String sql="insert into lentePedido(registroEntradasLente_id, pedido_id) values("+idRegistroEntradasLente+","+idPedido+")";
       return operaciones.guardarYRecuperarId(sql);
       
   }
   public int getId(){
       return id;
   }
}
