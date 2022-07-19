package hkucs.hk.comp3330_login_page;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class TutorProfileFragment extends Fragment {

    ImageView pic;
    Button edit_profile;
    TextView username;
    TextView self_intro;
    TextView qualifications;

    private String usernameS;

    private DatabaseHelper sqliteHelper;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tutor_profile, container, false);
        ImageView imageView =  view.findViewById(R.id.tutor_profile);

        if(getArguments() != null){
            usernameS = getArguments().getString("username");
        }

        // Toast.makeText(getActivity(), usernameS, Toast.LENGTH_SHORT).show();

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(getActivity());
        }

        //linking UI
        edit_profile = (Button) view.findViewById(R.id.tutor_edit_profile);
        username = (TextView)view.findViewById(R.id.tutor_profile_username);
        self_intro = (TextView)view.findViewById(R.id.tutor_profile_self_intro);
        qualifications = (TextView)view.findViewById(R.id.tutor_profile_qualifications);



        edit_profile.setOnClickListener(this::onClick);


        //initializing self-intro / qualifications
        username.setText(usernameS);

        Tutor tutor = sqliteHelper.getTutor(usernameS);
        self_intro.setText(tutor.getSelf_introduction());
        qualifications.setText(tutor.getQualifications());

        //initializing pic
        pic= (ImageView) view.findViewById(R.id.tutor_profile_pic);
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

        return view;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.tutor_edit_profile:

                Intent intent = new Intent (getActivity(), TutorEditProfileActivity.class); // edit profile activity
                intent.putExtra("username",usernameS);
                startActivity(intent);
                break;
        }
    }


}
