package com.example.androidcourse.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.androidcourse.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        loadFragment(new ConnectFragment());

        BottomNavigationView navigation = view.findViewById(R.id.navigation);                    //.xml dosyamızda tanımladığımız id'si navigasyon olan BottomNavigationView'in nesnesini oluşturduk.
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_infos:

                    loadFragment(new ConnectFragment());
                    return true;

                case R.id.navigation_list:

                    loadFragment(new FixturesFragment());
                    return true;

                case R.id.navigation_favs:

                    loadFragment(new TableFragment());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
