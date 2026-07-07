package codesgesture.app.clinax;

import android.content.Intent;
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

import codesgesture.app.clinax.Adapter.SpecialityAdapter;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;

public class ConsultActivity extends AppCompatActivity {
    RecyclerView rvspeciality;
    ArrayList<SpecialityModel> specialityModels=new ArrayList<>();
    SpecialityAdapter specialityAdapter;
    String s;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_activity);
        s=getString(R.string.con);
        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title=findViewById(R.id.title);
        title.setText("Consult Doctor With Speciality");

        rvspeciality=findViewById(R.id.rvspeciality);
        search=findViewById(R.id.search);

        GridLayoutManager mLayoutManager = new GridLayoutManager(ConsultActivity.this, 3);
        rvspeciality.setLayoutManager(mLayoutManager);
        specialityAdapter = new SpecialityAdapter(ConsultActivity.this, specialityModels, R.layout.item_problem);
        rvspeciality.setAdapter(specialityAdapter);
        rvspeciality.setItemViewCacheSize(specialityModels.size());
        GetSPData();

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
        ArrayList<SpecialityModel> temp = new ArrayList();
        for (SpecialityModel d : specialityModels) {
            if(d!=null){
                if (d.getSpeciality_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                }}
        }
        specialityAdapter.updateList(temp);
    }

    private void GetSPData() {
        specialityModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ConsultActivity.this);
        jc.SendRequest(s,"get_speciality_all", param, new JsonCallbacks() {
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

}