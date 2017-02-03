package afpa.learning.moneyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import afpa.learning.moneyconverter.metier.Convert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Set<String> c = Convert.getConversionTable().keySet(); // Récupération de toutes les monnaies

        List<String> money = new ArrayList<String>(c); // Passage en liste (possible de le faire en tableau, mais on ne peut rien lui ajouter)

        money.add(0, this.getString(R.string.select)); // Ajout d'une valeur par défaut

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, money); // Création de l'adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Récupération des éléments spinner
        Spinner spnMoneyBegin = (Spinner) findViewById(R.id.spnMoneyBegin);
        Spinner spnMoneyEnd = (Spinner) findViewById(R.id.spnMoneyEnd);

        // Enregistrement des adapters
        spnMoneyBegin.setAdapter(adapter);
        spnMoneyEnd.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid()); // Tuer le processus
        super.onDestroy();
    }

    public void convertAction(View v) {
        Spinner spnMoneyBegin = (Spinner) findViewById(R.id.spnMoneyBegin);
        if (spnMoneyBegin.getSelectedItemId() == 0) {
            Toast.makeText(getBaseContext(), this.getString(R.string.moneyBeginInfo), Toast.LENGTH_LONG).show();
            return;
        }
        Spinner spnMoneyEnd = (Spinner) findViewById(R.id.spnMoneyEnd);
        if (spnMoneyEnd.getSelectedItemId() == 0) {
            Toast.makeText(getBaseContext(), this.getString(R.string.moneyEndInfo), Toast.LENGTH_LONG).show();
            return;
        }
        EditText txtAmount = (EditText) findViewById(R.id.txtAmount);
        String valueAmount = txtAmount.getText().toString();
        double fltAmount = 0;
        if (!valueAmount.isEmpty()) {
            try {
                fltAmount = Double.parseDouble(valueAmount);
            } catch (Exception e) {
            }
        }
        if (valueAmount.isEmpty() || fltAmount == 0) {
            Toast.makeText(getBaseContext(), this.getString(R.string.amountInfo), Toast.LENGTH_LONG).show();
            return;
        }
        String source = spnMoneyBegin.getSelectedItem().toString();
        String cible = spnMoneyEnd.getSelectedItem().toString();
        Intent intent = new Intent(this, MainResult.class);
        intent.putExtra("source", source);
        intent.putExtra("cible", cible);
        intent.putExtra("fltAmount", fltAmount);
        startActivity(intent);
    }

    public void exitAction(View v) {
        finish();
    }
}
