/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glehenaff.gestform.report;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.glehenaff.gestform.dao.ConnectDAO;

/**
 *
 * @author gwenole
 */
public class Resultats  {

    public static void generate() {
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(Resultats.class.getResource("ExoFo.jasper"));
            report.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "false");
            report.setProperty("net.sf.jasperreports.default.font.name=SansSerif", "true");

            JasperPrint jPrint = JasperFillManager.fillReport(report, null, ConnectDAO.Instance().get());
            JasperExportManager.exportReportToPdfFile(jPrint, "/tmp/Resultats.pdf");

        } catch (JRException ex) {
            Logger.getLogger(Resultats.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
