package guntrigger.ndzl.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


//(C)2021 by cxnt48
public class MainActivity extends Activity{


    TextView tvOut;

    public static Context main_context;
    EditText etPTT;
    EditText etABC;

    Intent i_startscan;
    Intent i_stopscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_context = this;
        tvOut = (TextView) findViewById(R.id.tvOutput);

        etPTT = (EditText) findViewById(R.id.etPTT);
        etABC = (EditText) findViewById(R.id.etABC);



        i_startscan = new Intent();
        i_startscan.setAction("com.motorolasolutions.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER");
        i_startscan.putExtra("com.motorolasolutions.emdk.datawedge.api.EXTRA_PARAMETER", "START_SCANNING");

        i_stopscan = new Intent();
        i_stopscan.setAction("com.motorolasolutions.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER");
        i_stopscan.putExtra("com.motorolasolutions.emdk.datawedge.api.EXTRA_PARAMETER", "STOP_SCANNING");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==102) {
            if (etPTT.isFocused()) {
                tvOut.setText("CONTEXT #1 SELECTED");
                sendBroadcast(i_startscan);
            } else if (etABC.isFocused()) {
                tvOut.setText("CONTEXT #2 SELECTED");
                dispatchTakePictureIntent();
            } else
                tvOut.setText("NO CONTEXT SELECTED");

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.

    }

    @Override
    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.



    }




}





