package com.example.mad_project_2021.IT20426958;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project_2021.R;

public class Admin_Brand_New extends AppCompatActivity {

    Button computer, leptop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_brand_new);

        computer = (Button) findViewById(R.id.b_com);
        leptop = (Button) findViewById(R.id.b_lap);

        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Brand_New_Computer.class);
                startActivity(i);
            }
        });

        leptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Admin_Brand__New_Leptop.class);
                startActivity(i);
            }
        });
    }
}