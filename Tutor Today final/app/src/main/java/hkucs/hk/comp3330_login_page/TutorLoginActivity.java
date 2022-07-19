package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

public class TutorLoginActivity extends AppCompatActivity {

    //button / text for tutor login
    Button tutor_login_go_to_register;
    EditText tutor_login_email;
    EditText tutor_login_password;
    Button tutor_login_enter;

    private DatabaseHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_login);

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking buttons with UI buttons
        tutor_login_go_to_register = (Button)findViewById(R.id.tutor_login_go_to_register);
        tutor_login_email = (EditText)findViewById(R.id.tutor_login_email);
        tutor_login_password = (EditText)findViewById(R.id.tutor_login_password);
        tutor_login_enter= (Button)findViewById(R.id.tutor_login_enter);

        //moving to tutors sign up
        tutor_login_go_to_register.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {


                //reset text to empty
                tutor_login_email.setText("");
                tutor_login_password.setText("");

                //go to tutors sign up
                Intent intent = new Intent (TutorLoginActivity.this, TutorRegisterActivity.class);
                startActivity(intent);
                }
        });

        tutor_login_enter.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                tutorLogin();
            }

        });
    }


    private void tutorLogin() {
        //convert to String
        String email = tutor_login_email.getText().toString().trim();
        String password = tutor_login_password.getText().toString().trim();

        //check if login is valid (username = success, " error email" = email not registered, " error password" = email, password mismatch
        String error = sqliteHelper.checkTutorEmailPassword(email, password);

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
            tutor_login_email.setText("");
            tutor_login_password.setText("");

            Toast.makeText(this, "Successful login!",
                    Toast.LENGTH_SHORT).show();
            //Intent
            Intent intent = new Intent (TutorLoginActivity.this, TutorNavigationActivity.class);
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