package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import codesgesture.app.clinax.Adapter.DoctorAdapter;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;

public class AppoinmentFragment extends Fragment {

    EditText search;
    RecyclerView rvdoctors;
    DoctorAdapter doctorAdapter;
    ArrayList<DoctorModel> doctorModels=new ArrayList<>();
    String s;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appoinment, container, false);
        s=getString(R.string.con);
        search=view.findViewById(R.id.search);
        rvdoctors=view.findViewById(R.id.rvdoctors);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        rvdoctors.setLayoutManager(mLayoutManager);
        doctorAdapter = new DoctorAdapter(getActivity(), doctorModels, R.layout.item_doctor);
        rvdoctors.setAdapter(doctorAdapter);
        rvdoctors.setItemViewCacheSize(doctorModels.size());

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        String cdate = timeStampFormat.format(myDate);

        SimpleDateFormat timeStampFormat2 = new SimpleDateFormat("EEEE");
        Date myDate2 = new Date();
        String cday = timeStampFormat2.format(myDate2);

        GetDoctor(cdate,cday);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    private void GetDoctor(String cdate, String cday) {
        doctorModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(getActivity());
        param.add(new NetParam("cdate",cdate));
        param.add(new NetParam("cday",cday));
        jc.SendRequest(s,"get_doctor", param, new JsonCallbacks() {
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("Refresh..", "Yes");
        }
    }

    void filter(String text) {
        ArrayList<DoctorModel> temp = new ArrayList();
        for (DoctorModel d : doctorModels) {
            if(d!=null){
                if (d.getDoctors_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        doctorAdapter.updateList(temp);
    }
}