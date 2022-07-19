package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

public class ParentLoginActivity extends AppCompatActivity {

    //button / text for parent login
    Button parent_login_go_to_register;
    EditText parent_login_email;
    EditText parent_login_password;
    Button parent_login_enter;

    private DatabaseHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking buttons with UI buttons
        parent_login_go_to_register = (Button)findViewById(R.id.parent_login_go_to_register);
        parent_login_email = (EditText)findViewById(R.id.parent_login_email);
        parent_login_password = (EditText)findViewById(R.id.parent_login_password);
        parent_login_enter= (Button)findViewById(R.id.parent_login_enter);

        //moving to parents sign up
        parent_login_go_to_register.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                //reset text to empty
                parent_login_email.setText("");
                parent_login_password.setText("");

                //go to parents sign up
                Intent intent = new Intent (ParentLoginActivity.this, ParentRegisterActivity.class);
                startActivity(intent);
                }
        });

        parent_login_enter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentLogin();
            }
        });
    }

    private void parentLogin() {
        //convert to String
        String email = parent_login_email.getText().toString().trim();
        String password = parent_login_password.getText().toString().trim();

        //check if login is valid (username = success, " error email" = email not registered, " error password" = email, password mistach
        String error = sqliteHelper.checkParentEmailPassword(email, password);

        if (error.equals(" error email")){
            Toast.makeText(this, "Invalid email. Please try again.",
                    Toast.LENGTH_SHORT).show();
        }
        else if (error.equals(" error password")){
            Toast.makeText(this, "Wrong password. Please try again.",
                    Toast.LENGTH_SHORT).show();
        }
        //success
        else {
            //reset text to empty
            parent_login_email.setText("");
            parent_login_password.setText("");

            Toast.makeText(this, "Successful login!",
                    Toast.LENGTH_SHORT).show();
            //Intent
            Intent intent = new Intent (ParentLoginActivity.this, ParentNavigationActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("username", error); //pass username into intent
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sqliteHelper != null) {
            sqliteHelper.close();
        }
    }
}