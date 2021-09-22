package com.example.mad_project_2021;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

public class Cus_Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_register);

        textView = (TextView) findViewById(R.id.C_re_li);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        //spinner
        spinner = (Spinner) findViewById(R.id.C_re_spinner);
        String[] objects = { "Western", "North Western", "Central", "Eastern", "North Central",
                "Northern", "Sabaragamuwa", "Southern", "Uva"};

        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,objects);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }


    public void openNewActivity(){
        Intent intent = new Intent(this, Cus_Login.class);
        startActivity(intent);
    }
    public void onItemSelected(AdapterView parent, View view, int pos,
                               long id) {
        Toast.makeText(getApplicationContext(),
                spinner.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG)
                .show();
    }

    // Defining the Callback methods here
    @Override
    public void onNothingSelected(AdapterView arg0) {
// TODO Auto-generated method stub

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}