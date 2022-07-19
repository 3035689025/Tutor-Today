package hkucs.hk.comp3330_login_page;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;

public class ParentRegisterActivity  extends AppCompatActivity {

    //button and edit text for parent register
    EditText parent_register_email;
    EditText parent_register_username;
    EditText parent_register_password;
    EditText parent_register_reenter_password;
    Button parent_register_enter;
    Button parent_register_go_to_login;

    private DatabaseHelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking with UI
        parent_register_email = (EditText) findViewById(R.id.parent_register_email);
        parent_register_username = (EditText) findViewById(R.id.parent_register_username);
        parent_register_password = (EditText) findViewById(R.id.parent_register_password);
        parent_register_reenter_password = (EditText) findViewById(R.id.parent_register_reenter_password);
        parent_register_go_to_login = (Button) findViewById(R.id.parent_register_go_to_login);
        parent_register_enter = (Button) findViewById(R.id.parent_register_enter);

        //moving to parents login
        parent_register_go_to_login.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set text to empty
                parent_register_email.setText("");
                parent_register_username.setText("");
                parent_register_password.setText("");
                parent_register_reenter_password.setText("");

                //go to parents login
                Intent intent = new Intent(ParentRegisterActivity.this, ParentLoginActivity.class);
                startActivity(intent)
                ;
            }
        });

        parent_register_enter.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                parentRegister();
            }
        });

    }

    //register parent
    private void parentRegister() {

        // retrieving user input username, password
        //convert to String
        String email = parent_register_email.getText().toString().trim();
        String username = parent_register_username.getText().toString().trim();
        String password = parent_register_password.getText().toString().trim();
        String reenter_password = parent_register_reenter_password.getText().toString().trim();

        if (email.isEmpty()) {
            parent_register_email.setError("Please input your email.");
            parent_register_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            parent_register_email.setError("Please input valid email.");
            parent_register_email.requestFocus();
            return;
        }

        // check if it is empty
        if (username.isEmpty()) {
            parent_register_username.setError("Please input your username.");
            parent_register_username.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            parent_register_password.setError("Please input your password.");
            parent_register_password.requestFocus();
            return;
        }

        if (reenter_password.isEmpty()) {
            parent_register_reenter_password.setError("Please reenter your password.");
            parent_register_reenter_password.requestFocus();
            return;
        }

        if (password.length() < 7) {
            parent_register_password.setError("Password have at least 7 characters.");
            parent_register_password.requestFocus();
            return;
        }

        if (!password.equals(reenter_password)) {
            parent_register_password.setError("Passwords do not match");
            parent_register_reenter_password.requestFocus();
            return;
        }

        //check email, username (0 = success, 1 = email invalid, 2 = username invalid)
        int error = sqliteHelper.checkParentEmailUsername(email,username);
        if(error == 1) {
            Toast.makeText(this, "Sorry this email is taken.",
                    Toast.LENGTH_SHORT).show();

        }
        else if (error == 2){
            Toast.makeText(this, "Sorry this username is taken.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            //insert parent into table
            Parent parent = new Parent(email, username, password);
            long rowId = sqliteHelper.insertParent(parent);
            if (rowId != -1) {
                Toast.makeText(this,"Successful register!",
                        Toast.LENGTH_SHORT).show();

                //set text to empty
                parent_register_email.setText("");
                parent_register_username.setText("");
                parent_register_password.setText("");
                parent_register_reenter_password.setText("");
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
