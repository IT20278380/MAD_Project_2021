package com.example.mad_project_2021.tableModules.IT20278380;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mad_project_2021.IT20278380.Cus_Contact;
import com.example.mad_project_2021.IT20278380.Cus_Home;
import com.example.mad_project_2021.IT20278380.Cus_Profile;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 1 :
                return new Cus_Contact();
            case 2 :
                return new Cus_Profile();
        }

        return new Cus_Home();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
