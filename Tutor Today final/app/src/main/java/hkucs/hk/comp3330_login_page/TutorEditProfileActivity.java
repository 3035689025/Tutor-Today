package hkucs.hk.comp3330_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TutorEditProfileActivity extends AppCompatActivity {

    //button and text
    EditText editP_self_intro;
    EditText editP_qualifications;
    Button editP_enter;
    ImageButton[] pics;

    private String username;
    private int pic_chosen = -1;

    private DatabaseHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_edit_profile);

        //getting username from intent
        username = getIntent().getExtras().getString("username");

        //Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(this);
        }

        pics = new ImageButton [6];
        pics[0] = (ImageButton)findViewById(R.id.edit_pic0);
        pics[1] = (ImageButton)findViewById(R.id.edit_pic1);
        pics[2] = (ImageButton)findViewById(R.id.edit_pic2);
        pics[3] = (ImageButton)findViewById(R.id.edit_pic3);
        pics[4] = (ImageButton)findViewById(R.id.edit_pic4);
        pics[5] = (ImageButton)findViewById(R.id.edit_pic5);

        pics[0].setImageResource(R.drawable.pic0);
        pics[1].setImageResource(R.drawable.pic1);
        pics[2].setImageResource(R.drawable.pic2);
        pics[3].setImageResource(R.drawable.pic3);
        pics[4].setImageResource(R.drawable.pic4);
        pics[5].setImageResource(R.drawable.pic5);

        for(int i=0;i<6;i++){
            pics[i].setOnClickListener(this::onClick);
        }


        //linking with UI
        editP_self_intro = (EditText) findViewById(R.id.editP_new_self_intro);
        editP_qualifications = (EditText) findViewById(R.id.editP_new_qualifications);
        editP_enter = (Button) findViewById(R.id.editP_enter);


        //enter button clicked, change self intro and qualifications
        editP_enter.setOnClickListener(this::onClick);


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.edit_pic0:
                for(int i=0;i<6;i++){
                    pics[i].setAlpha(255);
                }
                pics[0].setAlpha(100);
                pic_chosen = 0;
                break;

            case R.id.edit_pic1:
                for(int i=0;i<6;i++){
                    pics[i].setAlpha(255);
                }
                pics[1].setAlpha(100);
                pic_chosen = 1;
                break;

            case R.id.edit_pic2:
                for(int i=0;i<6;i++){
                    pics[i].setAlpha(255);
                }
                pics[2].setAlpha(100);
                pic_chosen = 2;
                break;

            case R.id.edit_pic3:
                for(int i=0;i<6;i++){
                    pics[i].setAlpha(255);
                }
                pics[3].setAlpha(100);
                pic_chosen = 3;
                break;

            case R.id.edit_pic4:
                for(int i=0;i<6;i++){
                    pics[i].setAlpha(255);
                }
                pics[4].setAlpha(100);
                pic_chosen = 4;
                break;

            case R.id.edit_pic5:
                for(int i=0;i<6;i++){
                    pics[i].setAlpha(255);
                }
                pics[5].setAlpha(100);
                pic_chosen = 5;
                break;

            case R.id.editP_enter:
                //update database here
                updateTutor();
        }

    }

    //update tutor profile
    private void updateTutor(){

        //convert to String
        String self_intro = editP_self_intro.getText().toString();
        String qualifications = editP_qualifications.getText().toString();

        //check input

        if(pic_chosen == -1){
            Toast.makeText(TutorEditProfileActivity.this, "Please choose your new avatar.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(self_intro.isEmpty()){
            editP_self_intro.setError("Please input your updated self-introduction.");
            editP_self_intro.requestFocus();
            return;
        }

        if(qualifications.isEmpty()){
            editP_qualifications.setError("Please input your updated qualifications.");
            editP_qualifications.requestFocus();
            return;
        }

        //get tutor info from database
        Tutor tutor = sqliteHelper.getTutor(username);


        //update info to tutor object
        tutor.setSelf_introduction(self_intro);
        tutor.setQualifications(qualifications);
        tutor.setProfile_pic_id(pic_chosen);
        //update database
        long rowID = sqliteHelper.updateTutorProfile(tutor);

        //Toast.makeText(TutorEditProfileActivity.this, String.valueOf(rowID),Toast.LENGTH_SHORT).show();
        if( rowID!= -1){
            //success

            //reset
            editP_self_intro.setText("");
            editP_qualifications.setText("");
            for(int i=0;i<6;i++){
                pics[i].setAlpha(255);
            }


            Toast.makeText(TutorEditProfileActivity.this,"Profile updated!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(TutorEditProfileActivity.this,"Update failed. Please try again.",Toast.LENGTH_SHORT).show();
        }


    }
}