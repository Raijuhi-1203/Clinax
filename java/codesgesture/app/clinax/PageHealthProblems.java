package codesgesture.app.clinax;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Adapter.ProblemAdapter;
import codesgesture.app.clinax.Adapter.SpecialityAdapter;
import codesgesture.app.clinax.Models.HProblemModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;

public class PageHealthProblems extends AppCompatActivity {
    RecyclerView rvproblem,rvspeciality;
    ArrayList<SpecialityModel> specialityModels=new ArrayList<>();
    ArrayList<HProblemModel> hProblemModels=new ArrayList<>();

    SpecialityAdapter specialityAdapter;
    ProblemAdapter problemAdapter;
    String s;
    TextView bt_view_spcilist,bt_consult_healthprblm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthproblem_activity);
        s=getString(R.string.con);
        rvproblem = findViewById(R.id.rvproblem);
        rvspeciality = findViewById(R.id.rvspeciality);

        bt_consult_healthprblm = findViewById(R.id.bt_consult_healthprblm);
        bt_view_spcilist = findViewById(R.id.bt_view_spcilist);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title=findViewById(R.id.title);
        title.setText("Consult Doctor With Speciality and symtomps");

        GridLayoutManager mLayoutManager = new GridLayoutManager(PageHealthProblems.this, 3);
        rvspeciality.setLayoutManager(mLayoutManager);
        specialityAdapter = new SpecialityAdapter(PageHealthProblems.this, specialityModels, R.layout.item_problem);
        rvspeciality.setAdapter(specialityAdapter);
        rvspeciality.setItemViewCacheSize(specialityModels.size());

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(PageHealthProblems.this, 3);
        rvproblem.setLayoutManager(mLayoutManager2);
        problemAdapter = new ProblemAdapter(PageHealthProblems.this, hProblemModels, R.layout.item_problem);
        rvproblem.setAdapter(problemAdapter);
        rvproblem.setItemViewCacheSize(hProblemModels.size());

        GetSPData();
        GetHPData();


    }

    private void GetSPData() {
        specialityModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageHealthProblems.this);
        jc.SendRequest(s,"get_speciality", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    SpecialityModel product = new SpecialityModel();
                    product.setSpeciality_icon(obj.getString("speciality_icon"));
                    product.setSpeciality_name(obj.getString("speciality_name"));
                    product.setId(obj.getString("id"));
                    specialityModels.add(product);
                }
                specialityAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    private void GetHPData() {
        hProblemModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageHealthProblems.this);
        jc.SendRequest(s,"get_symptoms", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    HProblemModel product = new HProblemModel();
                    product.setSymptoms_icon(obj.getString("symptoms_icon"));
                    product.setSymptoms_name(obj.getString("symptoms_name"));
                    product.setId(obj.getString("id"));
                    hProblemModels.add(product);
                }
                problemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");

    }

}