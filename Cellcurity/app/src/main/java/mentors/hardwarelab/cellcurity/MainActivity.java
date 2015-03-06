package mentors.hardwarelab.cellcurity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    boolean system_setting_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button armButton = (Button) findViewById(R.id.armButton);
        View.OnClickListener armListener = new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                system_setting_status = true;
                TextView SystemStatus = (TextView) findViewById(R.id.SystemStatus);
                SystemStatus.setText("System Armed");
            }
        };
        armButton.setOnClickListener(armListener);

        Button disarmButton = (Button) findViewById(R.id.disarmButton);
        View.OnClickListener disarmListener = new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                system_setting_status = false;
                TextView SystemStatus = (TextView) findViewById(R.id.SystemStatus);
                SystemStatus.setText("System Disarmed");
            }
        };
        disarmButton.setOnClickListener(disarmListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public void onBackPressed() {
        if(system_setting_status == true)
        {
            new AlertDialog.Builder(this)
                .setTitle("Warning!")
                .setMessage("Continuing back will deactivate security")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        }
        else if(system_setting_status == false) {
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
