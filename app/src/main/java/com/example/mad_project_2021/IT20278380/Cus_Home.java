package com.example.mad_project_2021.IT20278380;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mad_project_2021.R;
import com.example.mad_project_2021.tableModules.IT20278380.Home_Computer;
import com.example.mad_project_2021.tableModules.IT20278380.Home_Laptop;
import com.example.mad_project_2021.tableModules.IT20278380.Home_com_ass;
import com.example.mad_project_2021.tableModules.IT20278380.Home_com_ass_list;
import com.example.mad_project_2021.tableModules.IT20278380.Home_com_list;
import com.example.mad_project_2021.tableModules.IT20278380.Home_lap_ass;
import com.example.mad_project_2021.tableModules.IT20278380.Home_lap_list;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cus_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cus_Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cus_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cus_Home newInstance(String param1, String param2) {
        Cus_Home fragment = new Cus_Home();
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
        return inflater.inflate(R.layout.fragment_cus__home, container, false);
    }

    RecyclerView recyclerView;
    DatabaseReference database;
    Home_com_list home_com_list;
    ArrayList<Home_Computer> lst;

    RecyclerView recyclerViewLep;
    DatabaseReference databaselep;
    Home_lap_list home_lap_list;
    ArrayList<Home_Laptop> lstLep;

    RecyclerView recyclerViewcomAss;
    DatabaseReference databasecomAss;
    Home_com_ass_list home_com_ass_list;
    ArrayList<Home_com_ass> hoalist;

    RecyclerView recyclerViewlapAss;
    DatabaseReference databaselapAss;
    Home_com_ass_list home_lap_ass_list;
    ArrayList<Home_lap_ass> lapAss0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //computer
        recyclerView = view.findViewById(R.id.home_list100);
        database = FirebaseDatabase.getInstance().getReference("Brand_New_Computers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        lst = new ArrayList<>();
        home_com_list = new Home_com_list(getContext(),lst);
        recyclerView.setAdapter(home_com_list);

       database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Home_Computer home_computer = dataSnapshot.getValue(Home_Computer.class);
                    lst.add(home_computer);


                }
                home_com_list.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       ///leptop
        recyclerViewLep = view.findViewById(R.id.home_lep100);
        databaselep = FirebaseDatabase.getInstance().getReference("Brand_New_Laptops");
        recyclerViewLep.setHasFixedSize(true);
        recyclerViewLep.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        lstLep = new ArrayList<>();
        home_lap_list = new Home_lap_list(getContext(),lstLep);
        recyclerViewLep.setAdapter(home_lap_list);

        databaselep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Home_Laptop home_laptop = dataSnapshot.getValue(Home_Laptop.class);
                    lstLep.add(home_laptop);


                }
                home_lap_list.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //com acc
        recyclerViewcomAss = view.findViewById(R.id.home_ass00);
        databasecomAss = FirebaseDatabase.getInstance().getReference("Computers Accessories");
        recyclerViewcomAss.setHasFixedSize(true);
        recyclerViewcomAss.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        hoalist = new ArrayList<>();
        home_com_ass_list = new Home_com_ass_list(getContext(),hoalist);
        recyclerViewcomAss.setAdapter(home_com_ass_list);

        databasecomAss.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Home_com_ass home_com_ass = dataSnapshot.getValue(Home_com_ass.class);
                    hoalist.add(home_com_ass);


                }
                home_com_ass_list.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //lep acc
        recyclerViewlapAss = view.findViewById(R.id.home_ass11);
        databaselapAss = FirebaseDatabase.getInstance().getReference("Laptop Accessories");
        recyclerViewlapAss.setHasFixedSize(true);
        recyclerViewlapAss.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        lapAss0 = new ArrayList<>();
        home_lap_ass_list = new Home_com_ass_list(getContext(),hoalist);
        recyclerViewlapAss.setAdapter(home_lap_ass_list);

        databaselapAss.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Home_lap_ass home_lap_ass = dataSnapshot.getValue(Home_lap_ass.class);
                    lapAss0.add(home_lap_ass);


                }
                home_lap_ass_list.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}