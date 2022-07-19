package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

public class TutorRegisterActivity  extends AppCompatActivity {

    //button and edit text for tutor register
    EditText tutor_register_email;
    EditText tutor_register_username;
    EditText tutor_register_password;
    EditText tutor_register_reenter_password;
    Button tutor_register_enter;
    Button tutor_register_go_to_login;

    private DatabaseHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_register);

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking with UI
        tutor_register_email = (EditText)findViewById(R.id.tutor_register_email);
        tutor_register_username = (EditText)findViewById(R.id.tutor_register_username);
        tutor_register_password = (EditText)findViewById(R.id.tutor_register_password);
        tutor_register_reenter_password = (EditText)findViewById(R.id.tutor_register_reenter_password);
        tutor_register_go_to_login = (Button)findViewById(R.id.tutor_register_go_to_login);
        tutor_register_enter = (Button)findViewById(R.id.tutor_register_enter);

        //moving to tutors login
        tutor_register_go_to_login.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set text to empty
                tutor_register_email.setText("");
                tutor_register_username.setText("");
                tutor_register_password.setText("");
                tutor_register_reenter_password.setText("");

                //go to tutors login
                Intent intent = new Intent (TutorRegisterActivity.this, TutorLoginActivity.class);
                startActivity(intent)
                ;}
        });

        tutor_register_enter.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                tutorRegister();
            }
        });
    }

    //register tutor
    private void tutorRegister() {
        //convert to String
        String email = tutor_register_email.getText().toString().trim();
        String username = tutor_register_username.getText().toString().trim();
        String password = tutor_register_password.getText().toString().trim();
        String reenter_password = tutor_register_reenter_password.getText().toString().trim();

        String empty = "";
        int pid = 0;

        if (email.isEmpty()) {
            tutor_register_email.setError("Please input your email.");
            tutor_register_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tutor_register_email.setError("Please input valid email.");
            tutor_register_email.requestFocus();
            return;
        }

        // check if it is empty
        if (username.isEmpty()) {
            tutor_register_username.setError("Please input your username.");
            tutor_register_username.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            tutor_register_password.setError("Please input your password.");
            tutor_register_password.requestFocus();
            return;
        }

        if (reenter_password.isEmpty()) {
            tutor_register_reenter_password.setError("Please reenter your password.");
            tutor_register_reenter_password.requestFocus();
            return;
        }

        if (password.length() < 7) {
            tutor_register_password.setError("Password have at least 7 characters.");
            tutor_register_password.requestFocus();
            return;
        }

        if (!password.equals(reenter_password)) {
            tutor_register_password.setError("Passwords do not match");
            tutor_register_reenter_password.requestFocus();
            return;
        }
        //check email, username (0 = success, 1 = email invalid, 2 = username invalid)
        int error = sqliteHelper.checkTutorEmailUsername(email, username);
        if (error == 1) {
            Toast.makeText(this, "Sorry this email is taken.",
                    Toast.LENGTH_SHORT).show();

        } else if (error == 2) {
            Toast.makeText(this, "Sorry this username is taken.",
                    Toast.LENGTH_SHORT).show();
        } else {
            //insert tutor into table
            Tutor tutor = new Tutor(email, username, password, empty, empty, pid);
            long rowId = sqliteHelper.insertTutor(tutor);
            if (rowId != -1) {
                Toast.makeText(this, "Successful register!",
                        Toast.LENGTH_SHORT).show();

                //set text to empty
                tutor_register_email.setText("");
                tutor_register_username.setText("");
                tutor_register_password.setText("");
                tutor_register_reenter_password.setText("");
            } else {
                Toast.makeText(this, "Register failed. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
            finish();
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