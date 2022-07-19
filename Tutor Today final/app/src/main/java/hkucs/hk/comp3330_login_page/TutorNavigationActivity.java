package hkucs.hk.comp3330_login_page;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TutorNavigationActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_navigation);

        username = getIntent().getExtras().getString("username");
        /*
        Toast.makeText(this, username,
                Toast.LENGTH_SHORT).show();

         */

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_tutor);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //pass username
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        TutorHomeFragment fragment = new TutorHomeFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){

                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.tutor_home:
                            selectedFragment = new TutorHomeFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                        case R.id.tutor_message:
                            selectedFragment = new TutorMessageFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                        case R.id.tutor_profile:
                            selectedFragment = new TutorProfileFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                        case R.id.tutor_setting:
                            selectedFragment = new TutorSettingFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
    };

}
