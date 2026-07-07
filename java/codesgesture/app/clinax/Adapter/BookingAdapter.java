package codesgesture.app.clinax.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codesgesture.app.clinax.Models.BookingModel;
import codesgesture.app.clinax.R;
import codesgesture.app.clinax.Utils.SessionManage;
import codesgesture.app.clinax.interfaces.ExtraCallBack;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private ArrayList<BookingModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String s,sm;
    int index=-1;
    public ExtraCallBack ecb;

    public BookingAdapter(Context context, ArrayList<BookingModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        s=context.getString(R.string.con);
        sm=context.getString(R.string.maincon);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getPatient_id();
    }

    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new BookingAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BookingAdapter.ViewHolder holder, final int i) {
        final BookingModel data = arrayList.get(i);

        holder.dtm.setText(data.getSchedule_date()+" "+data.getSchedule_time());
        holder.bookid.setText("Appointment ID - "+data.getBooking_id());
        holder.pnm.setText("Patient Name - "+data.getPatient_name());
        holder.dnm.setText("Doctor Name - "+data.getDoctor_name());
        holder.appstatus.setText(data.getBooking_status());
        holder.bookstatus.setText(data.getPayment_status());
        holder.hnm.setText("Clinic/Hospital Name- "+data.getDoctors_clinic_name());
        holder.hadd.setText("Address - "+data.getDoctors_address());

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dtm,bookid,pnm,dnm,appstatus,bookstatus,hnm,hadd;
        ImageView img;

        ViewHolder(View view) {
            super(view);
            dtm = view.findViewById(R.id.dtm);
            bookid = view.findViewById(R.id.bookid);
            pnm = view.findViewById(R.id.pnm);
            appstatus = view.findViewById(R.id.appstatus);
            bookstatus = view.findViewById(R.id.bookstatus);
            hnm = view.findViewById(R.id.hnm);
            hadd = view.findViewById(R.id.hadd);
            dnm = view.findViewById(R.id.dnm);
            img = view.findViewById(R.id.img);

        }
    }
    public void updateList(ArrayList<BookingModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }
}