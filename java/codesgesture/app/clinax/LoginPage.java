package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class LoginPage extends AppCompatActivity implements JsonCallbacks {
    Button btsave;
    EditText pass,mobileno;
    TextView btregister;
    ArrayList<NetParam> params;
    UserModel userModel;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        s=getString(R.string.con);
        CheckLogins();

        mobileno=findViewById(R.id.mobileno);
        pass=findViewById(R.id.pass);
        btsave=findViewById(R.id.btsave);
        btregister=findViewById(R.id.btregister);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validte()) {
                    params = new ArrayList<NetParam>();
                    CallJson jc = new CallJson(LoginPage.this);
                    params.add(new NetParam("mobileno", mobileno.getText().toString()));
                    params.add(new NetParam("password", pass.getText().toString()));
                    jc.SendRequest(s,"patient_login", params, LoginPage.this, "patient_login", "Please wait while verifying..");
                }
            }
        });

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,PageRegister.class));
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
                Intent act = new Intent(LoginPage.this, Dashboard.class);
                startActivity(act);
                UserUtil.ShowMsg("Login Successfully !", LoginPage.this);
                finish();
            } else {
                Utility.ShowMEssage(LoginPage.this, "Invalid details !");
            }
        } catch (JSONException e) {
            Utility.ShowMEssage(LoginPage.this, "Invalid details !");
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
        return valid;
    }

    private void CheckLogins() {
        userModel = SessionManage.getCurrentUser(this);
        if (userModel != null) {
            if (userModel.getPatient_id() != null) {
                Intent act = new Intent(LoginPage.this, Dashboard.class);
                startActivity(act);
                finish();
            }
        }
    }

}