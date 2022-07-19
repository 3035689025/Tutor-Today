package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //buttons for parents and tutors
    Button parent;
    Button tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupHyperlink();

        //linking buttons with UI buttons
        parent = (Button)findViewById(R.id.parent_button);
        tutor = (Button)findViewById(R.id.tutor_button);

        parent.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to parents login
                Intent intent = new Intent (MainActivity.this, ParentLoginActivity.class);
                startActivity(intent)
                ;}
        });

        tutor.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View view) {
                //go to tutor login
                Intent intent = new Intent (MainActivity.this, TutorLoginActivity.class);
                startActivity(intent)
                ;}
        });
    }

    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.activity_main_link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}