package codesgesture.app.clinax;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Models.DateModel;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Services.UserUtil;
import codesgesture.app.clinax.Utils.SessionManage;

public class FeedBackForm extends AppCompatActivity {
    EditText txfeedback;
    Button btfeedback;
    RatingBar rating;
    DoctorModel doctorModel;
    UserModel userModel;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_form);
        userModel=(UserModel) SessionManage.getCurrentUser(getApplicationContext());
        doctorModel=(DoctorModel) getIntent().getSerializableExtra("data");
        s=getString(R.string.con);

        txfeedback=findViewById(R.id.txfeedback);
        btfeedback=findViewById(R.id.btfeedback);
        rating=findViewById(R.id.rating);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText(doctorModel.getDoctors_name());

        btfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txfeedback.getText().length()==0){
                    txfeedback.setError("Please give feedback");
                }else {
                    AddFeedback();
                }
            }
        });

    }

    private void AddFeedback() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(FeedBackForm.this);
        param.add(new NetParam("doctor_id",doctorModel.getDoctors_id()));
        param.add(new NetParam("reviewer_id",userModel.getPatient_id()));
        param.add(new NetParam("reviewer_name",userModel.getPatient_name()));
        param.add(new NetParam("reviewer_rating",String.valueOf((int)rating.getRating())));
        param.add(new NetParam("reviewer_comments",txfeedback.getText().toString()));
        jc.SendRequest(s,"add_feedback", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Thank you for valuable feedback!",FeedBackForm.this);
                finish();
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");

    }
}
