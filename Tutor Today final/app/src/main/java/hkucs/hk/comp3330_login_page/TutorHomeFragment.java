package hkucs.hk.comp3330_login_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;


public class TutorHomeFragment extends Fragment {

    private DatabaseHelper sqliteHelper;

    private String username;

    TextView[] requestID;
    TextView[] subject;
    TextView[] salary;
    TextView[] duration;
    TextView[] lesson;
    TextView[] totalF;
    TextView[] specification;
    TextView[] gender;
    TextView[] grade;
    TextView[] location;
    Button[] apply;
    LinearLayout[] layouts;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tutor_home, container, false);
        ImageView imageView =  view.findViewById(R.id.tutor_home);

        if(getArguments() != null){
            username = getArguments().getString("username");
        }

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(getActivity());
        }

        //linking with UI
        requestID = new TextView[4];
        subject = new TextView[4];
        salary = new TextView[4];
        duration = new TextView[4];
        lesson = new TextView[4];
        totalF = new TextView[4];
        specification = new TextView[4];
        gender = new TextView[4];
        grade = new TextView[4];
        location = new TextView[4];
        apply = new Button[4];
        layouts = new LinearLayout[4];

        //request 1
        layouts[0] = (LinearLayout) view.findViewById (R.id.tutor_home_request1);
        requestID[0] = (TextView) view.findViewById(R.id.tutor_home_request_ID1);
        subject[0] = (TextView)  view.findViewById(R.id.tutor_home_subject1);
        salary[0] = (TextView)  view.findViewById(R.id.tutor_home_salary1);
        duration[0] = (TextView)  view.findViewById(R.id.tutor_home_length1);
        lesson[0] = (TextView)  view.findViewById(R.id.tutor_home_lessons1);
        totalF[0] = (TextView)  view.findViewById(R.id.tutor_home_total_fee1);
        specification[0] = (TextView) view.findViewById(R.id.tutor_home_spec1);
        gender[0] = (TextView) view.findViewById(R.id.tutor_home_gender1);
        grade[0] = (TextView) view.findViewById(R.id.tutor_home_grade1);
        location[0] = (TextView) view.findViewById(R.id.tutor_home_location1);
        apply[0] = (Button)  view.findViewById(R.id.tutor_home_apply1);

        //request 2
        layouts[1] = (LinearLayout) view.findViewById (R.id.tutor_home_request2);
        requestID[1] = (TextView) view.findViewById(R.id.tutor_home_request_ID2);
        subject[1] = (TextView)  view.findViewById(R.id.tutor_home_subject2);
        salary[1] = (TextView)  view.findViewById(R.id.tutor_home_salary2);
        duration[1] = (TextView)  view.findViewById(R.id.tutor_home_length2);
        lesson[1] = (TextView)  view.findViewById(R.id.tutor_home_lessons2);
        totalF[1] = (TextView)  view.findViewById(R.id.tutor_home_total_fee2);
        specification[1] = (TextView) view.findViewById(R.id.tutor_home_spec2);
        gender[1] = (TextView) view.findViewById(R.id.tutor_home_gender2);
        grade[1] = (TextView) view.findViewById(R.id.tutor_home_grade2);
        location[1] = (TextView) view.findViewById(R.id.tutor_home_location2);
        apply[1] = (Button)  view.findViewById(R.id.tutor_home_apply2);

        //request 3
        layouts[2] = (LinearLayout) view.findViewById (R.id.tutor_home_request3);
        requestID[2] = (TextView) view.findViewById(R.id.tutor_home_request_ID3);
        subject[2] = (TextView)  view.findViewById(R.id.tutor_home_subject3);
        salary[2] = (TextView)  view.findViewById(R.id.tutor_home_salary3);
        duration[2] = (TextView)  view.findViewById(R.id.tutor_home_length3);
        lesson[2] = (TextView)  view.findViewById(R.id.tutor_home_lessons3);
        totalF[2] = (TextView)  view.findViewById(R.id.tutor_home_total_fee3);
        specification[2] = (TextView) view.findViewById(R.id.tutor_home_spec3);
        gender[2] = (TextView) view.findViewById(R.id.tutor_home_gender3);
        grade[2] = (TextView) view.findViewById(R.id.tutor_home_grade3);
        location[2] = (TextView) view.findViewById(R.id.tutor_home_location3);
        apply[2] = (Button)  view.findViewById(R.id.tutor_home_apply3);

        //request 4
        layouts[3] = (LinearLayout) view.findViewById (R.id.tutor_home_request4);
        requestID[3] = (TextView) view.findViewById(R.id.tutor_home_request_ID4);
        subject[3] = (TextView)  view.findViewById(R.id.tutor_home_subject4);
        salary[3] = (TextView)  view.findViewById(R.id.tutor_home_salary4);
        duration[3] = (TextView)  view.findViewById(R.id.tutor_home_length4);
        lesson[3] = (TextView)  view.findViewById(R.id.tutor_home_lessons4);
        totalF[3] = (TextView)  view.findViewById(R.id.tutor_home_total_fee4);
        specification[3] = (TextView) view.findViewById(R.id.tutor_home_spec4);
        gender[3] = (TextView) view.findViewById(R.id.tutor_home_gender4);
        grade[3] = (TextView) view.findViewById(R.id.tutor_home_grade4);
        location[3] = (TextView) view.findViewById(R.id.tutor_home_location4);
        apply[3] = (Button)  view.findViewById(R.id.tutor_home_apply4);


        for (int i=0; i<4; i++){
            apply[i].setOnClickListener(this::onClick);
        }

        return view;
    }



    public void onClick (View v) {
        switch (v.getId()) {

            //subject buttons
            case R.id.tutor_home_apply1:
                sqliteHelper.updateApplicationCount(Integer.parseInt(String.valueOf(requestID[0].getText())));
                Request targetRequest1;
                targetRequest1 = sqliteHelper.findByRequestID(Integer.parseInt((String) requestID[0].getText()));
                Tutor tutor1;
                tutor1 = sqliteHelper.getTutor(username);
                sqliteHelper.insertT2PMessage(targetRequest1, username, tutor1.getEmail());
                Toast.makeText(getActivity(), "Application sent!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tutor_home_apply2:
                sqliteHelper.updateApplicationCount(Integer.parseInt(String.valueOf(requestID[1].getText())));
                Request targetRequest2;
                targetRequest2 = sqliteHelper.findByRequestID(Integer.parseInt((String) requestID[1].getText()));
                Tutor tutor2;
                tutor2 = sqliteHelper.getTutor(username);
                sqliteHelper.insertT2PMessage(targetRequest2, username, tutor2.getEmail());
                Toast.makeText(getActivity(), "Application sent!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tutor_home_apply3:
                sqliteHelper.updateApplicationCount(Integer.parseInt(String.valueOf(requestID[2].getText())));
                Request targetRequest3;
                targetRequest3 = sqliteHelper.findByRequestID(Integer.parseInt((String) requestID[2].getText()));
                Tutor tutor3;
                tutor3 = sqliteHelper.getTutor(username);
                sqliteHelper.insertT2PMessage(targetRequest3, username, tutor3.getEmail());
                Toast.makeText(getActivity(), "Application sent!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tutor_home_apply4:
                sqliteHelper.updateApplicationCount(Integer.parseInt(String.valueOf(requestID[3].getText())));
                Request targetRequest4;
                targetRequest4 = sqliteHelper.findByRequestID(Integer.parseInt((String) requestID[3].getText()));
                Tutor tutor4;
                tutor4 = sqliteHelper.getTutor(username);
                sqliteHelper.insertT2PMessage(targetRequest4, username, tutor4.getEmail());
                Toast.makeText(getActivity(), "Application sent!", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();


        //retrieve the the requests as a list
        List<Request> requestList = sqliteHelper.getAllRequest();

        //subject string for displaying subject
        String[] subject_name = {"CHINESE", "ENGLISH", "MATHS", "ICT", "LIBERAL STUDIES", "GENERAL", "HISTORY", "GEOGRAPHY", "CHINESE HISTORY", "ARTS", "CHINESE LITERATURE", "ENGLISH LITERATURE", "BAFS-ACC", "BAFS-MAN", "ECONOMICS", "M1", "PHYSICS", "CHEMISTRY", "BIOLOGY", "M2"};


        //display requests according to the count
        switch (requestList.size()){
            case 0:
                //set all requests to invisible
                for (int i=0; i<4; i++){
                    layouts[i].setVisibility(View.INVISIBLE);
                }

                Toast.makeText(getActivity(),"no requests found",Toast.LENGTH_SHORT).show();
                break;

            case 1:


                //set 2,3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.INVISIBLE);
                layouts[2].setVisibility(View.INVISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);


                //set UI values
                for (int i=0; i<1; i++){
                    sqliteHelper.updateViewCount(requestList.get(i).getRequest_ID());
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));


                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));

                    specification[i].setText(requestList.get(i).getSpecifications());
                    int displayGender = requestList.get(i).getGender();
                    if (displayGender == 0){
                        gender[i].setText("male");
                    }
                    else if (displayGender == 1){
                        gender[i].setText("female");
                    }
                    else{
                        gender[i].setText("others");
                    }

                    grade[i].setText(requestList.get(i).getCurrent_grade());
                    location[i].setText(requestList.get(i).getDistrict());



                }




                break;

            case 2:

                //set 3,4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.INVISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<2; i++){
                    sqliteHelper.updateViewCount(requestList.get(i).getRequest_ID());
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));

                    specification[i].setText(requestList.get(i).getSpecifications());

                    int displayGender = requestList.get(i).getGender();
                    if (displayGender == 0){
                        gender[i].setText("male");
                    }
                    else if (displayGender == 1){
                        gender[i].setText("female");
                    }
                    else{
                        gender[i].setText("others");
                    }
                    grade[i].setText(requestList.get(i).getCurrent_grade());
                    location[i].setText(requestList.get(i).getDistrict());

                }

                break;

            case 3:
                //set 4 to invisible
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.INVISIBLE);

                //set UI values
                for (int i=0; i<3; i++){
                    sqliteHelper.updateViewCount(requestList.get(i).getRequest_ID());
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));


                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));

                    specification[i].setText(requestList.get(i).getSpecifications());
                    int displayGender = requestList.get(i).getGender();
                    if (displayGender == 0){
                        gender[i].setText("male");
                    }
                    else if (displayGender == 1){
                        gender[i].setText("female");
                    }
                    else{
                        gender[i].setText("others");
                    }
                    grade[i].setText(requestList.get(i).getCurrent_grade());
                    location[i].setText(requestList.get(i).getDistrict());



                }

                break;

            case 4:
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);

                //set UI values
                for (int i=0; i<4; i++){
                    sqliteHelper.updateViewCount(requestList.get(i).getRequest_ID());
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));

                    specification[i].setText(requestList.get(i).getSpecifications());
                    int displayGender = requestList.get(i).getGender();
                    if (displayGender == 0){
                        gender[i].setText("male");
                    }
                    else if (displayGender == 1){
                        gender[i].setText("female");
                    }
                    else{
                        gender[i].setText("others");
                    }
                    grade[i].setText(requestList.get(i).getCurrent_grade());
                    location[i].setText(requestList.get(i).getDistrict());


                }

                break;

            default:

                //maximum requested
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);

                //set UI values
                for (int i=0; i<4; i++){
                    sqliteHelper.updateViewCount(requestList.get(i).getRequest_ID());
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));

                    specification[i].setText(requestList.get(i).getSpecifications());
                    int displayGender = requestList.get(i).getGender();
                    if (displayGender == 0){
                        gender[i].setText("male");
                    }
                    else if (displayGender == 1){
                        gender[i].setText("female");
                    }
                    else{
                        gender[i].setText("others");
                    }
                    grade[i].setText(requestList.get(i).getCurrent_grade());
                    location[i].setText(requestList.get(i).getDistrict());


                }

                Toast.makeText(getActivity(), "The top 4 requests are displayed.", Toast.LENGTH_SHORT).show();

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

