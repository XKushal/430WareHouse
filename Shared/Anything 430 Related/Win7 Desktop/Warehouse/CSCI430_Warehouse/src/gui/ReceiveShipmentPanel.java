package gui;
public class ReceiveShipmentPanel extends javax.swing.JPanel {

    /** Creates new form ReceiveShipment */
    public ReceiveShipmentPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productIDField = new javax.swing.JTextField();
        orderNumberField = new javax.swing.JTextField();
        backButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        topLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        manufacturerIDField = new javax.swing.JButton();
        noButton = new javax.swing.JButton();
        JScrllPane = new javax.swing.JScrollPane();
        ResultTextBox = new javax.swing.JTextArea();
        findOrdersButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(420, 450));
        setMinimumSize(new java.awt.Dimension(420, 450));
        setPreferredSize(new java.awt.Dimension(420, 450));

        jLabel2.setText("Enter Order Number:");
        jLabel2.setMaximumSize(new java.awt.Dimension(420, 420));
        jLabel2.setMinimumSize(new java.awt.Dimension(420, 420));

        jLabel3.setText("Enter Manufacturer ID:");
        jLabel3.setMaximumSize(new java.awt.Dimension(420, 420));
        jLabel3.setMinimumSize(new java.awt.Dimension(420, 420));

        backButton1.setText("Back");
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });

        topLabel.setText("Logged in as Sales Clerk: Add Client");

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        manufacturerIDField.setText("Yes");
        manufacturerIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manufacturerIDFieldActionPerformed(evt);
            }
        });

        noButton.setText("No");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });

        ResultTextBox.setColumns(20);
        ResultTextBox.setRows(5);
        JScrllPane.setViewportView(ResultTextBox);

        findOrdersButton.setText("Find Orders");
        findOrdersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findOrdersButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(topLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                        .addComponent(logoutButton)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(manufacturerIDField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(noButton)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrllPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(productIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(findOrdersButton))
                            .addComponent(orderNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(confirmButton)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton1)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findOrdersButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(confirmButton)
                .addGap(18, 18, 18)
                .addComponent(JScrllPane, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manufacturerIDField)
                    .addComponent(noButton))
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        ReceiveShipmentState.instance().backPressed();
}//GEN-LAST:event_backButton1ActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        ReceiveShipmentState.instance().logoutPressed();
}//GEN-LAST:event_logoutButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        ReceiveShipmentState.instance().confirmPressed();
}//GEN-LAST:event_confirmButtonActionPerformed

    private void manufacturerIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manufacturerIDFieldActionPerformed
        ReceiveShipmentState.instance().yesPressed();
    }//GEN-LAST:event_manufacturerIDFieldActionPerformed

    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
        ReceiveShipmentState.instance().noPressed();
    }//GEN-LAST:event_noButtonActionPerformed

    private void findOrdersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findOrdersButtonActionPerformed
        ReceiveShipmentState.instance().findManOrders();
    }//GEN-LAST:event_findOrdersButtonActionPerformed

    public void setup(){
        manufacturerIDField.setVisible(false);
        noButton.setVisible(false);
        productIDField.setText("");
        orderNumberField.setText("");
        topLabel.setText("Logged in as " + Security.instance().getLoginString() + ": Receive Shipment");
        ResultTextBox.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JScrollPane JScrllPane;
    public javax.swing.JTextArea ResultTextBox;
    public javax.swing.JButton backButton1;
    public javax.swing.JButton confirmButton;
    private javax.swing.JButton findOrdersButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JButton logoutButton;
    public javax.swing.JButton manufacturerIDField;
    public javax.swing.JButton noButton;
    public javax.swing.JTextField orderNumberField;
    public javax.swing.JTextField productIDField;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables

}