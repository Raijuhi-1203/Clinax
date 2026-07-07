package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Services.UserUtil;
import codesgesture.app.clinax.Services.Utility;
import codesgesture.app.clinax.Utils.SessionManage;

public class PageRegister extends AppCompatActivity implements JsonCallbacks {
    Button btsave;
    EditText pass,mobileno,name;
    ArrayList<NetParam> params;
    String s;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        s=getString(R.string.con);
        mobileno=findViewById(R.id.mobileno);
        pass=findViewById(R.id.pass);
        name=findViewById(R.id.name);
        btsave=findViewById(R.id.btsave);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validte()) {
                    params = new ArrayList<NetParam>();
                    CallJson jc = new CallJson(PageRegister.this);
                    params.add(new NetParam("mobile", mobileno.getText().toString()));
                    params.add(new NetParam("password", pass.getText().toString()));
                    params.add(new NetParam("name", name.getText().toString()));
                    jc.SendRequest(s,"register_patient", params, PageRegister.this, "register_patient", "Please wait while verifying..");
                }
            }
        });

    }

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
                SessionManage.SetCustomerSessions(getApplicationContext(), sd);
                Intent act = new Intent(PageRegister.this, Dashboard.class);
                startActivity(act);
                UserUtil.ShowMsg("Login Successfully !", PageRegister.this);
                finish();
            } else {
                Utility.ShowMEssage(PageRegister.this, "Invalid details !");
            }
        } catch (JSONException e) {
            Utility.ShowMEssage(PageRegister.this, "Invalid details !");
            e.printStackTrace();
        }
    }

    @Override
    public void onPostError(String msg) {

    }

    private boolean validte() {
        Boolean valid = true;
        if (mobileno.getText().length()==0){
            mobileno.setError("Please enter mobile no");
            valid=false;
        }
        else  if (pass.getText().length()==0){
            pass.setError("Please enter password");
            valid=false;
        }
        else  if (name.getText().length()==0){
            name.setError("Please enter name");
            valid=false;
        }
        return valid;
    }

}
