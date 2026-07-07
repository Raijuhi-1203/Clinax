package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Adapter.FeedbackAdapter;
import codesgesture.app.clinax.Adapter.SpecialityAdapter;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.FeedbackModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;

public class DoctorProfile extends AppCompatActivity {

    DoctorModel doctorModel;
    TextView heading,descp,feedhead;
    Button btfeedback;
    RecyclerView rvfeedback;
    FeedbackAdapter feedbackAdapter;
    ArrayList<FeedbackModel> feedbackModels=new ArrayList<>();
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_doc_profile);
        doctorModel=(DoctorModel)getIntent().getSerializableExtra("data");
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        heading=findViewById(R.id.heading);
        descp=findViewById(R.id.descp);
        feedhead=findViewById(R.id.feedhead);
        btfeedback=findViewById(R.id.btfeedback);
        rvfeedback=findViewById(R.id.rvfeedback);

        String str = doctorModel.getDoctors_name()+ ","+doctorModel.getDoctors_qualification()+", is a renowned "+ doctorModel.getDoctors_speciality() +"in Gorakhpur. He is available at "+doctorModel.getDoctors_clinic_name()+" located at "+doctorModel.getDoctors_address()+", Gorakhpur. He is specialized in treating a wide range of general medical diseases such as "+doctorModel.getSymptoms_check()+" , etc. You can book appointment and consult online with "+doctorModel.getDoctors_name()+", top Doctor in Gorakhpur through Clinax.";
        heading.setText(" Profile of "+doctorModel.getDoctors_name());
        feedhead.setText(" Feedback for "+doctorModel.getDoctors_name());
        descp.setText(str);

        btfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorProfile.this, FeedBackForm.class);
                intent.putExtra("data",doctorModel);
                startActivity(intent);
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(DoctorProfile.this, 1);
        rvfeedback.setLayoutManager(mLayoutManager);
        feedbackAdapter = new FeedbackAdapter(DoctorProfile.this, feedbackModels, R.layout.item_review);
        rvfeedback.setAdapter(feedbackAdapter);
        rvfeedback.setItemViewCacheSize(feedbackModels.size());
        GetData();


    }

    private void GetData() {
        feedbackModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(DoctorProfile.this);
        param.add(new NetParam("doctor_id",doctorModel.getDoctors_id()));
        jc.SendRequest(s,"get_feedback", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    FeedbackModel product = new FeedbackModel();
                    product.setReviewer_comments(obj.getString("reviewer_comments"));
                    product.setReviewer_name(obj.getString("reviewer_name"));
                    product.setReviewer_rating(obj.getString("reviewer_rating"));
                    feedbackModels.add(product);
                }
                feedbackAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }
}
