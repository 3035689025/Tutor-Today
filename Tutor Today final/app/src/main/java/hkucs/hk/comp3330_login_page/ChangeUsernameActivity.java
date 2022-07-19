package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangeUsernameActivity extends AppCompatActivity {

    //button and edit text for change password
    EditText cusername_new_username;
    EditText cusername_reenter_new_username;
    EditText cusername_password;
    Button cusername_enter;

    private String username;
    private String user;

    private DatabaseHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);

        //getting username from intent
        username = getIntent().getExtras().getString("username");
        user = getIntent().getExtras().getString("user");

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking with UI
        cusername_new_username = (EditText)findViewById(R.id.cusername_new_username);
        cusername_reenter_new_username = (EditText)findViewById(R.id.cusername_reenter_new_username);
        cusername_password = (EditText)findViewById(R.id.cusername_password);
        cusername_enter =(Button)findViewById(R.id.cusername_enter);

        //Toast.makeText(ChangeUsernameActivity.this,username,Toast.LENGTH_SHORT).show();

        //enter button clicked, change username
        cusername_enter.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert to String
                Parent checkParent = sqliteHelper.getParent(username);
                Tutor checkTutor = sqliteHelper.getTutor(username);

                String new_username = cusername_new_username.getText().toString().trim();
                String reenter_new_username = cusername_reenter_new_username.getText().toString().trim();
                String password = cusername_password.getText().toString().trim();

                //Toast.makeText(ChangeUsernameActivity.this,username+" "+reenter_new_username+" "+password,Toast.LENGTH_SHORT).show();

                if (! new_username.equals(reenter_new_username)){
                    Toast.makeText(ChangeUsernameActivity.this,"Usernames do not match!",Toast.LENGTH_SHORT).show();
                }
                //parent
                else if (user.equals("P")){

                    // check the input password is valid or not
                    if (checkParent.getPassword().equals(password)) {
                        //check if parent has made an request
                        if (sqliteHelper.parentRequested(username)){
                            //requests contains old parent username
                            Toast.makeText(ChangeUsernameActivity.this,"Sorry your username cannot be changed, as you have made a request.",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else {
                            sqliteHelper.updateParentUsername(checkParent.getEmail(), new_username);
                            //check password and update
                            Toast.makeText(ChangeUsernameActivity.this,"Username updated! Please log out to apply the changes!",Toast.LENGTH_LONG).show();
                            cusername_new_username.setText("");
                            cusername_reenter_new_username.setText("");
                            cusername_password.setText("");
                            return;
                        }

                    }
                    else{
                        Toast.makeText(ChangeUsernameActivity.this,"Invalid password.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                //tutor
                else{
                    if (checkTutor.getPassword().equals(password)){
                        //check if tutor has applied for a request
                        if (sqliteHelper.tutorApplied(username)){
                            //T2P messages contains old tutor username
                            Toast.makeText(ChangeUsernameActivity.this,"Sorry your username cannot be changed, as you have applied to a request.",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else {
                            sqliteHelper.updateTutorUsername(checkTutor.getEmail(), new_username);
                            //check password and update
                            Toast.makeText(ChangeUsernameActivity.this,"Username updated! Please log out to apply the changes!",Toast.LENGTH_SHORT).show();
                            cusername_new_username.setText("");
                            cusername_reenter_new_username.setText("");
                            cusername_password.setText("");
                            return;
                        }
                    }
                    else{
                        Toast.makeText(ChangeUsernameActivity.this,"Invalid password.",Toast.LENGTH_SHORT).show();
                        return;

                    }

                }


            }
        });

    }
}