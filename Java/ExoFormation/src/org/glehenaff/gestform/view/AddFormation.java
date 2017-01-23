/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

/**
 *
 * @author gwenole
 */
public class AddFormation extends javax.swing.JFrame {

    /**
     * Creates new form AddFormation
     */
    public AddFormation() {
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

        txtFormation = new javax.swing.JTextField();
        panHeader = new javax.swing.JPanel();
        lblNom = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        panGauche = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstAllStag = new javax.swing.JList<>();
        lblAllStag = new javax.swing.JLabel();
        panDroite = new javax.swing.JPanel();
        lblStag1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstStag = new javax.swing.JList<>();
        lblStag2 = new javax.swing.JLabel();
        btnFermer = new javax.swing.JButton();
        panCentre = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSuppr = new javax.swing.JButton();

        txtFormation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormationActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajouter une formation");

        lblNom.setText("Nom de la formation");

        txtNom.setToolTipText("Nom de la formation");

        javax.swing.GroupLayout panHeaderLayout = new javax.swing.GroupLayout(panHeader);
        panHeader.setLayout(panHeaderLayout);
        panHeaderLayout.setHorizontalGroup(
            panHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        panHeaderLayout.setVerticalGroup(
            panHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNom)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panHeader, java.awt.BorderLayout.PAGE_START);

        lstAllStag.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstAllStag.setToolTipText("Tous les stagiaires");
        jScrollPane1.setViewportView(lstAllStag);

        lblAllStag.setText("Tous les stagiaires");

        javax.swing.GroupLayout panGaucheLayout = new javax.swing.GroupLayout(panGauche);
        panGauche.setLayout(panGaucheLayout);
        panGaucheLayout.setHorizontalGroup(
            panGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panGaucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAllStag)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panGaucheLayout.setVerticalGroup(
            panGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panGaucheLayout.createSequentialGroup()
                .addComponent(lblAllStag)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(panGauche, java.awt.BorderLayout.LINE_START);

        lblStag1.setText("Stagiaires de la");

        lstStag.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstStag.setToolTipText("Stagiaires de la formation");
        jScrollPane2.setViewportView(lstStag);

        lblStag2.setText("Formation");

        btnFermer.setText("Fermer");

        javax.swing.GroupLayout panDroiteLayout = new javax.swing.GroupLayout(panDroite);
        panDroite.setLayout(panDroiteLayout);
        panDroiteLayout.setHorizontalGroup(
            panDroiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDroiteLayout.createSequentialGroup()
                .addGroup(panDroiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStag1)
                    .addComponent(lblStag2))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panDroiteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panDroiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFermer, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panDroiteLayout.setVerticalGroup(
            panDroiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDroiteLayout.createSequentialGroup()
                .addComponent(lblStag1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStag2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnFermer)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(panDroite, java.awt.BorderLayout.LINE_END);

        btnAdd.setText("->");
        btnAdd.setToolTipText("Ajouter un stagiaire sélectionné");
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSuppr.setText("<-");
        btnSuppr.setToolTipText("Supprimer un stagiaire sélectionné");
        btnSuppr.setEnabled(false);
        btnSuppr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panCentreLayout = new javax.swing.GroupLayout(panCentre);
        panCentre.setLayout(panCentreLayout);
        panCentreLayout.setHorizontalGroup(
            panCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCentreLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSuppr, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panCentreLayout.setVerticalGroup(
            panCentreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCentreLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnSuppr)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        getContentPane().add(panCentre, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormationActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSupprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFormation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFermer;
    private javax.swing.JButton btnSuppr;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAllStag;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblStag1;
    private javax.swing.JLabel lblStag2;
    private javax.swing.JList<String> lstAllStag;
    private javax.swing.JList<String> lstStag;
    private javax.swing.JPanel panCentre;
    private javax.swing.JPanel panDroite;
    private javax.swing.JPanel panGauche;
    private javax.swing.JPanel panHeader;
    private javax.swing.JTextField txtFormation;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
