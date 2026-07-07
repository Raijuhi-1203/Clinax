package codesgesture.app.clinax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import codesgesture.app.clinax.BookAppoinment;
import codesgesture.app.clinax.DoctorProfile;
import codesgesture.app.clinax.Models.DateModel;
import codesgesture.app.clinax.Models.DateModel;
import codesgesture.app.clinax.R;
import codesgesture.app.clinax.Utils.SessionManage;
import codesgesture.app.clinax.interfaces.ExtraCallBack;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {
    private ArrayList<DateModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String s,sm;
    int index=-1;
    public ExtraCallBack ecb;

    public DateAdapter(Context context, ArrayList<DateModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getPatient_id();
    }

    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new DateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DateAdapter.ViewHolder holder, final int i) {
        final DateModel data = arrayList.get(i);

        holder.dt.setText(data.getSchedule_date());

        holder.dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecb.OnCompleted(data.getSchedule_date());
                index=i;
                notifyDataSetChanged();

//                Intent intent=new Intent(context, BookAppoinment.class);
//                intent.putExtra("data",data);
//                context.startActivity(intent);
            }
        });

        if (index == i){
            holder.dt.setBackgroundResource(R.drawable.green_box);
        }else {
            holder.dt.setBackgroundResource(R.drawable.lightgreybt);
        }


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dt;

        ViewHolder(View view) {
            super(view);
            dt = view.findViewById(R.id.dt);

        }
    }

    public void updateList(ArrayList<DateModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}