package afpa.learning.moneyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    public void convertAction(View v) {
        Spinner spnMoneyBegin = (Spinner) findViewById(R.id.spnMoneyBegin);
        if (spnMoneyBegin.getSelectedItemId() == 0) {
            Toast.makeText(getBaseContext(), "Saisissez une monnaie de départ", Toast.LENGTH_LONG).show();
            return;
        }
        Spinner spnMoneyEnd = (Spinner) findViewById(R.id.spnMoneyEnd);
        if (spnMoneyEnd.getSelectedItemId() == 0) {
            Toast.makeText(getBaseContext(), "Saisissez une monnaie d'arrivée", Toast.LENGTH_LONG).show();
            return;
        }
        EditText txtAmount = (EditText) findViewById(R.id.txtAmount);
        String valueAmount = txtAmount.getText().toString();
        float fltAmount = 0;
        if (!valueAmount.isEmpty()) {
            try {
                fltAmount = Integer.parseInt(valueAmount);
            } catch (Exception e) {
            }
        }
        if (valueAmount.isEmpty() || fltAmount == 0) {
            Toast.makeText(getBaseContext(), "Saisissez un montant à convertir", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void exitAction(View v) {
        finish();
    }
}
