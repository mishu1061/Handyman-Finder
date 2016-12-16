package com.rbl.prateek.habdyman_rbl;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //DatabaseHelper myDb;
            public static final int sec=10;
            public static final int milisec=sec*1000;
    public static final int delay=2;
    private ProgressBar probar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
       probar= (ProgressBar) findViewById(R.id.probar);
        probar.setMax(max_progress());
       animation();
    }

    public void animation()
    {
        new CountDownTimer(milisec,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                probar.setProgress(bar_progress(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                Intent newfrom = new Intent(MainActivity.this,Principal.class);
                startActivity(newfrom);
                finish();
            }
        }.start();
    }

    public int bar_progress(long miliseconds)
    {
        return (int) ((milisec-miliseconds)/1000);
    }

    public int max_progress()
    {
        return milisec-delay;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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






















