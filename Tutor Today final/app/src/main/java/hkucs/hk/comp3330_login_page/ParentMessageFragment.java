package hkucs.hk.comp3330_login_page;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Collections;
import java.util.List;

import hkucs.hk.comp3330_login_page.R;

public class ParentMessageFragment extends Fragment {

    private DatabaseHelper sqliteHelper;

    private String username;

    TextView[] requestID;
    TextView[] subject;
    TextView[] tutor;
    TextView[] email;
    Button[] goToProfile;
    Button[] accept;
    LinearLayout[] layouts;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parent_message, container, false);
        ImageView imageView =  view.findViewById(R.id.parent_message);

        if(getArguments() != null){
            username = getArguments().getString("username");
        }

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(getActivity());
        }

        //Toast.makeText(getActivity(), username, Toast.LENGTH_SHORT).show();

        //linking with UI
        requestID = new TextView[4];
        subject = new TextView[4];
        tutor = new TextView[4];
        email = new TextView[4];

        goToProfile = new Button[4];
        accept = new Button[4];

        layouts = new LinearLayout[4];

        //message 1
        layouts[0] = (LinearLayout) view.findViewById (R.id.parent_message_request1);
        requestID[0] = (TextView) view.findViewById(R.id.parent_message_request_ID1);
        subject[0] = (TextView)  view.findViewById(R.id.parent_message_subject1);
        tutor[0] = (TextView)  view.findViewById(R.id.parent_message_tutor1);
        email[0] = (TextView)  view.findViewById(R.id.parent_message_email1);
        goToProfile[0] = (Button) view.findViewById(R.id.parent_message_goToProfile1);
        accept[0] = (Button) view.findViewById(R.id.parent_message_accept1);

        //message 2
        layouts[1] = (LinearLayout) view.findViewById (R.id.parent_message_request2);
        requestID[1] = (TextView) view.findViewById(R.id.parent_message_request_ID2);
        subject[1] = (TextView)  view.findViewById(R.id.parent_message_subject2);
        tutor[1] = (TextView)  view.findViewById(R.id.parent_message_tutor2);
        email[1] = (TextView)  view.findViewById(R.id.parent_message_email2);
        goToProfile[1] = (Button) view.findViewById(R.id.parent_message_goToProfile2);
        accept[1] = (Button) view.findViewById(R.id.parent_message_accept2);

        //message 3
        layouts[2] = (LinearLayout) view.findViewById (R.id.parent_message_request3);
        requestID[2] = (TextView) view.findViewById(R.id.parent_message_request_ID3);
        subject[2] = (TextView)  view.findViewById(R.id.parent_message_subject3);
        tutor[2] = (TextView)  view.findViewById(R.id.parent_message_tutor3);
        email[2] = (TextView)  view.findViewById(R.id.parent_message_email3);
        goToProfile[2] = (Button) view.findViewById(R.id.parent_message_goToProfile3);
        accept[2] = (Button) view.findViewById(R.id.parent_message_accept3);

        //message 4
        layouts[3] = (LinearLayout) view.findViewById (R.id.parent_message_request4);
        requestID[3] = (TextView) view.findViewById(R.id.parent_message_request_ID4);
        subject[3] = (TextView)  view.findViewById(R.id.parent_message_subject4);
        tutor[3] = (TextView)  view.findViewById(R.id.parent_message_tutor4);
        email[3] = (TextView)  view.findViewById(R.id.parent_message_email4);
        goToProfile[3] = (Button) view.findViewById(R.id.parent_message_goToProfile4);
        accept[3] = (Button) view.findViewById(R.id.parent_message_accept4);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        //retrieve the the messages from tutor to parents as a list
        //List<T2Pmessage> T2PmessageList = Collections.emptyList();
        List<T2Pmessage> T2PmessageList = sqliteHelper.returnMessageByParentUsername(username);

        //subject string for displaying subject
        String[] subject_name = {"CHINESE", "ENGLISH", "MATHS", "ICT", "LIBERAL STUDIES", "GENERAL", "HISTORY", "GEOGRAPHY", "CHINESE HISTORY", "ARTS", "CHINESE LITERATURE", "ENGLISH LITERATURE", "BAFS-ACC", "BAFS-MAN", "ECONOMICS", "M1", "PHYSICS", "CHEMISTRY", "BIOLOGY", "M2"};


        //display requests according to the count
        switch (T2PmessageList.size()){
            case 0:
                //set all requests to invisible
                for (int i=0; i<4; i++){
                    layouts[i].setVisibility(View.INVISIBLE);
                }

                Toast.makeText(getActivity(),"no messages found",Toast.LENGTH_SHORT).show();
                break;

            case 1:

                Toast.makeText(getActivity(),"Please feel free to email the tutor for more information!",Toast.LENGTH_SHORT).show();

                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.INVISIBLE);
                layouts[2].setVisibility(View.INVISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);


                //set UI values
                for (int i=0; i<1; i++){

                    requestID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);
                    tutor[i].setText(T2PmessageList.get(i).getTutor_username());
                    email[i].setText(T2PmessageList.get(i).getEmail());


                }



                //set go to profile buttons
                for (int i=0; i<1 ; i++){

                    final int temp = i;

                    goToProfile[temp].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //go to parents login
                            Intent intent = new Intent (getActivity(), TutorProfile4ParentActivity.class);
                            intent.putExtra("username",T2PmessageList.get(temp).getTutor_username());
                            startActivity(intent);
                            ;}
                    });
                }

                //set accept buttons
                for (int i=0; i<1 ; i++){

                    final int acceptButton1 = i;

                    accept[i].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //set ongoing to false (no decline > ongoing false = accepted)
                            sqliteHelper.setOngoingTofalse4tutorMessages(T2PmessageList.get(acceptButton1).getT2PRequest_ID());
                            sqliteHelper.setOngoingTofalse4Request(T2PmessageList.get(acceptButton1).getT2PRequest_ID());
                            sqliteHelper.insertAcceptedRequest(T2PmessageList.get(acceptButton1).getT2PRequest_ID(), T2PmessageList.get(acceptButton1).getTutor_username());
                            Toast.makeText(getActivity(),"Tutor matched!",Toast.LENGTH_SHORT).show();

                            ;}
                    });
                }

                break;

            case 2:

                Toast.makeText(getActivity(),"Please feel free to email the tutor for more information!",Toast.LENGTH_SHORT).show();

                //set 3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.INVISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);


                //set UI values
                for (int i=0; i<2; i++){
                    requestID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);
                    tutor[i].setText(T2PmessageList.get(i).getTutor_username());
                    email[i].setText(T2PmessageList.get(i).getEmail());
                }

                //set go to profile buttons
                for (int i=0; i<2 ; i++){

                    final int temp = i;

                    goToProfile[temp].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //go to parents login
                            Intent intent = new Intent (getActivity(), TutorProfile4ParentActivity.class);
                            intent.putExtra("username",T2PmessageList.get(temp).getTutor_username());
                            startActivity(intent);
                            ;}
                    });
                }

                //set accept buttons
                for (int i=0; i<2 ; i++){

                    final int acceptButton2 = i;

                    accept[i].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //set ongoing to false (no decline > ongoing false = accepted)
                            sqliteHelper.setOngoingTofalse4tutorMessages(T2PmessageList.get(acceptButton2).getT2PRequest_ID());
                            sqliteHelper.setOngoingTofalse4Request(T2PmessageList.get(acceptButton2).getT2PRequest_ID());
                            sqliteHelper.insertAcceptedRequest(T2PmessageList.get(acceptButton2).getT2PRequest_ID(), T2PmessageList.get(acceptButton2).getTutor_username());
                            Toast.makeText(getActivity(),"Tutor matched!",Toast.LENGTH_SHORT).show();

                            ;}
                    });
                }

                break;

            case 3:

                Toast.makeText(getActivity(),"Please feel free to email the tutor for more information!",Toast.LENGTH_SHORT).show();

                //set 4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);


                //set UI values
                for (int i=0; i<3; i++){
                    requestID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);
                    tutor[i].setText(T2PmessageList.get(i).getTutor_username());
                    email[i].setText(T2PmessageList.get(i).getEmail());
                }

                //set go to profile buttons
                for (int i=0; i<3 ; i++){

                    final int temp = i;

                    goToProfile[temp].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //go to parents login
                            Intent intent = new Intent (getActivity(), TutorProfile4ParentActivity.class);
                            intent.putExtra("username",T2PmessageList.get(temp).getTutor_username());
                            startActivity(intent);
                            ;}
                    });
                }

                //set accept buttons
                for (int i=0; i<3 ; i++){

                    final int acceptButton3 = i;

                    accept[i].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //set ongoing to false (no decline > ongoing false = accepted)
                            sqliteHelper.setOngoingTofalse4tutorMessages(T2PmessageList.get(acceptButton3).getT2PRequest_ID());
                            sqliteHelper.setOngoingTofalse4Request(T2PmessageList.get(acceptButton3).getT2PRequest_ID());
                            sqliteHelper.insertAcceptedRequest(T2PmessageList.get(acceptButton3).getT2PRequest_ID(), T2PmessageList.get(acceptButton3).getTutor_username());
                            Toast.makeText(getActivity(),"Tutor matched!",Toast.LENGTH_SHORT).show();

                            ;}
                    });
                }

                break;

            case 4:

                Toast.makeText(getActivity(),"Please feel free to email the tutor for more information!",Toast.LENGTH_SHORT).show();

                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);


                //set UI values
                for (int i=0; i<4; i++){
                    requestID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);
                    tutor[i].setText(T2PmessageList.get(i).getTutor_username());
                    email[i].setText(T2PmessageList.get(i).getEmail());
                }

                //set go to profile buttons
                for (int i=0; i<4 ; i++){

                    final int temp = i;

                    goToProfile[temp].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //go to parents login
                            Intent intent = new Intent (getActivity(), TutorProfile4ParentActivity.class);
                            intent.putExtra("username",T2PmessageList.get(temp).getTutor_username());
                            startActivity(intent);
                            ;}
                    });
                }

                //set accept buttons
                for (int i=0; i<4 ; i++){

                    final int acceptButton4 = i;

                    accept[i].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //set ongoing to false (no decline > ongoing false = accepted)
                            sqliteHelper.setOngoingTofalse4tutorMessages(T2PmessageList.get(acceptButton4).getT2PRequest_ID());
                            sqliteHelper.setOngoingTofalse4Request(T2PmessageList.get(acceptButton4).getT2PRequest_ID());
                            sqliteHelper.insertAcceptedRequest(T2PmessageList.get(acceptButton4).getT2PRequest_ID(), T2PmessageList.get(acceptButton4).getTutor_username());
                            Toast.makeText(getActivity(),"Tutor matched!",Toast.LENGTH_SHORT).show();

                            ;}
                    });
                }


                break;

            default:

                Toast.makeText(getActivity(),"The top 4 messages are displayed. Please feel free to email the tutor for more information!",Toast.LENGTH_SHORT).show();

                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);


                //set UI values
                for (int i=0; i<4; i++){
                    requestID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);
                    tutor[i].setText(T2PmessageList.get(i).getTutor_username());
                    email[i].setText(T2PmessageList.get(i).getEmail());
                }

                //set go to profile buttons
                for (int i=0; i<4 ; i++){

                    final int temp = i;

                    goToProfile[temp].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //go to parents login
                            Intent intent = new Intent (getActivity(), TutorProfile4ParentActivity.class);
                            intent.putExtra("username",T2PmessageList.get(temp).getTutor_username());
                            startActivity(intent);
                            ;}
                    });
                }

                //set accept buttons
                for (int i=0; i<4 ; i++){

                    final int acceptButtonD = i;

                    accept[i].setOnClickListener(new ImageButton.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //set ongoing to false (no decline > ongoing false = accepted)
                            sqliteHelper.setOngoingTofalse4tutorMessages(T2PmessageList.get(acceptButtonD).getT2PRequest_ID());
                            sqliteHelper.setOngoingTofalse4Request(T2PmessageList.get(acceptButtonD).getT2PRequest_ID());
                            sqliteHelper.insertAcceptedRequest(T2PmessageList.get(acceptButtonD).getT2PRequest_ID(), T2PmessageList.get(acceptButtonD).getTutor_username());
                            Toast.makeText(getActivity(),"Tutor matched!",Toast.LENGTH_SHORT).show();

                            ;}
                    });
                }

                break;


        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sqliteHelper != null) {
            sqliteHelper.close();
        }
    }



}