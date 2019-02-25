package gui;
public class ListOutstandingPanel extends javax.swing.JPanel {

    /** Creates new form LoginPanel1 */
    public ListOutstandingPanel() {
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

        listClientsButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextBox = new javax.swing.JTextArea();
        logoutButton = new javax.swing.JButton();
        topLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        backButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(420, 450));
        setMinimumSize(new java.awt.Dimension(420, 450));
        setPreferredSize(new java.awt.Dimension(420, 420));

        listClientsButton.setText("List Clients");
        listClientsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listClientsButtonActionPerformed(evt);
            }
        });

        resultTextBox.setColumns(20);
        resultTextBox.setRows(5);
        jScrollPane1.setViewportView(resultTextBox);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        topLabel.setText("Logged in as Sales Clerk: Add Client");

        backButton1.setText("Back");
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(listClientsButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(topLabel)
                .addGap(62, 62, 62))
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
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listClientsButton)
                .addGap(66, 66, 66))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listClientsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listClientsButtonActionPerformed
        ListOutstandingState.instance().confirmPressed();
    }//GEN-LAST:event_listClientsButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        ListOutstandingState.instance().logoutPressed();
}//GEN-LAST:event_logoutButtonActionPerformed

    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        ListOutstandingState.instance().backPressed();
}//GEN-LAST:event_backButton1ActionPerformed

    public void setup(){
        topLabel.setText("Logged in as " + Security.instance().getLoginString() + ": List Outstanding Backorders");
        resultTextBox.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton listClientsButton;
    private javax.swing.JButton logoutButton;
    public javax.swing.JTextArea resultTextBox;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables

}