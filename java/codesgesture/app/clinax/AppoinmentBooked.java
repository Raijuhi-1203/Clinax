package codesgesture.app.clinax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.DateFormate;
import codesgesture.app.clinax.Utils.SessionManage;


public class AppoinmentBooked extends AppCompatActivity {
    Button btdone;
    TextView descp,mob,cdt;
    UserModel userModel;
    String selectdt,selectime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appoinment_booked);
        userModel=(UserModel) SessionManage.getCurrentUser(getApplicationContext());
        selectdt=getIntent().getStringExtra("selectdt");
        selectime=getIntent().getStringExtra("selectime");

        mob=findViewById(R.id.mob);
        descp=findViewById(R.id.descp);
        btdone=findViewById(R.id.btdone);
        cdt=findViewById(R.id.cdt);

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd MMM yyyy");
        Date myDate = new Date();
        cdt.setText(timeStampFormat.format(myDate));

        mob.setText("+91-"+userModel.getPatient_mobile_no_1());

        String msg ="Dear User, your appointment is scheduled on "+ selectdt +" at "+ selectime +",Please reached at hospital/clinic before 20 minutes.";
        descp.setText(msg);

        btdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppoinmentBooked.this, Dashboard.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
