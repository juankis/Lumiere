/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * pedido.java
 *
 * Created on 10-jul-2013, 8:06:50
 */
package vista;

import conexion.Operaciones;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import objetos.Lente;
import objetos.Montura;
import objetos.Pago;
import objetos.Pedido;
import objetos.Persona;
import objetos.Usuario;
/**
 *
 * @author juanki
 */
public class pedido extends javax.swing.JPanel {

    /** Creates new form pedido */
  
    private JLabel fondo=new JLabel(new ImageIcon("imagenes/fondo.jpg"));
  
    private Persona persona=new Persona();
    private Usuario usuario;
    private Boolean nuevoPedido=false;
    private DetallePedido detallePedido;
    private Operaciones operaciones;
    private Ventaja_pedido ventana;
    private boolean edicion;
    public pedido(Usuario usuario,Operaciones operaciones,Ventaja_pedido ventana,boolean edicion){ 
        initComponents();
        this.usuario=usuario;
        this.operaciones=operaciones;
        this.ventana=ventana;
        this.edicion=edicion;
        valoresPorDefecto();
    }
    public void valoresPorDefecto(){
        if(!edicion){
        
        //autocompletes
        iniciarAutoCompletes(); 
        setnombresComponentes();
        //detallePedido
        //nuevoDetallePedido();
        mostrarDatosLabelsCliente(false);
        guardarCambios.setEnabled(false);
        editarCliente.setEnabled(false);
        addPedido.setEnabled(false);
        labelIdPedido.setText("Nuevo Pedido");
        }
       addFondo();
       
    }
    public void valoresEdicion(){
        addPedido.setVisible(false);  
        guardarCliente.setVisible(false);
        guardarCambios.setEnabled(false);
        guardarCambios.setLocation(70,guardarCambios.getLocation().y );
        mostrarDatosCliente(false);
        mostrarDatosLabelsCliente(true);
    }
    public void addFondo(){
        add(fondo,new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0,1000, -1));
    }
    public void setLabelIdPedido(){
        labelIdPedido.setText(""+detallePedido.getIdPedido());
    }
    public void nuevoDetallePedido(){
        edicion=false;
        if(detallePedido!=null)
            this.remove(detallePedido);
        detallePedido=new DetallePedido(usuario,this,false);
        
        this.add(detallePedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 900, 500));
        addFondo();
        addPedido.setEnabled(false);
    }
    public void setDetallePedido(DetallePedido detallePedido){
        this.detallePedido=detallePedido;
        this.add(detallePedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 900, 500));
        addFondo();
    }
    public void setnombresComponentes(){
        //persona
        nombre_cliente.setName("nombre_cliente");
        apellido_cliente.setName("apellido_cliente");
    }
    public void iniciarAutoCompletes(){
        nombre_cliente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,nombre_cliente);
            }
        });
        apellido_cliente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,apellido_cliente);
            }
        });
        
    }
    public void autocompletar(KeyEvent evt,JComboBox combo)
    {
        if(!edicion){
         String cadenaEscrita = combo.getEditor().getItem().toString();
             if(cadenaEscrita.length()>5){   
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        buscar(cadenaEscrita);
                }
                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    listarNombres(combo,cadenaEscrita);
                    
                    if (combo.getItemCount() > 0) {

                        combo.showPopup();
                        if (evt.getKeyCode() != 8) {
                            ((JTextComponent) combo.getEditor().getEditorComponent()).select(cadenaEscrita.length(), combo.getEditor().getItem().toString().length());

                        } else {
                            combo.getEditor().setItem(cadenaEscrita);
                        }

                    } else {

                        combo.addItem(cadenaEscrita);
                    }
                }
        }
        }
    }
    public void listarNombres(JComboBox combo,String cadenaEscrita){
        //System.out.println(combo.getName());
        if(combo.getName().equals("apellido_cliente"))
             combo.setModel(persona.listaApellidos(cadenaEscrita));
        if(combo.getName().equals("nombre_cliente"))
             combo.setModel(persona.listaNombres(cadenaEscrita));
        
        
    }
            
    public void buscar(String nombre) {
        int indice=nombre.indexOf(":");
        int id=Integer.parseInt(nombre.substring(indice+1));
        persona= new Persona(id);
        int ax = JOptionPane.showConfirmDialog(null, "Cliente: "+persona.getName()+" "+persona.getApellido()+ " ID: "+persona.getId());
         if(ax == JOptionPane.YES_OPTION){
            confirmarCliente(true);
            addPedido.setEnabled(true);
         }else if(ax == JOptionPane.NO_OPTION||ax == JOptionPane.CANCEL_OPTION||ax == JOptionPane.CLOSED_OPTION)
           confirmarCliente(false); 
       
    }
    public void confirmarCliente(boolean confirmacion){
        if(confirmacion){
            nombre_cliente.getEditor().setItem(persona.getName());
            apellido_cliente.getEditor().setItem(persona.getApellido());
            telefono.setText(persona.getTelf());
            mostrarDatosCliente(false);
            mostrarDatosLabelsCliente(true);
            llenarTablaPedidos();
            guardarCliente.setEnabled(false);
            editarCliente.setEnabled(true);
        }else{
            nombre_cliente.getEditor().setItem("");
            apellido_cliente.getEditor().setItem("");
            telefono.setText("");
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        apellido_cliente = new javax.swing.JComboBox();
        nombre_cliente = new javax.swing.JComboBox();
        labelIdPedido = new javax.swing.JLabel();
        addPedido = new javax.swing.JButton();
        guardarCliente = new javax.swing.JButton();
        nombreLabel = new javax.swing.JLabel();
        apellidoLabel = new javax.swing.JLabel();
        telefonoLabel = new javax.swing.JLabel();
        editarCliente = new javax.swing.JButton();
        guardarCambios = new javax.swing.JButton();
        ver = new javax.swing.JButton();
        salirPedido = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPedidosCliente = new javax.swing.JTable();
        listarPedidos = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, 260));

        jLabel3.setText("Telefono");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel2.setText("Apellidos");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });
        add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 85, -1));

        jLabel1.setText("Nombre");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        apellido_cliente.setEditable(true);
        add(apellido_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 240, -1));

        nombre_cliente.setEditable(true);
        add(nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 240, -1));

        labelIdPedido.setFont(new java.awt.Font("Verdana", 3, 24));
        labelIdPedido.setText("labelIdPedido");
        add(labelIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        addPedido.setText("Añiadir nuevo Pedido");
        addPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPedidoActionPerformed(evt);
            }
        });
        addPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addPedidoKeyPressed(evt);
            }
        });
        add(addPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        guardarCliente.setText("Guardar Cliente");
        guardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarClienteActionPerformed(evt);
            }
        });
        guardarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guardarClienteKeyPressed(evt);
            }
        });
        add(guardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        nombreLabel.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        add(nombreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        apellidoLabel.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        add(apellidoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        telefonoLabel.setFont(new java.awt.Font("Arial Narrow", 1, 18));
        add(telefonoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        editarCliente.setText("Editar Cliente");
        editarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarClienteActionPerformed(evt);
            }
        });
        editarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editarClienteKeyPressed(evt);
            }
        });
        add(editarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        guardarCambios.setText("Guardar Cambios");
        guardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCambiosActionPerformed(evt);
            }
        });
        guardarCambios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guardarCambiosKeyPressed(evt);
            }
        });
        add(guardarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        ver.setText("Ver Pedido");
        ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verActionPerformed(evt);
            }
        });
        add(ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 90, -1));

        salirPedido.setBackground(new java.awt.Color(255, 255, 255));
        salirPedido.setForeground(new java.awt.Color(255, 0, 0));
        salirPedido.setText("Salir Pedido");
        salirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirPedidoActionPerformed(evt);
            }
        });
        add(salirPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        tablaPedidosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPedidosCliente.setFocusable(false);
        jScrollPane2.setViewportView(tablaPedidosCliente);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 370, 90));

        listarPedidos.setText("Listar Pedidos Realizados por el Cliente");
        listarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarPedidosActionPerformed(evt);
            }
        });
        add(listarPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
    public boolean validar(){
        boolean exito=false;        
        if(validarDatosCliente())        
             exito=true;
        return exito;
    }
    public void actualizarPersona(){
        persona.setName(getString(nombre_cliente));
        persona.setApellido(getString(apellido_cliente));
        persona.setTelf(telefono.getText());
        persona.actualizar();
        
    }
  /*  private boolean validarFechas(){
        boolean validacion=false;
            try {
                    if(fecha_entrega.getDate().before(fecha_ingreso.getDate()))
                        JOptionPane.showMessageDialog(null, "no es posible entregar antes de la fecha de ingreso");
                    else
                        validacion=true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "una de las fechas ingresadas no es valida");
                }
        
        
        return validacion;
    } */  
    /*public boolean validarPago(){
        boolean validacion=false;
        calcularTotalAPagar();
        if(!total_2.getText().equals("0"))
            validacion=true;
        else
            JOptionPane.showMessageDialog(null, "el total no es correcto");
        return validacion;
    }*/
    private String getString(JComboBox combo){
        return combo.getEditor().getItem().toString();
    }
    private boolean validarDatosCliente(){
        boolean validacion=false;
        
        if(getString(apellido_cliente).equals("")&&apellido_cliente.getEditor().getItem().toString().equals("")){
            JOptionPane.showMessageDialog(null, "no coloco nombre y apellido");
        }else{
            if(getString(apellido_cliente).equals(""))
            JOptionPane.showMessageDialog(null, "no coloco nombre");
            else{
                if(getString(apellido_cliente).equals(""))
                JOptionPane.showMessageDialog(null, "no coloco apellido");
                else{
                    validacion=true;
                }
              }
            }
        return validacion;
    }
  /*  public void validarHora(){
        int hora_=Integer.parseInt(""+hora_ini.getSelectedItem());
        int minuto=Integer.parseInt(""+minuto_ini.getSelectedItem());
        hora=new Time(hora_,minuto,0);
       
    }
     * 
     */
    /*public void setNuevoPedido(Boolean nuevoPedido){
        this.nuevoPedido=nuevoPedido;
    }*/
    public Persona getCliente(){
        return persona;
    }
    public void guardarPersona(){
       if(edicion)
       {
           actualizarPersona();
       }else{
            persona =new Persona(getString(nombre_cliente), getString(apellido_cliente), telefono.getText());
            persona.guardar_en_BD();
       }
     }
      public void guardarPedido(){
        boolean exitoDetPedido=detallePedido.validar();
        if(exitoDetPedido){
            detallePedido.setCliente(getCliente());
            detallePedido.guardarPedido();
            
            JOptionPane.showMessageDialog(null, "El pedido se a guardado con exito con el ID: "+detallePedido.getIdPedido());
            cerrarDetallePedido();
        }
    }
    public void agregarATablaPedidos(){
        guardarPedido();
        ventana.actualizarTablaPrincipal();
        llenarTablaPedidos();
    }
    public void actualizarTablas(){
        ventana.actualizarTablaPrincipal();
        llenarTablaPedidos();
    }
    public void llenarTablaPedidos(){
        String sql = "SELECT ped.id as 'ID Pedido', per.nombre, per.apellidos, (pag.monto_total - pag.descuento - pag.a_cuenta) as Saldo, ped.fecha_entrega"
                   + " FROM pedido ped, persona per, pago pag"
                   + " WHERE per.id = ped.persona_id"
                   + " AND ped.id = pag.pedido_id"
                   + " AND per.id="+persona.getId();
    operaciones.getPedidos((DefaultTableModel) tablaPedidosCliente.getModel(),sql);
    } 
    public void enableBotonAdd(){
        addPedido.setEnabled(true);
    }
    public void cerrarDetallePedido(){
        detallePedido.setVisible(false);
    }
    public void habilitarDatosCliente(boolean estado){
        nombre_cliente.setEnabled(estado);
        apellido_cliente.setEnabled(estado);
        telefono.setEnabled(estado);
    }
    public void mostrarDatosCliente(boolean estado){
        nombre_cliente.setVisible(estado);
        apellido_cliente.setVisible(estado);
        telefono.setVisible(estado);
    }
    public void mostrarDatosLabelsCliente(boolean estado){
        nombreLabel.setText(persona.getName());
        apellidoLabel.setText(persona.getApellido());
        telefonoLabel.setText(persona.getTelf());
        nombreLabel.setVisible(estado);
        apellidoLabel.setVisible(estado);
        telefonoLabel.setVisible(estado);
    }

    public void cerrarVentanaPedido(){
        ventana.dispose();
    }
    public void presionarBotonGuardarCliente(){
           if(validar()){
            guardarPersona();
            
            mostrarDatosCliente(false);
            mostrarDatosLabelsCliente(true);
            llenarTablaPedidos();
            editarCliente.setEnabled(true);
            guardarCliente.setEnabled(false);
            addPedido.setEnabled(true);
           }
    }
    public void cancelarPedido(){
         addPedido.setEnabled(true);
         detallePedido.setVisible(false);
    }
    public void guardarCambios(){
        editarCliente.setEnabled(true);
       guardarPersona();
       edicion=false;
       guardarCambios.setEnabled(false);
       addPedido.setEnabled(true);
       mostrarDatosCliente(false);
       mostrarDatosLabelsCliente(true);                     
    }
    public void editarCliente(){
        llenarDatosCliente();
        mostrarDatosLabelsCliente(false);
        mostrarDatosCliente(true);
        edicion=true;
        guardarCambios.setEnabled(true);
        editarCliente.setEnabled(false);
        addPedido.setEnabled(false);
    }
    public void setIdPedido(int id){
        if(id==0)
        labelIdPedido.setText("");
        else
        labelIdPedido.setText("ID Pedido "+id);
    }
    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
}//GEN-LAST:event_telefonoKeyTyped

    private void addPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPedidoActionPerformed
       nuevoDetallePedido();
    }//GEN-LAST:event_addPedidoActionPerformed

    private void guardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarClienteActionPerformed
        presionarBotonGuardarCliente();
    }//GEN-LAST:event_guardarClienteActionPerformed

    private void editarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarClienteActionPerformed
        editarCliente();
    }//GEN-LAST:event_editarClienteActionPerformed

    private void guardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCambiosActionPerformed
        guardarCambios();
    }//GEN-LAST:event_guardarCambiosActionPerformed

    private void guardarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guardarClienteKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            presionarBotonGuardarCliente();
    }//GEN-LAST:event_guardarClienteKeyPressed

    private void editarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editarClienteKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            editarCliente();
    }//GEN-LAST:event_editarClienteKeyPressed

    private void guardarCambiosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guardarCambiosKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            guardarCambios();
    }//GEN-LAST:event_guardarCambiosKeyPressed

    private void addPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addPedidoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            nuevoDetallePedido();
    }//GEN-LAST:event_addPedidoKeyPressed

    private void verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActionPerformed
        edicion=true;
        DefaultTableModel modelo = (DefaultTableModel) tablaPedidosCliente.getModel();
        //ahora obtenemos la fila selccionada
        
        int fila_select = tablaPedidosCliente.getSelectedRow();
        
       
        if(fila_select<0){
            // no se puede eliminar
            JOptionPane.showMessageDialog(this,"Seleccione un Pedido para Editar.");
        }else {
            if(detallePedido!=null)
                this.remove(detallePedido);
            int id=(Integer)modelo.getValueAt(fila_select, 0);
            detallePedido=new DetallePedido(usuario, this, edicion);
            detallePedido.setCliente(persona);
            detallePedido.setIdPedido(id);
            labelIdPedido.setText("ID Pedido "+id);
            detallePedido.valoresEdicion();
            
            this.add(detallePedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 900, 490));
            addFondo();
            
        }
    }//GEN-LAST:event_verActionPerformed

    private void salirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirPedidoActionPerformed
        cerrarVentanaPedido();
    }//GEN-LAST:event_salirPedidoActionPerformed

    private void listarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPedidosActionPerformed
        llenarTablaPedidos();
    }//GEN-LAST:event_listarPedidosActionPerformed
  /*private void calcularTotalAPagar()
    {
        ;
        if(!lente_pago.getText().equals(""))
             costoLente=Integer.parseInt(lente_pago.getText());
        if(!armazon.getText().equals(""))
             costoArmazon=Integer.parseInt(armazon.getText());
        if(!consulta.getText().equals(""))
            costoConsulta=Integer.parseInt(consulta.getText());
        if(!descuento.getText().equals(""))
            costoDescuento=Integer.parseInt(descuento.getText());
        if(!a_cuenta.getText().equals(""))
            costoACuenta=Integer.parseInt(a_cuenta.getText());
        costoTotal=costoLente+costoArmazon+costoConsulta;
        total_2.setText(Integer.toString(costoTotal));
        totalAPagar=costoTotal-costoDescuento;
        total_a_pagar.setText(Integer.toString(totalAPagar));
        saldo.setText(Integer.toString(totalAPagar-costoACuenta));
    }
     * 
     */
/*    public void setPedido(Pedido pedido){
        this.pedido=pedido;
        cDCilindrico.setText(pedido.getDerCerCil());
        cDEsferico.setText(pedido.getDerCerEsf());
        cDEje.setText(pedido.getDerCerEje());
        cICilindrico.setText(pedido.getIzqCerCil());
        cIEsferico.setText(pedido.getIzqCerEsf());
        cIEje.setText(pedido.getIzqCerEje());
        lDCilindrico.setText(pedido.getDerLejCil());
        lDEsferico.setText(pedido.getDerLejEsf());
        lDEje.setText(pedido.getDerLejEje());
        lICilindrico.setText(pedido.getIzqLejCil());
        lIEsferico.setText(pedido.getIzqLejEsf());
        lIEje.setText(pedido.getIzqLejEje());
        fecha_entrega.setDate(pedido.getFechaEntrega());
        fecha_ingreso.setDate(pedido.getFechaIngreso());
        hora_ini.setSelectedItem(pedido.getHoraEntrega().getHours());
        minuto_ini.setSelectedItem(pedido.getHoraEntrega().getMinutes());
        observaciones.setText(pedido.getObservaciones());
        audicion.setText(pedido.getAudicion());
        altura.setText(pedido.getAltura());
        d_p_lejos.setText(pedido.getDPLejos());
        d_p_cerca.setText(pedido.getDPCerca());
        doctor.getEditor().setItem(pedido.getDoctor());
    }
     * 
     */
    public void setCliente(Persona persona){
        this.persona=persona;
        /*nombre_cliente.getEditor().setItem(persona.getName());
        apellido_cliente.getEditor().setItem(persona.getApellido());
        telefono.setText(persona.getTelf());
         * 
         */
    }
    public void llenarDatosCliente(){
        nombre_cliente.getEditor().setItem(persona.getName());
        apellido_cliente.getEditor().setItem(persona.getApellido());
        telefono.setText(persona.getTelf());
    }
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPedido;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JComboBox apellido_cliente;
    private javax.swing.JButton editarCliente;
    private javax.swing.JButton guardarCambios;
    private javax.swing.JButton guardarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelIdPedido;
    private javax.swing.JButton listarPedidos;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JComboBox nombre_cliente;
    private javax.swing.JButton salirPedido;
    private javax.swing.JTable tablaPedidosCliente;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JButton ver;
    // End of variables declaration//GEN-END:variables
}
