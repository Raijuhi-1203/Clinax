package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Models.CityModel;
import codesgesture.app.clinax.Models.StateModel;
import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.CallJsonWithoutProgress;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Services.UserUtil;
import codesgesture.app.clinax.Services.Utility;
import codesgesture.app.clinax.Utils.SessionManage;


public class ProfileFragment extends Fragment {
    TextView btedit,btsave;
    LinearLayout view_layout,edit_layout;
    Spinner sp_gender,sp_city,sp_state;
    String s;
    UserModel userModel;
    private static final String[] gender = {"Select Gender", "Male", "Female"};
    TextView name,mobile,mail,genders,citys,adds,states,ages,fathers,mothers,husbands;
    ArrayList<StateModel> stateModels=new ArrayList<>();
    ArrayList<CityModel> cityModels=new ArrayList<>();
    ArrayAdapter<StateModel> arrayAdapter;
    ArrayAdapter<CityModel> cityModelArrayAdapter;
    String stid,ctid,stnm,ctnm;
    EditText ename,eadd,emobile,email,ehusband,efather,emother,eage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_userac, container, false);

        userModel=(UserModel) SessionManage.getCurrentUser(getActivity());
        s=getString(R.string.con);

        BindIds(view);

        sp_gender=view.findViewById(R.id.sp_gender);
        sp_state=view.findViewById(R.id.sp_state);
        sp_city=view.findViewById(R.id.sp_city);

        btsave=view.findViewById(R.id.btsave);
        btedit=view.findViewById(R.id.btedit);
        edit_layout=view.findViewById(R.id.edit_layout);
        view_layout=view.findViewById(R.id.view_layout);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfile();
            }
        });

        btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_layout.setVisibility(View.VISIBLE);
                view_layout.setVisibility(View.GONE);
                btsave.setVisibility(View.VISIBLE);
                btedit.setVisibility(View.GONE);
            }
        });

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,gender);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter4);

        stateModels = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<StateModel>(getActivity(), android.R.layout.simple_spinner_item, stateModels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_state.setAdapter(arrayAdapter);
        BindState();

        cityModels = new ArrayList<>();
        cityModelArrayAdapter = new ArrayAdapter<CityModel>(getActivity(), android.R.layout.simple_spinner_item, cityModels);
        cityModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_city.setAdapter(cityModelArrayAdapter);

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = sp_state.getSelectedItemPosition();
                stid = String.valueOf(stateModels.get(pos).getState_id());
                stnm = String.valueOf(stateModels.get(pos).getState_name());
                BindCity(stid);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = sp_city.getSelectedItemPosition();
                ctid = String.valueOf(cityModels.get(pos).getDistrict_id());
                ctnm = String.valueOf(cityModels.get(pos).getDistrict_name());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;

    }

    private void UpdateProfile() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(getActivity());
        param.add(new NetParam("patient_city_id",ctid));
        param.add(new NetParam("patient_city_name",ctnm));
        param.add(new NetParam("patient_state_name",stnm));
        param.add(new NetParam("patient_state_id",stid));
        param.add(new NetParam("patient_address",eadd.getText().toString()));
        param.add(new NetParam("patient_email",email.getText().toString()));
        param.add(new NetParam("patient_gender",sp_gender.getSelectedItem().toString()));
        param.add(new NetParam("patient_age",eage.getText().toString()));
        param.add(new NetParam("patient_father",efather.getText().toString()));
        param.add(new NetParam("patient_mother",emother.getText().toString()));
        param.add(new NetParam("patient_husband",ehusband.getText().toString()));
        param.add(new NetParam("patient_mobile_no_1",emobile.getText().toString()));
        param.add(new NetParam("patient_mobile_no_2",emobile.getText().toString()));
        param.add(new NetParam("patient_id",userModel.getPatient_id()));
        jc.SendRequest(s,"update_patient", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserModel sd = new UserModel();
                try {
                    JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
                    if (obj.length() != 1) {
                        sd.setAuto_id(obj.getString("auto_id"));
                        sd.setId(obj.getString("id"));
                        sd.setPatient_id(obj.getString("patient_id"));
                        sd.setPatient_prefix(obj.getString("patient_prefix"));
                        sd.setPatient_name(obj.getString("patient_name"));
                        sd.setPatient_gender(obj.getString("patient_gender"));
                        sd.setPatient_age(obj.getString("patient_age"));
                        sd.setPatient_father(obj.getString("patient_father"));
                        sd.setPatient_mother(obj.getString("patient_mother"));
                        sd.setPatient_husband(obj.getString("patient_husband"));
                        sd.setPatient_mobile_no_1(obj.getString("patient_mobile_no_1"));
                        sd.setPatient_mobile_no_2(obj.getString("patient_mobile_no_2"));
                        sd.setPatient_email(obj.getString("patient_email"));
                        sd.setPatient_password(obj.getString("patient_password"));
                        sd.setPatient_address(obj.getString("patient_address"));
                        sd.setPatient_photo(obj.getString("patient_photo"));
                        sd.setPatient_state_id(obj.getString("patient_state_id"));
                        sd.setPatient_state_name(obj.getString("patient_state_name"));
                        sd.setPatient_city_id(obj.getString("patient_city_id"));
                        sd.setPatient_city_name(obj.getString("patient_city_name"));
                        sd.setPatient_create_date(obj.getString("patient_create_date"));
                        sd.setPatient_create_time(obj.getString("patient_create_time"));
                        sd.setPatient_modify_date(obj.getString("patient_modify_date"));
                        sd.setOtp_verify(obj.getString("otp_verify"));
                        sd.setPatient_status(obj.getString("patient_status"));
                        SessionManage.SetCustomerSessions(getActivity(), sd);
                        Intent act = new Intent(getActivity(), Dashboard.class);
                        startActivity(act);
                        UserUtil.ShowMsg("Profile updated Successfully !", getActivity());
                        getActivity().finish();
                    } else {
                        Utility.ShowMEssage(getActivity(), "Invalid details !");
                    }
                } catch (JSONException e) {
                    Utility.ShowMEssage(getActivity(), "Invalid details !");
                    e.printStackTrace();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Please wait while getting..");
    }

    private void BindCity(String stid) {
        cityModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(getActivity());
        param.add(new NetParam("state_id",stid));
        jc.SendRequest(s,"get_city", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    CityModel mod = new CityModel();
                    mod.setDistrict_name(obj.getString("district_name"));
                    mod.setDistrict_id(obj.getString("district_id"));
                    cityModels.add(mod);
                    cityModelArrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Please wait while getting..");
    }

    private void BindState() {
        stateModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(getActivity());
        jc.SendRequest(s,"get_state", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    StateModel mod = new StateModel();
                    mod.setState_id(obj.getString("state_id"));
                    mod.setState_name(obj.getString("state_name"));
                    stateModels.add(mod);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Please wait while getting..");
    }

    private void BindIds(View view) {
        name=view.findViewById(R.id.name); mobile=view.findViewById(R.id.mobile);
        mail=view.findViewById(R.id.mail); genders=view.findViewById(R.id.genders);
        adds=view.findViewById(R.id.adds);citys=view.findViewById(R.id.citys);
        ages=view.findViewById(R.id.ages);states=view.findViewById(R.id.states);
        fathers=view.findViewById(R.id.fathers);mothers=view.findViewById(R.id.mothers);
        husbands=view.findViewById(R.id.husbands);

        name.setText(userModel.getPatient_name());mobile.setText(userModel.getPatient_mobile_no_1());
        mail.setText(userModel.getPatient_email());genders.setText(userModel.getPatient_gender());
        adds.setText(userModel.getPatient_address());citys.setText(userModel.getPatient_city_name());
        states.setText(userModel.getPatient_state_name());ages.setText(userModel.getPatient_age());
        fathers.setText(userModel.getPatient_father());mothers.setText(userModel.getPatient_mother());
        husbands.setText(userModel.getPatient_husband());

        ename=view.findViewById(R.id.ename); eadd=view.findViewById(R.id.eadd);
        emobile=view.findViewById(R.id.emobile);eage=view.findViewById(R.id.eage);
        email=view.findViewById(R.id.email);ehusband=view.findViewById(R.id.ehusband);
        efather=view.findViewById(R.id.efather);emother=view.findViewById(R.id.emother);

        ename.setText(userModel.getPatient_name());emobile.setText(userModel.getPatient_mobile_no_1());
        email.setText(userModel.getPatient_email());eadd.setText(userModel.getPatient_address());
        eage.setText(userModel.getPatient_age());efather.setText(userModel.getPatient_father());
        emother.setText(userModel.getPatient_mother());ehusband.setText(userModel.getPatient_husband());

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

}
