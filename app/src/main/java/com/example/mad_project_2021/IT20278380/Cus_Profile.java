package com.example.mad_project_2021.IT20278380;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mad_project_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

public class Cus_Profile extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cus_Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cus_Profile newInstance(String param1, String param2) {
        Cus_Profile fragment = new Cus_Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cus__profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Cus_Login cus_login = new Cus_Login();

        TextView name = (TextView)view.findViewById(R.id.c_name);
        TextView email = (TextView)view.findViewById(R.id.c_Emname);
        TextView number = (TextView)view.findViewById(R.id.c_Pmobile);
        TextView province = (TextView)view.findViewById(R.id.c_Ppro);
        TextView password = (TextView)view.findViewById(R.id.c_Ppass);

        Button update = (Button)view.findViewById(R.id.c_Pup);
        Button delete = (Button)view.findViewById(R.id.c_Pdel);

        DatabaseReference readDB = FirebaseDatabase.getInstance().getReference().child("Customers").child(cus_login.name12);
        readDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String CuserName = snapshot.child("cuserName").getValue().toString();
                String Cemail = snapshot.child("cemail").getValue().toString();
                String CmobileNumber = snapshot.child("cmobileNumber").getValue().toString();
                String Cpassword = snapshot.child("cpassword").getValue().toString();
                String Cprovince = snapshot.child("cprovince").getValue().toString();

                name.setText(CuserName);
                email.setText(Cemail);
                number.setText(CmobileNumber);
                province.setText(Cprovince);
                password.setText(Cpassword);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cus_update))
                        .setExpanded(true,1200)
                        .create();

                View view = dialogPlus.getHolderView();

                TextView userEmail = (TextView)view.findViewById(R.id.c_name_up);
                TextView usernumber = (TextView)view.findViewById(R.id.c_emal_up);
                TextView userpassword = (TextView)view.findViewById(R.id.c_pass_up);

                Button up = (Button)view.findViewById(R.id.com_aa_upbut);

                userEmail.setText(email.getText().toString());
                usernumber.setText(number.getText().toString());
                userpassword.setText(password.getText().toString());

                dialogPlus.show();

                up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase update = FirebaseDatabase.getInstance();
                        DatabaseReference myref = update.getReference();
                        myref.child("Customers").child(name.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.getRef().child("cemail").setValue(userEmail.getText().toString());
                                snapshot.getRef().child("cmobileNumber").setValue(usernumber.getText().toString());
                                snapshot.getRef().child("cpassword").setValue(userpassword.getText().toString());

                                dialogPlus.dismiss();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Customers")
                        .child(name.getText().toString()).setValue(null);
            }
        });

    }

}
