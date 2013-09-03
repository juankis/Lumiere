/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Reportes.java
 *
 * Created on 28-ago-2013, 15:36:33
 */
package vista;

import conexion.Operaciones;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanki
 */
public class Reportes extends javax.swing.JFrame {
    private JLabel fondo=new JLabel(new ImageIcon("imagenes/fondo.jpg"));
    private Operaciones operaciones=new Operaciones();
    final int filas = 15;
    int alto;
    JScrollBar barra;
    public Reportes() {
       
        initComponents();
        iniciarPaginadorTablaPedidos();
        mostrarFechas(false);
        fecha1.setVisible(false);
        fecha2.setVisible(false);
        fecha3.setVisible(false);
        Date date=new Date();
        fecha1.setDate(date);
        fecha2.setDate(date);
        fecha3.setDate(date);
        this.setLocationRelativeTo(null);
        addFondo();
    }
    public void addFondo(){
        fondo.setBounds(0,0, 850, 650);
        add(fondo);
    }
    public void filtrarPedidos(){
        if(filtrarPedidos.getText().equals("Filtrar Pedidos")){
            panelFiltrar4.setVisible(true);
            filtrarPedidos.setText("Ocultar Filtros");    
        }else{
            panelFiltrar4.setVisible(false);
            filtrarPedidos.setText("Filtrar Pedidos");
        }
        llenarComboUsuarios();
    }
    public void iniciarPaginadorTablaPedidos(){
        Dimension dimension = tablaPedidos.getPreferredSize();
        jScrollPane1.setPreferredSize(new Dimension(dimension.width,
        tablaPedidos.getRowHeight()*filas));
    }
    public void llenarComboUsuarios(){
        String sql="select usu.login from usuario usu";
       // operaciones.llenarCombo(comboUsuarios,sql);
    }
    public void actualizar(){
    String join=" ";
    String reporte=""+reportesCombo.getSelectedItem();   
     if(reporte.equals("Tipos de Lentes Registrados")){
         join=" SELECT l.id, l.material, l.tipo, l.color, l.vision, l.cantidadStock"
                 + " FROM lente l";
        if(conStockCombo.getSelectedItem().equals("positivo"))
         join=join+" WHERE l.cantidadStock > 0";
        if(conStockCombo.getSelectedItem().equals("negativo"))
         join=join+" WHERE l.cantidadStock < 0";
        if(conStockCombo.getSelectedItem().equals("0"))
         join=join+" WHERE l.cantidadStock = 0";
     }
     if(reporte.equals("Tipos de Monturas Registradas")){
         join=" SELECT m.id, m.marca, m.modelo, m.tamanio, m.color, m.cantidadStock"
            +" FROM montura m"
            +" WHERE m.montura_gafa = 'MONTURA'";
        if(conStockCombo.getSelectedItem().equals("positivo"))
         join=join+" AND m.cantidadStock > 0";
        if(conStockCombo.getSelectedItem().equals("negativo"))
         join=join+" AND m.cantidadStock < 0";
        if(conStockCombo.getSelectedItem().equals("0"))
         join=join+" AND m.cantidadStock = 0";
     }
     if(reporte.equals("Tipos de Gafas Registradas")){
         join=" SELECT m.id, m.marca, m.modelo, m.tamanio, m.color, m.cantidadStock"
            +" FROM montura m"
            +" WHERE m.montura_gafa = 'GAFA DE SOL'";
         if(conStockCombo.getSelectedItem().equals("positivo"))
         join=join+" AND m.cantidadStock > 0";
        if(conStockCombo.getSelectedItem().equals("negativo"))
         join=join+" AND m.cantidadStock < 0";
        if(conStockCombo.getSelectedItem().equals("0"))
         join=join+" AND m.cantidadStock = 0";
     }
     if(reporte.equals("Monturas en Inventario")){
         join=" SELECT rem.id, re.nombre AS proveedor, re.cantidad, re.reservado, re.costoUnitario, precioDeVentaUnitario,"
            +" re.fechaDeIngreso, m.marca, m.modelo, m.tamanio, m.color"
            +" FROM montura m, registroEntradas re, registroEntradasMontura rem"
            +" WHERE rem.registroEntradas_id = re.id"
            +" AND rem.montura_id = m.id"
            +" AND re.nombre <> 'ninguno'"
            + " AND m.montura_gafa = 'MONTURA'";
        if(conStockCombo.getSelectedItem().equals("positivo"))
         join=join+" AND re.cantidad > 0";
        if(conStockCombo.getSelectedItem().equals("negativo"))
         join=join+" AND re.cantidad < 0";
        if(conStockCombo.getSelectedItem().equals("0"))
         join=join+" AND re.cantidad = 0";
        if(fechaDeRegistro.isSelected())
            join=join+" AND re.fechaDeIngreso = '"+new java.sql.Date(fecha3.getDate().getTime())+"'";
        if(rangoDeFechas.isSelected())
            join=join+" AND re.fechaDeIngreso BETWEEN '"+new java.sql.Date(fecha1.getDate().getTime())+"' AND '"+new java.sql.Date(fecha2.getDate().getTime())+"' ";
     }
     if(reporte.equals("Lentes en Inventario")){
         join=" SELECT rel.id, re.nombre AS proveedor, re.cantidad, re.reservado, re.costoUnitario, precioDeVentaUnitario,"
            +" re.fechaDeIngreso, l.material, l.tipo, l.color, l.vision"
            +" FROM lente l, registroEntradas re, registroEntradasLente rel"
            +" WHERE rel.registroEntradas_id = re.id"
            +" AND rel.lente_id = l.id"
            +" AND re.nombre <> 'ninguno'";
        if(conStockCombo.getSelectedItem().equals("positivo"))
         join=join+" AND re.cantidad > 0";
        if(conStockCombo.getSelectedItem().equals("negativo"))
         join=join+" AND re.cantidad < 0";
        if(conStockCombo.getSelectedItem().equals("0"))
         join=join+" AND re.cantidad = 0";
        if(fechaDeRegistro.isSelected())
            join=join+" AND re.fechaDeIngreso = '"+new java.sql.Date(fecha3.getDate().getTime())+"'";
        if(rangoDeFechas.isSelected())
            join=join+" AND re.fechaDeIngreso BETWEEN '"+new java.sql.Date(fecha1.getDate().getTime())+"' AND '"+new java.sql.Date(fecha2.getDate().getTime())+"' ";
     }
     if(reporte.equals("Gafas en Inventario")){
         join=" SELECT rem.id, re.nombre AS proveedor, re.cantidad, re.reservado,re.costoUnitario, precioDeVentaUnitario,"
            +" re.fechaDeIngreso, m.marca, m.modelo, m.tamanio, m.color"
            +" FROM montura m, registroEntradas re, registroEntradasMontura rem"
            +" WHERE rem.registroEntradas_id = re.id"
            +" AND rem.montura_id = m.id"
            +" AND re.nombre <> 'ninguno'"
            + " AND m.montura_gafa = 'GAFA DE SOL'";
        if(conStockCombo.getSelectedItem().equals("positivo"))
         join=join+" AND re.cantidad > 0";
        if(conStockCombo.getSelectedItem().equals("negativo"))
         join=join+" AND re.cantidad < 0";
        if(conStockCombo.getSelectedItem().equals("0"))
         join=join+" AND re.cantidad = 0";
        if(fechaDeRegistro.isSelected())
            join=join+" AND re.fechaDeIngreso = '"+new java.sql.Date(fecha3.getDate().getTime())+"'";
        if(rangoDeFechas.isSelected())
            join=join+" AND re.fechaDeIngreso BETWEEN '"+new java.sql.Date(fecha1.getDate().getTime())+"' AND '"+new java.sql.Date(fecha2.getDate().getTime())+"' ";
     }
     
     //System.out.println(join);
     operaciones.getPedidos((DefaultTableModel) tablaPedidos.getModel(),join);
    }
    public void llenarTablaPedidos(String join){
        
    String sql = "SELECT ped.id as IdPedido, per.nombre, per.apellidos, pag.saldo as Saldo, ped.fecha_ingreso, ped.fecha_entrega,usu.login"
                   + " FROM pedido ped, persona per, pago pag, usuario usu"
                   + " WHERE per.id = ped.persona_id"
                  
                   + " AND ped.usuario_id_usuario = usu.id_usuario"
                   + " AND ped.id = pag.pedido_id"+join;
    operaciones.getPedidos((DefaultTableModel) tablaPedidos.getModel(),sql);
    
    }
    public void anterior(){
        alto = tablaPedidos.getRowHeight() * (filas-1);
        barra = jScrollPane1.getVerticalScrollBar();
        barra.setValue( barra.getValue() - alto );
    }
    public void siguiente(){
        alto = tablaPedidos.getRowHeight() * (filas - 1);
        barra = jScrollPane1.getVerticalScrollBar();
        barra.setValue( barra.getValue() + alto );
    }
    public void mostrarFechas(){
        String reporte=""+reportesCombo.getSelectedItem();   
        if(reporte.equals("Gafas en Inventario") || reporte.equals("Lentes en Inventario") || reporte.equals("Monturas en Inventario") ){
            mostrarFechas(true);
        }else{
            mostrarFechas(false);
        }
    }
    public void mostrarFechas(boolean mostrar){
        
        rangoDeFechas.setVisible(mostrar);
        fechaDeRegistro.setVisible(mostrar);
    }
    public void rangoDeFechas(){
        if(rangoDeFechas.isSelected()){
            fecha2.setVisible(true);
            fecha1.setVisible(true);    
        }else{
            fecha2.setVisible(false);
            fecha1.setVisible(false);
        }
       // fechaDeRegistro.setSelected(false);
    }
    public void fechaDeRegistro(){
        if(fechaDeRegistro.isSelected())
           fecha3.setVisible(true);
       else
           fecha3.setVisible(false);
       //rangoDeFechas.setSelected(false);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFiltrar4 = new javax.swing.JPanel();
        reportesCombo = new javax.swing.JComboBox();
        conStockLabel = new javax.swing.JLabel();
        conStockCombo = new javax.swing.JComboBox();
        fecha1 = new com.toedter.calendar.JDateChooser();
        rangoDeFechas = new javax.swing.JRadioButton();
        fecha2 = new com.toedter.calendar.JDateChooser();
        fechaDeRegistro = new javax.swing.JRadioButton();
        fecha3 = new com.toedter.calendar.JDateChooser();
        filtrarPedidos = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        cerrarSecion = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        bienvenidaUsuario = new javax.swing.JLabel();
        actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFiltrar4.setBackground(new java.awt.Color(255, 255, 204));

        reportesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipos de Lentes Registrados", "Tipos de Monturas Registradas", "Tipos de Gafas Registradas", "Monturas en Inventario", "Lentes en Inventario", "Gafas en Inventario" }));
        reportesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesComboActionPerformed(evt);
            }
        });

        conStockLabel.setText("Con Stock :");

        conStockCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "positivo", "negativo", "0", "todos" }));

        rangoDeFechas.setText("Rango de Fechas");
        rangoDeFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangoDeFechasActionPerformed(evt);
            }
        });

        fechaDeRegistro.setText("Fecha de Registro");
        fechaDeRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaDeRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltrar4Layout = new javax.swing.GroupLayout(panelFiltrar4);
        panelFiltrar4.setLayout(panelFiltrar4Layout);
        panelFiltrar4Layout.setHorizontalGroup(
            panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrar4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(reportesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fechaDeRegistro))
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(conStockLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(conStockCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(rangoDeFechas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        panelFiltrar4Layout.setVerticalGroup(
            panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrar4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(fecha3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(fechaDeRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rangoDeFechas))
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addComponent(reportesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(conStockLabel)
                            .addComponent(conStockCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        filtrarPedidos.setText("Filtrar Pedidos");
        filtrarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarPedidosActionPerformed(evt);
            }
        });
        filtrarPedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filtrarPedidosKeyPressed(evt);
            }
        });

        siguiente.setText("Siguiente");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });
        siguiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                siguienteKeyPressed(evt);
            }
        });

        cerrarSecion.setText("Cerrar Secion");
        cerrarSecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSecionActionPerformed(evt);
            }
        });

        anterior.setText("Anterior");
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });
        anterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anteriorKeyPressed(evt);
            }
        });

        bienvenidaUsuario.setFont(new java.awt.Font("Verdana", 3, 24));
        bienvenidaUsuario.setText("NameUsuario");

        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        actualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                actualizarKeyPressed(evt);
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

        cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(448, 448, 448)
                        .addComponent(bienvenidaUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(anterior)
                        .addGap(10, 10, 10)
                        .addComponent(siguiente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(cancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(panelFiltrar4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(filtrarPedidos)
                                    .addGap(400, 400, 400)
                                    .addComponent(cerrarSecion)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(bienvenidaUsuario)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtrarPedidos)
                    .addComponent(cerrarSecion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFiltrar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anterior)
                    .addComponent(siguiente))
                .addGap(35, 35, 35)
                .addComponent(cancelar))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtrarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarPedidosActionPerformed
        filtrarPedidos();
}//GEN-LAST:event_filtrarPedidosActionPerformed

    private void filtrarPedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrarPedidosKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            filtrarPedidos();
}//GEN-LAST:event_filtrarPedidosKeyPressed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        siguiente();
}//GEN-LAST:event_siguienteActionPerformed

    private void siguienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_siguienteKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            siguiente();
}//GEN-LAST:event_siguienteKeyPressed

    private void cerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSecionActionPerformed
        menuPrincipal ventanaLogin=new menuPrincipal();
        ventanaLogin.setVisible(true);
        dispose();
}//GEN-LAST:event_cerrarSecionActionPerformed

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        anterior();
}//GEN-LAST:event_anteriorActionPerformed

    private void anteriorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anteriorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            anterior();
}//GEN-LAST:event_anteriorKeyPressed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        actualizar();
}//GEN-LAST:event_actualizarActionPerformed

    private void actualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actualizarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            actualizar();
}//GEN-LAST:event_actualizarKeyPressed

    private void reportesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesComboActionPerformed
        mostrarFechas();
    }//GEN-LAST:event_reportesComboActionPerformed

    private void rangoDeFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangoDeFechasActionPerformed
        rangoDeFechas();
        fechaDeRegistro.setSelected(false);
        fechaDeRegistro();
    }//GEN-LAST:event_rangoDeFechasActionPerformed

    private void fechaDeRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaDeRegistroActionPerformed
       fechaDeRegistro();
       rangoDeFechas.setSelected(false);
       rangoDeFechas();
    }//GEN-LAST:event_fechaDeRegistroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Reportes dialog = new Reportes();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton anterior;
    private javax.swing.JLabel bienvenidaUsuario;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton cerrarSecion;
    private javax.swing.JComboBox conStockCombo;
    private javax.swing.JLabel conStockLabel;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha2;
    private com.toedter.calendar.JDateChooser fecha3;
    private javax.swing.JRadioButton fechaDeRegistro;
    private javax.swing.JButton filtrarPedidos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFiltrar4;
    private javax.swing.JRadioButton rangoDeFechas;
    private javax.swing.JComboBox reportesCombo;
    private javax.swing.JButton siguiente;
    private javax.swing.JTable tablaPedidos;
    // End of variables declaration//GEN-END:variables
}
