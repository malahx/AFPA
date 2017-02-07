package afpa.learning.moneyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Afpa on 03/02/2017.
 */

public class MainResult extends DefaultMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();
        String source = thisIntent.getExtras().getString("source");
        String cible = thisIntent.getExtras().getString("cible");
        double fltAmount = thisIntent.getExtras().getDouble("fltAmount");
        double rate = Utils.getRate(this, cible)/Utils.getRate(this, source);
        double amount = Math.round(rate * fltAmount * 100)/100d;
        String value = fltAmount + " " + source + " = " + amount + " " + cible;
        setContentView(R.layout.result);
        TextView lblResult = (TextView) findViewById(R.id.lblResult);
        lblResult.setText(value);
    }

    public void exitAction(View v) {
        finish();
    }
}
