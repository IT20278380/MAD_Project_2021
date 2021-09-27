package com.example.mad_project_2021.IT20479428;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project_2021.R;

public class Admin_Accessories extends AppCompatActivity {

    Button comas, lapas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accessories);

        comas = findViewById(R.id.as_com);
        lapas = findViewById(R.id.as_lap);

        comas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Computer_Accessoies.class);
                startActivity(i);
            }
        });

        lapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Laptop_Acessories.class);
                startActivity(i);
            }
        });
    }
}