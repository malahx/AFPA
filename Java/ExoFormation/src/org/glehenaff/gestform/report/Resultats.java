/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.report;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.glehenaff.gestform.dao.ConnectDAO;
import org.glehenaff.gestform.view.Form;

/**
 *
 * @author gwenole
 */
public class Resultats {

    private static JasperPrint generate() {
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(Resultats.class.getResource("ExoFo.jasper"));
            report.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "false");
            report.setProperty("net.sf.jasperreports.default.font.name=SansSerif", "true");
            JasperPrint jPrint = JasperFillManager.fillReport(report, null, ConnectDAO.Instance().get());
            return jPrint;
        } catch (JRException ex) {
            Logger.getLogger(Resultats.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void view() {
        JasperPrint jPrint = generate();
        JFrame pdfFrame = new JFrame("Rapport");
        pdfFrame.getContentPane().add(new JRViewer(jPrint));
        pdfFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pdfFrame.setSize((int) (screenSize.getWidth() / 3), (int) (screenSize.getHeight() - 100));
        pdfFrame.setLocationRelativeTo(null);
        pdfFrame.setVisible(true);
    }

    public static void save(Form parent) {
        JasperPrint jPrint = generate();
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            String fileName = path.substring(path.length() -5) != ".pdf" ? path + ".pdf" : path;
            try {
                JasperExportManager.exportReportToPdfFile(jPrint, fileName);
            } catch (JRException ex) {
                Logger.getLogger(Resultats.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
