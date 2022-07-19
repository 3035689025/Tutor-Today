package hkucs.hk.comp3330_login_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hkucs.hk.comp3330_login_page.R;

public class ParentPostFragment extends Fragment {

    //Buttons
    Button Subject[]; // order following UI
    Button Gender[]; // male/female/others
    Button Post;

    //Edit text
    EditText salary;
    EditText hours;
    EditText lessons;
    EditText specifications;
    EditText grade;
    EditText district;
    ScrollView parents_post_scrollView;

    private DatabaseHelper sqliteHelper;

    private String username;
    private int subject_chosen;
    private int gender_chosen;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parent_post, container, false);
        ImageView imageView =  view.findViewById(R.id.parent_post);

        if(getArguments() != null){
            username = getArguments().getString("username");
        }

        //Toast.makeText(getActivity(), username, Toast.LENGTH_SHORT).show();

        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelper(getActivity());
        }

        parents_post_scrollView = (ScrollView) view.findViewById(R.id.parent_post_scrollView);


        //linking with UI

        Subject = new Button[20];
        Gender = new Button[3];

        //subject buttons
        //line 1
        Subject[0] = (Button) view.findViewById(R.id.requestS_chinese);
        Subject[1] = (Button) view.findViewById(R.id.requestS_english);
        Subject[2] = (Button) view.findViewById(R.id.requestS_maths);
        Subject[3] = (Button) view.findViewById(R.id.requestS_ICT);
        //line 2
        Subject[4] = (Button) view.findViewById(R.id.requestS_LS);
        Subject[5] = (Button) view.findViewById(R.id.requestS_general);
        Subject[6] = (Button) view.findViewById(R.id.requestS_history);
        //line 3
        Subject[7] = (Button) view.findViewById(R.id.requestS_geography);
        Subject[8] = (Button) view.findViewById(R.id.requestS_chinese_history);
        Subject[9] = (Button) view.findViewById(R.id.requestS_arts);
        //line 4
        Subject[10] = (Button) view.findViewById(R.id.requestS_chinese_lit);
        Subject[11] = (Button) view.findViewById(R.id.requestS_english_lit);
        //line 5
        Subject[12] = (Button) view.findViewById(R.id.requestS_BAFS_acc);
        Subject[13] = (Button) view.findViewById(R.id.requestS_BAFS_man);
        Subject[14] = (Button) view.findViewById(R.id.requestS_econ);
        Subject[15] = (Button) view.findViewById(R.id.requestS_m1);
        //line 6
        Subject[16] = (Button) view.findViewById(R.id.requestS_physics);
        Subject[17] = (Button) view.findViewById(R.id.requestS_chemistry);
        Subject[18] = (Button) view.findViewById(R.id.requestS_bio);
        Subject[19] = (Button) view.findViewById(R.id.requestS_m2);

        //Edit text
        salary = (EditText) view.findViewById(R.id.requestS_salary);
        hours = (EditText) view.findViewById(R.id.requestS_lesson_length);
        lessons = (EditText) view.findViewById(R.id.requestS_lessons_per_week);
        specifications = (EditText) view.findViewById(R.id.requestS_specifications);
        grade = (EditText) view.findViewById(R.id.requestS_current_grade);
        district = (EditText) view.findViewById(R.id.requestS_district);

        //Gender and post buttons
        Gender[0]  = (Button) view.findViewById(R.id.requestS_gender_male);
        Gender[1]  = (Button) view.findViewById(R.id.requestS_gender_female);
        Gender[2]  = (Button) view.findViewById(R.id.requestS_gender_others);
        Post = (Button) view.findViewById(R.id.requestS_enter);

        //set on click listener for buttons

        for (int i=0; i<20; i++){
            Subject[i].setOnClickListener(this::onClick);
        }
        for (int i=0;i<3;i++){
            Gender[i].setOnClickListener(this::onClick);
        }
        Post.setOnClickListener(this::onClick);

        return view;
    }

    public void onClick (View v){
        switch (v.getId()) {

            //subject buttons
            case R.id.requestS_chinese:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[0].setEnabled(false);
                subject_chosen = 0;
                break;

            case R.id.requestS_english:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[1].setEnabled(false);
                subject_chosen = 1;
                break;

            case R.id.requestS_maths:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[2].setEnabled(false);
                subject_chosen = 2;
                break;

            case R.id.requestS_ICT:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[3].setEnabled(false);
                subject_chosen = 3;
                break;

            case R.id.requestS_LS:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[4].setEnabled(false);
                subject_chosen = 4;
                break;

            case R.id.requestS_general:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[5].setEnabled(false);
                subject_chosen = 5;
                break;

            case R.id.requestS_history:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[6].setEnabled(false);
                subject_chosen = 6;
                break;

            case R.id.requestS_geography:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[7].setEnabled(false);
                subject_chosen = 7;
                break;

            case R.id.requestS_chinese_history:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[8].setEnabled(false);
                subject_chosen = 8;
                break;

            case R.id.requestS_arts:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[9].setEnabled(false);
                subject_chosen = 9;
                break;

            case R.id.requestS_chinese_lit:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[10].setEnabled(false);
                subject_chosen = 10;
                break;

            case R.id.requestS_english_lit:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[11].setEnabled(false);
                subject_chosen = 11;
                break;

            case R.id.requestS_BAFS_acc:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[12].setEnabled(false);
                subject_chosen = 12;
                break;

            case R.id.requestS_BAFS_man:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[13].setEnabled(false);
                subject_chosen = 13;
                break;

            case R.id.requestS_econ:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[14].setEnabled(false);
                subject_chosen = 14;
                break;

            case R.id.requestS_m1:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[15].setEnabled(false);
                subject_chosen = 15;
                break;

            case R.id.requestS_physics:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[16].setEnabled(false);
                subject_chosen = 16;
                break;

            case R.id.requestS_chemistry:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[17].setEnabled(false);
                subject_chosen = 17;
                break;

            case R.id.requestS_bio:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[18].setEnabled(false);
                subject_chosen = 18;
                break;

            case R.id.requestS_m2:
                //set button clicked here

                //make only the button clicked disabled
                for (int i=0;i<20;i++){
                    Subject[i].setEnabled(true);
                }
                Subject[19].setEnabled(false);
                subject_chosen = 19;
                break;

                //end of subject buttons

            //gender buttons

            case R.id.requestS_gender_male:
                //set gender clicked here

                for (int i=0;i<3;i++){
                    Gender[i].setEnabled(true);
                }
                Gender[0].setEnabled(false);
                gender_chosen = 0;
                break;

            case R.id.requestS_gender_female:
                //set gender clicked here

                for (int i=0;i<3;i++){
                    Gender[i].setEnabled(true);
                }
                Gender[1].setEnabled(false);
                gender_chosen = 1;
                break;

            case R.id.requestS_gender_others:
                //set gender clicked here

                for (int i=0;i<3;i++){
                    Gender[i].setEnabled(true);
                }
                Gender[2].setEnabled(false);
                gender_chosen = 2;
                break;

                //end of gender buttons


            case R.id.requestS_enter:
                //send data to database here
                postRequest();
                break;

                //end of enter button
        }
    }

    private void postRequest(){

        //get parent input values
        String salaryS = salary.getText().toString().trim();
        String hoursS = hours.getText().toString().trim();
        String lessonsS = lessons.getText().toString().trim();
        String specificationsS = specifications.getText().toString().trim();
        String gradeS = grade.getText().toString().trim();
        String districtS = district.getText().toString().trim();

        //check if user has input all info

        boolean noSubjectClicked = true;
        for (int i=0;i<20;i++){
            if (!Subject[i].isEnabled()){
                noSubjectClicked = false;
            }
        }
        if(noSubjectClicked){
            Toast.makeText(getActivity(), "Please choose a subject.", Toast.LENGTH_SHORT).show();
            return;
        }


        if(salaryS.isEmpty()){
            salary.setError("Please input the tuition fee.");
            salary.requestFocus();
            return;
        }

        //salary validation
        try {
            int salary_test = Integer.parseInt(salaryS);
        } catch (NumberFormatException nfe) {
            salary.setError("Please input an integer. e.g. 200");
            salary.requestFocus();
            return;
        }

        if(hoursS.isEmpty()){
            hours.setError("Please input the duration.");
            hours.requestFocus();
            return;
        }

        //hours validation
        try {
            Double hours_test = Double.parseDouble(hoursS);
        } catch (NumberFormatException nfe) {
            hours.setError("Please input a valid number. e.g. 1.5");
            hours.requestFocus();
            return;
        }

        if(lessonsS.isEmpty()){
            lessons.setError("Please input the number of lessons.");
            lessons.requestFocus();
            return;
        }

        //lessons validation
        try {
            int lessons_test = Integer.parseInt(lessonsS);
        } catch (NumberFormatException nfe) {
            lessons.setError("Please input an integer. e.g. 3");
            lessons.requestFocus();
            return;
        }

        if(specificationsS.isEmpty()){
            specifications.setError("Please input the specifications. Input NA if none.");
            specifications.requestFocus();
            return;
        }

        boolean noGenderClicked = true;
        for (int i=0;i<3;i++){
            if (!Gender[i].isEnabled()){
                noGenderClicked = false;
            }
        }
        if(noGenderClicked){
            Toast.makeText(getActivity(), "Please choose a gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(gradeS.isEmpty()){
            grade.setError("Please input the student's grade.");
            grade.requestFocus();
            return;
        }

        if(districtS.isEmpty()){
            district.setError("Please input the location.");
            district.requestFocus();
            return;
        }

        int lessonsInt = Integer.parseInt(lessonsS);
        int salaryInt = Integer.parseInt(salaryS);

        //insert request into table
        Request request = new Request( gender_chosen, gradeS, lessonsInt, specificationsS, salaryInt, hoursS, districtS, true, 0, subject_chosen, 0, username);
        long rowId = sqliteHelper.insertRequest(request);
        if (rowId != -1) {
            //Success


            //reset buttons and edit texts
            for (int i=0;i<20;i++) {
                Subject[i].setEnabled(true);

            }
            for (int i=0;i<3;i++){
                Gender[i].setEnabled(true);
            }
            salary.setText("");
            hours.setText("");
            lessons.setText("");
            specifications.setText("");
            grade.setText("");
            district.setText("");

            Toast.makeText(getActivity(),"Request posted!",Toast.LENGTH_SHORT).show();

            //scroll to top
            parents_post_scrollView.fullScroll(ScrollView.FOCUS_UP);
            parents_post_scrollView.scrollTo(0,0);

        } else {
            Toast.makeText(getActivity(), "Post failed. Please try again.",
                    Toast.LENGTH_SHORT).show();
        }

        //end of postRequest
    }

}