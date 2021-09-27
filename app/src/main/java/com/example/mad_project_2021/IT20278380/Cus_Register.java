package com.example.mad_project_2021.IT20278380;

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
import com.example.mad_project_2021.tableModules.IT20276232.Customer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cus_Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textView;
    Spinner spinner;
    Button register;

    EditText userName, email, mobileNumber, password;
    String province;

    DatabaseReference Cregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_register);

        textView = (TextView) findViewById(R.id.C_re_li);
        userName = (EditText) findViewById(R.id.C_reEnName);
        email = (EditText) findViewById(R.id.C_reEnEmail);
        mobileNumber = (EditText) findViewById(R.id.C_reEnMobile);
        password = (EditText) findViewById(R.id.C_reEnPass);

        register = (Button) findViewById(R.id.Cu_but_update);

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

        //register deliver
        Cregister = FirebaseDatabase.getInstance().getReference().child("Customers");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CuserName = userName.getText().toString();
                String Cemail = email.getText().toString();
                String CmobileNumber = mobileNumber.getText().toString();
                String Cpassword = password.getText().toString();
                String Cprovince = province;

                Customer customer = new Customer(CuserName, Cemail, CmobileNumber, Cpassword, Cprovince);

                if(CuserName.isEmpty() || Cemail.isEmpty() || CmobileNumber.isEmpty() || Cpassword.isEmpty() || Cprovince.isEmpty()){
                    Toast.makeText(Cus_Register.this, "Fill All Details", Toast.LENGTH_SHORT).show();

                }else {
                    Cregister.child(userName.getText().toString()).setValue(customer);
                    Toast.makeText(Cus_Register.this, "Customer Registered", Toast.LENGTH_SHORT).show();

                    navigateLogin();
                }

            }
        });

    }
    public void navigateLogin(){
        Intent intent = new Intent(this, Cus_Login.class);
        startActivity(intent);
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