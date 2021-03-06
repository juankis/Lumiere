/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * menuPrincipal.java
 *
 * Created on 01-ago-2013, 16:06:56
 */
package vista;

import conexion.Operaciones;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import objetos.Usuario;

/**
 *
 * @author juanki
 */
   
public class menuPrincipal extends javax.swing.JFrame {

    /** Creates new form menuPrincipal */
    Login_usuario logUsuario;
    nuevoUsuario nuevUsuario;
    private menuPrincipal inicio;
    private Operaciones operaciones=new Operaciones();
    private Principal principal;
    private Usuario usuario;
    private JLabel fondo=new JLabel(new ImageIcon("imagenes/fondo.jpg"));
   
    public menuPrincipal() {
        setLayout(null);
        initComponents();
        this.setLocationRelativeTo(null);
        addFondo();
        
    }
    public void loguearUsuario(){
        logUsuario=new Login_usuario(this);
        logUsuario.setBounds(0, 0, 400, 200);
        add(logUsuario);
    }
    public void addFondo(){
        fondo.setBounds(0, 0, 500, 500);
        this.getContentPane().add(fondo);
    }
    /*public void registrarnuevoUsuario(){
        nuevUsuario=new nuevoUsuario(this);
        nuevUsuario.setBounds(0, 0, 400, 300);
        add(nuevUsuario);
    }*/
    public void setVisibleFalsoTodos(){
        if(logUsuario!=null)
        logUsuario.setVisible(false);
        if(nuevUsuario!=null)
        nuevUsuario.setVisible(false);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        ingresar = new javax.swing.JButton();
        login = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Login");

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        salir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salirKeyPressed(evt);
            }
        });

        jLabel2.setText("Password");

        ingresar.setText("Ingresar");
        ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
        ingresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingresarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ingresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(ingresar))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        dispose();
}//GEN-LAST:event_salirActionPerformed

    private void salirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salirKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER)
            dispose();
}//GEN-LAST:event_salirKeyPressed

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        ingresar();
}//GEN-LAST:event_ingresarActionPerformed

    private void ingresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER)
        ingresar();
}//GEN-LAST:event_ingresarKeyPressed
    private boolean validarDatosUsuario(){
        boolean validacion=false;
        if(!login.getText().equals(""))
            if(!password.getText().equals(""))
                validacion=true;
            else          
                JOptionPane.showMessageDialog(null, "la contraseña no es valida");
        else 
            JOptionPane.showMessageDialog(null, "el nombre de usuario no es valido");    
        return validacion;
    }
    private int validarUsuario(){
        return operaciones.validarUsuario(login.getText(),password.getText());                   
    }
    public void ingresar(){
        if(validarDatosUsuario()){
            int id=validarUsuario();
            if(id!=0){
                usuario=new Usuario(id);
                //inicio.dispose();
                principal=new Principal(usuario);
                principal.setVisible(true);
                dispose();
            }else
                JOptionPane.showMessageDialog(null, "el login n es correcto");
        }
    }
    /*public void keyEnter(java.awt.event.KeyEvent evt){
        if (evt.getKeyCode() == evt.VK_ENTER) 
              ingresar();               
    }/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
                  /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
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
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new menuPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ingresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField login;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
