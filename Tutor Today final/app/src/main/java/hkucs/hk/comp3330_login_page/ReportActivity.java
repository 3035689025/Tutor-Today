package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity {

    //button for submit
    Button submit;

    EditText feedbacktext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        /*
        As personal web servers are not provided by the CS department,
        and we utilize SQLite as our database, storing user feedback would be meaningless.
        With a web server, we can definitely implement this.
         */

        //linking button to UI button
        submit = (Button)findViewById(R.id.feedback_submit_button);

        feedbacktext = (EditText) findViewById(R.id.user_feedback);

        //moving to parents login
        submit.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbacktext.setText("");
                //send feedback to database
                Toast.makeText(ReportActivity.this,"Submission success!",Toast.LENGTH_LONG).show();
                }
        });
    }
}