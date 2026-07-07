package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import codesgesture.app.clinax.Adapter.DoctorAdapter;
import codesgesture.app.clinax.Adapter.SpecialityAdapter;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.HProblemModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Utils.SessionManage;

public class ActivityAllSDoctors extends AppCompatActivity {

    RecyclerView rvdoctors;
    SpecialityModel hProblemModel;
    UserModel userModel;
    DoctorAdapter doctorAdapter;
    ArrayList<DoctorModel> doctorModels=new ArrayList<>();
    String s;
    String cdate,cday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_doctors_activity);
        userModel=(UserModel) SessionManage.getCurrentUser(getApplicationContext());
        hProblemModel=(SpecialityModel)getIntent().getSerializableExtra("data");
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title=findViewById(R.id.title);
        title.setText("Doctors");

        rvdoctors=findViewById(R.id.rvdoctors);

        GridLayoutManager mLayoutManager = new GridLayoutManager(ActivityAllSDoctors.this, 1);
        rvdoctors.setLayoutManager(mLayoutManager);
        doctorAdapter = new DoctorAdapter(ActivityAllSDoctors.this, doctorModels, R.layout.item_doctor);
        rvdoctors.setAdapter(doctorAdapter);
        rvdoctors.setItemViewCacheSize(doctorModels.size());

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        cdate = timeStampFormat.format(myDate);

        SimpleDateFormat timeStampFormat2 = new SimpleDateFormat("EEEE");
        Date myDate2 = new Date();
        cday = timeStampFormat2.format(myDate2);

        GetDoctor();

    }

    private void GetDoctor() {
        doctorModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ActivityAllSDoctors.this);
        param.add(new NetParam("department_id",hProblemModel.getId()));
        param.add(new NetParam("cdate",cdate));
        param.add(new NetParam("cday",cday));
        jc.SendRequest(s,"get_speciality_doctor", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    DoctorModel product = new DoctorModel();
                    product.setAuto_id(obj.getString("auto_id"));
                    product.setId(obj.getString("id"));
                    product.setDoctors_id(obj.getString("doctors_id"));
                    product.setDoctors_name(obj.getString("doctors_name"));
                    product.setDoctors_reg_no(obj.getString("doctors_reg_no"));
                    product.setDoctors_clinic_name(obj.getString("doctors_clinic_name"));
                    product.setDoctors_mobile_no(obj.getString("doctors_mobile_no"));
                    product.setDoctor_password(obj.getString("doctor_password"));
                    product.setDepartment_id(obj.getString("department_id"));
                    product.setSub_department_name(obj.getString("sub_department_name"));
                    product.setDepartment_name(obj.getString("department_name"));
                    product.setSymptoms_check(obj.getString("symptoms_check"));
                    product.setDoctor_description(obj.getString("doctor_description"));
                    product.setDoctors_speciality(obj.getString("doctors_speciality"));
                    product.setDoctors_experience(obj.getString("doctors_experience"));
                    product.setDoctors_qualification(obj.getString("doctors_qualification"));
                    product.setOther_qualification_certification(obj.getString("other_qualification_certification"));
                    product.setMedical_council(obj.getString("medical_council"));
                    product.setDoctors_photo(obj.getString("doctors_photo"));
                    product.setDoctors_by(obj.getString("doctors_by"));
                    product.setDoctors_fee(obj.getString("doctors_fee"));
                    product.setDoctors_address(obj.getString("doctors_address"));
                    product.setDoctors_state_id(obj.getString("doctors_state_id"));
                    product.setDoctors_state_name(obj.getString("doctors_state_name"));
                    product.setDoctors_city_id(obj.getString("doctors_city_id"));
                    product.setDoctors_city_name(obj.getString("doctors_city_name"));
                    product.setDoctors_join_date(obj.getString("doctors_join_date"));
                    product.setDoctors_modify_date(obj.getString("doctors_modify_date"));
                    product.setDoctors_status(obj.getString("doctors_status"));
                    product.setAccess_token_no(obj.getString("access_token_no"));
                    product.setStart_time(obj.getString("start_time"));
                    product.setEnd_time(obj.getString("end_time"));
                    product.setAval(obj.getString("aval"));
                    product.setFeedback(obj.getString("feedback"));
                    doctorModels.add(product);
                }
                doctorAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }
}