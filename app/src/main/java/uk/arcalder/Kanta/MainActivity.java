package uk.arcalder.Kanta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load fragment/s for Home(MainActivity) view
        //loadListFragment(new SongListFragment());

        // Setup bottom navigation view, disable shift mode (which looks crap) and add listener
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationBarBottom);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        LoadToolbarFragment(new TitlebarFragment());

    }

    private boolean loadListFragment(ListFragment list_frag) {
        if (list_frag != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_main, list_frag)
                    .commit();
            return true;
        }
        return false;

    }

    private boolean LoadToolbarFragment(Fragment toolbar_frag) {
        if (toolbar_frag != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_toolbar, toolbar_frag)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        ListFragment fragment = null;
        // TODO replace with actual functionality
        // TODO come up with better way to switch toolbar type
        switch (item.getItemId()) {
            case R.id.navigation_home:
                // Home
                fragment = new SongListFragment();
                LoadToolbarFragment(new TitlebarFragment());
                break;
            case R.id.navigation_dashboard:
                // Browse
                Intent i = new Intent(this, BigPlayerActivity.class);
                startActivity(i);
                break;
            case R.id.navigation_search:
                // Search
                fragment = new SongListFragment();
                LoadToolbarFragment(new SearchFragment());
                break;
            case R.id.navigation_notifications:
                // Library
                fragment = new AlbumListFragment();
                LoadToolbarFragment(new TitlebarFragment());
                break;
            case R.id.navigation_library:
                // Settings
                fragment = new SongListFragment();
                LoadToolbarFragment(new TitlebarFragment());
                break;
        }

        return loadListFragment(fragment);
    }

    @Override
    public void onClick(View view) {

    }
}