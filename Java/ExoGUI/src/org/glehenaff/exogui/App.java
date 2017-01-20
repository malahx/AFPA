package org.glehenaff.exogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        JButton btnFolder = new JButton ("A");
        JButton btnNew = new JButton ("B");
        JButton btnHelp = new JButton ("C");
        panHeader.add(lblExplorer);
        panHeader.add(cboFile);
        panHeader.add(btnFolder);
        panHeader.add(btnNew);
        panHeader.add(btnHelp);
        JPanel panMiddle = new JPanel();
        JPanel panFooterA = new JPanel();
        JPanel panFooterB = new JPanel();
        pan.add(panHeader, BorderLayout.PAGE_START);
        pan.add(panMiddle, BorderLayout.CENTER);
        pan.add(panFooterA, BorderLayout.PAGE_END);
        pan.add(panFooterB, BorderLayout.PAGE_END);
        this.pack();
        this.setVisible(true);
        
    }
	
}
