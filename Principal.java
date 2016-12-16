package com.rbl.prateek.habdyman_rbl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PRATEEK on 4/26/2016.
 */
public class Principal extends AppCompatActivity {
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Spinner spn = (Spinner) findViewById(R.id.spin);


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     Toast.makeText(Principal.this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                 str = parent.getSelectedItem().toString();
                Button b = (Button) findViewById(R.id.b);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent I = new Intent(getApplicationContext(),Main2Activity.class);
                        I.putExtra("city",str);
                        startActivity(I);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
