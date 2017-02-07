package afpa.learning.moneyconverter;

import android.support.v7.app.AppCompatActivity;
import android.util.Xml;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Afpa on 06/02/2017.
 */

public class Utils {

    // Récupération des monnaies se trouvant dans res/raw/monies.xml
    public static List<String> getMonies(AppCompatActivity app) {
        List<String> monies = new ArrayList<String>();
        InputStream fic = app.getResources().openRawResource(R.raw.monies);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            document = dbf.newDocumentBuilder().parse(fic);
            for (int i = document.getElementsByTagName("money").getLength() -1 ; i>=0 ; i--) {
                monies.add(document.getElementsByTagName("money").item(i).getFirstChild().getNextSibling().getFirstChild().getNodeValue());
            }
            fic.close();
        } catch (Exception e) {
            System.out.println("Document monies.xml error");
        }
        return monies;
    }


    public static double getRate(AppCompatActivity app, String money) {
        System.out.println(money);
        InputStream fic = app.getResources().openRawResource(R.raw.monies);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            document = dbf.newDocumentBuilder().parse(fic);
            for (int i = document.getElementsByTagName("money").getLength() -1 ; i>=0 ; i--) {
                String name = document.getElementsByTagName("money").item(i).getFirstChild().getNextSibling().getFirstChild().getNodeValue();
                double rate = Double.parseDouble(document.getElementsByTagName("money").item(i).getLastChild().getPreviousSibling().getFirstChild().getNodeValue());
                if (name.equals(money)) {
                    return rate;
                }
            }
            fic.close();
        } catch (Exception e) {
            System.out.println("Document monies.xml error");
        }
        return 0;
    }
}
