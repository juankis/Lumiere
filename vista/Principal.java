/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 10-jul-2013, 21:04:30
 */
package vista;

import conexion.Operaciones;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import objetos.AcomodadorPedidosI;
import objetos.Lente;
import objetos.Montura;
import objetos.Pago;
import objetos.Pedido;
import objetos.Persona;
import objetos.Usuario;
import recusos.JPanelTransparente;

/**
 *
 * @author juanki
 */
public class Principal extends javax.swing.JFrame {

    /** Creates new form Principal */
    private JLabel fondo=new JLabel(new ImageIcon("imagenes/fondo.jpg"));
    private AcomodadorPedidosI acomodador=new AcomodadorPedidosI();
    private Operaciones operaciones=new Operaciones();
    private Usuario usuario;
    public Principal(Usuario usuario) {
        initComponents();
        this.usuario=usuario;
        
       
        iniciarComponentes();
    }
    private void iniciarComponentes(){
      bienvenidaUsuario.setText(usuario.getLogin());
        //FONDO
        fondo.setBounds(0,0, 615, 570);
        panelPrincipal.add(fondo);
        operaciones.getPedidos((DefaultTableModel) tablaPedidos.getModel());
    }       

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        nuevoPedido = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        editarPedido = new javax.swing.JButton();
        bienvenidaUsuario = new javax.swing.JLabel();
        cerrarSecion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nuevoPedido.setText("Nuevo Pedido");
        nuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoPedidoActionPerformed(evt);
            }
        });

        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);

        editarPedido.setText("Editar Pedido");
        editarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarPedidoActionPerformed(evt);
            }
        });

        bienvenidaUsuario.setFont(new java.awt.Font("Verdana", 3, 24));
        bienvenidaUsuario.setText("NameUsuario");

        cerrarSecion.setText("Cerrar Secion");
        cerrarSecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSecionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(nuevoPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actualizar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                            .addComponent(editarPedido))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cerrarSecion)
                            .addComponent(bienvenidaUsuario))
                        .addGap(26, 26, 26))))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bienvenidaUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cerrarSecion)
                .addGap(39, 39, 39)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoPedido)
                    .addComponent(actualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editarPedido)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoPedidoActionPerformed
        Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario);
        ventana_pedido.setNuevoPedido(true);
        ventana_pedido.setLayout(null);
        ventana_pedido.setVisible(true);
        
    }//GEN-LAST:event_nuevoPedidoActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
      operaciones.getPedidos((DefaultTableModel) tablaPedidos.getModel());
    }//GEN-LAST:event_actualizarActionPerformed

    private void editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarPedidoActionPerformed
         // Nuevamente obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tablaPedidos.getModel();
        //ahora obtenemos la fila selccionada
        int fila_select = tablaPedidos.getSelectedRow();
        
       
        if(fila_select<0){
            // no se puede eliminar
            JOptionPane.showMessageDialog(this,"Seleccione un Pedido para Editar.");
        }else {
            int id=(Integer)modelo.getValueAt(fila_select, 0);
            Pedido pedido=new Pedido(id);
            Persona persona=new Persona(pedido.getIdCliente());
            Pago pago=new Pago(pedido.getId());
            Lente lente=new Lente(pedido.getId());
            Montura montura=new Montura(pedido.getId());
          // la eliminamos del modelo:
            Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario);
            ventana_pedido.setPedido(pedido);
            ventana_pedido.setCliente(persona);
            ventana_pedido.setPago(pago);
            ventana_pedido.setLente(lente);
            ventana_pedido.setMontura(montura);
            ventana_pedido.setLayout(null);
            ventana_pedido.setVisible(true);
        }

    }//GEN-LAST:event_editarPedidoActionPerformed

    private void cerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSecionActionPerformed
        menuPrincipal ventanaLogin=new menuPrincipal();
        ventanaLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_cerrarSecionActionPerformed

    /**
     * @param args the command line arguments
     */
    
    //public static void main(String args[]) {
          /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
    /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
      /*  java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Principal ventana=new Principal();
                ventana.setLocation(300,300);
                ventana.setVisible(true);
            }
        });
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JLabel bienvenidaUsuario;
    private javax.swing.JButton cerrarSecion;
    private javax.swing.JButton editarPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevoPedido;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
