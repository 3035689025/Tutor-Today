package hkucs.hk.comp3330_login_page;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.List;

public class TutorMessageFragment extends Fragment {

    private TextView[] request_ID;
    private TextView[] subject;
    private TextView[] salary;
    private TextView[] duration;
    private TextView[] lessons;
    private TextView[] status;
    private TextView[] email;
    private LinearLayout[] layouts;
    private LinearLayout[] contact;

    private String username;

    private DatabaseHelper sqliteHelper;

    private boolean accepted;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tutor_message, container, false);
        ImageView imageView =  view.findViewById(R.id.tutor_message);


        if(getArguments() != null){
            username = getArguments().getString("username");
        }

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(getActivity());
        }

        request_ID = new TextView[4];
        subject = new TextView[4];
        salary = new TextView[4];
        duration = new TextView[4];
        lessons = new TextView[4];
        status = new TextView[4];
        email = new TextView[4];
        accepted = false;

        layouts = new LinearLayout[4];
        contact = new LinearLayout[4];


        //message 1
        layouts[0] = (LinearLayout) view.findViewById (R.id.tutor_message_layout1);
        contact[0] = (LinearLayout) view.findViewById (R.id.tutor_message_contact1);
        request_ID[0] = (TextView) view.findViewById(R.id.tutor_message_request_ID1);
        salary[0] = (TextView) view.findViewById(R.id.tutor_message_salary1);
        subject[0] = (TextView) view.findViewById(R.id.tutor_message_subject1);
        duration[0] = (TextView) view.findViewById(R.id.tutor_message_length1);
        lessons[0] = (TextView) view.findViewById(R.id.tutor_message_lessons1);
        status[0] = (TextView) view.findViewById(R.id.tutor_message_status1);
        email[0] = (TextView) view.findViewById(R.id.tutor_message_email1);

        //message 2
        layouts[1] = (LinearLayout) view.findViewById (R.id.tutor_message_layout2);
        contact[1] = (LinearLayout) view.findViewById (R.id.tutor_message_contact2);
        request_ID[1] = (TextView) view.findViewById(R.id.tutor_message_request_ID2);
        salary[1] = (TextView) view.findViewById(R.id.tutor_message_salary2);
        subject[1] = (TextView) view.findViewById(R.id.tutor_message_subject2);
        duration[1] = (TextView) view.findViewById(R.id.tutor_message_length2);
        lessons[1] = (TextView) view.findViewById(R.id.tutor_message_lessons2);
        status[1] = (TextView) view.findViewById(R.id.tutor_message_status2);
        email[1] = (TextView) view.findViewById(R.id.tutor_message_email2);


        //message 3
        layouts[2] = (LinearLayout) view.findViewById (R.id.tutor_message_layout3);
        contact[2] = (LinearLayout) view.findViewById (R.id.tutor_message_contact3);
        request_ID[2] = (TextView) view.findViewById(R.id.tutor_message_request_ID3);
        salary[2] = (TextView) view.findViewById(R.id.tutor_message_salary3);
        subject[2] = (TextView) view.findViewById(R.id.tutor_message_subject3);
        duration[2] = (TextView) view.findViewById(R.id.tutor_message_length3);
        lessons[2] = (TextView) view.findViewById(R.id.tutor_message_lessons3);
        status[2] = (TextView) view.findViewById(R.id.tutor_message_status3);
        email[2] = (TextView) view.findViewById(R.id.tutor_message_email3);


        //message 4
        layouts[3] = (LinearLayout) view.findViewById (R.id.tutor_message_layout4);
        contact[3] = (LinearLayout) view.findViewById (R.id.tutor_message_contact4);
        request_ID[3] = (TextView) view.findViewById(R.id.tutor_message_request_ID4);
        salary[3] = (TextView) view.findViewById(R.id.tutor_message_salary4);
        subject[3] = (TextView) view.findViewById(R.id.tutor_message_subject4);
        duration[3] = (TextView) view.findViewById(R.id.tutor_message_length4);
        lessons[3] = (TextView) view.findViewById(R.id.tutor_message_lessons4);
        status[3] = (TextView) view.findViewById(R.id.tutor_message_status4);
        email[3] = (TextView) view.findViewById(R.id.tutor_message_email4);



        return view;
    }


    @Override
    public void onStart(){
        super.onStart();
        //retrieve the the messages from tutor to parents as a list
        //List<acceptedMessage> AcceptedMessageList = Collections.emptyList();
        List<T2Pmessage> T2PmessageList = sqliteHelper.returnMessageByTutorUsername(username);


        //subject string for displaying subject
        String[] subject_name = {"CHINESE", "ENGLISH", "MATHS", "ICT", "LIBERAL STUDIES", "GENERAL", "HISTORY", "GEOGRAPHY", "CHINESE HISTORY", "ARTS", "CHINESE LITERATURE", "ENGLISH LITERATURE", "BAFS-ACC", "BAFS-MAN", "ECONOMICS", "M1", "PHYSICS", "CHEMISTRY", "BIOLOGY", "M2"};

        //Toast.makeText(getActivity(), String.valueOf(T2PmessageList.size()),Toast.LENGTH_SHORT).show();


        //display messages according to the count
        switch(T2PmessageList.size()){

            case 0:
                //set all messages to invisible
                for (int i=0; i<4; i++){
                    layouts[i].setVisibility(View.INVISIBLE);
                }

                Toast.makeText(getActivity(),"no messages found",Toast.LENGTH_SHORT).show();
                break;

            case 1:
                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.INVISIBLE);
                layouts[2].setVisibility(View.INVISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);
                contact[0].setVisibility(View.INVISIBLE);
                contact[1].setVisibility(View.INVISIBLE);
                contact[2].setVisibility(View.INVISIBLE);
                contact[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<1; i++){

                    Request requestLoop = sqliteHelper.findByRequestID(T2PmessageList.get(i).getT2PRequest_ID());
                    Parent parentLoop = sqliteHelper.findByParentUsername(T2PmessageList.get(i).getParent_username());

                    // display request ID --> get from T2Pmessage
                    request_ID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));

                    // display salary --> get from request
                    salary[i].setText(String.valueOf(requestLoop.getSalary()));

                    // display subject --> get from T2Pmessage
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);

                    // display duration --> get from request
                    duration[i].setText(requestLoop.getLesson_length());

                    // display lessons --> get from request
                    lessons[i].setText(String.valueOf(requestLoop.getLessons_per_week()));

                    // calculate status
                    if (T2PmessageList.get(i).getOngoing() == true){


                        status[i].setText("Pending");

                    }
                    else{
                        AcceptedRequest acceptedRequestLoop = sqliteHelper.getAcceptedRequestByRequestID(T2PmessageList.get(i).getT2PRequest_ID());

                        if(acceptedRequestLoop.getAccepted_tutor_username().equals(username)){
                            contact[i].setVisibility(View.VISIBLE);
                            status[i].setText("Accepted");
                            // display parent email --> get from parent table
                            email[i].setText(parentLoop.getEmail());
                            accepted = true;
                        }
                        else{
                            status[i].setText("Closed");
                        }
                    }



                }
                if (accepted){
                    Toast.makeText(getActivity(),"Congratulations! Parent matched!",Toast.LENGTH_SHORT).show();
                }

                break;

            case 2:
                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.INVISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);
                contact[0].setVisibility(View.INVISIBLE);
                contact[1].setVisibility(View.INVISIBLE);
                contact[2].setVisibility(View.INVISIBLE);
                contact[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<2; i++){

                    Request requestLoop = sqliteHelper.findByRequestID(T2PmessageList.get(i).getT2PRequest_ID());
                    Parent parentLoop = sqliteHelper.findByParentUsername(T2PmessageList.get(i).getParent_username());

                    // display request ID --> get from T2Pmessage
                    request_ID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));

                    // display salary --> get from request
                    salary[i].setText(String.valueOf(requestLoop.getSalary()));

                    // display subject --> get from T2Pmessage
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);

                    // display duration --> get from request
                    duration[i].setText(requestLoop.getLesson_length());

                    // display lessons --> get from request
                    lessons[i].setText(String.valueOf(requestLoop.getLessons_per_week()));

                    // calculate status
                    if (T2PmessageList.get(i).getOngoing() == true){


                        status[i].setText("Pending");

                    }
                    else{
                        AcceptedRequest acceptedRequestLoop = sqliteHelper.getAcceptedRequestByRequestID(T2PmessageList.get(i).getT2PRequest_ID());

                        if(acceptedRequestLoop.getAccepted_tutor_username().equals(username)){
                            contact[i].setVisibility(View.VISIBLE);
                            status[i].setText("Accepted");
                            // display parent email --> get from parent table
                            email[i].setText(parentLoop.getEmail());
                            accepted = true;
                        }
                        else{
                            status[i].setText("Closed");
                        }
                    }

                }

                if (accepted){
                    Toast.makeText(getActivity(),"Congratulations! Parent matched!",Toast.LENGTH_SHORT).show();
                }
                break;


            case 3:
                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);
                contact[0].setVisibility(View.INVISIBLE);
                contact[1].setVisibility(View.INVISIBLE);
                contact[2].setVisibility(View.INVISIBLE);
                contact[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<3; i++){

                    Request requestLoop = sqliteHelper.findByRequestID(T2PmessageList.get(i).getT2PRequest_ID());
                    Parent parentLoop = sqliteHelper.findByParentUsername(T2PmessageList.get(i).getParent_username());


                    // display request ID --> get from T2Pmessage
                    request_ID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));

                    // display salary --> get from request
                    salary[i].setText(String.valueOf(requestLoop.getSalary()));

                    // display subject --> get from T2Pmessage
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);

                    // display duration --> get from request
                    duration[i].setText(requestLoop.getLesson_length());

                    // display lessons --> get from request
                    lessons[i].setText(String.valueOf(requestLoop.getLessons_per_week()));

                    // calculate status

                    if (T2PmessageList.get(i).getOngoing() == true){


                        status[i].setText("Pending");

                    }

                    else{
                        AcceptedRequest acceptedRequestLoop = sqliteHelper.getAcceptedRequestByRequestID(T2PmessageList.get(i).getT2PRequest_ID());

                        if(acceptedRequestLoop.getAccepted_tutor_username().equals(username)){
                            contact[i].setVisibility(View.VISIBLE);
                            status[i].setText("Accepted");
                            // display parent email --> get from parent table
                            email[i].setText(parentLoop.getEmail());
                            accepted = true;
                        }
                        else{
                            status[i].setText("Closed");
                        }

                    }






                }
                if (accepted){
                    Toast.makeText(getActivity(),"Congratulations! Parent matched!",Toast.LENGTH_SHORT).show();
                }

                break;


            case 4:
                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);
                contact[0].setVisibility(View.INVISIBLE);
                contact[1].setVisibility(View.INVISIBLE);
                contact[2].setVisibility(View.INVISIBLE);
                contact[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<4; i++){

                    Request requestLoop = sqliteHelper.findByRequestID(T2PmessageList.get(i).getT2PRequest_ID());
                    Parent parentLoop = sqliteHelper.findByParentUsername(T2PmessageList.get(i).getParent_username());


                    // display request ID --> get from T2Pmessage
                    request_ID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));

                    // display salary --> get from request
                    salary[i].setText(String.valueOf(requestLoop.getSalary()));

                    // display subject --> get from T2Pmessage
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);

                    // display duration --> get from request
                    duration[i].setText(requestLoop.getLesson_length());

                    // display lessons --> get from request
                    lessons[i].setText(String.valueOf(requestLoop.getLessons_per_week()));

                    // calculate status
                    if (T2PmessageList.get(i).getOngoing() == true){


                        status[i].setText("Pending");

                    }
                    else{
                        AcceptedRequest acceptedRequestLoop = sqliteHelper.getAcceptedRequestByRequestID(T2PmessageList.get(i).getT2PRequest_ID());

                        if(acceptedRequestLoop.getAccepted_tutor_username().equals(username)){
                            contact[i].setVisibility(View.VISIBLE);
                            status[i].setText("Accepted");
                            // display parent email --> get from parent table
                            email[i].setText(parentLoop.getEmail());
                            accepted = true;
                        }
                        else{
                            status[i].setText("Closed");
                        }
                    }


                }
                if (accepted){
                    Toast.makeText(getActivity(),"Congratulations! Parent matched!",Toast.LENGTH_SHORT).show();
                }

                break;

            default:
                Toast.makeText(getActivity(),"The top 4 messages are displayed. Please feel free to email the parent for more information!",Toast.LENGTH_SHORT).show();

                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);
                contact[0].setVisibility(View.INVISIBLE);
                contact[1].setVisibility(View.INVISIBLE);
                contact[2].setVisibility(View.INVISIBLE);
                contact[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<4; i++){

                    Request requestLoop = sqliteHelper.findByRequestID(T2PmessageList.get(i).getT2PRequest_ID());
                    Parent parentLoop = sqliteHelper.findByParentUsername(T2PmessageList.get(i).getParent_username());

                    // display request ID --> get from T2Pmessage
                    request_ID[i].setText(String.valueOf(T2PmessageList.get(i).getT2PRequest_ID()));

                    // display salary --> get from request
                    salary[i].setText(String.valueOf(requestLoop.getSalary()));

                    // display subject --> get from T2Pmessage
                    subject[i].setText(subject_name[T2PmessageList.get(i).getT2PSubject()]);

                    // display duration --> get from request
                    duration[i].setText(requestLoop.getLesson_length());

                    // display lessons --> get from request
                    lessons[i].setText(String.valueOf(requestLoop.getLessons_per_week()));

                    // calculate status
                    if (T2PmessageList.get(i).getOngoing() == true){


                        status[i].setText("Pending");

                    }
                    else{
                        AcceptedRequest acceptedRequestLoop = sqliteHelper.getAcceptedRequestByRequestID(T2PmessageList.get(i).getT2PRequest_ID());

                        if(acceptedRequestLoop.getAccepted_tutor_username().equals(username)){
                            contact[i].setVisibility(View.VISIBLE);
                            status[i].setText("Accepted");
                            // display parent email --> get from parent table
                            email[i].setText(parentLoop.getEmail());
                            accepted = true;
                        }
                        else{
                            status[i].setText("Closed");
                        }
                    }



                }
                if (accepted){
                    Toast.makeText(getActivity(),"Congratulations! Parent matched!",Toast.LENGTH_SHORT).show();
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