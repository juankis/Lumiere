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
import java.awt.Dimension;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

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
    
    private Operaciones operaciones=new Operaciones();
    private Usuario usuario;
    private SaldarCuenta saldarCuenta;
    final int filas = 15;
    int alto;
    JScrollBar barra;
    public Principal(Usuario usuario) {
        initComponents();
        this.usuario=usuario;
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        bienvenidaUsuario.setText(usuario.getLogin());
        fechaEmisionPedido.setDate(new Date());
        //FONDO
        fondo.setBounds(0,0, 850, 650);
        this.setLocationRelativeTo(null);
        panelPrincipal.add(fondo);
        llenarTablaPedidos("");
        panelFiltrar4.setVisible(false);
        //iniciar paginador tabla pedidos
        iniciarPaginadorTablaPedidos();
        verificarAdministrador();
    }
    public void verificarAdministrador(){
        if(!usuario.esAdmin()){
            cancelarPedido.setVisible(false);
            reportes.setVisible(false);
            usuarios.setVisible(false);
        }
    }
    public void iniciarPaginadorTablaPedidos(){
        Dimension dimension = tablaPedidos.getPreferredSize();
        jScrollPane1.setPreferredSize(new Dimension(dimension.width,
        tablaPedidos.getRowHeight()*filas));
    }
    public void llenarTablaPedidos(String join){
        
    String sql = "SELECT ped.id as IdPedido, per.nombre, per.apellidos, pag.saldo as Saldo, ped.fecha_ingreso, ped.fecha_entrega,usu.login"
                   + " FROM pedido ped, persona per, pago pag, usuario usu"
                   + " WHERE per.id = ped.persona_id"
                  
                   + " AND ped.usuario_id_usuario = usu.id_usuario"
                   + " AND ped.id = pag.pedido_id"+join;
    operaciones.getPedidos((DefaultTableModel) tablaPedidos.getModel(),sql);
    
    }
    public void saldarCuenta(){
        
        int id=idSeleccionadoEnTabla();
        if(id!=0){
            saldarCuenta=new SaldarCuenta(this, rootPaneCheckingEnabled,usuario);
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
    public void actualizar(){
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
       
       if(fechaBusquedaCombo.getSelectedItem().equals("Fecha Ingreso"))
            join=join+" AND ped.fecha_ingreso = '"+(new java.sql.Date(fechaEmisionPedido.getDate().getTime()))+"'";
       if(fechaBusquedaCombo.getSelectedItem().equals("Fecha Entrega"))
            join=join+" AND ped.fecha_entrega = '"+(new java.sql.Date(fechaEmisionPedido.getDate().getTime()))+"'";
        llenarTablaPedidos(join);
    }
    public void verPedido(){
        int id=idSeleccionadoEnTabla();
        
        if(id!=0){
         Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario,operaciones,true,this);
            ventana_pedido.setIdPedido(id);
            ventana_pedido.iniciar_componentesEdicion();
            ventana_pedido.setLayout(null);
            ventana_pedido.setVisible(true);
        }
    }
    public void verHistorial(){
        int id=idSeleccionadoEnTabla();
        
        if(id!=0){
            HistorialPagosPedido hpp=new HistorialPagosPedido(this, rootPaneCheckingEnabled, id, operaciones); 
            hpp.setVisible(true);
        }
    }
    public void eliminarPedido(){
        int id=idSeleccionadoEnTabla();
        
        if(id!=0){
            int ax = JOptionPane.showConfirmDialog(null, "Esta seguro de cancelar el Pedido con ID "+id);
            if(ax == JOptionPane.YES_OPTION){
                Pedido pedido=new Pedido(id);
                if(pedido.getEstado().equals("entregado"))
                    JOptionPane.showMessageDialog(null, "El pedido con ID "+id+" esta registrado como ya entregado, no es posible cancelarlo.");
               
                    
                
            }//else if(ax == JOptionPane.NO_OPTION||ax == JOptionPane.CANCEL_OPTION||ax == JOptionPane.CLOSED_OPTION)
             
        }
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
    public void mostrarReportes(){
        Reportes ventanaReportes = new  Reportes();
        ventanaReportes.setVisible(true);
    }
    public void mostrarVentanaUsuarios(){
        AdministracionDeUsuarios administracionDeUsuarios=new AdministracionDeUsuarios();
        administracionDeUsuarios.setVisible(true);
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
        pedidosPendientes = new javax.swing.JRadioButton();
        pedidosEntregados = new javax.swing.JRadioButton();
        pedidosCancelados = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        comboUsuarios = new javax.swing.JComboBox();
        fechaEmisionPedido = new com.toedter.calendar.JDateChooser();
        fechaBusquedaCombo = new javax.swing.JComboBox();
        saldarPedido = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        verHistorialPagos = new javax.swing.JButton();
        cancelarPedido = new javax.swing.JButton();
        reportes = new javax.swing.JButton();
        usuarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nuevoPedido.setText("Nuevo Pedido");
        nuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoPedidoActionPerformed(evt);
            }
        });
        nuevoPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPedidoKeyPressed(evt);
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
        verPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                verPedidoKeyPressed(evt);
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
        cerrarSecion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cerrarSecionKeyPressed(evt);
            }
        });

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

        panelFiltrar4.setBackground(new java.awt.Color(255, 255, 204));

        pedidosConSaldo.setText("Pedidos con saldo");

        pedidosPendientes.setText("Pedido pendientes");

        pedidosEntregados.setText("Pedidos entregados");

        pedidosCancelados.setText("Pedidos cancelados");

        jLabel1.setText("Pedidos Realizados por");

        comboUsuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "todos" }));

        fechaBusquedaCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguna Fecha", "Fecha Ingreso", "Fecha Entrega" }));
        fechaBusquedaCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaBusquedaComboActionPerformed(evt);
            }
        });

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
                    .addComponent(pedidosEntregados)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
                        .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboUsuarios, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pedidosPendientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addComponent(fechaBusquedaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaEmisionPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        panelFiltrar4Layout.setVerticalGroup(
            panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrar4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelFiltrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaEmisionPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaBusquedaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFiltrar4Layout.createSequentialGroup()
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
                                .addComponent(pedidosEntregados)))))
                .addContainerGap())
        );

        saldarPedido.setText("Saldar Pedido");
        saldarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldarPedidoActionPerformed(evt);
            }
        });
        saldarPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saldarPedidoKeyPressed(evt);
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

        verHistorialPagos.setText("Ver Historial Pagos");
        verHistorialPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verHistorialPagosActionPerformed(evt);
            }
        });
        verHistorialPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                verHistorialPagosKeyPressed(evt);
            }
        });

        cancelarPedido.setText("Cancelar Pedido");
        cancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarPedidoActionPerformed(evt);
            }
        });
        cancelarPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cancelarPedidoKeyPressed(evt);
            }
        });

        reportes.setText("Reportes");
        reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesActionPerformed(evt);
            }
        });
        reportes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reportesKeyPressed(evt);
            }
        });

        usuarios.setText("Usuarios");
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });
        usuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuariosKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(panelFiltrar4, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(verPedido)
                        .addGap(6, 6, 6)
                        .addComponent(saldarPedido)
                        .addGap(6, 6, 6)
                        .addComponent(verHistorialPagos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarPedido))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(anterior)
                        .addGap(10, 10, 10)
                        .addComponent(siguiente))
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(nuevoPedido)
                            .addGap(10, 10, 10)
                            .addComponent(filtrarPedidos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(reportes)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(usuarios)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cerrarSecion))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrincipalLayout.createSequentialGroup()
                            .addGap(448, 448, 448)
                            .addComponent(bienvenidaUsuario))))
                .addContainerGap(24, Short.MAX_VALUE))
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
                        .addComponent(filtrarPedidos)
                        .addComponent(reportes)
                        .addComponent(cerrarSecion)
                        .addComponent(usuarios)))
                .addGap(18, 18, 18)
                .addComponent(panelFiltrar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actualizar)
                    .addComponent(verPedido)
                    .addComponent(saldarPedido)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(verHistorialPagos)
                        .addComponent(cancelarPedido)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anterior)
                    .addComponent(siguiente)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
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
        verPedido();
    }//GEN-LAST:event_verPedidoActionPerformed

    private void cerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSecionActionPerformed
        menuPrincipal ventanaLogin=new menuPrincipal();
        ventanaLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_cerrarSecionActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        actualizar();
}//GEN-LAST:event_actualizarActionPerformed

    private void filtrarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarPedidosActionPerformed
        filtrarPedidos();
    }//GEN-LAST:event_filtrarPedidosActionPerformed

    private void saldarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldarPedidoActionPerformed
        saldarCuenta();
    }//GEN-LAST:event_saldarPedidoActionPerformed

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
       anterior();
    }//GEN-LAST:event_anteriorActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        siguiente();
    }//GEN-LAST:event_siguienteActionPerformed

    private void fechaBusquedaComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaBusquedaComboActionPerformed
        if(("Ninguna Fecha").equals(""+fechaBusquedaCombo.getSelectedItem())){
            fechaEmisionPedido.setEnabled(false);
            
        }else
            fechaEmisionPedido.setEnabled(true);
    }//GEN-LAST:event_fechaBusquedaComboActionPerformed

    private void verHistorialPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verHistorialPagosActionPerformed
        verHistorial();
        
    }//GEN-LAST:event_verHistorialPagosActionPerformed

    private void nuevoPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPedidoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) { 
        Ventaja_pedido ventana_pedido=new Ventaja_pedido(this, rootPaneCheckingEnabled,usuario,operaciones,false,this);
        ventana_pedido.setVisible(true);
        }
    }//GEN-LAST:event_nuevoPedidoKeyPressed

    private void filtrarPedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrarPedidosKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            filtrarPedidos();
    }//GEN-LAST:event_filtrarPedidosKeyPressed

    private void actualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actualizarKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER)
        actualizar();
    }//GEN-LAST:event_actualizarKeyPressed

    private void verPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verPedidoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
        verPedido();
    }//GEN-LAST:event_verPedidoKeyPressed

    private void saldarPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saldarPedidoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
        saldarCuenta();
    }//GEN-LAST:event_saldarPedidoKeyPressed

    private void verHistorialPagosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verHistorialPagosKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
        verHistorial();
    }//GEN-LAST:event_verHistorialPagosKeyPressed

    private void anteriorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anteriorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            anterior();
    }//GEN-LAST:event_anteriorKeyPressed

    private void siguienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_siguienteKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            siguiente();
    }//GEN-LAST:event_siguienteKeyPressed

    private void cancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarPedidoActionPerformed
        eliminarPedido();
    }//GEN-LAST:event_cancelarPedidoActionPerformed

    private void cancelarPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelarPedidoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            eliminarPedido();
    }//GEN-LAST:event_cancelarPedidoKeyPressed

    private void reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesActionPerformed
        mostrarReportes();
    }//GEN-LAST:event_reportesActionPerformed

    private void reportesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reportesKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            mostrarReportes();
    }//GEN-LAST:event_reportesKeyPressed

    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
        mostrarVentanaUsuarios();
    }//GEN-LAST:event_usuariosActionPerformed

    private void usuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuariosKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
        mostrarVentanaUsuarios();
    }//GEN-LAST:event_usuariosKeyPressed

    private void cerrarSecionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cerrarSecionKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
        menuPrincipal ventanaLogin=new menuPrincipal();
        ventanaLogin.setVisible(true);
        dispose();
        }
    }//GEN-LAST:event_cerrarSecionKeyPressed

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
    private javax.swing.JButton anterior;
    private javax.swing.JLabel bienvenidaUsuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton cancelarPedido;
    private javax.swing.JButton cerrarSecion;
    private javax.swing.JComboBox comboUsuarios;
    private javax.swing.JComboBox fechaBusquedaCombo;
    private com.toedter.calendar.JDateChooser fechaEmisionPedido;
    private javax.swing.JButton filtrarPedidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevoPedido;
    private javax.swing.JPanel panelFiltrar4;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JRadioButton pedidosCancelados;
    private javax.swing.JRadioButton pedidosConSaldo;
    private javax.swing.JRadioButton pedidosEntregados;
    private javax.swing.JRadioButton pedidosPendientes;
    private javax.swing.JButton reportes;
    private javax.swing.JButton saldarPedido;
    private javax.swing.JButton siguiente;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JButton usuarios;
    private javax.swing.JButton verHistorialPagos;
    private javax.swing.JButton verPedido;
    // End of variables declaration//GEN-END:variables
}
