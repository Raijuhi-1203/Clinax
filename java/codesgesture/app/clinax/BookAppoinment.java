package codesgesture.app.clinax;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import codesgesture.app.clinax.Adapter.DateAdapter;
import codesgesture.app.clinax.Adapter.DoctorAdapter;
import codesgesture.app.clinax.Adapter.SlotAdapter;
import codesgesture.app.clinax.Models.DateModel;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.SlotModel;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Services.UserUtil;
import codesgesture.app.clinax.Utils.SessionManage;
import codesgesture.app.clinax.interfaces.ExtraCallBack;

public class BookAppoinment extends AppCompatActivity {
    Button btcontinue;
    DoctorModel doctorModel;
    UserModel userModel;
    ImageView img;
    TextView docnm, qualify, fee;
    RecyclerView rvdate, rvtime;
    String s, sm;
    DateAdapter dateAdapter;
    ArrayList<DateModel> dateModels = new ArrayList<>();
    ArrayList<SlotModel> slotModels = new ArrayList<>();
    SlotAdapter slotAdapter;
    String cdate, cday;
    String selectdt,selectime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appoinment);
        userModel = (UserModel) SessionManage.getCurrentUser(getApplicationContext());
        doctorModel = (DoctorModel) getIntent().getSerializableExtra("data");
        s = getString(R.string.con);
        sm = getString(R.string.maincon);

        ImageView bt_back = findViewById(R.id.btback);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = findViewById(R.id.title);
        title.setText("Book Appoinment");

        img = findViewById(R.id.img);
        docnm = findViewById(R.id.docnm);
        qualify = findViewById(R.id.qualify);
        fee = findViewById(R.id.fee);
        rvdate = findViewById(R.id.rvdate);
        rvtime = findViewById(R.id.rvtime);

        Uri uri = Uri.parse(sm + doctorModel.getDoctors_photo());
        Glide.with(BookAppoinment.this).load(uri).into(img);

        docnm.setText(doctorModel.getDoctors_name());
        qualify.setText(doctorModel.getDoctors_qualification());
        fee.setText("₹ " + doctorModel.getDoctors_fee());


        btcontinue = findViewById(R.id.btcontinue);
        btcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBooking();

               // startActivity(new Intent(BookAppoinment.this, AppoinmentBooked.class));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvdate.setLayoutManager(layoutManager);
        dateAdapter = new DateAdapter(BookAppoinment.this, dateModels, R.layout.item_date);
        dateAdapter.ecb = new ExtraCallBack() {
            @Override
            public void OnCompleted(String arguments) {
                selectdt = arguments;
                FetchSlot(arguments);
            }
        };
        rvdate.smoothScrollToPosition(0);
        rvdate.setAdapter(dateAdapter);
        rvdate.setItemViewCacheSize(dateModels.size());

        GridLayoutManager mLayoutManager = new GridLayoutManager(BookAppoinment.this, 3);
        rvtime.setLayoutManager(mLayoutManager);
        slotAdapter = new SlotAdapter(BookAppoinment.this, slotModels, R.layout.item_time);
        slotAdapter.ecb=new ExtraCallBack() {
            @Override
            public void OnCompleted(String arguments) {
                selectime=arguments;
            }
        };
        rvtime.setAdapter(slotAdapter);
        rvtime.setItemViewCacheSize(slotModels.size());

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        cdate = timeStampFormat.format(myDate);

        SimpleDateFormat timeStampFormat2 = new SimpleDateFormat("EEEE");
        Date myDate2 = new Date();
        cday = timeStampFormat2.format(myDate2);

        GetDate();

    }

    private void AddBooking() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(BookAppoinment.this);
        param.add(new NetParam("doctor_id",doctorModel.getDoctors_id()));
        param.add(new NetParam("doctor_name",doctorModel.getDoctors_name()));
        param.add(new NetParam("patient_id",userModel.getPatient_id()));
        param.add(new NetParam("patient_name",userModel.getPatient_name()));
        param.add(new NetParam("registration_amount",doctorModel.getDoctors_fee()));
        param.add(new NetParam("schedule_date",selectdt));
        param.add(new NetParam("schedule_time",selectime));
        jc.SendRequest(s,"patient_booking", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                if(check()){
                    Intent intent=new Intent(BookAppoinment.this,AppoinmentBooked.class);
                    intent.putExtra("selectdt",selectdt);
                    intent.putExtra("selectime",selectime);
                    startActivity(intent);
                }else {
                    UserUtil.ShowMsg("Please select Slot",BookAppoinment.this);
                }
            }
            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    private void FetchSlot(String arguments)  {
        slotModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(BookAppoinment.this);
        param.add(new NetParam("doctor_id",doctorModel.getDoctors_id()));
        param.add(new NetParam("cdate",arguments));
        param.add(new NetParam("cday",cday));
        jc.SendRequest(s,"get_slot", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    SlotModel product = new SlotModel();
                    product.setSchedule_time(obj.getString("schedule_time"));
                    product.setSchedule_no_of_slot(obj.getString("schedule_no_of_slot"));
                    product.setbookingstatus(obj.getString("bookingstatus"));
                    slotModels.add(product);
                }
                slotAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    private void GetDate() {
        dateModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(BookAppoinment.this);
        param.add(new NetParam("doctor_id",doctorModel.getDoctors_id()));
        param.add(new NetParam("cdate",cdate));
        jc.SendRequest(s,"get_date", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    DateModel product = new DateModel();
                    product.setSchedule_date(obj.getString("schedule_date"));
                    dateModels.add(product);
                }
                dateAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }


    private boolean check() {
        Boolean valid = true;
        if (selectime == null || selectime.equals(" ") || selectime.equals("")){
            valid=false;
        }
        return valid;
    }
}