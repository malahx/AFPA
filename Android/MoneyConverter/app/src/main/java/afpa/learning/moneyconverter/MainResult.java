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
        String target = thisIntent.getExtras().getString("target");
        double fltAmount = thisIntent.getExtras().getDouble("fltAmount");
        double amount = Convert.getInstance().calc(source, target, fltAmount);
        String value = fltAmount + " " + source + " = " + amount + " " + target;
        setContentView(R.layout.result);
        TextView lblResult = (TextView) findViewById(R.id.lblResult);
        lblResult.setText(value);
    }

    public void exitAction(View v) {
        finish();
    }
}
