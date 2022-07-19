package hkucs.hk.comp3330_login_page;
import android.content.Intent;
import android.os.Bundle;
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

public class ParentSettingFragment extends Fragment implements View.OnClickListener{
    //Buttons
    TextView CUsername;
    TextView CPassword;
    TextView Report;
    TextView AboutUs;

    private String username;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parent_setting, container, false);
        ImageView imageView =  view.findViewById(R.id.parent_setting);

        if(getArguments() != null){
            username = getArguments().getString("username");
        }

        //Toast.makeText(getActivity(), username, Toast.LENGTH_SHORT).show();

        //linking text with UI
        CUsername = (TextView) view.findViewById(R.id.parent_change_username_text);
        CPassword = (TextView)view.findViewById(R.id.parent_change_password_text);
        AboutUs = (TextView) view.findViewById(R.id.parent_about_us_text);
        Report = (TextView) view.findViewById(R.id.parent_report_text);

        AboutUs.setOnClickListener(this);
        Report.setOnClickListener(this);
        CUsername.setOnClickListener(this);
        CPassword.setOnClickListener(this);

        return view;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.parent_change_username_text:
                Intent intent1 = new Intent (getActivity(), ChangeUsernameActivity.class); // change username activity
                intent1.putExtra("username",username);
                intent1.putExtra("user","P");
                startActivity(intent1);
                break;

            case R.id.parent_change_password_text:
                Intent intent2 = new Intent (getActivity(), ChangePasswordActivity.class); // change username activity
                intent2.putExtra("username",username);
                intent2.putExtra("user","P");
                startActivity(intent2);
                break;

            case R.id.parent_about_us_text:
                Intent intent3 = new Intent (getActivity(), AboutUsActivity.class); // about us activity
                startActivity(intent3);
                break;

            case R.id.parent_report_text:
                Intent intent4 = new Intent (getActivity(), ReportActivity.class); // report activity
                startActivity(intent4);
                break;
        }
    }
}
