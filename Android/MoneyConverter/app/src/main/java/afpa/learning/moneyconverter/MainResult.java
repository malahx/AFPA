package afpa.learning.moneyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import afpa.learning.moneyconverter.metier.Convert;

/**
 * Created by Afpa on 03/02/2017.
 */

public class MainResult extends defaultMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();
        String source = thisIntent.getExtras().getString("source");
        String cible = thisIntent.getExtras().getString("cible");
        double fltAmount = thisIntent.getExtras().getDouble("fltAmount");
        double amount = Math.round(Convert.convertir(source, cible, fltAmount) * 100) / 100;
        String value = fltAmount + " " + source + " = " + amount + " " + cible;
        //Toast.makeText(getBaseContext(), "Montant converti = " + amount, Toast.LENGTH_LONG).show();
        setContentView(R.layout.result);
        TextView lblResult = (TextView) findViewById(R.id.lblResult);
        lblResult.setText(value);
    }

    public void exitAction(View v) {
        finish();
    }
}
