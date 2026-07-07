package codesgesture.app.clinax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import codesgesture.app.clinax.ActivityAllHPDoctors;
import codesgesture.app.clinax.BookAppoinment;
import codesgesture.app.clinax.DoctorProfile;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.Models.DoctorModel;
import codesgesture.app.clinax.R;
import codesgesture.app.clinax.Utils.SessionManage;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private ArrayList<DoctorModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String s,sm;

    public DoctorAdapter(Context context, ArrayList<DoctorModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getPatient_id();
    }

    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new DoctorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DoctorAdapter.ViewHolder holder, final int i) {
        final DoctorModel data = arrayList.get(i);

        Uri uri = Uri.parse(sm+data.getDoctors_photo());
        Glide.with(context).load(uri).into(holder.img);

        holder.txnm.setText(data.getDoctors_name());
        holder.qualification.setText(data.getDoctors_qualification());
        holder.speciality.setText(data.getDoctors_speciality());
        holder.hospitalnm.setText(data.getDoctors_clinic_name());
        holder.fee.setText(data.getDoctors_fee());

        if (data.getAval().equals("not")){
            holder.txavlble.setText(" Today Available");
        }else {
            holder.txavlble.setText(" Not Available Today");
        }

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DoctorProfile.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });

        holder.txschedule.setText(" "+data.getStart_time()+" to "+data.getEnd_time());

        holder.btbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BookAppoinment.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });

        holder.txnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DoctorProfile.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });

        holder.feedback.setText("FeedBack ("+data.getFeedback()+")");

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txnm,qualification,speciality,hospitalnm,fee,txavlble,txschedule,feedback;
        TextView btbook;

        ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
            txnm = view.findViewById(R.id.txnm);
            qualification = view.findViewById(R.id.qualification);
            speciality = view.findViewById(R.id.speciality);
            hospitalnm = view.findViewById(R.id.hospitalnm);
            fee = view.findViewById(R.id.fee);
            txavlble = view.findViewById(R.id.txavlble);
            txschedule = view.findViewById(R.id.txschedule);
            btbook = view.findViewById(R.id.btbook);
            feedback = view.findViewById(R.id.feedback);

        }
    }

    public void updateList(ArrayList<DoctorModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}