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

import codesgesture.app.clinax.ActivityAllSDoctors;
import codesgesture.app.clinax.Models.SpecialityModel;
import codesgesture.app.clinax.R;
import codesgesture.app.clinax.Utils.SessionManage;

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.ViewHolder> {
    private ArrayList<SpecialityModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String s,sm;

    public SpecialityAdapter(Context context, ArrayList<SpecialityModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getPatient_id();
    }

    @Override
    public SpecialityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new SpecialityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SpecialityAdapter.ViewHolder holder, final int i) {
        final SpecialityModel data = arrayList.get(i);

        Uri uri = Uri.parse(sm+data.getSpeciality_icon());
        Glide.with(context).load(uri).into(holder.img);

        holder.nm.setText(data.getSpeciality_name());

        holder.btbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityAllSDoctors.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView nm;
        Button btbook;

        ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
            nm = view.findViewById(R.id.nm);
            btbook = view.findViewById(R.id.btbook);

        }
    }

    public void updateList(ArrayList<SpecialityModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}