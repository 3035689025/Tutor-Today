package hkucs.hk.comp3330_login_page;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentHomeFragment extends Fragment  {

    private DatabaseHelper sqliteHelper;

    private String username;

    TextView[] requestID;
    TextView[] subject;
    TextView[] salary;
    TextView[] duration;
    TextView[] lesson;
    TextView[] totalF;
    TextView[] view_count;
    TextView[] app_count;
    LinearLayout[] layouts;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parent_home, container, false);
        ImageView imageView =  view.findViewById(R.id.parent_home);

        if(getArguments() != null){
            username = getArguments().getString("username");
        }

        //Toast.makeText(getActivity(), username, Toast.LENGTH_SHORT).show();

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
        view_count = new TextView[4];
        app_count = new TextView[4];
        layouts = new LinearLayout[4];

        //request 1
        layouts[0] = (LinearLayout) view.findViewById (R.id.parent_home_request1);
        requestID[0] = (TextView) view.findViewById(R.id.parent_home_request_ID1);
        subject[0] = (TextView)  view.findViewById(R.id.parent_home_subject1);
        salary[0] = (TextView)  view.findViewById(R.id.parent_home_salary1);
        duration[0] = (TextView)  view.findViewById(R.id.parent_home_length1);
        lesson[0] = (TextView)  view.findViewById(R.id.parent_home_lessons1);
        totalF[0] = (TextView)  view.findViewById(R.id.parent_home_total_fee1);
        view_count[0] = (TextView)  view.findViewById(R.id.parent_home_view_count1);
        app_count[0] = (TextView)  view.findViewById(R.id.parent_home_application_count1);

        //request 2
        layouts[1] = (LinearLayout) view.findViewById(R.id.parent_home_request2);
        requestID[1] = (TextView) view.findViewById(R.id.parent_home_request_ID2);
        subject[1] = (TextView)  view.findViewById(R.id.parent_home_subject2);
        salary[1] = (TextView)  view.findViewById(R.id.parent_home_salary2);
        duration[1] = (TextView)  view.findViewById(R.id.parent_home_length2);
        lesson[1] = (TextView)  view.findViewById(R.id.parent_home_lessons2);
        totalF[1] = (TextView)  view.findViewById(R.id.parent_home_total_fee2);
        view_count[1] = (TextView)  view.findViewById(R.id.parent_home_view_count2);
        app_count[1] = (TextView)  view.findViewById(R.id.parent_home_application_count2);

        //request 3
        layouts[2] = (LinearLayout) view.findViewById(R.id.parent_home_request3);
        requestID[2] = (TextView) view.findViewById(R.id.parent_home_request_ID3);
        subject[2] = (TextView)  view.findViewById(R.id.parent_home_subject3);
        salary[2] = (TextView)  view.findViewById(R.id.parent_home_salary3);
        duration[2] = (TextView)  view.findViewById(R.id.parent_home_length3);
        lesson[2] = (TextView)  view.findViewById(R.id.parent_home_lessons3);
        totalF[2] = (TextView)  view.findViewById(R.id.parent_home_total_fee3);
        view_count[2] = (TextView)  view.findViewById(R.id.parent_home_view_count3);
        app_count[2] = (TextView)  view.findViewById(R.id.parent_home_application_count3);

        //request 4
        layouts[3] = (LinearLayout) view.findViewById(R.id.parent_home_request4);
        requestID[3] = (TextView) view.findViewById(R.id.parent_home_request_ID4);
        subject[3] = (TextView)  view.findViewById(R.id.parent_home_subject4);
        salary[3] = (TextView)  view.findViewById(R.id.parent_home_salary4);
        duration[3] = (TextView)  view.findViewById(R.id.parent_home_length4);
        lesson[3] = (TextView)  view.findViewById(R.id.parent_home_lessons4);
        totalF[3] = (TextView)  view.findViewById(R.id.parent_home_total_fee4);
        view_count[3] = (TextView)  view.findViewById(R.id.parent_home_view_count4);
        app_count[3] = (TextView)  view.findViewById(R.id.parent_home_application_count4);



        return view;
    }
    @Override
    public void onStart() {
        super.onStart();


        //retrieve the the requests as a list
        List<Request> requestList = sqliteHelper.getAllRequestByParent(username);

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
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));
                    view_count[i].setText(String.valueOf(requestList.get(i).getView_count()));
                    app_count[i].setText(String.valueOf(requestList.get(i).getApplication_count()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));
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
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));
                    view_count[i].setText(String.valueOf(requestList.get(i).getView_count()));
                    app_count[i].setText(String.valueOf(requestList.get(i).getApplication_count()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));
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
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));
                    view_count[i].setText(String.valueOf(requestList.get(i).getView_count()));
                    app_count[i].setText(String.valueOf(requestList.get(i).getApplication_count()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));

                }

                break;

            case 4:
                layouts[0].setVisibility(View.VISIBLE);
                layouts[1].setVisibility(View.VISIBLE);
                layouts[2].setVisibility(View.VISIBLE);
                layouts[3].setVisibility(View.VISIBLE);

                //set UI values
                for (int i=0; i<4; i++){
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));
                    view_count[i].setText(String.valueOf(requestList.get(i).getView_count()));
                    app_count[i].setText(String.valueOf(requestList.get(i).getApplication_count()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));
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
                    requestID[i].setText(String.valueOf(requestList.get(i).getRequest_ID()));
                    subject[i].setText(subject_name[requestList.get(i).getSubject()]);
                    salary[i].setText(String.valueOf(requestList.get(i).getSalary()));
                    duration[i].setText(requestList.get(i).getLesson_length());
                    lesson[i].setText(String.valueOf(requestList.get(i).getLessons_per_week()));
                    view_count[i].setText(String.valueOf(requestList.get(i).getView_count()));
                    app_count[i].setText(String.valueOf(requestList.get(i).getApplication_count()));

                    //total fee per week
                    Double temp = requestList.get(i).getSalary() * Double.parseDouble( requestList.get(i).getLesson_length() )* requestList.get(i).getLessons_per_week();
                    totalF[i].setText(String.valueOf(temp));
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