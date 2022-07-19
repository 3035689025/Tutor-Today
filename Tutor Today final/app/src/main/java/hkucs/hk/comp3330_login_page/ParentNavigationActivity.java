package hkucs.hk.comp3330_login_page;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;

        import android.os.Bundle;
        import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ParentNavigationActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_navigation);

        username = getIntent().getExtras().getString("username");

        //Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_parent);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //pass username
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        ParentHomeFragment fragment = new ParentHomeFragment();
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
                        case R.id.parent_home:
                            selectedFragment = new ParentHomeFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                        case R.id.parent_message:
                            selectedFragment = new ParentMessageFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                        case R.id.parent_post:
                            selectedFragment = new ParentPostFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                        case R.id.parent_setting:
                            selectedFragment = new ParentSettingFragment();
                            selectedFragment.setArguments(bundle);
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}
