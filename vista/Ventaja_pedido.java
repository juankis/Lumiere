/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ventaja_pedido.java
 *
 * Created on 11-jul-2013, 7:42:25
 */
package vista;
import conexion.Operaciones;
import java.security.acl.Owner;
import objetos.*;
/**
 *
 * @author juanki
 */
public class Ventaja_pedido extends javax.swing.JDialog {

    /** Creates new form Ventaja_pedido */
        private pedido panelPedido;
        private DetallePedido detallePedido;
        private Operaciones operaciones;
        private Usuario usuario;
        private boolean editar=false;
        private Principal ventanaPrincipal;
        
        private Persona cliente;
        private Pedido pedido;
       
   /* public Ventaja_pedido(java.awt.Frame parent, boolean modal,Usuario usuario,Operaciones operaciones) {
        super(parent, modal);
        this.usuario=usuario;
        this.operaciones=operaciones;
        initComponents();
        iniciar_componentes();
        setLayout(null);
    }
         * 
         */
    public Ventaja_pedido(java.awt.Frame parent, boolean modal,Usuario usuario,Operaciones operaciones,boolean editar,Principal ventanaPrincipal) {
        super(parent, modal);
        this.setLocationRelativeTo(null);
        this.usuario=usuario;
        this.operaciones=operaciones;
        this.editar=editar;
        this.ventanaPrincipal=ventanaPrincipal;
        initComponents();
        this.setLocationRelativeTo(null);
        valoresPorDefecto();
        
        setLayout(null);
    }
    public void valoresPorDefecto(){
        if(!editar)
            iniciar_componentes();    
    }
   
    public void iniciar_componentesEdicion(){
       panelPedido=new pedido(usuario,operaciones,this,editar);
       panelPedido.setCliente(cliente);
       panelPedido.valoresEdicion();
       panelPedido.setIdPedido(pedido.getId());
       panelPedido.setBounds(0, 0,900,850);
       detallePedido=new DetallePedido(usuario,panelPedido,editar,this);
       detallePedido.setPedido(pedido);
       detallePedido.valoresEdicion();
       detallePedido.setBounds(0, 260, 900, 500);
       panelPedido.setDetallePedido(detallePedido);
      
       add(panelPedido);
    }
    public void iniciar_componentes(){
        panelPedido=new pedido(usuario,operaciones,this,editar);
        panelPedido.setBounds(0, 0,900,850);
        add(panelPedido);
        
    }
    public void setIdPedido(int idPedido){
      pedido=new Pedido(idPedido);
      cliente=new Persona(pedido.getIdCliente());
       
    }
    public void actualizarTablaPrincipal(){
        
        ventanaPrincipal.llenarTablaPedidos("");
    }
    public void presionarBotonGuardarCliente(){
        panelPedido.presionarBotonGuardarCliente();
        //ventanaPrincipal.llenarTablaPedidos("");
    }
    public void setPedido(Pedido pedido){
        detallePedido.setPedido(pedido);
    }
    public void setCliente(Persona persona){
        panelPedido.setCliente(persona);
    }/*
    public void setPago(Pago pago){
        detallePedido.setPago(pago);
    }
    public void setLente(Lente lente){
        detallePedido.setLente(lente);
    }
    public void setMontura(Montura montura){
        detallePedido.setMontura(montura);
    }*/
    public void valoresEdicion(){
        detallePedido.valoresEdicion();
    }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 874, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       /* public void setNuevoPedido(boolean nuevoPedido){
        
        panelPedido.setNuevoPedido(nuevoPedido);
        //detallePedido.setNuevoPedido(nuevoPedido);
    }*/
    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Ventaja_pedido dialog = new Ventaja_pedido(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
