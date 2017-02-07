package afpa.learning.moneyconverter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Afpa on 07/02/2017.
 */
public class Convert extends SQLiteOpenHelper {

    // Singleton
    private static Convert instance;
    public static Convert getInstance() {
        if (instance == null) {
            instance = new Convert();
        }
        return instance;
    }

    private static String DB_PATH = "/data/data/afpa.learning.moneyconverter/databases/";
    private static String DB_NAME = "MoneyConverter.db";
    private static String DB_TABLE = "monies";

    // Table de hashage des monnaies
    private Map<String, Double> monies = new HashMap<>();

    public Convert() {
        super(null, DB_NAME, null, 1);

        // Récupération des données de la base de donnée
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase bdd = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        Cursor c = bdd.query(DB_TABLE, new String[]{"id", "name", "rate"}, null, null, null, null, null);
        while(c.moveToNext()) {
            monies.put(c.getString(1), c.getDouble(2));
        }
        c.close();
        bdd.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
