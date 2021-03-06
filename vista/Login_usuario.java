/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login_usuario.java
 *
 * Created on 10-jul-2013, 21:05:35
 */
package vista;

import conexion.Operaciones;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetos.Usuario;

/**
 *
 * @author juanki
 */

public class Login_usuario extends javax.swing.JPanel {

    /** Creates new form Login_usuario */
    private menuPrincipal inicio;
    private Operaciones operaciones=new Operaciones();
    private Principal principal;
    private Usuario usuario;
    public Login_usuario(menuPrincipal ventanaPrincipal) {
        initComponents();
        
        inicio =ventanaPrincipal;
    }
    public void ingresar(){
        if(validarDatosUsuario()){
            int id=validarUsuario();
            if(id!=0){
                usuario=new Usuario(id);
                inicio.dispose();
                principal=new Principal(usuario);
                principal.setVisible(true);    
            }else
                JOptionPane.showMessageDialog(null, "el login n es correcto");
        }
    }
    public void keyEnter(java.awt.event.KeyEvent evt){
        if (evt.getKeyCode() == evt.VK_ENTER) 
              ingresar();               
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
        jLabel2 = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        ingresar = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        salir = new javax.swing.JButton();

        jLabel1.setText("Login");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
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
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        ingresar();
    }//GEN-LAST:event_ingresarActionPerformed
    
    private void ingresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarKeyPressed
         keyEnter(evt);
    }//GEN-LAST:event_ingresarKeyPressed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        
    }//GEN-LAST:event_salirActionPerformed

    private void salirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_salirKeyPressed
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ingresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField login;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
