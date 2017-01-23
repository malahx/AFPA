package org.glehenaff.exogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class App extends JFrame {

	public static void main(String[] args) {
		App app = new App();
	}

    public App() {
        this.setTitle("TinyTinyEditor");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pan = (JPanel) this.getContentPane();
        
        JPanel panHeader = new JPanel();
        panHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblExplorer = new JLabel ("Explorer");
        JComboBox cboFile = new JComboBox ();
        cboFile.setPreferredSize(new Dimension(200, 20));
        //ImageIcon icoFolder = new ImageIcon("water.bmp");
        UIDefaults defaults = UIManager.getDefaults( );
        Icon icoFolder   = defaults.getIcon( "FileView.directoryIcon" );
        JButton btnFolder = new JButton (icoFolder);
        Icon icoNew   = defaults.getIcon( "Tree.leafIcon" );
        JButton btnNew = new JButton (icoNew);
        Icon icoHelp   = defaults.getIcon( "OptionPane.informationIcon" );
        JButton btnHelp = new JButton (icoHelp);
        panHeader.add(lblExplorer);
        panHeader.add(cboFile);
        panHeader.add(btnFolder);
        panHeader.add(btnNew);
        panHeader.add(btnHelp);
        
        JPanel panMiddle = new JPanel();
        JTextArea txtMsg = new JTextArea ();
        txtMsg.setColumns(35);
        txtMsg.setRows(10);
        txtMsg.setLineWrap(true);
        txtMsg.setWrapStyleWord(true);
        JScrollPane scrlPan = new JScrollPane(txtMsg, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrlBar = scrlPan.getVerticalScrollBar();
        scrlBar.setPreferredSize(new Dimension(20, 0));
        panMiddle.add(scrlPan);
        
        JPanel panFooter = new JPanel();
        panFooter.setLayout(new GridLayout(2,1));

        JPanel panFooterA = new JPanel();
        panFooterA.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblName = new JLabel ("Nom:");
        TextField txtName = new TextField ("Le Dormeur du Val");
        txtName.setColumns(35); 
        JButton btnOpen = new JButton ("Ouvrir");
        panFooterA.add(lblName);
        panFooterA.add(txtName);
        panFooterA.add(btnOpen);
        
        JPanel panFooterB = new JPanel();
        panFooterB.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblType = new JLabel ("Type:");
        JComboBox cboType = new JComboBox ();
        cboType.setPreferredSize(new Dimension(275, 20));
        JButton btnCancel = new JButton ("Annuler");
        panFooterB.add(lblType);
        panFooterB.add(cboType);
        panFooterB.add(btnCancel);
        
        panFooter.add(panFooterA);
        panFooter.add(panFooterB);
        
        pan.add(panHeader, BorderLayout.PAGE_START);
        pan.add(panMiddle, BorderLayout.CENTER);
        pan.add(panFooter, BorderLayout.PAGE_END);
        this.pack();
        this.setVisible(true);
        
    }
	
}
