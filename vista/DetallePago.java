/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetallePago.java
 *
 * Created on 23-ago-2013, 12:15:35
 */
package vista;

import java.util.Date;
import javax.swing.JOptionPane;
import objetos.ACuenta;
import objetos.Gafa;
import objetos.Lente;
import objetos.Montura;
import objetos.Pago;
import objetos.Pedido;
import objetos.Persona;
import objetos.RegistroEntradas;
import objetos.Usuario;

/**
 *
 * @author juanki
 */
public class DetallePago extends javax.swing.JDialog {

   private double costoLente=0;
    private double costoMontura=0;
    private double costoGafa=0;
    private double costoConsulta=0;
    private double costoDescuento=0;
    private double costoACuenta=0;
    private double costoTotal=0;
    private double costoSaldo=0;
    private double totalAPagar=0;
    
    private Pedido pedido;
    private Usuario usuario;
    private Lente lente;
    private Montura montura;
    private Gafa gafa;
    private Pago pago;
    private ACuenta aCuenta;
    private Persona cliente;
    private RegistroEntradas registroEntradas=new RegistroEntradas();
    
    private DetallePedido detallePedido;
    public DetallePago(javax.swing.JDialog parent, boolean modal,DetallePedido detallePedido) {
        super(parent, modal);
        this.setLocationRelativeTo(null);
        this.detallePedido=detallePedido;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLente = new javax.swing.JLabel();
        labelArmazon = new javax.swing.JLabel();
        labelVarios = new javax.swing.JLabel();
        labelCostoTotal = new javax.swing.JLabel();
        labelDescuento = new javax.swing.JLabel();
        lente_pago = new javax.swing.JTextField();
        armazon = new javax.swing.JTextField();
        consulta = new javax.swing.JTextField();
        total_2 = new javax.swing.JTextField();
        descuento = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();
        total_a_pagar = new javax.swing.JTextField();
        labelACuenta = new javax.swing.JLabel();
        a_cuenta = new javax.swing.JTextField();
        labelSaldo = new javax.swing.JLabel();
        saldo = new javax.swing.JTextField();
        realizarPedido = new javax.swing.JButton();
        atras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        gafaDeSol = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelLente.setText("Lente");

        labelArmazon.setText("Armazon");

        labelVarios.setText("Varios");

        labelCostoTotal.setText("Costo Total");

        labelDescuento.setText("Descuento");

        lente_pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lente_pagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lente_pagoKeyTyped(evt);
            }
        });

        armazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                armazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                armazonKeyTyped(evt);
            }
        });

        consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                consultaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                consultaKeyTyped(evt);
            }
        });

        total_2.setEditable(false);
        total_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_2KeyTyped(evt);
            }
        });

        descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoKeyTyped(evt);
            }
        });

        labelTotal.setText("Total");

        total_a_pagar.setEditable(false);
        total_a_pagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_a_pagarKeyTyped(evt);
            }
        });

        labelACuenta.setText("Acuenta");

        a_cuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                a_cuentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                a_cuentaKeyTyped(evt);
            }
        });

        labelSaldo.setText("Saldo");

        saldo.setEditable(false);
        saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                saldoKeyTyped(evt);
            }
        });

        realizarPedido.setText("Realizar Pedido");
        realizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarPedidoActionPerformed(evt);
            }
        });
        realizarPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                realizarPedidoKeyPressed(evt);
            }
        });

        atras.setText("Atras");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });
        atras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                atrasKeyPressed(evt);
            }
        });

        jLabel1.setText("Gafa de Sol");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(realizarPedido)
                .addGap(18, 18, 18)
                .addComponent(atras)
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(gafaDeSol))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelLente)
                                .addGap(43, 43, 43)
                                .addComponent(lente_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelArmazon)
                                .addGap(28, 28, 28)
                                .addComponent(armazon, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelVarios)
                                .addGap(41, 41, 41)
                                .addComponent(consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelCostoTotal)
                            .addGap(15, 15, 15)
                            .addComponent(total_2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelDescuento)
                                .addGap(19, 19, 19)
                                .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelACuenta)
                                    .addGap(30, 30, 30)
                                    .addComponent(a_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelTotal)
                                    .addGap(46, 46, 46)
                                    .addComponent(total_a_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelSaldo)
                                    .addGap(44, 44, 44)
                                    .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelArmazon)
                            .addComponent(armazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLente)
                            .addComponent(lente_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(gafaDeSol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCostoTotal)
                    .addComponent(total_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescuento)
                    .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotal)
                    .addComponent(total_a_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelACuenta)
                    .addComponent(a_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSaldo)
                    .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realizarPedido)
                    .addComponent(atras))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  private void calcularTotalAPagar(){
        if(!lente_pago.getText().equals(""))
             costoLente=Double.parseDouble(lente_pago.getText());
        if(!armazon.getText().equals(""))
             costoMontura=Double.parseDouble(armazon.getText());
        if(!consulta.getText().equals(""))
            costoConsulta=Double.parseDouble(consulta.getText());
        if(!descuento.getText().equals(""))
            costoDescuento=Double.parseDouble(descuento.getText());
        if(!a_cuenta.getText().equals(""))
            costoACuenta= Double.parseDouble(a_cuenta.getText());
        if(!gafaDeSol.getText().equals(""))
            costoGafa= Double.parseDouble(gafaDeSol.getText());
        costoTotal=costoLente+costoMontura+costoConsulta+costoGafa;
        total_2.setText(Double.toString(costoTotal));
        totalAPagar=costoTotal-costoDescuento;
        total_a_pagar.setText(Double.toString(totalAPagar));
        costoSaldo=totalAPagar-costoACuenta;
        saldo.setText(Double.toString(costoSaldo));
    }
   public boolean validarPago(){
        boolean validacion=false;
        calcularTotalAPagar();
        if(!total_2.getText().equals("0"))
            validacion=true;
        else
            JOptionPane.showMessageDialog(null, "el total no es correcto");
        return validacion;
    }
   public void setPedido(Pedido pedido){
       this.pedido=pedido;
   }
   public void setUsuario(Usuario usuario){
       this.usuario=usuario;
   }
   public void setMontura(Montura montura){
       this.montura=montura;
   }
   public void setGafa(Gafa gafa){
       this.gafa=gafa;
   }
   public void setLente(Lente lente){
       this.lente=lente;
   }
   public void setCliente(Persona cliente){
       this.cliente=cliente;
   }
   public void llenarDatos(){
       if(montura.buscarIdMontura()!=0)
           armazon.setText(""+montura.getPrecio());
       if(lente.buscarId()!=0)
          lente_pago.setText(""+lente.getPrecio());
       if(gafa.buscarIdGafa()!= 0)
           gafaDeSol.setText(""+gafa.getPrecio());
       calcularTotalAPagar();
       
   }
   public void guardarPago(){
       if(validarPago()){
           //montura=detallePedido.getMontura();
           
           if(registroEntradas.existeDefecto()==0)
                   registroEntradas.crearRegistroEntradaPorDefecto();
           pedido.guardarEnBD();
           int idRegMon;
           if(!montura.losDatosSonVacios()){
               if(montura.getId()!=0){
                   if(pedido.getEstado().equals("pendiente")){
                       montura.recuperarDatosMontura(montura.getId());
                        if(montura.getCantidadStock()<=0){
                            if(montura.getCantidadStock()==0)
                                registroEntradas.nuevoRegistroMontura(registroEntradas.getIdDefecto(), montura.getId());
                        }else
                            montura.descontarEnProveedor();
                       montura.descontarUno();
                   }else{
                       montura.reservar();
                   }
                   if(0==registroEntradas.getIdRegistroEntradaMontura(montura.getId()))
                    {
                        idRegMon=registroEntradas.getIdRegistroEntradaMonturaDefecto(montura.getId());
                    }else{
                        idRegMon=registroEntradas.getIdRegistroEntradaMontura(montura.getId());
                    }
               }else{ 
                     montura.guardarEnBD();
                     idRegMon=registroEntradas.nuevoRegistroMontura(registroEntradas.getIdDefecto(), montura.getId());//registroEntradas.nuevoRegistroMontura(registroEntradas.getId(),montura.getId());
               }
               registroEntradas.nuevoMonturaPedido(idRegMon, pedido.getId());
               
           }
           int idRegGaf;
           if(!gafa.losDatosSonVacios()){
               if(gafa.getId()!=0){
                   if(pedido.getEstado().equals("pendiente")){
                       gafa.recuperarDatosGafa(gafa.getId());
                        if(gafa.getCantidadStock()<=0){
                            if(gafa.getCantidadStock()==0)
                                registroEntradas.nuevoRegistroMontura(registroEntradas.getIdDefecto(), gafa.getId());
                        }else
                            gafa.descontarEnProveedor();
                        gafa.descontarUno();
                   }else{
                       gafa.reservar();
                   }
                   if(0==registroEntradas.getIdRegistroEntradaMontura(gafa.getId()))
                    {
                        idRegGaf=registroEntradas.getIdRegistroEntradaMonturaDefecto(gafa.getId());
                    }else{
                        idRegGaf=registroEntradas.getIdRegistroEntradaMontura(gafa.getId());
                    }
               }else{ 
                     gafa.guardarEnBD();
                     idRegGaf=registroEntradas.nuevoRegistroMontura(registroEntradas.getIdDefecto(), gafa.getId());
               }
               //int idRegMon=registroEntradas.getIdRegistroEntradaMontura(gafa.getId());//registroEntradas.nuevoRegistroMontura(registroEntradas.getId(),montura.getId());
               registroEntradas.nuevoMonturaPedido(idRegGaf, pedido.getId());
           }
           int idRegLen;
           if(!lente.losDatosSonVacios()){
               
               if(lente.getId()!=0){
                    if(pedido.getEstado().equals("pendiente")){
                        
                        lente.recuperarDatosLente(lente.getId());
                        if(lente.getCantidadStock()<=0){
                            if(lente.getCantidadStock()==0)
                                registroEntradas.nuevoRegistroLente(registroEntradas.getIdDefecto(), lente.getId());
                        }else
                            lente.descontarEnProveedor();
                        lente.descontarUno();
                    }else{
                        lente.reservar();
                    }
                         
                    if(0==registroEntradas.getIdRegistroEntradaLente(lente.getId()))
                    {
                        idRegLen=registroEntradas.getIdRegistroEntradaLenteDefecto(lente.getId());
                    }else{
                        idRegLen=registroEntradas.getIdRegistroEntradaLente(lente.getId());
                    }
                       
               }else{
                    //si no existe
                    lente.guardarEnBD();
                    idRegLen=registroEntradas.nuevoRegistroLente(registroEntradas.getIdDefecto(), lente.getId());
               }
               //registroEntradas.nuevoRegistroMontura(registroEntradas.getId(),montura.getId());
               registroEntradas.nuevoLentePedido(idRegLen, pedido.getId());
            }
           
           
           crearObjetoPago();
           pago.guardar_en_BD();
           crearObjetoAcuenta();
           aCuenta.guardarEnBD();
           detallePedido.cerrarVentana();
         }
   }
    
   private void crearObjetoPago(){
       pago= new Pago(costoSaldo,costoDescuento,pedido.getId(),costoLente,costoMontura,costoConsulta,costoGafa);
    }
    private void crearObjetoAcuenta(){
        aCuenta= new ACuenta(costoACuenta,new Date(),pago.getId(),usuario.get_id());
    }
    private void lente_pagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lente_pagoKeyReleased
        calcularTotalAPagar();
}//GEN-LAST:event_lente_pagoKeyReleased

    private void lente_pagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lente_pagoKeyTyped
        calcularTotalAPagar();
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_lente_pagoKeyTyped

    private void armazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_armazonKeyReleased
        calcularTotalAPagar();
}//GEN-LAST:event_armazonKeyReleased

    private void armazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_armazonKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_armazonKeyTyped

    private void consultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consultaKeyReleased
        calcularTotalAPagar();
}//GEN-LAST:event_consultaKeyReleased

    private void consultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consultaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_consultaKeyTyped

    private void total_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_2KeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_total_2KeyTyped

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
        calcularTotalAPagar();
}//GEN-LAST:event_descuentoKeyReleased

    private void descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_descuentoKeyTyped

    private void total_a_pagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_a_pagarKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_total_a_pagarKeyTyped

    private void a_cuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a_cuentaKeyReleased
        calcularTotalAPagar();
}//GEN-LAST:event_a_cuentaKeyReleased

    private void a_cuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a_cuentaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_a_cuentaKeyTyped

    private void saldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saldoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_saldoKeyTyped

    private void realizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarPedidoActionPerformed
        guardarPago();
        dispose();
    }//GEN-LAST:event_realizarPedidoActionPerformed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        dispose();
    }//GEN-LAST:event_atrasActionPerformed

    private void realizarPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_realizarPedidoKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER){
           guardarPago();
            dispose();
       }  
    }//GEN-LAST:event_realizarPedidoKeyPressed

    private void atrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_atrasKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            dispose();
    }//GEN-LAST:event_atrasKeyPressed

    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a_cuenta;
    private javax.swing.JTextField armazon;
    private javax.swing.JButton atras;
    private javax.swing.JTextField consulta;
    private javax.swing.JTextField descuento;
    private javax.swing.JTextField gafaDeSol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelACuenta;
    private javax.swing.JLabel labelArmazon;
    private javax.swing.JLabel labelCostoTotal;
    private javax.swing.JLabel labelDescuento;
    private javax.swing.JLabel labelLente;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelVarios;
    private javax.swing.JTextField lente_pago;
    private javax.swing.JButton realizarPedido;
    private javax.swing.JTextField saldo;
    private javax.swing.JTextField total_2;
    private javax.swing.JTextField total_a_pagar;
    // End of variables declaration//GEN-END:variables
}
