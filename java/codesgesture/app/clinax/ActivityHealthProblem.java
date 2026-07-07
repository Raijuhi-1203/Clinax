package codesgesture.app.clinax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Adapter.ProblemAdapter;
import codesgesture.app.clinax.Models.HProblemModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;


public class ActivityHealthProblem extends AppCompatActivity {

    ArrayList<HProblemModel> hProblemModels=new ArrayList<>();
    ProblemAdapter problemAdapter;
    String s;
    RecyclerView rvproblem;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_problem_activity);
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Health Problems");

        rvproblem=findViewById(R.id.rvproblem);
        search=findViewById(R.id.search);

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(ActivityHealthProblem.this, 3);
        rvproblem.setLayoutManager(mLayoutManager2);
        problemAdapter = new ProblemAdapter(ActivityHealthProblem.this, hProblemModels, R.layout.item_problem);
        rvproblem.setAdapter(problemAdapter);
        rvproblem.setItemViewCacheSize(hProblemModels.size());

        GetHPData();

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

    }

    void filter(String text) {
        ArrayList<HProblemModel> temp = new ArrayList();
        for (HProblemModel d : hProblemModels) {
            if(d!=null){
                if (d.getSymptoms_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        problemAdapter.updateList(temp);
    }

    private void GetHPData() {
        hProblemModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ActivityHealthProblem.this);
        jc.SendRequest(s,"get_symptoms_all", param, new JsonCallbacks() {
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