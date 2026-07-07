package codesgesture.app.clinax.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.clinax.Models.FeedbackModel;
import codesgesture.app.clinax.Models.FeedbackModel;
import codesgesture.app.clinax.R;
import codesgesture.app.clinax.Utils.SessionManage;
import codesgesture.app.clinax.interfaces.ExtraCallBack;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {
    private ArrayList<FeedbackModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String s,sm;
    int index=-1;
    public ExtraCallBack ecb;

    public FeedbackAdapter(Context context, ArrayList<FeedbackModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getPatient_id();
    }

    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new FeedbackAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FeedbackAdapter.ViewHolder holder, final int i) {
        final FeedbackModel data = arrayList.get(i);

        holder.txreview.setText(data.getReviewer_comments());
        holder.txnm.setText(data.getReviewer_name());

        holder.ratingBar.setRating(Float.parseFloat(data.getReviewer_rating()));
        holder.ratingBar.setClickable(false);
        holder.ratingBar.setOnClickListener(null);
        holder.ratingBar.setFocusable(false);

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txnm,txreview;
        RatingBar ratingBar;

        ViewHolder(View view) {
            super(view);
            ratingBar = view.findViewById(R.id.ratingBar);
            txnm = view.findViewById(R.id.txnm);
            txreview = view.findViewById(R.id.txreview);

        }
    }

    public void updateList(ArrayList<FeedbackModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}