package com.rbl.prateek.habdyman_rbl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PRATEEK on 4/30/2016.
 */
public class Main2Activity extends AppCompatActivity {
    TextView txtv;
    String city;
    String service;
    String pincode;
    EditText txt;
    DatabaseHelper myDb;
    Button search;
    Spinner spin;
    Button btnAddData;
    EditText editName,editConatc,editAddress,editSkill,editMin_charge,editPincode,editCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2activity);
        txtv = (TextView) findViewById(R.id.tv1);
        Intent i = getIntent();
        Bundle ib = i.getExtras();
        city = (String) ib.get("city");
        txtv.setText(city);editName = (EditText)findViewById(R.id.name);
        editConatc = (EditText)findViewById(R.id.contact);
        editAddress = (EditText)findViewById(R.id.address);
        editSkill = (EditText)findViewById(R.id.skill);
        editMin_charge = (EditText)findViewById(R.id.min_charge);
        editPincode = (EditText)findViewById(R.id.pincode);
        editCity = (EditText)findViewById(R.id.city);
        spin = (Spinner) findViewById(R.id.spin1);
        txt = (EditText) findViewById(R.id.editText);
        search = (Button) findViewById(R.id.button_search);
        btnAddData = (Button)findViewById(R.id.btn_add);
        myDb = new DatabaseHelper(this);
        AddData();
        viewAll();

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                service = parent.getSelectedItem().toString();
                pincode = txt.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void AddData()   {
        btnAddData.setOnClickListener(
                new View.OnClickListener()  {

                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editConatc.getText().toString(),
                                editAddress.getText().toString(),
                                editSkill.getText().toString(),
                                editMin_charge.getText().toString(),
                                editPincode.getText().toString(),
                                editCity.getText().toString());
                        if(isInserted = true)
                            Toast.makeText(Main2Activity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main2Activity.this, "Data is Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void viewAll()   {
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                pincode = txt.getText().toString();
           //     myDb = new DatabaseHelper(getApplicationContext());
             //   sqLiteDatabase = myDb.getReadableDatabase();
                Cursor cursor = myDb.getAllData(pincode);

              //  Cursor res = myDb.getAllData();
                if (cursor.getCount()==0)
                {
                    //show message
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext())
                {
                    buffer.append("rowid :"+cursor.getString(0)+"\n");
                    buffer.append("Name :"+cursor.getString(1)+"\n");
                    buffer.append("Contact :"+cursor.getString(2)+"\n");
                    buffer.append("Address :"+cursor.getString(3)+"\n");
                    buffer.append("Skill :"+cursor.getString(4)+"\n");
                    buffer.append("Min_charge :"+cursor.getString(5)+"\n");
                    buffer.append("Pincode :"+cursor.getString(6)+"\n");
                    buffer.append("City :"+cursor.getString(7)+"\n\n");
                }

                //Show all Data
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
