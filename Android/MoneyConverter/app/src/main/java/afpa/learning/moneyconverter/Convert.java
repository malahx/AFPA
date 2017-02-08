package afpa.learning.moneyconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Afpa on 07/02/2017.
 */
public class Convert {

    // Singleton
    private static Convert instance = new Convert();

    public static Convert getInstance() {
        if (instance == null) {
            instance = new Convert();
        }
        return instance;
    }


    // Table de hashage des monnaies
    private Map<String, Double> monies = new HashMap<>();

    public Convert() {
        Data data = new Data();
        data.execute();
        try {
            monies = data.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Calcul de conversion
    public double calc(String source, String target, double amount) {
        return Math.round(100d * amount * monies.get(target) / monies.get(source)) / 100d;
    }

    // Liste des monnaies
    public List<String> getMonies() {
        return new ArrayList<String>(monies.keySet());
    }
}