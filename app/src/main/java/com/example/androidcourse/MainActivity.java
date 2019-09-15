package com.example.androidcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.androidcourse.Fragments.ConnectFragment;
import com.example.androidcourse.Fragments.FixturesFragment;
import com.example.androidcourse.Fragments.MainFragment;
import com.example.androidcourse.Fragments.ProfileFragment;
import com.example.androidcourse.Fragments.TableFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {//Activity'i yeniden başlatmaması için
            loadFragment(new MainFragment());//Başlarken boş gelmesin diye
            navigationView.setCheckedItem(R.id.nav_home);//ilk açıldığında menüde seçili olmasını sağlıyoruz
        }

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                loadFragment(new MainFragment());
                break;
            case R.id.nav_connect:
                loadFragment(new ConnectFragment());
                break;
            case R.id.nav_fixtures:
                loadFragment(new FixturesFragment());
                break;
            case R.id.nav_table:
                loadFragment(new TableFragment());
                break;
            case R.id.nav_profile:
                loadFragment(new ProfileFragment());
                menuItem.setChecked(true);
                break;
                default:
                    break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }


}
