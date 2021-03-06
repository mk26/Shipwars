
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centerPanel = new javax.swing.JPanel();
        addNewUserTextArea = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        userNameTextField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        reenterPasswordField = new javax.swing.JPasswordField();
        reenterPasswordLabel = new javax.swing.JLabel();
        enterPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        centerPanel.setInheritsPopupMenu(true);
        centerPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        centerPanel.setRequestFocusEnabled(false);

        addNewUserTextArea.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        addNewUserTextArea.setText("Ship Wars Register");

        firstNameTextField.setColumns(11);
        firstNameTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        firstNameTextField.setToolTipText("");
        firstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTextFieldActionPerformed(evt);
            }
        });

        userNameTextField.setColumns(11);
        userNameTextField.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N

        firstNameLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        firstNameLabel.setText("Name");

        userNameLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        userNameLabel.setText("UserName");

        passwordLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        passwordLabel.setText("Password");

        addButton.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(0, 95, 45));
        addButton.setText("Submit");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addButtonActionPerformed(evt);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        reenterPasswordField.setColumns(11);
        reenterPasswordField.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        reenterPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reenterPasswordFieldActionPerformed(evt);
            }
        });

        reenterPasswordLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        reenterPasswordLabel.setText("Re-enter Password");

        enterPasswordField.setColumns(11);
        enterPasswordField.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameLabel)
                            .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(passwordLabel)
                                .addComponent(userNameLabel)))
                        .addGap(74, 74, 74)
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(centerPanelLayout.createSequentialGroup()
                                .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(99, Short.MAX_VALUE))
                            .addGroup(centerPanelLayout.createSequentialGroup()
                                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userNameTextField)
                                    .addComponent(enterPasswordField))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addNewUserTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(centerPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(reenterPasswordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(reenterPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(addButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addNewUserTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameLabel))
                .addGap(18, 18, 18)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameLabel))
                .addGap(18, 18, 18)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(enterPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reenterPasswordLabel)
                    .addComponent(reenterPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTextFieldActionPerformed

    private void addButtonActionPerformed(ActionEvent evt) throws URISyntaxException, SQLException {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:

        String name = firstNameTextField.getText();
        String username = userNameTextField.getText();
        String password = String.valueOf(enterPasswordField.getPassword());
        String conPassword = String.valueOf(reenterPasswordField.getPassword());

        if (name.equals("") || username.equals("") || password.equals("") || conPassword.equals("")) {
            JOptionPane.showMessageDialog(centerPanel, "Please enter complete Details with Password Confirmation");
        } else if (!String.valueOf(enterPasswordField.getPassword()).equals(String.valueOf(reenterPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            reenterPasswordField.setText("");
            reenterPasswordField.requestFocus();
            return;
        } else {
            boolean flag = DatabaseUtility.registerUser(name, username, password);
            if(flag) {
                JOptionPane.showMessageDialog(centerPanel, "New UserID" + "  " + username + " " + "has been added succesfully");
                //new Login().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(centerPanel, username+ " already exists");
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void reenterPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reenterPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reenterPasswordFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel addNewUserTextArea;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPasswordField enterPasswordField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField reenterPasswordField;
    private javax.swing.JLabel reenterPasswordLabel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
}
