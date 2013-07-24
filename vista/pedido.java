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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private Time hora=new Time(0, 0, 0);
    private JLabel fondo=new JLabel(new ImageIcon("imagenes/fondo.jpg"));
    private int costoLente=0;
    private int costoArmazon=0;
    private int costoConsulta=0;
    private int costoDescuento=0;
    private int costoACuenta=0;
    private int costoTotal=0;
    private int cotoSaldo=0;
    private int totalAPagar=0;
    private Persona persona=new Persona();
    private Montura montura=new Montura();
    private Lente lente=new Lente();
    public pedido() {
       
        initComponents();
        
        valoresPorDefecto();
        
    }
    public void valoresPorDefecto(){
        //fechas
        Date fechaActual=new Date();
        fecha_ingreso.setDate(fechaActual);
        fecha_entrega.setDate(fechaActual);
        fecha_ingreso.setMinSelectableDate(fechaActual);
        fecha_entrega.setMinSelectableDate(fechaActual);
        //fondo
        add(fondo,new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0,1000, -1));
        //autocompletes
        iniciarAutoCompletes(); 
        //nombres de componentes
        setnombresComponentes();
    }
    public void setnombresComponentes(){
        //persona
        nombre_cliente.setName("nombre_cliente");
        apellido_cliente.setName("apellido_cliente");
        //montura
        marca_montura.setName("marca_montura");
        codigo_montura.setName("codigo_montura");
        color_montura.setName("color_montura");
        tipo_montura.setName("tipo_montura");
        tamanio_montura.setName("tamanio_montura");
        //lente
        material_lente.setName("material_lente");
        tipo_lente.setName("tipo_lente");
        color_lente.setName("color_lente");
        vision_lente.setName("vision_lente");
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
        marca_montura.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,marca_montura);
            }
        });
        codigo_montura.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,codigo_montura);
            }
        });
        color_montura.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,color_montura);
            }
        });
        tipo_montura.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,tipo_montura);
            }
        });
        tamanio_montura.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,tamanio_montura);
            }
        });
        material_lente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,material_lente);
            }
        });
        tipo_lente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,tipo_lente);
            }
        });
        color_lente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,color_lente);
            }
        });
        vision_lente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override
            public void keyReleased(KeyEvent evt) {
               autocompletar(evt,vision_lente);
            }
        });
    }
       public void autocompletar(KeyEvent evt,JComboBox combo)
    {
         String cadenaEscrita = combo.getEditor().getItem().toString();

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

//                    buscar(cadenaEscrita);
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
    public void listarNombres(JComboBox combo,String cadenaEscrita){
        System.out.println(combo.getName());
        if(combo.getName().equals("apellido_cliente"))
             combo.setModel(persona.listaApellidos(cadenaEscrita));
        if(combo.getName().equals("nombre_cliente"))
             combo.setModel(persona.listaNombres(cadenaEscrita));
        if(combo.getName().equals("marca_montura"))
             combo.setModel(montura.listaMarcas(cadenaEscrita));
        if(combo.getName().equals("codigo_montura"))
             combo.setModel(montura.listaCodigos(cadenaEscrita));
        if(combo.getName().equals("color_montura"))
             combo.setModel(montura.listaColores(cadenaEscrita));
        if(combo.getName().equals("tipo_montura"))
             combo.setModel(montura.listaTipos(cadenaEscrita));
        if(combo.getName().equals("tamanio_montura"))
             combo.setModel(montura.listaTamanios(cadenaEscrita));
        if(combo.getName().equals("material_lente"))
             combo.setModel(lente.listaMateriales(cadenaEscrita));
        if(combo.getName().equals("tipo_lente"))
             combo.setModel(lente.listaTipos(cadenaEscrita));
        if(combo.getName().equals("color_lente"))
             combo.setModel(lente.listaColores(cadenaEscrita));
        if(combo.getName().equals("vision_lente"))
             combo.setModel(lente.listaVisiones(cadenaEscrita));
        
    }
            
/*    public void buscar(String nombre) {
        String datos[] = operacion.buscar(nombre);

        if (datos[0] != null) {
            jTextField1.setText(datos[0]);
            jTextField2.setText(datos[1]);
            jTextField3.setText(datos[2]);
            jTextField4.setText(datos[3]);

        } else {

            JOptionPane.showMessageDialog(this, "No se encontro ningun archivo", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
        * 
        */
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        a_cuenta = new javax.swing.JTextField();
        saldo = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        total_a_pagar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        audicion = new javax.swing.JTextField();
        altura = new javax.swing.JTextField();
        d_p_lejos = new javax.swing.JTextField();
        d_p_cerca = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        marca_montura = new javax.swing.JComboBox();
        tamanio_montura = new javax.swing.JComboBox();
        tipo_montura = new javax.swing.JComboBox();
        color_montura = new javax.swing.JComboBox();
        codigo_montura = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        vision_lente = new javax.swing.JComboBox();
        color_lente = new javax.swing.JComboBox();
        tipo_lente = new javax.swing.JComboBox();
        material_lente = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lente_pago = new javax.swing.JTextField();
        armazon = new javax.swing.JTextField();
        consulta = new javax.swing.JTextField();
        total_2 = new javax.swing.JTextField();
        descuento = new javax.swing.JTextField();
        fecha_ingreso = new com.toedter.calendar.JDateChooser();
        fecha_entrega = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lDEsferico = new javax.swing.JTextField();
        lIEsferico = new javax.swing.JTextField();
        lICilindrico = new javax.swing.JTextField();
        lIEje = new javax.swing.JTextField();
        lDEje = new javax.swing.JTextField();
        lDCilindrico = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cercaLejos = new javax.swing.JComboBox();
        cDEsferico = new javax.swing.JTextField();
        cIEsferico = new javax.swing.JTextField();
        cICilindrico = new javax.swing.JTextField();
        cIEje = new javax.swing.JTextField();
        cDCilindrico = new javax.swing.JTextField();
        cDEje = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        l_dos_puntos = new javax.swing.JLabel();
        minuto_ini = new javax.swing.JComboBox();
        hora_ini = new javax.swing.JComboBox();
        apellido_cliente = new javax.swing.JComboBox();
        nombre_cliente = new javax.swing.JComboBox();
        doctor = new javax.swing.JComboBox();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, -1));

        jLabel2.setText("Apellidos");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel3.setText("Telefono");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jLabel4.setText("Total");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        jLabel5.setText("Acuenta");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, -1, -1));

        jLabel6.setText("Saldo");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, -1, -1));

        jLabel7.setText("Doctor");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, -1, -1));

        a_cuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                a_cuentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                a_cuentaKeyTyped(evt);
            }
        });
        add(a_cuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 70, -1));

        saldo.setEditable(false);
        saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                saldoKeyTyped(evt);
            }
        });
        add(saldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 70, -1));

        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });
        add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 85, -1));

        total_a_pagar.setEditable(false);
        total_a_pagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_a_pagarKeyTyped(evt);
            }
        });
        add(total_a_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 70, -1));

        jLabel8.setText("AUDICION");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, -1, -1));

        jLabel9.setText("ALTURA");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, -1, -1));

        jLabel10.setText("D. P. LEJOS");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, -1, -1));

        jLabel11.setText("D. P. CERCA");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 510, -1, -1));
        add(audicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, 60, -1));
        add(altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 530, 62, -1));
        add(d_p_lejos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, 56, -1));
        add(d_p_cerca, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, 61, -1));

        jLabel12.setText("Oservaciones");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 560, -1, -1));

        observaciones.setColumns(20);
        observaciones.setRows(5);
        jScrollPane1.setViewportView(observaciones);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, 268, 68));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTURA/ARMAZON", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Hobo Std", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setText("Marca");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel30.setText("Codigo");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel31.setText("Color");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel32.setText("Tipo");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel33.setText("Tamaño");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 150, -1, -1));

        marca_montura.setEditable(true);
        jPanel1.add(marca_montura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 80, -1));

        tamanio_montura.setEditable(true);
        jPanel1.add(tamanio_montura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 80, -1));

        tipo_montura.setEditable(true);
        jPanel1.add(tipo_montura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 80, -1));

        color_montura.setEditable(true);
        jPanel1.add(color_montura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 80, -1));

        codigo_montura.setEditable(true);
        jPanel1.add(codigo_montura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 80, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 210, 180));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Hobo Std", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setText("Material");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 20));

        jLabel15.setText("Tipo");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel16.setText("Color");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel17.setText("Vision");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        vision_lente.setEditable(true);
        jPanel2.add(vision_lente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 80, -1));

        color_lente.setEditable(true);
        jPanel2.add(color_lente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 80, -1));

        tipo_lente.setEditable(true);
        jPanel2.add(tipo_lente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 80, -1));

        material_lente.setEditable(true);
        jPanel2.add(material_lente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 80, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 210, 160));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Hobo Std", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setText("Lente");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel19.setText("Armazon");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel20.setText("Consulta");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 21));

        jLabel21.setText("Total");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel22.setText("Descuento");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        lente_pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lente_pagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lente_pagoKeyTyped(evt);
            }
        });
        jPanel3.add(lente_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 71, -1));

        armazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                armazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                armazonKeyTyped(evt);
            }
        });
        jPanel3.add(armazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 71, -1));

        consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                consultaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                consultaKeyTyped(evt);
            }
        });
        jPanel3.add(consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 71, -1));

        total_2.setEditable(false);
        total_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_2KeyTyped(evt);
            }
        });
        jPanel3.add(total_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 71, -1));

        descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoKeyTyped(evt);
            }
        });
        jPanel3.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 72, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 109, 210, 170));

        fecha_ingreso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        fecha_ingreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fecha_ingresoKeyTyped(evt);
            }
        });
        add(fecha_ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));
        add(fecha_entrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, -1));

        jLabel23.setText("Fecha Ingreso");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));

        jLabel24.setText("Fecha Entrega");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, -1));
        add(lDEsferico, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 59, -1));
        add(lIEsferico, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 59, -1));
        add(lICilindrico, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 59, -1));
        add(lIEje, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 59, -1));
        add(lDEje, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 59, -1));
        add(lDCilindrico, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 59, -1));

        jLabel25.setText("Esferico");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, -1, -1));

        jLabel26.setText("Cilindrico");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, -1, -1));

        jLabel27.setText("Eje");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 370, -1, -1));

        jLabel28.setText("D");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, -1));

        jLabel35.setText("I");
        add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, -1, -1));

        cercaLejos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lejos", "Cerca" }));
        add(cercaLejos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, -1, -1));
        add(cDEsferico, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 59, -1));
        add(cIEsferico, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, 59, -1));
        add(cICilindrico, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 59, -1));
        add(cIEje, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 480, 59, -1));
        add(cDCilindrico, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 59, -1));
        add(cDEje, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, 59, -1));

        jLabel36.setText("D");
        add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, -1, -1));

        jLabel37.setText("I");
        add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 480, -1, -1));

        jLabel38.setText("Lejos");
        add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, -1));

        jLabel39.setText("Cerca");
        add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, -1));

        jLabel40.setText("Hora Entrega");
        add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, -1, -1));

        l_dos_puntos.setText(":");
        add(l_dos_puntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, -1));

        minuto_ini.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        add(minuto_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, -1, -1));

        hora_ini.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        add(hora_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, -1, -1));

        apellido_cliente.setEditable(true);
        add(apellido_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 240, -1));

        nombre_cliente.setEditable(true);
        add(nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 240, -1));

        doctor.setEditable(true);
        add(doctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 240, -1));
    }// </editor-fold>//GEN-END:initComponents
    public boolean validaPedidoYGuardar(){
        boolean exito=false;
        if(validarDatosCliente()){
            if(validarPago()){
                if(validarFechas()){
                    guardarPedido();
                    exito=true;}
            }
        }
        return exito;
    }
    private boolean validarFechas(){
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
    private String getString(JComboBox combo){
        return combo.getEditor().getItem().toString();
    }
    private boolean validarDatosCliente(){
        boolean validacion=false;
        validarHora();
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
    public void validarHora(){
        int hora_=Integer.parseInt(""+hora_ini.getSelectedItem());
        int minuto=Integer.parseInt(""+minuto_ini.getSelectedItem());
        hora=new Time(hora_,minuto,0);
       
    }
    private void guardarPedido(){
       Usuario usuario=new Usuario("juankiss", "juankiss", "conectado:D");
       Persona persona =new Persona(getString(apellido_cliente), getString(apellido_cliente), telefono.getText());
       persona.guardar_en_BD();
       
       
       int total=costoTotal;
       int acu= costoACuenta;
       int desc=costoDescuento;
       
       Pedido pedido= new Pedido(fecha_ingreso.getDate(), fecha_entrega.getDate(),hora, lDEsferico.getText()
                                , lDCilindrico.getText(),lDEje.getText(), lIEsferico.getText(),lICilindrico.getText()
                                , lIEje.getText(),cDEsferico.getText(), cDCilindrico.getText(),cDEje.getText()
                                , cIEsferico.getText(),cICilindrico.getText(),cIEje.getText(), audicion.getText()
                                , altura.getText(), d_p_lejos.getText(),d_p_cerca.getText(), observaciones.getText()
                                , "es",getString(doctor),persona.getId(),usuario.get_id());
       pedido.guardarEnBD();
       
       Pago pago= new Pago(total,"01",acu ,desc,pedido.getId()); 
       pago.guardar_en_BD();
       Lente lente=new Lente(getString(material_lente),getString(tipo_lente),
                             getString(color_lente), getString(vision_lente),"01",pedido.getId());
       lente.guardar_en_BD();
       Montura montura_a=new Montura(getString(marca_montura),getString(codigo_montura),
                                    getString(color_montura),getString(tipo_montura),
                                    getString(tamanio_montura),"01",pedido.getId());
       montura_a.guardar_en_BD();
     }
    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_telefonoKeyTyped

    private void total_a_pagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_a_pagarKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_total_a_pagarKeyTyped

    private void a_cuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a_cuentaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_a_cuentaKeyTyped

    private void saldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saldoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_saldoKeyTyped

    private void total_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_2KeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_total_2KeyTyped

    private void descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_descuentoKeyTyped

    private void lente_pagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lente_pagoKeyTyped
        calcularTotalAPagar();
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();            
    }//GEN-LAST:event_lente_pagoKeyTyped

    private void armazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_armazonKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_armazonKeyTyped

    private void consultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consultaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_consultaKeyTyped

    private void fecha_ingresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_ingresoKeyTyped
        fecha_entrega.setMinSelectableDate(fecha_ingreso.getDate());
    }//GEN-LAST:event_fecha_ingresoKeyTyped

    private void lente_pagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lente_pagoKeyReleased
        calcularTotalAPagar();
    }//GEN-LAST:event_lente_pagoKeyReleased

    private void armazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_armazonKeyReleased
        calcularTotalAPagar();
    }//GEN-LAST:event_armazonKeyReleased

    private void consultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consultaKeyReleased
        calcularTotalAPagar();
    }//GEN-LAST:event_consultaKeyReleased

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
        calcularTotalAPagar();
    }//GEN-LAST:event_descuentoKeyReleased

    private void a_cuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a_cuentaKeyReleased
        calcularTotalAPagar();
    }//GEN-LAST:event_a_cuentaKeyReleased
    private void calcularTotalAPagar()
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a_cuenta;
    private javax.swing.JTextField altura;
    private javax.swing.JComboBox apellido_cliente;
    private javax.swing.JTextField armazon;
    private javax.swing.JTextField audicion;
    private javax.swing.JTextField cDCilindrico;
    private javax.swing.JTextField cDEje;
    private javax.swing.JTextField cDEsferico;
    private javax.swing.JTextField cICilindrico;
    private javax.swing.JTextField cIEje;
    private javax.swing.JTextField cIEsferico;
    private javax.swing.JComboBox cercaLejos;
    private javax.swing.JComboBox codigo_montura;
    private javax.swing.JComboBox color_lente;
    private javax.swing.JComboBox color_montura;
    private javax.swing.JTextField consulta;
    private javax.swing.JTextField d_p_cerca;
    private javax.swing.JTextField d_p_lejos;
    private javax.swing.JTextField descuento;
    private javax.swing.JComboBox doctor;
    private com.toedter.calendar.JDateChooser fecha_entrega;
    private com.toedter.calendar.JDateChooser fecha_ingreso;
    private javax.swing.JComboBox hora_ini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lDCilindrico;
    private javax.swing.JTextField lDEje;
    private javax.swing.JTextField lDEsferico;
    private javax.swing.JTextField lICilindrico;
    private javax.swing.JTextField lIEje;
    private javax.swing.JTextField lIEsferico;
    private javax.swing.JLabel l_dos_puntos;
    private javax.swing.JTextField lente_pago;
    private javax.swing.JComboBox marca_montura;
    private javax.swing.JComboBox material_lente;
    private javax.swing.JComboBox minuto_ini;
    private javax.swing.JComboBox nombre_cliente;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextField saldo;
    private javax.swing.JComboBox tamanio_montura;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox tipo_lente;
    private javax.swing.JComboBox tipo_montura;
    private javax.swing.JTextField total_2;
    private javax.swing.JTextField total_a_pagar;
    private javax.swing.JComboBox vision_lente;
    // End of variables declaration//GEN-END:variables
}
