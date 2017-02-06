package afpa.learning.moneyconverter;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Afpa on 06/02/2017.
 */

public class DefaultMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmAbout:
                Toast.makeText(getBaseContext(), this.getString(R.string.aboutTxt), Toast.LENGTH_LONG).show();
                return true;
            case R.id.itmLang:
                Intent lang = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(lang);
                return true;
            case R.id.itmDisplay:
                Intent display = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                startActivity(display);
                return true;
            case R.id.itmClear:
                finish();
                Intent clear = new Intent(this, this.getClass());
                clear.putExtra("theme", R.style.AppTheme);
                startActivity(clear);
                return true;
            case R.id.itmDark:
                finish();
                Intent dark = new Intent(this, this.getClass());
                dark.putExtra("theme", R.style.AppTheme2);
                startActivity(dark);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void exitMenu(MenuItem m) {
        finish();
    }
}
