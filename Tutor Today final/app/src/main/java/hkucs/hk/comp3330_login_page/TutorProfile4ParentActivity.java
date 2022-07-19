package hkucs.hk.comp3330_login_page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorProfile4ParentActivity extends AppCompatActivity {

    ImageView pic;
    TextView username;
    TextView self_intro;
    TextView qualifications;

    private String usernameS;

    private DatabaseHelper sqliteHelper;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tutor_profile4_parent);
        ImageView imageView =  findViewById(R.id.T4P_profile_pic);

        //getting username from intent
        usernameS = getIntent().getExtras().getString("username");

        // Toast.makeText(getActivity(), usernameS, Toast.LENGTH_SHORT).show();

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        //linking UI
        username = (TextView) findViewById(R.id.T4P_username);
        self_intro = (TextView) findViewById(R.id.T4P_self_intro);
        qualifications = (TextView) findViewById(R.id.T4P_qualifications);


        //initializing self-intro / qualifications
        username.setText(usernameS);


        Tutor tutor = sqliteHelper.getTutor(usernameS);
        self_intro.setText(tutor.getSelf_introduction());
        qualifications.setText(tutor.getQualifications());


        //initializing pic
        pic = (ImageView) findViewById(R.id.T4P_profile_pic);
        switch(tutor.getProfile_pic_id()){
            case 0:
                pic.setImageResource(R.drawable.pic0);
                break;

            case 1:
                pic.setImageResource(R.drawable.pic1);
                break;

            case 2:
                pic.setImageResource(R.drawable.pic2);
                break;

            case 3:
                pic.setImageResource(R.drawable.pic3);
                break;

            case 4:
                pic.setImageResource(R.drawable.pic4);
                break;

            case 5:
                pic.setImageResource(R.drawable.pic5);
                break;
        }



    }


}