package afpa.learning.moneyconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends DefaultMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();

        SharedPreferences shared = getSharedPreferences("moneyconverter", Context.MODE_PRIVATE);

        int theme = shared.getInt("theme", 0);
        try {
            theme = thisIntent.getExtras().getInt("theme");
        } catch (Exception e) {
            System.out.println("No theme found");
        }
        if (theme != R.style.AppTheme && theme != R.style.AppTheme2) {
            theme = R.style.AppTheme;
        }
        setTheme(theme);

        setContentView(R.layout.activity_main);

        List<String> monies = Convert.getInstance().getMonies(); // Récupération de toutes les monnaies

        monies.add(0, this.getString(R.string.select)); // Ajout d'une valeur par défaut

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monies); // Création de l'adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Récupération des éléments spinner
        Spinner spnMoneyBegin = (Spinner) findViewById(R.id.spnMoneyBegin);
        Spinner spnMoneyEnd = (Spinner) findViewById(R.id.spnMoneyEnd);

        // Enregistrement des adapters
        spnMoneyBegin.setAdapter(adapter);
        spnMoneyEnd.setAdapter(adapter);

        // Donné enregistré
        int source = (int)shared.getLong("source", 0);
        int target = (int)shared.getLong("cible", 0);
        String amount = shared.getString("amount", "");
        spnMoneyBegin.setSelection(source);
        spnMoneyEnd.setSelection(target);
        EditText txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtAmount.setText(amount);

        registerForContextMenu((ImageView)findViewById(R.id.imgSettings));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmLang:
                Intent lang = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(lang);
                return true;
            case R.id.itmDisplay:
                Intent display = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                startActivity(display);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
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
                System.out.println("Can't parse amount");
            }
        }
        if (valueAmount.isEmpty() || fltAmount == 0) {
            Toast.makeText(getBaseContext(), this.getString(R.string.amountInfo), Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences("moneyconverter", Context.MODE_PRIVATE).edit();
        edit.putLong("source", spnMoneyBegin.getSelectedItemId());
        edit.putLong("target", spnMoneyEnd.getSelectedItemId());
        edit.putString("amount", valueAmount);
        edit.apply();
        String source = spnMoneyBegin.getSelectedItem().toString();
        String target = spnMoneyEnd.getSelectedItem().toString();
        Intent intent = new Intent(this, MainResult.class);
        intent.putExtra("source", source);
        intent.putExtra("target", target);
        intent.putExtra("fltAmount", fltAmount);
        startActivity(intent);
    }

    public void exitAction(View v) {
        finish();
    }
}
