package com.example.mad_project_2021;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.cus_log);
        button2 = (Button) findViewById(R.id.deli_log);
        button3 = (Button) findViewById(R.id.admin_log);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity1();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity3();
            }
        });
    }
    public void openNewActivity1(){
        Intent intent = new Intent(this, Cus_Login.class);
        startActivity(intent);
    }

    public void openNewActivity2(){
        Intent intent = new Intent(this, Deliver_Login.class);
        startActivity(intent);
    }

    public void openNewActivity3(){
        Intent intent = new Intent(this, Admin_Loin.class);
        startActivity(intent);
    }
}