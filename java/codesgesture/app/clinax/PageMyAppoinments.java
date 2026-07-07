package codesgesture.app.clinax;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Adapter.BookingAdapter;
import codesgesture.app.clinax.Adapter.DoctorAdapter;
import codesgesture.app.clinax.Models.BookingModel;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Utils.SessionManage;

public class PageMyAppoinments extends AppCompatActivity {
    BookingAdapter bookingAdapter;
    ArrayList<BookingModel> bookingModels=new ArrayList<>();
    RecyclerView rvbooking;
    UserModel userModel;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_appoinment);
        userModel=(UserModel) SessionManage.getCurrentUser(getApplicationContext());
        s=getString(R.string.con);

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title=findViewById(R.id.title);
        title.setText("My Appoinments");

        rvbooking=findViewById(R.id.rvbooking);

        GridLayoutManager mLayoutManager = new GridLayoutManager(PageMyAppoinments.this, 1);
        rvbooking.setLayoutManager(mLayoutManager);
        bookingAdapter = new BookingAdapter(PageMyAppoinments.this, bookingModels, R.layout.item_appoinment);
        rvbooking.setAdapter(bookingAdapter);
        rvbooking.setItemViewCacheSize(bookingModels.size());
        GetData();

    }

    private void GetData() {
        bookingModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageMyAppoinments.this);
        param.add(new NetParam("patient_id",userModel.getPatient_id()));
        jc.SendRequest(s,"get_my_appoinment", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    BookingModel product = new BookingModel();
                    product.setAuto_id(obj.getString("auto_id"));
                    product.setId(obj.getString("id"));
                    product.setDoctors_clinic_name(obj.getString("doctors_clinic_name"));
                    product.setDoctors_address(obj.getString("doctors_address"));
                    product.setDoctors_state_name(obj.getString("doctors_state_name"));
                    product.setDoctors_city_name(obj.getString("doctors_city_name"));
                    product.setAccess_token_no(obj.getString("access_token_no"));
                    product.setDoctor_id(obj.getString("doctor_id"));
                    product.setDoctor_name(obj.getString("doctor_name"));
                    product.setBooking_date(obj.getString("booking_date"));
                    product.setBooking_type(obj.getString("booking_type"));
                    product.setBooking_time(obj.getString("booking_time"));
                    product.setTotal_booking_amount(obj.getString("total_booking_amount"));
                    product.setPatient_id(obj.getString("patient_id"));
                    product.setPatient_name(obj.getString("patient_name"));
                    product.setPayment_status(obj.getString("payment_status"));
                    product.setSchedule_date(obj.getString("schedule_date"));
                    product.setSchedule_time(obj.getString("schedule_time"));
                    product.setBooking_id(obj.getString("booking_id"));
                    product.setBooking_status(obj.getString("booking_status"));

                    bookingModels.add(product);
                }
                bookingAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }
}
