package com.example.mad_project_2021.IT20276232;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mad_project_2021.R;
import com.example.mad_project_2021.tableModules.IT20276232.Delivers;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Deliver_Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textView;
    Spinner spinner;

    EditText userName, email, mobileNumber, password;
    String province;
    Button register;

    DatabaseReference Dregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_register);

        textView = (TextView) findViewById(R.id.D_re_link);

        userName = (EditText) findViewById(R.id.D_re_Nname);
        email = (EditText) findViewById(R.id.D_re_Email);
        mobileNumber = (EditText) findViewById(R.id.D_re_mob);
        password = (EditText) findViewById(R.id.D_re_Epass);
        spinner = (Spinner) findViewById(R.id.C_re_spinner);

        register = (Button) findViewById(R.id.D_re_but);

        //navigate login page
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        //spinner
        String[] objects = { "Western", "North Western", "Central", "Eastern", "North Central",
                "Northern", "Sabaragamuwa", "Southern", "Uva"};

        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,objects);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        //register deliver
        Dregister = FirebaseDatabase.getInstance().getReference().child("Delivers");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DuserName = userName.getText().toString();
                String Demail = email.getText().toString();
                String DmobileNumber = mobileNumber.getText().toString();
                String Dpassword = password.getText().toString();
                String Dprovince = province;

                Delivers deliver = new Delivers(DuserName, Demail, DmobileNumber, Dpassword, Dprovince);

                if(DuserName.isEmpty() || Demail.isEmpty() || DmobileNumber.isEmpty() || Dpassword.isEmpty() || Dprovince.isEmpty()){
                    Toast.makeText(Deliver_Register.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                }else {
                    Dregister.child(userName.getText().toString()).setValue(deliver);
                    Toast.makeText(Deliver_Register.this, "Deliver Registered", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });

    }

    public void navigateLogin(){
        Intent intent = new Intent(this, Deliver_Login.class);
        startActivity(intent);
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, Deliver_Login.class);
        startActivity(intent);
    }
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        Toast.makeText(getApplicationContext(),
                spinner.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG)
                .show();

        province = spinner.getItemAtPosition(pos).toString();
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