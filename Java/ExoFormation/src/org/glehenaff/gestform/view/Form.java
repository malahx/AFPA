/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.glehenaff.gestform.model.ECF;
import org.glehenaff.gestform.model.Formation;
import org.glehenaff.gestform.model.Stagiaire;

/**
 *
 * @author gwenole
 */
public class Form extends javax.swing.JFrame {

    private FormListModel lstFormModel;
    private StagTableModel tblStagModel;
    private StagTableModel tblStagFormModel;
    private ECFTableModel tblECFModel;

    /**
     * Creates new form Form
     */
    public Form(List<Formation> formations, List<Stagiaire> stagiaires) {
        lstFormModel = new FormListModel(formations);
        tblStagModel = new StagTableModel(stagiaires);
        tblStagFormModel = new StagTableModel();
        tblECFModel = new ECFTableModel();
        initComponents();
        txtFormNom.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtFormNomActionPerformed(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtFormNomActionPerformed(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtFormNomActionPerformed(null);
            }
        });
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panFooter = new javax.swing.JPanel();
        txtFooter = new javax.swing.JTextField();
        tbpContent = new javax.swing.JTabbedPane();
        panStag = new javax.swing.JPanel();
        panForm = new javax.swing.JPanel();
        panList = new javax.swing.JPanel();
        panLstForm = new javax.swing.JPanel();
        scrlForm = new javax.swing.JScrollPane();
        lstForm = new javax.swing.JList<>();
        panAddForm = new javax.swing.JPanel();
        panFormNom = new javax.swing.JPanel();
        lblFormNom = new javax.swing.JLabel();
        txtFormNom = new javax.swing.JTextField();
        panFormStag = new javax.swing.JPanel();
        sclFormStag = new javax.swing.JScrollPane();
        tblFormStag = new javax.swing.JTable();
        panFormStagBtn = new javax.swing.JPanel();
        btnFormStagSuppr = new javax.swing.JButton();
        btnFormStagAdd = new javax.swing.JButton();
        panFormECF = new javax.swing.JPanel();
        panFormECFBtn = new javax.swing.JPanel();
        btnFormECFSuppr = new javax.swing.JButton();
        btnFormECFRes = new javax.swing.JButton();
        btnFormECFAdd = new javax.swing.JButton();
        sclFormECF = new javax.swing.JScrollPane();
        tblFormECF = new javax.swing.JTable();
        panFormBtn = new javax.swing.JPanel();
        btnFormSuppr = new javax.swing.JButton();
        btnFormAdd = new javax.swing.JButton();
        mnuBar = new javax.swing.JMenuBar();
        mnuFichier = new javax.swing.JMenu();
        itmActualiser = new javax.swing.JMenuItem();
        itmSep = new javax.swing.JPopupMenu.Separator();
        itmQuitter = new javax.swing.JMenuItem();
        mnuAide = new javax.swing.JMenu();
        itmApropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestionnaire de formation");
        setMaximumSize(null);

        panFooter.setLayout(new javax.swing.BoxLayout(panFooter, javax.swing.BoxLayout.LINE_AXIS));

        txtFooter.setEditable(false);
        txtFooter.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFooter.setText("Gestionnaire de formations");
        txtFooter.setName(""); // NOI18N
        panFooter.add(txtFooter);

        getContentPane().add(panFooter, java.awt.BorderLayout.PAGE_END);

        panStag.setLayout(new java.awt.BorderLayout());
        tbpContent.addTab("Stagiaires", panStag);

        panForm.setLayout(new java.awt.BorderLayout(5, 5));

        panList.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste de formations"));
        panList.setAlignmentX(0.0F);
        panList.setAlignmentY(0.0F);
        panList.setLayout(new javax.swing.BoxLayout(panList, javax.swing.BoxLayout.Y_AXIS));

        panLstForm.setPreferredSize(new java.awt.Dimension(200, 400));
        panLstForm.setLayout(new javax.swing.BoxLayout(panLstForm, javax.swing.BoxLayout.LINE_AXIS));

        scrlForm.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlForm.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrlForm.setMaximumSize(null);
        scrlForm.setMinimumSize(null);
        scrlForm.setPreferredSize(new java.awt.Dimension(400, 323));

        lstForm.setModel(lstFormModel);
        lstForm.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstForm.setVisibleRowCount(20);
        lstForm.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstFormValueChanged(evt);
            }
        });
        scrlForm.setViewportView(lstForm);

        panLstForm.add(scrlForm);

        panList.add(panLstForm);

        panForm.add(panList, java.awt.BorderLayout.LINE_START);

        panAddForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations d'une formation"));
        panAddForm.setLayout(new javax.swing.BoxLayout(panAddForm, javax.swing.BoxLayout.PAGE_AXIS));

        panFormNom.setMaximumSize(new java.awt.Dimension(99999999, 23));
        panFormNom.setPreferredSize(new java.awt.Dimension(54, 23));
        panFormNom.setLayout(new javax.swing.BoxLayout(panFormNom, javax.swing.BoxLayout.LINE_AXIS));

        lblFormNom.setText("Nom :");
        panFormNom.add(lblFormNom);

        txtFormNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormNomActionPerformed(evt);
            }
        });
        panFormNom.add(txtFormNom);

        panAddForm.add(panFormNom);

        panFormStag.setBorder(javax.swing.BorderFactory.createTitledBorder("Stagaires de la formation"));
        panFormStag.setName(""); // NOI18N
        panFormStag.setLayout(new javax.swing.BoxLayout(panFormStag, javax.swing.BoxLayout.PAGE_AXIS));

        sclFormStag.setMinimumSize(new java.awt.Dimension(500, 200));
        sclFormStag.setPreferredSize(new java.awt.Dimension(500, 200));

        tblFormStag.setModel(tblStagFormModel);
        sclFormStag.setViewportView(tblFormStag);

        panFormStag.add(sclFormStag);

        btnFormStagSuppr.setBackground(new java.awt.Color(242, 222, 222));
        btnFormStagSuppr.setForeground(new java.awt.Color(169, 68, 68));
        btnFormStagSuppr.setText("Supprimer");
        btnFormStagSuppr.setEnabled(false);
        panFormStagBtn.add(btnFormStagSuppr);

        btnFormStagAdd.setBackground(new java.awt.Color(223, 240, 216));
        btnFormStagAdd.setForeground(new java.awt.Color(69, 118, 61));
        btnFormStagAdd.setText("Ajouter");
        btnFormStagAdd.setEnabled(false);
        panFormStagBtn.add(btnFormStagAdd);

        panFormStag.add(panFormStagBtn);

        panAddForm.add(panFormStag);

        panFormECF.setBorder(javax.swing.BorderFactory.createTitledBorder("ECF de la formation"));
        panFormECF.setMinimumSize(new java.awt.Dimension(500, 200));
        panFormECF.setPreferredSize(new java.awt.Dimension(500, 200));
        panFormECF.setLayout(new java.awt.BorderLayout());

        panFormECFBtn.setMinimumSize(new java.awt.Dimension(500, 40));
        panFormECFBtn.setName(""); // NOI18N
        panFormECFBtn.setPreferredSize(new java.awt.Dimension(500, 40));
        panFormECFBtn.setRequestFocusEnabled(false);

        btnFormECFSuppr.setBackground(new java.awt.Color(242, 222, 222));
        btnFormECFSuppr.setForeground(new java.awt.Color(169, 68, 68));
        btnFormECFSuppr.setText("Supprimer");
        btnFormECFSuppr.setEnabled(false);
        panFormECFBtn.add(btnFormECFSuppr);

        btnFormECFRes.setBackground(new java.awt.Color(217, 237, 247));
        btnFormECFRes.setForeground(new java.awt.Color(49, 112, 143));
        btnFormECFRes.setText("Résultat");
        btnFormECFRes.setEnabled(false);
        panFormECFBtn.add(btnFormECFRes);

        btnFormECFAdd.setBackground(new java.awt.Color(223, 240, 216));
        btnFormECFAdd.setForeground(new java.awt.Color(69, 118, 61));
        btnFormECFAdd.setText("Ajouter");
        btnFormECFAdd.setEnabled(false);
        btnFormECFAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormECFAddActionPerformed(evt);
            }
        });
        panFormECFBtn.add(btnFormECFAdd);

        panFormECF.add(panFormECFBtn, java.awt.BorderLayout.PAGE_END);

        sclFormECF.setMinimumSize(new java.awt.Dimension(500, 200));
        sclFormECF.setPreferredSize(new java.awt.Dimension(500, 200));

        tblFormECF.setModel(tblECFModel);
        sclFormECF.setViewportView(tblFormECF);

        panFormECF.add(sclFormECF, java.awt.BorderLayout.CENTER);

        panAddForm.add(panFormECF);

        btnFormSuppr.setBackground(new java.awt.Color(242, 222, 222));
        btnFormSuppr.setForeground(new java.awt.Color(169, 68, 68));
        btnFormSuppr.setText("Supprimer");
        btnFormSuppr.setEnabled(false);
        panFormBtn.add(btnFormSuppr);

        btnFormAdd.setBackground(new java.awt.Color(223, 240, 216));
        btnFormAdd.setForeground(new java.awt.Color(69, 118, 61));
        btnFormAdd.setText("Ajouter");
        btnFormAdd.setEnabled(false);
        panFormBtn.add(btnFormAdd);

        panAddForm.add(panFormBtn);

        panForm.add(panAddForm, java.awt.BorderLayout.CENTER);

        tbpContent.addTab("Formations", panForm);

        getContentPane().add(tbpContent, java.awt.BorderLayout.PAGE_START);

        mnuFichier.setText("Fichier");

        itmActualiser.setText("Actualiser");
        itmActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmActualiserActionPerformed(evt);
            }
        });
        mnuFichier.add(itmActualiser);
        mnuFichier.add(itmSep);

        itmQuitter.setText("Quitter");
        mnuFichier.add(itmQuitter);

        mnuBar.add(mnuFichier);
        itmQuitter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmQuitterActionPerformed(evt);
            }
        });

        mnuAide.setText("?");

        itmApropos.setText("A propos");
        itmApropos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAproposActionPerformed(evt);
            }
        });
        mnuAide.add(itmApropos);

        mnuBar.add(mnuAide);

        setJMenuBar(mnuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmActualiserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmActualiserActionPerformed

    private void btnFormECFAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormECFAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFormECFAddActionPerformed

    private void txtFormNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormNomActionPerformed
        lstForm.setSelectedIndex(-1);
        ResetFormBtn();
    }//GEN-LAST:event_txtFormNomActionPerformed

    private void lstFormValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstFormValueChanged
        ResetFormBtn();
        int index = lstForm.getSelectedIndex();
        if (index > -1) {
            Formation f = lstFormModel.getFormation(index);

            txtFormNom.setText(f.getNom());
            tblStagFormModel.reset();
            for (Stagiaire s : f.getStagiaires()) {
                tblStagFormModel.add(s);
            }
            tblECFModel.reset();
            for (ECF e : f.getECFs()) {
                tblECFModel.add(e);
            }
        }
    }//GEN-LAST:event_lstFormValueChanged

    private void ResetFormBtn() {
        if (lstForm.getSelectedIndex() > -1) {
            btnFormECFAdd.setEnabled(true);
            btnFormStagAdd.setEnabled(true);
            if (tblFormECF.getSelectedRow() > -1) {
                btnFormECFSuppr.setEnabled(true);
                btnFormECFRes.setEnabled(true);
            } else {
                btnFormECFSuppr.setEnabled(false);
                btnFormECFRes.setEnabled(false);
            }
            if (tblFormStag.getSelectedRow() > -1) {
                btnFormStagSuppr.setEnabled(true);
            } else {
                btnFormStagSuppr.setEnabled(false);
            }
            btnFormAdd.setEnabled(false);
            btnFormSuppr.setEnabled(true);
        } else {
            tblECFModel.reset();
            tblStagFormModel.reset();

            txtFormNom.setText("");
            btnFormAdd.setEnabled(false);
            btnFormSuppr.setEnabled(false);

            btnFormECFAdd.setEnabled(false);
            btnFormStagAdd.setEnabled(false);
            btnFormECFSuppr.setEnabled(false);
            btnFormECFRes.setEnabled(false);
            btnFormStagSuppr.setEnabled(false);
        }
    }

    private void itmAproposActionPerformed(java.awt.event.ActionEvent evt) {
        Apropos aPropos = new Apropos(this, true);
        aPropos.setVisible(true);
    }

    private void itmQuitterActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFormAdd;
    private javax.swing.JButton btnFormECFAdd;
    private javax.swing.JButton btnFormECFRes;
    private javax.swing.JButton btnFormECFSuppr;
    private javax.swing.JButton btnFormStagAdd;
    private javax.swing.JButton btnFormStagSuppr;
    private javax.swing.JButton btnFormSuppr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem itmActualiser;
    private javax.swing.JMenuItem itmApropos;
    private javax.swing.JMenuItem itmQuitter;
    private javax.swing.JPopupMenu.Separator itmSep;
    private javax.swing.JLabel lblFormNom;
    private javax.swing.JList<String> lstForm;
    private javax.swing.JMenu mnuAide;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenu mnuFichier;
    private javax.swing.JPanel panAddForm;
    private javax.swing.JPanel panFooter;
    private javax.swing.JPanel panForm;
    private javax.swing.JPanel panFormBtn;
    private javax.swing.JPanel panFormECF;
    private javax.swing.JPanel panFormECFBtn;
    private javax.swing.JPanel panFormNom;
    private javax.swing.JPanel panFormStag;
    private javax.swing.JPanel panFormStagBtn;
    private javax.swing.JPanel panList;
    private javax.swing.JPanel panLstForm;
    private javax.swing.JPanel panStag;
    private javax.swing.JScrollPane sclFormECF;
    private javax.swing.JScrollPane sclFormStag;
    private javax.swing.JScrollPane scrlForm;
    private javax.swing.JTable tblFormECF;
    private javax.swing.JTable tblFormStag;
    private javax.swing.JTabbedPane tbpContent;
    private javax.swing.JTextField txtFooter;
    private javax.swing.JTextField txtFormNom;
    // End of variables declaration//GEN-END:variables
}
