/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.glehenaff.gestform.GestForm;
import org.glehenaff.gestform.dao.FormationDAO;
import org.glehenaff.gestform.dao.AlreadyExistsException;
import org.glehenaff.gestform.dao.EcfDAO;
import org.glehenaff.gestform.dao.StagiaireDAO;
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
    private boolean disabledTextFields = false;

    /**
     * Creates new form Form
     */
    public Form() {
        lstFormModel = new FormListModel(GestForm.getFormations());
        tblStagModel = new StagTableModel(GestForm.getStagiaires());
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
        txtPreStag.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }
        });
        txtNomStag.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }
        });
        txtCodeStag.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtStagActionPerformed(null);
            }
        });
        txtNomEcf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtNomEcfActionPerformed(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtNomEcfActionPerformed(null);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtNomEcfActionPerformed(null);
            }
        });
        tblFormECF.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                tblFormECFValueChanged(evt);
            }
        });
        tblFormStag.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                tblFormStagValueChanged(evt);
            }
        });
        tblStag.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                tblStagValueChanged(evt);
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
        panInfoStag = new javax.swing.JPanel();
        panCodeNomStag = new javax.swing.JPanel();
        panNomStag = new javax.swing.JPanel();
        lblNomStag = new javax.swing.JLabel();
        txtNomStag = new javax.swing.JTextField();
        panCodeStag = new javax.swing.JPanel();
        lblCodeStag = new javax.swing.JLabel();
        txtCodeStag = new javax.swing.JTextField();
        panPreBtnStag = new javax.swing.JPanel();
        panPreStag = new javax.swing.JPanel();
        lblPreStag = new javax.swing.JLabel();
        txtPreStag = new javax.swing.JTextField();
        panBtnStag = new javax.swing.JPanel();
        btnSupprStag = new javax.swing.JButton();
        btnAddStag = new javax.swing.JButton();
        panListStag = new javax.swing.JPanel();
        sclStag = new javax.swing.JScrollPane();
        tblStag = new javax.swing.JTable();
        panForm = new javax.swing.JPanel();
        panList = new javax.swing.JPanel();
        panLstForm = new javax.swing.JPanel();
        scrlForm = new javax.swing.JScrollPane();
        lstForm = new javax.swing.JList<>();
        panInfoForm = new javax.swing.JPanel();
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
        sclFormECF = new javax.swing.JScrollPane();
        tblFormECF = new javax.swing.JTable();
        panFormECFBtn = new javax.swing.JPanel();
        btnFormECFSuppr = new javax.swing.JButton();
        btnFormECFRes = new javax.swing.JButton();
        panAddEcf = new javax.swing.JPanel();
        lblNomEcf = new javax.swing.JLabel();
        txtNomEcf = new javax.swing.JTextField();
        btnAddEcf = new javax.swing.JButton();
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
        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(750, 600));
        setResizable(false);

        panFooter.setLayout(new javax.swing.BoxLayout(panFooter, javax.swing.BoxLayout.LINE_AXIS));

        txtFooter.setEditable(false);
        txtFooter.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFooter.setText("Gestionnaire de formations");
        txtFooter.setName(""); // NOI18N
        panFooter.add(txtFooter);

        getContentPane().add(panFooter, java.awt.BorderLayout.PAGE_END);

        panStag.setLayout(new java.awt.BorderLayout());

        panInfoStag.setBorder(javax.swing.BorderFactory.createTitledBorder("Donnée d'un stagiaire"));
        panInfoStag.setMinimumSize(new java.awt.Dimension(800, 100));

        panCodeNomStag.setMinimumSize(new java.awt.Dimension(400, 23));
        panCodeNomStag.setPreferredSize(new java.awt.Dimension(580, 30));
        panCodeNomStag.setLayout(new java.awt.GridLayout(1, 2, 100, 0));

        panNomStag.setLayout(new javax.swing.BoxLayout(panNomStag, javax.swing.BoxLayout.LINE_AXIS));

        lblNomStag.setText("Nom :");
        lblNomStag.setPreferredSize(new java.awt.Dimension(75, 15));
        panNomStag.add(lblNomStag);
        panNomStag.add(txtNomStag);

        panCodeNomStag.add(panNomStag);

        panCodeStag.setLayout(new javax.swing.BoxLayout(panCodeStag, javax.swing.BoxLayout.LINE_AXIS));

        lblCodeStag.setText("Code :");
        lblCodeStag.setPreferredSize(new java.awt.Dimension(75, 15));
        panCodeStag.add(lblCodeStag);
        panCodeStag.add(txtCodeStag);

        panCodeNomStag.add(panCodeStag);

        panInfoStag.add(panCodeNomStag);

        panPreBtnStag.setPreferredSize(new java.awt.Dimension(580, 30));
        panPreBtnStag.setLayout(new java.awt.GridLayout(1, 2, 100, 0));

        panPreStag.setLayout(new javax.swing.BoxLayout(panPreStag, javax.swing.BoxLayout.LINE_AXIS));

        lblPreStag.setText("Prénom :");
        lblPreStag.setPreferredSize(new java.awt.Dimension(75, 15));
        panPreStag.add(lblPreStag);
        panPreStag.add(txtPreStag);

        panPreBtnStag.add(panPreStag);

        panBtnStag.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        btnSupprStag.setBackground(new java.awt.Color(242, 222, 222));
        btnSupprStag.setForeground(new java.awt.Color(169, 68, 68));
        btnSupprStag.setText("Supprimer");
        btnSupprStag.setEnabled(false);
        btnSupprStag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprStagActionPerformed(evt);
            }
        });
        panBtnStag.add(btnSupprStag);

        btnAddStag.setBackground(new java.awt.Color(223, 240, 216));
        btnAddStag.setForeground(new java.awt.Color(69, 118, 61));
        btnAddStag.setText("Ajouter");
        btnAddStag.setEnabled(false);
        btnAddStag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStagActionPerformed(evt);
            }
        });
        panBtnStag.add(btnAddStag);

        panPreBtnStag.add(panBtnStag);

        panInfoStag.add(panPreBtnStag);

        panStag.add(panInfoStag, java.awt.BorderLayout.CENTER);

        panListStag.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste de stagiaire"));
        panListStag.setPreferredSize(new java.awt.Dimension(550, 430));
        panListStag.setLayout(new java.awt.BorderLayout());

        sclStag.setMaximumSize(new java.awt.Dimension(0, 0));
        sclStag.setMinimumSize(new java.awt.Dimension(0, 0));
        sclStag.setPreferredSize(new java.awt.Dimension(400, 200));

        tblStag.setModel(tblStagModel);
        tblStag.setPreferredSize(new java.awt.Dimension(580, 400));
        sclStag.setViewportView(tblStag);

        panListStag.add(sclStag, java.awt.BorderLayout.CENTER);

        panStag.add(panListStag, java.awt.BorderLayout.NORTH);

        tbpContent.addTab("Stagiaires", panStag);

        panForm.setLayout(new java.awt.BorderLayout(5, 5));

        panList.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste de formation"));
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

        panInfoForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Donnée d'une formation"));
        panInfoForm.setLayout(new javax.swing.BoxLayout(panInfoForm, javax.swing.BoxLayout.PAGE_AXIS));

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

        panInfoForm.add(panFormNom);

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
        btnFormStagSuppr.setText("Supprimer stagiaire");
        btnFormStagSuppr.setEnabled(false);
        btnFormStagSuppr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormStagSupprActionPerformed(evt);
            }
        });
        panFormStagBtn.add(btnFormStagSuppr);

        btnFormStagAdd.setBackground(new java.awt.Color(223, 240, 216));
        btnFormStagAdd.setForeground(new java.awt.Color(69, 118, 61));
        btnFormStagAdd.setText("Ajouter stagiaire");
        btnFormStagAdd.setEnabled(false);
        btnFormStagAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormStagAddActionPerformed(evt);
            }
        });
        panFormStagBtn.add(btnFormStagAdd);

        panFormStag.add(panFormStagBtn);

        panInfoForm.add(panFormStag);

        panFormECF.setBorder(javax.swing.BorderFactory.createTitledBorder("ECF de la formation"));
        panFormECF.setMinimumSize(new java.awt.Dimension(500, 200));
        panFormECF.setPreferredSize(new java.awt.Dimension(500, 200));
        panFormECF.setLayout(new java.awt.BorderLayout());

        sclFormECF.setMinimumSize(new java.awt.Dimension(500, 100));
        sclFormECF.setPreferredSize(new java.awt.Dimension(500, 100));

        tblFormECF.setModel(tblECFModel);
        sclFormECF.setViewportView(tblFormECF);

        panFormECF.add(sclFormECF, java.awt.BorderLayout.NORTH);

        panFormECFBtn.setMinimumSize(new java.awt.Dimension(500, 40));
        panFormECFBtn.setName(""); // NOI18N
        panFormECFBtn.setPreferredSize(new java.awt.Dimension(500, 40));
        panFormECFBtn.setRequestFocusEnabled(false);

        btnFormECFSuppr.setBackground(new java.awt.Color(242, 222, 222));
        btnFormECFSuppr.setForeground(new java.awt.Color(169, 68, 68));
        btnFormECFSuppr.setText("Supprimer ECF");
        btnFormECFSuppr.setEnabled(false);
        btnFormECFSuppr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormECFSupprActionPerformed(evt);
            }
        });
        panFormECFBtn.add(btnFormECFSuppr);

        btnFormECFRes.setBackground(new java.awt.Color(217, 237, 247));
        btnFormECFRes.setForeground(new java.awt.Color(49, 112, 143));
        btnFormECFRes.setText("Résultat");
        btnFormECFRes.setEnabled(false);
        panFormECFBtn.add(btnFormECFRes);

        panFormECF.add(panFormECFBtn, java.awt.BorderLayout.CENTER);

        panAddEcf.setLayout(new javax.swing.BoxLayout(panAddEcf, javax.swing.BoxLayout.LINE_AXIS));

        lblNomEcf.setText("Nom :");
        panAddEcf.add(lblNomEcf);
        panAddEcf.add(txtNomEcf);

        btnAddEcf.setBackground(new java.awt.Color(223, 240, 216));
        btnAddEcf.setForeground(new java.awt.Color(69, 118, 61));
        btnAddEcf.setText("Ajouter ECF");
        btnAddEcf.setEnabled(false);
        btnAddEcf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEcfActionPerformed(evt);
            }
        });
        panAddEcf.add(btnAddEcf);

        panFormECF.add(panAddEcf, java.awt.BorderLayout.SOUTH);

        panInfoForm.add(panFormECF);

        btnFormSuppr.setBackground(new java.awt.Color(242, 222, 222));
        btnFormSuppr.setForeground(new java.awt.Color(169, 68, 68));
        btnFormSuppr.setText("Supprimer formation");
        btnFormSuppr.setEnabled(false);
        btnFormSuppr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormSupprActionPerformed(evt);
            }
        });
        panFormBtn.add(btnFormSuppr);

        btnFormAdd.setBackground(new java.awt.Color(223, 240, 216));
        btnFormAdd.setForeground(new java.awt.Color(69, 118, 61));
        btnFormAdd.setText("Ajouter formation");
        btnFormAdd.setEnabled(false);
        btnFormAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormAddActionPerformed(evt);
            }
        });
        panFormBtn.add(btnFormAdd);

        panInfoForm.add(panFormBtn);

        panForm.add(panInfoForm, java.awt.BorderLayout.CENTER);

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
        RefreshData();
    }//GEN-LAST:event_itmActualiserActionPerformed

    public void RefreshData() {
        GestForm.RefreshData();
        lstFormModel.set(GestForm.getFormations());
        tblStagModel.set(GestForm.getStagiaires());
    }

    private void tblStagValueChanged(javax.swing.event.ListSelectionEvent evt) {
        ResetStagBtn();
        int index = tblStag.getSelectedRow();
        if (index > -1) {
            Stagiaire s = tblStagModel.getStagiaire(index);
            disabledTextFields = true;
            txtNomStag.setText(s.getNom());
            txtPreStag.setText(s.getPrenom());
            txtCodeStag.setText(s.getCode());
            disabledTextFields = false;
        }
    }

    private void txtStagActionPerformed(java.awt.event.ActionEvent evt) {
        if (disabledTextFields) {
            return;
        }
        int index = tblStag.getSelectedRow();
        if (index > -1) {
            tblStag.clearSelection();
        } else {
            ResetStagBtn();
        }
    }

    private void ResetStagBtn() {
        int index = tblStag.getSelectedRow();
        if (index > -1) {
            btnAddStag.setEnabled(false);
            btnSupprStag.setEnabled(true);
        } else {
            if (txtNomStag.getText().isEmpty() || txtPreStag.getText().isEmpty() || txtCodeStag.getText().isEmpty()) {
                btnAddStag.setEnabled(false);
            } else {
                btnAddStag.setEnabled(true);
            }
            btnSupprStag.setEnabled(false);
        }
    }

    private void btnAddEcfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEcfActionPerformed
        int indexForm = lstForm.getSelectedIndex();
        int indexEcf = tblFormECF.getSelectedRow();
        if (txtNomEcf.getText().isEmpty()) {
            txtFooter.setText("Le champ texte est vide");
            return;
        }
        if (indexEcf > -1) {
            txtFooter.setText("Un ECF est selectionné");
            return;
        }
        if (indexForm == -1) {
            txtFooter.setText("Aucune formation n'est sélectionnée");
            return;
        }
        Formation f = lstFormModel.getFormation(indexForm);
        ECF ecf = new ECF(0, f, txtNomEcf.getText());
        try {
            ecf = EcfDAO.Instance().insert(ecf);
            tblECFModel.add(ecf);
            tblFormECF.setRowSelectionInterval(tblECFModel.getRowCount() -1, tblECFModel.getRowCount() -1);
            ResetFormBtn();
        } catch (AlreadyExistsException e) {
            txtFooter.setText("L'ECF semble déjà exister");
        }
    }//GEN-LAST:event_btnAddEcfActionPerformed

    private void txtFormNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormNomActionPerformed
        if (disabledTextFields) {
            return;
        }
        int index = lstForm.getSelectedIndex();
        if (index > -1) {
            lstForm.clearSelection();
        } else {
            ResetFormBtn();
        }
    }//GEN-LAST:event_txtFormNomActionPerformed

    private void lstFormValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstFormValueChanged
        ResetFormBtn();
        int index = lstForm.getSelectedIndex();
        tblStagFormModel.reset();
        tblECFModel.reset();
        if (index > -1) {
            Formation f = lstFormModel.getFormation(index);
            disabledTextFields = true;
            txtFormNom.setText(f.getNom());
            disabledTextFields = false;
            for (Stagiaire s : f.getStagiaires()) {
                tblStagFormModel.add(s);
            }
            for (ECF e : f.getECFs()) {
                tblECFModel.add(e);
            }
        }
    }//GEN-LAST:event_lstFormValueChanged

    private void tblFormECFValueChanged(javax.swing.event.ListSelectionEvent evt) {
        ResetFormBtn();
        int index = tblFormECF.getSelectedRow();
        if (index > -1) {
            ECF ecf = tblECFModel.getEcf(index);
            disabledTextFields = true;
            txtNomEcf.setText(ecf.getNom());
            disabledTextFields = false;
        }
    }

    private void txtNomEcfActionPerformed(java.awt.event.ActionEvent evt) {
        if (disabledTextFields) {
            return;
        }
        int index = tblFormECF.getSelectedRow();
        if (index > -1) {
            tblFormECF.clearSelection();
        } else {
            ResetFormBtn();
        }
    }

    private void tblFormStagValueChanged(javax.swing.event.ListSelectionEvent evt) {
        ResetFormBtn();
    }

    private void btnFormAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormAddActionPerformed
        int index = lstForm.getSelectedIndex();
        if (txtFormNom.getText().isEmpty()) {
            txtFooter.setText("Le champ texte est vide");
            return;
        }
        if (index > -1) {
            txtFooter.setText("Une formation est selectionnée");
            return;
        }
        Formation f = new Formation(0, txtFormNom.getText());
        try {
            f = FormationDAO.Instance().insert(f);
            lstFormModel.add(f);
            lstForm.setSelectedIndex(lstFormModel.getSize() - 1);
            ResetFormBtn();
        } catch (AlreadyExistsException e) {
            txtFooter.setText("La formation semble déjà exister");
        }
    }//GEN-LAST:event_btnFormAddActionPerformed

    private void btnFormSupprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormSupprActionPerformed
        int index = lstForm.getSelectedIndex();
        if (index == -1) {
            txtFooter.setText("Aucune formation n'est selectionnée");
            return;
        }
        Formation f = lstFormModel.getFormation(index);
        if (FormationDAO.Instance().delete(f)) {
            lstFormModel.remove(f);
            lstForm.clearSelection();
        } else {
            txtFooter.setText("La formation n'a pas pu être supprimée");
        }
    }//GEN-LAST:event_btnFormSupprActionPerformed

    private void btnFormStagSupprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormStagSupprActionPerformed
        int indexForm = lstForm.getSelectedIndex();
        int indexStag = tblFormStag.getSelectedRow();
        if (indexForm == -1) {
            txtFooter.setText("Aucune formation n'est selectionnée");
            return;
        }
        if (indexStag == -1) {
            txtFooter.setText("Aucun stagiaire n'est selectionné");
            return;
        }
        Formation f = lstFormModel.getFormation(indexForm);
        Stagiaire s = tblStagFormModel.getStagiaire(indexStag);
        if (FormationDAO.Instance().delete(f, s)) {
            tblStagFormModel.remove(s);
        } else {
            txtFooter.setText("Le stagiaire n'a pas pu être supprimé de la formation");
        }
    }//GEN-LAST:event_btnFormStagSupprActionPerformed

    private void btnFormECFSupprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormECFSupprActionPerformed
        int indexEcf = tblFormECF.getSelectedRow();
        if (indexEcf == -1) {
            txtFooter.setText("Aucun ECF n'est selectionné");
            return;
        }
        ECF ecf = tblECFModel.getEcf(indexEcf);
        if (EcfDAO.Instance().delete(ecf)) {
            tblECFModel.remove(ecf);
        } else {
            txtFooter.setText("Le stagiaire n'a pas pu être supprimé de la formation");
        }
    }//GEN-LAST:event_btnFormECFSupprActionPerformed

    private void btnAddStagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStagActionPerformed
        int index = tblStag.getSelectedRow();
        if (txtNomStag.getText().isEmpty() || txtCodeStag.getText().isEmpty()) {
            txtFooter.setText("Il manque le nom du stagiaire");
            return;
        }
        if (txtPreStag.getText().isEmpty()) {
            txtFooter.setText("Il manque le prénom du stagiaire");
            return;
        }
        if (txtCodeStag.getText().isEmpty()) {
            txtFooter.setText("Il manque le code du stagiaire");
            return;
        }
        if (index > -1) {
            txtFooter.setText("Un stagiaire est selectionné");
            return;
        }
        String nom = txtNomStag.getText();
        String prenom = txtPreStag.getText();
        String code = txtCodeStag.getText();
        Stagiaire s = new Stagiaire(0, nom, prenom, code);
        try {
            s = StagiaireDAO.Instance().insert(s);
            tblStagModel.add(s);
            tblStag.setRowSelectionInterval(tblStagModel.getRowCount() - 1, tblStagModel.getRowCount() - 1);
            ResetFormBtn();
        } catch (AlreadyExistsException e) {
            txtFooter.setText("La formation semble déjà exister");
        }
    }//GEN-LAST:event_btnAddStagActionPerformed

    private void btnSupprStagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprStagActionPerformed
        int index = tblStag.getSelectedRow();
        if (index == -1) {
            txtFooter.setText("Aucun stagiaire n'est selectionnée");
            return;
        }
        Stagiaire s = tblStagModel.getStagiaire(index);
        if (StagiaireDAO.Instance().delete(s)) {
            tblStagModel.remove(s);
            tblStag.clearSelection();
        } else {
            txtFooter.setText("Le stagiaire n'a pas pu être supprimé");
        }
    }//GEN-LAST:event_btnSupprStagActionPerformed

    private void btnFormStagAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormStagAddActionPerformed
        int indexForm = lstForm.getSelectedIndex();
        if (indexForm == -1) {
            txtFooter.setText("Aucune formation n'est selectionnée");
            return;
        }
        Formation f = lstFormModel.getFormation(indexForm);
        AddStagToForm addStag = new AddStagToForm(this, true, f, tblStagFormModel);
        addStag.setVisible(true);
    }//GEN-LAST:event_btnFormStagAddActionPerformed

    private void ResetFormBtn() {
        int index = lstForm.getSelectedIndex();
        if (index > -1) {
            txtNomEcf.setEnabled(true);
            if (txtNomEcf.getText().isEmpty() || tblFormECF.getSelectedRow() > -1) {
                btnAddEcf.setEnabled(false);
            } else {
                btnAddEcf.setEnabled(true);
            }
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
            if (txtFormNom.getText().isEmpty()) {
                btnFormAdd.setEnabled(false);
            } else {
                btnFormAdd.setEnabled(true);
            }
            btnFormSuppr.setEnabled(false);
            txtNomEcf.setEnabled(false);
            btnAddEcf.setEnabled(false);
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
    private javax.swing.JButton btnAddEcf;
    private javax.swing.JButton btnAddStag;
    private javax.swing.JButton btnFormAdd;
    private javax.swing.JButton btnFormECFRes;
    private javax.swing.JButton btnFormECFSuppr;
    private javax.swing.JButton btnFormStagAdd;
    private javax.swing.JButton btnFormStagSuppr;
    private javax.swing.JButton btnFormSuppr;
    private javax.swing.JButton btnSupprStag;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem itmActualiser;
    private javax.swing.JMenuItem itmApropos;
    private javax.swing.JMenuItem itmQuitter;
    private javax.swing.JPopupMenu.Separator itmSep;
    private javax.swing.JLabel lblCodeStag;
    private javax.swing.JLabel lblFormNom;
    private javax.swing.JLabel lblNomEcf;
    private javax.swing.JLabel lblNomStag;
    private javax.swing.JLabel lblPreStag;
    private javax.swing.JList<String> lstForm;
    private javax.swing.JMenu mnuAide;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenu mnuFichier;
    private javax.swing.JPanel panAddEcf;
    private javax.swing.JPanel panBtnStag;
    private javax.swing.JPanel panCodeNomStag;
    private javax.swing.JPanel panCodeStag;
    private javax.swing.JPanel panFooter;
    private javax.swing.JPanel panForm;
    private javax.swing.JPanel panFormBtn;
    private javax.swing.JPanel panFormECF;
    private javax.swing.JPanel panFormECFBtn;
    private javax.swing.JPanel panFormNom;
    private javax.swing.JPanel panFormStag;
    private javax.swing.JPanel panFormStagBtn;
    private javax.swing.JPanel panInfoForm;
    private javax.swing.JPanel panInfoStag;
    private javax.swing.JPanel panList;
    private javax.swing.JPanel panListStag;
    private javax.swing.JPanel panLstForm;
    private javax.swing.JPanel panNomStag;
    private javax.swing.JPanel panPreBtnStag;
    private javax.swing.JPanel panPreStag;
    private javax.swing.JPanel panStag;
    private javax.swing.JScrollPane sclFormECF;
    private javax.swing.JScrollPane sclFormStag;
    private javax.swing.JScrollPane sclStag;
    private javax.swing.JScrollPane scrlForm;
    private javax.swing.JTable tblFormECF;
    private javax.swing.JTable tblFormStag;
    private javax.swing.JTable tblStag;
    private javax.swing.JTabbedPane tbpContent;
    private javax.swing.JTextField txtCodeStag;
    private javax.swing.JTextField txtFooter;
    private javax.swing.JTextField txtFormNom;
    private javax.swing.JTextField txtNomEcf;
    private javax.swing.JTextField txtNomStag;
    private javax.swing.JTextField txtPreStag;
    // End of variables declaration//GEN-END:variables
}
