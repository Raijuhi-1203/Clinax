package codesgesture.app.clinax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.clinax.MainActivity;
import codesgesture.app.clinax.Models.SlotModel;
import codesgesture.app.clinax.Models.SlotModel;
import codesgesture.app.clinax.R;
import codesgesture.app.clinax.Services.UserUtil;
import codesgesture.app.clinax.Utils.SessionManage;
import codesgesture.app.clinax.interfaces.ExtraCallBack;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder> {
    private ArrayList<SlotModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String s,sm;
    int index=-1;
    public ExtraCallBack ecb;

    public SlotAdapter(Context context, ArrayList<SlotModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getPatient_id();
    }

    @Override
    public SlotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new SlotAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SlotAdapter.ViewHolder holder, final int i) {
        final SlotModel data = arrayList.get(i);

        holder.tm.setText(data.getSchedule_time());

        holder.tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecb.OnCompleted(data.getSchedule_time());
                index=i;
                notifyDataSetChanged();
//                Intent intent=new Intent(context, BookAppoinment.class);
//                intent.putExtra("data",data);
//                context.startActivity(intent);
            }
        });

        if (data.getSchedule_no_of_slot().equals("0")){
            holder.tm.setFocusable(false);
            holder.tm.setBackgroundResource(R.drawable.grey_box);
        }else {
            holder.tm.setFocusable(true);
        }

        if (index == i){
            holder.tm.setBackgroundResource(R.drawable.blue_box);
        }else {

            if (data.getSchedule_no_of_slot().equals("0")){
                holder.tm.setBackgroundResource(R.drawable.grey_box);
                holder.tm.setFocusable(false);
                holder.tm.setClickable(false);
            }else {
                holder.tm.setBackgroundResource(R.drawable.lightgreybt);
            }
        }

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tm;
        ViewHolder(View view) {
            super(view);
            tm = view.findViewById(R.id.tm);
        }
    }

    public void updateList(ArrayList<SlotModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}