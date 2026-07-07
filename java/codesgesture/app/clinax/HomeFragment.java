package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import codesgesture.app.clinax.Adapter.ProblemAdapter;
import codesgesture.app.clinax.Adapter.SpecialityAdapter;
import codesgesture.app.clinax.Models.HProblemModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Services.UserUtil;

public class HomeFragment extends Fragment{

    SliderLayout image_slider;
    HashMap<Integer,Integer> file_maps = new HashMap<Integer, Integer>();
    Button btconsult,btappoinment;
    RecyclerView rvproblem,rvspeciality;
    ArrayList<SpecialityModel> specialityModels=new ArrayList<>();
    ArrayList<HProblemModel> hProblemModels=new ArrayList<>();

    SpecialityAdapter specialityAdapter;
    ProblemAdapter problemAdapter;
    String s;
    TextView bt_view_spcilist,bt_consult_healthprblm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        s=getString(R.string.con);
        image_slider = view.findViewById(R.id.pager);
        btappoinment = view.findViewById(R.id.btappoinment);
        btconsult = view.findViewById(R.id.btconsult);

        rvproblem = view.findViewById(R.id.rvproblem);
        rvspeciality = view.findViewById(R.id.rvspeciality);

        bt_consult_healthprblm = view.findViewById(R.id.bt_consult_healthprblm);
        bt_view_spcilist = view.findViewById(R.id.bt_view_spcilist);

        bt_view_spcilist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ConsultActivity.class));
            }
        });
        bt_consult_healthprblm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ActivityHealthProblem.class));
            }
        });

        btconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PageHealthProblems.class));
            }
        });
        btappoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PageDoctors.class));
            }
        });

        file_maps.put(1,R.drawable.sl1);
        file_maps.put(2,R.drawable.sl2);
        inflateImageSlider();

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvspeciality.setLayoutManager(mLayoutManager);
        specialityAdapter = new SpecialityAdapter(getActivity(), specialityModels, R.layout.item_problem);
        rvspeciality.setAdapter(specialityAdapter);
        rvspeciality.setItemViewCacheSize(specialityModels.size());

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(getActivity(), 3);
        rvproblem.setLayoutManager(mLayoutManager2);
        problemAdapter = new ProblemAdapter(getActivity(), hProblemModels, R.layout.item_problem);
        rvproblem.setAdapter(problemAdapter);
        rvproblem.setItemViewCacheSize(hProblemModels.size());

        GetSPData();
        GetHPData();

        return view;
    }

    private void GetSPData() {
        specialityModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(getActivity());
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
        CallJson jc = new CallJson(getActivity());
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

    private void inflateImageSlider() {
        for(Integer name : file_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            defaultSliderView.image(file_maps.get(name)); // adding images here
            defaultSliderView.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            image_slider.addSlider(defaultSliderView);

        }
       // image_slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        image_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        image_slider.setCustomAnimation(new DescriptionAnimation());
        image_slider.setDuration(5000);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("Refresh...", "Yes");
        }
    }

}