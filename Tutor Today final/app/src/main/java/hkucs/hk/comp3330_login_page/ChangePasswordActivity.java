package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    //button and edit text for change password
    EditText cpassword_current_password;
    EditText cpassword_new_password;
    EditText cpassword_reenter_new_password;
    Button cpassword_enter;

    private String username;
    private String user;

    private DatabaseHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //getting username from intent
        username = getIntent().getExtras().getString("username");
        user = getIntent().getExtras().getString("user");

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking with UI
        cpassword_current_password = (EditText)findViewById(R.id.cpassword_current_password);
        cpassword_new_password = (EditText)findViewById(R.id.cpassword_new_password);
        cpassword_reenter_new_password = (EditText)findViewById(R.id.cpassword_reenter_new_password);
        cpassword_enter =(Button)findViewById(R.id.cpassword_enter);


        //enter button clicked, change password
        cpassword_enter.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                Parent checkParent = sqliteHelper.getParent(username);
                Tutor checkTutor = sqliteHelper.getTutor(username);

                //convert to String
                String current_password = cpassword_current_password.getText().toString().trim();
                String new_password = cpassword_new_password.getText().toString().trim();
                String reenter_new_password = cpassword_reenter_new_password.getText().toString().trim();

                if (! new_password.equals(reenter_new_password)){
                    //check password and update
                    Toast.makeText(ChangePasswordActivity.this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
                }
                else if(user.equals("P")){
                    // check the input password is valid or not
                    if (checkParent.getPassword().equals(current_password)) {
                        sqliteHelper.updateParentPassword(checkParent.getEmail(), new_password);
                        //check password and update
                        Toast.makeText(ChangePasswordActivity.this,"Password updated!",Toast.LENGTH_SHORT).show();
                        cpassword_current_password.setText("");
                        cpassword_new_password.setText("");
                        cpassword_reenter_new_password.setText("");
                    }
                    else{
                        Toast.makeText(ChangePasswordActivity.this,"Invalid password!",Toast.LENGTH_SHORT).show();

                    }

                }

                else{

                    if (checkTutor.getPassword().equals(current_password)) {
                        sqliteHelper.updateTutorPassword(checkTutor.getEmail(), new_password);
                        //check password and update
                        Toast.makeText(ChangePasswordActivity.this, "Password updated!", Toast.LENGTH_SHORT).show();
                        cpassword_current_password.setText("");
                        cpassword_new_password.setText("");
                        cpassword_reenter_new_password.setText("");
                    }
                    else{
                        Toast.makeText(ChangePasswordActivity.this,"Invalid password!",Toast.LENGTH_SHORT).show();

                    }
                }}
        });


    }



}