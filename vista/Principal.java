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
    private SaldarCuenta saldarCuenta;
    public Principal(Usuario usuario) {
        initComponents();
        this.usuario=usuario;
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        bienvenidaUsuario.setText(usuario.getLogin());
        //FONDO
        fondo.setBounds(0,0, 680, 650);
        panelPrincipal.add(fondo);
        llenarTablaPedidos("");
        panelFiltrar4.setVisible(false);
    }
    public void llenarTablaPedidos(String join){
        
    String sql = "SELECT ped.id as IdPedido, per.nombre, per.apellidos, (pag.monto_total - pag.descuento - pag.a_cuenta) as Saldo, ped.fecha_ingreso, ped.fecha_entrega,usu.login"
                   + " FROM pedido ped, persona per, pago pag, usuario usu"
                   + " WHERE per.id = ped.persona_id"
                   + " AND ped.usuario_id_usuario = usu.id_usuario"
                   + " AND ped.id = pag.pedido_id"+join;
    operaciones.getPedidos((DefaultTableModel) tablaPedidos.getModel(),sql);
    
    }
    public void saldarCuenta(){
        
        int id=idSeleccionadoEnTabla();
        if(id!=0){
            saldarCuenta=new SaldarCuenta(this, rootPaneCheckingEnabled);
            Pedido pedid=new Pedido(id);
            Persona cliente=new Persona(pedid.getIdCliente());
            Pago pago=new Pago(pedid.getId());
            saldarCuenta.setPedido(pedid);
            saldarCuenta.setCliente(cliente);
            saldarCuenta.setPago(pago);
            saldarCuenta.mostrarCuenta();
            if(pago.getSaldo()==0)
                JOptionPane.showMessageDialog(this,"La Cuenta ya esta Saldada");
            else
                saldarCuenta.setVisible(true);
        }
    }
    public int idSeleccionadoEnTabla(){
        // Nuevamente obtenemos el modelo de la tabla
        int id=0;
        DefaultTableModel modelo = (DefaultTableModel) tablaPedidos.getModel();
        //ahora obtenemos la fila selccionada
        int fila_select = tablaPedidos.getSelectedRow();
        if(fila_select<0){
            // no se puede eliminar
            JOptionPane.showMessageDialog(this,"SSeleccione un Pedido de la tabla de Pedidos");
        }else {
             id=(Integer)modelo.getValueAt(fila_select, 0);   
        }
        return id;
    }
    public void llenarComboUsuarios(){
        String sql="select usu.login from usuario usu";
        operaciones.llenarCombo(comboUsuarios,sql);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        nuevoPedido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        verPedido = new javax.swing.JButton();
        bienvenidaUsuario = new javax.swing.JLabel();
        cerrarSecion = new javax.swing.JButton();
        filtrarPedidos = new javax.swing.JButton();
        panelFiltrar4 = new javax.swing.JPanel();
        pedidosConSaldo = new javax.swing.JRadioButton();
        filtrar4 = new javax.swing.JButton();
        pedidosPendientes = new javax.swing.JRadioButton();
        pedidosEntregados = new javax.swing.JRadioButton();
        pedidosCancelados = new javax.swing.JRadioButton();
        ocultar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboUsuarios = new javax.swing.JComboBox();
        saldarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nuevoPedido.setText("Nuevo Pedido");
        nuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoPedidoActionPerformed(evt);
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

        verPedido.setText("Ver Pedido");
        verPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPedidoActionPerformed(evt);
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

        filtrarPedidos.setText("Filtrar Pedidos");
        filtrarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarPedidosActionPerformed(evt);
            }
        });

        panelFiltrar4.setBackground(new java.awt.Color(255, 255, 204));

        pedidosConSaldo.setText("Pedidos con saldo");

        filtrar4.setText("Filtrar");
        filtrar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar4ActionPerformed(evt);
            }
        });

        pedidosPendientes.setText("Pedido pendientes");

        pedidosEntregados.setText("Pedidos entregados");

        pedidosCancelados.setText("Pedidos cancelados");

        ocultar.setText("ocultar");
        ocultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocultarActionPerformed(evt);
            }
        });

        jLabel1.setText("Pedidos Realizados por");

        comboUsuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "todos" }));

        javax.swing.GroupLayout panelFiltrar4Layout = new javax.swing.GroupLayout(panelFiltrar4);
        panelFiltrar4.setLayout(panelFiltrar4Layout);
        panelFiltrar4Layout.setHorizontalGroup(
            panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrar4Layout.createSequentialGroup()
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pedidosConSaldo)
                            .addComponent(pedidosCancelados)))
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(2, 2, 2)
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ocultar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pedidosEntregados)
                            .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboUsuarios, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pedidosPendientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(292, 292, 292)
                        .addComponent(filtrar4)))
                .addContainerGap())
        );
        panelFiltrar4Layout.setVerticalGroup(
            panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrar4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(pedidosConSaldo)
                        .addGap(2, 2, 2)
                        .addComponent(pedidosCancelados))
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(pedidosPendientes)
                        .addGap(2, 2, 2)
                        .addComponent(pedidosEntregados))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrar4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ocultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(filtrar4)
                .addContainerGap())
        );

        saldarPedido.setText("Saldar Pedido");
        saldarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(448, 448, 448)
                        .addComponent(bienvenidaUsuario))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelFiltrar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(nuevoPedido)
                                .addGap(6, 6, 6)
                                .addComponent(verPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saldarPedido)
                                .addGap(29, 29, 29)
                                .addComponent(filtrarPedidos)
                                .addGap(80, 80, 80)
                                .addComponent(cerrarSecion)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(bienvenidaUsuario)
                .addGap(6, 6, 6)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevoPedido)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(verPedido)
                        .addComponent(saldarPedido))
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cerrarSecion)
                        .addComponent(filtrarPedidos)))
                .addGap(18, 18, 18)
                .addComponent(panelFiltrar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoPedidoActionPerformed
        Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario,operaciones,false,this);
        //ventana_pedido.setNuevoPedido(true);
        //ventana_pedido.setLayout(null);
        ventana_pedido.setVisible(true);
        
    }//GEN-LAST:event_nuevoPedidoActionPerformed

    private void verPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verPedidoActionPerformed
        int id=idSeleccionadoEnTabla();
        if(id!=0){
            
            
            
          // la eliminamos del modelo:
           /* Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario,operaciones,true,this);
            ventana_pedido.setPedido(pedido);
            ventana_pedido.setCliente(persona);
            ventana_pedido.setPago(pago);
            ventana_pedido.setLente(lente);
            ventana_pedido.setMontura(montura);
            
            ventana_pedido.presionarBotonGuardarCliente();
            
             * 
             * */
            Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario,operaciones,true,this);
            ventana_pedido.setIdPedido(id);
            ventana_pedido.iniciar_componentesEdicion();
            ventana_pedido.setLayout(null);
            ventana_pedido.setVisible(true);
        }

    }//GEN-LAST:event_verPedidoActionPerformed

    private void cerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSecionActionPerformed
        menuPrincipal ventanaLogin=new menuPrincipal();
        ventanaLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_cerrarSecionActionPerformed

    private void filtrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar4ActionPerformed
        String join=" ";
        if(!(pedidosConSaldo.isSelected()&&pedidosCancelados.isSelected())){
            if(pedidosConSaldo.isSelected())
                join=join+" AND (pag.monto_total - pag.descuento - pag.a_cuenta) >0 ";
            if(pedidosCancelados.isSelected())
                join=join+" AND (pag.monto_total - pag.descuento - pag.a_cuenta) =0 ";
        }
        if(!(pedidosPendientes.isSelected()&&pedidosEntregados.isSelected())){    
            if(pedidosPendientes.isSelected())
                join=join+" AND ped.estado='pendiente' ";
            if(pedidosEntregados.isSelected())
                join=join+" AND ped.estado='entregado' ";
        }
        if(!comboUsuarios.getSelectedItem().equals("todos"))
            join=join+" AND usu.login='"+comboUsuarios.getSelectedItem()+"'";
        llenarTablaPedidos(join);
}//GEN-LAST:event_filtrar4ActionPerformed

    private void filtrarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarPedidosActionPerformed
        panelFiltrar4.setVisible(true);
        filtrarPedidos.setEnabled(false);
        llenarComboUsuarios();
    }//GEN-LAST:event_filtrarPedidosActionPerformed

    private void ocultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocultarActionPerformed
        panelFiltrar4.setVisible(false);
        filtrarPedidos.setEnabled(true);
    }//GEN-LAST:event_ocultarActionPerformed

    private void saldarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldarPedidoActionPerformed
        saldarCuenta();
    }//GEN-LAST:event_saldarPedidoActionPerformed

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
    private javax.swing.JLabel bienvenidaUsuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton cerrarSecion;
    private javax.swing.JComboBox comboUsuarios;
    private javax.swing.JButton filtrar4;
    private javax.swing.JButton filtrarPedidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevoPedido;
    private javax.swing.JButton ocultar;
    private javax.swing.JPanel panelFiltrar4;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JRadioButton pedidosCancelados;
    private javax.swing.JRadioButton pedidosConSaldo;
    private javax.swing.JRadioButton pedidosEntregados;
    private javax.swing.JRadioButton pedidosPendientes;
    private javax.swing.JButton saldarPedido;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JButton verPedido;
    // End of variables declaration//GEN-END:variables
}
